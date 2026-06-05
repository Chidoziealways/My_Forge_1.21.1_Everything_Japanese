package net.Chidoziealways.everythingjapanese.entity.client.cursed_samurai

import com.geckolib.model.GeoModel
import com.geckolib.renderer.base.GeoRenderState
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.CursedSamurai
import net.minecraft.resources.Identifier

class CursedSamuraiModel: GeoModel<CursedSamurai>() {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "cursed_samurai/cursed_samurai")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/entity/cursed_samurai/cursed_samurai.png")
    }

    override fun getAnimationResource(animatable: CursedSamurai): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "cursed_samurai/cursed_samurai")
    }
}