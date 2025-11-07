package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.entity.custom.BulletEntity
import net.minecraft.core.Direction
import net.minecraft.core.Position
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ProjectileItem
import net.minecraft.world.level.Level

class BulletItem(props: Properties): Item(props), ProjectileItem {
    override fun asProjectile(
        level: Level,
        pos: Position,
        stack: ItemStack,
        direction: Direction
    ): Projectile {
        val bullet = BulletEntity(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null)
        bullet.pickup = AbstractArrow.Pickup.ALLOWED
        return bullet
    }

    fun createBullet(level: Level, ammo: ItemStack, shooter: LivingEntity, weapon: ItemStack?): BulletEntity {
        return BulletEntity(level, shooter, ammo.copyWithCount(1), weapon)
    }
}