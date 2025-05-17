package net.Chidoziealways.everythingjapanese.block.custom;

import com.mojang.serialization.MapCodec;
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

public class HellPortalBlock extends Block implements Portal{
    private static final Logger LOGGER = LoggerFactory.getLogger(HellPortalBlock.class);
    public static final MapCodec<HellPortalBlock> CODEC = simpleCodec(HellPortalBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final Map<Direction.Axis, VoxelShape> SHAPES = Shapes.rotateHorizontalAxis(Block.column(4.0, 16.0, 0.0, 16.0));

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public HellPortalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected VoxelShape getEntityInsideCollisionShape(BlockState p_396779_, BlockGetter p_395715_, BlockPos p_396107_, Entity p_395206_) {
        return p_396779_.getShape(p_395715_, p_396107_);
    }

    @Override
    protected void entityInside(BlockState p_54915_, Level p_54916_, BlockPos p_54917_, Entity p_54918_, InsideBlockEffectApplier p_392916_) {
        if (p_54918_.canUsePortal(false)) {
            p_54918_.setAsInsidePortal(this, p_54917_);
        }
    }

    @Override
    public int getPortalTransitionTime(ServerLevel p_342064_, Entity p_344634_) {
        return p_344634_ instanceof Player player
                ? Math.max(0, p_342064_.getGameRules().getInt(player.getAbilities().invulnerable ? GameRules.RULE_PLAYERS_NETHER_PORTAL_CREATIVE_DELAY : GameRules.RULE_PLAYERS_NETHER_PORTAL_DEFAULT_DELAY))
                : 0;
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(ServerLevel pLevel, Entity pEntity, BlockPos pPos) {
        ResourceKey<Level> resourcekey = pLevel.dimension() == ModDimensions.HELL_LEVEL_KEY ? Level.OVERWORLD : ModDimensions.HELL_LEVEL_KEY;
        ServerLevel serverlevel = pLevel.getServer().getLevel(resourcekey);
        if (serverlevel == null) {
            return null;
        } else {
            boolean flag = serverlevel.dimension() == ModDimensions.HELL_LEVEL_KEY;
            WorldBorder worldborder = serverlevel.getWorldBorder();
            double d0 = DimensionType.getTeleportationScale(pLevel.dimensionType(), serverlevel.dimensionType());
            BlockPos blockpos = worldborder.clampToBounds(pEntity.getX() * d0, pEntity.getY(), pEntity.getZ() * d0);
            return this.getExitPortal(serverlevel, pEntity, pPos, blockpos, flag, worldborder);
        }
    }

    @javax.annotation.Nullable
    private TeleportTransition getExitPortal(
            ServerLevel pLevel, Entity pEntity, BlockPos pPos, BlockPos pExitPos, boolean pIsNether, WorldBorder pWorldBorder
    ) {
        Optional<BlockPos> optional = pLevel.getPortalForcer().findClosestPortalPosition(pExitPos, pIsNether, pWorldBorder);
        BlockUtil.FoundRectangle blockutil$foundrectangle;
        TeleportTransition.PostTeleportTransition teleporttransition$postteleporttransition;
        if (optional.isPresent()) {
            BlockPos blockpos = optional.get();
            BlockState blockstate = pLevel.getBlockState(blockpos);
            blockutil$foundrectangle = BlockUtil.getLargestRectangleAround(
                    blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, p_343533_ -> pLevel.getBlockState(p_343533_) == blockstate
            );
            teleporttransition$postteleporttransition = TeleportTransition.PLAY_PORTAL_SOUND.then(p_343530_ -> p_343530_.placePortalTicket(blockpos));
        } else {
            Direction.Axis direction$axis = pEntity.level().getBlockState(pPos).getOptionalValue(AXIS).orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> optional1 = pLevel.getPortalForcer().createPortal(pExitPos, direction$axis);
            if (optional1.isEmpty()) {
                LOGGER.error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            blockutil$foundrectangle = optional1.get();
            teleporttransition$postteleporttransition = TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
        }

        return getDimensionTransitionFromExit(pEntity, pPos, blockutil$foundrectangle, pLevel, teleporttransition$postteleporttransition);
    }

    private static TeleportTransition getDimensionTransitionFromExit(
            Entity pEntity, BlockPos pPos, BlockUtil.FoundRectangle pRectangle, ServerLevel pLevel, TeleportTransition.PostTeleportTransition pPostTeleportTransition
    ) {
        BlockState blockstate = pEntity.level().getBlockState(pPos);
        Direction.Axis direction$axis;
        Vec3 vec3;
        if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            direction$axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle blockutil$foundrectangle = BlockUtil.getLargestRectangleAround(
                    pPos, direction$axis, 21, Direction.Axis.Y, 21, p_342174_ -> pEntity.level().getBlockState(p_342174_) == blockstate
            );
            vec3 = pEntity.getRelativePortalPosition(direction$axis, blockutil$foundrectangle);
        } else {
            direction$axis = Direction.Axis.X;
            vec3 = new Vec3(0.5, 0.0, 0.0);
        }

        return createDimensionTransition(pLevel, pRectangle, direction$axis, vec3, pEntity, pPostTeleportTransition);
    }

    private static TeleportTransition createDimensionTransition(
            ServerLevel pLevel,
            BlockUtil.FoundRectangle pRectangle,
            Direction.Axis pAxis,
            Vec3 pOffset,
            Entity pEntity,
            TeleportTransition.PostTeleportTransition pPostTeleportTransition
    ) {
        BlockPos blockpos = pRectangle.minCorner;
        BlockState blockstate = pLevel.getBlockState(blockpos);
        Direction.Axis direction$axis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double d0 = pRectangle.axis1Size;
        double d1 = pRectangle.axis2Size;
        EntityDimensions entitydimensions = pEntity.getDimensions(pEntity.getPose());
        int i = pAxis == direction$axis ? 0 : 90;
        double d2 = entitydimensions.width() / 2.0 + (d0 - entitydimensions.width()) * pOffset.x();
        double d3 = (d1 - entitydimensions.height()) * pOffset.y();
        double d4 = 0.5 + pOffset.z();
        boolean flag = direction$axis == Direction.Axis.X;
        Vec3 vec3 = new Vec3(blockpos.getX() + (flag ? d2 : d4), blockpos.getY() + d3, blockpos.getZ() + (flag ? d4 : d2));
        Vec3 vec31 = PortalShape.findCollisionFreePosition(vec3, pLevel, pEntity, entitydimensions);
        return new TeleportTransition(pLevel, vec31, Vec3.ZERO, i, 0.0F, Relative.union(Relative.DELTA, Relative.ROTATION), pPostTeleportTransition);
    }

    @Override
    public Portal.Transition getLocalTransition() {
        return Portal.Transition.CONFUSION;
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIncludeData) {
        return ItemStack.EMPTY;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS);
    }
}
