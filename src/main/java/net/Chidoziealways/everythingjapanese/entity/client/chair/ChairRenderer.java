package net.Chidoziealways.everythingjapanese.entity.client.chair;

import net.Chidoziealways.everythingjapanese.entity.custom.ChairEntity;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ChairRenderer extends EntityRenderer<ChairEntity, ChairRenderState> {
    public ChairRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public boolean shouldRender(ChairEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }

    @Override
    public ChairRenderState createRenderState() {
        return new ChairRenderState();
    }

    @Override
    public void extractRenderState(ChairEntity pEntity, ChairRenderState pReusedState, float pPartialTick) {
        pReusedState.setEntity(pEntity);
        super.extractRenderState(pEntity, pReusedState, pPartialTick);
    }
}
