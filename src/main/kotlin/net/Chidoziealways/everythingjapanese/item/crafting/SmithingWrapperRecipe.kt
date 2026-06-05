package net.Chidoziealways.everythingjapanese.item.crafting

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.item.custom.KatanaItem
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.Chidoziealways.everythingjapanese.recipe.ModRecipes
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

data class SmithingWrapperRecipe(private val template: Ingredient, private val base: Ingredient, private val wrapper: Wrapping): SmithingRecipe {
    override fun getSerializer(): RecipeSerializer<out SmithingRecipe> = ModRecipes.SMITHING_WRAPPER_SERIALIZER

    override fun templateIngredient(): Optional<Ingredient> = Optional.of(template)

    override fun baseIngredient(): Ingredient = base

    override fun additionIngredient(): Optional<Ingredient> = Optional.empty()

    companion object {
        fun applyBlade(base: ItemStack, wrapper: Wrapping): ItemStack {
            val current = KatanaItem.getWrap(base)
            if (current == wrapper) return ItemStack.EMPTY

            val copy = base.copyWithCount(1)
            KatanaItem.setWrap(copy, wrapper)
            return copy
        }
        val CODEC: MapCodec<SmithingWrapperRecipe> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter { it.template },
                Ingredient.CODEC.fieldOf("base").forGetter { it.base },
                Wrapping.CODEC.fieldOf("wrapper").forGetter { it.wrapper }
            ).apply(instance, ::SmithingWrapperRecipe)
        }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SmithingWrapperRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, {it.template},
            Ingredient.CONTENTS_STREAM_CODEC, {it.base},
            Wrapping.STREAM_CODEC, {it.wrapper},
            ::SmithingWrapperRecipe
        )
        val SERIALIZER = RecipeSerializer(CODEC, STREAM_CODEC)
    }

    override fun assemble(
        input: SmithingRecipeInput,
    ): ItemStack = applyBlade(input.base, this.wrapper)

    override fun showNotification(): Boolean = true

    override fun group(): String = ""

    override fun placementInfo(): PlacementInfo = PlacementInfo.create(listOf(template, base))
}
