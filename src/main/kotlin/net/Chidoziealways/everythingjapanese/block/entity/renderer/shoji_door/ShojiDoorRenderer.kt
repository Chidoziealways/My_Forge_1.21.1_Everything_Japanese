package net.Chidoziealways.everythingjapanese.block.entity.renderer.shoji_door

import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.Chidoziealways.everythingjapanese.block.model.ShojiDoorModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import software.bernie.geckolib.renderer.GeoBlockRenderer

class ShojiDoorRenderer(ctx: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<ShojiDoorBlockEntity, ShojiDoorRenderState>(ShojiDoorModel()) {
}