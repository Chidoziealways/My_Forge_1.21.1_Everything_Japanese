package net.Chidoziealways.everythingjapanese.worldgen;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.custom.YamazakiBerryBushBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PYRITE_ORE_KEY = registerKey("pyrite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NEPHRITE_ORE_KEY = registerKey("nephrite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HINOKI_KEY = registerKey("hinoki");

    public static final ResourceKey<ConfiguredFeature<?, ?>> YAMAZAKI_BERRY_BUSH_KEY = registerKey("yamazaki_berry_bush");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> pyriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.PYRITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.PYRITE_DEEPSLATE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> nephriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.NEPHRITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.NEPHRITE_DEEPSLATE_ORE.get().defaultBlockState()));

        register(context, PYRITE_ORE_KEY, Feature.ORE, new OreConfiguration(pyriteOres, 10));

        register(context, NEPHRITE_ORE_KEY, Feature.ORE, new OreConfiguration(nephriteOres, 5));

        register(context, HINOKI_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(

                BlockStateProvider.simple(ModBlocks.HINOKI_MARUTA.get()),
                new DarkOakTrunkPlacer(6, 2, 1),

                BlockStateProvider.simple(ModBlocks.HINOKI_HA.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),

                new ThreeLayersFeatureSize(1, 1, 0, 1, 2,OptionalInt.empty()))

                .build());

        register(context, YAMAZAKI_BERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                BlockStateProvider.simple(ModBlocks.YAMAZAKI_BERRY_BUSH.get().defaultBlockState().setValue(YamazakiBerryBushBlock.AGE, 3))
                        ),
                        List.of(Blocks.GRASS_BLOCK)
                ));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
