package net.Chidoziealways.everythingjapanese.curse

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.ObjectSelectionList
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.network.chat.Component

class CurseList(
    minecraft: Minecraft,
    width: Int,
    height: Int,
    top: Int,
    itemHeight: Int
): ObjectSelectionList<CurseList.Entry>(
    minecraft,
    width,
    height,
    top,
    itemHeight
) {
    var selectedCurse: Curse? = null

    fun addCurse(curse: Curse) {
        addEntry(Entry(curse))
        println("Curse Added! ${curse.displayName}")
    }


    inner class Entry(
        val curse: Curse,
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
            print("X: $p1, Y: $p2")
            p0.text(minecraft.font, curse.displayName, p1+4, p2 + 4, 0xFFFFFF)
            println("Contents Extracted ${curse.displayName}")
        }

        override fun mouseClicked(event: MouseButtonEvent, doubleClick: Boolean): Boolean {
            this@CurseList.selected = this
            this@CurseList.selectedCurse = curse
            return true
        }
    }
}