package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.component.BlockItemStateProperties
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, value = [Dist.CLIENT])
object ToolTipEventHandler {
    @SubscribeEvent
    fun onItemTooltip(event: ItemTooltipEvent) {
        if (event.itemStack.item === ModItems.RADIATION_STAFF) {
            event.toolTip.add(Component.literal("This Item does literally NOTHING"))
        }

        if (event.itemStack.item === ModItems.IRON_BATTLE_AXE) {
            event.toolTip.add(Component.literal("Right Click to Throw this Item!"))
        }

        if (event.itemStack.item === ModItems.SUSHI) {
            event.toolTip.add(Component.translatable("tooltip.everythingjapanese.sushi"))
        }

        if (event.itemStack.item === ModBlocks.TRANSFORMER_BLOCK.asItem()) {
            event.toolTip.add(Component.translatable("tooltip.everythingjapanese.magic_block.tooltip"))
        }

        if (event.itemStack.item === ModItems.CHISEL) {
            val pStack = ModItems.CHISEL.defaultInstance

            if (Screen.hasShiftDown()) {
                event.toolTip.add(Component.translatable("tooltip.everythingjapanese.chisel_item"))
            } else {
                event.toolTip.add(Component.translatable("tooltip.everythingjapanese.chisel_item.shift_down"))
            }

            if (pStack.get(ModDataComponentTypes.COORDINATES) != null) {
                event.toolTip
                    .add(Component.literal("Last Block Changed at :" + pStack.get<BlockPos?>(ModDataComponentTypes.COORDINATES)))
            }
            if (pStack.get<BlockItemStateProperties?>(DataComponents.BLOCK_STATE) != null) {
                event.toolTip.add(
                    Component.literal(
                        "Last Block Changed state:" + pStack.get<BlockItemStateProperties?>(DataComponents.BLOCK_STATE)
                    )
                )
            }
        }
    }
}
