package net.Chidoziealways.everythingjapanese.worldgen.dimension

import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes
import net.minecraft.core.Holder
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TimelineTags
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.attribute.BedRule
import net.minecraft.world.attribute.EnvironmentAttributeMap
import net.minecraft.world.attribute.EnvironmentAttributes
import net.minecraft.world.clock.WorldClocks
import net.minecraft.world.level.CardinalLighting
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Climate.ParameterPoint
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.dimension.DimensionType.MonsterSettings
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import net.minecraft.world.timeline.Timelines
import java.util.*
import java.util.List

object ModDimensions {
    val HELL_KEY: ResourceKey<LevelStem> = ResourceKey.create<LevelStem>(
        Registries.LEVEL_STEM,
        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hell")
    )

    val HELL_LEVEL_KEY: ResourceKey<Level> = ResourceKey.create<Level>(
        Registries.DIMENSION,
        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hell")
    )

    val HELL_TYPE: ResourceKey<DimensionType> = ResourceKey.create<DimensionType>(
        Registries.DIMENSION_TYPE,
        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hell_type")
    )

    fun bootstrapType(context: BootstrapContext<DimensionType>) {
        val blocks = context.lookup(Registries.BLOCK)
        val holdergetter = context.lookup(Registries.TIMELINE)
        val clocks = context.lookup(Registries.WORLD_CLOCK)
        context.register(
            HELL_TYPE, DimensionType(
                true,
                false,
                false,
                false,
                (3.0F).toDouble(),
                112,
                256,
                90,
                blocks.getOrThrow(ModTags.Blocks.INFINIBURN_HELL),
                3.0F,
                MonsterSettings(UniformInt.of(0, 7), 1),
                DimensionType.Skybox.END,
                CardinalLighting.Type.NETHER,
                EnvironmentAttributeMap.builder()
                    .set<Float>(EnvironmentAttributes.FOG_START_DISTANCE, 10.0f)
                    .set<Float>(EnvironmentAttributes.FOG_END_DISTANCE, 96.0f)
                    .set<Int>(EnvironmentAttributes.SKY_LIGHT_COLOR, Timelines.NIGHT_SKY_LIGHT_COLOR)
                    .set<Float>(EnvironmentAttributes.SKY_LIGHT_LEVEL, 4.0f)
                    .set<Float>(EnvironmentAttributes.SKY_LIGHT_FACTOR, 0.0f)
                    .set<ParticleOptions>(
                        EnvironmentAttributes.DEFAULT_DRIPSTONE_PARTICLE,
                        ParticleTypes.DRIPPING_DRIPSTONE_LAVA
                    )
                    .set<BedRule>(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES)
                    .set<Boolean>(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true)
                    .set<Boolean>(EnvironmentAttributes.WATER_EVAPORATES, true)
                    .set<Boolean>(EnvironmentAttributes.FAST_LAVA, true)
                    .set<Boolean>(EnvironmentAttributes.PIGLINS_ZOMBIFY, false)
                    .set<Boolean>(EnvironmentAttributes.CAN_START_RAID, false)
                    .set<Boolean>(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
                    .build(),
                holdergetter.getOrThrow(TimelineTags.IN_NETHER),
                Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))
            )
        )
    }

    fun bootstrapStem(context: BootstrapContext<LevelStem>) {
        val biomeRegistry = context.lookup(Registries.BIOME)
        val dimTypes = context.lookup(Registries.DIMENSION_TYPE)
        val noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS)

        val wrappedChunkGenerator = NoiseBasedChunkGenerator(
            FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.HELL_BIOME)),
            noiseGenSettings.getOrThrow(NoiseGeneratorSettings.FLOATING_ISLANDS)
        )

        val noiseBasedChunkGenerator = NoiseBasedChunkGenerator(
            MultiNoiseBiomeSource.createFromList(
                Climate.ParameterList<Holder<Biome>>(
                    List.of<Pair<ParameterPoint, Holder<Biome>>>(
                        Pair.of<ParameterPoint, Holder<Biome>>(
                            Climate.parameters(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
                            biomeRegistry.getOrThrow(ModBiomes.HELL_BIOME)
                        ),
                        Pair.of<ParameterPoint, Holder<Biome>>(
                            Climate.parameters(0.1f, 0.2f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f),
                            biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)
                        ),
                        Pair.of<ParameterPoint, Holder<Biome>>(
                            Climate.parameters(0.3f, 0.6f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f),
                            biomeRegistry.getOrThrow(Biomes.OCEAN)
                        ),
                        Pair.of<ParameterPoint, Holder<Biome>>(
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
