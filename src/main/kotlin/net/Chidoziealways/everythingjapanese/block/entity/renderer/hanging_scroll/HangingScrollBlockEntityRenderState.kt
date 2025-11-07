package net.Chidoziealways.everythingjapanese.block.entity.renderer.hanging_scroll

import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.Design
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState

class HangingScrollBlockEntityRenderState: BlockEntityRenderState() {
    var currentDesign: Design? = null
}