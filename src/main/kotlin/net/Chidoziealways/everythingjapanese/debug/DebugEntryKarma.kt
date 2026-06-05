package net.Chidoziealways.everythingjapanese.debug

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer
import net.minecraft.client.gui.components.debug.DebugScreenEntry
import net.minecraft.world.level.Level
import net.minecraft.world.level.chunk.LevelChunk
import java.util.Locale

class DebugEntryKarma: DebugScreenEntry {
    override fun display(
        displayer: DebugScreenDisplayer,
        level: Level?,
        chunk: LevelChunk?,
        chunk2: LevelChunk?
    ) {
        val mc = Minecraft.getInstance()
        val entity = mc.cameraEntity
        if (entity != null && mc.level != null && chunk2 != null && level != null) {
            val player = mc.player ?: return
            val karmaCap = player.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: return
            val karmaAmount = karmaCap.getKarmaAmount()

            displayer.addLine(
                String.format(
                    Locale.ROOT,
                    "Karma: $karmaAmount"
                )
            )
        }
    }
}