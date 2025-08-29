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
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level

data class KanjiType(
    val id: String,
    val blockEffectId: ResourceLocation,
    val entityEffectId: ResourceLocation,
    val overlay: ResourceLocation
) {
    val blockEffect: KanjiBlockEffect?
        get() = KanjiEffectLoader.getBlockEffect(blockEffectId)

    val entityEffect: KanjiEntityEffect?
        get() = KanjiEffectLoader.getEntityEffect(entityEffectId)

    companion object {

        val DIRECT_CODEC: Codec<KanjiType> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("id").forGetter { it.id },
                ResourceLocation.CODEC.fieldOf("blockEffectId").forGetter { it.blockEffectId },
                ResourceLocation.CODEC.fieldOf("entityEffectId").forGetter { it.entityEffectId },
                ResourceLocation.CODEC.fieldOf("overlay").forGetter { it.overlay }
            ).apply(instance, ::KanjiType)
        }
        val DIRECT_STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, KanjiType> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, {it.id},
            ResourceLocation.STREAM_CODEC, {it.blockEffectId},
            ResourceLocation.STREAM_CODEC, {it.entityEffectId},
            ResourceLocation.STREAM_CODEC, {it.overlay},
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