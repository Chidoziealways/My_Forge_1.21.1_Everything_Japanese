package net.Chidoziealways.everythingjapanese.fluids.custom

import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.fluids.ModFluidTypes
import net.Chidoziealways.everythingjapanese.fluids.ModFluids
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.InsideBlockEffectApplier
import net.minecraft.world.entity.InsideBlockEffectType
import net.minecraft.world.item.Item
import net.minecraft.world.level.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.gamerules.GameRules
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.neoforged.neoforge.fluids.FluidType
import java.util.*

abstract class BloodFluid: FlowingFluid() {
    override fun getFlowing(): Fluid = ModFluids.FLOWING_BLOOD.get()
    override fun getSource(): Fluid = ModFluids.BLOOD.get()
    override fun getFluidType(): FluidType = ModFluidTypes.BLOOD_TYPE
    override fun getPickupSound(): Optional<SoundEvent> = Optional.empty<SoundEvent>() as Optional<SoundEvent>
    override fun getDripParticle(): ParticleOptions? {
        return super.getDripParticle()
    }
    override fun getBucket(): Item = JModItems.BLOOD_BUCKET

    public override fun animateTick(p_230606_: Level, p_230607_: BlockPos, p_230608_: FluidState, p_230609_: RandomSource) {
        if (!p_230608_.isSource() && !p_230608_.getValue<Boolean>(FALLING)) {
            if (p_230609_.nextInt(64) == 0) {
                p_230606_.playLocalSound(
                    p_230607_.getX() + 0.5,
                    p_230607_.getY() + 0.5,
                    p_230607_.getZ() + 0.5,
                    SoundEvents.WATER_AMBIENT,
                    SoundSource.AMBIENT,
                    p_230609_.nextFloat() * 0.25f + 0.75f,
                    p_230609_.nextFloat() + 0.5f,
                    false
                )
            }
        } else if (p_230609_.nextInt(10) == 0) {
            p_230606_.addParticle(
                ParticleTypes.UNDERWATER,
                p_230607_.getX() + p_230609_.nextDouble(),
                p_230607_.getY() + p_230609_.nextDouble(),
                p_230607_.getZ() + p_230609_.nextDouble(),
                0.0,
                0.0,
                0.0
            )
        }
    }

    override fun canConvertToSource(p_376722_: ServerLevel): Boolean {
        return p_376722_.gameRules.get(GameRules.WATER_SOURCE_CONVERSION)
    }

    override fun beforeDestroyingBlock(level: LevelAccessor, pos: BlockPos, state: BlockState) {
        val blockentity = if (state.hasBlockEntity()) level.getBlockEntity(pos) else null
        Block.dropResources(state, level, pos, blockentity)
    }

    override fun entityInside(
        p_404956_: Level,
        p_405311_: BlockPos,
        p_405780_: Entity,
        p_405240_: InsideBlockEffectApplier
    ) {
        p_405240_.apply(InsideBlockEffectType.EXTINGUISH)
    }

    public override fun getSlopeFindDistance(level: LevelReader): Int {
        return 4
    }

    public override fun createLegacyBlock(state: FluidState): BlockState {
        return JModBlocks.BLOOD.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state))
    }

    override fun isSame(fluid: Fluid): Boolean {
        return fluid === ModFluids.BLOOD.get() || fluid === ModFluids.FLOWING_BLOOD.get()
    }

    public override fun getDropOff(level: LevelReader): Int {
        return 1
    }

    override fun getTickDelay(p_76454_: LevelReader): Int {
        return 5
    }

    public override fun canBeReplacedWith(
        fluidState: FluidState,
        blockReader: BlockGetter,
        pos: BlockPos,
        fluid: Fluid,
        direction: Direction
    ): Boolean {
        return direction == Direction.DOWN && !fluid.`is`(ModTags.Fluids.BLOOD)
    }

    override fun getExplosionResistance(): Float {
        return 100.0f
    }
}

class FlowingBloodFluid: BloodFluid() {
    override fun createFluidStateDefinition(builder: StateDefinition.Builder<Fluid, FluidState>) {
        super.createFluidStateDefinition(builder)
        builder.add(LEVEL)
    }

    override fun isSource(state: FluidState): Boolean = false
    override fun getAmount(state: FluidState): Int = state.getValue(LEVEL)
}

class BloodSourceFluid: BloodFluid() {
    override fun isSource(state: FluidState): Boolean = true
    override fun getAmount(p_164509_: FluidState): Int = 8
}