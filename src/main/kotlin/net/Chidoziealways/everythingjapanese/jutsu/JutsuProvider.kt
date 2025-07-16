package net.Chidoziealways.everythingjapanese.jutsu

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.common.util.NonNullSupplier

class JutsuProvider : ICapabilityProvider {
    private val instance: IJutsuCapability = JutsuCapability()
    private val optional: LazyOptional<IJutsuCapability?> =
        LazyOptional.of<IJutsuCapability?>(NonNullSupplier { instance })

    override fun <T> getCapability(cap: Capability<T?>, side: Direction?): LazyOptional<T?> {
        return if (cap == ModCapabilities.JUTSU_CAPABILITY) optional.cast<T?>() else LazyOptional.empty<T?>()
    }

    // Optional serialization logic
    fun serializeNBT(): CompoundTag {
        return instance.serializeNBT()
    }

    fun deserializeNBT(nbt: CompoundTag) {
        instance.deserializeNBT(nbt)
    }
}
