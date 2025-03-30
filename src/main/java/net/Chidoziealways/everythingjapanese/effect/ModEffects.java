package net.Chidoziealways.everythingjapanese.effect;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EverythingJapanese.MOD_ID);

    public static final RegistryObject<MobEffect> ADRENALINE_EFFECT = MOB_EFFECTS.register("adrenaline",
            () -> new AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x36ebab)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "adrenaline"),
                            -0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.MINING_EFFICIENCY, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "adrenaline"),
                            2.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.SUBMERGED_MINING_SPEED, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "adrenaline"),
                            2.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
