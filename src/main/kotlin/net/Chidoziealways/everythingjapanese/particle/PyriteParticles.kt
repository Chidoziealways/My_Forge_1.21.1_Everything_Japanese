package net.Chidoziealways.everythingjapanese.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.util.RandomSource

class PyriteParticles(
    level: ClientLevel,
    x: Double,
    y: Double,
    z: Double,
    private val spriteSet: SpriteSet
) : SingleQuadParticle(level, x, y, z, spriteSet.first()) {

    init {
        this.friction = 0.8f
        this.lifetime = 40

        // initial color
        this.rCol = 1f
        this.gCol = 1f
        this.bCol = 1f
    }

    override fun tick() {
        super.tick()
        // Update sprite each tick
        this.setSpriteFromAge(spriteSet)
    }

    override fun getLayer(): SingleQuadParticle.Layer {
        // Use a default translucent particle layer
        return PARTICLE_LAYER
    }

    companion object {
        // Define a custom particle layer (or reuse vanilla ones)
        val PARTICLE_LAYER = SingleQuadParticle.Layer(
            true,                      // allows translucency
            TextureAtlas.LOCATION_PARTICLES,
            RenderPipelines.WEATHER_DEPTH_WRITE // choose appropriate pipeline
        )
    }

    // Provider for registration
    class Provider(private val spriteSet: SpriteSet) : ParticleProvider<SimpleParticleType> {
        override fun createParticle(
            type: SimpleParticleType,
            level: ClientLevel,
            x: Double,
            y: Double,
            z: Double,
            xd: Double,
            yd: Double,
            zd: Double,
            random: RandomSource
        ): Particle {
            return PyriteParticles(level, x, y, z, spriteSet)
        }
    }
}
