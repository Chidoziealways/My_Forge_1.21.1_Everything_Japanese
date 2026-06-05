package net.Chidoziealways.everythingjapanese.block.custom

import net.Chidoziealways.everythingjapanese.custom.ModdedCakeBlock
import net.Chidoziealways.everythingjapanese.custom.ModdedCakeBlock.Companion.eat
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
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class JapaneseCheesecakeBlock(properties: Properties): Block(properties) {
    init {
        registerDefaultState(
            stateDefinition.any().setValue(BITES, 0)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(BITES)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return SHAPE_BY_BITE
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

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return level.getBlockState(pos.below()).isSolid
    }

    override fun hasAnalogOutputSignal(state: BlockState): Boolean {
        return true
    }

    override fun getAnalogOutputSignal(state: BlockState, level: Level, pos: BlockPos, p_435855_: Direction): Int {
        return getOutputSignal(state.getValue(BITES))
    }

    companion object {
        const val MAX_BITES = 11
        val BITES = ModBlockStateProperties.BITES_11

        protected val SHAPE_BY_BITE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0)

        protected fun eat(
            level: LevelAccessor,
            pos: BlockPos,
            state: BlockState,
            player: Player
        ): InteractionResult {
            if (!player.canEat(false)) {
                return InteractionResult.PASS
            } else {
                player.awardStat(net.minecraft.stats.Stats.EAT_CAKE_SLICE)
                player.getFoodData().eat(2, 0.1f)
                val i: Int = state.getValue(BITES)
                level.gameEvent(player, GameEvent.EAT, pos)
                if (i < 11) {
                    level.setBlock(
                        pos,
                        state.setValue(BITES, i + 1),
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
            return (11 - bites) * 2
        }
    }
}