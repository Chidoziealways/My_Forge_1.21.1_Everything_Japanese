package net.Chidoziealways.everythingjapanese.block.entity.renderer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.LightLayer
import net.minecraft.world.phys.Vec3

class PedestalBlockEntityRenderer(context: BlockEntityRendererProvider.Context?) :
    BlockEntityRenderer<PedestalBlockEntity?> {

    override fun render(
        pBlockEntity: PedestalBlockEntity?,
        pPartialTick: Float,
        pPoseStack: PoseStack,
        pBufferSource: MultiBufferSource,
        pPackedOverlay: Int,
        p_112312_: Int,
        cameraPos: Vec3
    ) {
        val itemRenderer = Minecraft.getInstance().getItemRenderer()
        val stack: ItemStack = pBlockEntity!!.inventory.getStackInSlot(0);

        pPoseStack.pushPose()
        pPoseStack.translate(0.5f, 1.15f, 0.5f)
        pPoseStack.mulPose(Axis.YP.rotationDegrees(pBlockEntity.renderingRotation))

        itemRenderer.renderStatic(
            stack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel()!!, pBlockEntity.blockPos),
            OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(), 1
        )
        pPoseStack.popPose()
    }


    private fun getLightLevel(level: Level, pos: BlockPos): Int {
        val bLight = level.getBrightness(LightLayer.BLOCK, pos)
        val sLight = level.getBrightness(LightLayer.SKY, pos)
        return LightTexture.pack(bLight, sLight)
    }
}
