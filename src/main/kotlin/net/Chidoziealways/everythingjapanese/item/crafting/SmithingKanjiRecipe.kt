package net.Chidoziealways.everythingjapanese.item.crafting

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.item.custom.TalismanItem
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.recipe.ModRecipes
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.PlacementInfo
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.SmithingRecipe
import net.minecraft.world.item.crafting.SmithingRecipeInput
import java.util.Optional

data class SmithingKanjiRecipe(private val template: Ingredient, private val base: Ingredient, private val kanji: Holder<KanjiType>): SmithingRecipe {
    override fun getSerializer(): RecipeSerializer<out SmithingRecipe> = ModRecipes.SMITHING_KANJI_SERIALIZER

    override fun templateIngredient(): Optional<Ingredient> = Optional.of(template)

    override fun baseIngredient(): Ingredient = base

    override fun additionIngredient(): Optional<Ingredient> = Optional.empty()

    companion object {
        fun applyKanji(base: ItemStack, type: Holder<KanjiType>): ItemStack {
            val current = TalismanItem.getKanji(base)
            if (current == type) return ItemStack.EMPTY

            val copy = base.copyWithCount(1)
            TalismanItem.setKanji(copy, type)
            return copy
        }
        val CODEC: MapCodec<SmithingKanjiRecipe> = RecordCodecBuilder.mapCodec{ instance ->
            instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter { it.template },
                Ingredient.CODEC.fieldOf("base").forGetter { it.base },
                KanjiType.CODEC.fieldOf("kanji").forGetter { it.kanji }
            ).apply(instance, ::SmithingKanjiRecipe)
        }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SmithingKanjiRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, {it.template},
            Ingredient.CONTENTS_STREAM_CODEC, {it.base},
            KanjiType.STREAM_CODEC, {it.kanji},
            ::SmithingKanjiRecipe
        )
        val SERIALIZER = RecipeSerializer(CODEC, STREAM_CODEC);
    }

    override fun assemble(
        input: SmithingRecipeInput,
    ): ItemStack = applyKanji(input.base, this.kanji)

    override fun showNotification(): Boolean = true

    override fun group(): String = ""

    override fun placementInfo(): PlacementInfo = PlacementInfo.create(listOf(template, base))

}