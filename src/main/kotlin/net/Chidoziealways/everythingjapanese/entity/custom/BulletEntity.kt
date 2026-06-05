package net.Chidoziealways.everythingjapanese.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.arrow.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class BulletEntity: AbstractArrow {
    constructor(level: Level): super(ModEntities.BULLET, level) {
        this.isNoGravity = true
    }

    constructor(level: Level, x: Double, y: Double, z: Double, pickupItemStack: ItemStack, firedFromWeapon: ItemStack?): super(ModEntities.BULLET, x, y, z, level, pickupItemStack, firedFromWeapon) {
        this.isNoGravity = true
    }

    constructor(level: Level, owner: LivingEntity, pickupItemStack: ItemStack, firedFromWeapon: ItemStack?): super(ModEntities.BULLET, owner, level, pickupItemStack, firedFromWeapon) {
        this.isNoGravity = true
    }

    override fun getDefaultPickupItem(): ItemStack = ItemStack(JModItems.BULLET)

    override fun getDefaultGravity(): Double {
        return 0.0
    }
}