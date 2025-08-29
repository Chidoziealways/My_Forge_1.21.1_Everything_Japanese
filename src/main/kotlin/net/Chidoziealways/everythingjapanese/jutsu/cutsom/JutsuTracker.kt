package net.Chidoziealways.everythingjapanese.jutsu.cutsom

import net.Chidoziealways.everythingjapanese.entity.custom.ChiretsuShōProjectileEntity
import net.minecraft.world.entity.player.Player
import java.util.*

object JutsuTracker {
    private val levitatingProjectiles = mutableMapOf<UUID, MutableList<ChiretsuShōProjectileEntity>>()

    fun getAll(player: Player): List<ChiretsuShōProjectileEntity> {
        return levitatingProjectiles[player.uuid] ?: emptyList()
    }

    fun add(player: Player, projectile: ChiretsuShōProjectileEntity) {
        levitatingProjectiles.computeIfAbsent(player.uuid) { mutableListOf() }.add(projectile)
    }

    fun clear(player: Player) {
        levitatingProjectiles.remove(player.uuid)
    }

    fun has(player: Player): Boolean {
        return levitatingProjectiles.containsKey(player.uuid) && levitatingProjectiles[player.uuid]!!.isNotEmpty()
    }
}
