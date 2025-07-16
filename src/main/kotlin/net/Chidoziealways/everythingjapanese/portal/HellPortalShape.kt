package net.Chidoziealways.everythingjapanese.portal

import net.minecraft.BlockUtil.FoundRectangle
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.NetherPortalBlock
import net.minecraft.world.level.block.state.BlockBehaviour.StatePredicate
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import org.apache.commons.lang3.mutable.MutableInt
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate
import kotlin.math.max

class HellPortalShape private constructor(
    private val axis: Direction.Axis,
    private val numPortalBlocks: Int,
    private val rightDir: Direction,
    private val bottomLeft: BlockPos,
    private val width: Int,
    private val height: Int
) {
    val isValid: Boolean
        get() = this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21

    fun createPortalBlocks(p_366077_: LevelAccessor) {
        val blockstate = Blocks.NETHER_PORTAL.defaultBlockState()
            .setValue<Direction.Axis?, Direction.Axis?>(NetherPortalBlock.AXIS, this.axis)
        BlockPos.betweenClosed(
            this.bottomLeft,
            this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)
        )
            .forEach(Consumer { p_360642_: BlockPos? -> p_366077_.setBlock(p_360642_, blockstate, 18) })
    }

    val isComplete: Boolean
        get() = this.isValid && this.numPortalBlocks == this.width * this.height

    companion object {
        private const val MIN_WIDTH = 2
        const val MAX_WIDTH: Int = 21
        private const val MIN_HEIGHT = 3
        const val MAX_HEIGHT: Int = 21
        private val FRAME = StatePredicate { p_77720_: BlockState?, p_77721_: BlockGetter?, p_77722_: BlockPos? ->
            p_77720_!!.isPortalFrame(
                p_77721_,
                p_77722_
            )
        }
        private const val SAFE_TRAVEL_MAX_ENTITY_XY = 4.0f
        private const val SAFE_TRAVEL_MAX_VERTICAL_DELTA = 1.0
        fun findEmptyPortalShape(
            p_77709_: LevelAccessor,
            p_77710_: BlockPos,
            p_77711_: Direction.Axis
        ): Optional<HellPortalShape?> {
            return findPortalShape(
                p_77709_,
                p_77710_,
                Predicate { p_77727_: HellPortalShape? -> p_77727_!!.isValid && p_77727_.numPortalBlocks == 0 },
                p_77711_
            )
        }

        fun findPortalShape(
            p_77713_: LevelAccessor,
            p_77714_: BlockPos,
            p_77715_: Predicate<HellPortalShape?>,
            p_77716_: Direction.Axis
        ): Optional<HellPortalShape?> {
            val optional = Optional.of<HellPortalShape?>(findAnyShape(p_77713_, p_77714_, p_77716_)).filter(p_77715_)
            if (optional.isPresent()) {
                return optional
            } else {
                val `direction$axis` = if (p_77716_ === Direction.Axis.X) Direction.Axis.Z else Direction.Axis.X
                return Optional.of<HellPortalShape?>(findAnyShape(p_77713_, p_77714_, `direction$axis`))
                    .filter(p_77715_)
            }
        }

        fun findAnyShape(p_362003_: BlockGetter, p_369293_: BlockPos, p_363410_: Direction.Axis): HellPortalShape {
            val direction = if (p_363410_ === Direction.Axis.X) Direction.WEST else Direction.SOUTH
            val blockpos: BlockPos? = calculateBottomLeft(p_362003_, direction, p_369293_)
            if (blockpos == null) {
                return HellPortalShape(p_363410_, 0, direction, p_369293_, 0, 0)
            } else {
                val i: Int = calculateWidth(p_362003_, blockpos, direction)
                if (i == 0) {
                    return HellPortalShape(p_363410_, 0, direction, blockpos, 0, 0)
                } else {
                    val mutableint = MutableInt()
                    val j: Int = calculateHeight(p_362003_, blockpos, direction, i, mutableint)
                    return HellPortalShape(p_363410_, mutableint.getValue(), direction, blockpos, i, j)
                }
            }
        }

        private fun calculateBottomLeft(p_366894_: BlockGetter, p_361188_: Direction, p_77734_: BlockPos): BlockPos? {
            var p_77734_ = p_77734_
            val i = max(p_366894_.getMinY(), p_77734_.getY() - 21)

            while (p_77734_.getY() > i && isEmpty(p_366894_.getBlockState(p_77734_.below()))) {
                p_77734_ = p_77734_.below()
            }

            val direction = p_361188_.getOpposite()
            val j: Int = getDistanceUntilEdgeAboveFrame(p_366894_, p_77734_, direction) - 1
            return if (j < 0) null else p_77734_.relative(direction, j)
        }

        private fun calculateWidth(p_362377_: BlockGetter, p_369982_: BlockPos, p_367434_: Direction): Int {
            val i: Int = getDistanceUntilEdgeAboveFrame(p_362377_, p_369982_, p_367434_)
            return if (i >= 2 && i <= 21) i else 0
        }

        private fun getDistanceUntilEdgeAboveFrame(
            p_366562_: BlockGetter,
            p_77736_: BlockPos,
            p_77737_: Direction
        ): Int {
            val `blockpos$mutableblockpos` = MutableBlockPos()

            for (i in 0..21) {
                `blockpos$mutableblockpos`.set(p_77736_).move(p_77737_, i)
                val blockstate = p_366562_.getBlockState(`blockpos$mutableblockpos`)
                if (!isEmpty(blockstate)) {
                    if (FRAME.test(blockstate, p_366562_, `blockpos$mutableblockpos`)) {
                        return i
                    }
                    break
                }

                val blockstate1 = p_366562_.getBlockState(`blockpos$mutableblockpos`.move(Direction.DOWN))
                if (!FRAME.test(blockstate1, p_366562_, `blockpos$mutableblockpos`)) {
                    break
                }
            }

            return 0
        }

        private fun calculateHeight(
            p_366874_: BlockGetter,
            p_367382_: BlockPos,
            p_369713_: Direction,
            p_364755_: Int,
            p_366395_: MutableInt
        ): Int {
            val `blockpos$mutableblockpos` = MutableBlockPos()
            val i: Int =
                getDistanceUntilTop(p_366874_, p_367382_, p_369713_, `blockpos$mutableblockpos`, p_364755_, p_366395_)
            return if (i >= 3 && i <= 21 && hasTopFrame(
                    p_366874_,
                    p_367382_,
                    p_369713_,
                    `blockpos$mutableblockpos`,
                    p_364755_,
                    i
                )
            ) i else 0
        }

        private fun hasTopFrame(
            p_360937_: BlockGetter,
            p_362624_: BlockPos,
            p_365783_: Direction,
            p_77731_: MutableBlockPos,
            p_77732_: Int,
            p_369385_: Int
        ): Boolean {
            for (i in 0..<p_77732_) {
                val `blockpos$mutableblockpos` =
                    p_77731_.set(p_362624_).move(Direction.UP, p_369385_).move(p_365783_, i)
                if (!FRAME.test(
                        p_360937_.getBlockState(`blockpos$mutableblockpos`),
                        p_360937_,
                        `blockpos$mutableblockpos`
                    )
                ) {
                    return false
                }
            }

            return true
        }

        private fun getDistanceUntilTop(
            p_366399_: BlockGetter,
            p_367032_: BlockPos,
            p_362252_: Direction,
            p_77729_: MutableBlockPos,
            p_361664_: Int,
            p_363201_: MutableInt
        ): Int {
            for (i in 0..20) {
                p_77729_.set(p_367032_).move(Direction.UP, i).move(p_362252_, -1)
                if (!FRAME.test(p_366399_.getBlockState(p_77729_), p_366399_, p_77729_)) {
                    return i
                }

                p_77729_.set(p_367032_).move(Direction.UP, i).move(p_362252_, p_361664_)
                if (!FRAME.test(p_366399_.getBlockState(p_77729_), p_366399_, p_77729_)) {
                    return i
                }

                for (j in 0..<p_361664_) {
                    p_77729_.set(p_367032_).move(Direction.UP, i).move(p_362252_, j)
                    val blockstate = p_366399_.getBlockState(p_77729_)
                    if (!isEmpty(blockstate)) {
                        return i
                    }

                    if (blockstate.`is`(Blocks.NETHER_PORTAL)) {
                        p_363201_.increment()
                    }
                }
            }

            return 21
        }

        private fun isEmpty(p_77718_: BlockState): Boolean {
            return p_77718_.isAir() || p_77718_.`is`(BlockTags.FIRE) || p_77718_.`is`(Blocks.NETHER_PORTAL)
        }

        fun getRelativePosition(
            p_77739_: FoundRectangle,
            p_77740_: Direction.Axis,
            p_77741_: Vec3,
            p_77742_: EntityDimensions
        ): Vec3 {
            val d0 = p_77739_.axis1Size.toDouble() - p_77742_.width()
            val d1 = p_77739_.axis2Size.toDouble() - p_77742_.height()
            val blockpos = p_77739_.minCorner
            val d2: Double
            if (d0 > 0.0) {
                val d3 = blockpos.get(p_77740_) + p_77742_.width() / 2.0
                d2 = Mth.clamp(Mth.inverseLerp(p_77741_.get(p_77740_) - d3, 0.0, d0), 0.0, 1.0)
            } else {
                d2 = 0.5
            }

            val d5: Double
            if (d1 > 0.0) {
                val `direction$axis` = Direction.Axis.Y
                d5 = Mth.clamp(
                    Mth.inverseLerp(p_77741_.get(`direction$axis`) - blockpos.get(`direction$axis`), 0.0, d1),
                    0.0,
                    1.0
                )
            } else {
                d5 = 0.0
            }

            val `direction$axis1` = if (p_77740_ === Direction.Axis.X) Direction.Axis.Z else Direction.Axis.X
            val d4 = p_77741_.get(`direction$axis1`) - (blockpos.get(`direction$axis1`) + 0.5)
            return Vec3(d2, d5, d4)
        }

        fun findCollisionFreePosition(
            p_260315_: Vec3,
            p_259704_: ServerLevel,
            p_259626_: Entity?,
            p_259816_: EntityDimensions
        ): Vec3? {
            if (!(p_259816_.width() > 4.0f) && !(p_259816_.height() > 4.0f)) {
                val d0 = p_259816_.height() / 2.0
                val vec3 = p_260315_.add(0.0, d0, 0.0)
                val voxelshape = Shapes.create(
                    AABB.ofSize(vec3, p_259816_.width().toDouble(), 0.0, p_259816_.width().toDouble())
                        .expandTowards(0.0, 1.0, 0.0).inflate(1.0E-6)
                )
                val optional = p_259704_.findFreePosition(
                    p_259626_,
                    voxelshape,
                    vec3,
                    p_259816_.width().toDouble(),
                    p_259816_.height().toDouble(),
                    p_259816_.width().toDouble()
                )
                val optional1 = optional.map<Vec3?>(Function { p_259019_: Vec3? -> p_259019_!!.subtract(0.0, d0, 0.0) })
                return optional1.orElse(p_260315_)
            } else {
                return p_260315_
            }
        }
    }
}
