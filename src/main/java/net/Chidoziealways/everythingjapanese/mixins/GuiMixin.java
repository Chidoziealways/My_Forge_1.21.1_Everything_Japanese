package net.Chidoziealways.everythingjapanese.mixins;

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.Chidoziealways.everythingjapanese.chakra.ChakraHUDOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = {Gui.class},
        priority = 6000
)
public abstract class GuiMixin {

    private static final Logger log = LoggerFactory.getLogger(GuiMixin.class);
    @Shadow @Final private Minecraft minecraft;

    @Unique
    private float lastChakra = -1; // Store previous chakra value

    @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"))
    private void onRender(GuiGraphics pGuiGraphics, DeltaTracker pDeltaTracker, CallbackInfo ci) {
        if (minecraft.player == null) return;

        // Fetch Chakra Capability
        minecraft.player.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iChakra -> {
            float currentChakra = iChakra.getChakra();

            ChakraHUDOverlay.renderChakraBar(pGuiGraphics, currentChakra, iChakra.getMaxChakra());

            // Only redraw if chakra has changed
            if (currentChakra != lastChakra) {
                log.debug("Updating Chakra Bar: {} -> {}", lastChakra, currentChakra);
                ChakraHUDOverlay.renderChakraBar(pGuiGraphics, currentChakra, iChakra.getMaxChakra());
                lastChakra = currentChakra; // Update stored value
            } else {
                log.debug("The Chakra is the same");
            }
        });
    }
}
