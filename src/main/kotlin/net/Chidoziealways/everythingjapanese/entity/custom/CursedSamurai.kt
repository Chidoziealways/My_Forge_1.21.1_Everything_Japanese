package net.Chidoziealways.everythingjapanese.entity.custom

import com.geckolib.animatable.GeoEntity
import com.geckolib.animatable.instance.AnimatableInstanceCache
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.RawAnimation
import com.geckolib.animation.`object`.PlayState
import com.geckolib.animation.state.AnimationTest
import com.geckolib.util.GeckoLibUtil
import net.Chidoziealways.everythingcore.capabilities.SharedCapabilities
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.getAnimationSeconds
import net.Chidoziealways.everythingjapanese.entity.getAnimationTick
import net.Chidoziealways.everythingjapanese.entity.isAnimationPlaying
import net.Chidoziealways.everythingjapanese.quest.ModQuests
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.animal.golem.IronGolem
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.npc.Npc
import net.minecraft.world.entity.npc.villager.AbstractVillager
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.neoforged.neoforge.common.CommonHooks
import java.util.Random

class CursedSamurai(level: Level): Monster(ModEntities.CURSED_SAMURAI, level), Npc, GeoEntity {

    val WALK = RawAnimation.begin().thenLoop("walk")
    val IDLE = RawAnimation.begin().thenLoop("idle")
    val HORIZONTAL_SWORD_SWING = RawAnimation.begin().thenPlay("horizontal_sword_swing")
    val SUCCESSIVE_SWORD_SWING = RawAnimation.begin().thenPlay("successive_sword_swing")
    val HURT = RawAnimation.begin().thenPlay("hurt")
    val DEATH = RawAnimation.begin().thenPlay("death")

    val geoCache = GeckoLibUtil.createInstanceCache(this)

    private var currentTarget: Entity? = null
    private var hitsRemaining: Int = 0

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController<CursedSamurai>("Movement", 5, this::walkAnimController))
        controllers.add(AnimationController<CursedSamurai>("horizontal_attack_controller", 0) { animState: AnimationTest<CursedSamurai> ->
            if (animState.isAnimationPlaying()) {
                print("HORIZONTAL ATTACK")
                performAttackIfFrame(animState, "horizontal_sword_swing", listOf(2))
            }
            PlayState.STOP
        }.triggerableAnim("horizontal_sword_swing", HORIZONTAL_SWORD_SWING))

        controllers.add(AnimationController<CursedSamurai>("successive_attack_controller", 0) { animState: AnimationTest<CursedSamurai> ->
            if (animState.isAnimationPlaying()) {
                print("SUCCESSIVE ATTACK")
                performAttackIfFrame(animState, "successive_sword_swing", listOf(2, 3, 4, 5))
            }
            PlayState.STOP
        }.triggerableAnim("successive_sword_swing", SUCCESSIVE_SWORD_SWING))
        controllers.add(AnimationController<CursedSamurai>("hurt", 0) { animTest -> PlayState.STOP }.triggerableAnim("hurt", HURT))
        controllers.add(AnimationController<CursedSamurai>("death", 0) { animTest -> PlayState.STOP }.triggerableAnim("death", DEATH))
    }

    private fun performAttackIfFrame(animState: AnimationTest<CursedSamurai>, animName: String, hitFrames: List<Int>) {
        if (currentTarget == null || hitsRemaining <= 0) return

        val currentTick = animState.getAnimationSeconds().toInt()
        println(currentTick)
        if (hitFrames.contains(currentTick)) {
            // Melee range check
            if (distanceTo(currentTarget!!) <= 5.0) {
                currentTarget!!.hurt(damageSources().mobAttack(this), attributes.getValue(Attributes.ATTACK_DAMAGE).toFloat())
            }
            hitsRemaining--
            if (hitsRemaining <= 0) currentTarget = null
        }
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
        //goalSelector.addGoal(9, MeleeAttackGoal(this, 1.0, true))
        goalSelector.addGoal(6, MoveThroughVillageGoal(this, 1.0, false, 6) {true})
        targetSelector.addGoal(5, HurtByTargetGoal(this).setAlertOthers(CursedSamurai::class.java))
        //targetSelector.addGoal(10, NearestAttackableTargetGoal(this, Player::class.java, false))
        //targetSelector.addGoal(10, NearestAttackableTargetGoal(this, AbstractVillager::class.java, false))
        //targetSelector.addGoal(10, NearestAttackableTargetGoal(this, IronGolem::class.java, false))
    }

    override fun doHurtTarget(server: ServerLevel, entity: Entity): Boolean {
        val attacks = arrayOf("horizontal_sword_swing", "successive_sword_swing")
        val controllers = arrayOf("horizontal_attack_controller", "successive_attack_controller")
        val index = Random().nextInt(0, 2)

        currentTarget = entity
        hitsRemaining = if (attacks[index] == "horizontal_sword_swing") 1 else 4

        triggerAnim(controllers[index], attacks[index])

        return super.doHurtTarget(server, entity) // damage will be handled in the controller
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

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        if (this.level().isClientSide) return super.mobInteract(player, hand)
        val stack = player.getItemInHand(hand)
        if (!stack.isEmpty) return super.mobInteract(player, hand)
        val cap = player.getCapability(SharedCapabilities.QUEST_CAPABILITY) ?: return super.mobInteract(player, hand)
        cap.giveQuest(ModQuests.VILLAGE.identifier(), player as ServerPlayer)
        return super.mobInteract(player, hand)
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
                if (entity == null || entity.killedEntity(level, this, damageSource)) {
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
                .add(Attributes.ARMOR, 100.0)
                .add(Attributes.ARMOR_TOUGHNESS, 900.0)
                .add(Attributes.MAX_HEALTH, 100.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 10.0)
        }
    }
}