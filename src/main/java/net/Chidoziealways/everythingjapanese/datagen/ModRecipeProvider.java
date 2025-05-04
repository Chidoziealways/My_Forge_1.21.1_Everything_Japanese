package net.Chidoziealways.everythingjapanese.datagen;

import com.mojang.datafixers.util.Pair;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.trim.ModTrimPatterns;
import net.Chidoziealways.everythingjapanese.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final Logger log = LoggerFactory.getLogger(ModRecipeProvider.class);

    public ModRecipeProvider(HolderLookup.Provider lookup, RecipeOutput recipeOutput) {
        super(lookup, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> providerCompletableFuture) {
            super(output, providerCompletableFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
            return new ModRecipeProvider(pRegistries, pOutput);
        }

        @Override
        public String getName() {
            return "Recipes";
        }
    }

    public static Stream<ModRecipeProvider.TrimTemplate> smithingTrims() {
        return Stream.of(
                Pair.of(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, ModTrimPatterns.KOI_FISH)
        ).map(registryObjectResourceKeyPair -> {
           RegistryObject<Item> item = (RegistryObject<Item>) registryObjectResourceKeyPair.getFirst();
           ResourceKey<TrimPattern> resourceKey = (ResourceKey<TrimPattern>) registryObjectResourceKeyPair.getSecond();
           ResourceKey<Recipe<?>> resourceKey1 = ResourceKey.create(
                   Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, getItemName(item.get()) + "_smithing_trim")
           );
           return new ModRecipeProvider.TrimTemplate(item, resourceKey, resourceKey1);
        });
    }

    public record TrimTemplate(RegistryObject<Item> template, ResourceKey<TrimPattern> patternId, ResourceKey<Recipe<?>> recipeId) {
    }

    @Override
    protected void buildRecipes() {
        List<ItemLike> PYRITE_SMELTABLES = List.of(
                        ModItems.RAW_PYRITE.get(),
                        ModBlocks.RAW_PYRITE_BLOCK.get(),
                        ModBlocks.PYRITE_ORE.get(),
                        ModBlocks.PYRITE_DEEPSLATE_ORE.get());

        saveShapedRecipe(
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEPHRITE_BLOCK.get())
                        .pattern("AAA")
                        .pattern("AAA")
                        .pattern("AAA")
                        .define('A', ModItems.NEPHRITE.get())
                , ModItems.NEPHRITE.get());

        saveShapedRecipe(
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PYRITE_BLOCK.get())
                        .pattern("AAA")
                        .pattern("AAA")
                        .pattern("AAA")
                        .define('A', ModItems.RAW_PYRITE.get())
                , ModItems.RAW_PYRITE.get());

        saveShapedRecipe(
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BLOCK.get())
                        .pattern("AAA")
                        .pattern("AAA")
                        .pattern("AAA")
                        .define('A', ModItems.PYRITE_INGOT.get())
        , ModItems.PYRITE_INGOT.get());

        this.copySmithingTemplate(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModBlocks.PYRITE_BLOCK.get());

        saveShapedRecipe(
                shaped(RecipeCategory.MISC, ModBlocks.GROWTH_CHAMBER.get())
                        .pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.IRON_INGOT)
                        .define('B', Items.REDSTONE)
                        .define('C', Items.CRAFTING_TABLE)
                , Items.CRAFTING_TABLE);

        saveShapedRecipe(
                shaped(RecipeCategory.MISC, ModBlocks.PEDESTAL.get())
                        .pattern("ABA")
                        .pattern("BAB")
                        .pattern("ABA")
                        .define('A', Blocks.CHEST)
                        .define('B', Blocks.STONE)
                , Blocks.CHEST.asItem());

        saveShapedRecipe(
                shaped(RecipeCategory.FOOD, ModBlocks.CHOCOLATE_CAKE.get())
                        .pattern("MMM")
                        .pattern("SES")
                        .pattern("AWA")
                        .define('M', Items.MILK_BUCKET)
                        .define('S', Items.SUGAR)
                        .define('E', Items.EGG)
                        .define('W', Items.WHEAT)
                        .define('A', Items.COCOA_BEANS)
                , Items.COCOA_BEANS);

        saveShapedRecipe(
                shaped(RecipeCategory.MISC, ModItems.CHISEL.get())
                        .pattern("ABC")
                        .pattern("EDE")
                        .pattern("CBA")
                        .define('A', Items.WIND_CHARGE)
                        .define('B', ModItems.PYRITE_INGOT.get())
                        .define('C', Items.BLAZE_POWDER)
                        .define('D', Items.STICK)
                        .define('E', Items.GHAST_TEAR)

                , Items.GHAST_TEAR);

        saveShapelessRecipe(
                shapeless(RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 9)
                        .requires(ModBlocks.PYRITE_BLOCK.get())
                , ModBlocks.PYRITE_BLOCK.get().asItem());

        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, ModBlocks.TRANSFORMER_BLOCK.get())
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern("AAA")
                        .define('A', ModBlocks.PYRITE_BLOCK.get())
                        .define('B', ModItems.CHISEL.get())
                , ModItems.CHISEL.get());

        pickaxe(ModItems.PYRITE_INGOT, ModItems.PYRITE_PICKAXE);

        axe(ModItems.PYRITE_INGOT, ModItems.PYRITE_AXE);

        shovel(ModItems.PYRITE_INGOT, ModItems.PYRITE_SHOVEL);

        hoe(ModItems.PYRITE_INGOT, ModItems.PYRITE_HOE);

        sword(ModItems.NEPHRITE, ModItems.NEPHRITE_SWORD);

        pickaxe(ModItems.NEPHRITE, ModItems.NEPHRITE_PICKAXE);

        axe(ModItems.NEPHRITE, ModItems.NEPHRITE_AXE);

        shovel(ModItems.NEPHRITE, ModItems.NEPHRITE_SHOVEL);

        hoe(ModItems.NEPHRITE, ModItems.NEPHRITE_HOE);


        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, ModItems.PYRITE_HAMMER.get())
                        .pattern("AAA")
                        .pattern("AAA")
                        .pattern(" B ")
                        .define('A', ModItems.PYRITE_INGOT.get())
                        .define('B', Items.STICK)
                , ModItems.PYRITE_INGOT.get());

        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, ModItems.PYRITE_BATTLE_AXE.get())
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern(" B ")
                        .define('A', ModItems.PYRITE_INGOT.get())
                        .define('B', Items.STICK)
                , ModItems.PYRITE_INGOT.get());

        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, ModItems.IRON_BATTLE_AXE.get())
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern(" B ")
                        .define('A', Items.IRON_INGOT)
                        .define('B', Items.STICK)
                , Items.IRON_INGOT);

        helmet(ModItems.PYRITE_INGOT, ModItems.PYRITE_HELMET);

        chestplate(ModItems.PYRITE_INGOT, ModItems.PYRITE_CHESTPLATE);

        leggings(ModItems.PYRITE_INGOT, ModItems.PYRITE_LEGGINGS);

        boots(ModItems.PYRITE_INGOT, ModItems.PYRITE_BOOTS);

        helmet(ModItems.NEPHRITE, ModItems.NEPHRITE_HELMET);

        chestplate(ModItems.NEPHRITE, ModItems.NEPHRITE_CHESTPLATE);

        leggings(ModItems.NEPHRITE, ModItems.NEPHRITE_LEGGINGS);

        boots(ModItems.NEPHRITE, ModItems.NEPHRITE_BOOTS);

        saveShapelessRecipe(
                shapeless(RecipeCategory.MISC, ModItems.NEPHRITE.get(), 9)
                        .requires(ModBlocks.NEPHRITE_BLOCK.get())
                , ModBlocks.NEPHRITE_BLOCK.get().asItem());

        saveShapelessRecipe(
                shapeless(RecipeCategory.MISC, ModItems.RAW_PYRITE.get(), 9)
                        .requires(ModBlocks.RAW_PYRITE_BLOCK.get())
                , ModBlocks.RAW_PYRITE_BLOCK.get().asItem());

        saveShapedRecipe(
                shaped(RecipeCategory.FOOD, ModItems.SUSHI.get())
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern("AAA")
                        .define('A', ModItems.RICE.get())
                        .define('B', Items.COOKED_SALMON)
                , ModItems.RICE.get());

        planksFromLogs(ModBlocks.HINOKI_BAN.get(), ModTags.Items.HINOKI_MARUTA, 4);

        woodFromLogs(ModBlocks.HINOKI_MOKUZAI.get(), ModBlocks.HINOKI_MARUTA.get());

        smelting(this.output, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 200, "pyrite");
        oreBlasting(this.output, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 100, "pyrite");
        smoking(this.output, List.of(ModItems.RAW_RICE.get()), RecipeCategory.FOOD, ModItems.RICE.get(), 1f, 100, "rice");

        stairBuilder(ModBlocks.PYRITE_STAIRS.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output);

        buttonBuilder(ModBlocks.PYRITE_BUTTON.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output);

        pressurePlate(ModBlocks.PYRITE_PRESSURE_PLATE.get(), ModItems.PYRITE_INGOT.get());

        fenceBuilder(ModBlocks.PYRITE_FENCE.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output);

        fenceGateBuilder(ModBlocks.PYRITE_FENCE_GATE.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output);

        wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_WALL.get(), ModItems.PYRITE_INGOT.get());

        doorBuilder(ModBlocks.PYRITE_DOOR.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output);

        trapdoorBuilder(ModBlocks.PYRITE_TRAPDOOR.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output);

        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_SLAB.get(), ModItems.PYRITE_INGOT.get());

        HolderLookup.Provider registries = this.registries;

        HolderGetter<TrimPattern> trimPatterns = registries.lookupOrThrow(Registries.TRIM_PATTERN);
        Optional<Holder.Reference<TrimPattern>> koiFish = trimPatterns.get(ModTrimPatterns.KOI_FISH);
        if (koiFish.isPresent()) {
            trimSmithing(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModTrimPatterns.KOI_FISH, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "koi_fish_smithing_recipe")));
        }else {
            log.error("For some reason, my armor trims won't be registered");
            log.info("Continuing with my life");
        }

        //smithingTrims().forEach(trimTemplate -> this.trimSmithing(trimTemplate.template().get(), trimTemplate.patternId(), trimTemplate.recipeId()));
    }


    protected void smelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                   float pExperience, int pCookingTIme, String pGroup) {

        cooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected void smoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                  float pExperience, int pCookingTIme, String pGroup) {

        cooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        cooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void cooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                             List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, EverythingJapanese.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    private void saveShapedRecipe(ShapedRecipeBuilder builder, Item unlockItem) {
        builder.unlockedBy(getHasName(unlockItem), has(unlockItem)).save(this.output);
    }

    private void saveShapelessRecipe(ShapelessRecipeBuilder builder, Item unlockItem) {
        builder.unlockedBy(getHasName(unlockItem), has(unlockItem)).save(this.output);
    }

    private void helmet(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("AAA")
                        .pattern("A A")
                        .pattern("   ")
                        .define('A', ingredient.get())
        , ingredient.get());
    }

    private void chestplate(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("A A")
                        .pattern("AAA")
                        .pattern("AAA")
                        .define('A', ingredient.get())
                , ingredient.get());
    }

    private void leggings(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("AAA")
                        .pattern("A A")
                        .pattern("A A")
                        .define('A', ingredient.get())
                , ingredient.get());
    }

    private void boots(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("A A")
                        .pattern("A A")
                        .pattern("   ")
                        .define('A', ingredient.get())
                , ingredient.get());
    }

    private void hoe(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("AA ")
                        .pattern(" B ")
                        .pattern(" B ")
                        .define('A', ingredient.get())
                        .define('B', Items.STICK)
        , ingredient.get());
    }

    private void shovel(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.TOOLS, result.get())
                        .pattern(" A ")
                        .pattern(" B ")
                        .pattern(" B ")
                        .define('A', ingredient.get())
                        .define('B', Items.STICK)
                , ingredient.get());
    }

    private void axe(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("AA ")
                        .pattern("AB ")
                        .pattern(" B ")
                        .define('A', ingredient.get())
                        .define('B', Items.STICK)
                , ingredient.get());
    }

    private void pickaxe(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("AAA")
                        .pattern(" B ")
                        .pattern(" B ")
                        .define('A', ingredient.get())
                        .define('B', Items.STICK)
                , ingredient.get());
    }

    private void sword(RegistryObject<Item> ingredient, RegistryObject<Item> result) {
        saveShapedRecipe(
                shaped(RecipeCategory.TOOLS, result.get())
                        .pattern(" A ")
                        .pattern(" A ")
                        .pattern(" B ")
                        .define('A', ingredient.get())
                        .define('B', Items.STICK)
                , ingredient.get());
    }

}
