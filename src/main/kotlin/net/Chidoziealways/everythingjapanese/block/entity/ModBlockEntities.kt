package net.Chidoziealways.everythingjapanese.entity

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.entity.custom.GrowthChamberBlockEntity
import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.registerObject
import java.util.Set
import java.util.function.Supplier

object ModBlockEntities {
    val BLOCK_ENTITIES: DeferredRegister<BlockEntityType<*>?> =
        DeferredRegister.create<BlockEntityType<*>?>(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID)

    val PEDESTAL_BE =
        BLOCK_ENTITIES.registerObject("pedestal_be") {
            val type: BlockEntityType<PedestalBlockEntity?> =
                BlockEntityType<PedestalBlockEntity?>(BlockEntitySupplier { pPos: BlockPos, pBlockState: BlockState ->
                    PedestalBlockEntity(
                        pPos,
                        pBlockState
                    )
                }, mutableSetOf<Block?>(ModBlocks.PEDESTAL.get()))
            Registry.register<BlockEntityType<*>?, BlockEntityType<PedestalBlockEntity?>?>(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "pedestal_be"), type
            )
        }

    val GROWTH_CHAMBER_BE =
        BLOCK_ENTITIES.registerObject("growth_chamber_be") {
            val type: BlockEntityType<GrowthChamberBlockEntity?> = BlockEntityType<GrowthChamberBlockEntity?>(
                BlockEntitySupplier { pPos: BlockPos, pState: BlockState ->
                    GrowthChamberBlockEntity(
                        pPos,
                        pState
                    )
                },
                mutableSetOf<Block?>(ModBlocks.GROWTH_CHAMBER.get())
            )
            Registry.register<BlockEntityType<*>?, BlockEntityType<GrowthChamberBlockEntity?>?>(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "growth_chamber_be"), type
            )
        }

    fun register(eventBus: BusGroup?) {
        BLOCK_ENTITIES.register(eventBus)
    }
}
