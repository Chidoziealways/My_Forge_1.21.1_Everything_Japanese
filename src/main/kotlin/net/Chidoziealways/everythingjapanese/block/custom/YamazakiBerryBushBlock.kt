package net.Chidoziealways.everythingjapanese.custom

import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.SweetBerryBushBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult

class YamazakiBerryBushBlock(properties: Properties) : SweetBerryBushBlock(properties) {
    public override fun getCloneItemStack(
        pLevel: LevelReader,
        pPos: BlockPos,
        pState: BlockState,
        bool: Boolean
    ): ItemStack {
        return ItemStack(ModItems.YAMAZAKI_BERRIES)
    }

    override fun useWithoutItem(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHitResult: BlockHitResult
    ): InteractionResult {
        val i = pState.getValue<Int>(AGE)
        val flag = i == 3
        if (i > 1) {
            val j = 1 + pLevel.random.nextInt(2)
            popResource(pLevel, pPos, ItemStack(ModItems.YAMAZAKI_BERRIES, j + (if (flag) 1 else 0)))
            pLevel.playSound(
                null,
                pPos,
                SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
                SoundSource.BLOCKS,
                1.0f,
                0.8f + pLevel.random.nextFloat() * 0.4f
            )
            val blockstate = pState.setValue<Int, Int?>(AGE, 1)
            pLevel.setBlock(pPos, blockstate, 2)
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate))
            return InteractionResult.SUCCESS
        } else {
            return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult)
        }
    }
}
