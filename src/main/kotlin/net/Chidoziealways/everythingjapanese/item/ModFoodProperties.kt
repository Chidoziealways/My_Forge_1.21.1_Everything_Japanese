package net.Chidoziealways.everythingjapanese.item

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.component.Consumable
import net.minecraft.world.item.component.Consumables
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect

object ModFoodProperties {
    val SUSHI: FoodProperties = FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build()

    val SUSHI_EFFECT: Consumable = Consumables.defaultFood().onConsume(
        ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 1f)
    ).build()

    val UDON: FoodProperties = FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build()

    val UDON_EFFECT: Consumable = Consumables.defaultFood().onConsume(
        ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 1f)
    ).build()

    val RICE: FoodProperties = FoodProperties.Builder().nutrition(10).saturationModifier(0.25f).build()

    val YAMAZAKI_BERRIES: FoodProperties = FoodProperties.Builder().nutrition(10).saturationModifier(0.25f)
        .build()
}
