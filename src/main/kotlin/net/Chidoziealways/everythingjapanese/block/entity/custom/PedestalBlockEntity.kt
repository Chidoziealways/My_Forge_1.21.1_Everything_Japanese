package net.Chidoziealways.everythingjapanese.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalMenu
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import net.minecraftforge.items.ItemStackHandler
import java.util.function.Consumer

class PedestalBlockEntity(pPos: BlockPos, pBlockState: BlockState) :
    BlockEntity(ModBlockEntities.PEDESTAL_BE?.get(), pPos, pBlockState), MenuProvider {
    val inventory: ItemStackHandler = object : ItemStackHandler(1) {
        override fun getStackLimit(slot: Int, stack: ItemStack): Int {
            return 1
        }

        override fun onContentsChanged(slot: Int) {
            setChanged()
            if (!level!!.isClientSide()) {
                level?.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3)
            }
        }
    }

    private var rotation = 0f

    val renderingRotation: Float
        get() {
            rotation += 0.5f
            if (rotation >= 360) {
                rotation = 0f
            }
            return rotation
        }

    fun clearContents() {
        inventory.setStackInSlot(0, ItemStack.EMPTY)
    }

    override fun preRemoveSideEffects(p_397404_: BlockPos, p_395805_: BlockState) {
        val inv: SimpleContainer = SimpleContainer(inventory.getSlots())
        for (i in 0..<inventory.getSlots()) {
            inv.setItem(i, inventory.getStackInSlot(i))
        }

        Containers.dropContents(this.level, this.worldPosition, inv)
        super.preRemoveSideEffects(p_397404_, p_395805_)
    }

    override fun saveAdditional(output: ValueOutput) {
        super.saveAdditional(output)
        output.store<CompoundTag?>("inventory", CompoundTag.CODEC, inventory.serializeNBT(this.level?.registryAccess()))
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)
        input.read<CompoundTag?>("inventory", CompoundTag.CODEC)
            .ifPresent(Consumer { tag: CompoundTag? -> inventory.deserializeNBT(level?.registryAccess(), tag) })
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener?>? {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    override fun createMenu(i: Int, inventory: Inventory, player: Player): AbstractContainerMenu? {
        return PedestalMenu(i, inventory, this)
    }

    override fun getUpdateTag(pRegistries: HolderLookup.Provider): CompoundTag {
        return saveWithoutMetadata(pRegistries)
    }

    override fun getDisplayName(): Component {
        return Component.literal("Pedestal");
    }
}
