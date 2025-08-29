package net.Chidoziealways.everythingjapanese.jutsu

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer

interface IJutsuCapability {
    // Adds a Jutsu to the learned list
    fun learnJutsu(jutsuId: String, player: ServerPlayer)

    // Checks if a Jutsu has been learned
    fun hasLearnedJutsu(jutsuId: String): Boolean

    // Gets all learned Jutsus
    fun getLearnedJutsus(): MutableSet<String>

    fun getMastery(jutsuId: String): Float

    fun addMastery(jutsuId: String, amount: Float, player: ServerPlayer)

    fun setMastery(masteryMap: MutableMap<String, Float>)

    fun getMastery(): Map<String, Float>

    fun serializeNBT(): CompoundTag

    fun deserializeNBT(nbt: CompoundTag)

    fun getSelectedJutsu(): String

    fun setSelectedJutsu(jutsu: String)

    fun setLearnedJutsus(jutsus: MutableSet<String>)

    fun cycleJutsu(player: ServerPlayer)
}
