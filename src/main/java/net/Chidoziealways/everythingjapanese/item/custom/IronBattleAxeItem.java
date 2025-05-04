package net.Chidoziealways.everythingjapanese.item.custom;

import net.Chidoziealways.everythingjapanese.entity.custom.IronBattleAxeProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IronBattleAxeItem extends Item {
    public IronBattleAxeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            IronBattleAxeProjectileEntity ironBattleAxeProjectile = new IronBattleAxeProjectileEntity(pPlayer, pLevel);
            ironBattleAxeProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 0F);
            pLevel.addFreshEntity(ironBattleAxeProjectile);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild)
            itemStack.shrink(1);

        return InteractionResult.SUCCESS;
    }
}
