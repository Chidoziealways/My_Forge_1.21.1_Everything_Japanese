package net.Chidoziealways.everythingjapanese.entity.client.cursed_samurai

import com.geckolib.renderer.GeoEntityRenderer
import net.Chidoziealways.everythingjapanese.entity.custom.CursedSamurai
import net.minecraft.client.renderer.entity.EntityRendererProvider

class CursedSamuraiRenderer(val context: EntityRendererProvider.Context): GeoEntityRenderer<CursedSamurai, CursedSamuraiRenderState>(context,
    CursedSamuraiModel()) {
}