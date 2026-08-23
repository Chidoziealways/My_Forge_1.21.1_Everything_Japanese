package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.structure.ModStructures
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.predicates.LocationPredicate
import net.minecraft.advancements.triggers.PlayerTrigger
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import java.util.function.Consumer

class AdventureAdvancementProvider : AdvancementSubProvider {
    /**
     *
     * Generates advancements for the adventure advancement tree with root `minecraft:adventure/root`
     *
     * @param registries - a lookup provider to get datagen registries
     * @param output - Where it will be saved
     */
    override fun generate(registries: HolderLookup.Provider, output: Consumer<AdvancementHolder> ) {
        // Things needed (HolderGetters)
        val structures = registries.lookupOrThrow(Registries.STRUCTURE)

        // Actual Advancements

        // The Shrine Advancement
        val shintoShrine = Advancement.Builder.advancement()
            .parent(Identifier.withDefaultNamespace("adventure/root")) // Parent using an RL (may break)
            .display(
                JModBlocks.HINOKI_BAN,
                Component.literal("The Shrine"),
                Component.literal("Locate a Shinto Shrine"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            ) // Display
            .rewards(AdvancementRewards.Builder.experience(500)) // Give 500 XP points upon completion
            .addCriterion(
                "found_shrine",
                PlayerTrigger.TriggerInstance.located(
                    LocationPredicate.Builder.inStructure(structures.getOrThrow(ModStructures.SHINTO_SHRINE))
                )
            )
            .save(output, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "adventure/shinto_shrine"))

        Advancement.Builder.advancement()
            .parent(shintoShrine)
            .display(
                JModBlocks.STONE_LANTERN,
                Component.literal("Tea Time"),
                Component.literal("Locate a Tea House"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .rewards(AdvancementRewards.Builder.experience(1000))
            .addCriterion(
                "found_tea_house",
                PlayerTrigger.TriggerInstance.located(
                    LocationPredicate.Builder.inStructure(structures.getOrThrow(ModStructures.TEA_HOUSE))
                )
            )
            .save(output, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "adventure/tea_house"))
        Advancement.Builder.advancement()
            .parent(shintoShrine)
            .display(
                JModBlocks.TATAMI_MAT,
                Component.literal("Time to train"),
                Component.literal("Find & Enter a Dojo"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .rewards(AdvancementRewards.Builder.experience(2000))
            .addCriterion(
                "found_dojo",
                PlayerTrigger.TriggerInstance.located(
                    LocationPredicate.Builder.inStructure(structures.getOrThrow(ModStructures.DOJO))
                )
            )
            .save(output, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "adventure/dojo"))
    }
}