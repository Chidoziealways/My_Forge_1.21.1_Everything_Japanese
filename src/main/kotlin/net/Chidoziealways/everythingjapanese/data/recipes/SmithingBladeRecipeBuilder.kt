package net.Chidoziealways.everythingjapanese.data.recipes

import net.Chidoziealways.everythingjapanese.item.crafting.SmithingBladeRecipe
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe

class SmithingBladeRecipeBuilder(
    private val category: RecipeCategory,
    private val template: Ingredient,
    private val base: Ingredient,
    private val bladeType: BladeType
) {
    private val criteria: MutableMap<String, Criterion<*>> = LinkedHashMap()

    companion object {
        fun smithingBlade(
            template: Ingredient,
            base: Ingredient,
            bladeType: BladeType,
            category: RecipeCategory
        ): SmithingBladeRecipeBuilder {
            return SmithingBladeRecipeBuilder(category, template, base, bladeType)
        }
    }

    fun unlocks(key: String, criterion: Criterion<*>): SmithingBladeRecipeBuilder {
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

        val recipe = SmithingBladeRecipe(template, base, bladeType)
        output.accept(
            recipeKey,
            recipe,
            advancementBuilder.build(recipeKey.location().withPrefix("recipes/${category.folderName}/"))
        )
    }

    private fun ensureValid(recipeKey: ResourceKey<Recipe<*>>) {
        if (criteria.isEmpty()) {
            throw IllegalStateException("No way of obtaining recipe ${recipeKey.location()}")
        }
    }
}