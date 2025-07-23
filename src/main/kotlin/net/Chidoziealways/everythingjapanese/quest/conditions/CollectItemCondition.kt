package net.Chidoziealways.everythingjapanese.quest.conditions

import net.Chidoziealways.everythingjapanese.quest.ParsedCondition
import net.Chidoziealways.everythingjapanese.quest.Quest
import net.Chidoziealways.everythingjapanese.quest.QuestCondition
import net.Chidoziealways.everythingjapanese.quest.QuestStage
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.registries.ForgeRegistries

object CollectItemCondition: QuestCondition {
    override fun isComplete(
        player: ServerPlayer,
        quest: Quest,
        stage: QuestStage
    ): Boolean {
        val itemId = ParsedCondition.parse(stage.completeWhen).args.firstOrNull() ?: return false
        val item = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(itemId)) ?: return false
        return player.inventory.countItem(item) > 0
    }
}