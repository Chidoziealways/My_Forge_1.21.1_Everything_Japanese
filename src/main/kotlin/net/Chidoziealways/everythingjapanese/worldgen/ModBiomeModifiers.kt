package net.Chidoziealways.everythingjapanese.worldgen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.util.random.WeightedList
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.BiomeModifiers
import net.neoforged.neoforge.registries.NeoForgeRegistries

object ModBiomeModifiers {
    val ADD_PYRITE_ORE: ResourceKey<BiomeModifier> = registerKey("add_pyrite_ore")

    val ADD_NEPHRITE_ORE: ResourceKey<BiomeModifier> = registerKey("add_nephrite_ore")

    val ADD_HINOKI_TREE: ResourceKey<BiomeModifier> = registerKey("add_hinoki_tree")

    val ADD_YAMAZAKI_BERRY_BUSH: ResourceKey<BiomeModifier> = registerKey("add_yamazaki_berry_bush")

    val SPAWN_TRICERATOPS: ResourceKey<BiomeModifier> = registerKey("spawn_triceratops")

    val SPAWN_SIKA_DEER: ResourceKey<BiomeModifier> = registerKey("spawn_sika_deer")


    fun bootstrap(context: BootstrapContext<BiomeModifier>) {
        val placedFeature = context.lookup(Registries.PLACED_FEATURE)
        val biomes = context.lookup(Registries.BIOME)

        context.register(
            ADD_PYRITE_ORE, BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.PYRITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
            )
        )

        context.register(
            ADD_NEPHRITE_ORE, BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct<PlacedFeature>(placedFeature.getOrThrow(ModPlacedFeatures.NEPHRITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
            )
        )

        context.register(
            ADD_HINOKI_TREE, BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct<Biome>(
                    biomes.getOrThrow(Biomes.DARK_FOREST),
                    biomes.getOrThrow(Biomes.TAIGA) /* Use for Reference: , biomes.getOrThrow(Biomes.Any_Biome)*/
                ),
                HolderSet.direct<PlacedFeature>(placedFeature.getOrThrow(ModPlacedFeatures.HINOKI_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
            )
        )

        context.register(
            ADD_YAMAZAKI_BERRY_BUSH, BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct<Biome>(
                    biomes.getOrThrow(Biomes.FOREST),
                    biomes.getOrThrow(Biomes.BIRCH_FOREST),
                    biomes.getOrThrow(Biomes.PLAINS)
                ),
                HolderSet.direct<PlacedFeature>(placedFeature.getOrThrow(ModPlacedFeatures.YAMAZAKI_BERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
            )
        )

        context.register(
            SPAWN_TRICERATOPS, BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct<Biome>(biomes.getOrThrow(Biomes.BAMBOO_JUNGLE), biomes.getOrThrow(Biomes.PLAINS)),
                WeightedList.of<SpawnerData>(SpawnerData(ModEntities.TRICERATOPS, 3, 25))
            )
        )

        context.register(
            SPAWN_SIKA_DEER, BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct<Biome>(
                    biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                    biomes.getOrThrow(Biomes.PLAINS),
                    biomes.getOrThrow(Biomes.BIRCH_FOREST),
                    biomes.getOrThrow(Biomes.DARK_FOREST),
                    biomes.getOrThrow(Biomes.TAIGA),
                    biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                    biomes.getOrThrow(Biomes.JUNGLE)
                ),
                WeightedList.of<SpawnerData>(SpawnerData(ModEntities.SIKA_DEER, 9, 25))
            )
        )
    }

    private fun registerKey(name: String): ResourceKey<BiomeModifier> {
        return ResourceKey.create<BiomeModifier>(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
        )
    }
}
