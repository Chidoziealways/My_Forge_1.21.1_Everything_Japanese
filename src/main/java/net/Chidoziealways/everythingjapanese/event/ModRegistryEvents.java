package net.Chidoziealways.everythingjapanese.event;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu;
import net.Chidoziealways.everythingjapanese.util.ModRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistryEvents {
    private static final Logger log = LoggerFactory.getLogger(ModRegistryEvents.class);
    private static final Marker marker = MarkerFactory.getMarker("MODREGISTRYEVENTS");

    private static final int MAX_VARINT = Integer.MAX_VALUE - 1;

    @SubscribeEvent
    public static void onNewRegistries(NewRegistryEvent event) {
        log.info(marker, "Registering Custom Registries");
        event.create(
                new RegistryBuilder<Jutsu>()
                        .setName(ModRegistries.JUTSU.location())
                        .setDefaultKey(ModRegistries.JUTSU.location())
                        .setMaxID(MAX_VARINT)
        );
        log.info(marker, "Finished Registering Jutsu");
    }

}
