package net.Chidoziealways.everythingjapanese.entity.custom

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.custom.ChiretsuShōProjectileEntity
import net.Chidoziealways.everythingjapanese.jutsu.MasteryHandler
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.projectile.ProjectileUtil
import net.minecraft.world.entity.projectile.ThrowableProjectile
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import kotlin.math.atan2
import kotlin.math.sin
import kotlin.math.sqrt

class EkiretsuShōProjectileEntity (
    level: Level
) : ThrowableProjectile(ModEntities.EKIRETSU_SHO_PROJECTILE, level) {

    companion object {
        private val FLUID_ID = SynchedEntityData.defineId(EkiretsuShōProjectileEntity::class.java,
            EntityDataSerializers.STRING)
    }

    private var noPhysics = false

    fun setNoPhysics(flag: Boolean) {
        noPhysics = flag
        this.setNoGravity(flag)  // easier to keep it floating
    }

    var levitationTargetY: Double? = null
    var levitating = false
    var levitationSpeed = 0.05

    var flyingFluid = false

    fun setFlying(flag: Boolean) {
        flyingFluid = flag
        this.isNoGravity = flag // just to be safe
    }

    override fun tick() {
        if (flyingFluid) {
            val startPos = position()
            val endPos = startPos.add(deltaMovement)

            // Rotation
            val horizontal = sqrt(deltaMovement.x * deltaMovement.x + deltaMovement.z * deltaMovement.z)
            if (horizontal > 0.0) {
                yRot = Math.toDegrees(atan2(deltaMovement.x, deltaMovement.z)).toFloat()
                xRot = Math.toDegrees(atan2(deltaMovement.y, horizontal)).toFloat()
                yRotO = yRot
                xRotO = xRot
            }

            // Raytrace entities & blocks between start and end
            val hitResult = ProjectileUtil.getHitResultOnMoveVector(this) { entity ->
                entity.isPickable && entity != owner
            }

            if (hitResult != null && hitResult.type != HitResult.Type.MISS) {
                when (hitResult) {
                    is EntityHitResult -> onHitEntity(hitResult)
                    is BlockHitResult -> onHitBlock(hitResult)
                }
            } else {
                // Only move if no collision
                setPos(endPos.x, endPos.y, endPos.z)
            }
        }
        else if (noPhysics) {
            // levitating block
            if (levitating && levitationTargetY != null && y < levitationTargetY!!) {
                setPos(x, y + levitationSpeed + 0.01 * sin(tickCount.toDouble()), z)
            } else if (levitating) {
                levitating = false
            }
            // rotation optional
        } else {
            super.tick()
        }
    }


    var fluidState: FluidState = Fluids.EMPTY.defaultFluidState()
        set(value) {
            field = value

            val id = BuiltInRegistries.FLUID.getKey(value.type).toString()
            entityData.set(FLUID_ID, id)
        }

        get() {
            val idString = entityData.get(FLUID_ID)
            val fluid: Fluid = BuiltInRegistries.FLUID.getValue(ResourceLocation.tryParse(idString))
            return fluid.defaultFluidState()
        }

    var blockPos: BlockPos = BlockPos(0, 0, 0)

    constructor(
        level: Level,
        fluidState: FluidState,
        shooter: LivingEntity,
        blockPos: BlockPos
    ) : this(level) {
        this.fluidState = fluidState
        this.setOwner(shooter)
        this.setPos(shooter.x, shooter.eyeY - 0.1, shooter.z)
        this.blockPos = blockPos
    }

    override fun shoot(dx: Double, dy: Double, dz: Double, velocity: Float, inaccuracy: Float) {
        // Normalize the vector
        val distance = sqrt(dx * dx + dy * dy + dz * dz)
        if (distance == 0.0) return

        // Straight-line velocity
        this.deltaMovement = Vec3(dx / distance * velocity, dy / distance * velocity, dz / distance * velocity)
        this.hasImpulse = true

        // Set rotation to face movement direction
        val horizontal = sqrt(deltaMovement.x * deltaMovement.x + deltaMovement.z * deltaMovement.z)
        this.yRot = Math.toDegrees(atan2(deltaMovement.x, deltaMovement.z)).toFloat()
        this.xRot = Math.toDegrees(atan2(deltaMovement.y, horizontal)).toFloat()
        this.yRotO = this.yRot
        this.xRotO = this.xRot
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        super.addAdditionalSaveData(output)
        val fluidId = BuiltInRegistries.FLUID.getKey(fluidState.type).toString()
        output.putString("FluidID", fluidId)
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(FLUID_ID, "minecraft:empty")
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        super.readAdditionalSaveData(input)
        val blockIdStr = input.getStringOr("FluidID", "minecraft:empty")
        val block = BuiltInRegistries.FLUID.getValue(ResourceLocation.tryParse(blockIdStr))
        fluidState = block.defaultFluidState()
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)

        val pos = result.blockPos
        val level = this.level()

        val shooter = this.getOwner()

        if (!level.isClientSide && shooter is ServerPlayer) {
            val blockState = level.getBlockState(pos)

            // Only break if not air or bedrock
            if (!blockState.isAir && !blockState.`is`(Blocks.BEDROCK)) {
                level.destroyBlock(pos, true) // true to drop items
            }

            MasteryHandler.addMastery(shooter, ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "ekiretsu_sho_jutsu"), 0.02f)

            this.discard() // Remove the projectile after hitting
        }
    }

    override fun onHitEntity(result: EntityHitResult) {
        super.onHitEntity(result)

        val entity = result.entity
        val shooter = this.getOwner()

        val level = level()

        if (level is ServerLevel && shooter is ServerPlayer) {
            val baseDamage = 5.0f

            val mastery = MasteryHandler.getMastery(shooter, ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "ekiretsu_sho_jutsu"))

            val scaledDamage = baseDamage * (1f + (mastery / 200f))

            // Deal 5 damage from the shooter, if available
            if (entity != shooter) {
                entity.hurtServer(level, damageSources().indirectMagic(this, shooter), scaledDamage)

                // Determine mastery gain
                val category = entity.type.category
                var gain = when (category) {
                    MobCategory.MONSTER -> 0.9f // Hostile
                    MobCategory.CREATURE -> 0.5f
                    MobCategory.AXOLOTLS -> 0.5f
                    MobCategory.WATER_CREATURE -> 0.7f
                    MobCategory.UNDERGROUND_WATER_CREATURE -> 0.5f // Neutral-ish
                    else -> 0.8f // Passive / misc
                }

                // Bonus if killed
                if (!entity.isAlive) {
                    gain *= 4f
                }

                gain *= 1f + (scaledDamage / 50)

                MasteryHandler.addMastery(
                    shooter,
                    ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "ekiretsu_sho_jutsu"),
                    gain
                )

                this.discard()
            }
        }
    }
}