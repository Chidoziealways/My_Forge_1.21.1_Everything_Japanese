package net.Chidoziealways.everythingjapanese.screen.custom.pedestal

import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.Chidoziealways.everythingjapanese.screen.ModMenuTypes
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraftforge.items.SlotItemHandler

class PedestalMenu(pContainerId: Int, inv: Inventory, blockEntity: BlockEntity?) :
    AbstractContainerMenu(ModMenuTypes.PEDESTAL_MENU!!.get(), pContainerId) {
    val blockEntity: PedestalBlockEntity
    private val level: net.minecraft.world.level.Level

    constructor(pContainerId: Int, inv: Inventory, extraData: FriendlyByteBuf) : this(
        pContainerId,
        inv,
        inv.player.level().getBlockEntity(extraData.readBlockPos())
    )

    init {
        this.blockEntity = (blockEntity as PedestalBlockEntity)
        this.level = inv.player.level()

        addPlayerInventory(inv)
        addPlayerHotBar(inv)

        this.addSlot(SlotItemHandler(this.blockEntity.inventory, 0, 80, 35))
    }

    override fun quickMoveStack(playerIn: net.minecraft.world.entity.player.Player, pIndex: kotlin.Int): ItemStack {
        val sourceSlot = slots.get(pIndex)!!
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY //EMPTY_ITEM

        val sourceStack: ItemStack = sourceSlot.item
        val copyOfSourceStack: ItemStack = sourceStack.copy()

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(
                    sourceStack,
                    TE_INVENTORY_FIRST_SLOT_INDEX,
                    TE_INVENTORY_FIRST_SLOT_INDEX
                            + TE_INVENTORY_SLOT_COUNT,
                    false
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
            kotlin.io.println("Invalid slotIndex:" + pIndex)
            return ItemStack.EMPTY
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY)
        } else {
            sourceSlot.setChanged()
        }
        sourceSlot.onTake(playerIn, sourceStack)
        return copyOfSourceStack
    }

    override fun stillValid(player: net.minecraft.world.entity.player.Player): kotlin.Boolean {
        return AbstractContainerMenu.stillValid(
            ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
            player, ModBlocks.PEDESTAL.get()
        )
    }


    private fun addPlayerInventory(playerInventory: Inventory) {
        for (i in 0..2) {
            for (l in 0..8) {
                this.addSlot(
                    net.minecraft.world.inventory.Slot(
                        playerInventory,
                        l + i * 9 + 9,
                        8 + l * 18,
                        84 + i * 18
                    )
                )
            }
        }
    }

    private fun addPlayerHotBar(playerInventory: Inventory) {
        for (i in 0..8) {
            this.addSlot(net.minecraft.world.inventory.Slot(playerInventory, i, 8 + i * 18, 142))
        }
    }

    companion object {
        // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
        // must assign a slot number to each of the slots used by the GUI.
        // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
        // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
        //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
        //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
        //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
        private const val HOTBAR_SLOT_COUNT = 9
        private const val PLAYER_INVENTORY_ROW_COUNT = 3
        private const val PLAYER_INVENTORY_COLUMN_COUNT = 9
        private val PLAYER_INVENTORY_SLOT_COUNT: kotlin.Int =
            PedestalMenu.Companion.PLAYER_INVENTORY_COLUMN_COUNT * PedestalMenu.Companion.PLAYER_INVENTORY_ROW_COUNT
        private val VANILLA_SLOT_COUNT: kotlin.Int =
            PedestalMenu.Companion.HOTBAR_SLOT_COUNT + PedestalMenu.Companion.PLAYER_INVENTORY_SLOT_COUNT
        private const val VANILLA_FIRST_SLOT_INDEX = 0
        private val TE_INVENTORY_FIRST_SLOT_INDEX: kotlin.Int =
            PedestalMenu.Companion.VANILLA_FIRST_SLOT_INDEX + PedestalMenu.Companion.VANILLA_SLOT_COUNT

        // THIS YOU HAVE TO DEFINE!
        private const val TE_INVENTORY_SLOT_COUNT = 1 // must be the number of slots you have!
    }
}
