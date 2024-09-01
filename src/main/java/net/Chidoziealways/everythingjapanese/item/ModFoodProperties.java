package net.Chidoziealways.everythingjapanese.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties SUSHI = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 1f).usingConvertsTo(Items.BOWL).build();

    public static final FoodProperties UDON = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 1f).usingConvertsTo(Items.BOWL).build();
}
