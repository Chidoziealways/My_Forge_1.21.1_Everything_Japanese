package net.Chidoziealways.everythingjapanese.block.custom

import net.Chidoziealways.everythingjapanese.block.custom.FusumaDoorBlock.Companion.HINGE
import net.Chidoziealways.everythingjapanese.block.custom.FusumaDoorBlock.Companion.OPEN
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class TatamiMatBlock(props: Properties): Block(props) {

    init {
        this.registerDefaultState(
            this.stateDefinition
                .any()
                .setValue(FACING, Direction.NORTH)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        val facing = state.getValue(FACING)
        return state.setValue(FACING, rotation.rotate(facing))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
        val facing = context.horizontalDirection.opposite

        return defaultBlockState()
            .setValue(FACING, facing)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when (state.getValue(FACING)) {
            Direction.EAST -> BASE_SHAPE
            Direction.WEST -> box(-32.0, 0.0, 0.0, 16.0, 1.0, 16.0)
            Direction.NORTH -> box(0.0, 0.0, -32.0, 16.0, 1.0, 16.0)
            Direction.SOUTH -> box(0.0, 0.0, 0.0, 16.0, 1.0, 48.0)
            else -> BASE_SHAPE
        }
    }

    override fun getCollisionShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when (state.getValue(FACING)) {
            Direction.EAST -> BASE_SHAPE
            Direction.WEST -> box(-32.0, 0.0, 0.0, 16.0, 1.0, 16.0)
            Direction.NORTH -> box(0.0, 0.0, -32.0, 16.0, 1.0, 16.0)
            Direction.SOUTH -> box(0.0, 0.0, 0.0, 16.0, 1.0, 48.0)
            else -> BASE_SHAPE
        }
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return !level.getBlockState(pos.below()).isAir
    }

    companion object {
        val FACING = HorizontalDirectionalBlock.FACING

        private val BASE_SHAPE = box(0.0, 0.0, 0.0, 48.0, 1.0, 16.0)
    }
}