package net.Chidoziealways.everythingjapanese.seal

import java.util.UUID

class SealLock: ISealLock {
    private var locked = false
    private var owner: UUID? = null

    override fun isLocked(): Boolean = locked

    override fun setLocked(locked: Boolean) {
        this.locked = locked
    }

    override fun getOwner(): UUID? = owner

    override fun setOwner(owner: UUID?) {
        this.owner = owner
    }
}