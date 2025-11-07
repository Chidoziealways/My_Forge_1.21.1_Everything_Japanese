package net.Chidoziealways.everythingjapanese.worldgen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
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

object JModConfiguredFeatures {
    val PYRITE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("pyrite_ore")

    val NEPHRITE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("nephrite_ore")

    val HINOKI_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("hinoki")

    val YAMAZAKI_BERRY_BUSH_KEY: ResourceKey<ConfiguredFeature<*, *>> =
        registerKey("yamazaki_berry_bush")

    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val stoneReplaceables: RuleTest = TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepslateReplaceables: RuleTest = TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)

        val pyriteOres: kotlin.collections.MutableList<OreConfiguration.TargetBlockState?> =
            mutableListOf(
                OreConfiguration.target(stoneReplaceables, JModBlocks.PYRITE_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, JModBlocks.PYRITE_DEEPSLATE_ORE.defaultBlockState())
            )

        val nephriteOres: kotlin.collections.MutableList<OreConfiguration.TargetBlockState?> =
            mutableListOf(
                OreConfiguration.target(stoneReplaceables, JModBlocks.NEPHRITE_ORE.defaultBlockState()),
                OreConfiguration.target(
                    deepslateReplaceables,
                    JModBlocks.NEPHRITE_DEEPSLATE_ORE.defaultBlockState()
                )
            )

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
                BlockStateProvider.simple(JModBlocks.HINOKI_MARUTA),
                DarkOakTrunkPlacer(6, 2, 1),

                BlockStateProvider.simple(JModBlocks.HINOKI_HA),
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
                        JModBlocks.YAMAZAKI_BERRY_BUSH.defaultBlockState()
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
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, name)
        )
    }

    private fun <FC : FeatureConfiguration?, F : Feature<FC?>?> register(
        context: BootstrapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>, feature: F?, configuration: FC?
    ) {
        context.register(key, ConfiguredFeature<FC?, F?>(feature, configuration))
    }
}
