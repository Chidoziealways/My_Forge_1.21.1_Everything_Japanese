package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ToolTipEventHandler {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event){
        if (event.getItemStack().getItem() == ModItems.RADIATION_STAFF.get()) {
            event.getToolTip().add(Component.literal("This Item does literally NOTHING"));
        }

        if (event.getItemStack().getItem() == ModItems.IRON_BATTLE_AXE.get()) {
            event.getToolTip().add(Component.literal("Right Click to Throw this Item!"));
        }

        if (event.getItemStack().getItem() == ModItems.SUSHI.get()) {
            event.getToolTip().add(Component.translatable("tooltip.everythingjapanese.sushi"));
        }

        if (event.getItemStack().getItem() == ModBlocks.TRANSFORMER_BLOCK.get().asItem()) {
            event.getToolTip().add(Component.translatable("tooltip.everythingjapanese.magic_block.tooltip"));
        }

        if (event.getItemStack().getItem() == ModItems.CHISEL.get()) {
            ItemStack pStack = ModItems.CHISEL.get().getDefaultInstance();

            if(Screen.hasShiftDown()){
                event.getToolTip().add(Component.translatable("tooltip.everythingjapanese.chisel_item"));
            }else {
                event.getToolTip().add(Component.translatable("tooltip.everythingjapanese.chisel_item.shift_down"));
            }

            if(pStack.get(ModDataComponentTypes.COORDINATES.get()) != null){
                event.getToolTip().add(Component.literal("Last Block Changed at :" + pStack.get(ModDataComponentTypes.COORDINATES.get())));
            }
            if(pStack.get(DataComponents.BLOCK_STATE) != null){
                event.getToolTip().add(Component.literal("Last Block Changed state:" + pStack.get(DataComponents.BLOCK_STATE)));
            }
        }
    }
}
