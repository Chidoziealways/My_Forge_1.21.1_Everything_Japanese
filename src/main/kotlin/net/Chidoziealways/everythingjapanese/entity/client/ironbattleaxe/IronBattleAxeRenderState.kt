package net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe

import net.Chidoziealways.everythingjapanese.entity.custom.IronBattleAxeProjectileEntity
import net.minecraft.client.renderer.entity.state.EntityRenderState
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class IronBattleAxeRenderState : EntityRenderState() {
    var xRot: Float = 0f
    var yRot: Float = 0f
    var shake: Float = 0f
    private var entity: IronBattleAxeProjectileEntity? = null
    private val pPartialTick = 0f

    fun getEntity(): IronBattleAxeProjectileEntity? {
        return entity
    }

    fun getpPartialTick(): Float {
        return pPartialTick
    }

    fun setEntity(entity: IronBattleAxeProjectileEntity?) {
        this.entity = entity
    }
}
