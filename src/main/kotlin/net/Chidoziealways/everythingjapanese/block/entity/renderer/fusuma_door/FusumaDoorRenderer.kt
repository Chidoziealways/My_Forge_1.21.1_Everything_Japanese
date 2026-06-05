package net.Chidoziealways.everythingjapanese.block.entity.renderer.fusuma_door

import com.geckolib.renderer.GeoBlockRenderer
import com.mojang.blaze3d.vertex.PoseStack
import net.Chidoziealways.everythingjapanese.block.entity.custom.FusumaDoorBlockEntity
import net.Chidoziealways.everythingjapanese.block.model.FusumaDoorModel
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState

class FusumaDoorRenderer(ctx: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<FusumaDoorBlockEntity, FusumaDoorRenderState>(ctx, FusumaDoorModel()) {
}