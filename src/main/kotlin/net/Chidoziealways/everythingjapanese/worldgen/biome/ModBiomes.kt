package net.Chidoziealways.everythingjapanese.worldgen.biome

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.sounds.Musics
import net.minecraft.world.attribute.AmbientMoodSettings
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Biome.BiomeBuilder
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature

object ModBiomes {
    val HELL_BIOME: ResourceKey<Biome> = ResourceKey.create<Biome>(
        Registries.BIOME,
        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hell_biome")
    )

    fun bootstrap(context: BootstrapContext<Biome>) {
        println("Bootstrapping Biomes")

        context.register(HELL_BIOME, hellBiome(context))
    }

    fun globalOverworldGeneration(builder: BiomeGenerationSettings.Builder) {
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder)
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder)
        BiomeDefaultFeatures.addSurfaceFreezing(builder)
    }

    private fun hellBiome(context: BootstrapContext<Biome>): Biome {
        val spawnBuilder = MobSpawnSettings.Builder()
        spawnBuilder.addSpawn(MobCategory.MONSTER, 10, SpawnerData(EntityType.ZOMBIE, 50, 100))

        BiomeDefaultFeatures.monsters(spawnBuilder, 95, 5, 100, 10, false)

        val biomeBuilder =
            BiomeGenerationSettings.Builder(
                context.lookup<PlacedFeature>(Registries.PLACED_FEATURE), context.lookup<ConfiguredWorldCarver<*>>(
                    Registries.CONFIGURED_CARVER
                )
            )

        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        return BiomeBuilder()
            .hasPrecipitation(false)
            .downfall(0.8f)
            .temperature(0.7f)
            .generationSettings(biomeBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .specialEffects(
                (BiomeSpecialEffects.Builder())
                    .waterColor(0xe82e3b)
                    .grassColorOverride(0x7f03fc)
                    .foliageColorOverride(0xd203fc).build()
            )
            .build()
    }
}
