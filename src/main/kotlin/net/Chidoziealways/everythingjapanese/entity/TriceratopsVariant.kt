package net.Chidoziealways.everythingjapanese.entity

import java.util.*
import java.util.function.ToIntFunction

enum class TriceratopsVariant(val id: Int) {
    GRAY(0),
    GREEN(1);

    companion object {
        private val BY_ID: Array<TriceratopsVariant?> =
            Arrays.stream<TriceratopsVariant?>(entries.toTypedArray()).sorted(
                Comparator
                    .comparingInt<TriceratopsVariant?>(ToIntFunction { obj: TriceratopsVariant? -> obj!!.id })
            ).toArray { Array(2, TriceratopsVariant::byId) }

        fun byId(id: Int): TriceratopsVariant? {
            return BY_ID[id % BY_ID.size]
        }
    }
}
