package net.Chidoziealways.everythingjapanese.gui.layers

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.gui.HUDManager
import net.minecraft.resources.ResourceLocation
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent
import net.neoforged.neoforge.client.gui.VanillaGuiLayers
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, value = [Dist.CLIENT])
object ModGUILayers {

    val EV_HUD = ResourceLocation.fromNamespaceAndPath(MOD_ID, "ev_hud")

    @SubscribeEvent
    fun onRegisterGuiLayers(event: RegisterGuiLayersEvent) {
        print("Registering Layers")
        event.registerAbove(VanillaGuiLayers.HOTBAR, EV_HUD, HUDManager::renderStaminaHUD)
        println("Registered!")
    }
}