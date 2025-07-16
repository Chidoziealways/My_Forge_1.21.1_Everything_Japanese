package net.Chidoziealways.everythingjapanese.particle

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModParticles {
    val PARTICLE_TYPES: DeferredRegister<ParticleType<*>?> =
        DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID)

    val PYRITE_PARTICLES: RegistryObject<SimpleParticleType?>? =
        PARTICLE_TYPES.register<SimpleParticleType?>("pyrite_particles", Supplier { SimpleParticleType(true) })

    fun register(eventBus: BusGroup?) {
        PARTICLE_TYPES.register(eventBus)
    }
}
