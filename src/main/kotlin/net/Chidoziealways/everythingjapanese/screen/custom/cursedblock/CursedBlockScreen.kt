package net.Chidoziealways.everythingjapanese.screen.custom.cursedblock

import com.mojang.blaze3d.platform.InputConstants
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.curse.CurseList
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.screens.Screen
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
import kotlin.properties.Delegates

class CursedBlockScreen : Screen(Component.translatable("screen.everythingjapanese.cursed_block")),
    ContainerListener {

    init {
        println("SCREEN CRETED")
    }

    var leftPos by Delegates.notNull<Int>()
    var topPos by Delegates.notNull<Int>()
    var imageWidth: Int = 176
    var imageHeight: Int = 166

    // Name of the Player to Curse
    private lateinit var playerName: EditBox
    private lateinit var curseList: CurseList
    private lateinit var curseButton: Button

    private var name: String = ""
    private var curseID: Identifier = Identifier.fromNamespaceAndPath("","")

    override fun init() {
        super.init()
        this.leftPos = (this.width - this.imageWidth) / 2
        this.topPos = (this.height - this.imageHeight) / 2
        playerName = EditBox(font, leftPos+29, topPos+39, 150, 20, Component.literal("Enter Player Name"))
        this.setInitialFocus(playerName)
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
            30,
            0,
            50,
            this
        ) { curse ->
            curseID = ModRegistries.CURSES.getKey(curse) ?: return@CurseList
        }

        curseList.x = leftPos + 29
        curseList.y = topPos + 65

        ModRegistries.CURSES.forEach {
            this.curseList.addCurse(it)
        }

        this.addRenderableWidget(curseList)

        curseButton = Button.Builder(Component.literal("Curse")){
            ClientPacketDistributor.sendToServer(CursePlayerPacket(name, curseID))
        }.createNarration { sup->
            return@createNarration sup.get().append(Component.literal(" Press this button to curse the selected player w/ the selected curse!"))
        }.bounds(leftPos + 64, topPos + 136, 50, 50).build()

        this.addRenderableWidget(curseButton)
    }

    override fun removed() {
        super.removed()
    }

    private fun onTextChanged(str: String) {
        name = str
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

    override fun keyPressed(event: KeyEvent): Boolean {
        if (minecraft.options.keyInventory.isActiveAndMatches(InputConstants.getKey(event)))
            return false
        else
            return super.keyPressed(event)
    }

    companion object {
        val BG_LOCATION = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/gui/cursed_block.png")
    }
}