package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class StoryAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        AdvancementHolder pyrite = Advancement.Builder.advancement()
                .parent(ResourceLocation.withDefaultNamespace("story/smelt_iron"))
                .display(
                        ModItems.PYRITE_INGOT.get(),
                        Component.literal("Fake Gold!"),
                        Component.literal("Smelt some Raw Pyrite"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("pyrite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PYRITE_INGOT.get()))
                .save(saver, "story/smelt_pyrite");

        AdvancementHolder nephrite = Advancement.Builder.advancement()
                .parent(pyrite)
                .display(
                        ModItems.NEPHRITE.get(),
                        Component.literal("Are these Emeralds?"),
                        Component.literal("Acquire Nephrite"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("nephrite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NEPHRITE.get()))
                .save(saver, "story/nephrite");
    }
}
