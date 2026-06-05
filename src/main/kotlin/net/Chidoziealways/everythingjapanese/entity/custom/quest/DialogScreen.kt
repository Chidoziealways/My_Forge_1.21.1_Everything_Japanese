package net.Chidoziealways.everythingjapanese.entity.custom.quest

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.KeyEvent
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW

class DialogScreen(val lines: List<String>, val choices: List<DialogChoice>) : Screen(Component.literal("Dialog")) {
    private var selectedChoice: Int = 0;

    override fun extractRenderState(guiGraphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int, partialTick: Float) {
        // Draw the dialog background
        val x0 = 50
        val y0 = 200
        val x1 = width - 50
        val y1 = height - 50
        guiGraphics.fill(x0, y0, x1, y1, 0xAA000000.toInt())

        // Draw dialog lines
        var y = y0 + 10
        lines.forEach { line ->
            guiGraphics.text(
                minecraft.font,
                line,
                x0.toInt() + 10,
                y.toInt(),
                0xFFFFFF,
                false
            )
            y += 12
        }

        // Draw choices
        y += 5
        choices.forEachIndexed { index, choice ->
            val prefix = if (index == selectedChoice) "> " else ""
            guiGraphics.text(
                minecraft.font,
                "$prefix${choice.text}",
                x0.toInt() + 10,
                y.toInt(),
                0xFFFF55,
                false
            )
            y += 12
        }

        super.extractRenderState(guiGraphics, mouseX, mouseY, partialTick)
    }

    override fun keyPressed(event: KeyEvent): Boolean {
        if (event.key == GLFW.GLFW_KEY_DOWN)
        {
            selectedChoice = (selectedChoice + 1) % choices.size
            return true
        }
        if (event.key == GLFW.GLFW_KEY_UP)
        {
            selectedChoice = (selectedChoice - 1 + choices.size) % choices.size
            return true
        }
        if (event.key == GLFW.GLFW_KEY_ENTER)
        {
            choices[selectedChoice].select()
            return true
        }

        return super.keyPressed(event)
    }

    class DialogChoice(
        val text: String?, // what happens when selected
        val action: Runnable
    ) {
        fun select() {
            action.run()
        }
    }
}