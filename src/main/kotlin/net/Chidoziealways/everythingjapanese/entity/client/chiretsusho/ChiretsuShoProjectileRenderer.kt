package net.Chidoziealways.everythingjapanese.entity.client.chiretsusho

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.block.BlockRenderDispatcher
import net.minecraft.client.renderer.chunk.ChunkSectionLayer
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.EmptyBlockAndTintGetter
import net.Chidoziealways.everythingjapanese.entity.custom.ChiretsuShōProjectileEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.joml.Quaternionf

class ChiretsuShoProjectileRenderer(context: EntityRendererProvider.Context):
EntityRenderer<ChiretsuShōProjectileEntity, ChiretsuShoProjectileRenderState>(context){

    val minecraft = Minecraft.getInstance()

    private val blockRendererDispatcher: BlockRenderDispatcher = minecraft.blockRenderer
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
        val level = entity.level() ?: minecraft.level ?: EmptyBlockAndTintGetter.INSTANCE
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
            val beRenderState = blockEntityRendererDispatcher.tryExtractRenderState<BlockEntity, BlockEntityRenderState>(blockEntity, renderState.ageInTicks, null)
            if (beRenderState != null) {
                blockEntityRendererDispatcher.submit(beRenderState, poseStack, collector, cameraRenderState)
            }
        } else {
            ChunkSectionLayer.values().forEach { layer ->
                val renderType = when (layer) {
                    ChunkSectionLayer.SOLID -> RenderType.solid()
                    ChunkSectionLayer.CUTOUT, ChunkSectionLayer.CUTOUT_MIPPED -> RenderType.cutout()
                    ChunkSectionLayer.TRANSLUCENT -> RenderType.translucentMovingBlock()
                    ChunkSectionLayer.TRIPWIRE -> RenderType.cutout()
                }
                collector.submitBlockModel(
                    poseStack,
                    renderType,
                    blockRendererDispatcher.getBlockModel(blockState),
                    1f, 1f, 1f, // RGB multiplier
                    renderState.lightCoords,
                    0, // overlay
                    0 // outline
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