package net.Chidoziealways.everythingjapanese.data.recipes

import net.Chidoziealways.everythingjapanese.item.crafting.SmithingBladeRecipe
import net.Chidoziealways.everythingjapanese.item.crafting.SmithingWrapperRecipe
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.triggers.Criterion
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe

class SmithingWrapperRecipeBuilder(
    private val category: RecipeCategory,
    private val template: Ingredient,
    private val base: Ingredient,
    private val wrapper: Wrapping
) {
    private val criteria: MutableMap<String, Criterion<*>> = LinkedHashMap()

    companion object {
        fun smithingWrapper(
            template: Ingredient,
            base: Ingredient,
            wrapper: Wrapping,
            category: RecipeCategory
        ): SmithingWrapperRecipeBuilder {
            return SmithingWrapperRecipeBuilder(category, template, base, wrapper)
        }
    }

    fun unlocks(key: String, criterion: Criterion<*>): SmithingWrapperRecipeBuilder {
        criteria[key] = criterion
        return this
    }

    fun save(output: RecipeOutput, recipeKey: ResourceKey<Recipe<*>>) {
        ensureValid(recipeKey)

        val advancementBuilder = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeKey))
            .rewards(AdvancementRewards.Builder.recipe(recipeKey))
            .requirements(AdvancementRequirements.Strategy.OR)

        criteria.forEach { (key, criterion) -> advancementBuilder.addCriterion(key, criterion) }

        val recipe = SmithingWrapperRecipe(template, base, wrapper)
        output.accept(
            recipeKey,
            recipe,
            advancementBuilder.build(recipeKey.registry().withPrefix("recipes/${category.folderName}/"))
        )
    }

    private fun ensureValid(recipeKey: ResourceKey<Recipe<*>>) {
        if (criteria.isEmpty()) {
            throw IllegalStateException("No way of obtaining recipe ${recipeKey.registry()}")
        }
    }
}