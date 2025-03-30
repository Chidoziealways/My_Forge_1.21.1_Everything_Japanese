package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> PYRITE_SMELTABLES = List.of(ModItems.RAW_PYRITE.get(),
                ModBlocks.RAW_PYRITE_BLOCK.get(),
                ModBlocks.PYRITE_ORE.get(),
                ModBlocks.PYRITE_DEEPSLATE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PYRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.PYRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        threeByThreePacker(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModItems.NEPHRITE.get(), ModBlocks.NEPHRITE_BLOCK.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.CHOCOLATE_CAKE.get())
                        .pattern("MMM")
                        .pattern("SES")
                        .pattern("AWA")
                        .define('M', Items.MILK_BUCKET)
                        .define('S', Items.SUGAR)
                        .define('E', Items.EGG)
                        .define('W', Items.WHEAT)
                        .define('A', Items.COCOA_BEANS)
                        .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
                        .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHISEL.get())
                .pattern("ABC")
                .pattern("EDE")
                .pattern("CBA")
                .define('A', Items.WIND_CHARGE)
                .define('B', ModItems.PYRITE_INGOT.get())
                .define('C', Items.BLAZE_POWDER)
                .define('D', Items.STICK)
                .define('E', Items.GHAST_TEAR)
                .unlockedBy(getHasName(Items.GHAST_TEAR), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 9)
                .requires(ModBlocks.PYRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PYRITE_BLOCK.get()), has(ModBlocks.PYRITE_BLOCK.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PYRITE_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.NEPHRITE.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModBlocks.TRANSFORMER_BLOCK.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.PYRITE_BLOCK.get())
                .define('B', ModItems.CHISEL.get())
                .unlockedBy(getHasName(ModItems.CHISEL.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PYRITE_PICKAXE.get())
                        .pattern("AAA")
                        .pattern(" B ")
                        .pattern(" B ")
                        .define('A', ModItems.PYRITE_INGOT.get())
                        .define('B', Items.STICK)
                        .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PYRITE_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PYRITE_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PYRITE_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NEPHRITE_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.NEPHRITE.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NEPHRITE_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.NEPHRITE.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NEPHRITE_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.NEPHRITE.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NEPHRITE_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.NEPHRITE.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PYRITE_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.NEPHRITE.get())
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PYRITE_HAMMER.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PYRITE_BATTLE_AXE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_BATTLE_AXE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PYRITE_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.PYRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PYRITE_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.PYRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PYRITE_BOOTS.get())
                .pattern("A A")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.NEPHRITE.get())
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.NEPHRITE.get())
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_BOOTS.get())
                .pattern("A A")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.NEPHRITE.get())
                .unlockedBy(getHasName(ModItems.NEPHRITE.get()), has(ModItems.NEPHRITE.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NEPHRITE.get(), 9)
                        .requires(ModBlocks.NEPHRITE_BLOCK.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_PYRITE.get(), 9)
                .requires(ModBlocks.RAW_PYRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_PYRITE_BLOCK.get()), has(ModBlocks.RAW_PYRITE_BLOCK.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.SUSHI.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModItems.RICE.get())
                .define('B', Items.COOKED_SALMON)
                .unlockedBy(getHasName(ModItems.RICE.get()), has(ModItems.RICE.get())).save(pRecipeOutput);

        planksFromLogs(pRecipeOutput, ModBlocks.HINOKI_BAN.get(), ModTags.Items.HINOKI_MARUTA, 4);

        woodFromLogs(pRecipeOutput, ModBlocks.HINOKI_MOKUZAI.get(), ModBlocks.HINOKI_MARUTA.get());




        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_PYRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_PYRITE.get())
                .unlockedBy(getHasName(ModItems.RAW_PYRITE.get()), has(ModItems.RAW_PYRITE.get())).save(pRecipeOutput);
        smelting(pRecipeOutput, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 200, "pyrite");
        oreBlasting(pRecipeOutput, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 100, "pyrite");
        smoking(pRecipeOutput, List.of(ModItems.RAW_RICE.get()), RecipeCategory.FOOD, ModItems.RICE.get(), 1f, 100, "rice");

        stairBuilder(ModBlocks.PYRITE_STAIRS.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        buttonBuilder(ModBlocks.PYRITE_BUTTON.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                        .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        pressurePlate(pRecipeOutput, ModBlocks.PYRITE_PRESSURE_PLATE.get(), ModItems.PYRITE_INGOT.get());

        fenceBuilder(ModBlocks.PYRITE_FENCE.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        fenceGateBuilder(ModBlocks.PYRITE_FENCE_GATE.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_WALL.get(), ModItems.PYRITE_INGOT.get());

        doorBuilder(ModBlocks.PYRITE_DOOR.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        trapdoorBuilder(ModBlocks.PYRITE_TRAPDOOR.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
                .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(pRecipeOutput);

        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_SLAB.get(), ModItems.PYRITE_INGOT.get());

        trimSmithing(pRecipeOutput, ModItems.KOI_FISH_SMITHING_TEMPLATE.get(), ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "koi_fish"));

    }


    protected static void smelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                   float pExperience, int pCookingTIme, String pGroup) {

        cooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void smoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                  float pExperience, int pCookingTIme, String pGroup) {

        cooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        cooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void cooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                    List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, EverythingJapanese.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
