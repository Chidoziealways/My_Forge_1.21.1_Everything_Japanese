package net.Chidoziealways.everythingjapanese.custom

import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class ModdedCakeBlock(properties: BlockBehaviour.Properties) : Block(properties) {
    init {
        // Initialize default state with BITES set to 0
        this.registerDefaultState(
            this.stateDefinition.any().setValue(BITES, 0)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(ModdedCakeBlock.Companion.BITES)
    }


    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE_BY_BITE[state.getValue(BITES)]
    }

    public override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hit: BlockHitResult
    ): InteractionResult {
        if (level.isClientSide) {
            if (eat(level, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty) {
                return InteractionResult.CONSUME
            }
        }

        return eat(level, pos, state, player)
    }

    public override fun updateShape(
        state: BlockState,
        reader: LevelReader,
        scheduledTickAccess: ScheduledTickAccess,
        currentPos: BlockPos,
        facing: net.minecraft.core.Direction,
        pos2: BlockPos,
        newState: BlockState,
        randomSource: RandomSource
    ): BlockState {
        return if (facing == net.minecraft.core.Direction.DOWN && !state.canSurvive(reader, currentPos))
            net.minecraft.world.level.block.Blocks.AIR.defaultBlockState()
        else
            super.updateShape(state, reader, scheduledTickAccess, currentPos, facing, pos2, newState, randomSource)
    }

    public override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): kotlin.Boolean {
        return level.getBlockState(pos.below()).isSolid()
    }

    public override fun hasAnalogOutputSignal(state: BlockState): Boolean {
        return true
    }

    public override fun getAnalogOutputSignal(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        p_435855_: Direction
    ): Int {
        return getOutputSignal(state.getValue<Int>(BITES))
    }

    public override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return false
    }

    companion object {
        const val MAX_BITES: Int = 16
        val BITES: IntegerProperty = ModBlockStateProperties.BITES_16
        val FULL_CAKE_SIGNAL: Int = getOutputSignal(0)
        protected const val AABB_OFFSET: Float = 1.0f
        protected const val AABB_SIZE_PER_BITE: Float = 2.0f
        protected val SHAPE_BY_BITE: Array<VoxelShape> = arrayOf<VoxelShape>(
            box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(2.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(4.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(6.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(8.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(10.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(12.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(13.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(14.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(15.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            box(16.0, 0.0, 1.0, 16.0, 8.0, 15.0),
            box(17.0, 0.0, 1.0, 17.0, 8.0, 15.0),
        )

        protected fun eat(
            level: LevelAccessor,
            pos: BlockPos,
            state: BlockState,
            player: net.minecraft.world.entity.player.Player
        ): InteractionResult {
            if (!player.canEat(false)) {
                return InteractionResult.PASS
            } else {
                player.awardStat(net.minecraft.stats.Stats.EAT_CAKE_SLICE)
                player.getFoodData().eat(2, 0.1f)
                val i: kotlin.Int = state.getValue<Int>(BITES)
                level.gameEvent(player, GameEvent.EAT, pos)
                if (i < 16) {
                    level.setBlock(
                        pos,
                        state.setValue<Int, Int?>(BITES, i + 1),
                        3
                    )
                } else {
                    level.removeBlock(pos, false)
                    level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos)
                }

                return InteractionResult.SUCCESS
            }
        }

        fun getOutputSignal(bites: kotlin.Int): kotlin.Int {
            return (16 - bites) * 2
        }
    }
}
