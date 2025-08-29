package net.Chidoziealways.everythingjapanese.portal

import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.block.custom.HellPortalBlock
import net.Chidoziealways.everythingjapanese.poi.ModPoiTypes
import net.minecraft.BlockUtil
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.Mth
import net.minecraft.world.entity.ai.village.poi.PoiManager
import net.minecraft.world.entity.ai.village.poi.PoiRecord
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.border.WorldBorder
import net.minecraft.world.level.levelgen.Heightmap
import java.util.Optional
import java.util.function.ToIntFunction

class HellPortalForcer(p_77650_: ServerLevel) {
    protected val level: ServerLevel

    init {
        this.level = p_77650_
    }

    fun findClosestPortalPosition(
        p_345495_: BlockPos,
        p_345384_: Boolean,
        p_344228_: WorldBorder
    ): java.util.Optional<BlockPos?> {
        val poimanager: PoiManager = this.level.poiManager
        val i = if (p_345384_) 16 else 128
        poimanager.ensureLoadedAndValid(this.level, p_345495_, i)
        return poimanager.getInSquare({ p_230634_: net.minecraft.core.Holder<PoiType?>? ->
            p_230634_!!.`is`(
                ModPoiTypes.HELL_PORTAL.getKey()
            )
        }, p_345495_, i, PoiManager.Occupancy.ANY)
            .map<BlockPos?> { obj: PoiRecord? -> obj!!.getPos() }
            .filter { p_61938_: BlockPos? -> p_344228_.isWithinBounds(p_61938_) }
            .filter { p_341965_: BlockPos? ->
                this.level.getBlockState(p_341965_).hasProperty(BlockStateProperties.HORIZONTAL_AXIS)
            }
            .min(java.util.Comparator.comparingDouble<BlockPos?>(java.util.function.ToDoubleFunction { p_341964_: BlockPos? ->
                p_341964_!!.distSqr(
                    p_345495_
                )
            }).thenComparingInt(ToIntFunction { obj: BlockPos? -> obj!!.y }))
    }

    fun createPortal(
        p_77667_: BlockPos,
        p_77668_: Direction.Axis
    ): Optional<BlockUtil.FoundRectangle> {
        val direction = Direction.get(Direction.AxisDirection.POSITIVE, p_77668_)
        var d0 = -1.0
        var blockpos: BlockPos? = null
        var d1 = -1.0
        var blockpos1: BlockPos? = null
        val worldborder: WorldBorder = this.level.getWorldBorder()
        val i = kotlin.math.min(this.level.getMaxY(), this.level.getMinY() + this.level.getLogicalHeight() - 1)
        val j = 1
        val `blockpos$mutableblockpos`: BlockPos.MutableBlockPos = p_77667_.mutable()

        for (`blockpos$mutableblockpos1` in BlockPos.spiralAround(
            p_77667_,
            16,
            Direction.EAST,
            Direction.SOUTH
        )) {
            val k = kotlin.math.min(
                i,
                this.level.getHeight(
                    Heightmap.Types.MOTION_BLOCKING,
                    `blockpos$mutableblockpos1`.x,
                    `blockpos$mutableblockpos1`.z
                )
            )
            if (worldborder.isWithinBounds(`blockpos$mutableblockpos1`) && worldborder.isWithinBounds(
                    `blockpos$mutableblockpos1`.move(direction, 1)
                )
            ) {
                `blockpos$mutableblockpos1`.move(direction.opposite, 1)

                var l = k
                while (l >= this.level.minY) {
                    `blockpos$mutableblockpos1`.setY(l)
                    if (this.canPortalReplaceBlock(`blockpos$mutableblockpos1`)) {
                        val i1 = l

                        while (l > this.level.getMinY() && this.canPortalReplaceBlock(
                                `blockpos$mutableblockpos1`.move(
                                    net.minecraft.core.Direction.DOWN
                                )
                            )
                        ) {
                            l--
                        }

                        if (l + 4 <= i) {
                            val j1 = i1 - l
                            if (j1 <= 0 || j1 >= 3) {
                                `blockpos$mutableblockpos1`.setY(l)
                                if (this.canHostFrame(
                                        `blockpos$mutableblockpos1`,
                                        `blockpos$mutableblockpos`,
                                        direction,
                                        0
                                    )
                                ) {
                                    val d2: kotlin.Double = p_77667_.distSqr(`blockpos$mutableblockpos1`)
                                    if (this.canHostFrame(
                                            `blockpos$mutableblockpos1`,
                                            `blockpos$mutableblockpos`,
                                            direction,
                                            -1
                                        )
                                        && this.canHostFrame(
                                            `blockpos$mutableblockpos1`,
                                            `blockpos$mutableblockpos`,
                                            direction,
                                            1
                                        )
                                        && (d0 == -1.0 || d0 > d2)
                                    ) {
                                        d0 = d2
                                        blockpos = `blockpos$mutableblockpos1`.immutable()
                                    }

                                    if (d0 == -1.0 && (d1 == -1.0 || d1 > d2)) {
                                        d1 = d2
                                        blockpos1 = `blockpos$mutableblockpos1`.immutable()
                                    }
                                }
                            }
                        }
                    }
                    l--
                }
            }
        }

        if (d0 == -1.0 && d1 != -1.0) {
            blockpos = blockpos1
            d0 = d1
        }

        if (d0 == -1.0) {
            val k1 = kotlin.math.max(this.level.getMinY() - -1, 70)
            val i2 = i - 9
            if (i2 < k1) {
                return Optional.empty<BlockUtil.FoundRectangle?>()
            }

            blockpos = BlockPos(
                p_77667_.x - direction.stepX * 1,
                Mth.clamp(p_77667_.y, k1, i2),
                p_77667_.z - direction.stepZ * 1
            )
                .immutable()
            blockpos = worldborder.clampToBounds(blockpos)
            val direction1 = direction.clockWise

            for (i3 in -1..1) {
                for (j3 in 0..1) {
                    for (k3 in -1..2) {
                        val blockstate1: BlockState =
                            if (k3 < 0) Blocks.NETHERRACK.defaultBlockState() else Blocks.AIR.defaultBlockState()
                        `blockpos$mutableblockpos`.setWithOffset(
                            blockpos,
                            j3 * direction.getStepX() + i3 * direction1.getStepX(),
                            k3,
                            j3 * direction.getStepZ() + i3 * direction1.getStepZ()
                        )
                        this.level.setBlockAndUpdate(`blockpos$mutableblockpos`, blockstate1)
                    }
                }
            }
        }

        for (l1 in -1..2) {
            for (j2 in -1..3) {
                if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
                    `blockpos$mutableblockpos`.setWithOffset(
                        blockpos,
                        l1 * direction.getStepX(),
                        j2,
                        l1 * direction.getStepZ()
                    )
                    this.level.setBlock(
                        `blockpos$mutableblockpos`,
                        Blocks.NETHERRACK.defaultBlockState(),
                        3
                    )
                }
            }
        }

