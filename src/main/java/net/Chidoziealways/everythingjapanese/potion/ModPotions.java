package net.Chidoziealways.everythingjapanese.potion;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, EverythingJapanese.MOD_ID);

    public static final RegistryObject<Potion> ADRENALINE_POTION = POTIONS.register("adrenaline_potion",
            () -> new Potion("adrenaline_potion", new MobEffectInstance(ModEffects.ADRENALINE_EFFECT.getHolder().get(), 200, 0)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
