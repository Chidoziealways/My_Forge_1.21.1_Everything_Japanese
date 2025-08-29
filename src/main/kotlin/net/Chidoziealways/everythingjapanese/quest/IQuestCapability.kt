package net.Chidoziealways.everythingjapanese.quest

import net.minecraft.client.player.LocalPlayer
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer

interface IQuestCapability {
    fun getQuest(): Quest?

    fun setQuest(id: ResourceLocation)

    fun serializeNBT(): CompoundTag

    var currentQuestId: ResourceLocation?
    var completedQuests: MutableSet<ResourceLocation>

    fun deserializeNBT(nbt: CompoundTag)

    fun getQuestProgress(): Map<ResourceLocation, QuestProgress>

    fun giveQuest(id: ResourceLocation, player: ServerPlayer): Boolean

    fun giveQuest(id: ResourceLocation, player: LocalPlayer): Boolean

    fun finishQuest(id: ResourceLocation, player: ServerPlayer): Boolean

    fun finishQuest(id: ResourceLocation, player: LocalPlayer): Boolean
}