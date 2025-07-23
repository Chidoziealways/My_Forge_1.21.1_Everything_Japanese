package net.Chidoziealways.everythingjapanese.quest

import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.common.capabilities.AutoRegisterCapability

@AutoRegisterCapability
interface IQuestCapability {
    fun getQuest(): Quest?

    fun setQuest(id: ResourceLocation)

    fun serializeNBT(): CompoundTag

    var currentQuestId: ResourceLocation?

    fun deserializeNBT(nbt: CompoundTag)

    fun getQuestProgress(): Map<ResourceLocation, QuestProgress>

    fun giveQuest(id: ResourceLocation, player: ServerPlayer): Boolean

    fun finishQuest(id: ResourceLocation, player: ServerPlayer): Boolean
}