package net.Chidoziealways.everythingjapanese.event.render

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.world.entity.player.Player
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RenderLevelStageEvent
import net.neoforged.neoforge.client.event.RenderLivingEvent
import net.neoforged.neoforge.client.event.RenderPlayerEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID, value = [Dist.CLIENT])
object PlayerRenderEvent {
    @SubscribeEvent
    fun onPlayerRender(event: RenderPlayerEvent.Pre<*>) {
        val state = event.renderState
        val entityType = state.entityType
    }
}