package net.Chidoziealways.everythingjapanese.karma

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer

interface IKarma {
    fun getKarmaAmount(): Int

    fun setKarmaAmount(karma: Int)

    fun addKarma(karma: Int)

    fun removeKarma(karma: Int)

    fun serializeNBT(): CompoundTag?
    fun deserializeNBT(nbt: CompoundTag?)
}