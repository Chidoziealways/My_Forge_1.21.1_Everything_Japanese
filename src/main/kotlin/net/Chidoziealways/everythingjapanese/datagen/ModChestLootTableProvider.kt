package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.LootTableSubProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import java.util.function.BiConsumer

class ModChestLootTableProvider(registries: HolderLookup.Provider): LootTableSubProvider {
    override fun generate(builder: BiConsumer<ResourceKey<LootTable?>?, LootTable.Builder?>) {
        builder.accept(
            ModLootTables.DOJO,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(3.0F))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(5))
                        .add(LootItem.lootTableItem { JModItems.WINDBALL_SCROLL }.setWeight(20))
                        .add(LootItem.lootTableItem(JModItems.FIREBALL_SCROLL).setWeight(10))
                        .add(LootItem.lootTableItem(JModItems.CHIRETSU_SHO_SCROLL).setWeight(10))
                        .add(LootItem.lootTableItem(JModItems.EKIRETSU_SHO_SCROLL).setWeight(9))
                        .add(LootItem.lootTableItem { Items.IRON_SWORD }.setWeight(30))
                )
        )
    }
}