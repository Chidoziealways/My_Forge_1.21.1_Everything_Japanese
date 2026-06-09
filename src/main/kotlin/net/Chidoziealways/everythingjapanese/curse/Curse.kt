package net.Chidoziealways.everythingjapanese.curse

import net.minecraft.server.level.ServerPlayer


fun interface ActCurse {
    fun curse(player: ServerPlayer)
}
open class Curse(val displayName: String, val onActivate: ActCurse) {
    constructor(dN: String) : this(dN, {}) {

    }

    open fun curse(player: ServerPlayer) {
        onActivate.curse(player)
    }
}