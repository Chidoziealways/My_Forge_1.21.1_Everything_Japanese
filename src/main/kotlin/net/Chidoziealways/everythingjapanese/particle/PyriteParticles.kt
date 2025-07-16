package net.Chidoziealways.everythingjapanese.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.SimpleParticleType

/**
 * This class represents a sheet of particles containing the Pyrite Ingot Texture.
 * @author Chidozie Derek Chidozie-Uzowulu
 */
class PyriteParticles protected constructor(
    pLevel: ClientLevel, pX: Double, pY: Double, pZ: Double,
    spriteSet: SpriteSet, pXSpeed: Double, pYSpeed: Double, pZSpeed: Double
) : TextureSheetParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed) {
    init {
        this.friction = 0.8f
        this.lifetime = 40

        this.setSpriteFromAge(spriteSet)

        this.rCol = 1f
        this.gCol = 1f
        this.bCol = 1f
    }

    override fun getRenderType(): ParticleRenderType {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT
    }

    class Provider(private val spriteSet: SpriteSet) : ParticleProvider<SimpleParticleType?> {
        override fun createParticle(
            pType: SimpleParticleType?, pLevel: ClientLevel, pX: Double, pY: Double, pZ: Double,
            pXSpeed: Double, pYSpeed: Double, pZSpeed: Double
        ): Particle? {
            return PyriteParticles(pLevel, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed)
        }
    }
}
