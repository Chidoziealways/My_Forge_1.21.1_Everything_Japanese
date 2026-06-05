package net.Chidoziealways.everythingjapanese.block.model

import com.geckolib.model.GeoModel
import com.geckolib.renderer.base.GeoRenderState
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.minecraft.resources.Identifier

class ShojiDoorModel: GeoModel<ShojiDoorBlockEntity>() {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_door/shoji_door")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/block/shoji_door.png")
    }

    override fun getAnimationResource(animatable: ShojiDoorBlockEntity): Identifier {
        return Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_door/shoji_door")
    }
}