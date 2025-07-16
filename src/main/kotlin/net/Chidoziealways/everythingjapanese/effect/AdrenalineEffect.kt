package net.Chidoziealways.everythingjapanese.effect

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3

class AdrenalineEffect(pCategory: MobEffectCategory, pColor: Int) : MobEffect(pCategory, pColor) {
    override fun applyEffectTick(level: ServerLevel, pLivingEntity: LivingEntity, pAmplifier: Int): Boolean {
        if (pLivingEntity.horizontalCollision) {
            val initialVec = pLivingEntity.deltaMovement
            val climbVec = Vec3(initialVec.x, 0.2, initialVec.z)
            pLivingEntity.deltaMovement = climbVec.scale(0.97)
            if (pLivingEntity.health < pLivingEntity.maxHealth) {
                pLivingEntity.heal(3.0f)
            }
            pLivingEntity.health = pLivingEntity.maxHealth + 15
            return true
        }

        return super.applyEffectTick(level, pLivingEntity, pAmplifier)
    }

    override fun shouldApplyEffectTickThisTick(pDuration: Int, pAmplifier: Int): Boolean {
        return true
    }
}
