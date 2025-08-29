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
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.joml.Quaternionf

class ChiretsuShoProjectileRenderer(context: EntityRendererProvider.Context):
EntityRenderer<ChiretsuShōProjectileEntity, ChiretsuShoProjectileRenderState>(context){

    val minecraft = Minecraft.getInstance()

    private val blockRendererDispatcher: BlockRenderDispatcher = minecraft.blockRenderer
    private val blockEntityRendererDispatcher: BlockEntityRenderDispatcher = minecraft.blockEntityRenderDispatcher

    override fun render(
        renderState: ChiretsuShoProjectileRenderState,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        val blockState = renderState.blockState ?: return
        val entity = renderState.entity ?: return
        val blockEntity = renderState.blockEntityCopy
        val level = entity.level() ?: minecraft.level ?: EmptyBlockAndTintGetter.INSTANCE
        val blockPos = entity.blockPosition()

        poseStack.pushPose()

        // Position & rotation
        val yRotRad = Math.toRadians(entity.yRot.toDouble())
        val xRotRad = Math.toRadians(entity.xRot.toDouble())
        val rotation = Quaternionf().apply {
            rotateY(yRotRad.toFloat())
            rotateX(-xRotRad.toFloat())
        }
        poseStack.mulPose(rotation)
        poseStack.scale(1f, 1f, 1f)

        if (blockEntity != null) {
            // Create a temporary BlockEntity from the BlockState for rendering
            // Render the block entity with the dispatcher
            blockEntityRendererDispatcher.render(blockEntity, 0f, poseStack, bufferSource)
        } else {
            // Normal block rendering
            val blockModel = blockRendererDispatcher.getBlockModel(blockState)
            val parts = blockModel.collectParts(level, blockPos, blockState, RandomSource.create(42L))
            val bufferLookup: (ChunkSectionLayer) -> VertexConsumer = { layer ->
                when (layer) {
                    ChunkSectionLayer.SOLID -> bufferSource.getBuffer(RenderType.solid())
                    ChunkSectionLayer.CUTOUT -> bufferSource.getBuffer(RenderType.cutout())
                    ChunkSectionLayer.TRANSLUCENT -> bufferSource.getBuffer(RenderType.translucentMovingBlock())
                    else -> bufferSource.getBuffer(RenderType.solid())
                }
            }
            blockRendererDispatcher.modelRenderer.tesselateBlock(
                level,
                parts,
                blockState,
                blockPos,
                poseStack,
                bufferLookup,
                true,
                packedLight
            )
        }

        poseStack.popPose()
        super.render(renderState, poseStack, bufferSource, packedLight)
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