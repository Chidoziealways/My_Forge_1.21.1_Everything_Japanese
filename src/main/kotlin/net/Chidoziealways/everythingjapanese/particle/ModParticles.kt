package net.Chidoziealways.everythingjapanese.particle

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.function.Supplier

object ModParticles {
    val PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, JAPANESE_MOD_ID)

    val PYRITE_PARTICLES by PARTICLE_TYPES.register("pyrite_particles", Supplier { SimpleParticleType(true) })

    fun register(eventBus: IEventBus) {
        PARTICLE_TYPES.register(eventBus)
    }
}
