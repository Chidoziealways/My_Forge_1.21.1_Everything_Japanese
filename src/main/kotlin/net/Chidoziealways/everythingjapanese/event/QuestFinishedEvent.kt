package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.eventbus.api.bus.EventBus
import net.minecraftforge.eventbus.api.event.MutableEvent

class QuestFinishedEvent(
    val player: ServerPlayer,
    val quest: Quest
): MutableEvent() {
    companion object {
        val BUS: EventBus<QuestFinishedEvent> = EventBus.create(QuestFinishedEvent::class.java)
    }
}