package net.Chidoziealways.everythingjapanese.debug

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.resources.Identifier
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterDebugEntriesEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object RegisterEvent {
    @SubscribeEvent
    fun registerDebug(event: RegisterDebugEntriesEvent) {
        event.register(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "karma"), DebugEntryKarma())
    }
}