package net.Chidoziealways.everythingjapanese.recipe

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeBookCategory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModRecipeBookCategories {
    val RECIPE_BOOK_CATEGORIES: DeferredRegister<RecipeBookCategory?> =
        DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, MOD_ID)

    val GROWTH_CHAMBER: RegistryObject<RecipeBookCategory?>? = register("growth_chamber")

    private fun register(pName: String?): RegistryObject<RecipeBookCategory?>? {
        return RECIPE_BOOK_CATEGORIES.register<RecipeBookCategory?>(pName, Supplier { RecipeBookCategory() })
    }
}
