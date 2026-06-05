package net.Chidoziealways.everythingjapanese.screen.custom.calligraphytable

import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.item.custom.ScrollItem
import net.Chidoziealways.everythingjapanese.screen.ModMenuTypes
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.StringUtil
import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.ResultContainer
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

class CalligraphyTableMenu(containerId: Int, playerInventory: Inventory, private val access: ContainerLevelAccess): AbstractContainerMenu(
    ModMenuTypes.CALLIGRAPHY_TABLE_MENU, containerId) {

    private var scrollText: String = ""

    val container = object : SimpleContainer(1) {
        override fun setChanged() {
            this@CalligraphyTableMenu.slotsChanged(this)
            super.setChanged()
        }
    }

    var lastSoundTime: Long = 0

    val resultContainer = object : ResultContainer() {
        override fun setChanged() {
            this@CalligraphyTableMenu.slotsChanged(this)
            super.setChanged()
        }
    }

    init {
        this.addSlot(object : Slot(this.container, 0, 15, 38) {
            override fun mayPlace(stack: ItemStack): Boolean {
                return stack.has(ModDataComponentTypes.EDITABLE_TEXT)
            }
        })
        this.addSlot(object : Slot(this.resultContainer, 0, 141, 35) {
            override fun mayPlace(stack: ItemStack): Boolean = false

            override fun onTake(player: Player, stack: ItemStack) {
                if (player is ServerPlayer && !StringUtil.isBlank(scrollText) && ScrollItem.getText(container.getItem(0)) != this@CalligraphyTableMenu.scrollText) {
                    player.textFilter.processStreamMessage(scrollText)
                }

                this@CalligraphyTableMenu.slots[0].remove(1)
                access.execute { level, pos ->
                    val i = level.gameTime
                    if (this@CalligraphyTableMenu.lastSoundTime != i) {
                        level.playSound(null, pos, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F)
                        this@CalligraphyTableMenu.lastSoundTime = i
                    }
                }
                super.onTake(player, stack)
            }
        })

        this.addStandardInventorySlots(playerInventory, 8, 84)
    }

    override fun slotsChanged(container: Container) {
        super.slotsChanged(container)
        if (container == this.container) {
            val item = container.getItem(0)

            if (item.isEmpty) {
                resultContainer.setItem(0, ItemStack.EMPTY)
                return
            }

            val result = item.copy()
            ScrollItem.setText(result, scrollText)
            resultContainer.setItem(0, result)

            this.broadcastChanges()
        }
    }

    fun setItemText(text: String): Boolean {
        if (text != scrollText) {
            scrollText = text
            if (this.getSlot(0).hasItem()) {
                val stack = this.getSlot(0).item
                val result = stack.copy()
                ScrollItem.setText(result, text)
                resultContainer.setItem(0, result)
            }
            return true
        }
        return false
    }


    constructor(containerId: Int, playerInventory: Inventory): this(containerId, playerInventory, ContainerLevelAccess.NULL)

    override fun quickMoveStack(playerIn: Player, pIndex: Int): ItemStack {
        val sourceSlot = slots[pIndex]
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY //EMPTY_ITEM

        val sourceStack: ItemStack = sourceSlot.item
        val copyOfSourceStack: ItemStack = sourceStack.copy()

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(
                    sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                            + TE_INVENTORY_SLOT_COUNT, false
                )
            ) {
                return ItemStack.EMPTY // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(
                    sourceStack,
                    VANILLA_FIRST_SLOT_INDEX,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
                    false
                )
            ) {
                return ItemStack.EMPTY
            }
        } else {
            println("Invalid slotIndex:$pIndex")
            return ItemStack.EMPTY
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.count == 0) {
            sourceSlot.set(ItemStack.EMPTY)
        } else {
            sourceSlot.setChanged()
        }
        sourceSlot.onTake(playerIn, sourceStack)
        return copyOfSourceStack
    }

    override fun stillValid(player: Player): Boolean {
        return /*stillValid(access, player, JModBlocks.CALLIGRAPHY_TABLE)*/ true
    }

    override fun removed(player: Player) {
        super.removed(player)
        resultContainer.removeItemNoUpdate(1)
        access.execute { _, _ -> clearContainer(player, container) }
    }

    companion object {
        // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
        // must assign a slot number to each of the slots used by the GUI.
        // For this container, we can see both the tile inventory's slots and the player inventory slots and the hotbar.
        // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
        //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
        //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
        //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
        private const val HOTBAR_SLOT_COUNT = 9
        private const val PLAYER_INVENTORY_ROW_COUNT = 3
        private const val PLAYER_INVENTORY_COLUMN_COUNT = 9
        private const val PLAYER_INVENTORY_SLOT_COUNT: Int = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT
        private const val VANILLA_SLOT_COUNT: Int = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT
        private const val VANILLA_FIRST_SLOT_INDEX = 0
        private const val TE_INVENTORY_FIRST_SLOT_INDEX: Int = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT

        // THIS YOU HAVE TO DEFINE!
        private const val TE_INVENTORY_SLOT_COUNT = 1 // must be the number of slots you have!
    }
}