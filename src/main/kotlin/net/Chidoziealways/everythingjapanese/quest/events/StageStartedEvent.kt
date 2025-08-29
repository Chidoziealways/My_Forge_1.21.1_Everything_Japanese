package net.Chidoziealways.everythingjapanese.quest.events

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.Chidoziealways.everythingjapanese.quest.QuestStage
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.Event

data class StageStartedEvent(
    val player: ServerPlayer,
    val quest: Quest,
    val stage: QuestStage
): Event()