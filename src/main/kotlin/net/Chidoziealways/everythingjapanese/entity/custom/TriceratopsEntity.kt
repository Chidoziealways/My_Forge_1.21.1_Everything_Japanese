package net.Chidoziealways.everythingjapanese.entity.custom

import com.mojang.serialization.Codec
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.TriceratopsVariant
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.Util
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerBossEvent
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.BossEvent
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

class TriceratopsEntity(pEntityType: EntityType<out Animal?>, pLevel: Level) : Animal(pEntityType, pLevel) {
    val idleAnimationState: AnimationState = AnimationState()
    private var idleAnimationTimeout = 0

    private val bossEvent = ServerBossEvent(
        Component.literal("私たちのかっこいトリケラトプス(Our Cool Triceratops)"),
        BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20
    )

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
        return ModEntities.TRICERATOPS.create(pLevel, EntitySpawnReason.BREEDING)
    }

    private fun setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout =
                80 //This should match the number of ticks your idle animation plays for which is S X 20 S = seconds
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

    /*VARIANT*/
    override fun defineSynchedData(pBuilder: SynchedEntityData.Builder) {
        super.defineSynchedData(pBuilder)
        pBuilder.define(VARIANT, 0)
    }

    private val typeVariant: Int
        get() = this.entityData.get(VARIANT)

    var variant: TriceratopsVariant?
        get() = TriceratopsVariant.Companion.byId(this.typeVariant and 255)
        private set(variant) {
            this.entityData.set(VARIANT, variant!!.id and 255)
        }

    public override fun addAdditionalSaveData(output: ValueOutput) {
        super.addAdditionalSaveData(output)
        output.store("Variant", Codec.INT, this.typeVariant)
    }

    public override fun readAdditionalSaveData(input: ValueInput) {
        super.readAdditionalSaveData(input)
        this.entityData.set(VARIANT, input.read("Variant", Codec.INT).orElse(0))
    }

    override fun finalizeSpawn(
        pLevel: ServerLevelAccessor, pDifficulty: DifficultyInstance,
        pSpawnType: EntitySpawnReason, pSpawnGroupData: SpawnGroupData?
    ): SpawnGroupData {
        val variant = Util.getRandom<TriceratopsVariant>(TriceratopsVariant.entries.toTypedArray(), this.random)
        this.variant = variant
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData)!!
    }

    override fun finalizeSpawnChildFromBreeding(pLevel: ServerLevel, pAnimal: Animal, pBaby: AgeableMob?) {
        val variant = Util.getRandom<TriceratopsVariant>(TriceratopsVariant.entries.toTypedArray(), this.random)
        (pBaby as TriceratopsEntity).variant = variant
        super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby)
    }

    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT
    }

    override fun getHurtSound(pDamageSource: DamageSource): SoundEvent? {
        return SoundEvents.RAVAGER_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.SNIFFER_DEATH
    }

    override fun startSeenByPlayer(pServerPlayer: ServerPlayer) {
        super.startSeenByPlayer(pServerPlayer)
        this.bossEvent.addPlayer(pServerPlayer)
    }

    override fun stopSeenByPlayer(pServerPlayer: ServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer)
        this.bossEvent.removePlayer(pServerPlayer)
    }

    override fun aiStep() {
        super.aiStep()
        this.bossEvent.setProgress(this.health / this.maxHealth)
    }

    companion object {
        private val VARIANT: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(TriceratopsEntity::class.java, EntityDataSerializers.INT)

        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.TEMPT_RANGE, 24.0)
        }
    }
}
