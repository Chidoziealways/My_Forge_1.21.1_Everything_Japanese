package net.Chidoziealways.everythingjapanese.quest

import net.minecraft.server.level.ServerPlayer

interface QuestCondition {
    fun isComplete(player: ServerPlayer, quest: Quest, stage: QuestStage): Boolean
}