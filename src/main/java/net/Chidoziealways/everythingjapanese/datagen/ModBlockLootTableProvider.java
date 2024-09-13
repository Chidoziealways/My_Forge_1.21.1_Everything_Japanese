package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider( HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.PYRITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_PYRITE_BLOCK.get());
        dropSelf(ModBlocks.CHOCOLATE_CAKE.get());
        dropSelf(ModBlocks.HINOKI_MARUTA.get());
        dropSelf(ModBlocks.JAPANESE_CHEESECAKE.get());
        dropSelf(ModBlocks.STRIPPED_HINOKI_MARUTA.get());
        dropSelf(ModBlocks.WORKBENCH.get());

        this.add(ModBlocks.PYRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.PYRITE_ORE.get(), ModItems.RAW_PYRITE.get()));
        this.add(ModBlocks.PYRITE_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.PYRITE_DEEPSLATE_ORE.get(), ModItems.RAW_PYRITE.get(), 9, 20));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
