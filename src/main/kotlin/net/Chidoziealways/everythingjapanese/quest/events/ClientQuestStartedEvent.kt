package net.Chidoziealways.everythingjapanese.quest.events

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.client.player.LocalPlayer
import net.neoforged.bus.api.Event

data class ClientQuestStartedEvent(
    val player: LocalPlayer,
    val quest: Quest
): Event()