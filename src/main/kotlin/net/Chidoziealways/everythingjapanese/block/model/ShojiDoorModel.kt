package net.Chidoziealways.everythingjapanese.block.model

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class ShojiDoorModel: GeoModel<ShojiDoorBlockEntity>() {
    override fun getModelResource(renderState: GeoRenderState?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_door/shoji_door")
    }

    override fun getTextureResource(renderState: GeoRenderState?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/block/shoji_door.png")
    }

    override fun getAnimationResource(animatable: ShojiDoorBlockEntity?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_door/shoji_door")
    }
}