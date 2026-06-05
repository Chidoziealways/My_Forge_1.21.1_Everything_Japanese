package net.Chidoziealways.everythingjapanese.recipe

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.item.crafting.SmithingBladeRecipe
import net.Chidoziealways.everythingjapanese.item.crafting.SmithingKanjiRecipe
import net.Chidoziealways.everythingjapanese.item.crafting.SmithingWrapperRecipe
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.function.Supplier

object ModRecipes {
    val SERIALIZERS = DeferredRegister.create<RecipeSerializer<*>>(Registries.RECIPE_SERIALIZER, JAPANESE_MOD_ID)
    val TYPES = DeferredRegister.create<RecipeType<*>>(Registries.RECIPE_TYPE, JAPANESE_MOD_ID)

    val GROWTH_CHAMBER_SERIALIZER by
        SERIALIZERS.register(
            "growth_chamber",
            Supplier { GrowthChamberRecipe.SERIALIZER })
    val GROWTH_CHAMBER_TYPE by
        TYPES.register("growth_chamber", Supplier {
            object : RecipeType<GrowthChamberRecipe> {
                override fun toString(): String {
                    return "growth_chamber"
                }
            }
        })

    val SMITHING_KANJI_SERIALIZER by
            SERIALIZERS.register(
                "smithing_kanji",
                Supplier{ SmithingKanjiRecipe.SERIALIZER })

    val SMITHING_KANJI_TYPE by
            TYPES.register("smithing_kanji", Supplier {
                object : RecipeType<SmithingKanjiRecipe> {
                    override fun toString(): String {
                        return "smithing_kanji"
                    }
                }
            })

    val SMITHING_BLADE_SERIALIZER by
            SERIALIZERS.register("smithing_blade",
                Supplier { SmithingBladeRecipe.SERIALIZER })

    val SMITHING_BLADE_TYPE by
            TYPES.register("smithing_blade", Supplier {
                object : RecipeType<SmithingBladeRecipe> {
                    override fun toString(): String {
                        return "smithing_blade"
                    }
                }
            })

    val SMITHING_WRAPPER_SERIALIZER by
    SERIALIZERS.register("smithing_wrapper",
        Supplier { SmithingWrapperRecipe.SERIALIZER })

    val SMITHING_WRAPPER_TYPE by
    TYPES.register("smithing_wrapper", Supplier {
        object : RecipeType<SmithingWrapperRecipe> {
            override fun toString(): String {
                return "smithing_wrapper"
            }
        }
    })

    fun register(eventBus: IEventBus) {
        SERIALIZERS.register(eventBus)
        TYPES.register(eventBus)
    }
}
