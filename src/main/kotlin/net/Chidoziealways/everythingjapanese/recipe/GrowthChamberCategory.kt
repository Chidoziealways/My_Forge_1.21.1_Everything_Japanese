package net.Chidoziealways.everythingjapanese.recipe

import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import java.util.function.IntFunction
import java.util.function.Supplier
import java.util.function.ToIntFunction

enum class GrowthChamberCategory(val disName: String, private val id: Int) : StringRepresentable {
    INGREDIENTS("ingredients", 0);

    override fun getSerializedName(): String {
        return disName
    }

    private fun id(): Int {
        return this.id
    }

    companion object {
        val CODEC = StringRepresentable.fromEnum(Supplier { entries.toTypedArray() })
        val BY_ID: IntFunction<GrowthChamberCategory> = ByIdMap.continuous<GrowthChamberCategory>(
            ToIntFunction { obj: GrowthChamberCategory -> obj!!.id() },
            entries.toTypedArray(),
            ByIdMap.OutOfBoundsStrategy.ZERO
        )
        val STREAM_CODEC: StreamCodec<ByteBuf, GrowthChamberCategory> =
            ByteBufCodecs.idMapper<GrowthChamberCategory>(
                BY_ID, ToIntFunction { obj: GrowthChamberCategory -> obj!!.id() })
    }
}
