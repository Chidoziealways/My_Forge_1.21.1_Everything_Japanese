package net.Chidoziealways.everythingjapanese.fluids

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.fluids.custom.BloodSourceFluid
import net.Chidoziealways.everythingjapanese.fluids.custom.FlowingBloodFluid
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.WaterFluid
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModFluids {
    val FLUIDS = DeferredRegister.create(Registries.FLUID, MOD_ID)

    val BLOOD = FLUIDS.register("blood") { ->
        BloodSourceFluid()
    }
    val FLOWING_BLOOD = FLUIDS.register("flowing_blood") { ->
        FlowingBloodFluid()
    }

    fun register(eventBus: IEventBus) {
        FLUIDS.register(eventBus)
    }
}