package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DataMapProvider
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable
import java.util.concurrent.CompletableFuture

class ModDataMapProvider(packOutput: PackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>): DataMapProvider(packOutput, lookupProvider) {
    override fun gather(provider: HolderLookup.Provider) {
        builder(NeoForgeDataMaps.COMPOSTABLES)
            .add(JModItems.SUSHI.builtInRegistryHolder(), Compostable(1f, true), false)
            .add(JModItems.UDON.builtInRegistryHolder(), Compostable(1f, true), false)
            .add(JModItems.RICE.builtInRegistryHolder(), Compostable(1f, true), false)
            .add(JModItems.RICE_SEEDS.builtInRegistryHolder(), Compostable(1f, true), false)
            .add(JModItems.RAMEN.builtInRegistryHolder(), Compostable(1f, true), false)
            .add(JModItems.RAW_RICE.builtInRegistryHolder(), Compostable(1f, true), false)
            .add(JModItems.YAMAZAKI_BERRIES.builtInRegistryHolder(), Compostable(1f, true), false)

        builder(NeoForgeDataMaps.STRIPPABLES)
            .add(JModBlocks.HINOKI_MARUTA.builtInRegistryHolder(), Strippable(JModBlocks.STRIPPED_HINOKI_MARUTA), false)
            .add(JModBlocks.HINOKI_MOKUZAI.builtInRegistryHolder(), Strippable(JModBlocks.STRIPPED_HINOKI_MOKUZAI), false)

        builder(NeoForgeDataMaps.FURNACE_FUELS)
            .add(JModItems.INCENSE.builtInRegistryHolder(), FurnaceFuel(20000), false)
            .add(JModItems.DIESEL.builtInRegistryHolder(), FurnaceFuel(1200), false)
    }
}