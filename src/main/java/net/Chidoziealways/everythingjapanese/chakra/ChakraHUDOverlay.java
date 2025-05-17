package net.Chidoziealways.everythingjapanese.chakra;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, value = Dist.CLIENT)
public class ChakraHUDOverlay {
    private static final Logger log = LoggerFactory.getLogger(ChakraHUDOverlay.class);

    public static void renderChakraBar(GuiGraphics guiGraphics, float chakra, int maxChakra) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;

        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        // Bar dimensions and position
        int barWidth = 102;
        int barHeight = 10;
        int x = screenWidth / 2 - (barWidth / 2); // Center horizontally
        int y = screenHeight / 2 - 51; // Position above hotbar
        //log.debug("ScreenWidth: {}, ScreenHeight: {}, x: {}, y: {}, BarWidth: {}, BarHeight: {}", screenWidth, screenHeight, x, y, barWidth, barHeight);

        if (maxChakra <= 0) maxChakra = 1;

        // Background bar
        guiGraphics.fill(x, y, x + barWidth, y + barHeight, 0xFF555555); // Gray background
        //log.debug("Background Minx: {}, Background MinY: {}, Background MaxX: {}, Background MaxY: {}", x, y, x + barWidth, y + barHeight);

        // Foreground bar
        int filledWidth = (int) ((chakra / (float) maxChakra) * (barWidth - 2));
        guiGraphics.fill(x + 1, y + 1, x + 1 + filledWidth, y + barHeight - 1, 0xFF0000FF); // Blue bar
        //log.debug("Foreground MinX: {}, Foreground MinY: {}, Foreground MaxX: {}, Foreground MaxY: {}", x + 1, y + 1, x + 1 + filledWidth, y + barHeight - 1);

        // Text overlay
        String chakraText = chakra + " / " + maxChakra;
        int textWidth = font.width(chakraText);
        //log.debug("Chakra: {}, MaxChakra: {}", chakra, maxChakra);
        guiGraphics.drawString(font, chakraText, x + (barWidth - textWidth) / 2, y - 10, 0xFFFFFF, false);
        //log.debug("String X: {}, String Y: {}", x + (barWidth - textWidth) / 2, y - 10);
        //log.debug("Font: {}", font);
    }
}
