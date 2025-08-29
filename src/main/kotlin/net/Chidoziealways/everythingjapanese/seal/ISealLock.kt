package net.Chidoziealways.everythingjapanese.seal

import java.util.UUID

interface ISealLock {
    fun isLocked(): Boolean
    fun setLocked(locked: Boolean)
    fun getOwner(): UUID?
    fun setOwner(owner: UUID?)
}