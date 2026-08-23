package net.Chidoziealways.everythingjapanese.chakra

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer

interface IChakra {
    fun addChakra(amount: Float, player: ServerPlayer)
    fun subtractChakra(amount: Float, player: ServerPlayer) : Boolean
    fun getMaxChakra(): Int
    fun setMaxChakra(max: Int)
    fun getCurrentChakra(): Float
    fun setCurrentChakra(chakra: Float, player: ServerPlayer)
    fun setCurrentChakra(chakra: Float)
    fun serializeNBT(): CompoundTag?
    fun deserializeNBT(nbt: CompoundTag?)
    fun updateMaxChakraBasedOnXP(xpLevel: Int, player: ServerPlayer)
}
