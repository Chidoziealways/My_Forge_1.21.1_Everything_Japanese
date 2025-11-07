package net.Chidoziealways.everythingjapanese.item.katana

import com.mojang.serialization.Codec
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.item.ModToolMaterials
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ToolMaterial

enum class BladeType(val id: String, val displayName: String, val material: ToolMaterial, val overlay: ResourceLocation) {
    STEEL("steel", "Steel", ModToolMaterials.STEEL, ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/blade_steel"));

    companion object {
        val CODEC: Codec<BladeType> = Codec.STRING.xmap(
            { id -> entries.find { it.id == id }  ?: STEEL},
            { it.id }
        )

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, BladeType> = StreamCodec.of(
            { buf, bladeType -> buf.writeUtf(bladeType.id) },  // encode
            { buf -> entries.find { it.id == buf.readUtf() } ?: STEEL } // decode
        )
    }
}