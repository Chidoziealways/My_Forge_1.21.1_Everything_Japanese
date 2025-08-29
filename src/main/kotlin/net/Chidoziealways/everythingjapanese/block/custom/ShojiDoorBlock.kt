package net.Chidoziealways.everythingjapanese.block.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DoorHingeSide
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class ShojiDoorBlock(properties: BlockBehaviour.Properties): BaseEntityBlock(properties) {

    init {
        registerDefaultState(
            this.stateDefinition
                .any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(OPEN, FACING, HINGE)
    }


    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hit: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide) {
            val blockEntity = level.getBlockEntity(pos)
            if (blockEntity is ShojiDoorBlockEntity) {
                level.gameEvent(
                    player,
                    if (isOpen(state)) GameEvent.BLOCK_OPEN else GameEvent.BLOCK_CLOSE,
                    pos
                )
                val newState = state.cycle(OPEN)
                level.setBlock(pos, newState, 3)
            }
        }

        return InteractionResult.SUCCESS
    }

    fun isOpen(state: BlockState): Boolean{
        return state.getValue(OPEN)
    }


    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    // This one controls collision (can you walk through?)
    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (state.getValue(OPEN)) Shapes.empty() else getClosedShape(state)
    }

    // This one controls clicking/interaction
    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return getClosedShape(state)
    }

    private fun getClosedShape(state: BlockState): VoxelShape {
        return when (state.getValue(FACING)) {
            Direction.NORTH -> box(0.0, 0.0, 0.0, 16.0, 30.0, 3.0)
            Direction.SOUTH -> box(0.0, 0.0, 13.0, 16.0, 30.0, 16.0)
            Direction.EAST -> box(13.0, 0.0, 0.0, 16.0, 30.0, 16.0)
            Direction.WEST -> box(0.0, 0.0, 0.0, 3.0, 30.0, 16.0)
            else -> box(0.0, 0.0, 0.0, 3.0, 30.0, 16.0)
        }
    }


    override fun getInteractionShape(p_60547_: BlockState, p_60548_: BlockGetter, p_60549_: BlockPos): VoxelShape {
        return box(0.0, 0.0, 0.0, 16.0, 30.0, 16.0)
    }

    override fun getRenderShape(p_60550_: BlockState): RenderShape = RenderShape.INVISIBLE

    override fun newBlockEntity(
        p_153215_: BlockPos,
        p_153216_: BlockState
    ): BlockEntity? {
        return ShojiDoorBlockEntity(p_153215_, p_153216_)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
        val level = context.level
        val pos = context.clickedPos
        val facing = context.horizontalDirection.opposite
        val hinge = getHinge(context)

        return defaultBlockState()
            .setValue(FACING, facing)
            .setValue(HINGE, hinge)
            .setValue(OPEN, false)
    }

    private fun getHinge(ctx: BlockPlaceContext): DoorHingeSide {
        val click = ctx.clickLocation
        val blockPos = ctx.clickedPos
        val dx = click.x - blockPos.x
        val dz = click.z - blockPos.z
        val direction = ctx.horizontalDirection

        return when (direction) {
            Direction.NORTH -> if (dx < 0.5) DoorHingeSide.LEFT else DoorHingeSide.RIGHT
            Direction.SOUTH -> if (dx > 0.5) DoorHingeSide.LEFT else DoorHingeSide.RIGHT
            Direction.WEST  -> if (dz > 0.5) DoorHingeSide.LEFT else DoorHingeSide.RIGHT
            Direction.EAST  -> if (dz < 0.5) DoorHingeSide.LEFT else DoorHingeSide.RIGHT
            else -> DoorHingeSide.LEFT
        }
    }

    companion object {
        val OPEN: BooleanProperty = BlockStateProperties.OPEN
        val FACING: EnumProperty<Direction> = HorizontalDirectionalBlock.FACING
        val HINGE: EnumProperty<DoorHingeSide> = BlockStateProperties.DOOR_HINGE

        val CODEC: MapCodec<ShojiDoorBlock?> =
            BlockBehaviour.simpleCodec<ShojiDoorBlock?> { pProperties: BlockBehaviour.Properties ->
                ShojiDoorBlock(pProperties)
            }
    }
}