package net.Chidoziealways.everythingjapanese.entity.client.chair

import net.Chidoziealways.everythingjapanese.entity.custom.ChairEntity
import net.minecraft.client.renderer.culling.Frustum
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider

class ChairRenderer(pContext: EntityRendererProvider.Context) :
    EntityRenderer<ChairEntity, ChairRenderState>(pContext) {
    override fun shouldRender(
        pLivingEntity: ChairEntity,
        pCamera: Frustum,
        pCamX: Double,
        pCamY: Double,
        pCamZ: Double
    ): Boolean {
        return true
    }

    override fun createRenderState(): ChairRenderState {
        return ChairRenderState()
    }

    override fun extractRenderState(pEntity: ChairEntity, pReusedState: ChairRenderState, pPartialTick: Float) {
        pReusedState.entity = pEntity
        super.extractRenderState(pEntity, pReusedState, pPartialTick)
    }
}
