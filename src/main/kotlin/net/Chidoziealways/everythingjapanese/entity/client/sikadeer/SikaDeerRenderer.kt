package net.Chidoziealways.everythingjapanese.entity.client.sikadeer

import com.mojang.blaze3d.vertex.PoseStack
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity
import net.minecraft.client.model.AdultAndBabyModelPair
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.resources.Identifier

class SikaDeerRenderer(pContext: EntityRendererProvider.Context) :
    MobRenderer<SikaDeerEntity, SikaDeerRenderState, SikaDeerModel<SikaDeerEntity>>(
        pContext,
        SikaDeerModel<SikaDeerEntity>(pContext.bakeLayer(ModModelLayers.SIKA_DEER)),
        0.86f
    ) {
    private val models: AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity>>

    init {
        this.models = bakeModel(pContext)
    }

    override fun createRenderState(): SikaDeerRenderState {
        return SikaDeerRenderState()
    }

    override fun extractRenderState(entity: SikaDeerEntity, renderState: SikaDeerRenderState, partialTicks: Float) {
        super.extractRenderState(entity, renderState, partialTicks)
        renderState.entity = entity
        renderState.idleAnimationState.copyFrom(entity.idleAnimationState)
    }

    override fun getTextureLocation(pEntity: SikaDeerRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            JAPANESE_MOD_ID,
            "textures/entity/sikadeer/sika_deer.png"
        )
    }

    override fun submit(
        sikaDeerRenderState: SikaDeerRenderState,
        poseStack: PoseStack,
        collector: SubmitNodeCollector,
        cameraRenderState: CameraRenderState
    ) {
        if (sikaDeerRenderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f)
            this.shadowRadius = 0.35f
        } else {
            poseStack.scale(1.0f, 1.0f, 1.0f)
            this.shadowRadius = 0.7f
        }
        super.submit(sikaDeerRenderState, poseStack, collector, cameraRenderState)
    }

    companion object {
        private fun bakeModel(context: EntityRendererProvider.Context): AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity>> {
            // Assume you have two ModelLayerLocations defined somewhere:
            //   SIKA_DEER_ADULT_LAYER  and  SIKA_DEER_BABY_LAYER
            val adult =
                SikaDeerModel<SikaDeerEntity>(context.bakeLayer(ModModelLayers.SIKA_DEER))
            val baby =
                SikaDeerModel<SikaDeerEntity>(context.bakeLayer(ModModelLayers.SIKA_DEER_BABY))

            return AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity>>(adult, baby)
        }
    }
}
