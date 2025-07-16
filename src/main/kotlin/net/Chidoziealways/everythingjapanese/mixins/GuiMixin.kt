package net.Chidoziealways.everythingjapanese.mixins

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.ChakraHUDOverlay.renderChakraBar
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiGraphics
import net.minecraftforge.common.util.NonNullConsumer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.spongepowered.asm.mixin.Final
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(value = [Gui::class], priority = 6000)
abstract class GuiMixin {
    @Shadow
    @Final
    private val minecraft: Minecraft? = null

    @Inject(method = ["renderHotbarAndDecorations"], at = [At("TAIL")])
    private fun onRender(pGuiGraphics: GuiGraphics, pDeltaTracker: DeltaTracker?, ci: CallbackInfo?) {
        if (minecraft!!.player == null) return
        // Fetch the client-side stored Chakra (updated via packets)
        minecraft.player!!.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY!!)
            .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                renderChakraBar(
                    pGuiGraphics,
                    iChakra!!.chakra,
                    iChakra.getMaxChakra()
                )
            })
    }

    private companion object {
        private val log: Logger? = LoggerFactory.getLogger(GuiMixin::class.java)
    }
}
