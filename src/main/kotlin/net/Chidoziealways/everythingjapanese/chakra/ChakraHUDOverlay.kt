package net.Chidoziealways.everythingjapanese.chakra

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ChakraHUDOverlay {
    private val log: Logger? = LoggerFactory.getLogger(ChakraHUDOverlay::class.java)

    @JvmStatic
    fun renderChakraBar(guiGraphics: GuiGraphics, chakra: Float, maxChakra: Int) {
        var maxChakra = maxChakra
        val minecraft = Minecraft.getInstance()
        val font = minecraft.font

        val screenWidth = minecraft.getWindow().getGuiScaledWidth()
        val screenHeight = minecraft.getWindow().getGuiScaledHeight()

        // Bar dimensions and position
        val barWidth = 102
        val barHeight = 10
        val x = screenWidth / 2 - (barWidth / 2) // Center horizontally
        val y = screenHeight / 2 - 51 // Position above hotbar

        //log.debug("ScreenWidth: {}, ScreenHeight: {}, x: {}, y: {}, BarWidth: {}, BarHeight: {}", screenWidth, screenHeight, x, y, barWidth, barHeight);
        if (maxChakra <= 0) maxChakra = 1

        // Background bar
        guiGraphics.fill(x, y, x + barWidth, y + barHeight, -0xaaaaab) // Gray background

        //log.debug("Background Minx: {}, Background MinY: {}, Background MaxX: {}, Background MaxY: {}", x, y, x + barWidth, y + barHeight);

        // Foreground bar
        val filledWidth = ((chakra / maxChakra.toFloat()) * (barWidth - 2)).toInt()
        guiGraphics.fill(x + 1, y + 1, x + 1 + filledWidth, y + barHeight - 1, -0xffff01) // Blue bar

        //log.debug("Foreground MinX: {}, Foreground MinY: {}, Foreground MaxX: {}, Foreground MaxY: {}", x + 1, y + 1, x + 1 + filledWidth, y + barHeight - 1);

        // Text overlay
        val chakraText = chakra.toString() + " / " + maxChakra
        val textWidth = font.width(chakraText)
        //log.debug("Chakra: {}, MaxChakra: {}", chakra, maxChakra);
        guiGraphics.drawString(font, chakraText, x + (barWidth - textWidth) / 2, y - 10, 0xFFFFFF, false)
        //log.debug("String X: {}, String Y: {}", x + (barWidth - textWidth) / 2, y - 10);
        //log.debug("Font: {}", font);
    }
}
