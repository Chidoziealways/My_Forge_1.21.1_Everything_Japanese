package net.Chidoziealways.everythingjapanese.jutsu

import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.AutoRegisterCapability

@AutoRegisterCapability
interface IJutsuCapability {
    // Adds a Jutsu to the learned list
    fun learnJutsu(jutsuId: String?)

    // Checks if a Jutsu has been learned
    fun hasLearnedJutsu(jutsuId: String?): Boolean

    // Gets all learned Jutsus
    fun getLearnedJutsus(): MutableSet<String?>?

    fun serializeNBT(): CompoundTag

    fun deserializeNBT(nbt: CompoundTag)

    fun getSelectedJutsu(): String?

    fun cycleJutsu()
}
