package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.loot.AddItemModifier
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraftforge.common.data.GlobalLootModifierProvider
import net.minecraftforge.common.loot.LootTableIdCondition
import java.util.concurrent.CompletableFuture

class ModGlobalLootModifierProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider?>) :
    GlobalLootModifierProvider(output, MOD_ID, registries) {
    override fun start(provider: HolderLookup.Provider) {
        this.add<AddItemModifier?>(
            "rice_seeds_from_short_grass",
            AddItemModifier(
                arrayOf<LootItemCondition>(
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                    LootItemRandomChanceCondition.randomChance(0.5f).build()
                ) as Array<LootItemCondition?>, ModItems.RICE_SEEDS!!.get()
            )
        )
        this.add<AddItemModifier?>(
            "rice_seeds_from_tall_grass",
            AddItemModifier(
                arrayOf<LootItemCondition>(
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                    LootItemRandomChanceCondition.randomChance(0.9f).build()
                ) as Array<LootItemCondition?>, ModItems.RICE_SEEDS!!.get()
            )
        )
        this.add<AddItemModifier?>(
            "chisel_from_jungle_temple",
            AddItemModifier(
                arrayOf<LootItemCondition>(
                    LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build()
                ) as Array<LootItemCondition?>, ModItems.CHISEL!!.get()
            )
        )
        this.add<AddItemModifier?>(
            "small_fireballjutsu_from_jungle_temple",
            AddItemModifier(
                arrayOf<LootItemCondition>(
                    LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build()
                ) as Array<LootItemCondition?>, ModItems.SMALL_FIREBALL_SCROLL!!.get()
            )
        )
        this.add<AddItemModifier?>(
            "large_fireballjutsu_from_ancient_city",
            AddItemModifier(
                arrayOf<LootItemCondition>(
                    LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ancient_city")).build()
                ) as Array<LootItemCondition?>, ModItems.LARGE_FIREBALL_SCROLL!!.get()
            )
        )
        this.add<AddItemModifier?>(
            "small_windballjutsu_from_end_city",
            AddItemModifier(
                arrayOf<LootItemCondition>(
                    LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build()
                ) as Array<LootItemCondition?>, ModItems.WINDBALL_SCROLL!!.get()
            )
        )
        this.add<AddItemModifier?>(
            "rice_from_creeper",
            AddItemModifier(
                arrayOf<LootItemCondition>(
                    LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/creeper"))
                        .and(LootItemRandomChanceCondition.randomChance(0.29f)).build()
                ) as Array<LootItemCondition?>,
                ModItems.RICE!!.get()
            )
        )
    }
}
