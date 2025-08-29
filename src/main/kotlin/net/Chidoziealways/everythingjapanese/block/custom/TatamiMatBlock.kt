package net.Chidoziealways.everythingjapanese.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class TatamiMatBlock(props: Properties): Block(props) {

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return box(0.0, 0.0, 0.0, 48.0, 1.0, 16.0)
    }

    override fun getCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return box(0.0, 0.0, 0.0, 48.0, 1.0, 16.0)
    }

    override fun getInteractionShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return box(0.0, 0.0, 0.0, 48.0, 1.0, 16.0)
    }

    override fun getBlockSupportShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return box(0.0, 0.0, 0.0, 48.0, 1.0, 16.0)
    }

    override fun getEntityInsideCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        entity: Entity
    ): VoxelShape {
        return box(0.0, 0.0, 0.0, 48.0, 1.0, 16.0)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return level.getBlockState(pos.below()).isAir
    }
}