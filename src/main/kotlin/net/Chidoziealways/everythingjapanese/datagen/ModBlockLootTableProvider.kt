package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.minecraft.advancements.criterion.StatePropertiesPredicate
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
        dropSelf(JModBlocks.PYRITE_BLOCK)
        dropSelf(JModBlocks.RAW_PYRITE_BLOCK)
        dropSelf(JModBlocks.HINOKI_MARUTA)
        this.add(JModBlocks.CHOCOLATE_CAKE, noDrop())
        dropSelf(JModBlocks.PYRITE_BUTTON)
        dropSelf(JModBlocks.HINOKI_BUTTON)
        dropSelf(JModBlocks.TATAMI_MAT)
        dropSelf(JModBlocks.PYRITE_FENCE)
        dropSelf(JModBlocks.CURSED_BLOCK)
        dropSelf(JModBlocks.PYRITE_FENCE_GATE)
        dropSelf(JModBlocks.PYRITE_PRESSURE_PLATE)
        dropSelf(JModBlocks.HANGING_SCROLL)
        dropSelf(JModBlocks.PYRITE_STAIRS)
        dropSelf(JModBlocks.JAPANESE_FLAG)
        //dropSelf(JModBlocks.CALLIGRAPHY_TABLE)
        dropSelf(JModBlocks.HINOKI_FENCE)
        dropSelf(JModBlocks.HINOKI_FENCE_GATE)
        dropSelf(JModBlocks.HINOKI_PRESSURE_PLATE)
        dropSelf(JModBlocks.HINOKI_STAIRS)
        dropSelf(JModBlocks.WASHI_WINDOW)
        dropSelf(JModBlocks.SHOJI_WINDOW)
        dropSelf(JModBlocks.SHOJI_DOOR)
        dropSelf(JModBlocks.CHABUDAI)
        dropSelf(JModBlocks.FUSUMA_DOOR)
        dropSelf(JModBlocks.PYRITE_TRAPDOOR)
        //dropSelf(ModBlocks.BYOUBU)
        dropSelf(JModBlocks.MONEY_VAULT_BLOCK)
        dropSelf(JModBlocks.PYRITE_WALL)
        dropSelf(JModBlocks.PYRITE_LAMP)
        dropSelf(JModBlocks.NEPHRITE_BLOCK)
        this.add(
            JModBlocks.PYRITE_ORE
        ) { block: Block? ->
            createOreDrop(
                JModBlocks.PYRITE_ORE,
                JModItems.RAW_PYRITE
            )
        }
        this.add(
            JModBlocks.PYRITE_DEEPSLATE_ORE
        ) { block: Block? ->
            createMultipleOreDrops(
                JModBlocks.PYRITE_DEEPSLATE_ORE,
                JModItems.RAW_PYRITE,
                9f,
                20f
            )
        }

        this.add(
            JModBlocks.NEPHRITE_ORE
        ) { block: Block? ->
            createOreDrop(
                JModBlocks.NEPHRITE_ORE,
                JModItems.NEPHRITE
            )
        }
        this.add(
            JModBlocks.NEPHRITE_DEEPSLATE_ORE
        ) { block: Block? ->
            createMultipleOreDrops(
                JModBlocks.NEPHRITE_DEEPSLATE_ORE,
                JModItems.NEPHRITE,
                9f,
                20f
            )
        }
        this.add(
            JModBlocks.PYRITE_SLAB
        ) { block: Block? -> createSlabItemTable(JModBlocks.PYRITE_SLAB) }
        this.add(
            JModBlocks.HINOKI_SLAB
        ) { _ -> createSlabItemTable(JModBlocks.HINOKI_SLAB) }
        this.add(
            JModBlocks.PYRITE_DOOR
        ) { block: Block? -> createDoorTable(JModBlocks.PYRITE_DOOR) }
        val lootItemConditionBuilder: LootItemCondition.Builder =
            LootItemBlockStatePropertyCondition.hasBlockStateProperties(JModBlocks.RICE_CROP)
                .setProperties(
                    StatePropertiesPredicate.Builder.properties().hasProperty(ModBlockStateProperties.AGE_4, 4)
                )

        this.add(
            JModBlocks.RICE_CROP, this.createCropDrops(
                JModBlocks.RICE_CROP,
                JModItems.RAW_RICE, JModItems.RICE_SEEDS, lootItemConditionBuilder
            )
        )
        val registrylookup: HolderLookup.RegistryLookup<Enchantment> =
            this.registries.lookupOrThrow<Enchantment>(Registries.ENCHANTMENT)
        this.add(
            JModBlocks.YAMAZAKI_BERRY_BUSH
        ) { block: Block ->
            this.applyExplosionDecay<LootTable.Builder>(
                block, LootTable.lootTable().withPool(
                    LootPool.lootPool().`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(JModBlocks.YAMAZAKI_BERRY_BUSH)
                            .setProperties(
                                StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(SweetBerryBushBlock.AGE, 3)
                            )
                    ).add(LootItem.lootTableItem(JModItems.YAMAZAKI_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 3.0f)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(
                    LootPool.lootPool().`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(JModBlocks.YAMAZAKI_BERRY_BUSH)
                            .setProperties(
                                StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(SweetBerryBushBlock.AGE, 2)
                            )
                    ).add(LootItem.lootTableItem(JModItems.YAMAZAKI_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(net.minecraft.world.item.enchantment.Enchantments.FORTUNE)))
                )
            )
        }

        this.dropSelf(JModBlocks.HINOKI_MARUTA)
        this.dropSelf(JModBlocks.HINOKI_MOKUZAI)
        this.dropSelf(JModBlocks.STRIPPED_HINOKI_MARUTA)
        this.dropSelf(JModBlocks.STRIPPED_HINOKI_MOKUZAI)
        this.dropSelf(JModBlocks.HINOKI_BAN)
        this.dropSelf(JModBlocks.HINOKI_NAEGI)

        this.add(
            JModBlocks.HINOKI_HA
        ) { block: Block ->
            createLeavesDrops(
                block,
                JModBlocks.HINOKI_NAEGI,
                *NORMAL_LEAVES_SAPLING_CHANCES
            )
        }

        this.dropSelf(JModBlocks.CHAIR)
        dropSelf(JModBlocks.ZABUTON_BLUE)
        dropSelf(JModBlocks.ZABUTON_RED)
        dropSelf(JModBlocks.ZABUTON_GREEN)
        dropSelf(JModBlocks.PEDESTAL)
        dropSelf(JModBlocks.GROWTH_CHAMBER)
    }

    override fun getKnownBlocks(): Iterable<Block> {
        // Only return your mod's blocks
        return BuiltInRegistries.BLOCK.stream()
            .filter { block: Block -> Objects.equals(BuiltInRegistries.BLOCK.getKey(block).namespace, JAPANESE_MOD_ID) }
            .toList()
    }


    protected fun createMultipleOreDrops(
        pBlock: Block,
        item: Item,
        minDrops: Float,
        maxDrops: Float
    ): LootTable.Builder {
        val registrylookup: HolderLookup.RegistryLookup<Enchantment> =
            this.registries.lookupOrThrow<Enchantment>(Registries.ENCHANTMENT)
        return this.createSilkTouchDispatchTable(
            pBlock, this.applyExplosionDecay(
                pBlock, LootItem.lootTableItem(item)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                    .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(net.minecraft.world.item.enchantment.Enchantments.FORTUNE)))
            )
        )
    }
}
