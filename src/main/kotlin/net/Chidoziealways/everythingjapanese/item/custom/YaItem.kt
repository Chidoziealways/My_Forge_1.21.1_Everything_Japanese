package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.entity.custom.YaProjectileEntity
import net.minecraft.core.Direction
import net.minecraft.core.Position
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ProjectileItem
import net.minecraft.world.level.Level

class YaItem(pProperties: Properties) : Item(pProperties), ProjectileItem {
    fun createArrow(pLevel: Level, pAmmo: ItemStack, pShooter: LivingEntity, pWeapon: ItemStack?): AbstractArrow {
        return YaProjectileEntity(pLevel, pShooter, pAmmo.copyWithCount(1), pWeapon)
    }

    override fun asProjectile(pLevel: Level, pPos: Position, pStack: ItemStack, pDirection: Direction): Projectile {
        val ya = YaProjectileEntity(pLevel, pPos.x(), pPos.y(), pPos.z(), pStack.copyWithCount(1), null)
        ya.pickup = AbstractArrow.Pickup.ALLOWED
        return ya
    }

    fun isInfinite(stack: ItemStack?, bow: ItemStack?, owner: LivingEntity?): Boolean {
        return false
        // Infinity doesnt go on arrows anymore..
        //int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        //return enchant <= 0 ? false : this.getClass() == ArrowItem.class;
    }
}
