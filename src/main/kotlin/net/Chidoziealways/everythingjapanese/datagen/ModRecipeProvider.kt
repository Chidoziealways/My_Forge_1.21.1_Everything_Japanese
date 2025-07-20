package net.Chidoziealways.everythingjapanese.datagen

import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.trim.ModTrimPatterns
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.SmithingTemplateItem
import net.minecraft.world.item.crafting.*
import net.minecraft.world.item.equipment.trim.TrimPattern
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.common.crafting.conditions.IConditionBuilder
import net.minecraftforge.registries.RegistryObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.List
import java.util.concurrent.CompletableFuture
import java.util.stream.Stream

class ModRecipeProvider(lookup: HolderLookup.Provider, recipeOutput: RecipeOutput) :
    RecipeProvider(lookup, recipeOutput), IConditionBuilder {
    class Runner(output: PackOutput, providerCompletableFuture: CompletableFuture<HolderLookup.Provider?>) :
        RecipeProvider.Runner(output, providerCompletableFuture) {
        override fun createRecipeProvider(pRegistries: HolderLookup.Provider, pOutput: RecipeOutput): RecipeProvider {
            return ModRecipeProvider(pRegistries, pOutput)
        }

        override fun getName(): String {
            return "Recipes"
        }
    }

    @JvmRecord
    data class TrimTemplate(
        val template: RegistryObject<SmithingTemplateItem?>?,
        val patternId: ResourceKey<TrimPattern?>?,
        val recipeId: ResourceKey<Recipe<*>?>?
    )

    override fun buildRecipes() {
        val PYRITE_SMELTABLES = listOf<ItemLike?>(
            ModItems.RAW_PYRITE!!.get(),
            ModBlocks.RAW_PYRITE_BLOCK.get(),
            ModBlocks.PYRITE_ORE.get(),
            ModBlocks.PYRITE_DEEPSLATE_ORE.get()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEPHRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.NEPHRITE!!.get()),
            ModItems.NEPHRITE.get()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PYRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_PYRITE.get()),
            ModItems.RAW_PYRITE.get()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.PYRITE_INGOT!!.get()),
            ModItems.PYRITE_INGOT.get()
        )

        this.copySmithingTemplate(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE!!.get(), ModBlocks.PYRITE_BLOCK.get())

        saveShapedRecipe(
            shaped(RecipeCategory.MISC, ModBlocks.GROWTH_CHAMBER.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.REDSTONE)
                .define('C', Items.CRAFTING_TABLE),
            Items.CRAFTING_TABLE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.MISC, ModBlocks.PEDESTAL.get())
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', Blocks.CHEST)
                .define('B', Blocks.STONE),
            Blocks.CHEST.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.FOOD, ModBlocks.CHOCOLATE_CAKE.get())
                .pattern("MMM")
                .pattern("SES")
                .pattern("AWA")
                .define('M', Items.MILK_BUCKET)
                .define('S', Items.SUGAR)
                .define('E', Items.EGG)
                .define('W', Items.WHEAT)
                .define('A', Items.COCOA_BEANS),
            Items.COCOA_BEANS
        )

        saveShapedRecipe(
            shaped(RecipeCategory.MISC, ModItems.CHISEL!!.get())
                .pattern("ABC")
                .pattern("EDE")
                .pattern("CBA")
                .define('A', Items.WIND_CHARGE)
                .define('B', ModItems.PYRITE_INGOT.get())
                .define('C', Items.BLAZE_POWDER)
                .define('D', Items.STICK)
                .define('E', Items.GHAST_TEAR),

            Items.GHAST_TEAR
        )

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 9)
                .requires(ModBlocks.PYRITE_BLOCK.get()),
            ModBlocks.PYRITE_BLOCK.get()!!.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModBlocks.TRANSFORMER_BLOCK.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.PYRITE_BLOCK.get())
                .define('B', ModItems.CHISEL!!.get()),
            ModItems.CHISEL.get()
        )

        pickaxe(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_PICKAXE!!.registryObject)

        axe(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_AXE!!.registryObject)

        shovel(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_SHOVEL!!.registryObject)

        hoe(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_HOE!!.registryObject)

        sword(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_SWORD!!.registryObject)

        pickaxe(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_PICKAXE!!.registryObject)

        axe(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_AXE!!.registryObject)

        shovel(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_SHOVEL!!.registryObject)

        hoe(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_HOE!!.registryObject)


        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModItems.PYRITE_HAMMER!!.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK),
            ModItems.PYRITE_INGOT.get()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModItems.PYRITE_BATTLE_AXE!!.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT.get())
                .define('B', Items.STICK),
            ModItems.PYRITE_INGOT.get()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModItems.IRON_BATTLE_AXE!!.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK),
            Items.IRON_INGOT
        )

        helmet(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_HELMET!!.registryObject)

        chestplate(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_CHESTPLATE!!.registryObject)

        leggings(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_LEGGINGS!!.registryObject)

        boots(ModItems.PYRITE_INGOT.registryObject, ModItems.PYRITE_BOOTS!!.registryObject)

        helmet(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_HELMET!!.registryObject)

        chestplate(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_CHESTPLATE!!.registryObject)

        leggings(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_LEGGINGS!!.registryObject)

        boots(ModItems.NEPHRITE.registryObject, ModItems.NEPHRITE_BOOTS!!.registryObject)

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, ModItems.NEPHRITE.get(), 9)
                .requires(ModBlocks.NEPHRITE_BLOCK.get()),
            ModBlocks.NEPHRITE_BLOCK.get()!!.asItem()
        )

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, ModItems.RAW_PYRITE.get(), 9)
                .requires(ModBlocks.RAW_PYRITE_BLOCK.get()),
            ModBlocks.RAW_PYRITE_BLOCK.get()!!.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.FOOD, ModItems.SUSHI!!.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModItems.RICE!!.get())
                .define('B', Items.COOKED_SALMON),
            ModItems.RICE.get()
        )

        planksFromLogs(ModBlocks.HINOKI_BAN.get(), ModTags.Items.HINOKI_MARUTA, 4)

        woodFromLogs(ModBlocks.HINOKI_MOKUZAI.get(), ModBlocks.HINOKI_MARUTA.get())

        smelting(this.output,
            PYRITE_SMELTABLES as MutableList<ItemLike>, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 200, "pyrite")
        oreBlasting(this.output, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), 1f, 100, "pyrite")
        smoking(
            this.output,
            listOf<ItemLike>(ModItems.RAW_RICE!!.get()) as MutableList<ItemLike>,
            RecipeCategory.FOOD,
            ModItems.RICE.get(),
            1f,
            100,
            "rice"
        )

        stairBuilder(ModBlocks.PYRITE_STAIRS.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output)

        buttonBuilder(ModBlocks.PYRITE_BUTTON.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output)

        pressurePlate(ModBlocks.PYRITE_PRESSURE_PLATE.get(), ModItems.PYRITE_INGOT.get())

        fenceBuilder(ModBlocks.PYRITE_FENCE.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output)

        fenceGateBuilder(ModBlocks.PYRITE_FENCE_GATE.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output)

        wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_WALL.get(), ModItems.PYRITE_INGOT.get())

        doorBuilder(ModBlocks.PYRITE_DOOR.get(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output)

        trapdoorBuilder(ModBlocks.PYRITE_TRAPDOOR.get()!!.asItem(), Ingredient.of(ModItems.PYRITE_INGOT.get())).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT.get()), has(ModItems.PYRITE_INGOT.get())).save(this.output)

        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_SLAB.get(), ModItems.PYRITE_INGOT.get())

        smithingTrims()!!.forEach { trimTemplate: TrimTemplate? ->
            this.trimSmithing(
                trimTemplate!!.template!!.get(),
                trimTemplate.patternId,
                trimTemplate.recipeId
            )
        }
    }


    protected fun smelting(
        recipeOutput: RecipeOutput, pIngredients: MutableList<ItemLike>, pCategory: RecipeCategory, pResult: ItemLike,
        pExperience: Float, pCookingTIme: Int, pGroup: String?
    ) {
        cooking<SmeltingRecipe?>(
            recipeOutput,
            RecipeSerializer.SMELTING_RECIPE,
            AbstractCookingRecipe.Factory { p_250200_: String?, p_251114_: CookingBookCategory?, p_250340_: Ingredient?, p_250306_: ItemStack?, p_249577_: Float, p_250030_: Int ->
                SmeltingRecipe(
                    p_250200_,
                    p_251114_,
                    p_250340_,
                    p_250306_,
                    p_249577_,
                    p_250030_
                )
            },
            pIngredients,
            pCategory,
            pResult,
            pExperience,
            pCookingTIme,
            pGroup,
            "_from_smelting"
        )
    }

    protected fun smoking(
        recipeOutput: RecipeOutput, pIngredients: MutableList<ItemLike>, pCategory: RecipeCategory, pResult: ItemLike,
        pExperience: Float, pCookingTIme: Int, pGroup: String?
    ) {
        cooking<SmokingRecipe?>(
            recipeOutput,
            RecipeSerializer.SMOKING_RECIPE,
            AbstractCookingRecipe.Factory { p_249312_: String?, p_251017_: CookingBookCategory?, p_252345_: Ingredient?, p_250002_: ItemStack?, p_250535_: Float, p_251222_: Int ->
                SmokingRecipe(
                    p_249312_,
                    p_251017_,
                    p_252345_,
                    p_250002_,
                    p_250535_,
                    p_251222_
                )
            },
            pIngredients,
            pCategory,
            pResult,
            pExperience,
            pCookingTIme,
            pGroup,
            "_from_smelting"
        )
    }

    protected fun oreBlasting(
        recipeOutput: RecipeOutput, pIngredients: MutableList<ItemLike>, pCategory: RecipeCategory, pResult: ItemLike,
        pExperience: Float, pCookingTime: Int, pGroup: String?
    ) {
        cooking<BlastingRecipe?>(
            recipeOutput,
            RecipeSerializer.BLASTING_RECIPE,
            AbstractCookingRecipe.Factory { p_251053_: String?, p_249936_: CookingBookCategory?, p_251550_: Ingredient?, p_251027_: ItemStack?, p_250843_: Float, p_249841_: Int ->
                BlastingRecipe(
                    p_251053_,
                    p_249936_,
                    p_251550_,
                    p_251027_,
                    p_250843_,
                    p_249841_
                )
            },
            pIngredients,
            pCategory,
            pResult,
            pExperience,
            pCookingTime,
            pGroup,
            "_from_blasting"
        )
    }

    protected fun <T : AbstractCookingRecipe?> cooking(
        recipeOutput: RecipeOutput,
        pCookingSerializer: RecipeSerializer<T?>?,
        factory: AbstractCookingRecipe.Factory<T?>,
        pIngredients: MutableList<ItemLike>,
        pCategory: RecipeCategory,
        pResult: ItemLike,
        pExperience: Float,
        pCookingTime: Int,
        pGroup: String?,
        pRecipeName: String?
    ) {
        for (itemlike in pIngredients) {
            SimpleCookingRecipeBuilder.generic<T?>(
                Ingredient.of(itemlike),
                pCategory,
                pResult,
                pExperience,
                pCookingTime,
                pCookingSerializer,
                factory
            ).group(pGroup).unlockedBy(
                getHasName(itemlike), has(itemlike)
            )
                .save(
                    recipeOutput,
                    MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike)
                )
        }
    }

    private fun saveShapedRecipe(builder: ShapedRecipeBuilder, unlockItem: Item) {
        builder.unlockedBy(getHasName(unlockItem), has(unlockItem)).save(this.output)
    }

    private fun saveShapelessRecipe(builder: ShapelessRecipeBuilder, unlockItem: Item) {
        builder.unlockedBy(getHasName(unlockItem), has(unlockItem)).save(this.output)
    }

    private fun helmet(ingredient: RegistryObject<Item>, result: RegistryObject<Item>) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ingredient.get()),
            ingredient.get()
        )
    }

    private fun chestplate(ingredient: RegistryObject<Item>, result: RegistryObject<Item>) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ingredient.get()),
            ingredient.get()
        )
    }

    private fun leggings(ingredient: RegistryObject<Item>, result: RegistryObject<Item>) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ingredient.get()),
            ingredient.get()
        )
    }

    private fun boots(ingredient: RegistryObject<Item>, result: RegistryObject<Item>) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result.get())
                .pattern("A A")
                .pattern("A A")
                .pattern("   ")
                .define('A', ingredient.get()),
            ingredient.get()
        )
    }

    private fun hoe(ingredient: RegistryObject<Item>, result: RegistryObject<HoeItem>) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ingredient.get())
                .define('B', Items.STICK),
            ingredient.get()
        )
    }

    private fun shovel(ingredient: RegistryObject<Item>, result: RegistryObject<ShovelItem>) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ingredient.get())
                .define('B', Items.STICK),
            ingredient.get()
        )
    }

    private fun axe(ingredient: RegistryObject<Item>, result: RegistryObject<AxeItem>) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ingredient.get())
                .define('B', Items.STICK),
            ingredient.get()
        )
    }

    private fun pickaxe(ingredient: RegistryObject<Item>, result: RegistryObject<Item>) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ingredient.get())
                .define('B', Items.STICK),
            ingredient.get()
        )
    }

    private fun sword(ingredient: RegistryObject<Item>, result: RegistryObject<Item>) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ingredient.get())
                .define('B', Items.STICK),
            ingredient.get()
        )
    }

    companion object {
        private val log: Logger? = LoggerFactory.getLogger(ModRecipeProvider::class.java)

        fun smithingTrims(): Stream<TrimTemplate?>? {
            return Stream.of<Pair<RegistryObject<SmithingTemplateItem>, ResourceKey<TrimPattern?>?>?>(
                Pair.of<RegistryObject<SmithingTemplateItem>, ResourceKey<TrimPattern?>?>(
                    ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE.registryObject,
                    ModTrimPatterns.KOI_FISH
                )
            )
                .map<TrimTemplate?> { registryObjectResourceKeyPair: Pair<RegistryObject<SmithingTemplateItem>, ResourceKey<TrimPattern?>?>? ->
                    val item = registryObjectResourceKeyPair!!.getFirst() as RegistryObject<SmithingTemplateItem?>
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create<Recipe<*>?>(
                        Registries.RECIPE,
                        ResourceLocation.fromNamespaceAndPath(
                            MOD_ID,
                            getItemName(item.get()) + "_smithing_trim"
                        )
                    )
                    TrimTemplate(item, resourceKey, resourceKey1)
                }
        }
    }
}
