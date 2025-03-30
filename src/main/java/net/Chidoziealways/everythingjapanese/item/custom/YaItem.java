package net.Chidoziealways.everythingjapanese.item.custom;

import net.Chidoziealways.everythingjapanese.entity.custom.YaProjectileEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class YaItem extends Item implements ProjectileItem {
    public YaItem(Properties pProperties) {
        super(pProperties);
    }

    public AbstractArrow createArrow(Level pLevel, ItemStack pAmmo, LivingEntity pShooter, @Nullable ItemStack pWeapon) {
        return new YaProjectileEntity(pLevel, pShooter, pAmmo.copyWithCount(1), pWeapon);
    }

    @Override
    public Projectile asProjectile(Level pLevel, Position pPos, ItemStack pStack, Direction pDirection) {
        YaProjectileEntity ya = new YaProjectileEntity(pLevel, pPos.x(), pPos.y(), pPos.z(), pStack.copyWithCount(1), null);
        ya.pickup = AbstractArrow.Pickup.ALLOWED;
        return ya;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.LivingEntity owner) {
        return false;
        // Infinity doesnt go on arrows anymore..
        //int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        //return enchant <= 0 ? false : this.getClass() == ArrowItem.class;
    }
}
