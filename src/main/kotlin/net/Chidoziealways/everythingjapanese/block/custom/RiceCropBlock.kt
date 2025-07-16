package net.Chidoziealways.everythingjapanese.custom

import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty

class RiceCropBlock(properties: BlockBehaviour.Properties) : CropBlock(properties) {

    override fun getBaseSeedId(): ItemLike {
        return ModItems.RICE_SEEDS!!.get()
    }

    override fun getAgeProperty(): IntegerProperty {
        return ModBlockStateProperties.AGE_4
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block?, BlockState?>) {
        pBuilder.add(ModBlockStateProperties.AGE_4)
    }

    companion object {
        val maxAge: Int = 4
    }
}
