package net.Chidoziealways.everythingjapanese.recipe;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeBookCategories {
    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES =
            DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, EverythingJapanese.MOD_ID);

    public static final RegistryObject<RecipeBookCategory> GROWTH_CHAMBER = register("growth_chamber");

    private static RegistryObject<RecipeBookCategory> register(String pName) {
        return RECIPE_BOOK_CATEGORIES.register(pName, RecipeBookCategory::new);
    }
}
