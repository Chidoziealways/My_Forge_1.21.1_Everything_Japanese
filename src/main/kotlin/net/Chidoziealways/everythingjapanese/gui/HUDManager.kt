package net.Chidoziealways.everythingjapanese.gui

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.jutsu.MasteryHandler
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation

object HUDManager {

    private const val BAR_WIDTH = 100
    private const val BAR_HEIGHT = 8
    private const val PADDING = 4

    var startY = 10

    fun renderJutsuHUD(gui: GuiGraphics, partialTicks: DeltaTracker) {
        val mc = Minecraft.getInstance()
        val player = mc.player ?: return

        val jutsuCap = player.getCapability(ModCapabilities.JUTSU_CAPABILITY) ?: return
        val selectedJutsuId = jutsuCap.getSelectedJutsu()
        val allJutsus = jutsuCap.getLearnedJutsus()

        val startX = 10
        startY = 10

        ModRegistries.JUTSU.getOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, selectedJutsuId)).ifPresent { selectedJutsu ->
            gui.drawString(mc.font, "Jutsu: ${selectedJutsu.name}", startX, startY, 0xFFFFFFFF.toInt())
            startY += PADDING + 20

            val masteryValue = MasteryHandler.getMastery(player, ResourceLocation.fromNamespaceAndPath(MOD_ID, selectedJutsuId))
            val masteryFraction = masteryValue / 1000
            gui.fill(
                startX,
                startY,
                startX + (BAR_WIDTH * masteryFraction).coerceAtMost(BAR_WIDTH.toFloat()).toInt(),
                startY + BAR_HEIGHT,
                0xFFFFAA00.toInt()
            )
            val masteryText = "Mastery: $masteryValue / 1000"
            val masteryTextWidth = mc.font.width(masteryText)
            gui.drawString(mc.font, masteryText, startX + (BAR_WIDTH - masteryTextWidth) / 2, startY - 10, 0xFFFFFFFF.toInt(), false)

            startY += BAR_HEIGHT + 10

            for (jutsuId in allJutsus) {
                val color = if (jutsuId == selectedJutsuId) 0xFF00FF00.toInt() else 0xFFFFFFFF.toInt()
                ModRegistries.JUTSU.getOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, jutsuId)).ifPresent { juts ->
                    gui.drawString(mc.font, juts.name, startX, startY, color)
                    startY += 12
                }
            }
        }
    }

    fun renderChakraHUD(gui: GuiGraphics, deltaTracker: DeltaTracker) {
        val mc = Minecraft.getInstance()
        val player = mc.player ?: return
        val chakraCap = player.getCapability(ModCapabilities.CHAKRA_CAPABILITY) ?: return

        val chakra = chakraCap.getCurrentChakra()
        val maxChakra = chakraCap.getMaxChakra().coerceAtLeast(1)

        val startX = 10
        startY += 10

        val filledWidth = ((chakra / maxChakra.toFloat()) * BAR_WIDTH).coerceAtMost(BAR_WIDTH.toFloat()).toInt()

        gui.fill(startX, startY, startX + BAR_WIDTH, startY + BAR_HEIGHT, 0xFF555555.toInt())
        gui.fill(startX, startY, startX + filledWidth, startY + BAR_HEIGHT, 0xFF0000FF.toInt())

        val text = "Chakra: $chakra / $maxChakra"
        val textWidth = mc.font.width(text)
        gui.drawString(mc.font, text, startX + (BAR_WIDTH - textWidth) / 2, startY - 10, 0xFFFFFFFF.toInt(), false)

        startY += BAR_HEIGHT
    }

    fun renderStaminaHUD(gui: GuiGraphics, deltaTracker: DeltaTracker) {
        val mc = Minecraft.getInstance()
        val player = mc.player ?: return
        val staminaCap = player.getCapability(ModCapabilities.STAMINA_CAPABILITY) ?: return

        val stamina = staminaCap.getStamina()
        val maxStamina = staminaCap.getMaxStamina().coerceAtLeast(1)

        val startX = 10
        startY += 10

        val filledWidth = ((stamina / maxStamina) * BAR_WIDTH).coerceAtMost(BAR_WIDTH.toFloat()).toInt()

        gui.fill(startX, startY, startX + BAR_WIDTH, startY + BAR_HEIGHT, 0xFF555555.toInt())
        gui.fill(startX, startY, startX + filledWidth, startY + BAR_HEIGHT, 0xFF00FF00.toInt()) // green

        val text = "Stamina: $stamina / $maxStamina"
        val textWidth = mc.font.width(text)
        gui.drawString(mc.font, text, startX + (BAR_WIDTH - textWidth) / 2, startY - 10, 0xFFFFFFFF.toInt(), false)
    }

    fun renderMoneyHUD(gui: GuiGraphics, deltaTracker: DeltaTracker) {
        val mc = Minecraft.getInstance()
        val player = mc.player ?: return
        val moneyCap = player.getCapability(ModCapabilities.MONEY_CAPABILITY_ENTITY) ?: return

        val money = moneyCap.getMoney()
        val startX = 10
        startY += 10
        gui.drawString(mc.font, "¥ $money", startX, startY, 0xFFFFFF00.toInt())
        startY += 12
    }
}
