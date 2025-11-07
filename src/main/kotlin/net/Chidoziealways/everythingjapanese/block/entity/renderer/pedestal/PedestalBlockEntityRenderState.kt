package net.Chidoziealways.everythingjapanese.block.entity.renderer.pedestal

import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.world.item.ItemStack

class PedestalBlockEntityRenderState: BlockEntityRenderState() {
    var blockEntity: PedestalBlockEntity? = null
    var itemStack: ItemStack? = null

    var renderingRotation: Float = 0f

    var itemStackRenderState: ItemStackRenderState? = null
}