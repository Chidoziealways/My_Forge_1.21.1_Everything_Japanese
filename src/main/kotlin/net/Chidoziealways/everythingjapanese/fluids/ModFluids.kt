package net.Chidoziealways.everythingjapanese.fluids

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.fluids.custom.BloodSourceFluid
import net.Chidoziealways.everythingjapanese.fluids.custom.FlowingBloodFluid
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModFluids {
    val FLUIDS = DeferredRegister.create(Registries.FLUID, JAPANESE_MOD_ID)

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