package net.Chidoziealways.everythingjapanese.custom

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SaplingBlock
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.world.level.block.state.BlockState
import java.util.function.Supplier

class ModSaplingBlock(treeGrower: TreeGrower, properties: Properties, private val block: Supplier<Block>) :
    SaplingBlock(treeGrower, properties) {
    override fun mayPlaceOn(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos): Boolean {
        return pState.`is`(block.get())
    }
}
