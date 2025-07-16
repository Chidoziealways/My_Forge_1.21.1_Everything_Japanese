package net.Chidoziealways.everythingjapanese.chakra

import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.AutoRegisterCapability

@AutoRegisterCapability
interface IChakra {
    var chakra: Float
    fun addChakra(amount: Float)
    fun subtractChakra(amount: Float)
    fun getMaxChakra(): Int
    fun setMaxChakra(max: Int)
    fun serializeNBT(): CompoundTag?
    fun deserializeNBT(nbt: CompoundTag?)
    fun updateMaxChakraBasedOnXP(xpLevel: Int)
}
