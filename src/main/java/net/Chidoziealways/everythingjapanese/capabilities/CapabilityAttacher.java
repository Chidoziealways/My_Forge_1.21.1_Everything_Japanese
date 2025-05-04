package net.Chidoziealways.everythingjapanese.capabilities;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.chakra.ChakraProvider;
import net.Chidoziealways.everythingjapanese.jutsu.JutsuProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityAttacher {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "chakra"), new ChakraProvider());
            event.addCapability(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "jutsu"), new JutsuProvider());
        }
    }
}
