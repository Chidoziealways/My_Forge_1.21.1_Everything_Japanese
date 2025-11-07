package net.Chidoziealways.everythingjapanese.money

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer

interface IMoneyCapability {
    fun getMaxMoney(): Int?
    fun setMaxMoney(amount: Int)
    fun getMoney(): Int
    fun addMoney(amount: Int, player: ServerPlayer)
    fun addMoney(amount: Int)
    fun removeMoney(amount: Int, player: ServerPlayer)
    fun removeMoney(amount: Int)
    fun setMoney(amount: Int, player: ServerPlayer)
    fun setMoney(amount: Int)
    fun serializeNBT(): CompoundTag
    fun deserializeNBT(nbt: CompoundTag)
}