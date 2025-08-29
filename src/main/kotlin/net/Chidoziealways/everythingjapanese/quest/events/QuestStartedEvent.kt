package net.Chidoziealways.everythingjapanese.quest.events

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.Event

data class QuestStartedEvent(
    val player: ServerPlayer,
    val quest: Quest
): Event()