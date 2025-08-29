package net.Chidoziealways.everythingjapanese

import net.Chidoziealways.everythingjapanese.data.recipes.SmithingBladeRecipeBuilder
import net.Chidoziealways.everythingjapanese.data.recipes.SmithingKanjiRecipeBuilder
import net.Chidoziealways.everythingjapanese.data.recipes.SmithingWrapperRecipeBuilder
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.item.crafting.SmithingWrapperRecipe
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe

fun RecipeProvider.kanjiSmithing(template: Item, kanji: ResourceKey<KanjiType>, recipe: ResourceKey<Recipe<*>>) {
    val reference = this.registries.lookupOrThrow(ModRegistries.KANJI).getOrThrow(kanji)
    SmithingKanjiRecipeBuilder.smithingKanji(
        Ingredient.of(template),
        Ingredient.of(ModItems.TALISMAN_ITEM), reference, RecipeCategory.MISC).unlocks("has_smithing_kanji_template", this.has(template))
        .save(this.output, recipe)
}

fun RecipeProvider.bladeSmithing(template: Item, blade: BladeType, recipe: ResourceKey<Recipe<*>>) {
    SmithingBladeRecipeBuilder.smithingBlade(
        Ingredient.of(template),
        Ingredient.of(ModItems.KATANA), blade, RecipeCategory.COMBAT).unlocks("has_smithing_blade_template", this.has(template))
        .save(this.output, recipe)
}

fun RecipeProvider.wrapSmithing(template: Item, wrapper: Wrapping, recipe: ResourceKey<Recipe<*>>) {
    SmithingWrapperRecipeBuilder.smithingWrapper(
        Ingredient.of(template),
        Ingredient.of(ModItems.KATANA), wrapper, RecipeCategory.COMBAT).unlocks("has_smithing_wrapper_template", this.has(template))
        .save(this.output, recipe)
}