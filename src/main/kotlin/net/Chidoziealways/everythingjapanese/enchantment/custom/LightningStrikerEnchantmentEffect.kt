package net.Chidoziealways.everythingjapanese.enchantment.custom

import com.mojang.serialization.MapCodec
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.enchantment.EnchantedItemInUse
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect
import net.minecraft.world.phys.Vec3
import java.util.function.Supplier

class LightningStrikerEnchantmentEffect : EnchantmentEntityEffect {
    override fun apply(
        pLevel: ServerLevel,
        pEnchantmentLevel: Int,
        pItem: EnchantedItemInUse,
        pEntity: Entity,
        pOrigin: Vec3
    ) {
        for (i in 0..<pEnchantmentLevel) {
            EntityType.LIGHTNING_BOLT.spawn(pLevel, pEntity.getOnPos(), EntitySpawnReason.TRIGGERED)
        }
    }

    override fun codec(): MapCodec<out EnchantmentEntityEffect?> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<LightningStrikerEnchantmentEffect> = MapCodec.unit<LightningStrikerEnchantmentEffect>(
            Supplier { LightningStrikerEnchantmentEffect() })
    }
}
