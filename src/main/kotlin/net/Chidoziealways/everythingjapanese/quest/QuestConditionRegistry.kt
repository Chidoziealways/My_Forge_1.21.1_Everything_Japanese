package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingjapanese.quest.QuestCondition

object QuestConditionRegistry {
    private val handlers = mutableMapOf<String, QuestCondition>()

    fun register(id: String, condition: QuestCondition) {
        handlers[id] = condition
    }

    fun get(id: String): QuestCondition? = handlers[id]
}