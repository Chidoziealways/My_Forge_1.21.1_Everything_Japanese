package net.Chidoziealways.everythingjapanese.tiers;

import com.google.common.base.Suppliers;
import java.util.function.Supplier;

import net.Chidoziealways.everythingjapanese.tags.ModBLockTags;
import net.Chidoziealways.everythingjapanese.tags.ModItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public enum ModTiers implements Tier {
    Pyrite(ModBLockTags.INCORRECT_FOR_PYRITE_TOOL, 100, 10.0F, 20.0F, 30, () -> Ingredient.of(ModItemTags.PYRITE));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    private ModTiers(
            final TagKey<Block> pIncorrectBlockForDrops, final int pUses, final float pSpeed, final float pDamage, final int pEnchantmentValue, final Supplier<Ingredient> pRepairIngredient
    ) {
        this.incorrectBlocksForDrops = pIncorrectBlockForDrops;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = Suppliers.memoize(pRepairIngredient::get);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}