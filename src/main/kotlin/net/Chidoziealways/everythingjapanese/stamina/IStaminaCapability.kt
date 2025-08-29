package net.Chidoziealways.everythingjapanese.stamina

import net.minecraft.client.player.LocalPlayer
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer

interface IStaminaCapability {
    //Max Stamina
    fun setMaxStamina(max: Int)
    fun getMaxStamina(): Int

    //Current Stamina
    fun setStamina(stamina: Float)
    fun getStamina(): Float

    //Stamina Modifiers
    //Server
    fun decreaseStamina(amount: Float, player: ServerPlayer)
    fun increaseStamina(amount: Float, player: ServerPlayer)
    //Client
    fun decreaseStamina(amount: Float, player: LocalPlayer)
    fun increaseStamina(amount: Float, player: LocalPlayer)

    //Serialization & Deserialization
    fun serializeNBT(): CompoundTag
    fun deserializeNBT(nbt: CompoundTag)
}