package net.Chidoziealways.everythingjapanese.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class Katana extends SwordItem {
    MobEffects effects;
    public Katana(Tier pTier, Properties pProperties, MobEffects effects) {
        super(pTier, pProperties);
        this.effects = effects;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.everythingjapanese.earth_katana"));
        }
        else {
            pTooltipComponents.add(Component.translatable("tooltip.everythingjapanese.earth_katana.no_shift"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
