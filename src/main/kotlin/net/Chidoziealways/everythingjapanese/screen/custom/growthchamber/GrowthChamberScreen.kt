package net.Chidoziealways.everythingjapanese.screen.custom.growthchamber

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class GrowthChamberScreen(pMenu: GrowthChamberMenu, pPlayerInventory: Inventory, pTitle: Component) :
    AbstractContainerScreen<GrowthChamberMenu>(pMenu, pPlayerInventory, pTitle) {
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

        // 3) Draw the animated arrow (same pattern)
        renderProgressArrow(gui, x, y)
    }

    private fun renderProgressArrow(gui: GuiGraphics, x: Int, y: Int) {
        if (!menu.isCrafting) return

        val arrowWidth = menu.scaledArrowProgress
        // draw only the partial arrow
        gui.blit(
            RenderPipelines.GUI_TEXTURED,
            ARROW_TEXTURE,  // same texture atlas
            x + 73, y + 35,  // arrow offset inside the GUI
            176F, 14F,  // u/v start of the arrow in the texture
            arrowWidth, 16,  // draw only this wide slice
            24, 16 // overall texture dimensions :contentReference[oaicite:3]{index=3}
        )
    }


    override fun render(pGuiGraphics: GuiGraphics, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick)
        renderTooltip(pGuiGraphics, pMouseX, pMouseY)
    }

    companion object {
        private val GUI_TEXTURE: ResourceLocation = ResourceLocation.fromNamespaceAndPath(
            MOD_ID,
            "textures/gui/growth_chamber/growth_chamber_gui.png"
        )
        private val ARROW_TEXTURE: ResourceLocation =
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/gui/arrow_progress.png")
    }
}
