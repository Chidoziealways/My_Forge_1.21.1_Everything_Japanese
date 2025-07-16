package net.Chidoziealways.everythingjapanese.entity.client.ya

import net.Chidoziealways.everythingjapanese.entity.custom.YaProjectileEntity
import net.minecraft.client.renderer.entity.state.EntityRenderState

class YaRenderState : EntityRenderState() {
    private var partialTicks = 0f
    private var entity: YaProjectileEntity? = null

    fun getPartialTicks(): Float {
        return partialTicks
    }

    fun setPartialTicks(partialTicks: Float) {
        this.partialTicks = partialTicks
    }

    fun getEntity(): YaProjectileEntity? {
        return entity
    }

    fun setEntity(entity: YaProjectileEntity?) {
        this.entity = entity
    }
}
