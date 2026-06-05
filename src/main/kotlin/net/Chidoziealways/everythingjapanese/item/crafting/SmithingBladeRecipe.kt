package net.Chidoziealways.everythingjapanese.item.crafting

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.item.custom.KatanaItem
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
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

data class SmithingBladeRecipe(private val template: Ingredient, private val base: Ingredient, private val blade: BladeType): SmithingRecipe {
    override fun getSerializer(): RecipeSerializer<out SmithingRecipe> = ModRecipes.SMITHING_BLADE_SERIALIZER

    override fun templateIngredient(): Optional<Ingredient> = Optional.of(template)

    override fun baseIngredient(): Ingredient = base

    override fun additionIngredient(): Optional<Ingredient> = Optional.empty()

    override fun showNotification(): Boolean = true

    override fun group(): String = ""

    companion object {
        fun applyBlade(base: ItemStack, bladeType: BladeType): ItemStack {
            val current = KatanaItem.getBlade(base)
            if (current == bladeType) return ItemStack.EMPTY

            val copy = base.copyWithCount(1)
            KatanaItem.setBlade(copy, bladeType)
            return copy
        }

        val CODEC: MapCodec<SmithingBladeRecipe> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter { it.template },
                Ingredient.CODEC.fieldOf("base").forGetter { it.base },
                BladeType.CODEC.fieldOf("blade").forGetter { it.blade }
            ).apply(instance, ::SmithingBladeRecipe)
        }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SmithingBladeRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, {it.template},
            Ingredient.CONTENTS_STREAM_CODEC, {it.base},
            BladeType.STREAM_CODEC, {it.blade},
            ::SmithingBladeRecipe
        )
        val SERIALIZER: RecipeSerializer<SmithingBladeRecipe> = RecipeSerializer(CODEC, STREAM_CODEC)

        init {

        }
    }

    override fun assemble(
        input: SmithingRecipeInput,
    ): ItemStack = applyBlade(input.base, this.blade)

    override fun placementInfo(): PlacementInfo = PlacementInfo.create(listOf(template, base))
}
