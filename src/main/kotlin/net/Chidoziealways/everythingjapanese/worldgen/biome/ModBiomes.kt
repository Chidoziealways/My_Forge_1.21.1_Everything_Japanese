package net.Chidoziealways.everythingjapanese.worldgen.biome

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.Musics
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Biome.BiomeBuilder
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature

object ModBiomes {
    val HELL_BIOME: ResourceKey<Biome?> = ResourceKey.create<Biome?>(
        Registries.BIOME,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell_biome")
    )

    fun bootstrap(context: BootstrapContext<Biome?>) {
        context.register(HELL_BIOME, hellBiome(context))
    }

    fun globalOverworldGeneration(builder: BiomeGenerationSettings.Builder) {
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder)
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder)
        BiomeDefaultFeatures.addSurfaceFreezing(builder)
    }

    private fun hellBiome(context: BootstrapContext<Biome?>): Biome {
        val spawnBuilder = MobSpawnSettings.Builder()
        spawnBuilder.addSpawn(MobCategory.MONSTER, 10, SpawnerData(EntityType.ZOMBIE, 50, 100))

        BiomeDefaultFeatures.monsters(spawnBuilder, 95, 5, 100, false)

        val biomeBuilder =
            BiomeGenerationSettings.Builder(
                context.lookup<PlacedFeature?>(Registries.PLACED_FEATURE), context.lookup<ConfiguredWorldCarver<*>?>(
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
                    .waterFogColor(0xbf1b26)
                    .skyColor(0x30c918)
                    .grassColorOverride(0x7f03fc)
                    .foliageColorOverride(0xd203fc)
                    .fogColor(0x22a1e6)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(Musics.createGameMusic(ModSounds.AO_TO_NATSU!!.getHolder().get())).build()
            )
            .build()
    }
}
