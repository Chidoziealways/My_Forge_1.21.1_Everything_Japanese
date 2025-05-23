package net.Chidoziealways.everythingjapanese.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.packs.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModAdvancementProvider {
    public static ForgeAdvancementProvider create(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries, ExistingFileHelper fileHelper) {
        return new ForgeAdvancementProvider(
                pOutput,
                pRegistries,
                fileHelper,
                List.of(
                        new HellAdvancementProvider(),
                        new StoryAdvancementProvider()
                )
        );
    }
}
