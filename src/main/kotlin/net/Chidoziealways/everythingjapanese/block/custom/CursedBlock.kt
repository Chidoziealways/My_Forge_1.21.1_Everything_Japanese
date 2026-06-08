package net.Chidoziealways.everythingjapanese.block.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.block.entity.custom.CursedBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import java.util.function.Function

class CursedBlock(properties: Properties) : BaseEntityBlock(properties) {
    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity? {
        return CursedBlockEntity(blockPos, blockState)
    }

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    override fun playerWillDestroy(
        pLevel: Level,
        pPos: BlockPos,
        pState: BlockState,
        pPlayer: Player
    ): BlockState {
        // Check if the block is actually being replaced (like on block break)
        if (pState.getBlock() !== pLevel.getBlockState(pPos).block) {
            // Get the block entity at the position and check if it's an instance of your GrowthChamberBlockEntity
            val blockEntity: BlockEntity? = pLevel.getBlockEntity(pPos)
            if (blockEntity is CursedBlockEntity) {
                blockEntity.preRemoveSideEffects(pPos, pState) // Call drops() method on the GrowthChamberBlockEntity
            }
        }
        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer)
    }


    override fun useItemOn(
        itemStack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide) {
            val entity = level.getBlockEntity(pos)
            if (entity !is CursedBlockEntity)
                throw IllegalStateException("The Block Entity isn't a CursedBlockEntity?!")

            (player as ServerPlayer).openMenu(
                SimpleMenuProvider(
                    entity,
                    Component.literal("Cursed Block")
                ),
                pos
            )
        }

        return InteractionResult.SUCCESS
    }

    companion object {
        var CODEC: MapCodec<CursedBlock> =
            BlockBehaviour.simpleCodec<CursedBlock>(Function { properties: Properties? -> CursedBlock(properties!!) })
    }
}