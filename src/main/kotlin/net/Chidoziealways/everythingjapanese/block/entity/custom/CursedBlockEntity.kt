package net.Chidoziealways.everythingjapanese.block.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.screen.custom.cursedblock.CursedBlockMenu
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class CursedBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(ModBlockEntities.CURSED_BLOCK_BE, pos, state), MenuProvider {
    override fun createMenu(p0: Int, p1: Inventory, p2: Player): AbstractContainerMenu {
        return CursedBlockMenu(p0, p1, this)
    }

    override fun getDisplayName(): Component {
        return Component.translatable("block.everythingjapanese.cursed_block")
    }
}
