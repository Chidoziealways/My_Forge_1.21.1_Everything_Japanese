package net.Chidoziealways.everythingjapanese.entity.client.bullet

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.BulletEntity
import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.Identifier

class BulletRenderer(context: EntityRendererProvider.Context): ArrowRenderer<BulletEntity, BulletRenderState>(context) {
    override fun getTextureLocation(p0: BulletRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            JAPANESE_MOD_ID,
            "textures/entity/bullet/bullet.png"
        )
    }

    override fun createRenderState(): BulletRenderState = BulletRenderState()
}