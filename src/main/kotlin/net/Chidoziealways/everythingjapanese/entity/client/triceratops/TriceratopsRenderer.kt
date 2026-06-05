package net.Chidoziealways.everythingjapanese.entity.client.triceratops

import com.mojang.blaze3d.vertex.PoseStack
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.TriceratopsVariant
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers
import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity
import net.minecraft.client.model.AdultAndBabyModelPair
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.resources.Identifier
import java.util.*

class TriceratopsRenderer(pContext: EntityRendererProvider.Context) :
    MobRenderer<TriceratopsEntity, TriceratopsRenderState, TriceratopsModel<TriceratopsEntity>>(
        pContext,
        TriceratopsModel<TriceratopsEntity>(pContext.bakeLayer(ModModelLayers.TRICERATOPS)),
        0.86f
    ) {
    private val model: AdultAndBabyModelPair<TriceratopsModel<TriceratopsEntity>>


    init {
        this.model = bakeModel(pContext)
    }

    override fun getTextureLocation(state: TriceratopsRenderState): Identifier {
        return LOCATION_BY_VARIANT[state.entity!!.variant]!!
    }

    override fun submit(
        pEntity: TriceratopsRenderState, pPoseStack: PoseStack,
        collector: SubmitNodeCollector, cameraRenderState: CameraRenderState
    ) {
        if (pEntity.isBaby) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f)
        } else {
            pPoseStack.scale(1f, 1f, 1f)
        }

        super.submit(pEntity, pPoseStack, collector, cameraRenderState)
    }

    override fun createRenderState(): TriceratopsRenderState {
        return TriceratopsRenderState()
    }

    override fun extractRenderState(p_368665_: TriceratopsEntity, p_363057_: TriceratopsRenderState, p_364497_: Float) {
        super.extractRenderState(p_368665_, p_363057_, p_364497_)
        p_363057_.entity = p_368665_
        p_363057_.idleAnimationState.copyFrom(p_368665_.idleAnimationState)
    }

    companion object {
        private val LOCATION_BY_VARIANT: Map<TriceratopsVariant, Identifier> by lazy {
            EnumMap<TriceratopsVariant, Identifier>(TriceratopsVariant::class.java).apply {
                put(
                    TriceratopsVariant.GRAY,
                    Identifier.fromNamespaceAndPath(
                        JAPANESE_MOD_ID,
                        "textures/entity/triceratops/triceratops_gray.png"
                    )
                )
                put(
                    TriceratopsVariant.GREEN,
                    Identifier.fromNamespaceAndPath(
                        JAPANESE_MOD_ID,
                        "textures/entity/triceratops/triceratops_green.png"
                    )
                )
            }
        }

        private fun bakeModel(context: EntityRendererProvider.Context): AdultAndBabyModelPair<TriceratopsModel<TriceratopsEntity>> {
            val adult =
                TriceratopsModel<TriceratopsEntity>(context.bakeLayer(ModModelLayers.TRICERATOPS))
            val baby =
                TriceratopsModel<TriceratopsEntity>(context.bakeLayer(ModModelLayers.TRICERATOPS_BABY))
            return AdultAndBabyModelPair<TriceratopsModel<TriceratopsEntity>>(adult, baby)
        }
    }
}
