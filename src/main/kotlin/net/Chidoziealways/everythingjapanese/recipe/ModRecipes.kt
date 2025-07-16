package net.Chidoziealways.everythingjapanese.recipe

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModRecipes {
    val SERIALIZERS: DeferredRegister<RecipeSerializer<*>?> =
        DeferredRegister.create<RecipeSerializer<*>?>(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID)
    val TYPES: DeferredRegister<RecipeType<*>?> =
        DeferredRegister.create<RecipeType<*>?>(ForgeRegistries.RECIPE_TYPES, MOD_ID)

    val GROWTH_CHAMBER_SERIALIZER: RegistryObject<RecipeSerializer<GrowthChamberRecipe?>?>? =
        SERIALIZERS.register<RecipeSerializer<GrowthChamberRecipe?>?>(
            "growth_chamber",
            Supplier { GrowthChamberRecipe.Serializer() })
    val GROWTH_CHAMBER_TYPE: RegistryObject<RecipeType<GrowthChamberRecipe?>?> =
        TYPES.register<RecipeType<GrowthChamberRecipe?>?>("growth_chamber", Supplier {
            object : RecipeType<GrowthChamberRecipe?> {
                override fun toString(): String {
                    return "growth_chamber"
                }
            }
        })

    fun register(eventBus: BusGroup?) {
        SERIALIZERS.register(eventBus)
        TYPES.register(eventBus)
    }
}
