package net.Chidoziealways.everythingjapanese.custom

import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionResult
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult

class PyriteLampBlock(properties: BlockBehaviour.Properties) : Block(properties) {
    init {
        this.registerDefaultState(
            this.defaultBlockState()
                .setValue<Boolean, Boolean?>(CLICKED, false)
        )
    }

    override fun useWithoutItem(
        pState: BlockState, pLevel: net.minecraft.world.level.Level, pPos: BlockPos,
        pPlayer: net.minecraft.world.entity.player.Player, pHitResult: BlockHitResult
    ): InteractionResult {
        if (!pLevel.isClientSide()) {
            val currentState: Boolean = pState.getValue<Boolean>(CLICKED)
            pLevel.setBlockAndUpdate(
                pPos,
                pState.setValue<Boolean, Boolean?>(CLICKED, !currentState)
            )
        }

        return InteractionResult.SUCCESS
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block?, BlockState?>) {
        pBuilder.add(CLICKED)
    }

    companion object {
        val CLICKED: BooleanProperty = ModBlockStateProperties.CLICKED
    }
}
