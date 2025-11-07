package net.Chidoziealways.everythingjapanese.entity.client.ekiretsusho

import net.minecraft.client.renderer.entity.state.EntityRenderState
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.material.FluidState

class EkiretsuShoProjectileRenderState: EntityRenderState() {
    var entity: Entity? = null
    var fluidState: FluidState? = null

    var blockPos: BlockPos? = null
}
