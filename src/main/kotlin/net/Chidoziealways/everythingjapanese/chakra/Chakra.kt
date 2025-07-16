package net.Chidoziealways.everythingjapanese.chakra

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import java.util.function.BiFunction
import java.util.function.Consumer
import java.util.function.Function
import kotlin.math.min

class Chakra : IChakra {
    override var chakra = 100f // Default Chakra amount
    private var maxChakra = 100

    constructor(chakra: Float, maxChakra: Int) {
        this.chakra = chakra
        this.maxChakra = maxChakra
    }

    constructor()

    override fun addChakra(amount: Float) {
        chakra = chakra + amount
    }

    override fun subtractChakra(amount: Float) {
        chakra = chakra - amount
    }

    override fun getMaxChakra(): Int {
        return maxChakra
    }

    override fun setMaxChakra(max: Int) {
        this.maxChakra = max
    }

    override fun updateMaxChakraBasedOnXP(xpLevel: Int) {
        this.maxChakra = 100 + (xpLevel * 10)
    }

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart<Tag?>(NbtOps.INSTANCE, this)
            .resultOrPartial(Consumer { error: String? -> System.err.println("Failed to Serialize Chakra: " + error) })
            .orElse(CompoundTag()) as CompoundTag
    }

    override fun deserializeNBT(nbt: CompoundTag?) {
        CODEC.decode<Tag?>(NbtOps.INSTANCE, nbt)
            .resultOrPartial(Consumer { error: String? -> System.err.println("Failed to Deserialize Chakra: " + error) })
            .ifPresent(Consumer { iChakraTagPair: Pair<IChakra?, Tag?>? ->
                val capability: IChakra = iChakraTagPair!!.getFirst()!!
                this.chakra = capability.chakra
                this.maxChakra = capability.getMaxChakra()
            })
    }

    companion object {
        val CODEC: Codec<IChakra> =
            RecordCodecBuilder.create<IChakra?>(Function { iChakraInstance: RecordCodecBuilder.Instance<IChakra?>? ->
                iChakraInstance!!.group<Float?, Int?>(
                    Codec.FLOAT.fieldOf("chakra").forGetter<IChakra?>(Function { obj: IChakra? -> obj!!.chakra }),
                    Codec.INT.fieldOf("maxChakra")
                        .forGetter<IChakra?>(Function { obj: IChakra? -> obj!!.getMaxChakra() })
                ).apply<IChakra?>(
                    iChakraInstance,
                    BiFunction { chakra: Float?, maxChakra: Int? -> Chakra(chakra!!, maxChakra!!) })
            })
    }
}
