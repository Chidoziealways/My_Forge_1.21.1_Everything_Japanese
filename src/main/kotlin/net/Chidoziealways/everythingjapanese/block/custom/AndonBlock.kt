package net.Chidoziealways.everythingjapanese.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class AndonBlock(props: Properties) : Block(props) {
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
            box(0.0, 0.0, 0.0, 1.0, 16.0, 1.0),
            box(15.0, 0.0, 0.0, 16.0, 16.0, 1.0),
            box(0.0, 0.0, 15.0, 1.0, 16.0, 16.0),
            box(15.0, 0.0, 15.0, 16.0, 16.0, 16.0),
            box(0.0, 1.0, 1.0, 1.0, 2.0, 15.0),
            box(0.0, 14.0, 1.0, 1.0, 15.0, 15.0),
            box(15.0, 1.0, 1.0, 16.0, 2.0, 15.0),
            box(15.0, 14.0, 1.0, 16.0, 15.0, 15.0),
            box(0.0, 1.0, 1.0, 1.0, 2.0, 15.0),
            box(1.0, 1.0, 15.0, 15.0, 2.0, 16.0),
            box(1.0, 1.0, 0.0, 15.0, 2.0, 1.0),
            box(1.0, 14.0, 15.0, 15.0, 15.0, 16.0),
            box(1.0, 14.0, 0.0, 15.0, 15.0, 1.0),
            box(1.0, 2.0, 0.0, 15.0, 14.0, 1.0),
            box(1.0, 2.0, 15.0, 15.0, 14.0, 16.0),
            box(0.0, 2.0, 1.0, 1.0, 14.0, 15.0),
            box(15.0, 2.0, 1.0, 16.0, 14.0, 15.0),
            box(1.0, 1.0, 1.0, 15.0, 2.0, 15.0),
            box(1.0, 14.0, 1.25, 15.0, 15.0, 15.25)
        )
    }
}