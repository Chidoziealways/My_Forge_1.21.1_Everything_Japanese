package net.Chidoziealways.everythingjapanese.curse

import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import java.util.UUID

object CurseDialogManager {
    private val pendingCurses = mutableMapOf<UUID, PendingCurse>()

    data class PendingCurse(
        val targetName: String,
        val curseID: Identifier
    )

    fun add(player: ServerPlayer, pending: PendingCurse) {
        pendingCurses[player.uuid] = pending
    }

    fun consume(player: ServerPlayer): PendingCurse? {
        return pendingCurses.remove(player.uuid)
    }
}