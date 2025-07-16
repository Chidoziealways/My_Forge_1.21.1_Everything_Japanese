package net.Chidoziealways.everythingjapanese.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.portal.HellPortalForcer
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions
import net.minecraft.BlockUtil
import net.minecraft.BlockUtil.FoundRectangle
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.InsideBlockEffectApplier
import net.minecraft.world.entity.Relative
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Portal
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.border.WorldBorder
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.portal.PortalShape
import net.minecraft.world.level.portal.TeleportTransition
import net.minecraft.world.level.portal.TeleportTransition.PostTeleportTransition
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.function.Function
import java.util.function.Predicate
import kotlin.math.max

class HellPortalBlock(properties: Properties) : Block(properties), Portal {
    override fun codec(): MapCodec<out Block?> {
        return CODEC
    }

    init {
        this.registerDefaultState(
            this.stateDefinition.any().setValue<Direction.Axis?, Direction.Axis?>(AXIS, Direction.Axis.X)
        )
    }

    override fun getEntityInsideCollisionShape(
        p_396779_: BlockState,
        p_395715_: BlockGetter,
        p_396107_: BlockPos,
        p_395206_: Entity
    ): VoxelShape {
        return p_396779_.getShape(p_395715_, p_396107_)
    }

    override fun entityInside(
        p_54915_: BlockState,
        p_54916_: Level,
        p_54917_: BlockPos,
        p_54918_: Entity,
        p_392916_: InsideBlockEffectApplier
    ) {
        if (p_54918_.canUsePortal(false)) {
            p_54918_.setAsInsidePortal(this, p_54917_)
        }
    }

    override fun getPortalTransitionTime(p_342064_: ServerLevel, p_344634_: Entity): Int {
        return if (p_344634_ is Player) max(
            0,
            p_342064_.getGameRules()
                .getInt(if (p_344634_.getAbilities().invulnerable) GameRules.RULE_PLAYERS_NETHER_PORTAL_CREATIVE_DELAY else GameRules.RULE_PLAYERS_NETHER_PORTAL_DEFAULT_DELAY)
        ) else
            0
    }

    override fun getPortalDestination(pLevel: ServerLevel, pEntity: Entity, pPos: BlockPos): TeleportTransition? {
        val resourcekey =
            if (pLevel.dimension() === ModDimensions.HELL_LEVEL_KEY) Level.OVERWORLD else ModDimensions.HELL_LEVEL_KEY
        val serverlevel = pLevel.getServer().getLevel(resourcekey)
        if (serverlevel == null) {
            return null
        } else {
            val flag = serverlevel.dimension() === ModDimensions.HELL_LEVEL_KEY
            val worldborder = serverlevel.getWorldBorder()
            val d0 = DimensionType.getTeleportationScale(pLevel.dimensionType(), serverlevel.dimensionType())
            val blockpos = worldborder.clampToBounds(pEntity.getX() * d0, pEntity.getY(), pEntity.getZ() * d0)
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
        val `blockutil$foundrectangle`: FoundRectangle?
        val `teleporttransition$postteleporttransition`: PostTeleportTransition?
        if (optional.isPresent()) {
            val blockpos = optional.get()
            val blockstate = pLevel.getBlockState(blockpos)
            `blockutil$foundrectangle` = BlockUtil.getLargestRectangleAround(
                blockpos,
                blockstate.getValue<Direction.Axis?>(BlockStateProperties.HORIZONTAL_AXIS),
                21,
                Direction.Axis.Y,
                21,
                Predicate { p_343533_: BlockPos? -> pLevel.getBlockState(p_343533_) === blockstate }
            )
            `teleporttransition$postteleporttransition` =
                TeleportTransition.PLAY_PORTAL_SOUND.then(PostTeleportTransition { p_343530_: Entity? ->
                    p_343530_!!.placePortalTicket(blockpos)
                })
        } else {
            val `direction$axis` = pEntity.level().getBlockState(pPos).getOptionalValue<Direction.Axis>(AXIS).orElse(
                Direction.Axis.X
            )
            val optional1 = forcer.createPortal(pExitPos, `direction$axis`)
            if (optional1.isEmpty()) {
                LOGGER.error("Unable to create a portal, likely target out of worldborder")
                return null
            }

            `blockutil$foundrectangle` = optional1.get()
            `teleporttransition$postteleporttransition` =
                TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET)
        }

        return getDimensionTransitionFromExit(
            pEntity,
            pPos,
            `blockutil$foundrectangle`!!,
            pLevel,
            `teleporttransition$postteleporttransition`
        )
    }

