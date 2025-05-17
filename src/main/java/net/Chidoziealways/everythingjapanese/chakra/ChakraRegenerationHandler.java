package net.Chidoziealways.everythingjapanese.chakra;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionDefaults;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChakraRegenerationHandler {

    public static void onServerTick(TickEvent.ServerTickEvent event) {
        // Run regeneration logic during the tick event (server-side only)
        if (event.phase == TickEvent.Phase.END) {
            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                player.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(chakra -> {
                    float currentChakra = chakra.getChakra();
                    int maxChakra = chakra.getMaxChakra();
                    chakra.updateMaxChakraBasedOnXP(player.experienceLevel);
                    if (currentChakra < maxChakra) {
                        chakra.addChakra(0.01f);// Regenerate 1 Chakra every tick, adjust as needed
                        player.level().addParticle(ParticleTypes.ENCHANT, player.getX(), player.getY(), player.getZ(), 0, 0.5, 0);
                    }
                });
            }
        }
    }
}
