package net.Chidoziealways.everythingjapanese.block.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.portal.HellPortalForcer
import net.Chidoziealways.everythingjapanese.portal.HellPortalShape
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.BlockUtil
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.InsideBlockEffectApplier
import net.minecraft.world.entity.Relative
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.Portal
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.border.WorldBorder
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.gamerules.GameRules
import net.minecraft.world.level.portal.PortalShape
import net.minecraft.world.level.portal.TeleportTransition
import net.minecraft.world.level.portal.TeleportTransition.PostTeleportTransition
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.max

class HellPortalBlock(properties: Properties) : Block(properties), Portal {
    override fun codec(): MapCodec<out Block?> {
        return CODEC
    }

    init {
        this.registerDefaultState(
            this.stateDefinition.any().setValue<Direction.Axis, Direction.Axis>(AXIS, Direction.Axis.X)
        )
    }

    override fun getEntityInsideCollisionShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        entity: Entity
    ): VoxelShape {
        return blockState.getShape(blockGetter, blockPos)
    }

    override fun entityInside(
        blockState: BlockState,
        level: Level,
        blockPos: BlockPos,
        entity: Entity,
        insideBlockEffectApplier: InsideBlockEffectApplier,
        boolean: Boolean
    ) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, blockPos)
        }
    }

    override fun updateShape(
        state: BlockState,
        level: LevelReader,
        scheduledTickAccess: ScheduledTickAccess,
        pos: BlockPos,
        direction: Direction,
        neighborPos: BlockPos,
        neighborState: BlockState,
        random: RandomSource
    ): BlockState {
        val axis = direction.axis
        val axis1 = state.getValue(AXIS)
        val flag = axis1 != axis && axis.isHorizontal
        return if(!flag && !neighborState.`is`(this) && !HellPortalShape.findAnyShape(level, pos, axis1).isComplete) Blocks.AIR.defaultBlockState() else super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random)
    }

    override fun getPortalTransitionTime(serverLevel: ServerLevel, entity: Entity): Int {
        return if (entity is Player) max(
            0,
            serverLevel.gameRules.get (if (entity.abilities.invulnerable)  GameRules.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY else GameRules.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY)
        ) else
            0
    }

    override fun getPortalDestination(pLevel: ServerLevel, pEntity: Entity, pPos: BlockPos): TeleportTransition? {
        val resourcekey =
            if (pLevel.dimension() === ModDimensions.HELL_LEVEL_KEY) Level.OVERWORLD else ModDimensions.HELL_LEVEL_KEY
        val serverlevel = pLevel.server.getLevel(resourcekey)
        if (serverlevel == null) {
            return null
        } else {
            val flag = serverlevel.dimension() === ModDimensions.HELL_LEVEL_KEY
            val worldborder = serverlevel.worldBorder
            val d0 = DimensionType.getTeleportationScale(pLevel.dimensionType(), serverlevel.dimensionType())
            val blockpos = worldborder.clampToBounds(pEntity.x * d0, pEntity.y, pEntity.z * d0)
            return this.getExitPortal(serverlevel, pEntity, pPos, blockpos, flag, worldborder)
        }
    }

    private fun getExitPortal(
        pLevel: ServerLevel,
        pEntity: Entity,
        pPos: BlockPos,
        pExitPos: BlockPos,
        pIsNether: Boolean,
        pWorldBorder: WorldBorder
    ): TeleportTransition? {
        val forcer = HellPortalForcer(pLevel)
        val optional = forcer.findClosestPortalPosition(pExitPos, pIsNether, pWorldBorder)
        val foundRectangle: BlockUtil.FoundRectangle
        val postTeleportTransition: PostTeleportTransition
        if (optional.isPresent) {
            val blockpos = optional.get()
            val blockstate = pLevel.getBlockState(blockpos)
            foundRectangle = BlockUtil.getLargestRectangleAround(
                blockpos,
                blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS),
                21,
                Direction.Axis.Y,
                21
            ) { pos: BlockPos -> pLevel.getBlockState(pos) === blockstate }
            postTeleportTransition =
                TeleportTransition.PLAY_PORTAL_SOUND.then { entity: Entity ->
                    entity.placePortalTicket(blockpos)
                }
        } else {
            val directionAxis = pEntity.level().getBlockState(pPos).getOptionalValue(AXIS).orElse(
                Direction.Axis.X
            )
            val optional1 = forcer.createPortal(pExitPos, directionAxis)
            if (optional1.isEmpty) {
                LOGGER.error("Unable to create a portal, likely target out of worldborder")
                return null
            }

            foundRectangle = optional1.get()
            postTeleportTransition =
                TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET)
        }

        return getDimensionTransitionFromExit(
            pEntity,
            pPos,
            foundRectangle,
            pLevel,
            postTeleportTransition
        )
    }

    override fun getLocalTransition(): Portal.Transition {
        return Portal.Transition.CONFUSION
    }

    @Deprecated("Deprecated in Java")
    override fun getCloneItemStack(
        pLevel: LevelReader,
        pPos: BlockPos,
        pState: BlockState,
        pIncludeData: Boolean
    ): ItemStack {
        return ItemStack.EMPTY
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(AXIS)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(HellPortalBlock::class.java)
        val CODEC: MapCodec<HellPortalBlock> = simpleCodec<HellPortalBlock> { properties: Properties ->
            HellPortalBlock(
                properties
            )
        }
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.HORIZONTAL_AXIS
        private val SHAPES: MutableMap<Direction.Axis, VoxelShape> =
            Shapes.rotateHorizontalAxis(column(4.0, 16.0, 0.0, 16.0))

        private fun getDimensionTransitionFromExit(
            pEntity: Entity,
            pPos: BlockPos,
            pRectangle: BlockUtil.FoundRectangle,
            pLevel: ServerLevel,
            pPostTeleportTransition: PostTeleportTransition
        ): TeleportTransition {
            val blockstate = pEntity.level().getBlockState(pPos)
            val directionAxis: Direction.Axis?
            val vec3: Vec3?
            if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                directionAxis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS)
                val blockUtilFoundRectangle = BlockUtil.getLargestRectangleAround(
                    pPos,
                    directionAxis,
                    21,
                    Direction.Axis.Y,
                    21
                ) { blockPos: BlockPos -> pEntity.level().getBlockState(blockPos) === blockstate }
                vec3 = pEntity.getRelativePortalPosition(directionAxis, blockUtilFoundRectangle)
            } else {
                directionAxis = Direction.Axis.X
                vec3 = Vec3(0.5, 0.0, 0.0)
            }

            return createDimensionTransition(
                pLevel,
                pRectangle,
                directionAxis,
                vec3,
                pEntity,
                pPostTeleportTransition
            )
        }

        private fun createDimensionTransition(
            pLevel: ServerLevel,
            pRectangle: BlockUtil.FoundRectangle,
            pAxis: Direction.Axis?,
            pOffset: Vec3,
            pEntity: Entity,
            pPostTeleportTransition: PostTeleportTransition
        ): TeleportTransition {
            val blockpos = pRectangle.minCorner
            val blockstate = pLevel.getBlockState(blockpos)
            val directionAxis =
                blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(
                    Direction.Axis.X
                )
            val d0 = pRectangle.axis1Size.toDouble()
            val d1 = pRectangle.axis2Size.toDouble()
            val entitydimensions = pEntity.getDimensions(pEntity.pose)
            val i = if (pAxis === directionAxis) 0 else 90
            val d2 = entitydimensions.width() / 2.0 + (d0 - entitydimensions.width()) * pOffset.x()
            val d3 = (d1 - entitydimensions.height()) * pOffset.y()
            val d4 = 0.5 + pOffset.z()
            val flag = directionAxis === Direction.Axis.X
            val vec3 = Vec3(
                blockpos.x + (if (flag) d2 else d4),
                blockpos.y + d3,
                blockpos.z + (if (flag) d4 else d2)
            )
            val vec31 = PortalShape.findCollisionFreePosition(vec3, pLevel, pEntity, entitydimensions)
            return TeleportTransition(
                pLevel,
                vec31,
                Vec3.ZERO,
                i.toFloat(),
                0.0f,
                Relative.union(Relative.DELTA, Relative.ROTATION),
                pPostTeleportTransition
            )
        }
    }
}
