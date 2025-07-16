package net.Chidoziealways.everythingjapanese.entity.client.ya

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.YaProjectileEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class YaRenderer(pContext: EntityRendererProvider.Context) :
    EntityRenderer<YaProjectileEntity, YaRenderState>(pContext) {
    override fun render(state: YaRenderState, pPoseStack: PoseStack, pBuffer: MultiBufferSource, pPackedLight: Int) {
        pPoseStack.pushPose()

        val texture = determineTexture(state.getEntity())

        pPoseStack.mulPose(
            Axis.YP.rotationDegrees(
                Mth.lerp(
                    state.getPartialTicks(),
                    state.getEntity()!!.yRotO,
                    state.getEntity()!!.getYRot()
                ) - 90.0f
            )
        )
        pPoseStack.mulPose(
            Axis.ZP.rotationDegrees(
                Mth.lerp(
                    state.getPartialTicks(),
                    state.getEntity()!!.xRotO,
                    state.getEntity()!!.getXRot()
                )
            )
        )
        val i = 0
        val f = 0.0f
        val f1 = 0.5f
        val f2 = 0.0f
        val f3 = 0.15625f
        val f4 = 0.0f
        val f5 = 0.15625f
        val f6 = 0.15625f
        val f7 = 0.3125f
        val f8 = 0.05625f
        val f9 = state.getEntity()!!.shakeTime.toFloat() - state.getPartialTicks()
        if (f9 > 0.0f) {
            val f10 = -Mth.sin(f9 * 3.0f) * f9
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(f10))
        }

        pPoseStack.mulPose(Axis.XP.rotationDegrees(45.0f))
        pPoseStack.scale(0.05625f, 0.05625f, 0.05625f)
        pPoseStack.translate(-4.0f, 0.0f, 0.0f)
        val vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(texture))
        val `posestack$pose` = pPoseStack.last()
        this.vertex(`posestack$pose`, vertexconsumer, -7, -2, -2, 0.0f, 0.15625f, -1, 0, 0, pPackedLight)
        this.vertex(`posestack$pose`, vertexconsumer, -7, -2, 2, 0.15625f, 0.15625f, -1, 0, 0, pPackedLight)
        this.vertex(`posestack$pose`, vertexconsumer, -7, 2, 2, 0.15625f, 0.3125f, -1, 0, 0, pPackedLight)
        this.vertex(`posestack$pose`, vertexconsumer, -7, 2, -2, 0.0f, 0.3125f, -1, 0, 0, pPackedLight)
        this.vertex(`posestack$pose`, vertexconsumer, -7, 2, -2, 0.0f, 0.15625f, 1, 0, 0, pPackedLight)
        this.vertex(`posestack$pose`, vertexconsumer, -7, 2, 2, 0.15625f, 0.15625f, 1, 0, 0, pPackedLight)
        this.vertex(`posestack$pose`, vertexconsumer, -7, -2, 2, 0.15625f, 0.3125f, 1, 0, 0, pPackedLight)
        this.vertex(`posestack$pose`, vertexconsumer, -7, -2, -2, 0.0f, 0.3125f, 1, 0, 0, pPackedLight)

        for (j in 0..3) {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0f))
            this.vertex(`posestack$pose`, vertexconsumer, -8, -2, 0, 0.0f, 0.0f, 0, 1, 0, pPackedLight)
            this.vertex(`posestack$pose`, vertexconsumer, 8, -2, 0, 0.5f, 0.0f, 0, 1, 0, pPackedLight)
            this.vertex(`posestack$pose`, vertexconsumer, 8, 2, 0, 0.5f, 0.15625f, 0, 1, 0, pPackedLight)
            this.vertex(`posestack$pose`, vertexconsumer, -8, 2, 0, 0.0f, 0.15625f, 0, 1, 0, pPackedLight)
        }

        pPoseStack.popPose()
        super.render(state, pPoseStack, pBuffer, pPackedLight)
    }

    override fun createRenderState(): YaRenderState {
        return YaRenderState()
    }

    override fun extractRenderState(pEntity: YaProjectileEntity, pReusedState: YaRenderState, pPartialTick: Float) {
        pReusedState.setEntity(pEntity)
        super.extractRenderState(pEntity, pReusedState, pPartialTick)
    }

    fun vertex(
        pPose: PoseStack.Pose,
        pConsumer: VertexConsumer,
        pX: Int,
        pY: Int,
        pZ: Int,
        pU: Float,
        pV: Float,
        pNormalX: Int,
        pNormalY: Int,
        pNormalZ: Int,
        pPackedLight: Int
    ) {
        pConsumer.addVertex(pPose, pX.toFloat(), pY.toFloat(), pZ.toFloat())
            .setColor(-1)
            .setUv(pU, pV)
            .setOverlay(OverlayTexture.NO_OVERLAY)
            .setLight(pPackedLight)
            .setNormal(pPose, pNormalX.toFloat(), pNormalZ.toFloat(), pNormalY.toFloat())
    }

    private fun determineTexture(entity: YaProjectileEntity?): ResourceLocation {
        // Logic to determine the appropriate texture based on the entity's state
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/entity/ya/ya.png")
    }
}