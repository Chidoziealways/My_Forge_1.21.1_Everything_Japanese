package net.Chidoziealways.everythingjapanese.jutsu.cutsom

import net.Chidoziealways.everythingjapanese.entity.custom.EkiretsuShōProjectileEntity
import net.minecraft.world.entity.player.Player
import java.util.*

object EkiretsuJutsuTracker {
    private val levitatingProjectiles = mutableMapOf<UUID, MutableList<EkiretsuShōProjectileEntity>>()

    fun getAll(player: Player): List<EkiretsuShōProjectileEntity> {
        return levitatingProjectiles[player.uuid] ?: emptyList()
    }

    fun add(player: Player, projectile: EkiretsuShōProjectileEntity) {
        levitatingProjectiles.computeIfAbsent(player.uuid) { mutableListOf() }.add(projectile)
    }

    fun clear(player: Player) {
        levitatingProjectiles.remove(player.uuid)
    }

    fun has(player: Player): Boolean {
        return levitatingProjectiles.containsKey(player.uuid) && levitatingProjectiles[player.uuid]!!.isNotEmpty()
    }
}
