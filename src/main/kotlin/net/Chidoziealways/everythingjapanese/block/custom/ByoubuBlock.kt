/*package net.Chidoziealways.everythingjapanese.block.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.block.entity.custom.ByoubuBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty

class ByoubuBlock(properties: Properties): BaseEntityBlock(properties) {
    init {
        registerDefaultState(
            this.stateDefinition
                .any()
                .setValue(OPEN, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(OPEN)
    }

    companion object {
        val OPEN: BooleanProperty = BlockStateProperties.OPEN
    }

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        TODO("Not yet implemented")
    }

    override fun newBlockEntity(
        pos: BlockPos,
        state: BlockState
    ): BlockEntity {
        return ByoubuBlockEntity(pos, state)
    }
}
 */