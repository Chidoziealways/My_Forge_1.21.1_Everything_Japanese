package net.Chidoziealways.everythingjapanese.worldgen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*
import java.util.List

object ModPlacedFeatures {
    val PYRITE_ORE_PLACED_KEY: ResourceKey<PlacedFeature> = registerKey("pyrite_ore_placed")

    val NEPHRITE_ORE_PLACED_KEY: ResourceKey<PlacedFeature> = registerKey("nephrite_ore_placed")

    val HINOKI_PLACED_KEY: ResourceKey<PlacedFeature> = registerKey("hinoki_placed")

    val YAMAZAKI_BERRY_BUSH_PLACED_KEY: ResourceKey<PlacedFeature> = registerKey("yamazaki_berry_bush_placed")


    fun bootstrap(context: BootstrapContext<PlacedFeature>) {
        val configuredFeatures = context.lookup<ConfiguredFeature<*, *>>(Registries.CONFIGURED_FEATURE)

        register(
            context, PYRITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PYRITE_ORE_KEY),
            ModOrePlacement.commonOrePlacement(
                18,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))
            )
        )

        register(
            context, NEPHRITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NEPHRITE_ORE_KEY),
            ModOrePlacement.rareOrePlacement(
                5,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(50))
            )
        )

        register(
            context, HINOKI_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HINOKI_KEY),
            VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(3, 0.5f, 2),
                ModBlocks.HINOKI_NAEGI
            )
        )

        register(
            context,
            YAMAZAKI_BERRY_BUSH_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.YAMAZAKI_BERRY_BUSH_KEY),
            mutableListOf(
                RarityFilter.onAverageOnceEvery(32),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome()
            )
        )
    }


    fun registerKey(name: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create<PlacedFeature>(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
        )
    }

    private fun register(
        context: BootstrapContext<PlacedFeature>,
        key: ResourceKey<PlacedFeature>,
        configuration: Holder<ConfiguredFeature<*, *>>,
        modifiers: MutableList<PlacementModifier?>
    ) {
        context.register(key, PlacedFeature(configuration, List.copyOf<PlacementModifier>(modifiers)))
    }
}
