package net.Chidoziealways.everythingjapanese.block.custom;

import net.Chidoziealways.everythingjapanese.block.state.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModdedCakeBlock extends Block {
    public static final int MAX_BITES = 16;
    public static final IntegerProperty BITES = ModBlockStateProperties.BITES;
    public static final int FULL_CAKE_SIGNAL = getOutputSignal(0);
    protected static final float AABB_OFFSET = 1.0F;
    protected static final float AABB_SIZE_PER_BITE = 2.0F;
    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(2.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(4.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(6.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(8.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(10.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(12.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(13.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(14.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(15.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(16.0, 0.0, 1.0, 16.0, 8.0, 15.0),
            Block.box(17.0, 0.0, 1.0, 17.0, 8.0, 15.0),
    };

    public ModdedCakeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        // Initialize default state with BITES set to 0
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_BITE[state.getValue(BITES)];
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            if (eat(level, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(level, pos, state, player);
    }

    protected static InteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(2, 0.1F);
            int i = state.getValue(BITES);
            level.gameEvent(player, GameEvent.EAT, pos);
            if (i < 16) {
                level.setBlock(pos, state.setValue(BITES, i + 1), 3);
            } else {
                level.removeBlock(pos, false);
                level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos pos2, BlockState newState, RandomSource randomSource) {
        return facing == Direction.DOWN && !state.canSurvive(reader, currentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, reader, scheduledTickAccess, currentPos, facing, pos2, newState, randomSource);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return getOutputSignal(state.getValue(BITES));
    }

    public static int getOutputSignal(int bites) {
        return (16 - bites) * 2;
    }

    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
}
