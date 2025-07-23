package net.Chidoziealways.everythingjapanese.quest

data class ParsedCondition(val type: String, val args: List<String>) {
    companion object {
        fun parse(raw: String): ParsedCondition {
            val split = raw.split(":", limit = 2)
            val typea = split[0]
            val argsa = if (split.size > 1) listOf(split[1]) else emptyList()
            println("ParsedCondition(type=$typea, args=$argsa)")
            return ParsedCondition(typea, argsa)
        }
    }
}