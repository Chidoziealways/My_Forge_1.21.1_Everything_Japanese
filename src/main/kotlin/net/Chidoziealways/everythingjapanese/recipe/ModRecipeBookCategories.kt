package net.Chidoziealways.everythingjapanese.recipe

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeBookCategory
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.function.Supplier

object ModRecipeBookCategories {
    val RECIPE_BOOK_CATEGORIES = DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, JAPANESE_MOD_ID)

    val GROWTH_CHAMBER by RECIPE_BOOK_CATEGORIES.register("growth_chamber", Supplier { RecipeBookCategory() })
}
