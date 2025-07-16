package net.Chidoziealways.everythingjapanese.util

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.CycleJutsuPacket
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCastPacket
import net.Chidoziealways.everythingjapanese.network.ModNetwork
import net.minecraft.client.Minecraft
import net.minecraft.world.entity.player.Player
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.network.PacketDistributor
import org.lwjgl.glfw.GLFW
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE, value = [Dist.CLIENT])
object KeyPressHandler {
    private val log: Logger? = LoggerFactory.getLogger(KeyPressHandler::class.java)

    @JvmStatic
    @SubscribeEvent
    fun onClientTick(event: InputEvent.Key) {
        val minecraft = Minecraft.getInstance()
        val player: Player? = minecraft.player

        if (player != null) {
            if (event.getKey() == ModKeyBinds.CYCLE_JUTSU!!.key.value && event.action == GLFW.GLFW_PRESS) {
                ModNetwork.CHANNEL.send(CycleJutsuPacket(), PacketDistributor.SERVER.noArg())
            }

            if (event.getKey() == ModKeyBinds.CAST_JUTSU!!.key.value && event.action == GLFW.GLFW_PRESS) {
                ModNetwork.CHANNEL.send(JutsuCastPacket(), PacketDistributor.SERVER.noArg())
            }
        }
    }
}
