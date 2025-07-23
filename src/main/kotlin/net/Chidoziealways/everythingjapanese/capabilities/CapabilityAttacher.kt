package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.chakra.ChakraProvider
import net.Chidoziealways.everythingjapanese.jutsu.JutsuProvider
import net.Chidoziealways.everythingjapanese.quest.QuestProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod.KotlinEventBusSubscriber

@KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE)
object CapabilityAttacher {

    @SubscribeEvent
    fun onAttachCapabilities(event: AttachCapabilitiesEvent.Entities) {
        if (event.getObject() is Player) {
            event.addCapability(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "chakra"),
                ChakraProvider()
            )
            event.addCapability(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "jutsu"),
                JutsuProvider()
            )
            event.addCapability(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "quests"),
                QuestProvider()
            )
        }
    }
}
