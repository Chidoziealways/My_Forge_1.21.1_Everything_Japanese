package net.Chidoziealways.everythingjapanese.entity.client.sikadeer

import com.mojang.blaze3d.vertex.PoseStack
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity
import net.minecraft.client.model.AdultAndBabyModelPair
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation

class SikaDeerRenderer(pContext: EntityRendererProvider.Context) :
    MobRenderer<SikaDeerEntity, SikaDeerRenderState, SikaDeerModel<SikaDeerEntity?>?>(
        pContext,
        SikaDeerModel<SikaDeerEntity?>(pContext.bakeLayer(ModModelLayers.SIKA_DEER)),
        0.86f
    ) {
    private val models: AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity?>?>

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

    override fun getTextureLocation(pEntity: SikaDeerRenderState): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(
            MOD_ID,
            "textures/entity/sikadeer/sika_deer.png"
        )
    }

    override fun render(
        sikaDeerRenderState: SikaDeerRenderState,
        poseStack: PoseStack,
        multiBufferSource: MultiBufferSource,
        p_115313_: Int
    ) {
        if (sikaDeerRenderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f)
            this.shadowRadius = 0.35f
        } else {
            poseStack.scale(1.0f, 1.0f, 1.0f)
            this.shadowRadius = 0.7f
        }
        super.render(sikaDeerRenderState, poseStack, multiBufferSource, p_115313_)
    }

    companion object {
        private fun bakeModel(context: EntityRendererProvider.Context): AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity?>?> {
            // Assume you have two ModelLayerLocations defined somewhere:
            //   SIKA_DEER_ADULT_LAYER  and  SIKA_DEER_BABY_LAYER
            val adult =
                SikaDeerModel<SikaDeerEntity?>(context.bakeLayer(ModModelLayers.SIKA_DEER))
            val baby =
                SikaDeerModel<SikaDeerEntity?>(context.bakeLayer(ModModelLayers.SIKA_DEER_BABY))

            return AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity?>?>(adult, baby)
        }
    }
}
