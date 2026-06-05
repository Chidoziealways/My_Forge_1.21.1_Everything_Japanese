package net.Chidoziealways.everythingjapanese.block.entity.renderer.shoji_door

import com.geckolib.renderer.GeoBlockRenderer
import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.Chidoziealways.everythingjapanese.block.model.ShojiDoorModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

class ShojiDoorRenderer(ctx: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<ShojiDoorBlockEntity, ShojiDoorRenderState>(ctx, ShojiDoorModel()) {
}