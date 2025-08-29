package net.Chidoziealways.everythingjapanese.entity.custom

import it.unimi.dsi.fastutil.ints.Int2IntFunction
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.core.component.DataComponents
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.level.Level
import kotlin.math.max

class YaProjectileEntity : AbstractArrow {
    constructor(entityType: EntityType<out AbstractArrow?>, level: Level) : super(entityType, level)

    constructor(
        pLevel: Level,
        pX: Double,
        pY: Double,
        pZ: Double,
        pPickupItemStack: ItemStack,
        pFiredFromWeapon: ItemStack?
    ) : super(ModEntities.YA, pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon) {
        this.updateColor()
    }

    constructor(pLevel: Level, pOwner: LivingEntity, pPickupItemStack: ItemStack, pFiredFromWeapon: ItemStack?) : super(
        ModEntities.YA,
        pOwner,
        pLevel,
        pPickupItemStack,
        pFiredFromWeapon
    ) {
        this.updateColor()
    }

    private fun getPotionContents(): PotionContents {
        return this.getPickupItemStackOrigin()
            .getOrDefault<PotionContents>(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
    }

    private fun setPotionContents(pPotionContents: PotionContents?) {
        this.getPickupItemStackOrigin().set<PotionContents?>(DataComponents.POTION_CONTENTS, pPotionContents)
        this.updateColor()
    }

    override fun setPickupItemStack(pPickupItemStack: ItemStack) {
        super.setPickupItemStack(pPickupItemStack)
        this.updateColor()
    }

    private fun updateColor() {
        val potioncontents = this.getPotionContents()
        this.entityData.set<Int?>(
            ID_EFFECT_COLOR,
            if (potioncontents == PotionContents.EMPTY) -1 else potioncontents.getColor()
        )
    }

    fun addEffect(pEffectInstance: MobEffectInstance) {
        this.setPotionContents(this.getPotionContents().withEffectAdded(pEffectInstance))
    }

    override fun defineSynchedData(pBuilder: SynchedEntityData.Builder) {
        super.defineSynchedData(pBuilder)
        pBuilder.define<Int?>(ID_EFFECT_COLOR, -1)
    }

    override fun tick() {
        super.tick()
        if (this.level().isClientSide) {
            if (this.onGround()) {
                if (this.inGroundTime % 5 == 0) {
                    this.makeParticle(1)
                }
            } else {
                this.makeParticle(2)
            }
        } else if (this.onGround() && this.inGroundTime != 0 && this.getPotionContents() != PotionContents.EMPTY && this.inGroundTime >= 600) {
            this.level().broadcastEntityEvent(this, 0.toByte())
            this.setPickupItemStack(ItemStack(ModItems.YA))
        }
    }

    private fun makeParticle(pParticleAmount: Int) {
        val i = this.getColor()
        if (i != -1 && pParticleAmount > 0) {
            for (j in 0..<pParticleAmount) {
                this.level()
                    .addParticle(
                        ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, i),
                        this.getRandomX(0.5),
                        this.getRandomY(),
                        this.getRandomZ(0.5),
                        0.0,
                        0.0,
                        0.0
                    )
            }
        }
    }

    fun getColor(): Int {
        return this.entityData.get<Int?>(ID_EFFECT_COLOR)
    }

    override fun doPostHurtEffects(pLiving: LivingEntity) {
        super.doPostHurtEffects(pLiving)
        val entity = this.getEffectSource()
        val potioncontents = this.getPotionContents()
        if (potioncontents.potion().isPresent()) {
            for (mobeffectinstance in potioncontents.potion().get().value().getEffects()) {
                pLiving.addEffect(
                    MobEffectInstance(
                        mobeffectinstance.getEffect(),
                        max(mobeffectinstance.mapDuration(Int2IntFunction { p_268168_: Int -> p_268168_ / 8 }), 1),
                        mobeffectinstance.getAmplifier(),
                        mobeffectinstance.isAmbient(),
                        mobeffectinstance.isVisible()
                    ),
                    entity
                )
            }
        }

        for (mobeffectinstance1 in potioncontents.customEffects()) {
            pLiving.addEffect(mobeffectinstance1, entity)
        }
    }

    override fun getDefaultPickupItem(): ItemStack {
        return ItemStack(ModItems.YA)
    }

    override fun handleEntityEvent(pId: Byte) {
        if (pId.toInt() == 0) {
            val i = this.getColor()
            if (i != -1) {
                val f = (i shr 16 and 0xFF).toFloat() / 255.0f
                val f1 = (i shr 8 and 0xFF).toFloat() / 255.0f
                val f2 = (i shr 0 and 0xFF).toFloat() / 255.0f

                for (j in 0..19) {
                    this.level()
                        .addParticle(
                            ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, f, f1, f2),
                            this.getRandomX(0.5),
                            this.getRandomY(),
                            this.getRandomZ(0.5),
                            0.0,
                            0.0,
                            0.0
                        )
                }
            }
        } else {
            super.handleEntityEvent(pId)
        }
    }

    companion object {
        private const val EXPOSED_POTION_DECAY_TIME = 600
        private val NO_EFFECT_COLOR = -1
        private val ID_EFFECT_COLOR: EntityDataAccessor<Int?> =
            SynchedEntityData.defineId<Int?>(YaProjectileEntity::class.java, EntityDataSerializers.INT)
        private const val EVENT_POTION_PUFF: Byte = 0
    }
}