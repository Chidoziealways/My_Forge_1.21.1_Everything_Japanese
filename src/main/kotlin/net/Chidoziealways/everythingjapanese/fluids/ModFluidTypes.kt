package net.Chidoziealways.everythingjapanese.fluids

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.world.entity.item.ItemEntity
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModFluidTypes {
    val FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, MOD_ID)

    val BLOOD_TYPE by FLUID_TYPES.register("blood") { ->
        object : FluidType(
            FluidType.Properties.create()
                .descriptionId("block.$MOD_ID.blood")
                .density(1200)
                .viscosity(1500)
                .temperature(300)
                .motionScale(0.015)
        ) {
            override fun setItemMovement(entity: ItemEntity) {
                if (!entity.isNoGravity) {
                    entity.deltaMovement = entity.deltaMovement.add(0.0, -0.03, 0.0)
                }
            }
        }
    }

    fun register(eventBus: IEventBus) {
        FLUID_TYPES.register(eventBus)
    }
}