package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ProjectileWeaponItem
import net.minecraft.world.level.Level
import java.util.function.Predicate

class GunItem(props: Properties): ProjectileWeaponItem(props) {
    override fun releaseUsing(stack: ItemStack, level: Level, entity: LivingEntity, timeLeft: Int): Boolean {
        if (entity !is Player) return false
        val itemStack = entity.getProjectile(stack)
        if (itemStack.isEmpty) return false
        val i = getUseDuration(stack, entity) - timeLeft
        if (i < 0) return false
        val f = getPowerForTime(i)
        if (f < 0.1) return false
        val list = draw(stack, itemStack, entity)
        if (level is ServerLevel && !list.isEmpty()) {
            this.shoot(level, entity, entity.usedItemHand, stack, list, f * 30.0F, 0F, f >= 1.0F, null)

            level.playSound(
                null,
                entity.x,
                entity.y,
                entity.z,
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0f,
                1.0f  / (level.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f
            )
            entity.awardStat(Stats.ITEM_USED.get(this))
        }
        return true
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        val stack = player.getItemInHand(hand)
        val flag = !player.getProjectile(stack).isEmpty

        if (!player.hasInfiniteMaterials() && !flag) return InteractionResult.FAIL
        player.startUsingItem(hand)
        return InteractionResult.CONSUME
    }

    override fun createProjectile(
        level: Level,
        shooter: LivingEntity,
        weapon: ItemStack,
        ammo: ItemStack,
        isCrit: Boolean
    ): Projectile {
        val bulletItem: BulletItem = ammo.item as? BulletItem ?: JModItems.BULLET
        val bullet = bulletItem.createBullet(level, ammo, shooter, weapon)
        if (isCrit) bullet.isCritArrow = true
        return bullet
    }

    @Deprecated("Deprecated in Java")
    override fun getAllSupportedProjectiles(): Predicate<ItemStack> = BULLET

    override fun getDefaultProjectileRange(): Int = 30

    override fun shootProjectile(shooter: LivingEntity, projectile: Projectile, index: Int, velocity: Float, inaccuracy: Float, angle: Float, target: LivingEntity?) {
        projectile.shootFromRotation(shooter, shooter.xRot, shooter.yRot + angle, 0.0F, velocity, inaccuracy)
    }

    override fun getUseDuration(stack: ItemStack, entity: LivingEntity): Int = 5200

    companion object {
        val BULLET: Predicate<ItemStack> = Predicate { item -> item.`is`(ModTags.Items.BULLETS) }

        fun getPowerForTime(charge: Int): Float {
            var f = charge / 20f
            f = (f * f + f * 2.0F) / 3.0F
            if (f > 1.0F) f = 1.0F

            return f
        }
    }
}