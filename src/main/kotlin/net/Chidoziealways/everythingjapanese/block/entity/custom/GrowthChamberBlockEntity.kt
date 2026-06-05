package net.Chidoziealways.everythingjapanese.entity.custom

import com.mojang.serialization.Codec
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.recipe.GrowthChamberRecipe
import net.Chidoziealways.everythingjapanese.recipe.GrowthChamberRecipeInput
import net.Chidoziealways.everythingjapanese.recipe.ModRecipes
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberMenu
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.ItemStackHandler
import java.util.*
import java.util.function.Consumer

class GrowthChamberBlockEntity(pPos: BlockPos, pBlockState: BlockState) :
    BlockEntity(ModBlockEntities.GROWTH_CHAMBER_BE, pPos, pBlockState), MenuProvider {
    val itemHandler: ItemStackHandler = object : ItemStackHandler(2) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
            if (!level!!.isClientSide()) {
                level?.sendBlockUpdated(blockPos, blockState, blockState, 3)
            }
        }
    }

    protected val data: ContainerData
    private var progress = 0
    private var maxProgress = 72

    init {
        data = object : ContainerData {
            override fun get(i: Int): Int {
                return when (i) {
                    0 -> this@GrowthChamberBlockEntity.progress
                    1 -> this@GrowthChamberBlockEntity.maxProgress
                    else -> 0
                }
            }

            override fun set(i: Int, i1: Int) {
                when (i) {
                    0 -> {
                        this@GrowthChamberBlockEntity.progress = i1
                        this@GrowthChamberBlockEntity.maxProgress = i1
                    }

                    1 -> this@GrowthChamberBlockEntity.maxProgress = i1
                }
            }

            override fun getCount(): Int {
                return 2
            }
        }
    }

    override fun preRemoveSideEffects(p_397404_: BlockPos, p_395805_: BlockState) {
        val inventory = SimpleContainer(itemHandler.getSlots())
        for (i in 0..<itemHandler.getSlots()) {
            inventory.setItem(i, itemHandler.getStackInSlot(i))
        }
        val levl = this.level ?: return
        Containers.dropContents(levl, this.worldPosition, inventory)
        super.preRemoveSideEffects(p_397404_, p_395805_)
    }

    override fun saveAdditional(output: ValueOutput) {
        itemHandler.serialize(output)
        output.store<Int>("growth_chamber.progress", Codec.INT, progress)
        output.store<Int>("growth_chamber.max_progress", Codec.INT, maxProgress)

        super.saveAdditional(output)
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)

        // 1.21.5+: itemHandler likely still uses `deserializeNBT`, but watch for capability changes
        itemHandler.deserialize(input)

        this.progress = input.read<Int>("growth_chamber.progress", Codec.INT).orElse(0)
        this.maxProgress = input.read<Int>("growth_chamber.max_progress", Codec.INT).orElse(0)
    }

    override fun createMenu(i: Int, inventory: Inventory, player: Player): AbstractContainerMenu? {
        return GrowthChamberMenu(i, inventory, this, this.data)
    }

    fun tick(level: Level, blockPos: BlockPos, blockState: BlockState) {
        if (hasRecipe()) {
            increaseCraftingProgress()
            BlockEntity.setChanged(level, blockPos, blockState)

            if (hasCraftingFinished()) {
                craftItem()
                resetProgress()
            }
        } else {
            resetProgress()
        }
    }

    private fun resetProgress() {
        this.progress = 0
        this.maxProgress = 72
    }

    private fun craftItem() {
        val recipe: Optional<RecipeHolder<GrowthChamberRecipe>> = this.currentRecipe as Optional<RecipeHolder<GrowthChamberRecipe>>
        val output: ItemStack? = recipe.get().value().output

        itemHandler.extractItem(INPUT_SLOT, 1, false)
        itemHandler.setStackInSlot(
            OUTPUT_SLOT, ItemStack(
                output!!.item,
                itemHandler.getStackInSlot(OUTPUT_SLOT).count + output.count
            )
        )
    }

    private fun hasCraftingFinished(): Boolean {
        return this.progress >= this.maxProgress
    }

    private fun increaseCraftingProgress() {
        progress++
    }

    private fun hasRecipe(): Boolean {
        val recipe: Optional<RecipeHolder<GrowthChamberRecipe>> = this.currentRecipe as Optional<RecipeHolder<GrowthChamberRecipe>>
        if (recipe.isEmpty()) {
            return false
        }

        val output: ItemStack? = recipe.get().value().output
        return canInsertItemIntoOutputSlot(output)
                && canInsertAmountIntoOutputSlot(output!!.count)
    }

    private val currentRecipe: Optional<out RecipeHolder<out GrowthChamberRecipe>>
        get() {
            (level as? ServerLevel)?.let {
                return it?.server?.recipeManager
                    ?.getRecipeFor(
                        ModRecipes.GROWTH_CHAMBER_TYPE,
                        GrowthChamberRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)),
                        it
                    ) ?: return@let
            }
            return Optional.empty<RecipeHolder<GrowthChamberRecipe>>()
        }

    private fun canInsertItemIntoOutputSlot(output: ItemStack?): Boolean {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT)
            .item === output!!.item
    }

    private fun canInsertAmountIntoOutputSlot(count: Int): Boolean {
        val maxCount = if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) 64 else itemHandler.getStackInSlot(
            OUTPUT_SLOT
        ).getMaxStackSize()
        val currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()

        return maxCount >= currentCount + count
    }

    override fun getUpdateTag(pRegistries: HolderLookup.Provider): CompoundTag {
        return saveWithoutMetadata(pRegistries)
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener> {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    override fun getDisplayName(): Component {
        return Component.translatable("block.everythingjapanese.growth_chamber")
    }

    companion object {
        private const val INPUT_SLOT = 0
        private const val OUTPUT_SLOT = 1
    }
}
