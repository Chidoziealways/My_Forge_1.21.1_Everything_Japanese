package net.Chidoziealways.everythingjapanese.capabilities;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID)
public class PlayerEvents {
    private static final Logger log = LoggerFactory.getLogger(PlayerEvents.class);

    public static void saveChakraData(Player player) {

        assert player != null;
        log.info("Saving data");
        player.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iChakra -> {
            CompoundTag chakraTag = iChakra.serializeNBT();
            log.info("Saving Chakra");
            player.getPersistentData().put("everythingjapanese:chakra_data", chakraTag); // Save Chakra data to persistent NBT
        });

        player.getCapability(ModCapabilities.JUTSU_CAPABILITY).ifPresent(iJutsu -> {
            CompoundTag jutsuTag = iJutsu.serializeNBT();
            player.getPersistentData().put("everythingjapanese:jutsu_data", jutsuTag); // Save Jutsu data to persistent NBT
        });
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            saveChakraData(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();

        // Save Chakra and Jutsu data to persistent NBT
        saveChakraData(player);
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        // Retrieve persistent data
        if (player.getPersistentData().contains("everythingjapanese:chakra_data")) {
            CompoundTag jutsuTag = player.getPersistentData().getCompoundOrEmpty("everythingjapanese:chakra_data");
            player.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iJutsu -> {
                log.info("Loading Chakra data from persistent NBT");
                iJutsu.deserializeNBT(jutsuTag);
            });
        }

        // Retrieve persistent data
        if (player.getPersistentData().contains("everythingjapanese:jutsu_data")) {
            CompoundTag jutsuTag = player.getPersistentData().getCompoundOrEmpty("everythingjapanese:jutsu_data");
            player.getCapability(ModCapabilities.JUTSU_CAPABILITY).ifPresent(iJutsu -> {
                log.info("Loading Jutsu data from persistent NBT");
                iJutsu.deserializeNBT(jutsuTag);
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        Player player = event.getEntity();

        // Save Chakra and Jutsu data to persistent NBT
        saveChakraData(player);
    }




    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        log.info("Loading data");
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        // Restore Chakra data
        if (oldPlayer.getPersistentData().contains("everythingjapanese:chakra_data")) {
            CompoundTag chakraTag = oldPlayer.getPersistentData().getCompoundOrEmpty("everythingjapanese:chakra_data");
            newPlayer.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iChakra -> {
                log.info("Restoring Chakra Data");
                iChakra.deserializeNBT(chakraTag);
            });
        } else {
            log.error("Doesn't contain chakra_data");
        }

        // Restore Jutsu data
        if (oldPlayer.getPersistentData().contains("everythingjapanese:jutsu_data")) {
            CompoundTag jutsuTag = oldPlayer.getPersistentData().getCompoundOrEmpty("everythingjapanese:jutsu_data");
            newPlayer.getCapability(ModCapabilities.JUTSU_CAPABILITY).ifPresent(iJutsu -> {
                log.info("Restoring Jutsu Data");
                iJutsu.deserializeNBT(jutsuTag);
            });
        } else {
            log.error("Doesn't contain jutsu_data");
        }
    }

}
