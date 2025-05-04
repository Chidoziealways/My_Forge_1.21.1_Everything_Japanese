package net.Chidoziealways.everythingjapanese.util;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.Chidoziealways.everythingjapanese.jutsu.CycleJutsuPacket;
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability;
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCastPacket;
import net.Chidoziealways.everythingjapanese.network.ModNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyPressHandler {
    private static final Logger log = LoggerFactory.getLogger(KeyPressHandler.class);

    @SubscribeEvent
    public static void onClientTick(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player != null) {
            if (event.getKey() == ModKeyBinds.CYCLE_JUTSU.getKey().getValue() && event.getAction() == GLFW.GLFW_PRESS) {
                ModNetwork.CHANNEL.send(new CycleJutsuPacket(), PacketDistributor.SERVER.noArg());
            }

            if (event.getKey() == ModKeyBinds.CAST_JUTSU.getKey().getValue() && event.getAction() == GLFW.GLFW_PRESS) {
                ModNetwork.CHANNEL.send(new JutsuCastPacket(), PacketDistributor.SERVER.noArg());
            }
        }
    }

}
