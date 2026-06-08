package net.Chidoziealways.everythingjapanese.screen.custom.cursedblock

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.curse.CurseList
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerListener
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.client.network.ClientPacketDistributor

class CursedBlockScreen(menu: CursedBlockMenu, inv: Inventory, title: Component) : AbstractContainerScreen<CursedBlockMenu>(menu, inv, title),
    ContainerListener {

    init {
        println("SCREEN CRETED")
        playerInventoryTitle = Component.literal("")
    }

    // Name of the Player to Curse
    private lateinit var playerName: EditBox
    private lateinit var curseList: CurseList

    override fun init() {
        super.init()
        playerName = EditBox(font, leftPos+29, topPos+39, 150, 150, Component.literal("Enter Player Name"))
        playerName.setCanLoseFocus(false)
        playerName.setTextColor(-1)
        playerName.setTextColorUneditable(-1)
        playerName.isBordered = false
        playerName.setMaxLength(20)
        playerName.setResponder(this@CursedBlockScreen::onTextChanged)
        playerName.value = ""
        this.addRenderableWidget(this@CursedBlockScreen.playerName)
        playerName.setEditable(true) // Maybe Change Later?

        curseList = CurseList(
            minecraft,
            100,
            130,
            0,
            18
        )

        curseList.x = leftPos + 29
        curseList.y = topPos + 65

        ModRegistries.CURSES.forEach {
            println("Curse: ${it.displayName}")
            this.curseList.addCurse(it)
        }

        this.addRenderableWidget(curseList)
    }

    override fun removed() {
        super.removed()
    }

    private fun onTextChanged(str: String) {
        ClientPacketDistributor.sendToServer(SetPlayerNamePacket(playerName.value))
    }

    override fun slotChanged(
        p0: AbstractContainerMenu,
        p1: Int,
        p2: ItemStack
    ) {

    }

    override fun dataChanged(p0: AbstractContainerMenu, p1: Int, p2: Int) {

    }

    override fun extractBackground(graphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int, a: Float) {
        val i = leftPos
        val j = topPos
        graphics.blit(RenderPipelines.GUI_TEXTURED,
            BG_LOCATION, i, j, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256)
    }

    companion object {
        val BG_LOCATION = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/gui/cursed_block.png")
    }
}