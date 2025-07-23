package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.eventbus.api.bus.EventBus
import net.minecraftforge.eventbus.api.event.MutableEvent
import net.minecraftforge.eventbus.internal.Event

class QuestStartedEvent(
    val player: ServerPlayer,
    val quest: Quest
): MutableEvent() {
    companion object {
        val BUS: EventBus<QuestStartedEvent> = EventBus.create(QuestStartedEvent::class.java)
    }
}