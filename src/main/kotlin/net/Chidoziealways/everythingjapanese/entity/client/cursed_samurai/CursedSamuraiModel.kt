package net.Chidoziealways.everythingjapanese.entity.client.cursed_samurai

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.CursedSamurai
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.animatable.processing.AnimationState
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class CursedSamuraiModel: GeoModel<CursedSamurai>() {
    override fun getModelResource(renderState: GeoRenderState?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "cursed_samurai/cursed_samurai")
    }

    override fun getTextureResource(renderState: GeoRenderState?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/entity/cursed_samurai/cursed_samurai.png")
    }

    override fun getAnimationResource(animatable: CursedSamurai?): ResourceLocation? {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "cursed_samurai/cursed_samurai")
    }
}