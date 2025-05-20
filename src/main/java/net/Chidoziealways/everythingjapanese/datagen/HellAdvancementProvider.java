package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider.AdvancementGenerator;

import java.util.function.Consumer;

public class HellAdvancementProvider implements AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        AdvancementHolder enteredHell = Advancement.Builder.advancement()
                .display(
                        ModBlocks.PYRITE_BLOCK.get(),
                        Component.translatable("advancements.hell.root.title"),
                        Component.translatable("advancements.hell.root.description"),
                        ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "gui/advancements/backgrounds/hell"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("entered_hell", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.HELL_LEVEL_KEY))
                .save(saver, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "hell/root"));


    }
}
