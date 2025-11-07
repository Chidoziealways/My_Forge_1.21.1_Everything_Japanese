package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.structure.ModStructures
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.critereon.ChangeDimensionTrigger
import net.minecraft.advancements.critereon.LocationPredicate
import net.minecraft.advancements.critereon.PlayerTrigger
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.structure.Structure
import java.util.function.Consumer

class HellAdvancementProvider : AdvancementSubProvider {
    override fun generate(
        registries: HolderLookup.Provider,
        saver: Consumer<AdvancementHolder>
    ) {
        val holderGetter: HolderGetter<Structure> = registries.lookupOrThrow(Registries.STRUCTURE)

        val enteredHell = Advancement.Builder.advancement()
            .display(
                JModBlocks.PYRITE_BLOCK,
                Component.translatable("advancements.hell.root.title"),
                Component.translatable("advancements.hell.root.description"),
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "gui/advancements/backgrounds/hell"),
                AdvancementType.TASK,
                false,
                false,
                false
            )
            .addCriterion(
                "entered_hell",
                ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.HELL_LEVEL_KEY)
            )
            .save(saver, ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "hell/root"))

        val findTemple = Advancement.Builder.advancement()
            .parent(enteredHell)
            .display(
                Blocks.DEEPSLATE_TILES,
                Component.literal("The Devil's Domain"),
                Component.literal("Find a Hell Temple"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .rewards(AdvancementRewards.Builder.experience(100))
            .addCriterion(
                "found_temple",
                PlayerTrigger.TriggerInstance.located(
                    LocationPredicate.Builder.inStructure(holderGetter.getOrThrow(ModStructures.HELL_TEMPLE))
                )
            )
            .save(saver, ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "hell/find_temple"))
    }
}
