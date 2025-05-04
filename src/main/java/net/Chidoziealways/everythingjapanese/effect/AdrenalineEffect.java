package net.Chidoziealways.everythingjapanese.effect;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

public class AdrenalineEffect extends MobEffect {
    public AdrenalineEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity pLivingEntity, int pAmplifier) {
        if(pLivingEntity.horizontalCollision) {
            Vec3 initialVec = pLivingEntity.getDeltaMovement();
            Vec3 climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
            pLivingEntity.setDeltaMovement(climbVec.scale(0.97D));
            if(pLivingEntity.getHealth() < pLivingEntity.getMaxHealth()){
                pLivingEntity.heal(3.0F);
            }
            pLivingEntity.setHealth(pLivingEntity.getMaxHealth() + 15);
            return true;
        }

        return super.applyEffectTick(level, pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
