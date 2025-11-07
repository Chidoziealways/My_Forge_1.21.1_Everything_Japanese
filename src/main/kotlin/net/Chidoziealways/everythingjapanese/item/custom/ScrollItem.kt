package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ScrollItem(props: Properties): Item(props) {
    companion object {
        fun setText(stack: ItemStack, text: String) {
            stack.set(ModDataComponentTypes.EDITABLE_TEXT, text)
        }

        fun getText(stack: ItemStack): String? {
            return stack.get(ModDataComponentTypes.EDITABLE_TEXT)
        }
    }
}