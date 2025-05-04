/*package net.Chidoziealways.everythingjapanese.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.recipe.GrowthChamberRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class GrowthChamberRecipeCategory implements IRecipeCategory<GrowthChamberRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "growth_chamber");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID,
            "textures/gui/growth_chamber/growth_chamber_gui.png");

    public static final RecipeType<GrowthChamberRecipe> GROWTH_CHAMBER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, GrowthChamberRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public GrowthChamberRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GROWTH_CHAMBER.get()));
    }

    @Override
    public RecipeType<GrowthChamberRecipe> getRecipeType() {
        return GROWTH_CHAMBER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.everythingjapanese.growth_chamber");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, GrowthChamberRecipe growthChamberRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(growthChamberRecipe.getIngredients().get(0));

        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(growthChamberRecipe.getResultItem(null));
    }
}*/
