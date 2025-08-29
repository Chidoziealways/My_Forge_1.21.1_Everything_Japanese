package net.Chidoziealways.everythingjapanese.block.custom

import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.TransparentBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState

class PaperWindowBlock(properties: BlockBehaviour.Properties): TransparentBlock(properties) {
    override fun getRenderShape(p_60550_: BlockState): RenderShape {
        return RenderShape.MODEL
    }
}