package net.Chidoziealways.everythingjapanese.item.custom

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeType

class FuelItem(pProperties: Properties, burnTime: Int) : Item(pProperties) {
    private var burnTime = 0

    init {
        this.burnTime = burnTime
    }

    override fun getBurnTime(itemStack: ItemStack?, recipeType: RecipeType<*>?): Int {
        return this.burnTime
    }
}
