package net.Chidoziealways.everythingjapanese.block.entity.renderer.pedestal

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.feature.ModelFeatureRenderer
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.LightLayer
import net.minecraft.world.phys.Vec3
import kotlin.random.Random

class PedestalBlockEntityRenderer(context: BlockEntityRendererProvider.Context?) :
    BlockEntityRenderer<PedestalBlockEntity, PedestalBlockEntityRenderState> {
    private fun getLightLevel(level: Level, pos: BlockPos): Int {
        val bLight = level.getBrightness(LightLayer.BLOCK, pos)
        val sLight = level.getBrightness(LightLayer.SKY, pos)
        return LightTexture.pack(bLight, sLight)
    }

    override fun createRenderState(): PedestalBlockEntityRenderState {
        return PedestalBlockEntityRenderState()
    }

    override fun extractRenderState(
        entity: PedestalBlockEntity,
        renderState: PedestalBlockEntityRenderState,
        p_446851_: Float,
        p_445788_: Vec3,
        p_446944_: ModelFeatureRenderer.CrumblingOverlay?
    ) {
        renderState.blockEntity = entity
        renderState.itemStack = entity.inventory.getStackInSlot(0)
        renderState.renderingRotation = entity.renderingRotation
        renderState.itemStackRenderState = ItemStackRenderState()

        super.extractRenderState(entity, renderState, p_446851_, p_445788_, p_446944_)
    }

    override fun submit(
        state: PedestalBlockEntityRenderState,
        poseStack: PoseStack,
        collector: SubmitNodeCollector,
        camera: CameraRenderState
    ) {
        val pBlockEntity = state.blockEntity!!
        val stack = state.itemStack!!
        if (stack.isEmpty) return

        poseStack.pushPose()
        poseStack.translate(0.5, 1.15, 0.5)
        poseStack.mulPose(Axis.YP.rotationDegrees(state.renderingRotation))

        // Use ItemStackRenderState directly
        state.itemStackRenderState!!.submit(
            poseStack,
            collector,
            getLightLevel(pBlockEntity.getLevel()!!, pBlockEntity.blockPos),
            OverlayTexture.NO_OVERLAY,
            0 // outline color
        )

        poseStack.popPose()
    }

}