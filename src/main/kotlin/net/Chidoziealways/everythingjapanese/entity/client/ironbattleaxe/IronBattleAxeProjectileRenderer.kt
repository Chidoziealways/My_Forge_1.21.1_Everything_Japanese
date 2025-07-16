package net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.IronBattleAxeProjectileEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth

class IronBattleAxeProjectileRenderer(pContext: EntityRendererProvider.Context) :
    EntityRenderer<IronBattleAxeProjectileEntity, IronBattleAxeRenderState>(pContext) {
    private val model: IronBattleAxeProjectileModel

    init {
        this.model =
            IronBattleAxeProjectileModel(pContext.bakeLayer(IronBattleAxeProjectileModel.Companion.LAYER_LOCATION))
    }

    override fun render(
        state: IronBattleAxeRenderState,
        pPoseStack: PoseStack,
        pBufferSource: MultiBufferSource,
        pPackedLight: Int
    ) {
        pPoseStack.pushPose()

        // Determine the texture based on the entity's state
        val texture = determineTexture(state.getEntity())

        // Apply transformations based on the entity's movement
        if (!state.getEntity()!!.isGrounded) {
            pPoseStack.mulPose(
                Axis.YP.rotationDegrees(
                    Mth.lerp(
                        state.getpPartialTick(),
                        state.getEntity()!!.yRotO,
                        state.getEntity()!!.getYRot()
                    )
                )
            )
            pPoseStack.mulPose(Axis.XP.rotationDegrees(state.getEntity()!!.renderingRotation * 5f))
            pPoseStack.translate(0f, -1.0f, 0f)
        } else {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(state.getEntity()!!.groundedOffset!!.y))
            pPoseStack.mulPose(Axis.XP.rotationDegrees(state.getEntity()!!.groundedOffset!!.x))
            pPoseStack.translate(0f, -1.0f, 0f)
        }

        // Render the model with the determined texture
        val vertexConsumer = ItemRenderer.getFoilBuffer(
            pBufferSource, this.model.renderType(texture), false, false
        )
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY)

        pPoseStack.popPose()

        super.render(state, pPoseStack, pBufferSource, pPackedLight)
    }

    private fun determineTexture(entity: IronBattleAxeProjectileEntity?): ResourceLocation {
        // Logic to determine the appropriate texture based on the entity's state
        return ResourceLocation.fromNamespaceAndPath(
            MOD_ID,
            "textures/entity/ironbattleaxe/iron_battle_axe.png"
        )
    }


    override fun createRenderState(): IronBattleAxeRenderState {
        return IronBattleAxeRenderState()
    }

    override fun extractRenderState(
        pEntity: IronBattleAxeProjectileEntity,
        pReusedState: IronBattleAxeRenderState,
        pPartialTick: Float
    ) {
        pReusedState.setEntity(pEntity)
        super.extractRenderState(pEntity, pReusedState, pPartialTick)
    }
}
