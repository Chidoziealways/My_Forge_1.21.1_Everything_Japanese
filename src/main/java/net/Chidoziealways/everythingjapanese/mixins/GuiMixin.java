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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    private static final Logger log = LoggerFactory.getLogger(GuiMixin.class);
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"))
    private void onRender(GuiGraphics pGuiGraphics, DeltaTracker pDeltaTracker, CallbackInfo ci) {
        if (minecraft.player == null || minecraft.screen != null) return;
        log.debug("Mixin!");

        minecraft.player.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iChakra -> ChakraHUDOverlay.renderChakraBar(pGuiGraphics, iChakra.getChakra(), iChakra.getMaxChakra()));
    }
}
