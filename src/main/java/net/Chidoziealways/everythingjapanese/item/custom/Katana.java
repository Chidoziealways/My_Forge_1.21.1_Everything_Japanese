package net.Chidoziealways.everythingjapanese.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class Katana extends Item {
    MobEffects effects;
    public Katana(Properties pProperties, MobEffects effects) {
        super(pProperties);
        this.effects = effects;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, TooltipDisplay display, Consumer<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()){
            pTooltipComponents.accept(Component.translatable("tooltip.everythingjapanese.earth_katana"));
        }
        else {
            pTooltipComponents.accept(Component.translatable("tooltip.everythingjapanese.earth_katana.no_shift"));
        }
        super.appendHoverText(pStack, pContext, display, pTooltipComponents, pTooltipFlag);
    }
}
