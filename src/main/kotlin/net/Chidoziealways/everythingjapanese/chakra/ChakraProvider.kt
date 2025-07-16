package net.Chidoziealways.everythingjapanese.chakra

import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.common.util.NonNullSupplier
import java.util.function.Consumer

class ChakraProvider : ICapabilityProvider {
    private val instance = Chakra()
    private val optional: LazyOptional<IChakra?> = LazyOptional.of<IChakra?>(NonNullSupplier { instance })

    override fun <T> getCapability(cap: Capability<T?>, side: Direction?): LazyOptional<T?> {
        if (cap == ModCapabilities.CHAKRA_CAPABILITY) {
            return optional.cast<T?>()
        }

        return LazyOptional.empty<T?>()
    }

    fun serializeNBT(): CompoundTag {
        return Chakra.Companion.CODEC.encodeStart<Tag?>(NbtOps.INSTANCE, instance)
            .resultOrPartial(Consumer { error: String? -> System.err.println("Failed to Serialize Chakra: " + error) })
            .orElse(CompoundTag()) as CompoundTag
    }

    fun deserializeNBT(nbt: CompoundTag?) {
        Chakra.Companion.CODEC.decode<Tag?>(NbtOps.INSTANCE, nbt)
            .resultOrPartial(Consumer { error: String? -> System.err.println("Failed to Deserialize Chakra: " + error) })
            .ifPresent(Consumer { iChakraTagPair: Pair<IChakra?, Tag?>? ->
                val capability: IChakra = iChakraTagPair!!.getFirst()!!
                instance.chakra = capability.chakra
                instance.setMaxChakra(capability.getMaxChakra())
            })
    }
}
