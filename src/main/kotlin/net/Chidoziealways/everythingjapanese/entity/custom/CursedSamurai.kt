package net.Chidoziealways.everythingjapanese.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableWitchTargetGoal
import net.minecraft.world.entity.animal.IronGolem
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.npc.AbstractVillager
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.Vec3
import net.neoforged.neoforge.common.CommonHooks
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animatable.processing.AnimationController
import software.bernie.geckolib.animatable.processing.AnimationTest
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

class CursedSamurai(level: Level): Monster(ModEntities.CURSED_SAMURAI, level), GeoEntity {

    val WALK = RawAnimation.begin().thenLoop("walk")
    val IDLE = RawAnimation.begin().thenLoop("idle")
    val SWORD_SWING = RawAnimation.begin().thenPlay("horizontal_sword_swing")
    val HURT = RawAnimation.begin().thenPlay("hurt")
    val DEATH = RawAnimation.begin().thenPlay("death")

    val geoCache = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController("Movement", 5, this::walkAnimController))
        controllers.add(AnimationController<CursedSamurai>("attack_controller", 0) { animTest -> PlayState.STOP}.triggerableAnim("sword_swing", SWORD_SWING))
        controllers.add(AnimationController<CursedSamurai>("hurt", 0) { animTest -> PlayState.STOP }.triggerableAnim("hurt", HURT))
        controllers.add(AnimationController<CursedSamurai>("death", 0) { animTest -> PlayState.STOP }.triggerableAnim("death", DEATH))
    }

    fun walkAnimController(animTest: AnimationTest<CursedSamurai>): PlayState {
        return if (animTest.isMoving)
            animTest.setAndContinue(WALK)
        else {
            animTest.setAndContinue(IDLE)
        }
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = geoCache

    override fun registerGoals() {
        goalSelector.addGoal(8, LookAtPlayerGoal(this, Player::class.java, 8.0F))
        goalSelector.addGoal(8, RandomLookAroundGoal(this))
        addBehaviourGoals()
    }

    fun addBehaviourGoals() {
        goalSelector.addGoal(9, MeleeAttackGoal(this, 1.0, true))
        goalSelector.addGoal(6, MoveThroughVillageGoal(this, 1.0, false, 6) {true})
        targetSelector.addGoal(5, HurtByTargetGoal(this).setAlertOthers(CursedSamurai::class.java))
        targetSelector.addGoal(6, NearestAttackableTargetGoal(this, Player::class.java, true))
        targetSelector.addGoal(6, NearestAttackableTargetGoal(this, AbstractVillager::class.java, true))
        targetSelector.addGoal(6, NearestAttackableTargetGoal(this, IronGolem::class.java, true))
    }

    override fun doHurtTarget(server: ServerLevel, entity: Entity): Boolean {
        println("Setting Swinging to true!")
        triggerAnim("attack_controller", "sword_swing")
        server.scheduleTick(blockPosition(), server.getBlockState(blockPosition()).block, 10)
        return super.doHurtTarget(server, entity)
    }

    override fun animateHurt(p_265265_: Float) {
        super.animateHurt(p_265265_)
        if (level() is ServerLevel) {
            triggerAnim("hurt", "hurt")
        }
    }

    override fun tickDeath() {
        deathTime++

        if (deathTime == 1 && level() is ServerLevel) {
            println("Death")

        }

        // Stop vanilla death physics
        if (deathTime > 0) {
            setDeltaMovement(0.0, 0.0, 0.0)
            isNoGravity = true
        }

        if (deathTime >= 60 && !level().isClientSide) {
            remove(RemovalReason.KILLED)
        }
    }

    override fun die(damageSource: DamageSource) {
        if (CommonHooks.onLivingDeath(this, damageSource)) return
        if (!isRemoved && !dead) {
            val entity = damageSource.entity
            val livingEntity = killCredit
            livingEntity?.awardKillScore(this, damageSource)

            if (isSleeping) stopSleeping()

            dead = true
            combatTracker.recheckStatus()
            val level = level()
            if (level is ServerLevel) {
                if (entity == null || entity.killedEntity(level, this)) {
                    gameEvent(GameEvent.ENTITY_DIE)
                    dropAllDeathLoot(level, damageSource)
                    createWitherRose(livingEntity)
                }

                triggerAnim("death", "death")
            }
        }
    }

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR, 50.0)
                .add(Attributes.ARMOR_TOUGHNESS, 100.0)
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 10.0)
        }
    }
}