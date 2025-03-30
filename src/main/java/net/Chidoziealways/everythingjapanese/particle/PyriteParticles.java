package net.Chidoziealways.everythingjapanese.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents a sheet of particles containing the Pyrite Ingot Texture.
 * @author Chidozie Derek Chidozie-Uzowulu
 */
public class PyriteParticles extends TextureSheetParticle {
    protected PyriteParticles(ClientLevel pLevel, double pX, double pY, double pZ,
                              SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.8f;
        this.lifetime = 40;

        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ,
                                                 double pXSpeed, double pYSpeed, double pZSpeed) {
            return new PyriteParticles(pLevel, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
