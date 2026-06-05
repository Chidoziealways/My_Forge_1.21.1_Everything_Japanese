package net.Chidoziealways.everythingjapanese.entity.client.ekiretsusho

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.chunk.ChunkSectionLayer
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.Chidoziealways.everythingjapanese.entity.custom.EkiretsuShōProjectileEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.block.BlockAndTintGetter
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.util.RandomSource
import org.joml.Quaternionf

class EkiretsuShoProjectileRenderer(context: EntityRendererProvider.Context):
EntityRenderer<EkiretsuShōProjectileEntity, EkiretsuShoProjectileRenderState>(context){

    val minecraft = Minecraft.getInstance()

    override fun submit(
        renderState: EkiretsuShoProjectileRenderState,
        poseStack: PoseStack,
        collector: SubmitNodeCollector,
        cameraRenderState: CameraRenderState
    ) {
        super.submit(renderState, poseStack, collector, cameraRenderState)

        val fluidState = renderState.fluidState ?: return
        val entity = renderState.entity ?: return

        poseStack.pushPose()

        poseStack.translate(
            entity.x - cameraRenderState.pos.x,
            entity.y - cameraRenderState.pos.y,
            entity.z - cameraRenderState.pos.z
        )


        // Apply entity rotation
        val yRotRad = Math.toRadians(entity.yRot.toDouble()).toFloat()
        val xRotRad = Math.toRadians(entity.xRot.toDouble()).toFloat()
        val rotation = Quaternionf().apply {
            rotateY(yRotRad)
            rotateX(-xRotRad)
        }
        poseStack.mulPose(rotation)
        poseStack.scale(1f, 1f, 1f)

        ChunkSectionLayer.values().forEach { _ ->
            val pos = renderState.blockPos!! // floors the x, y, z to nearest block
            val blockState = entity.level().getBlockState(pos)
            val model = minecraft.modelManager.blockStateModelSet.get(blockState)
            val parts = mutableListOf<BlockStateModelPart>()

            model.collectParts(RandomSource.create(), parts)
            collector.submitBlockModel(
                poseStack,
                RenderTypes.translucentMovingBlock(),
                parts,
                intArrayOf(-1, -1),
                renderState.lightCoords,
                0, // overlay
                1
            )
//            collector.submitCustomGeometry(poseStack, RenderTypes.translucentMovingBlock()
//            ) { _, vertexConsumer ->
//                val level = entity.level()
//
//
//                minecraft.blockRenderer.liquidBlockRenderer.tesselate(
//                    level,
//                    pos,
//                    vertexConsumer,
//                    blockState,
//                    fluidState
//                )
//            }
        }

        poseStack.popPose()
    }


    override fun createRenderState(): EkiretsuShoProjectileRenderState {
        return EkiretsuShoProjectileRenderState()
    }

    override fun extractRenderState(
        entity: EkiretsuShōProjectileEntity,
        reusedState: EkiretsuShoProjectileRenderState,
        partialTick: Float
    ) {
        reusedState.entity = entity
        reusedState.fluidState = entity.fluidState
        reusedState.blockPos = entity.blockPos
        super.extractRenderState(entity, reusedState, partialTick)
    }
}