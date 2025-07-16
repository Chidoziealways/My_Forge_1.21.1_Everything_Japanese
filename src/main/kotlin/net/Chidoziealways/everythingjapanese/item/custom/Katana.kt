package net.Chidoziealways.everythingjapanese.item.custom

import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.TooltipDisplay
import java.util.function.Consumer

class Katana(pProperties: Properties, var effects: MobEffects?) : Item(pProperties) {
    override fun appendHoverText(
        pStack: ItemStack,
        pContext: TooltipContext,
        display: TooltipDisplay,
        pTooltipComponents: Consumer<Component?>,
        pTooltipFlag: TooltipFlag
    ) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.accept(Component.translatable("tooltip.everythingjapanese.earth_katana"))
        } else {
            pTooltipComponents.accept(Component.translatable("tooltip.everythingjapanese.earth_katana.no_shift"))
        }
        super.appendHoverText(pStack, pContext, display, pTooltipComponents, pTooltipFlag)
    }
}
