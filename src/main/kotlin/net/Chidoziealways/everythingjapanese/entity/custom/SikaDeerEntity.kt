package net.Chidoziealways.everythingjapanese.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.AnimationState
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class SikaDeerEntity(pEntityType: EntityType<out Animal>, pLevel: Level) : Animal(pEntityType, pLevel) {
    val idleAnimationState: AnimationState = AnimationState()
    private var idleAnimationTimeout = 0

    override fun registerGoals() {
        this.goalSelector.addGoal(0, FloatGoal(this))

        this.goalSelector.addGoal(1, PanicGoal(this, 2.0))
        this.goalSelector.addGoal(2, BreedGoal(this, 1.0))
        this.goalSelector.addGoal(
            3,
            TemptGoal(
                this,
                1.25,
                { stack: ItemStack? -> stack!!.`is`(JModItems.YAMAZAKI_BERRIES) },
                false
            )
        )

        this.goalSelector.addGoal(4, FollowParentGoal(this, 1.25))

        this.goalSelector.addGoal(5, WaterAvoidingRandomStrollGoal(this, 1.0))
        this.goalSelector.addGoal(6, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        this.goalSelector.addGoal(7, RandomLookAroundGoal(this))
    }

    override fun isFood(pStack: ItemStack): Boolean {
        return pStack.`is`(JModItems.YAMAZAKI_BERRIES)
    }

    override fun getBreedOffspring(pLevel: ServerLevel, pOtherParent: AgeableMob): AgeableMob? {
        return ModEntities.SIKA_DEER.create(pLevel, EntitySpawnReason.BREEDING)
    }

    private fun setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout =
                40 //This should match the number of ticks your idle animation plays for which is S X 20 S = seconds
            this.idleAnimationState.start(this.tickCount)
        } else {
            --this.idleAnimationTimeout
        }
    }

    override fun tick() {
        super.tick()

        if (this.level().isClientSide()) {
            this.setupAnimationStates()
        }
    }

    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.HORSE_AMBIENT
    }

    override fun getHurtSound(pDamageSource: DamageSource): SoundEvent? {
        return SoundEvents.HORSE_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.HORSE_DEATH
    }

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.TEMPT_RANGE, 24.0)
        }
    }
}
