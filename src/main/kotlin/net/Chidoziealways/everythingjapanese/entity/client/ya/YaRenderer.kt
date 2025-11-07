package net.Chidoziealways.everythingjapanese.entity.client.ya

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.YaProjectileEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth

class YaRenderer(pContext: EntityRendererProvider.Context) :
    ArrowRenderer<YaProjectileEntity, YaRenderState>(pContext) {

    override fun getTextureLocation(renderState: YaRenderState): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/entity/ya/ya.png")
    }

    override fun createRenderState(): YaRenderState {
        return YaRenderState()
    }
}