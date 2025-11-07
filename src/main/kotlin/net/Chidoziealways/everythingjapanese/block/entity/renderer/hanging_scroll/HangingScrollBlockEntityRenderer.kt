package net.Chidoziealways.everythingjapanese.block.entity.renderer.hanging_scroll

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.HangingScrollBlock
import net.Chidoziealways.everythingjapanese.block.entity.custom.HangingScrollBlockEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.feature.ModelFeatureRenderer
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.phys.Vec3
import org.joml.Quaternionf

class HangingScrollBlockEntityRenderer(context: BlockEntityRendererProvider.Context): BlockEntityRenderer<HangingScrollBlockEntity, HangingScrollBlockEntityRenderState> {
    override fun createRenderState(): HangingScrollBlockEntityRenderState {
        return HangingScrollBlockEntityRenderState()
    }

    override fun extractRenderState(
        blockEntity: HangingScrollBlockEntity,
        renderState: HangingScrollBlockEntityRenderState,
        p_446851_: Float,
        p_445788_: Vec3,
        overlay: ModelFeatureRenderer.CrumblingOverlay?
    ) {
        renderState.currentDesign = blockEntity.currentDesign
        super.extractRenderState(blockEntity, renderState, p_446851_, p_445788_, overlay)
    }

    override fun submit(
        renderState: HangingScrollBlockEntityRenderState,
        poseStack: PoseStack,
        nodeCollector: SubmitNodeCollector,
        cameraRenderState: CameraRenderState
    ) {
        val design = renderState.currentDesign ?: return
        val texture = design.location

        poseStack.pushPose()
        poseStack.translate(0.5, 0.5, 0.5) // center the block

        val facing = renderState.blockState.getValue(HangingScrollBlock.FACING)


        // Apply facing rotation
        when (facing) {
            Direction.NORTH -> {} // identity
            Direction.SOUTH -> poseStack.mulPose(Quaternionf().rotateY(Math.toRadians(180.0).toFloat()))
            Direction.WEST  -> poseStack.mulPose(Quaternionf().rotateY(Math.toRadians(90.0).toFloat()))
            Direction.EAST  -> poseStack.mulPose(Quaternionf().rotateY(Math.toRadians(-90.0).toFloat()))
            Direction.UP    -> {
                val q = Quaternionf().rotateX(Math.toRadians(-90.0).toFloat())
                q.rotateY(Math.toRadians(180.0).toFloat())
                q.rotateZ(Math.toRadians(180.0).toFloat())
                poseStack.mulPose(q)
            }
            Direction.DOWN  -> {
                val q = Quaternionf().rotateX(Math.toRadians(90.0).toFloat())
                q.rotateY(Math.toRadians(180.0).toFloat())
                q.rotateZ(Math.toRadians(180.0).toFloat())
                poseStack.mulPose(q)
            }
        }

        // Paper element coordinates from Blockbench (6th child)
        val from = floatArrayOf(3f, 5f, 1f)
        val to   = floatArrayOf(13f, 15f, 2f)

        // Render using the model
        nodeCollector.submitCustomGeometry(poseStack, HangingScrollModel.material(texture)) { pose, vc ->
            HangingScrollModel.render(vc, pose, from, to)
        }

        poseStack.popPose()
    }
}

object HangingScrollModel {
    fun material(texture: ResourceLocation) = RenderType.entityCutoutNoCull(texture)

    fun render(vertexConsumer: VertexConsumer, pose: PoseStack.Pose, from: FloatArray, to: FloatArray) {
        val x0 = (from[0] / 16f) - 0.5f
        val y0 = (from[1] / 16f) - 0.5f
        val z0 = (from[2] / 16f) - 0.5f
        val x1 = (to[0] / 16f) - 0.5f
        val y1 = (to[1] / 16f) - 0.5f
        val z1 = (to[2] / 16f) - 0.5f
        val epsilon = 0.001f

        // Front face
        vertexConsumer.addVertex(pose.pose(), x0, y0, z1 + epsilon)
        vertexConsumer.addVertex(pose.pose(), x1, y0, z1 + epsilon)
        vertexConsumer.addVertex(pose.pose(), x1, y1, z1 + epsilon)
        vertexConsumer.addVertex(pose.pose(), x0, y1, z1 + epsilon)

        // Back face
        vertexConsumer.addVertex(pose.pose(), x0, y0, z0 - epsilon)
        vertexConsumer.addVertex(pose.pose(), x1, y0, z0 - epsilon)
        vertexConsumer.addVertex(pose.pose(), x1, y1, z0 - epsilon)
        vertexConsumer.addVertex(pose.pose(), x0, y1, z0 - epsilon)
    }
}
