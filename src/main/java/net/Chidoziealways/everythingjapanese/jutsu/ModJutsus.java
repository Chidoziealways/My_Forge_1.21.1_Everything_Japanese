package net.Chidoziealways.everythingjapanese.jutsu;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.LargeFireballJutsuLvel1;
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.SmallFireballJutsu;
import net.Chidoziealways.everythingjapanese.jutsu.cutsom.SmallWindballJutsu;
import net.Chidoziealways.everythingjapanese.util.ModRegistries;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModJutsus {
    public static final DeferredRegister<Jutsu> JUTSUS =
            DeferredRegister.create(ModRegistries.JUTSU, EverythingJapanese.MOD_ID);

    public static final RegistryObject<Jutsu> SMALL_FIREBALL = JUTSUS.register("small_fireball",
            SmallFireballJutsu::new);

    public static final RegistryObject<Jutsu> SMALL_WINDBALL = JUTSUS.register("small_windball",
            SmallWindballJutsu::new);

    public static final RegistryObject<Jutsu> LARGE_FIREBALL = JUTSUS.register("large_fireball",
            LargeFireballJutsuLvel1::new);

    public static void register(IEventBus eventBus) {
        JUTSUS.register(eventBus);
    }

    private static final Map<ResourceLocation, Jutsu> LOOKUP = new HashMap<>();

    // This should be called after the registry has been fired.
    public static void buildLookup() {
        JUTSUS.getEntries().forEach(regObj -> {
            LOOKUP.put(regObj.getId(), regObj.get());
        });
    }

    // Lookup method used in our packet.
    public static Jutsu getJutsu(ResourceLocation id) {
        return LOOKUP.get(id);
    }

    public static boolean isValidJutsu(ResourceLocation id) {
        return LOOKUP.containsKey(id);
    }

    public static Holder<Jutsu> getHolder(ResourceLocation id) {
        return JUTSUS.getEntries().stream()
                .filter(entry -> entry.getKey().location().equals(id))
                .map(entry -> entry.getHolder().orElseThrow())
                .findFirst()
                .orElse(null);
    }

}
