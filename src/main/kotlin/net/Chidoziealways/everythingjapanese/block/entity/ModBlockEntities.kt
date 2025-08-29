package net.Chidoziealways.everythingjapanese.entity

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.block.entity.custom.ByoubuBlockEntity
import net.Chidoziealways.everythingjapanese.block.entity.custom.FusumaDoorBlockEntity
import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.Chidoziealways.everythingjapanese.entity.custom.GrowthChamberBlockEntity
import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModBlockEntities {
    val BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID)

    val SHOJI_DOOR_BE by BLOCK_ENTITIES.register("shoji_door_be") { ->
        BlockEntityType(BlockEntitySupplier { pPos: BlockPos, pBlockState: BlockState ->
            ShojiDoorBlockEntity(
                pPos,
                pBlockState) },
            mutableSetOf<Block>(ModBlocks.SHOJI_DOOR))
    }

    val FUSUMA_DOOR_BE by BLOCK_ENTITIES.register("fusuma_door_be") { ->
        BlockEntityType(BlockEntitySupplier { pPos: BlockPos, pBlockState: BlockState ->
            FusumaDoorBlockEntity(
                pPos,
                pBlockState) },
            mutableSetOf<Block>(ModBlocks.FUSUMA_DOOR))
    }

    val BYOUBU_BE by BLOCK_ENTITIES.register("byoubu_be") { ->
        BlockEntityType(BlockEntitySupplier { pos, state ->
            ByoubuBlockEntity(
                pos,
                state
            ) },
            mutableSetOf<Block>(ModBlocks.BYOUBU))
    }

    val PEDESTAL_BE by
        BLOCK_ENTITIES.register("pedestal_be") { ->
                BlockEntityType(BlockEntitySupplier { pPos: BlockPos, pBlockState: BlockState ->
                    PedestalBlockEntity(
                        pPos,
                        pBlockState
                    ) },
                    mutableSetOf<Block?>(ModBlocks.PEDESTAL))
        }

    val GROWTH_CHAMBER_BE by BLOCK_ENTITIES.register("growth_chamber_be") { ->
        BlockEntityType(
            BlockEntitySupplier { pPos: BlockPos, pState: BlockState ->
                GrowthChamberBlockEntity(
                    pPos,
                    pState
                ) },
            mutableSetOf<Block?>(ModBlocks.GROWTH_CHAMBER)
        )
    }

    fun register(eventBus: IEventBus) {
        BLOCK_ENTITIES.register(eventBus)
    }
}
