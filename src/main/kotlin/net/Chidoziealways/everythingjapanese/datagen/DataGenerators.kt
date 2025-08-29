package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.Util
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.data.event.GatherDataEvent
import thedarkcolour.kotlinforforge.common.KotlinMod
import java.util.concurrent.CompletableFuture

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object DataGenerators {
    @SubscribeEvent
    fun gatherClientData(event: GatherDataEvent.Client) {
        // Pass this factory to your RegistrySetBuilder or generator
        val generator = event.generator
        val packOutput = generator.packOutput

        val lookupProvider = event.lookupProvider
        val fLookupProvider = CompletableFuture.supplyAsync(
            { ModDatapackEntries.Companion.createLookup() },
            Util.backgroundExecutor()
        )

        generator.addProvider(
            true,
            EnglishLanguageProvider(
                packOutput
            )
        )

        generator.addProvider(
            true,
            JapaneseLanguageProvider(
                packOutput
            )
        )

        generator.addProvider(
            true,
            KoreanLanguageProvider(
                packOutput
            )
        )

        generator.addProvider(
            true,
            SimplifiedChineseLanguageProvider(
                packOutput
            )
        )

        generator.addProvider(
            true,
            TraditionalChineseLanguageProvider(
                packOutput
            )
        )

        generator.addProvider(
            true,
            LootTableProvider(
                packOutput,
                mutableSetOf(),
                listOf(
                    SubProviderEntry({ registries ->
                        ModBlockLootTableProvider(registries)
                    }, LootContextParamSets.BLOCK),
                    SubProviderEntry( {registries ->
                        ModChestLootTableProvider(registries)
                    }, LootContextParamSets.CHEST)
                ),
                lookupProvider
            )
        )

        generator.addProvider(
            true
        ) { ModDatapackEntries(packOutput, lookupProvider) }
        generator.addProvider(
            true,
            ModBlockTagProvider(packOutput, fLookupProvider)
        )
        generator.addProvider(
            true,
            ModItemTagProvider(packOutput, fLookupProvider)
        )
        generator.addProvider(
            true,
            ModBiomeTagProvider(packOutput, fLookupProvider)
        )
        generator.addProvider(
            true,
            ModFluidTagProvider(packOutput, fLookupProvider)
        )

        generator.addProvider(true, ModModelProvider(packOutput, fLookupProvider))

        generator.addProvider(
            true,
            ModGlobalLootModifierProvider(packOutput, lookupProvider)
        )

        generator.addProvider(
            true,
            ModRecipeProvider.Runner(packOutput, fLookupProvider)
        )

        generator.addProvider(
            true,
            ModAdvancementProvider.create(packOutput, fLookupProvider)
        )
    }

    @SubscribeEvent
    fun gatherServerData(event: GatherDataEvent.Server) {
        // Pass this factory to your RegistrySetBuilder or generator
        val generator = event.generator
        val packOutput = generator.packOutput

        val lookupProvider = event.lookupProvider
        val fLookupProvider = CompletableFuture.supplyAsync(
            { ModDatapackEntries.Companion.createLookup() },
            Util.backgroundExecutor()
        )

        generator.addProvider(
            true,
            LootTableProvider(
                packOutput,
                mutableSetOf(),
                listOf(
                    SubProviderEntry({ registries ->
                        ModBlockLootTableProvider(registries)
                    }, LootContextParamSets.BLOCK),
                    SubProviderEntry( {registries ->
                        ModChestLootTableProvider(registries)
                    }, LootContextParamSets.CHEST)
                ),
                lookupProvider
            )
        )

        generator.addProvider(
            true,
            ModBlockTagProvider(packOutput, lookupProvider)
        )
        generator.addProvider(
            true,
            ModItemTagProvider(packOutput, lookupProvider)
        )
        generator.addProvider(
            true,
            ModBiomeTagProvider(packOutput, fLookupProvider)
        )

        generator.addProvider(
            true,
            ModDatapackEntries(packOutput, fLookupProvider)
        )

        generator.addProvider(true, ModModelProvider(packOutput, fLookupProvider))

        generator.addProvider(
            true,
            ModGlobalLootModifierProvider(packOutput, lookupProvider)
        )

        generator.addProvider(
            true,
            ModRecipeProvider.Runner(packOutput, fLookupProvider)
        )

        generator.addProvider(
            true,
            ModAdvancementProvider.create(packOutput, fLookupProvider)
        )
    }
}
