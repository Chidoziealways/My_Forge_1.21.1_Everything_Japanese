package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
//import net.Chidoziealways.everythingkorean.datagen.KModDatapackEntries
import net.minecraft.Util
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry
import net.minecraft.data.registries.RegistryPatchGenerator
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.data.event.GatherDataEvent
import thedarkcolour.kotlinforforge.common.KotlinMod
import java.util.concurrent.CompletableFuture

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object DataGenerators {
    @SubscribeEvent
    fun gatherClientData(event: GatherDataEvent.Client) {
        // Pass this factory to your RegistrySetBuilder or generator
        val generator = event.generator
        val packOutput = generator.packOutput

        val lookupFuture = RegistryPatchGenerator.createLookup(
            event.lookupProvider,
            JModDatapackEntries.BUILDER
        )/*.thenCompose { patched1 ->
            RegistryPatchGenerator.createLookup(
                CompletableFuture.completedFuture(patched1.full()),
                KModDatapackEntries.BUILDER
            )
        }*/

        val lookupProvider =
            lookupFuture.thenApply { it.full() }

        generator.addProvider(
            true,
            EnglishLanguageProvider(
                packOutput
            )
        )

        event.createProvider(::ModDataMapProvider)

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
        ) { JModDatapackEntries(packOutput, lookupProvider) }
        generator.addProvider(
            true,
            ModBlockTagProvider(packOutput, lookupProvider)
        )
        generator.addProvider(
            true,
            ModItemTagProvider(packOutput, lookupProvider)
        )
        event.createProvider(::ModEntityTagProvider)
        generator.addProvider(
            true,
            ModBiomeTagProvider(packOutput, lookupProvider)
        )
        generator.addProvider(
            true,
            ModFluidTagProvider(packOutput, lookupProvider)
        )

        generator.addProvider(true, ModModelProvider(packOutput, lookupProvider))

        generator.addProvider(
            true,
            ModGlobalLootModifierProvider(packOutput, lookupProvider)
        )

        generator.addProvider(
            true,
            ModRecipeProvider.Runner(packOutput, lookupProvider)
        )

        generator.addProvider(
            true,
            ModAdvancementProvider.create(packOutput, lookupProvider)
        )
    }

    @SubscribeEvent
    fun gatherServerData(event: GatherDataEvent.Server) {
        // Pass this factory to your RegistrySetBuilder or generator
        val generator = event.generator
        val packOutput = generator.packOutput

        val lookupProvider = event.lookupProvider
        val fLookupProvider = CompletableFuture.supplyAsync(
            { JModDatapackEntries.createLookup() },
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
            JModDatapackEntries(packOutput, fLookupProvider)
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