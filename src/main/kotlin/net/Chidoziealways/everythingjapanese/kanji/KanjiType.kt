package net.Chidoziealways.everythingjapanese.kanji

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.RegistryFileCodec
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level

data class KanjiType(
    val id: String,
    val blockEffectId: Identifier,
    val entityEffectId: Identifier,
    val overlay: Identifier
) {
    val blockEffect: KanjiBlockEffect?
        get() = KanjiEffectLoader.getBlockEffect(blockEffectId)

    val entityEffect: KanjiEntityEffect?
        get() = KanjiEffectLoader.getEntityEffect(entityEffectId)

    companion object {

        val DIRECT_CODEC: Codec<KanjiType> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("id").forGetter { it.id },
                Identifier.CODEC.fieldOf("blockEffectId").forGetter { it.blockEffectId },
                Identifier.CODEC.fieldOf("entityEffectId").forGetter { it.entityEffectId },
                Identifier.CODEC.fieldOf("overlay").forGetter { it.overlay }
            ).apply(instance, ::KanjiType)
        }
        val DIRECT_STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, KanjiType> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, {it.id},
            Identifier.STREAM_CODEC, {it.blockEffectId},
            Identifier.STREAM_CODEC, {it.entityEffectId},
            Identifier.STREAM_CODEC, {it.overlay},
            ::KanjiType)
        val CODEC: Codec<Holder<KanjiType>> = RegistryFileCodec.create(ModRegistries.KANJI, DIRECT_CODEC)
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, Holder<KanjiType>> = ByteBufCodecs.holder(
            ModRegistries.KANJI, DIRECT_STREAM_CODEC
        )
    }
}

fun interface KanjiBlockEffect {
    fun apply(level: Level, pos: BlockPos, player: Player)
}

fun interface KanjiEntityEffect {
    fun apply(level: Level, pos: BlockPos, player: Player, target: LivingEntity)
}