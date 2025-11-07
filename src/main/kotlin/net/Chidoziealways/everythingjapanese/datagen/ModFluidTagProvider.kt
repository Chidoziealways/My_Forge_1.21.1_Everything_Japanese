package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.fluids.ModFluids
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.world.level.material.Fluid
import java.util.concurrent.CompletableFuture

class ModFluidTagProvider(
    output: PackOutput,
    future: CompletableFuture<HolderLookup.Provider>
): IntrinsicHolderTagsProvider<Fluid>(
    output,
    Registries.FLUID,
    future,
    { fluid -> fluid.builtInRegistryHolder().key() },
    JAPANESE_MOD_ID
) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(ModTags.Fluids.BLOOD).add(ModFluids.BLOOD.get()).add(ModFluids.FLOWING_BLOOD.get())
    }
}