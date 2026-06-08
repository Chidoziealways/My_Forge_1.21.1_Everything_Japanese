package net.Chidoziealways.everythingjapanese.screen.custom.cursedblock

import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.block.entity.custom.CursedBlockEntity
import net.Chidoziealways.everythingjapanese.screen.ModMenuTypes
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity

class CursedBlockMenu(id: Int, inv: Inventory, entity: BlockEntity?) : AbstractContainerMenu(ModMenuTypes.CURSED_BLOCK_MENU, id) {

    init {
        println("MENU CRETED")
    }

    private var playerName = ""
    private val toCurse: Player?
        get() {
            val serverLevel = level as? ServerLevel ?: return null
            return serverLevel.players()
                .firstOrNull {it.plainTextName.equals(playerName, true)}
        }

    constructor(id: Int, inv: Inventory, buf: FriendlyByteBuf) : this(
        id,
        inv,
        inv.player.level().getBlockEntity(buf.readBlockPos())
    )
    private val level: Level
    private val blockEntity: CursedBlockEntity

    init {
        this.level = inv.player.level()
        this.blockEntity = entity as? CursedBlockEntity
            ?: error("Expected CursedBlockEntity")
    }

    override fun quickMoveStack(
        p0: Player,
        p1: Int
    ): ItemStack {
        return ItemStack.EMPTY
    }

    override fun stillValid(p0: Player): Boolean {
        return stillValid(
            ContainerLevelAccess.create(level, blockEntity.blockPos),
            p0, JModBlocks.CURSED_BLOCK
        )
    }

    fun setPlayerName(name: String) {
        playerName = name
    }
}