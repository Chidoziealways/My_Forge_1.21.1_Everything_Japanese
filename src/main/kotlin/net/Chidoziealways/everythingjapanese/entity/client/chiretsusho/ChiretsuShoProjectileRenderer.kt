package net.Chidoziealways.everythingjapanese.entity.client.chiretsusho

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.chunk.ChunkSectionLayer
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.util.RandomSource
import net.Chidoziealways.everythingjapanese.entity.custom.ChiretsuShōProjectileEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.world.level.block.entity.BlockEntity
import org.joml.Quaternionf

class ChiretsuShoProjectileRenderer(context: EntityRendererProvider.Context):
EntityRenderer<ChiretsuShōProjectileEntity, ChiretsuShoProjectileRenderState>(context){

    val minecraft = Minecraft.getInstance()

    private val modelManager = minecraft.modelManager;
    private val blockEntityRendererDispatcher: BlockEntityRenderDispatcher = minecraft.blockEntityRenderDispatcher

    override fun submit(
        renderState: ChiretsuShoProjectileRenderState,
        poseStack: PoseStack,
        collector: SubmitNodeCollector,
        cameraRenderState: CameraRenderState
    ) {
        super.submit(renderState, poseStack, collector, cameraRenderState)

        val blockState = renderState.blockState ?: return
        val entity = renderState.entity ?: return
        val blockEntity = renderState.blockEntityCopy
        val level = entity.level()
        val blockPos = entity.blockPosition()

        poseStack.pushPose()

        // Apply entity rotation
        val yRotRad = Math.toRadians(entity.yRot.toDouble()).toFloat()
        val xRotRad = Math.toRadians(entity.xRot.toDouble()).toFloat()
        val rotation = Quaternionf().apply {
            rotateY(yRotRad)
            rotateX(-xRotRad)
        }
        poseStack.mulPose(rotation)
        poseStack.scale(1f, 1f, 1f)

        if (blockEntity != null) {
            val beRenderState = blockEntityRendererDispatcher.tryExtractRenderState<BlockEntity, BlockEntityRenderState>(blockEntity, renderState.ageInTicks, null, true)
            if (beRenderState != null) {
                blockEntityRendererDispatcher.submit(beRenderState, poseStack, collector, cameraRenderState)
            }
        } else {
            ChunkSectionLayer.values().forEach { layer ->
                val renderType = when (layer) {
                    ChunkSectionLayer.SOLID -> RenderTypes.solidMovingBlock()
                    ChunkSectionLayer.CUTOUT -> RenderTypes.cutoutMovingBlock()
                    ChunkSectionLayer.TRANSLUCENT -> RenderTypes.translucentMovingBlock()
                }
                val model = modelManager.blockStateModelSet.get(blockState)

                val parts = mutableListOf<BlockStateModelPart>()

                model.collectParts(
                    RandomSource.create(),
                    parts
                )


                collector.submitBlockModel(
                    poseStack,
                    renderType,
                    parts,
                    intArrayOf(-1, -1),
                    renderState.lightCoords,
                    0, // overlay
                    1
                )
            }
        }

        poseStack.popPose()
    }


    override fun createRenderState(): ChiretsuShoProjectileRenderState {
        return ChiretsuShoProjectileRenderState()
    }

    override fun extractRenderState(
        entity: ChiretsuShōProjectileEntity,
        reusedState: ChiretsuShoProjectileRenderState,
        partialTick: Float
    ) {
        reusedState.entity = entity
        reusedState.blockState = entity.blockState
        reusedState.blockEntityCopy = entity.blockEntityCopy
        super.extractRenderState(entity, reusedState, partialTick)
    }
}