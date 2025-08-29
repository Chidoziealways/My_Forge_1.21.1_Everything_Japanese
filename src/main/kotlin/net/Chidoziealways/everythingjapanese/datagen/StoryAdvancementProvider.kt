package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.core.HolderLookup
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import java.util.function.Consumer

class StoryAdvancementProvider : AdvancementSubProvider {
    override fun generate(
        registries: HolderLookup.Provider,
        saver: Consumer<AdvancementHolder?>
    ) {
        val pyrite = Advancement.Builder.advancement()
            .parent(ResourceLocation.withDefaultNamespace("story/smelt_iron"))
            .display(
                ModItems.PYRITE_INGOT,
                Component.literal("Fake Gold!"),
                Component.literal("Smelt some Raw Pyrite"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion("pyrite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PYRITE_INGOT))
            .save(saver, "story/smelt_pyrite")

        val nephrite = Advancement.Builder.advancement()
            .parent(pyrite)
            .display(
                ModItems.NEPHRITE,
                Component.literal("Are these Emeralds?"),
                Component.literal("Acquire Nephrite"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion("nephrite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NEPHRITE))
            .save(saver, "story/nephrite")
    }
}
