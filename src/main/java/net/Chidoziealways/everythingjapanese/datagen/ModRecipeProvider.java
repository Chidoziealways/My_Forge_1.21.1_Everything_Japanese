package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_PYRITE.get(), 9)
                .requires(ModBlocks.RAW_PYRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_PYRITE_BLOCK.get()), has(ModBlocks.RAW_PYRITE_BLOCK.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_PYRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_PYRITE.get())
                .unlockedBy(getHasName(ModItems.RAW_PYRITE.get()), has(ModItems.RAW_PYRITE.get())).save(pRecipeOutput);
        oreSmelting(pRecipeOutput, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 200, "pyrite");
        oreBlasting(pRecipeOutput, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 100, "pyrite");

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

    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {

        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, EverythingJapanese.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
