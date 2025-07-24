package net.Chidoziealways.everythingjapanese.quest.conditions

import net.Chidoziealways.everythingjapanese.quest.ParsedCondition
import net.Chidoziealways.everythingjapanese.quest.Quest
import net.Chidoziealways.everythingjapanese.quest.QuestCondition
import net.Chidoziealways.everythingjapanese.quest.QuestStage
import net.minecraft.core.RegistryAccess
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.StructureManager

object LocatePlaceCondition : QuestCondition {
    override fun isComplete(player: ServerPlayer, quest: Quest, stage: QuestStage): Boolean {
        val parsed = ParsedCondition.parse(stage.completeWhen)
        val structureId = parsed.args.getOrNull(0) ?: return false

        val level = player.level() ?: return false
        val structureManager: StructureManager = level.structureManager()
        val registryAccess: RegistryAccess = level.registryAccess()
        val structureRegistry = registryAccess.lookupOrThrow(Registries.STRUCTURE)

        val resourceLocation = ResourceLocation.tryParse(structureId) ?: return false
        val structureKey = ResourceKey.create(Registries.STRUCTURE, resourceLocation)

        val structure = structureRegistry.get(structureKey).get().get() ?: return false

        // Check if the player is inside the structure
        val structureAt = structureManager.getStructureAt(player.blockPosition(), structure)
        return structureAt != null && structureAt.isValid
    }
}
