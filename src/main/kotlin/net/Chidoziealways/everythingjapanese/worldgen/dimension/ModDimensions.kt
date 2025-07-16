package net.Chidoziealways.everythingjapanese.worldgen.dimension

import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Climate.ParameterPoint
import net.minecraft.world.level.dimension.BuiltinDimensionTypes
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.dimension.DimensionType.MonsterSettings
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import java.util.*
import java.util.List

object ModDimensions {
    val HELL_KEY: ResourceKey<LevelStem?> = ResourceKey.create<LevelStem?>(
        Registries.LEVEL_STEM,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell")
    )

    val HELL_LEVEL_KEY: ResourceKey<Level?> = ResourceKey.create<Level?>(
        Registries.DIMENSION,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell")
    )

    val HELL_TYPE: ResourceKey<DimensionType?> = ResourceKey.create<DimensionType?>(
        Registries.DIMENSION_TYPE,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell_type")
    )

    fun bootstrapType(context: BootstrapContext<DimensionType?>) {
        context.register(
            HELL_TYPE, DimensionType(
                OptionalLong.of(18000L),
                false,
                false,
                true,
                false,
                1.0,
                false,
                false,
                -64,
                400,
                400,
                ModTags.Blocks.INFINIBURN_HELL,
                BuiltinDimensionTypes.NETHER_EFFECTS,
                0.4f,
                Optional.empty<Int?>(),
                MonsterSettings(false, false, ConstantInt.of(7), 15)
            )
        )
    }

    fun bootstrapStem(context: BootstrapContext<LevelStem?>) {
        val biomeRegistry = context.lookup<Biome?>(Registries.BIOME)
        val dimTypes = context.lookup<DimensionType?>(Registries.DIMENSION_TYPE)
        val noiseGenSettings = context.lookup<NoiseGeneratorSettings?>(Registries.NOISE_SETTINGS)

        val wrappedChunkGenerator = NoiseBasedChunkGenerator(
            FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.HELL_BIOME)),
            noiseGenSettings.getOrThrow(NoiseGeneratorSettings.FLOATING_ISLANDS)
        )

        val noiseBasedChunkGenerator = NoiseBasedChunkGenerator(
            MultiNoiseBiomeSource.createFromList(
                Climate.ParameterList<Holder<Biome?>?>(
                    List.of<Pair<ParameterPoint?, Holder<Biome?>?>?>(
                        Pair.of<ParameterPoint?, Holder<Biome?>?>(
                            Climate.parameters(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                            biomeRegistry.getOrThrow(ModBiomes.HELL_BIOME)
                        ),
                        Pair.of<ParameterPoint?, Holder<Biome?>?>(
                            Climate.parameters(0.1f, 0.2f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f),
                            biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)
                        ),
                        Pair.of<ParameterPoint?, Holder<Biome?>?>(
                            Climate.parameters(0.3f, 0.6f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f),
                            biomeRegistry.getOrThrow(Biomes.OCEAN)
                        ),
                        Pair.of<ParameterPoint?, Holder<Biome?>?>(
                            Climate.parameters(0.4f, 0.3f, 0.2f, 0.1f, 0.0f, 0.0f, 0.0f),
                            biomeRegistry.getOrThrow(Biomes.DARK_FOREST)
                        )
                    )
                )
            ),
            noiseGenSettings.getOrThrow(NoiseGeneratorSettings.FLOATING_ISLANDS)
        )

        val hellStem = LevelStem(dimTypes.getOrThrow(HELL_TYPE), wrappedChunkGenerator)

        context.register(HELL_KEY, hellStem)
    }
}
