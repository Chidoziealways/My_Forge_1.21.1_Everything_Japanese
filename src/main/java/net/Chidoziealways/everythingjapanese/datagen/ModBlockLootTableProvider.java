package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.custom.ModdedCakeBlock;
import net.Chidoziealways.everythingjapanese.block.custom.RiceCropBlock;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider( HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        System.out.println("Generating loot tables For Pyrite Block");
        dropSelf(ModBlocks.PYRITE_BLOCK.get());
        System.out.println("2");
        dropSelf(ModBlocks.RAW_PYRITE_BLOCK.get());
        System.out.println("3");
        dropSelf(ModBlocks.HINOKI_MARUTA.get());
        dropSelf(ModBlocks.CHAIR.get());
        System.out.println("4");
        this.add(ModBlocks.CHOCOLATE_CAKE.get(), noDrop());
        System.out.println("5");
        dropSelf(ModBlocks.PYRITE_BUTTON.get());
        System.out.println("6");
        dropSelf(ModBlocks.PYRITE_FENCE.get());
        System.out.println("7");
        dropSelf(ModBlocks.PYRITE_FENCE_GATE.get());
        System.out.println("8");
        dropSelf(ModBlocks.PYRITE_PRESSURE_PLATE.get());
        System.out.println("9");
        dropSelf(ModBlocks.PYRITE_STAIRS.get());
        System.out.println("10");
        dropSelf(ModBlocks.PYRITE_TRAPDOOR.get());
        System.out.println("11");
        dropSelf(ModBlocks.PYRITE_WALL.get());
        System.out.println("12");
        dropSelf(ModBlocks.PYRITE_LAMP.get());
        dropSelf(ModBlocks.NEPHRITE_BLOCK.get());
        System.out.println("13");
        this.add(ModBlocks.PYRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.PYRITE_ORE.get(), ModItems.RAW_PYRITE.get()));
        System.out.println("14");
        this.add(ModBlocks.PYRITE_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.PYRITE_DEEPSLATE_ORE.get(), ModItems.RAW_PYRITE.get(), 9, 20));

        this.add(ModBlocks.NEPHRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.NEPHRITE_ORE.get(), ModItems.NEPHRITE.get()));
        System.out.println("14");
        this.add(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get(), ModItems.NEPHRITE.get(), 9, 20));

        System.out.println("15");
        this.add(ModBlocks.PYRITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.PYRITE_SLAB.get()));
        System.out.println("16");
        this.add(ModBlocks.PYRITE_DOOR.get(),
                block -> createDoorTable(ModBlocks.PYRITE_DOOR.get()));
        System.out.println("17");

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropBlock.AGE, 4));

        this.add(ModBlocks.RICE_CROP.get(), this.createCropDrops(ModBlocks.RICE_CROP.get(),
                ModItems.RAW_RICE.get(), ModItems.RICE_SEEDS.get(), lootItemConditionBuilder));

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.YAMAZAKI_BERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.YAMAZAKI_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.YAMAZAKI_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.YAMAZAKI_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.YAMAZAKI_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

        this.dropSelf(ModBlocks.HINOKI_MARUTA.get());
        this.dropSelf(ModBlocks.HINOKI_MOKUZAI.get());
        this.dropSelf(ModBlocks.STRIPPED_HINOKI_MARUTA.get());
        this.dropSelf(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get());
        this.dropSelf(ModBlocks.HINOKI_BAN.get());
        this.dropSelf(ModBlocks.HINOKI_NAEGI.get());

        this.add(ModBlocks.HINOKI_HA.get(), block ->
                createLeavesDrops(block, ModBlocks.HINOKI_NAEGI.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.CHAIR.get());
        dropSelf(ModBlocks.PEDESTAL.get());
        dropSelf(ModBlocks.GROWTH_CHAMBER.get());
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
