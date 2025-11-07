package net.Chidoziealways.everythingjapanese.block.entity.custom

import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.Design
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

class HangingScrollBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModBlockEntities.HANGING_SCROLL_BE, pos, state) {
    /** The current design applied to this scroll */
    var currentDesign: Design? = null

    // Access registry lazily
    val allDesigns: List<Design>
        get() = level?.registryAccess()?.lookupOrThrow(ModRegistries.DESIGN)?.stream()?.toList()
            ?: emptyList()


    // --------------------
    // Saving / Loading
    // --------------------
    override fun saveAdditional(output: ValueOutput) {
        super.saveAdditional(output)
        println("Saving HangingScrollBlockEntity at $blockPos with design=$currentDesign")
        currentDesign?.let { design ->
            output.store("design", Design.CODEC, design)
        }
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)
        currentDesign = input.read("design", Design.CODEC).orElse(null)
        println("Loading HangingScrollBlockEntity at $blockPos with design=$currentDesign")
        setChanged()  // mark dirty
        level?.sendBlockUpdated(worldPosition, blockState, blockState, 3) // sync to client
    }

    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag {
        // Send NBT to client
        return saveWithoutMetadata(registries)
    }

    override fun handleUpdateTag(input: ValueInput) {
        // Read NBT on client
        loadAdditional(input)
    }
}