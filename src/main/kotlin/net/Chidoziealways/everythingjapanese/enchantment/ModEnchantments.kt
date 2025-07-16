package net.Chidoziealways.everythingjapanese.enchantment

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.enchantment.custom.LightningStrikerEnchantmentEffect
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.EnchantmentTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents
import net.minecraft.world.item.enchantment.EnchantmentTarget
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect

object ModEnchantments {
    val LIGHTNING_STRIKER: ResourceKey<Enchantment?> = ResourceKey.create<Enchantment?>(
        Registries.ENCHANTMENT,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "lightning_striker")
    )

    fun bootstrap(context: BootstrapContext<Enchantment?>) {
        val enchantments = context.lookup<Enchantment?>(Registries.ENCHANTMENT)
        val items = context.lookup<Item?>(Registries.ITEM)

        register(
            context, LIGHTNING_STRIKER, Enchantment.enchantment(
                Enchantment.definition(
                    items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                    items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                    5,
                    255,
                    Enchantment.dynamicCost(5, 8),
                    Enchantment.dynamicCost(25, 8),
                    2,
                    EquipmentSlotGroup.MAINHAND
                )
            )
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect<EnchantmentEntityEffect?>(
                    EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                    EnchantmentTarget.VICTIM, LightningStrikerEnchantmentEffect()
                )
        )
    }


    private fun register(
        registry: BootstrapContext<Enchantment?>,
        key: ResourceKey<Enchantment?>,
        builder: Enchantment.Builder
    ) {
        registry.register(key, builder.build(key.location()))
    }
}
