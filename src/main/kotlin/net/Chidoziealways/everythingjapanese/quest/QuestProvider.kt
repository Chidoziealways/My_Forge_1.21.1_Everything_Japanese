package net.Chidoziealways.everythingjapanese.quest

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional

class QuestProvider: ICapabilityProvider {
    private val instance: IQuestCapability = QuestCapability()
    private val optional: LazyOptional<IQuestCapability?> =
        LazyOptional.of<IQuestCapability?> { instance }

    override fun <T : Any?> getCapability(cap: Capability<T?>, side: Direction?): LazyOptional<T?> {
        return if (cap == ModCapabilities.QUEST_CAPABILITY) optional.cast<T?>() else LazyOptional.empty<T?>()
    }

    fun serializeNBT(): CompoundTag {
        return instance.serializeNBT()
    }

    fun deserializeNBT(nbt: CompoundTag) {
        instance.deserializeNBT(nbt)
    }
}