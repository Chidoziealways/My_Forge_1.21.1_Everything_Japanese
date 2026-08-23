package net.Chidoziealways.everythingjapanese.curse

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.ObjectSelectionList
import net.minecraft.client.gui.components.StringWidget
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.network.chat.Component

class CurseList(
    minecraft: Minecraft,
    width: Int,
    height: Int,
    top: Int,
    itemHeight: Int,
    val screen: Screen,
    val onSelected: (Curse) -> Unit
): ObjectSelectionList<CurseList.Entry>(
    minecraft,
    width,
    height,
    top,
    itemHeight
) {
    var selectedCurse: Curse? = null

    fun addCurse(curse: Curse) {
        val lineHeight = 9
        val paddingTop = if (this.children().isEmpty()) 0 else lineHeight * 2
        addEntry(Entry(curse, screen, paddingTop))
    }


    inner class Entry(
        val curse: Curse,
        val screen: Screen,
        val paddingTop: Int
    ) : ObjectSelectionList.Entry<Entry>() {
        override fun getNarration(): Component {
            return Component.literal(curse.displayName)
        }

        override fun extractContent(
            p0: GuiGraphicsExtractor,
            p1: Int,
            p2: Int,
            p3: Boolean,
            p4: Float
        ) {
            val dX = this.screen.width / 2 - 55
            val dY = contentY + paddingTop
            p0.text(minecraft.font, Component.literal(curse.displayName), dX, dY, 0xFFFF00FF.toInt())
        }

        override fun mouseClicked(event: MouseButtonEvent, doubleClick: Boolean): Boolean {
            this@CurseList.selected = this
            this@CurseList.selectedCurse = curse
            this@CurseList.onSelected(this.curse)
            return true
        }
    }
}