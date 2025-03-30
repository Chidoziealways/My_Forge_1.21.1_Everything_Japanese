package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier PYRITE = new ForgeTier(1000, 5, 10.0F, 10,
            ModTags.Blocks.NEEDS_PYRITE_TOOL, () -> Ingredient.of(ModItems.PYRITE_INGOT.get()),
            ModTags.Blocks.INCORRECT_FOR_PYRITE_TOOL);
    public static final Tier NEPHRITE = new ForgeTier(10000, 8, 14.0F, 20,
            ModTags.Blocks.NEEDS_NEPHRITE_TOOL, () -> Ingredient.of(ModItems.NEPHRITE.get()),
            ModTags.Blocks.INCORRECT_FOR_NEPHRITE_TOOL);
}
