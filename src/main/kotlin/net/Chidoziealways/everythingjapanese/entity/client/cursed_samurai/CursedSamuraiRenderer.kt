package net.Chidoziealways.everythingjapanese.entity.client.cursed_samurai

import net.Chidoziealways.everythingjapanese.entity.custom.CursedSamurai
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.renderer.GeoEntityRenderer

class CursedSamuraiRenderer(val context: EntityRendererProvider.Context): GeoEntityRenderer<CursedSamurai, CursedSamuraiRenderState>(context,
    CursedSamuraiModel()) {
}