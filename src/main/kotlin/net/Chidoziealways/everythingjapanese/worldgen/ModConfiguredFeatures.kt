package net.Chidoziealways.everythingjapanese.worldgen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.custom.YamazakiBerryBushBlock
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SweetBerryBushBlock
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import java.util.OptionalInt

object ModConfiguredFeatures {
    val PYRITE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("pyrite_ore")

    val NEPHRITE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("nephrite_ore")

    val HINOKI_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("hinoki")

    val YAMAZAKI_BERRY_BUSH_KEY: ResourceKey<ConfiguredFeature<*, *>> =
        registerKey("yamazaki_berry_bush")

    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val stoneReplaceables: RuleTest = TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepslateReplaceables: RuleTest = TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)

        val pyriteOres: kotlin.collections.MutableList<OreConfiguration.TargetBlockState?> =
            listOf<OreConfiguration.TargetBlockState?>(
                OreConfiguration.target(stoneReplaceables, ModBlocks.PYRITE_ORE!!.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.PYRITE_DEEPSLATE_ORE!!.defaultBlockState())
            ) as MutableList<OreConfiguration.TargetBlockState?>

        val nephriteOres: kotlin.collections.MutableList<OreConfiguration.TargetBlockState?> =
            listOf<OreConfiguration.TargetBlockState?>(
                OreConfiguration.target(stoneReplaceables, ModBlocks.NEPHRITE_ORE!!.defaultBlockState()),
                OreConfiguration.target(
                    deepslateReplaceables,
                    ModBlocks.NEPHRITE_DEEPSLATE_ORE.defaultBlockState()
                )
            ) as MutableList<OreConfiguration.TargetBlockState?>

        register(
            context,
            PYRITE_ORE_KEY,
            Feature.ORE,
            OreConfiguration(pyriteOres, 10)
        )

        register(
            context,
            NEPHRITE_ORE_KEY,
            Feature.ORE,
            OreConfiguration(nephriteOres, 5)
        )

        register(
            context,
            HINOKI_KEY,
            Feature.TREE,
            TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.HINOKI_MARUTA),
                DarkOakTrunkPlacer(6, 2, 1),

                BlockStateProvider.simple(ModBlocks.HINOKI_HA),
                DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),

                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
            )

                .build()
        )

        register(
            context,
            YAMAZAKI_BERRY_BUSH_KEY,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(
                    BlockStateProvider.simple(
                        ModBlocks.YAMAZAKI_BERRY_BUSH.defaultBlockState()
                            .setValue(SweetBerryBushBlock.AGE, 3)
                    )
                ),
                listOf<Block?>(Blocks.GRASS_BLOCK)
            )
        )
    }


    fun registerKey(name: kotlin.String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(
            net.minecraft.core.registries.Registries.CONFIGURED_FEATURE,
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
        )
    }

    private fun <FC : FeatureConfiguration?, F : Feature<FC?>?> register(
        context: BootstrapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>, feature: F?, configuration: FC?
    ) {
        context.register(key, ConfiguredFeature<FC?, F?>(feature, configuration))
    }
}