        val blockstate: BlockState = ModBlocks.HELL_PORTAL.defaultBlockState()
            .setValue(
                HellPortalBlock.AXIS,
                p_77668_
            )

        for (k2 in 0..1) {
            for (l2 in 0..2) {
                `blockpos$mutableblockpos`.setWithOffset(
                    blockpos,
                    k2 * direction.stepX,
                    l2,
                    k2 * direction.stepZ
                )
                this.level.setBlock(`blockpos$mutableblockpos`, blockstate, 18)
            }
        }

        return Optional.of<BlockUtil.FoundRectangle>(BlockUtil.FoundRectangle(blockpos!!.immutable(), 2, 3))
    }

    private fun canPortalReplaceBlock(p_248971_: BlockPos.MutableBlockPos): Boolean {
        val blockstate: BlockState = this.level.getBlockState(p_248971_)
        return blockstate.canBeReplaced() && blockstate.fluidState.isEmpty
    }

    private fun canHostFrame(
        p_77662_: BlockPos,
        p_77663_: BlockPos.MutableBlockPos,
        p_77664_: Direction,
        p_77665_: Int
    ): Boolean {
        val direction = p_77664_.getClockWise()

        for (i in -1..2) {
            for (j in -1..3) {
                p_77663_.setWithOffset(
                    p_77662_,
                    p_77664_.getStepX() * i + direction.getStepX() * p_77665_,
                    j,
                    p_77664_.getStepZ() * i + direction.getStepZ() * p_77665_
                )
                if (j < 0 && !this.level.getBlockState(p_77663_).isSolid()) {
                    return false
                }

                if (j >= 0 && !this.canPortalReplaceBlock(p_77663_)) {
                    return false
                }
            }
        }

        return true
    }

    companion object {
        const val TICKET_RADIUS: kotlin.Int = 3
        private const val NETHER_PORTAL_RADIUS = 16
        private const val OVERWORLD_PORTAL_RADIUS = 128
        private const val FRAME_HEIGHT = 5
        private const val FRAME_WIDTH = 4
        private const val FRAME_BOX = 3
        private val FRAME_HEIGHT_START = -1
        private const val FRAME_HEIGHT_END = 4
        private val FRAME_WIDTH_START = -1
        private const val FRAME_WIDTH_END = 3
        private val FRAME_BOX_START = -1
        private const val FRAME_BOX_END = 2
        private val NOTHING_FOUND = -1
    }
}