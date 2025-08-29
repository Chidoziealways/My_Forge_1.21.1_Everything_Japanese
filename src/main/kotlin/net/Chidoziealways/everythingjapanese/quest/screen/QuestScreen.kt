package net.Chidoziealways.everythingjapanese.quest.screen

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.quest.QuestCapability
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

class QuestScreen : Screen(Component.translatable("gui.quests")) {
    private var scrollOffset = 0
    private var totalContentHeight = 0
    private val visibleHeight = 110 // how much vertical space you want visible


    companion object {
        val WINDOW = ResourceLocation.withDefaultNamespace("textures/gui/advancements/window.png")
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        val i = (width - 252) / 2
        val j = (height - 140) / 2
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, WINDOW, i, j, 0.0F, 0.0F, 252, 140, 256, 256)

        val player = Minecraft.getInstance().player
        val font = Minecraft.getInstance().font

        val cap = player?.getCapability(ModCapabilities.QUEST_CAPABILITY)

            println("Client Completed Quests: ${cap?.completedQuests}")
            println("Client Current Quest: ${cap?.currentQuestId}")
            println("Client Progress Map: ${cap?.getQuestProgress()}")

            // Position of the scrollable box
            val contentX = i + 10
            val contentY = j + 18
            val contentWidth = 222
            val contentMaxY = contentY + visibleHeight

            guiGraphics.enableScissor(contentX, contentY, contentX + contentWidth, contentMaxY)
            guiGraphics.pose().pushMatrix()
            guiGraphics.pose().translate(0F, -scrollOffset.toFloat())

            var y = contentY

            // === Finished Quests ===
            guiGraphics.drawString(font, "✔ Finished Quests", contentX, y, 0xFF00FF00.toInt(), false)
            y += 15

            for (id in cap!!.completedQuests) {
                val quest = QuestCapability.QUEST_LOOKUP[id] ?: continue
                guiGraphics.drawString(font, "※　${quest.title.string}　※", contentX + 10, y, 0xFFAAAAAA.toInt(), false)
                y += 12
            }

            val currentQuest = cap.getQuest()
            if (currentQuest != null) {
                y += 15
                guiGraphics.drawString(font, " Current Quest", contentX, y, 0xFFFFFF00.toInt(), false)
                y += 15

                guiGraphics.drawString(font, currentQuest.title.string, contentX + 10, y, 0xFFFFFFFF.toInt(), false)
                y += 15

                val stageIndex = cap.getQuestProgress()[currentQuest.id]?.stageIndex ?: 0
                for ((i, stage) in currentQuest.stages.withIndex()) {
                    val color = when {
                        i < stageIndex -> 0xFF00FF00.toInt()
                        i == stageIndex -> 0xFFFFFF00.toInt()
                        else -> 0xFF555555.toInt()
                    }
                    guiGraphics.drawString(font, "※　${stage.objective.string}　※", contentX + 20, y, color, false)
                    y += 12
                }
            } else {
                y += 20
                guiGraphics.drawString(font, "No active quest", contentX, y, 0xFFFF5555.toInt(), false)
            }

            guiGraphics.pose().popMatrix()
            guiGraphics.disableScissor()

            // Update content height so scrolling bounds stay correct
            totalContentHeight = y - contentY
        super.render(guiGraphics, mouseX, mouseY, partialTick)
    }

    override fun shouldCloseOnEsc(): Boolean = true

    override fun mouseScrolled(delta: Double, p_94687_: Double, p_94688_: Double, p_299502_: Double): Boolean {
        val maxScroll = (totalContentHeight - visibleHeight).coerceAtLeast(0)
        scrollOffset = (scrollOffset - (delta * 10)).toInt().coerceIn(0, maxScroll)
        return true
    }

}