    override fun getLocalTransition(): Portal.Transition {
        return Portal.Transition.CONFUSION
    }

    override fun getCloneItemStack(
        pLevel: LevelReader,
        pPos: BlockPos,
        pState: BlockState,
        pIncludeData: Boolean
    ): ItemStack {
        return ItemStack.EMPTY
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block?, BlockState?>) {
        pBuilder.add(AXIS)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(HellPortalBlock::class.java)
        val CODEC: MapCodec<HellPortalBlock?> = simpleCodec<HellPortalBlock?>(Function { properties: Properties? ->
            HellPortalBlock(
                properties!!
            )
        })
        val AXIS: EnumProperty<Direction.Axis?> = BlockStateProperties.HORIZONTAL_AXIS
        private val SHAPES: MutableMap<Direction.Axis?, VoxelShape?> =
            Shapes.rotateHorizontalAxis(column(4.0, 16.0, 0.0, 16.0))

        private fun getDimensionTransitionFromExit(
            pEntity: Entity,
            pPos: BlockPos,
            pRectangle: FoundRectangle,
            pLevel: ServerLevel,
            pPostTeleportTransition: PostTeleportTransition
        ): TeleportTransition {
            val blockstate = pEntity.level().getBlockState(pPos)
            val `direction$axis`: Direction.Axis?
            val vec3: Vec3?
            if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                `direction$axis` = blockstate.getValue<Direction.Axis?>(BlockStateProperties.HORIZONTAL_AXIS)
                val `blockutil$foundrectangle` = BlockUtil.getLargestRectangleAround(
                    pPos,
                    `direction$axis`,
                    21,
                    Direction.Axis.Y,
                    21,
                    Predicate { p_342174_: BlockPos? -> pEntity.level().getBlockState(p_342174_) === blockstate }
                )
                vec3 = pEntity.getRelativePortalPosition(`direction$axis`, `blockutil$foundrectangle`)
            } else {
                `direction$axis` = Direction.Axis.X
                vec3 = Vec3(0.5, 0.0, 0.0)
            }

            return createDimensionTransition(
                pLevel,
                pRectangle,
                `direction$axis`,
                vec3,
                pEntity,
                pPostTeleportTransition
            )
        }

        private fun createDimensionTransition(
            pLevel: ServerLevel,
            pRectangle: FoundRectangle,
            pAxis: Direction.Axis?,
            pOffset: Vec3,
            pEntity: Entity,
            pPostTeleportTransition: PostTeleportTransition
        ): TeleportTransition {
            val blockpos = pRectangle.minCorner
            val blockstate = pLevel.getBlockState(blockpos)
            val `direction$axis` =
                blockstate.getOptionalValue<Direction.Axis>(BlockStateProperties.HORIZONTAL_AXIS).orElse(
                    Direction.Axis.X
                )
            val d0 = pRectangle.axis1Size.toDouble()
            val d1 = pRectangle.axis2Size.toDouble()
            val entitydimensions = pEntity.getDimensions(pEntity.getPose())
            val i = if (pAxis === `direction$axis`) 0 else 90
            val d2 = entitydimensions.width() / 2.0 + (d0 - entitydimensions.width()) * pOffset.x()
            val d3 = (d1 - entitydimensions.height()) * pOffset.y()
            val d4 = 0.5 + pOffset.z()
            val flag = `direction$axis` === Direction.Axis.X
            val vec3 = Vec3(
                blockpos.getX() + (if (flag) d2 else d4),
                blockpos.getY() + d3,
                blockpos.getZ() + (if (flag) d4 else d2)
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
