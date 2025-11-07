package net.Chidoziealways.everythingjapanese.block.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.screen.custom.calligraphytable.CalligraphyTableMenu
import net.Chidoziealways.everythingjapanese.stats.ModStats
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class CalligraphyTableBlock(props: Properties): Block(props) {

    override fun codec(): MapCodec<out Block> = CODEC

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide) {
            player.openMenu(state.getMenuProvider(level, pos))
            player.awardStat(ModStats.INTERACT_WITH_CALLIGRAPHY_TABLE)
        }

        return InteractionResult.SUCCESS
    }

    override fun getMenuProvider(state: BlockState, level: Level, pos: BlockPos): MenuProvider {
        return SimpleMenuProvider(
            { int, inv, _  -> CalligraphyTableMenu(int, inv, ContainerLevelAccess.create(level, pos))},
            CONTAINER_TITLE
        )
    }

    companion object {
        val CODEC: MapCodec<CalligraphyTableBlock> = simpleCodec(::CalligraphyTableBlock)
        val CONTAINER_TITLE = Component.translatable("container.calligraphy_table")
    }
}