package net.Chidoziealways.everythingjapanese.screen.custom.growthchamber;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GrowthChamberScreen extends AbstractContainerScreen<GrowthChamberMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/gui/growth_chamber/growth_chamber_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/gui/arrow_progress.png");

    public GrowthChamberScreen(GrowthChamberMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
        // 1) Compute top‑left corner to center the GUI
        int x = (this.width  - this.imageWidth ) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // 2) Draw the full background in one call
        //    – no more setShader/setShaderTexture calls
        //    – blit now takes a RenderType supplier + texture + coords + texture‑size
        gui.blit(
                RenderType::guiTextured,      // use the built‑in GUI shader/type :contentReference[oaicite:0]{index=0}
                GUI_TEXTURE,                  // your GUI texture ResourceLocation
                x, y,                         // screen position
                0, 0,                         // u/v start in the texture
                imageWidth, imageHeight,      // width/height to draw
                256, 256                      // full PNG size of your texture :contentReference[oaicite:1]{index=1}
        );

        // 3) Draw the animated arrow (same pattern)
        renderProgressArrow(gui, x, y);
    }

    private void renderProgressArrow(GuiGraphics gui, int x, int y) {
        if (!menu.isCrafting()) return;

        int arrowWidth = menu.getScaledArrowProgress();
        // draw only the partial arrow
        gui.blit(
                RenderType::guiTextured,   // still the same GUI render type :contentReference[oaicite:2]{index=2}
                GUI_TEXTURE,               // same texture atlas
                x + 73, y + 35,            // arrow offset inside the GUI
                176, 14,                   // u/v start of the arrow in the texture
                arrowWidth, 16,            // draw only this wide slice
                256, 256                   // overall texture dimensions :contentReference[oaicite:3]{index=3}
        );
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
