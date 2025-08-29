package net.Chidoziealways.everythingjapanese.item.custom

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.block.entity.FuelValues

class FuelItem(pProperties: Properties, burnTime: Int) : Item(pProperties) {
    private var burnTime = 0

    init {
        this.burnTime = burnTime
    }

    override fun getBurnTime(itemStack: ItemStack, recipeType: RecipeType<*>?, fuelValues: FuelValues): Int {
        return this.burnTime
    }
}
