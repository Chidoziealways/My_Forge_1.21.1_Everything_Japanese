package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, EverythingJapanese.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider provider) {
        this.add("rice_seeds_from_short_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build() }, ModItems.RICE_SEEDS.get()));
        this.add("rice_seeds_from_tall_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.9f).build() }, ModItems.RICE_SEEDS.get()));
        this.add("chisel_from_jungle_temple",
                new AddItemModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build()
                }, ModItems.CHISEL.get()));
        this.add("rice_from_creeper",
                new AddItemModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/creeper"))
                                .and(LootItemRandomChanceCondition.randomChance(0.29f)).build() },
                        ModItems.RICE.get()));
    }
}
