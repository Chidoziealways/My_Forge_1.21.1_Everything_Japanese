package net.Chidoziealways.everythingjapanese.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.core.Direction
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.Vec2

class IronBattleAxeProjectileEntity : AbstractArrow {
    private var rotation = 0f
    var groundedOffset: Vec2? = null

    constructor(pEntityType: EntityType<out AbstractArrow?>, pLevel: Level) : super(pEntityType, pLevel)

    constructor(shooter: LivingEntity, level: Level) : super(
        ModEntities.IRON_BATTLE_AXE,
        shooter,
        level,
        ItemStack(ModItems.IRON_BATTLE_AXE),
        null
    )

    override fun getDefaultPickupItem(): ItemStack {
        return ItemStack(ModItems.IRON_BATTLE_AXE)
    }

    val renderingRotation: Float
        get() {
            rotation += 0.5f
            if (rotation >= 360) {
                rotation = 0f
            }
            return rotation
        }

    val isGrounded: Boolean
        get() = onGround()

    override fun onHitEntity(result: EntityHitResult) {
        super.onHitEntity(result)
        val entity = result.getEntity()
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4f)

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, 3.toByte())
            this.discard()
        }
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)

        if (result.getDirection() == Direction.SOUTH) {
            groundedOffset = Vec2(215f, 180f)
        }
        if (result.getDirection() == Direction.NORTH) {
            groundedOffset = Vec2(215f, 0f)
        }
        if (result.getDirection() == Direction.EAST) {
            groundedOffset = Vec2(215f, -90f)
        }
        if (result.getDirection() == Direction.WEST) {
            groundedOffset = Vec2(215f, 90f)
        }

        if (result.getDirection() == Direction.DOWN) {
            groundedOffset = Vec2(115f, 180f)
        }
        if (result.getDirection() == Direction.UP) {
            groundedOffset = Vec2(285f, 180f)
        }
    }
}
