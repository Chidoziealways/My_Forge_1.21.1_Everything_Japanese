package net.Chidoziealways.everythingjapanese.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class StoneLanternBlock(props: Properties) : Block(props) {
    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return SHAPE
    }

    override fun getCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    companion object {
        val SHAPE: VoxelShape = Shapes.or(
            box(6.0, 1.0, 6.0, 10.0, 3.0, 10.0),
            box(7.0, 3.0, 7.0, 9.0, 9.0, 9.0),
            box(2.0, 0.0, 2.0, 14.0, 1.0, 14.0),
            box(6.0, 9.0, 6.0, 10.0, 15.0, 10.0),
            box(4.0, 15.0, 4.0, 12.0, 16.0, 12.0)
        )
    }
}