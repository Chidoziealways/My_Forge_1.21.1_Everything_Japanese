package net.Chidoziealways.everythingjapanese.block.entity;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.entity.custom.GrowthChamberBlockEntity;
import net.Chidoziealways.everythingjapanese.block.entity.custom.PedestalBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EverythingJapanese.MOD_ID);

    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", () -> {
              BlockEntityType<PedestalBlockEntity> type = new BlockEntityType<>(PedestalBlockEntity::new, Set.of(ModBlocks.PEDESTAL.get()));
              return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                      ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pedestal_be"), type);
            });

    public static final RegistryObject<BlockEntityType<GrowthChamberBlockEntity>> GROWTH_CHAMBER_BE =
            BLOCK_ENTITIES.register("growth_chamber_be", () -> {
                BlockEntityType<GrowthChamberBlockEntity> type = new BlockEntityType<>(GrowthChamberBlockEntity::new, Set.of(ModBlocks.GROWTH_CHAMBER.get()));
                return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                        ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "growth_chamber_be"), type);
            });

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
