package net.Chidoziealways.everythingjapanese.chakra

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.PacketDistributor
import java.util.function.Consumer
import java.util.function.Function

class Chakra : IChakra {
    var chakra = 100f // Default Chakra amount
    private var maxChakra = 100

    constructor(chakra: Float, maxChakra: Int) {
        this.chakra = chakra
        this.maxChakra = maxChakra
    }

    constructor()

    override fun addChakra(amount: Float, player: ServerPlayer) {
        chakra = (chakra + amount).coerceAtMost(maxChakra.toFloat())
        PacketDistributor.sendToPlayer(player, ChakraSyncPacket(chakra, maxChakra))
    }

    override fun setCurrentChakra(chakra: Float, player: ServerPlayer) {
        this.chakra = chakra.coerceAtMost(maxChakra.toFloat())
        PacketDistributor.sendToPlayer(player, ChakraSyncPacket(chakra, maxChakra))
    }

    override fun setCurrentChakra(chakra: Float) {
        this.chakra = chakra.coerceAtMost(maxChakra.toFloat()).coerceAtLeast(0f)
    }

    override fun getCurrentChakra(): Float = chakra

    override fun subtractChakra(amount: Float, player: ServerPlayer) {
        chakra = (chakra - amount.coerceAtLeast(0f)).coerceAtLeast(0f)
        PacketDistributor.sendToPlayer(player, ChakraSyncPacket(chakra, maxChakra))
    }

    override fun getMaxChakra(): Int {
        return maxChakra
    }

    override fun setMaxChakra(max: Int) {
        this.maxChakra = max
    }

    override fun updateMaxChakraBasedOnXP(xpLevel: Int, player: ServerPlayer) {
        this.maxChakra = 100 + (xpLevel * 10)
        PacketDistributor.sendToPlayer(player, ChakraSyncPacket(chakra, maxChakra))
    }

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart<Tag>(NbtOps.INSTANCE, this)
            .resultOrPartial { error: String -> System.err.println("Failed to Serialize Chakra: $error") }
            .orElse(CompoundTag()) as CompoundTag
    }

    override fun deserializeNBT(nbt: CompoundTag?) {
        CODEC.decode<Tag>(NbtOps.INSTANCE, nbt)
            .resultOrPartial { error: String -> System.err.println("Failed to Deserialize Chakra: $error") }
            .ifPresent(Consumer { iChakraTagPair: Pair<IChakra, Tag> ->
                val capability: IChakra = iChakraTagPair.getFirst()
                this.maxChakra = capability.getMaxChakra()
                this.chakra = capability.getCurrentChakra().coerceAtMost(maxChakra.toFloat()).coerceAtLeast(0f)
            })
    }

    companion object {
        val CODEC: Codec<IChakra> =
            RecordCodecBuilder.create<IChakra>(Function { iChakraInstance: RecordCodecBuilder.Instance<IChakra> ->
                iChakraInstance.group<Float, Int>(
                    Codec.FLOAT.fieldOf("chakra").forGetter<IChakra> { obj: IChakra -> obj.getCurrentChakra() },
                    Codec.INT.fieldOf("maxChakra")
                        .forGetter<IChakra> { obj: IChakra -> obj.getMaxChakra() }
                ).apply<IChakra>(
                    iChakraInstance
                ) { chakra: Float, maxChakra: Int -> Chakra(chakra, maxChakra) }
            })
    }
}
