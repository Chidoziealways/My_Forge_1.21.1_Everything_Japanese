package net.Chidoziealways.everythingjapanese.block.entity.custom

import com.mojang.serialization.Codec
import net.Chidoziealways.everythingjapanese.block.custom.MoneyVaultBlock
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.money.IMoneyCapability
import net.Chidoziealways.everythingjapanese.block.custom.MoneyVaultBlock.VaultSection
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import net.neoforged.neoforge.capabilities.BlockCapabilityCache
import org.apache.logging.log4j.LogManager

class MoneyVaultBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModBlockEntities.MONEY_VAULT_BE, pos, state) {
    private var moneyCache: BlockCapabilityCache<IMoneyCapability, Void?>? = null
    private var controller: BlockPos? = null

    override fun setRemoved() {
        val controllerBE = getControllerBE()
        super.setRemoved()
        if (controllerBE != null && !controllerBE.isRemoved) {
            controllerBE.updateConnectivity()
        }
    }

    fun isController(): Boolean = controller == null || controller == worldPosition

    fun getControllerBE(): MoneyVaultBlockEntity? {
        if (isController()) return this
        val be = level?.getBlockEntity(controller ?: return null)
        return be as? MoneyVaultBlockEntity
    }

    fun getControllerCapability(): IMoneyCapability? =
        getControllerBE()?.moneyCache?.getCapability()

    fun setController(pos: BlockPos?) {
        if (controller == pos) return
        controller = pos
        setChanged()
    }

    fun getWorldPositionEX(): BlockPos = worldPosition

    /** Update connectivity, otherwise fallback to SINGLE */
    fun updateConnectivity() {
        if (level!!.isClientSide) return

        // Flood-fill connected vault blocks
        val visited = mutableSetOf<BlockPos>()
        val queue = ArrayDeque<BlockPos>()
        queue.add(worldPosition)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (!visited.add(current)) continue

            for (dir in Direction.entries) {
                val next = current.relative(dir)
                if (!visited.contains(next) && level!!.getBlockState(next).block is MoneyVaultBlock) {
                    queue.add(next)
                }
            }
        }

        if (visited.isEmpty()) return

        val controllerPos = getControllerBE()?.worldPosition ?: worldPosition

        // Local space relative to controller
        val localVisited = visited.map { pos ->
            Triple(pos, pos.x - controllerPos.x, pos.z - controllerPos.z)
        }

        val minX = localVisited.minOf { it.second }
        val maxX = localVisited.maxOf { it.second }
        val minZ = localVisited.minOf { it.third }
        val maxZ = localVisited.maxOf { it.third }

        val sizeX = maxX - minX + 1
        val sizeZ = maxZ - minZ + 1

        // Shift X/Z to start at 0 for proper LEFT/MIDDLE/RIGHT and FRONT/MIDDLE/BACK assignment
        val normalized = localVisited.map { (pos, lx, lz) ->
            Triple(pos, lx - minX, lz - minZ)
        }

        val minY = visited.minOf { it.y }
        val maxY = visited.maxOf { it.y }
        val height = maxY - minY + 1

        val valid = (sizeX in 2..5) && (sizeZ in 2..5)

        // --- NEW: Calculate dynamic max money ---
        val vaultVolume = sizeX * sizeZ * height
        val baseMaxPerBlock = 1000 // adjust as needed
        getControllerCapability()?.setMaxMoney(baseMaxPerBlock * vaultVolume)
        println(getControllerCapability()?.getMaxMoney())

