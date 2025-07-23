package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.quest.Quest
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.Util
import net.minecraft.core.Cloner
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraftforge.common.data.ForgeAdvancementProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.registries.RegistryBuilder
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod
import java.util.List
import java.util.concurrent.CompletableFuture
import java.util.function.Function
import java.util.function.Supplier

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.MOD)
object DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val factory = Cloner.Factory()
        factory.addCodec(ModRegistries.QUEST, Quest.QUEST_CODEC)
        // Pass this factory to your RegistrySetBuilder or generator


        val generator = event.generator
        val packOutput = generator.packOutput
        val existingFileHelper = event.existingFileHelper

        val lookupProvider = event.lookupProvider
        val fLookupProvider = CompletableFuture.supplyAsync<HolderLookup.Provider?>(
            Supplier { ModDatapackEntries.Companion.createLookup() },
            Util.backgroundExecutor()
        )

        generator.addProvider(
            event.includeServer(),
            LootTableProvider(
                packOutput,
                mutableSetOf(),
                listOf(
                    SubProviderEntry({ registries ->
                        ModBlockLootTableProvider(registries)
                    }, LootContextParamSets.BLOCK)
                ),
                lookupProvider
            )
        )

        generator.addProvider<ModBlockTagProvider?>(
            event.includeServer(),
            ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper)
        )
        generator.addProvider<ModItemTagProvider?>(
            event.includeServer(),
            ModItemTagProvider(packOutput, lookupProvider, existingFileHelper)
        )
        generator.addProvider<ModBiomeTagProvider?>(
            event.includeServer(),
            ModBiomeTagProvider(packOutput, fLookupProvider, existingFileHelper)
        )

        generator.addProvider<ModDatapackEntries?>(
            event.includeServer(),
            ModDatapackEntries(packOutput, fLookupProvider)
        )

        generator.addProvider<ModModelProvider?>(event.includeClient(), ModModelProvider(packOutput))

        generator.addProvider<ModGlobalLootModifierProvider?>(
            event.includeServer(),
            ModGlobalLootModifierProvider(packOutput, lookupProvider)
        )

        generator.addProvider<ModRecipeProvider.Runner?>(
            event.includeServer(),
            ModRecipeProvider.Runner(packOutput, fLookupProvider)
        )

        generator.addProvider<ForgeAdvancementProvider?>(
            event.includeServer(),
            ModAdvancementProvider.create(packOutput, fLookupProvider, existingFileHelper)
        )
    }
}
