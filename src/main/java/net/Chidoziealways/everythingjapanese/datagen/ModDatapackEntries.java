package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.enchantment.ModEnchantments;
import net.Chidoziealways.everythingjapanese.structure.ModPools;
import net.Chidoziealways.everythingjapanese.structure.ModProcessorLists;
import net.Chidoziealways.everythingjapanese.structure.ModStructureSets;
import net.Chidoziealways.everythingjapanese.structure.ModStructuresGen;
import net.Chidoziealways.everythingjapanese.tests.environments.ModGameTestEnvironments;
import net.Chidoziealways.everythingjapanese.tests.instances.ModGameTestInstances;
import net.Chidoziealways.everythingjapanese.trim.ModTrimMaterials;
import net.Chidoziealways.everythingjapanese.trim.ModTrimPatterns;
import net.Chidoziealways.everythingjapanese.worldgen.ModBiomeModifiers;
import net.Chidoziealways.everythingjapanese.worldgen.ModConfiguredFeatures;
import net.Chidoziealways.everythingjapanese.worldgen.ModPlacedFeatures;
import net.Chidoziealways.everythingjapanese.worldgen.biome.ModBiomes;
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.*;
import net.minecraft.data.worldgen.biome.BiomeData;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.gametest.framework.GameTestEnvironments;
import net.minecraft.gametest.framework.GameTestInstances;
import net.minecraft.network.chat.ChatType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.animal.CatVariants;
import net.minecraft.world.entity.animal.ChickenVariants;
import net.minecraft.world.entity.animal.CowVariants;
import net.minecraft.world.entity.animal.PigVariants;
import net.minecraft.world.entity.animal.frog.FrogVariants;
import net.minecraft.world.entity.animal.wolf.WolfSoundVariants;
import net.minecraft.world.entity.animal.wolf.WolfVariants;
import net.minecraft.world.entity.decoration.PaintingVariants;
import net.minecraft.world.item.Instruments;
import net.minecraft.world.item.JukeboxSongs;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterLists;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerConfigs;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorPresets;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, DimensionTypes::bootstrap)
            .add(Registries.CONFIGURED_CARVER, (RegistrySetBuilder.RegistryBootstrap)Carvers::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, (RegistrySetBuilder.RegistryBootstrap)FeatureUtils::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacementUtils::bootstrap)
            .add(Registries.STRUCTURE, Structures::bootstrap)
            .add(Registries.STRUCTURE_SET, StructureSets::bootstrap)
            .add(Registries.PROCESSOR_LIST, ProcessorLists::bootstrap)
            .add(Registries.TEMPLATE_POOL, Pools::bootstrap)
            .add(Registries.BIOME, BiomeData::bootstrap)
            .add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, MultiNoiseBiomeSourceParameterLists::bootstrap)
            .add(Registries.NOISE, NoiseData::bootstrap)
            .add(Registries.DENSITY_FUNCTION, NoiseRouterData::bootstrap)
            .add(Registries.NOISE_SETTINGS, NoiseGeneratorSettings::bootstrap)
            .add(Registries.WORLD_PRESET, WorldPresets::bootstrap)
            .add(Registries.FLAT_LEVEL_GENERATOR_PRESET, FlatLevelGeneratorPresets::bootstrap)
            .add(Registries.CHAT_TYPE, ChatType::bootstrap)
            .add(Registries.TRIM_PATTERN, TrimPatterns::bootstrap)
            .add(Registries.TRIM_MATERIAL, TrimMaterials::bootstrap)
            .add(Registries.TRIAL_SPAWNER_CONFIG, TrialSpawnerConfigs::bootstrap)
            .add(Registries.WOLF_VARIANT, WolfVariants::bootstrap)
            .add(Registries.WOLF_SOUND_VARIANT, WolfSoundVariants::bootstrap)
            .add(Registries.PAINTING_VARIANT, PaintingVariants::bootstrap)
            .add(Registries.DAMAGE_TYPE, DamageTypes::bootstrap)
            .add(Registries.BANNER_PATTERN, BannerPatterns::bootstrap)
            .add(Registries.ENCHANTMENT, Enchantments::bootstrap)
            .add(Registries.ENCHANTMENT_PROVIDER, VanillaEnchantmentProviders::bootstrap)
            .add(Registries.JUKEBOX_SONG, JukeboxSongs::bootstrap)
            .add(Registries.INSTRUMENT, Instruments::bootstrap)
            .add(Registries.PIG_VARIANT, PigVariants::bootstrap)
            .add(Registries.COW_VARIANT, CowVariants::bootstrap)
            .add(Registries.CHICKEN_VARIANT, ChickenVariants::bootstrap)
            .add(Registries.TEST_ENVIRONMENT, GameTestEnvironments::bootstrap)
            .add(Registries.TEST_INSTANCE, GameTestInstances::bootstrap)
            .add(Registries.FROG_VARIANT, FrogVariants::bootstrap)
            .add(Registries.CAT_VARIANT, CatVariants::bootstrap)

            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.STRUCTURE, ModStructuresGen::bootstrap)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            .add(Registries.PROCESSOR_LIST, ModProcessorLists::bootstrap)
            .add(Registries.TEMPLATE_POOL, ModPools::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.TEST_ENVIRONMENT, ModGameTestEnvironments::bootstrap)
            .add(Registries.TEST_INSTANCE, ModGameTestInstances::bootstrap);
    public static final List<? extends ResourceKey<? extends Registry<?>>> DATAPACK_REGISTRY_KEYS = BUILDER.getEntryKeys();

    private static void validateThatAllBiomeFeaturesHaveBiomeFilter(HolderLookup.Provider pProvider) {
        validateThatAllBiomeFeaturesHaveBiomeFilter(pProvider.lookupOrThrow(Registries.PLACED_FEATURE), pProvider.lookupOrThrow(Registries.BIOME));
    }

    public static void validateThatAllBiomeFeaturesHaveBiomeFilter(HolderGetter<PlacedFeature> pFeatures, HolderLookup<Biome> pBiomes) {
        pBiomes.listElements().forEach(p_256326_ -> {
            ResourceLocation resourcelocation = p_256326_.key().location();
            List<HolderSet<PlacedFeature>> list = p_256326_.value().getGenerationSettings().features();
            list.stream().flatMap(HolderSet::stream).forEach(p_256657_ -> p_256657_.unwrap().ifLeft(p_325923_ -> {
                Holder.Reference<PlacedFeature> reference = pFeatures.getOrThrow((ResourceKey<PlacedFeature>)p_325923_);
                if (!validatePlacedFeature(reference.value())) {
                    Util.logAndPauseIfInIde("Placed feature " + p_325923_.location() + " in biome " + resourcelocation + " is missing BiomeFilter.biome()");
                }
            }).ifRight(p_325920_ -> {
                if (!validatePlacedFeature(p_325920_)) {
                    Util.logAndPauseIfInIde("Placed inline feature in biome " + p_256326_ + " is missing BiomeFilter.biome()");
                }
            }));
        });
    }

    private static boolean validatePlacedFeature(PlacedFeature pFeature) {
        return pFeature.placement().contains(BiomeFilter.biome());
    }

    public ModDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(EverythingJapanese.MOD_ID));
    }

    public static HolderLookup.Provider createLookup() {
        RegistryAccess.Frozen registryaccess$frozen = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        HolderLookup.Provider holderlookup$provider = BUILDER.build(registryaccess$frozen);
        validateThatAllBiomeFeaturesHaveBiomeFilter(holderlookup$provider);
        return holderlookup$provider;
    }

    public static RegistrySetBuilder builder() {
        return BUILDER.copy();
    }
}
