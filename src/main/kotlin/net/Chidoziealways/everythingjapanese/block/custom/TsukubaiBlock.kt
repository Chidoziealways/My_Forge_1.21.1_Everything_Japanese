package net.Chidoziealways.everythingjapanese.block.custom

import net.minecraft.client.gui.components.SplashRenderer
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUtils
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class TsukubaiBlock(props: Properties) : Block(props) {
    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return SHAPE
    }

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FILLED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FILLED)
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
        if (level.isClientSide) return InteractionResult.SUCCESS
        if (itemStack.item == Items.WATER_BUCKET) {
            if (!state.getValue(FILLED)) {
                level.setBlock(
                    pos,
                    state.setValue(FILLED, true),
                    UPDATE_ALL
                )

                player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack, player, ItemStack(Items.BUCKET)))
            }
        } else if (itemStack.item == Items.BUCKET) {
            if (state.getValue(FILLED)) {
                level.setBlock(
                    pos,
                    state.setValue(FILLED, false),
                    UPDATE_ALL
                )
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack, player, ItemStack(Items.WATER_BUCKET)))
            }
        }
        return InteractionResult.SUCCESS
    }

    override fun getCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    companion object {
        val SHAPE: VoxelShape = Shapes.or(
            box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            box(0.0, 2.0, 0.0, 2.0, 14.0, 16.0),
            box(14.0, 2.0, 0.0, 16.0, 14.0, 16.0),
            box(2.0, 2.0, 0.0, 14.0, 14.0, 2.0),
            box(2.0, 2.0, 14.0, 14.0, 14.0, 16.0)
        )

        val FILLED = BooleanProperty.create("filled")
    }
}