package net.Chidoziealways.everythingjapanese.screen.custom.calligraphytable

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.item.custom.ScrollItem
import net.Chidoziealways.everythingjapanese.item.custom.SetScrollTextPacket
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerListener
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.client.network.ClientPacketDistributor
import org.lwjgl.glfw.GLFW

class CalligraphyTableScreen(menu: CalligraphyTableMenu, playerInventory: Inventory, title: Component): AbstractContainerScreen<CalligraphyTableMenu>(menu, playerInventory, title), ContainerListener {
    private lateinit var scrollText: EditBox

    override fun init() {
        super.init()
        menu.addSlotListener(this)

        scrollText = EditBox(font, imageWidth + 59, imageHeight - 110, 102, 15, Component.literal("Edit Text"))
        scrollText.setCanLoseFocus(false)
        scrollText.setTextColor(-1)
        scrollText.setTextColorUneditable(-1)
        scrollText.isBordered = false
        scrollText.setMaxLength(100000)
        scrollText.setResponder(this@CalligraphyTableScreen::onTextChanged)
        scrollText.value = ""
        this.addRenderableWidget(this@CalligraphyTableScreen.scrollText)
        scrollText.setEditable(this@CalligraphyTableScreen.menu.getSlot(0).hasItem())
    }

    override fun resize(width: Int, height: Int) {
        val s = scrollText.value
        init(width, height)
        scrollText.value = s
    }

    override fun removed() {
        super.removed()
        menu.removeSlotListener(this)
    }

    override fun setInitialFocus() {
        super.setInitialFocus(this.scrollText)
    }

    override fun keyPressed(event: KeyEvent): Boolean {
        val keyCode = event.key
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.minecraft?.player?.closeContainer()
        }

        return if(!this.scrollText.keyPressed(event) && !scrollText.canConsumeInput()) super.keyPressed(event) else true
    }

    private fun onTextChanged(name: String) {
        val slot = menu.getSlot(0)
        if (slot.hasItem()) {
            val stack = slot.item
            ClientPacketDistributor.sendToServer(SetScrollTextPacket(name, stack))
        }
    }


    override fun extractRenderState(p_283479_: GuiGraphicsExtractor, p_283661_: Int, p_281248_: Int, p_281886_: Float) {
        super.extractRenderState(p_283479_, p_283661_, p_281248_, p_281886_)
        this.extractTooltip(p_283479_, p_283661_, p_281248_)
    }

    override fun extractBackground(
        guiGraphics: GuiGraphicsExtractor,
        mouseX: Int,
        mouseY: Int,
        parialTick: Float
    ) {
        val i = leftPos
        val j = topPos
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BG_LOCATION, i, j, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256)
    }

    override fun slotChanged(
        containerToSend: AbstractContainerMenu,
        dataSlotIndex: Int,
        stack: ItemStack
    ) {
        if (dataSlotIndex == 0) {
            val text = ScrollItem.getText(stack)
            if (text == null) {
                println("Text is empty!")
            }
            scrollText.value = if(stack.isEmpty) "" else text ?: ""
            scrollText.setEditable(!stack.isEmpty)
            focused = scrollText
        }
    }

    override fun dataChanged(
        containerMenu: AbstractContainerMenu,
        dataSlotIndex: Int,
        value: Int
    ) {

    }

    companion object {
        val BG_LOCATION = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/gui/calligraphy_table.png")
    }
}