package net.Chidoziealways.everythingjapanese.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class PedestalBlock(pProperties: BlockBehaviour.Properties) : BaseEntityBlock(pProperties) {
    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    override fun getRenderShape(pState: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity? {
        return PedestalBlockEntity(blockPos, blockState)
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
            if (blockEntity is PedestalBlockEntity) {
                blockEntity.preRemoveSideEffects(pPos, pState) // Call drops() method on the GrowthChamberBlockEntity
            }
        }
        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer)
    }

    override fun useItemOn(
        pStack: ItemStack,
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHitResult: BlockHitResult
    ): InteractionResult {
        if (pLevel.getBlockEntity(pPos) is PedestalBlockEntity) {
            val pedestalBlockEntity: PedestalBlockEntity = pLevel.getBlockEntity(pPos) as PedestalBlockEntity
            if (pPlayer.isCrouching && !pLevel.isClientSide()) {
                (pPlayer as ServerPlayer).openMenu(
                    SimpleMenuProvider(
                        pedestalBlockEntity,
                        Component.literal("Pedestal")
                    ), pPos
                )
                return InteractionResult.SUCCESS
            }

            if (pPlayer.isCrouching && pLevel.isClientSide()) {
                return InteractionResult.SUCCESS
            }

            if (pedestalBlockEntity.inventory.getStackInSlot(0).isEmpty() && !pStack.isEmpty()) {
                pedestalBlockEntity.inventory.insertItem(0, pStack.copy(), false)
                pStack.shrink(1)
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f)
            } else if (pStack.isEmpty) {
                val stackOnPedestal: ItemStack = pedestalBlockEntity.inventory.extractItem(0, 1, false)
                pPlayer.setItemInHand(InteractionHand.MAIN_HAND, stackOnPedestal)
                pedestalBlockEntity.clearContents()
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f)
            }
        }

        return InteractionResult.SUCCESS
    }

    companion object {
        val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0)
        val CODEC: MapCodec<PedestalBlock?> =
            BlockBehaviour.simpleCodec<PedestalBlock?>(java.util.function.Function { pProperties: BlockBehaviour.Properties ->
                PedestalBlock(pProperties)
            })
    }
}
