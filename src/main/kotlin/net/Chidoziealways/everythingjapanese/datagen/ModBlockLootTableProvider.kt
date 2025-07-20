package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.loot.BlockLootSubProvider
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
import net.minecraftforge.registries.RegistryObject

class ModBlockLootTableProvider(pRegistries: HolderLookup.Provider) : BlockLootSubProvider(
    kotlin.collections.mutableSetOf<net.minecraft.world.item.Item?>(),
    net.minecraft.world.flag.FeatureFlags.REGISTRY.allFlags(),
    pRegistries
) {
    override fun generate() {
        kotlin.io.println("Generating loot tables For Pyrite Block")
        dropSelf(ModBlocks.PYRITE_BLOCK.get())
        kotlin.io.println("2")
        dropSelf(ModBlocks.RAW_PYRITE_BLOCK.get())
        kotlin.io.println("3")
        dropSelf(ModBlocks.HINOKI_MARUTA.get())
        dropSelf(ModBlocks.CHAIR.get())
        kotlin.io.println("4")
        this.add(ModBlocks.CHOCOLATE_CAKE.get(), BlockLootSubProvider.noDrop())
        kotlin.io.println("5")
        dropSelf(ModBlocks.PYRITE_BUTTON.get())
        kotlin.io.println("6")
        dropSelf(ModBlocks.PYRITE_FENCE.get())
        kotlin.io.println("7")
        dropSelf(ModBlocks.PYRITE_FENCE_GATE.get())
        kotlin.io.println("8")
        dropSelf(ModBlocks.PYRITE_PRESSURE_PLATE.get())
        kotlin.io.println("9")
        dropSelf(ModBlocks.PYRITE_STAIRS.get())
        kotlin.io.println("10")
        dropSelf(ModBlocks.PYRITE_TRAPDOOR.get())
        kotlin.io.println("11")
        dropSelf(ModBlocks.PYRITE_WALL.get())
        kotlin.io.println("12")
        dropSelf(ModBlocks.PYRITE_LAMP.get())
        dropSelf(ModBlocks.NEPHRITE_BLOCK.get())
        kotlin.io.println("13")
        this.add(
            ModBlocks.PYRITE_ORE.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? ->
                createOreDrop(
                    ModBlocks.PYRITE_ORE.get(),
                    ModItems.RAW_PYRITE!!.get()
                )
            })
        kotlin.io.println("14")
        this.add(
            ModBlocks.PYRITE_DEEPSLATE_ORE.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? ->
                createMultipleOreDrops(
                    ModBlocks.PYRITE_DEEPSLATE_ORE.get()!!,
                    ModItems.RAW_PYRITE!!.get(),
                    9f,
                    20f
                )
            })

        this.add(
            ModBlocks.NEPHRITE_ORE.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? ->
                createOreDrop(
                    ModBlocks.NEPHRITE_ORE.get(),
                    ModItems.NEPHRITE!!.get()
                )
            })
        kotlin.io.println("14")
        this.add(
            ModBlocks.NEPHRITE_DEEPSLATE_ORE.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? ->
                createMultipleOreDrops(
                    ModBlocks.NEPHRITE_DEEPSLATE_ORE.get()!!,
                    ModItems.NEPHRITE!!.get(),
                    9f,
                    20f
                )
            })

        kotlin.io.println("15")
        this.add(
            ModBlocks.PYRITE_SLAB.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? -> createSlabItemTable(ModBlocks.PYRITE_SLAB.get()) })
        kotlin.io.println("16")
        this.add(
            ModBlocks.PYRITE_DOOR.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? -> createDoorTable(ModBlocks.PYRITE_DOOR.get()) })
        kotlin.io.println("17")

        val lootItemConditionBuilder: LootItemCondition.Builder =
            LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP.get())
                .setProperties(
                    StatePropertiesPredicate.Builder.properties().hasProperty(ModBlockStateProperties.AGE_4, 4)
                )

        this.add(
            ModBlocks.RICE_CROP.get(), this.createCropDrops(
                ModBlocks.RICE_CROP.get(),
                ModItems.RAW_RICE!!.get(), ModItems.RICE_SEEDS!!.get(), lootItemConditionBuilder
            )
        )

        val registrylookup: HolderLookup.RegistryLookup<Enchantment?> =
            this.registries.lookupOrThrow<Enchantment?>(Registries.ENCHANTMENT)

        this.add(
            ModBlocks.YAMAZAKI_BERRY_BUSH.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? ->
                this.applyExplosionDecay<LootTable.Builder?>(
                    block, LootTable.lootTable().withPool(
                        LootPool.lootPool().`when`(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.YAMAZAKI_BERRY_BUSH.get())
                                .setProperties(
                                    StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(SweetBerryBushBlock.AGE, 3)
                                )
                        ).add(LootItem.lootTableItem(ModItems.YAMAZAKI_BERRIES!!.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 3.0f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                    ).withPool(
                        LootPool.lootPool().`when`(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.YAMAZAKI_BERRY_BUSH.get())
                                .setProperties(
                                    StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(SweetBerryBushBlock.AGE, 2)
                                )
                        ).add(LootItem.lootTableItem(ModItems.YAMAZAKI_BERRIES.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(net.minecraft.world.item.enchantment.Enchantments.FORTUNE)))
                    )
                )
            })

        this.dropSelf(ModBlocks.HINOKI_MARUTA.get())
        this.dropSelf(ModBlocks.HINOKI_MOKUZAI.get())
        this.dropSelf(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
        this.dropSelf(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get())
        this.dropSelf(ModBlocks.HINOKI_BAN.get())
        this.dropSelf(ModBlocks.HINOKI_NAEGI.get())

        this.add(
            ModBlocks.HINOKI_HA.get(),
            java.util.function.Function { block: net.minecraft.world.level.block.Block? ->
                createLeavesDrops(
                    block,
                    ModBlocks.HINOKI_NAEGI.get(),
                    *BlockLootSubProvider.NORMAL_LEAVES_SAPLING_CHANCES
                )
            })

        this.dropSelf(ModBlocks.CHAIR.get())
        dropSelf(ModBlocks.PEDESTAL.get())
        dropSelf(ModBlocks.GROWTH_CHAMBER.get())
    }

    protected fun createMultipleOreDrops(
        pBlock: net.minecraft.world.level.block.Block,
        item: net.minecraft.world.item.Item,
        minDrops: kotlin.Float,
        maxDrops: kotlin.Float
    ): LootTable.Builder {
        val registrylookup: HolderLookup.RegistryLookup<Enchantment?> =
            this.registries.lookupOrThrow<Enchantment?>(net.minecraft.core.registries.Registries.ENCHANTMENT)
        return this.createSilkTouchDispatchTable(
            pBlock, this.applyExplosionDecay(
                pBlock, LootItem.lootTableItem(item)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                    .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(net.minecraft.world.item.enchantment.Enchantments.FORTUNE)))
            )
        )
    }

    override fun getKnownBlocks(): Iterable<Block?> {
        return Iterable {
            ModBlocks.BLOCKS.getEntries().stream()
                .map<net.minecraft.world.level.block.Block?> { obj: RegistryObject<Block?>? -> obj!!.get() }
                .iterator()
        }
    }
}
