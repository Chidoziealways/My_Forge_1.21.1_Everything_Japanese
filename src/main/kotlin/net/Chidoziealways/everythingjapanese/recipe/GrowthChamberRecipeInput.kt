package net.Chidoziealways.everythingjapanese.recipe

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeInput

@JvmRecord
data class GrowthChamberRecipeInput(val input: ItemStack) : RecipeInput {
    override fun getItem(i: Int): ItemStack {
        return input
    }

    override fun size(): Int {
        return 1
    }
}
