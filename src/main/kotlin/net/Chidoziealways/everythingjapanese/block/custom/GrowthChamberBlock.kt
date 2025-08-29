package net.Chidoziealways.everythingjapanese.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.entity.custom.GrowthChamberBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class GrowthChamberBlock(pProperties: BlockBehaviour.Properties) : BaseEntityBlock(pProperties) {
    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return GrowthChamberBlock.Companion.CODEC
    }

    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity? {
        return GrowthChamberBlockEntity(blockPos, blockState)
    }

    override fun getRenderShape(pState: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    override fun playerWillDestroy(
        pLevel: net.minecraft.world.level.Level,
        pPos: BlockPos,
        pState: BlockState,
        pPlayer: net.minecraft.world.entity.player.Player
    ): BlockState {
        // Check if the block is actually being replaced (like on block break)
        if (pState.getBlock() !== pLevel.getBlockState(pPos).getBlock()) {
            // Get the block entity at the position and check if it's an instance of your GrowthChamberBlockEntity
            val blockEntity: BlockEntity? = pLevel.getBlockEntity(pPos)
            if (blockEntity is GrowthChamberBlockEntity) {
                blockEntity.preRemoveSideEffects(pPos, pState) // Call drops() method on the GrowthChamberBlockEntity
            }
        }
        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer)
    }

    override fun useItemOn(
        pStack: ItemStack,
        pState: BlockState,
        pLevel: net.minecraft.world.level.Level,
        pPos: BlockPos,
        pPlayer: net.minecraft.world.entity.player.Player,
        pHand: InteractionHand,
        pHitResult: BlockHitResult
    ): InteractionResult {
        if (!pLevel.isClientSide()) {
            val entity: BlockEntity? = pLevel.getBlockEntity(pPos)
            if (entity is GrowthChamberBlockEntity) {
                (pPlayer as ServerPlayer).openMenu(
                    SimpleMenuProvider(
                        entity,
                        Component.literal("Growth Chamber")
                    ), pPos
                )
            } else {
                throw java.lang.IllegalStateException("Our Container provider is missing!")
            }
        }

        return InteractionResult.SUCCESS
    }


    override fun <T : BlockEntity?> getTicker(
        pLevel: Level,
        pState: BlockState,
        pBlockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if (pLevel.isClientSide) {
            null
        } else {
            BaseEntityBlock.createTickerHelper<GrowthChamberBlockEntity, T>(
                pBlockEntityType,
                ModBlockEntities.GROWTH_CHAMBER_BE
            ) { level, pos, state, blockEntity ->
                (blockEntity as GrowthChamberBlockEntity).tick(level, pos, state)
            }
        }
    }

    companion object {
        val CODEC: MapCodec<GrowthChamberBlock?> =
            BlockBehaviour.simpleCodec<GrowthChamberBlock?>(java.util.function.Function { pProperties: BlockBehaviour.Properties ->
                GrowthChamberBlock(pProperties)
            })
    }
}
