package net.Chidoziealways.everythingjapanese.block.model

import com.geckolib.model.GeoModel
import com.geckolib.renderer.base.GeoRenderState
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.entity.custom.FusumaDoorBlockEntity
import net.minecraft.resources.Identifier

class FusumaDoorModel: GeoModel<FusumaDoorBlockEntity>() {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "fusuma/fusuma")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/block/fusuma.png")
    }

    override fun getAnimationResource(animatable: FusumaDoorBlockEntity): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "fusuma/fusuma")
    }
}