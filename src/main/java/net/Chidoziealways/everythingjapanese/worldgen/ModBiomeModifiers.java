package net.Chidoziealways.everythingjapanese.worldgen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_PYRITE_ORE = registerKey("add_pyrite_ore");

    public static final ResourceKey<BiomeModifier> ADD_NEPHRITE_ORE = registerKey("add_nephrite_ore");

    public static final ResourceKey<BiomeModifier> ADD_HINOKI_TREE = registerKey("add_hinoki_tree");

    public static final ResourceKey<BiomeModifier> ADD_YAMAZAKI_BERRY_BUSH = registerKey("add_yamazaki_berry_bush");

    public static final ResourceKey<BiomeModifier> SPAWN_TRICERATOPS = registerKey("spawn_triceratops");

    public static final ResourceKey<BiomeModifier> SPAWN_SIKA_DEER = registerKey("spawn_sika_deer");


    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_PYRITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.PYRITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NEPHRITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.NEPHRITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_HINOKI_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.TAIGA)/* Use for Reference: , biomes.getOrThrow(Biomes.Any_Biome)*/),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.HINOKI_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_YAMAZAKI_BERRY_BUSH, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST), biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.PLAINS)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.YAMAZAKI_BERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(SPAWN_TRICERATOPS, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BAMBOO_JUNGLE), biomes.getOrThrow(Biomes.PLAINS)),
                WeightedList.of(new MobSpawnSettings.SpawnerData(ModEntities.TRICERATOPS.get(), 3, 25))));

        context.register(SPAWN_SIKA_DEER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BAMBOO_JUNGLE), biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.TAIGA), biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS), biomes.getOrThrow(Biomes.JUNGLE)),
                WeightedList.of(new MobSpawnSettings.SpawnerData(ModEntities.SIKA_DEER.get(), 9, 25))));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name){
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
    }
}
