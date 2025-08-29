package net.Chidoziealways.everythingjapanese.util

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.chakra.IncreaseChakraPacket
import net.Chidoziealways.everythingjapanese.jutsu.CycleJutsuPacket
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCastPacket
import net.Chidoziealways.everythingjapanese.quest.screen.QuestScreen
import net.minecraft.client.Minecraft
import net.minecraft.world.entity.player.Player
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.InputEvent
import net.neoforged.neoforge.client.network.ClientPacketDistributor
import org.lwjgl.glfw.GLFW
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, value = [Dist.CLIENT])
object KeyPressHandler {
    private val log: Logger? = LoggerFactory.getLogger(KeyPressHandler::class.java)

    @SubscribeEvent
    fun onClientTick(event: InputEvent.Key) {
        val minecraft = Minecraft.getInstance()

        if (minecraft.screen != null) return

        val player: Player? = minecraft.player

        if (player != null) {
            if (event.key == ModKeyBinds.CYCLE_JUTSU.key.value && event.action == GLFW.GLFW_PRESS) {
                ClientPacketDistributor.sendToServer(CycleJutsuPacket)
            }

            if (event.key == ModKeyBinds.CAST_JUTSU.key.value && event.action == GLFW.GLFW_PRESS) {
                ClientPacketDistributor.sendToServer(JutsuCastPacket)
            }

            if (event.key == ModKeyBinds.SHOW_QUESTS.key.value && event.action == GLFW.GLFW_PRESS) {
                minecraft.setScreen(QuestScreen())
            }

            if (event.key == ModKeyBinds.REGEN_CHAKRA.key.value && event.action == GLFW.GLFW_REPEAT || event.action == GLFW.GLFW_PRESS) {
                ClientPacketDistributor.sendToServer(IncreaseChakraPacket(6.8f))
            }
        }
    }

}
