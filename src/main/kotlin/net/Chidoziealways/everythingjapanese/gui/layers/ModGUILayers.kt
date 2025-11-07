package net.Chidoziealways.everythingjapanese.gui.layers

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.gui.HUDManager
import net.minecraft.resources.ResourceLocation
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent
import net.neoforged.neoforge.client.gui.VanillaGuiLayers
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID, value = [Dist.CLIENT])
object ModGUILayers {

    val JUTSU = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "jutsu")
    val CHAKRA = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "chakra")
    val STAMINA = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "stamina")
    val MONEY = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "money")

    @SubscribeEvent
    fun onRegisterGuiLayers(event: RegisterGuiLayersEvent) {
        print("Registering Layers")
        event.registerAbove(VanillaGuiLayers.HOTBAR, JUTSU, HUDManager::renderJutsuHUD)
        event.registerAbove(JUTSU, CHAKRA, HUDManager::renderChakraHUD)
        event.registerAbove(CHAKRA, STAMINA, HUDManager::renderStaminaHUD)
        event.registerAbove(STAMINA, MONEY, HUDManager::renderMoneyHUD)
        println("Registered!")
    }
}