package net.Chidoziealways.everythingjapanese.data.recipes

import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.item.crafting.SmithingKanjiRecipe
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.triggers.Criterion
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput

class SmithingKanjiRecipeBuilder(
    private val category: RecipeCategory,
    private val template: Ingredient,
    private val base: Ingredient,
    private val kanjiType: Holder<KanjiType> // or just KanjiType enum if you prefer
) {
    private val criteria: MutableMap<String, Criterion<*>> = LinkedHashMap()

    companion object {
        fun smithingKanji(
            template: Ingredient,
            base: Ingredient,
            kanjiType: Holder<KanjiType>, // swap for plain KanjiType if you don’t want registry-backed
            category: RecipeCategory
        ): SmithingKanjiRecipeBuilder {
            return SmithingKanjiRecipeBuilder(category, template, base,  kanjiType)
        }
    }

    fun unlocks(key: String, criterion: Criterion<*>): SmithingKanjiRecipeBuilder {
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

        val recipe = SmithingKanjiRecipe(template, base, kanjiType)
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
