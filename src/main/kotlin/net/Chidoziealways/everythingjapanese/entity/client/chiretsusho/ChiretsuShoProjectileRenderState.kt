package net.Chidoziealways.everythingjapanese.entity.client.chiretsusho

import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.EntityRenderState
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class ChiretsuShoProjectileRenderState: EntityRenderState() {
    var entity: Entity? = null
    var blockState: BlockState? = null
    var blockEntityCopy: BlockEntity? = null
}
