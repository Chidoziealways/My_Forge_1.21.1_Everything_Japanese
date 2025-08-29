package net.Chidoziealways.everythingjapanese.screen.custom.pedestal

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class PedestalScreen(pMenu: PedestalMenu, pPlayerInventory: Inventory, pTitle: Component) :
    AbstractContainerScreen<PedestalMenu?>(pMenu, pPlayerInventory, pTitle) {

        init {
            imageWidth = 256
            imageHeight = 256
        }

    override fun renderBg(gui: GuiGraphics, partialTicks: Float, mouseX: Int, mouseY: Int) {
        // 1) Compute top‑left corner to center the GUI
        val x = (this.width - this.imageWidth) / 2
        val y = (this.height - this.imageHeight) / 2

        // 2) Draw the full background in one call
        //    – no more setShader/setShaderTexture calls
        //    – blit now takes a RenderType supplier + texture + coords + texture‑size
        gui.blit(
            RenderPipelines.GUI_TEXTURED,
            GUI_TEXTURE,  // your GUI texture ResourceLocation
            x, y,  // screen position
            0F, 0F,  // u/v start in the texture
            imageWidth, imageHeight,  // width/height to draw
            256, 256 // full PNG size of your texture :contentReference[oaicite:1]{index=1}
        )
    }

    override fun render(pGuiGraphics: GuiGraphics, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick)
        renderTooltip(pGuiGraphics, pMouseX, pMouseY)
    }

    companion object {
        private val GUI_TEXTURE: ResourceLocation =
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/gui/pedestal/pedestal_gui.png")
    }
}
