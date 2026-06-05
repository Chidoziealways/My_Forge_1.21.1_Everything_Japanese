package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player

object MasteryHandler {
    fun addMastery(player: ServerPlayer, jutsuId: Identifier, amount: Float) {
        val jutsu =  player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
        jutsu?.addMastery(jutsuId.path, amount, player)
    }

    fun getMastery(player: Player, jutsuId: Identifier): Float {
        val jutsu = player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
        val mastery = jutsu?.getMastery()[jutsuId.path] ?: 0f
        return mastery
    }
}
