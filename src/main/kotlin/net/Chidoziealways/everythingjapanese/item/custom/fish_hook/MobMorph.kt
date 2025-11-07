package net.Chidoziealways.everythingjapanese.item.custom.fish_hook

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec2

data class MobMorph(
    val name: String,
    val type: EntityType<*>,
    val hitbox: Vec2,
    val abilities: (Player) -> Unit = {}
) {
    companion object {
        private val VEC2_CODEC: Codec<Vec2> = Codec.FLOAT.listOf().xmap(
            { list -> Vec2(list[0], list[1]) },
            { vec -> listOf(vec.x, vec.y) }
        )

        val CODEC: Codec<MobMorph> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("name").forGetter(MobMorph::name),
                BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("type").forGetter(MobMorph::type),
                VEC2_CODEC.fieldOf("hitbox").forGetter(MobMorph::hitbox)
            ).apply(instance) { name, type, hitbox ->
                // abilities default to {}
                MobMorph(name, type, hitbox)
            }
        }
    }
}