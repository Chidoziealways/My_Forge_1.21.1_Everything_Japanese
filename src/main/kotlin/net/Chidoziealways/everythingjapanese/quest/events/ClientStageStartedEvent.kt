package net.Chidoziealways.everythingjapanese.quest.events

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.Chidoziealways.everythingjapanese.quest.QuestStage
import net.minecraft.client.player.LocalPlayer
import net.neoforged.bus.api.Event

data class ClientStageStartedEvent(
    val player: LocalPlayer,
    val quest: Quest,
    val stage: QuestStage
): Event()