package net.Chidoziealways.everythingjapanese.block.entity.renderer.hanging_scroll

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.HangingScrollBlock
import net.Chidoziealways.everythingjapanese.block.entity.custom.HangingScrollBlockEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.feature.ModelFeatureRenderer
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.resources.Identifier
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

    fun material(texture: Identifier) = RenderTypes.entityCutout(texture)

    fun render(vertexConsumer: VertexConsumer, pose: PoseStack.Pose, from: FloatArray, to: FloatArray) {
        val x0 = from[0] / 16f - 0.5f
        val y0 = from[1] / 16f - 0.5f
        val z0 = from[2] / 16f - 0.5f
        val x1 = to[0] / 16f - 0.5f
        val y1 = to[1] / 16f - 0.5f
        val z1 = to[2] / 16f - 0.5f
        val epsilon = 0.001f

        // Define UVs (simple full-face mapping; you can adapt to Blockbench UVs)
        val uvsFront = arrayOf(
            floatArrayOf(0f, 1f),
            floatArrayOf(1f, 1f),
            floatArrayOf(1f, 0f),
            floatArrayOf(0f, 0f)
        )
        val uvsBack = arrayOf(
            floatArrayOf(0f, 0f),
            floatArrayOf(1f, 0f),
            floatArrayOf(1f, 1f),
            floatArrayOf(0f, 1f)
        )

        fun vertex(x: Float, y: Float, z: Float, u: Float, v: Float, nx: Float, ny: Float, nz: Float) {
            vertexConsumer.addVertex(pose.pose(), x, y, z)
                .setColor(255, 255, 255, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(0xf0, 0xf0)
                .setNormal(nx, ny, nz)
        }

        // Front face (+Z)
        vertex(x0, y0, z1 + epsilon, uvsFront[0][0], uvsFront[0][1], 0f, 0f, 1f)
        vertex(x1, y0, z1 + epsilon, uvsFront[1][0], uvsFront[1][1], 0f, 0f, 1f)
        vertex(x1, y1, z1 + epsilon, uvsFront[2][0], uvsFront[2][1], 0f, 0f, 1f)
        vertex(x0, y1, z1 + epsilon, uvsFront[3][0], uvsFront[3][1], 0f, 0f, 1f)

        // Back face (-Z)
        vertex(x0, y0, z0 - epsilon, uvsBack[0][0], uvsBack[0][1], 0f, 0f, -1f)
        vertex(x1, y0, z0 - epsilon, uvsBack[1][0], uvsBack[1][1], 0f, 0f, -1f)
        vertex(x1, y1, z0 - epsilon, uvsBack[2][0], uvsBack[2][1], 0f, 0f, -1f)
        vertex(x0, y1, z0 - epsilon, uvsBack[3][0], uvsBack[3][1], 0f, 0f, -1f)
    }
}