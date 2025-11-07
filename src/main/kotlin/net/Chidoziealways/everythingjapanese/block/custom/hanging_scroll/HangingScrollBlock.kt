package net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.block.entity.custom.HangingScrollBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class HangingScrollBlock(properties: Properties): BaseEntityBlock(properties) {
    init {
        registerDefaultState(
            stateDefinition
                .any()
                .setValue(FACING, Direction.NORTH)
        )
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when (state.getValue(FACING)) {
            Direction.NORTH -> box(2.0, 0.0, 1.0, 14.0, 28.0, 2.0)
            Direction.SOUTH -> box(2.0, 0.0, 14.0, 14.0, 28.0, 15.0)
            Direction.WEST -> box(1.0, 0.0, 2.0, 2.0, 28.0, 14.0)
            Direction.EAST -> box(14.0, 0.0, 2.0, 15.0, 28.0, 14.0)
            Direction.UP -> box(2.0, 14.0, 0.0, 14.0, 15.0, 28.0)  // top-facing plane
            Direction.DOWN -> box(2.0, 1.0, -12.0, 14.0, 2.0, 16.0)  // bottom-facing plane
        }
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        val be = level.getBlockEntity(pos) as? HangingScrollBlockEntity ?: return InteractionResult.PASS
        val currentIndex = be.allDesigns.indexOf(be.currentDesign).takeIf { it >= 0 } ?: -1
        val nextIndex = (currentIndex + 1) % be.allDesigns.size
        be.currentDesign = be.allDesigns[nextIndex]
        be.setChanged()
        level.sendBlockUpdated(pos, state, state, 3)
        println(be.currentDesign)
        return InteractionResult.SUCCESS
    }

    override fun codec(): MapCodec<out BaseEntityBlock> = CODEC

    override fun newBlockEntity(
        pos: BlockPos,
        state: BlockState
    ): BlockEntity {
        return HangingScrollBlockEntity(pos, state)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
        // Or, if you want strictly vertical if clicked on top/bottom:
        val direction = context.clickedFace.opposite

        // Assuming your block has a FACING property that supports all 6 directions:
        return stateDefinition.any().setValue(FACING, direction)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return when (state.getValue(FACING)) {
            Direction.SOUTH -> !level.getBlockState(pos.south()).isAir
            Direction.NORTH -> !level.getBlockState(pos.north()).isAir
            Direction.EAST -> !level.getBlockState(pos.east()).isAir
            Direction.WEST -> !level.getBlockState(pos.west()).isAir
            Direction.UP -> !level.getBlockState(pos.above()).isAir
            Direction.DOWN -> !level.getBlockState(pos.below()).isAir
        }
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        val facing = state.getValue(FACING)
        return state.setValue(FACING, rotation.rotate(facing))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(FACING)
    }

    companion object {
        val CODEC: MapCodec<HangingScrollBlock> = simpleCodec{ properties ->
            HangingScrollBlock(properties)
        }

        val FACING: EnumProperty<Direction> = BlockStateProperties.FACING
    }
}