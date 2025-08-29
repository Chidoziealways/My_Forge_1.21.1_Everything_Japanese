package net.Chidoziealways.everythingjapanese.block.model

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.entity.custom.FusumaDoorBlockEntity
import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class FusumaDoorModel: GeoModel<FusumaDoorBlockEntity>() {
    override fun getModelResource(renderState: GeoRenderState?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "fusuma/fusuma")
    }

    override fun getTextureResource(renderState: GeoRenderState?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/block/fusuma.png")
    }

    override fun getAnimationResource(animatable: FusumaDoorBlockEntity?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "fusuma/fusuma")
    }
}