        for ((pos, nx, nz) in normalized) {
            val state = level!!.getBlockState(pos)
            if (state.block !is MoneyVaultBlock) continue

            if (!valid) {
                level!!.setBlock(
                    pos,
                    state.setValue(MoneyVaultBlock.SECTION, VaultSection.SINGLE),
                    3
                )
                continue
            }

            val row = when (pos.y) {
                minY -> "BOTTOM"
                maxY -> "TOP"
                else -> "MIDDLE"
            }

            val lx = pos.x - controllerPos.x
            //val lz = pos.z - controllerPos.z

            val col = when {
                lx == minX -> "LEFT"   // +X = left
                lx == maxX -> "RIGHT"  // -X = right
                else -> "MIDDLE"
            }

            val depth = when (nz) {
                0 -> "FRONT"
                sizeZ - 1 -> "BACK"
                else -> "MIDDLE"
            }

            val sectionName = "${row}_${col}_${depth}"
            val section = VaultSection.valueOf(sectionName)
            val be = level!!.getBlockEntity(pos) as? MoneyVaultBlockEntity
            be?.setController(controllerPos) // make sure all connected blocks point to the controller

            level!!.setBlock(
                pos,
                state.setValue(MoneyVaultBlock.SECTION, section),
                3
            )
        }
    }

    override fun onLoad() {
        super.onLoad()
        if (level?.isClientSide == false) {
            moneyCache = BlockCapabilityCache.create(
                ModCapabilities.MONEY_CAPABILITY_BLOCK,
                level as ServerLevel,
                worldPosition,
                null,
                { !isRemoved },
                { onMoneyCapInvalidated() }
            )
        }
    }

    private fun onMoneyCapInvalidated() {
        level?.invalidateCapabilities(controller ?: worldPosition)
    }

    override fun saveAdditional(output: ValueOutput) {
        val money = getControllerCapability()?.getMoney() ?: 0
        output.store("stored_money", Codec.INT, money)
        super.saveAdditional(output)
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)
        val money = input.read("stored_money", Codec.INT).orElse(0)
        getControllerCapability()?.setMoney(money)
    }

    fun depositFromPlayer(cap: IMoneyCapability, amount: Int, player: ServerPlayer): String {
        val controllerCapability = getControllerCapability() ?: return "The Controller is NULL!"
        if ((controllerCapability.getMoney() +  amount) > controllerCapability.getMaxMoney()!!) {
            return "The Vault is full!"
        }
        if (cap.getMoney() <= 0) {
            return "There is no money to Deposit!"
        }
        log.info("About to remove money from the player")
        cap.removeMoney(amount, player)
        log.info("Removed money from player, adding to vault")
        controllerCapability.addMoney(amount)
        log.info("Added to vault")
        setChanged() // mark block for saving

        return "Successfully Deposited Money!"
    }

    fun deposit(cap: IMoneyCapability, amount: Int): String {
        val controllerCapability = getControllerCapability() ?: return "The Controller is NULL!"
        if ((controllerCapability.getMoney() +  amount) > controllerCapability.getMaxMoney()!!) {
            return "The Vault is full!"
        }
        if (cap.getMoney() <= 0) {
            return "There is no Money to deposit!"
        }
        log.info("About to remove money from the card")
        cap.removeMoney(amount)
        log.info("Removed money from card, adding to vault")
        controllerCapability.addMoney(amount)
        log.info("Added to vault credit card side")
        setChanged() // mark block for saving

        return "Successfully Deposited Money!"
    }

    fun withdrawFromPlayer(capNew: IMoneyCapability, amount: Int, player: ServerPlayer): Boolean {
        log.info("Withdrawing money from the vault to the player!")
        val cap = getControllerCapability()
        if (cap == null) {
            log.info("MoneyCache Capability is NULL Player side!")
            return false
        }
        return if (cap.getMoney() >= amount) {
            log.info("About to remove money from vault player side")
            cap.removeMoney(amount)
            log.info("Removed Money from vault about to add money to player")
            capNew.addMoney(amount, player)
            log.info("Added Money to player!")
            setChanged()
            true
        } else false
    }

    fun withdraw(capNew: IMoneyCapability, amount: Int): Boolean {
        log.info("Withdrawing money from the vault to the credit card!")
        val cap = getControllerCapability()
        if (cap == null) {
            log.info("MoneyCache Capability is NULL")
            return false
        }
        return if (cap.getMoney() >= amount) {
            log.info("About to remove money from vault")
            cap.removeMoney(amount)
            log.info("Removed Money from vault about to add money to credit card")
            capNew.addMoney(amount)
            log.info("Added Money to credit card!")
            setChanged()
            true
        } else false
    }

    companion object {
        val log = LogManager.getLogger(MoneyVaultBlockEntity::class.java)
    }
}