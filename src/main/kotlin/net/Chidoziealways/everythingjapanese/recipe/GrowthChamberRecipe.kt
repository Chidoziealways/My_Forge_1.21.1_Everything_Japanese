package net.Chidoziealways.everythingjapanese.recipe

import com.mojang.datafixers.util.Function3
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.*
import net.minecraft.world.level.Level
import java.util.*
import java.util.List
import java.util.function.Function

@JvmRecord
data class GrowthChamberRecipe(
    val inputItem: Ingredient,
    val output: ItemStack,
    val category: GrowthChamberCategory
) : Recipe<GrowthChamberRecipeInput> {
    override fun matches(growthChamberRecipeInput: GrowthChamberRecipeInput, level: Level): Boolean {
        if (level.isClientSide()) {
            return false
        }

        return inputItem!!.test(growthChamberRecipeInput!!.getItem(0))
    }

    override fun assemble(
        growthChamberRecipeInput: GrowthChamberRecipeInput,
    ): ItemStack {
        return output!!.copy()
    }

    override fun showNotification(): Boolean = true

    override fun group(): String = ""

    override fun getSerializer(): RecipeSerializer<out Recipe<GrowthChamberRecipeInput>> {
        return ModRecipes.GROWTH_CHAMBER_SERIALIZER
    }

    override fun getType(): RecipeType<out Recipe<GrowthChamberRecipeInput>> {
        return ModRecipes.GROWTH_CHAMBER_TYPE
    }

    override fun placementInfo(): PlacementInfo {
        if (placementInfo == null) {
            placementInfo = PlacementInfo.createFromOptionals(
                listOf<Optional<Ingredient>>(
                    Optional.of<Ingredient>(
                        this.inputItem!!
                    )
                )
            )
        }
        return placementInfo!!
    }

    override fun recipeBookCategory(): RecipeBookCategory {
        return when (this.category) {
            GrowthChamberCategory.INGREDIENTS -> RecipeBookCategories.BLAST_FURNACE_MISC
            null -> TODO()
        }
    }

    companion object {
        private var placementInfo: PlacementInfo? = null

        val CODEC: MapCodec<GrowthChamberRecipe> =
            RecordCodecBuilder.mapCodec<GrowthChamberRecipe>(Function { inst: RecordCodecBuilder.Instance<GrowthChamberRecipe> ->
                inst!!.group<Ingredient, ItemStack, GrowthChamberCategory>(
                    Ingredient.CODEC.fieldOf("ingredient")
                        .forGetter<GrowthChamberRecipe>(Function { obj: GrowthChamberRecipe -> obj!!.inputItem }),
                    ItemStack.CODEC.fieldOf("result")
                        .forGetter<GrowthChamberRecipe>(Function { obj: GrowthChamberRecipe -> obj!!.output }),
                    GrowthChamberCategory.Companion.CODEC.fieldOf("category")
                        .forGetter<GrowthChamberRecipe>(GrowthChamberRecipe::category)
                ).apply<GrowthChamberRecipe>(
                    inst,
                    Function3 { inputItem: Ingredient, output: ItemStack, category: GrowthChamberCategory ->
                        GrowthChamberRecipe(
                            inputItem,
                            output,
                            category
                        )
                    })
            })

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, GrowthChamberRecipe> =
            StreamCodec.composite<RegistryFriendlyByteBuf, GrowthChamberRecipe, Ingredient, ItemStack, GrowthChamberCategory>(
                Ingredient.CONTENTS_STREAM_CODEC, { obj: GrowthChamberRecipe -> obj!!.inputItem },
                ItemStack.STREAM_CODEC, { obj: GrowthChamberRecipe -> obj!!.output },
                GrowthChamberCategory.Companion.STREAM_CODEC, GrowthChamberRecipe::category,
                { inputItem: Ingredient, output: ItemStack, category: GrowthChamberCategory ->
                    GrowthChamberRecipe(
                        inputItem,
                        output,
                        category
                    )
                })

        val SERIALIZER = RecipeSerializer(CODEC, STREAM_CODEC)
    }
}
