package net.Chidoziealways.everythingjapanese.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodProperties {
    public static final FoodProperties SUSHI = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build();

    public static final Consumable SUSHI_EFFECT = Consumables.defaultFood().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 1f)
    ).build();

    public static final FoodProperties UDON = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build();

    public static final Consumable UDON_EFFECT = Consumables.defaultFood().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 1f)
    ).build();

    public static final FoodProperties RICE = new FoodProperties.Builder().nutrition(10).saturationModifier(0.25f).build();

    public static final FoodProperties YAMAZAKI_BERRIES = new FoodProperties.Builder().nutrition(10).saturationModifier(0.25f)
            .build();
}
