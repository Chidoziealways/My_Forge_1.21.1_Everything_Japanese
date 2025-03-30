package net.Chidoziealways.everythingjapanese.worldgen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PYRITE_ORE_PLACED_KEY = registerKey("pyrite_ore_placed");

    public static final ResourceKey<PlacedFeature> NEPHRITE_ORE_PLACED_KEY = registerKey("nephrite_ore_placed");

    public static final ResourceKey<PlacedFeature> HINOKI_PLACED_KEY = registerKey("hinoki_placed");

    public static final ResourceKey<PlacedFeature> YAMAZAKI_BERRY_BUSH_PLACED_KEY = registerKey("yamazaki_berry_bush_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PYRITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PYRITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(18,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, NEPHRITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NEPHRITE_ORE_KEY),
                ModOrePlacement.rareOrePlacement(5,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(50))));

        register(context, HINOKI_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HINOKI_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.5f, 2),
                        ModBlocks.HINOKI_NAEGI.get()));

        register(context, YAMAZAKI_BERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.YAMAZAKI_BERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    }


    public static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
