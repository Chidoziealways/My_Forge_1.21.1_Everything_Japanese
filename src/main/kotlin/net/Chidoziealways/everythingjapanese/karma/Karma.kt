package net.Chidoziealways.everythingjapanese.karma

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.neoforged.neoforge.network.PacketDistributor
import java.util.function.Consumer

class Karma(): IKarma {
    var karma: Int = 100

    constructor(karma: Int) : this() {
        this.karma = karma
    }

    override fun getKarmaAmount(): Int = karma

    override fun setKarmaAmount(karma: Int) {
        this.karma = karma
    }

    override fun addKarma(karma: Int) {
        this.karma += karma
    }

    override fun removeKarma(karma: Int) {
        this.karma -= karma
    }

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart(NbtOps.INSTANCE, this)
            .resultOrPartial { error: String -> System.err.println("Failed to Serialize Karma: $error") }
            .orElse(CompoundTag()) as CompoundTag
    }

    override fun deserializeNBT(nbt: CompoundTag?) {
        CODEC.decode(NbtOps.INSTANCE, nbt)
            .resultOrPartial { error: String -> System.err.println("Failed to Deserialize Karma: $error") }
            .ifPresent(Consumer { pair ->
                val capability = pair.getFirst()
                this.karma = capability.getKarmaAmount()
            })
    }

    companion object {
        val CODEC: Codec<IKarma> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("karma").forGetter { it.getKarmaAmount() }
            ).apply(instance) { karma -> Karma(karma) }
        }
    }
}