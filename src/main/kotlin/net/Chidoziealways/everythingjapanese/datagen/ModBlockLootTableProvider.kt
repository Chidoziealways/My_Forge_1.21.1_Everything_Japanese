package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SweetBerryBushBlock
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.Objects

class ModBlockLootTableProvider(pRegistries: HolderLookup.Provider) : BlockLootSubProvider(
    mutableSetOf<Item>(),
    FeatureFlags.REGISTRY.allFlags(),
    pRegistries
) {
    override fun generate() {
        dropSelf(ModBlocks.PYRITE_BLOCK)
        dropSelf(ModBlocks.RAW_PYRITE_BLOCK)
        dropSelf(ModBlocks.HINOKI_MARUTA)
        this.add(ModBlocks.CHOCOLATE_CAKE, noDrop())
        dropSelf(ModBlocks.PYRITE_BUTTON)
        dropSelf(ModBlocks.TATAMI_MAT)
        dropSelf(ModBlocks.PYRITE_FENCE)
        dropSelf(ModBlocks.PYRITE_FENCE_GATE)
        dropSelf(ModBlocks.PYRITE_PRESSURE_PLATE)
        dropSelf(ModBlocks.PYRITE_STAIRS)
        dropSelf(ModBlocks.WASHI_WINDOW)
        dropSelf(ModBlocks.SHOJI_DOOR)
        dropSelf(ModBlocks.CHABUDAI)
        dropSelf(ModBlocks.FUSUMA_DOOR)
        dropSelf(ModBlocks.PYRITE_TRAPDOOR)
        //dropSelf(ModBlocks.BYOUBU)
        dropSelf(ModBlocks.MONEY_VAULT_BLOCK)
        dropSelf(ModBlocks.PYRITE_WALL)
        dropSelf(ModBlocks.PYRITE_LAMP)
        dropSelf(ModBlocks.NEPHRITE_BLOCK)
        this.add(
            ModBlocks.PYRITE_ORE
        ) { block: Block? ->
            createOreDrop(
                ModBlocks.PYRITE_ORE,
                ModItems.RAW_PYRITE
            )
        }
        this.add(
            ModBlocks.PYRITE_DEEPSLATE_ORE
        ) { block: Block? ->
            createMultipleOreDrops(
                ModBlocks.PYRITE_DEEPSLATE_ORE,
                ModItems.RAW_PYRITE,
                9f,
                20f
            )
        }

        this.add(
            ModBlocks.NEPHRITE_ORE
        ) { block: Block? ->
            createOreDrop(
                ModBlocks.NEPHRITE_ORE,
                ModItems.NEPHRITE
            )
        }
        this.add(
            ModBlocks.NEPHRITE_DEEPSLATE_ORE
        ) { block: Block? ->
            createMultipleOreDrops(
                ModBlocks.NEPHRITE_DEEPSLATE_ORE,
                ModItems.NEPHRITE,
                9f,
                20f
            )
        }
        this.add(
            ModBlocks.PYRITE_SLAB
        ) { block: Block? -> createSlabItemTable(ModBlocks.PYRITE_SLAB) }
        this.add(
            ModBlocks.PYRITE_DOOR
        ) { block: Block? -> createDoorTable(ModBlocks.PYRITE_DOOR) }
        val lootItemConditionBuilder: LootItemCondition.Builder =
            LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP)
                .setProperties(
                    StatePropertiesPredicate.Builder.properties().hasProperty(ModBlockStateProperties.AGE_4, 4)
                )

        this.add(
            ModBlocks.RICE_CROP, this.createCropDrops(
                ModBlocks.RICE_CROP,
                ModItems.RAW_RICE, ModItems.RICE_SEEDS, lootItemConditionBuilder
            )
        )
        val registrylookup: HolderLookup.RegistryLookup<Enchantment?> =
            this.registries.lookupOrThrow<Enchantment?>(Registries.ENCHANTMENT)
        this.add(
            ModBlocks.YAMAZAKI_BERRY_BUSH
        ) { block: Block? ->
            this.applyExplosionDecay<LootTable.Builder?>(
                block, LootTable.lootTable().withPool(
                    LootPool.lootPool().`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.YAMAZAKI_BERRY_BUSH)
                            .setProperties(
                                StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(SweetBerryBushBlock.AGE, 3)
                            )
                    ).add(LootItem.lootTableItem(ModItems.YAMAZAKI_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 3.0f)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(
                    LootPool.lootPool().`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.YAMAZAKI_BERRY_BUSH)
                            .setProperties(
                                StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(SweetBerryBushBlock.AGE, 2)
                            )
                    ).add(LootItem.lootTableItem(ModItems.YAMAZAKI_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(net.minecraft.world.item.enchantment.Enchantments.FORTUNE)))
                )
            )
        }

        this.dropSelf(ModBlocks.HINOKI_MARUTA)
        this.dropSelf(ModBlocks.HINOKI_MOKUZAI)
        this.dropSelf(ModBlocks.STRIPPED_HINOKI_MARUTA)
        this.dropSelf(ModBlocks.STRIPPED_HINOKI_MOKUZAI)
        this.dropSelf(ModBlocks.HINOKI_BAN)
        this.dropSelf(ModBlocks.HINOKI_NAEGI)

        this.add(
            ModBlocks.HINOKI_HA
        ) { block: Block ->
            createLeavesDrops(
                block,
                ModBlocks.HINOKI_NAEGI,
                *NORMAL_LEAVES_SAPLING_CHANCES
            )
        }

        this.dropSelf(ModBlocks.CHAIR)
        dropSelf(ModBlocks.ZABUTON_BLUE)
        dropSelf(ModBlocks.ZABUTON_RED)
        dropSelf(ModBlocks.ZABUTON_GREEN)
        dropSelf(ModBlocks.PEDESTAL)
        dropSelf(ModBlocks.GROWTH_CHAMBER)
    }

    override fun getKnownBlocks(): Iterable<Block> {
        // Only return your mod's blocks
        return BuiltInRegistries.BLOCK.stream()
            .filter { block: Block -> Objects.equals(BuiltInRegistries.BLOCK.getKey(block).namespace, MOD_ID) }
            .toList()
    }


    protected fun createMultipleOreDrops(
        pBlock: Block,
        item: Item,
        minDrops: Float,
        maxDrops: Float
    ): LootTable.Builder {
        val registrylookup: HolderLookup.RegistryLookup<Enchantment?> =
            this.registries.lookupOrThrow<Enchantment?>(Registries.ENCHANTMENT)
        return this.createSilkTouchDispatchTable(
            pBlock, this.applyExplosionDecay(
                pBlock, LootItem.lootTableItem(item)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                    .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(net.minecraft.world.item.enchantment.Enchantments.FORTUNE)))
            )
        )
    }
}
