package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.enchantment.ModEnchantments
import net.Chidoziealways.everythingjapanese.quest.ModQuestsGen
import net.Chidoziealways.everythingjapanese.quest.Quest
import net.Chidoziealways.everythingjapanese.structure.ModPools
import net.Chidoziealways.everythingjapanese.structure.ModProcessorLists
import net.Chidoziealways.everythingjapanese.structure.ModStructureSets
import net.Chidoziealways.everythingjapanese.structure.ModStructuresGen
import net.Chidoziealways.everythingjapanese.tests.environments.ModGameTestEnvironments
import net.Chidoziealways.everythingjapanese.tests.instances.ModGameTestInstances
import net.Chidoziealways.everythingjapanese.trim.ModTrimMaterials
import net.Chidoziealways.everythingjapanese.trim.ModTrimPatterns
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.Chidoziealways.everythingjapanese.worldgen.ModBiomeModifiers
import net.Chidoziealways.everythingjapanese.worldgen.ModConfiguredFeatures
import net.Chidoziealways.everythingjapanese.worldgen.ModPlacedFeatures
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions
import net.minecraft.Util
import net.minecraft.core.*
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.worldgen.*
import net.minecraft.data.worldgen.biome.BiomeData
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.gametest.framework.GameTestEnvironments
import net.minecraft.gametest.framework.GameTestInstance
import net.minecraft.gametest.framework.GameTestInstances
import net.minecraft.gametest.framework.TestEnvironmentDefinition
import net.minecraft.network.chat.ChatType
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.damagesource.DamageTypes
import net.minecraft.world.entity.animal.*
import net.minecraft.world.entity.animal.frog.FrogVariant
import net.minecraft.world.entity.animal.frog.FrogVariants
import net.minecraft.world.entity.animal.wolf.WolfSoundVariant
import net.minecraft.world.entity.animal.wolf.WolfSoundVariants
import net.minecraft.world.entity.animal.wolf.WolfVariant
import net.minecraft.world.entity.animal.wolf.WolfVariants
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraft.world.entity.decoration.PaintingVariants
import net.minecraft.world.item.Instrument
import net.minecraft.world.item.Instruments
import net.minecraft.world.item.JukeboxSong
import net.minecraft.world.item.JukeboxSongs
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders
import net.minecraft.world.item.equipment.trim.TrimMaterial
import net.minecraft.world.item.equipment.trim.TrimMaterials
import net.minecraft.world.item.equipment.trim.TrimPattern
import net.minecraft.world.item.equipment.trim.TrimPatterns
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterLists
import net.minecraft.world.level.block.entity.BannerPattern
import net.minecraft.world.level.block.entity.BannerPatterns
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerConfig
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerConfigs
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.DensityFunction
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import net.minecraft.world.level.levelgen.NoiseRouterData
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorPreset
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorPresets
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.presets.WorldPreset
import net.minecraft.world.level.levelgen.presets.WorldPresets
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSet
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import net.minecraft.world.level.levelgen.synth.NormalNoise.NoiseParameters
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.registries.ForgeRegistries
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class ModDatapackEntries(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider?>) :
    DatapackBuiltinEntriesProvider(
        output,
        registries,
        BUILDER,
        mutableSetOf(MOD_ID)
    ) {
    companion object {
        val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, { p_333835_: BootstrapContext<DimensionType?>? -> DimensionTypes.bootstrap(p_333835_) })
            .add(Registries.CONFIGURED_CARVER, { p_334235_: BootstrapContext<ConfiguredWorldCarver<*>>? -> Carvers.bootstrap(p_334235_) })
            .add(Registries.CONFIGURED_FEATURE, { p_331696_: BootstrapContext<ConfiguredFeature<*, *>?> -> FeatureUtils.bootstrap(p_331696_) })
            .add(Registries.PLACED_FEATURE, { p_333757_: BootstrapContext<PlacedFeature?>? -> PlacementUtils.bootstrap(p_333757_) })
            .add(Registries.STRUCTURE, { p_329393_: BootstrapContext<Structure?>? -> Structures.bootstrap(p_329393_) })
            .add(Registries.STRUCTURE_SET, { p_336184_: BootstrapContext<StructureSet?>? -> StructureSets.bootstrap(p_336184_) })
            .add(Registries.PROCESSOR_LIST, { p_333601_: BootstrapContext<StructureProcessorList?>? -> ProcessorLists.bootstrap(p_333601_) })
            .add(
                Registries.TEMPLATE_POOL,
                RegistrySetBuilder.RegistryBootstrap { p_332528_: BootstrapContext<StructureTemplatePool?>? ->
                    Pools.bootstrap(p_332528_)
                })
            .add(
                Registries.BIOME,
                RegistrySetBuilder.RegistryBootstrap { p_333300_: BootstrapContext<Biome?>? ->
                    BiomeData.bootstrap(p_333300_)
                })
            .add(
                Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST,
                RegistrySetBuilder.RegistryBootstrap { p_327712_: BootstrapContext<MultiNoiseBiomeSourceParameterList?>? ->
                    MultiNoiseBiomeSourceParameterLists.bootstrap(p_327712_)
                })
            .add(
                Registries.NOISE,
                RegistrySetBuilder.RegistryBootstrap { p_330944_: BootstrapContext<NoiseParameters?>? ->
                    NoiseData.bootstrap(p_330944_)
                })
            .add(
                Registries.DENSITY_FUNCTION,
                RegistrySetBuilder.RegistryBootstrap { p_335193_: BootstrapContext<DensityFunction?>? ->
                    NoiseRouterData.bootstrap(p_335193_)
                })
            .add(
                Registries.NOISE_SETTINGS,
                RegistrySetBuilder.RegistryBootstrap { p_334698_: BootstrapContext<NoiseGeneratorSettings?>? ->
                    NoiseGeneratorSettings.bootstrap(p_334698_)
                })
            .add(
                Registries.WORLD_PRESET,
                RegistrySetBuilder.RegistryBootstrap { p_329030_: BootstrapContext<WorldPreset?>? ->
                    WorldPresets.bootstrap(p_329030_)
                })
            .add(
                Registries.FLAT_LEVEL_GENERATOR_PRESET,
                RegistrySetBuilder.RegistryBootstrap { p_330734_: BootstrapContext<FlatLevelGeneratorPreset?>? ->
                    FlatLevelGeneratorPresets.bootstrap(p_330734_)
                })
            .add(
                Registries.CHAT_TYPE,
                RegistrySetBuilder.RegistryBootstrap { p_335852_: BootstrapContext<ChatType?>? ->
                    ChatType.bootstrap(p_335852_)
                })
            .add(
                Registries.TRIM_PATTERN,
                RegistrySetBuilder.RegistryBootstrap { p_362921_: BootstrapContext<TrimPattern?>? ->
                    TrimPatterns.bootstrap(p_362921_)
                })
            .add(
                Registries.TRIM_MATERIAL,
                RegistrySetBuilder.RegistryBootstrap { p_368813_: BootstrapContext<TrimMaterial?>? ->
                    TrimMaterials.bootstrap(p_368813_)
                })
            .add(
                Registries.TRIAL_SPAWNER_CONFIG,
                RegistrySetBuilder.RegistryBootstrap { p_366114_: BootstrapContext<TrialSpawnerConfig?>? ->
                    TrialSpawnerConfigs.bootstrap(p_366114_)
                })
            .add(
                Registries.WOLF_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_395773_: BootstrapContext<WolfVariant?>? ->
                    WolfVariants.bootstrap(p_395773_)
                })
            .add(
                Registries.WOLF_SOUND_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_392134_: BootstrapContext<WolfSoundVariant?>? ->
                    WolfSoundVariants.bootstrap(p_392134_)
                })
            .add(
                Registries.PAINTING_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_342103_: BootstrapContext<PaintingVariant?>? ->
                    PaintingVariants.bootstrap(p_342103_)
                })
            .add(
                Registries.DAMAGE_TYPE,
                RegistrySetBuilder.RegistryBootstrap { p_335750_: BootstrapContext<DamageType?>? ->
                    DamageTypes.bootstrap(p_335750_)
                })
            .add(
                Registries.BANNER_PATTERN,
                RegistrySetBuilder.RegistryBootstrap { p_335175_: BootstrapContext<BannerPattern?>? ->
                    BannerPatterns.bootstrap(p_335175_)
                })
            .add(
                Registries.ENCHANTMENT,
                RegistrySetBuilder.RegistryBootstrap { p_343249_: BootstrapContext<Enchantment?>? ->
                    Enchantments.bootstrap(p_343249_)
                })
            .add(
                Registries.ENCHANTMENT_PROVIDER,
                RegistrySetBuilder.RegistryBootstrap { p_344835_: BootstrapContext<EnchantmentProvider?>? ->
                    VanillaEnchantmentProviders.bootstrap(p_344835_)
                })
            .add(
                Registries.JUKEBOX_SONG,
                RegistrySetBuilder.RegistryBootstrap { p_344061_: BootstrapContext<JukeboxSong?>? ->
                    JukeboxSongs.bootstrap(p_344061_)
                })
            .add(
                Registries.INSTRUMENT,
                RegistrySetBuilder.RegistryBootstrap { p_370056_: BootstrapContext<Instrument?>? ->
                    Instruments.bootstrap(p_370056_)
                })
            .add(
                Registries.PIG_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_396775_: BootstrapContext<PigVariant?>? ->
                    PigVariants.bootstrap(p_396775_)
                })
            .add(
                Registries.COW_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_397429_: BootstrapContext<CowVariant?>? ->
                    CowVariants.bootstrap(p_397429_)
                })
            .add(
                Registries.CHICKEN_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_392192_: BootstrapContext<ChickenVariant?>? ->
                    ChickenVariants.bootstrap(p_392192_)
                })
            .add(
                Registries.TEST_ENVIRONMENT,
                RegistrySetBuilder.RegistryBootstrap { p_396849_: BootstrapContext<TestEnvironmentDefinition?>? ->
                    GameTestEnvironments.bootstrap(p_396849_)
                })
            .add(
                Registries.TEST_INSTANCE,
                RegistrySetBuilder.RegistryBootstrap { p_393955_: BootstrapContext<GameTestInstance?>? ->
                    GameTestInstances.bootstrap(p_393955_)
                })
            .add(
                Registries.FROG_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_395413_: BootstrapContext<FrogVariant?>? ->
                    FrogVariants.bootstrap(p_395413_)
                })
            .add(
                Registries.CAT_VARIANT,
                RegistrySetBuilder.RegistryBootstrap { p_394778_: BootstrapContext<CatVariant?>? ->
                    CatVariants.bootstrap(p_394778_)
                })

            .add(
                Registries.DIMENSION_TYPE,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<DimensionType?> ->
                    ModDimensions.bootstrapType(context)
                })
            .add(
                Registries.LEVEL_STEM,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<LevelStem?> ->
                    ModDimensions.bootstrapStem(context)
                })
            .add(
                Registries.CONFIGURED_FEATURE,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<ConfiguredFeature<*, *>?> ->
                    ModConfiguredFeatures.bootstrap(context)
                })
            .add(
                Registries.PLACED_FEATURE,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<PlacedFeature?> ->
                    ModPlacedFeatures.bootstrap(context)
                })
            .add(
                Registries.STRUCTURE,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<Structure?> ->
                    ModStructuresGen.bootstrap(context)
                })
            .add(
                ModRegistries.QUEST,
                { context: BootstrapContext<Quest> ->
                    ModQuestsGen.bootstrap(context)
                }
            )
            .add(
                Registries.STRUCTURE_SET,
                { context: BootstrapContext<StructureSet?> ->
                    ModStructureSets.bootstrap(context)
                })
            .add(
                Registries.PROCESSOR_LIST,
                RegistrySetBuilder.RegistryBootstrap { pContext: BootstrapContext<StructureProcessorList?> ->
                    ModProcessorLists.bootstrap(pContext)
                })
            .add(
                Registries.TEMPLATE_POOL,
                RegistrySetBuilder.RegistryBootstrap { pContext: BootstrapContext<StructureTemplatePool?> ->
                    ModPools.bootstrap(pContext)
                })
            .add(
                ForgeRegistries.Keys.BIOME_MODIFIERS,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<BiomeModifier?> ->
                    ModBiomeModifiers.bootstrap(context)
                })
            .add(
                Registries.BIOME,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<Biome?> -> ModBiomes.bootstrap(context) })
            .add(
                Registries.TRIM_PATTERN,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<TrimPattern?> ->
                    ModTrimPatterns.bootstrap(context)
                })
            .add(
                Registries.TRIM_MATERIAL,
                RegistrySetBuilder.RegistryBootstrap { context: BootstrapContext<TrimMaterial?> ->
                    ModTrimMaterials.bootstrap(context)
                })
            .add(
                Registries.ENCHANTMENT,
                RegistrySetBuilder.RegistryBootstrap { obj: BootstrapContext<Enchantment?> -> ModEnchantments.bootstrap(obj) })
            .add(
                Registries.TEST_ENVIRONMENT,
                RegistrySetBuilder.RegistryBootstrap { pContext: BootstrapContext<TestEnvironmentDefinition?> ->
                    ModGameTestEnvironments.bootstrap(pContext)
                })
            .add(
                Registries.TEST_INSTANCE,
                RegistrySetBuilder.RegistryBootstrap { pContext: BootstrapContext<GameTestInstance?> ->
                    ModGameTestInstances.bootstrap(pContext)
                })
        val DATAPACK_REGISTRY_KEYS: MutableList<out ResourceKey<out Registry<*>?>?> = BUILDER.getEntryKeys()

        private fun validateThatAllBiomeFeaturesHaveBiomeFilter(pProvider: HolderLookup.Provider) {
            validateThatAllBiomeFeaturesHaveBiomeFilter(
                pProvider.lookupOrThrow<PlacedFeature?>(Registries.PLACED_FEATURE), pProvider.lookupOrThrow<Biome?>(
                    Registries.BIOME
                )
            )
        }

        fun validateThatAllBiomeFeaturesHaveBiomeFilter(
            pFeatures: HolderGetter<PlacedFeature?>,
            pBiomes: HolderLookup<Biome?>
        ) {
            pBiomes.listElements().forEach { p_256326_: Holder.Reference<Biome?>? ->
                val resourcelocation = p_256326_!!.key().location()
                val list = p_256326_.value()?.getGenerationSettings()?.features()
                list!!.stream().flatMap<Holder<PlacedFeature?>?> { obj: HolderSet<PlacedFeature?>? -> obj!!.stream() }
                    .forEach { p_256657_: Holder<PlacedFeature?>? ->
                        p_256657_!!.unwrap().ifLeft(
                            Consumer { p_325923_: ResourceKey<PlacedFeature?>? ->
                                val reference = pFeatures.getOrThrow(p_325923_)
                                if (!Companion.validatePlacedFeature(reference.value()!!)) {
                                    Util.logAndPauseIfInIde("Placed feature " + p_325923_!!.location() + " in biome " + resourcelocation + " is missing BiomeFilter.biome()")
                                }
                            }).ifRight(Consumer { p_325920_: PlacedFeature? ->
                            if (!Companion.validatePlacedFeature(p_325920_!!)) {
                                Util.logAndPauseIfInIde("Placed inline feature in biome " + p_256326_ + " is missing BiomeFilter.biome()")
                            }
                        })
                    }
            }
        }

        private fun validatePlacedFeature(pFeature: PlacedFeature): Boolean {
            return pFeature.placement().contains(BiomeFilter.biome())
        }

        fun createLookup(): HolderLookup.Provider {
            val `registryaccess$frozen` = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
            val `holderlookup$provider`: HolderLookup.Provider = BUILDER.build(`registryaccess$frozen`)
            validateThatAllBiomeFeaturesHaveBiomeFilter(`holderlookup$provider`)
            return `holderlookup$provider`
        }

        fun builder(): RegistrySetBuilder {
            return BUILDER.copy()
        }
    }
}
