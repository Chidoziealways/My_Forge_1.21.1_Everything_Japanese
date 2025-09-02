package net.Chidoziealways.everythingjapanese.datagen

import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.bladeSmithing
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.kanji.KanjiTypes
import net.Chidoziealways.everythingjapanese.kanjiSmithing
import net.Chidoziealways.everythingjapanese.trim.ModTrimPatterns
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.wrapSmithing
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
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.List
import java.util.concurrent.CompletableFuture
import java.util.stream.Stream

open class ModRecipeProvider(lookup: HolderLookup.Provider, recipeOutput: RecipeOutput) :
    RecipeProvider(lookup, recipeOutput){

    class Runner(output: PackOutput, providerCompletableFuture: CompletableFuture<HolderLookup.Provider>) :
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
        val template: SmithingTemplateItem,
        val patternId: ResourceKey<TrimPattern>,
        val recipeId: ResourceKey<Recipe<*>>
    )

    @JvmRecord
    data class KanjiTemplate(
        val template: Item,
        val patternId: ResourceKey<KanjiType>,
        val recipeId: ResourceKey<Recipe<*>>
    )

    @JvmRecord
    data class BladeTemplate(
        val template: Item,
        val patternId: BladeType,
        val recipeId: ResourceKey<Recipe<*>>
    )

    @JvmRecord
    data class WrappingTemplate(
        val template: Item,
        val patternId: Wrapping,
        val recipeId: ResourceKey<Recipe<*>>
    )

    override fun buildRecipes() {
        val PYRITE_SMELTABLES = listOf<ItemLike>(
            ModItems.RAW_PYRITE,
            ModBlocks.RAW_PYRITE_BLOCK,
            ModBlocks.PYRITE_ORE,
            ModBlocks.PYRITE_DEEPSLATE_ORE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEPHRITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.NEPHRITE),
            ModItems.NEPHRITE
        )

        saveShapelessRecipe(shapeless(RecipeCategory.TOOLS, ModItems.TALISMAN_ITEM, 5).requires(Items.BAMBOO).requires(Items.INK_SAC),
            Items.INK_SAC)

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PYRITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_PYRITE),
            ModItems.RAW_PYRITE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.DECORATIONS, ModBlocks.MONEY_VAULT_BLOCK)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('G', Blocks.IRON_BLOCK)
                .define('C', Blocks.CHEST),
            Blocks.CHEST.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, ModItems.CREDIT_CARD_ITEM)
                .pattern("III")
                .pattern("SPS")
                .pattern("   ")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.PAPER)
                .define('P', Items.REDSTONE),
            Items.PAPER
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TATAMI_MAT, 2)
                .pattern("HHH")
                .pattern("WWW")
                .pattern("HHH")
                .define('H', ModTags.Items.ANIMAL_SKIN)
                .define('W', Items.WHEAT),
            Items.WHEAT
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.PYRITE_INGOT),
            ModItems.PYRITE_INGOT
        )

        this.copySmithingTemplate(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, ModBlocks.PYRITE_BLOCK)

        saveShapedRecipe(
            shaped(RecipeCategory.MISC, ModBlocks.GROWTH_CHAMBER)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.REDSTONE)
                .define('C', Items.CRAFTING_TABLE),
            Items.CRAFTING_TABLE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.MISC, ModBlocks.PEDESTAL)
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', Blocks.CHEST)
                .define('B', Blocks.STONE),
            Blocks.CHEST.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.FOOD, ModBlocks.CHOCOLATE_CAKE)
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
            shaped(RecipeCategory.MISC, ModItems.CHISEL)
                .pattern("ABC")
                .pattern("EDE")
                .pattern("CBA")
                .define('A', Items.WIND_CHARGE)
                .define('B', ModItems.PYRITE_INGOT)
                .define('C', Items.BLAZE_POWDER)
                .define('D', Items.STICK)
                .define('E', Items.GHAST_TEAR),

            Items.GHAST_TEAR
        )

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, ModItems.PYRITE_INGOT, 9)
                .requires(ModBlocks.PYRITE_BLOCK),
            ModBlocks.PYRITE_BLOCK.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModBlocks.TRANSFORMER_BLOCK)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.PYRITE_BLOCK)
                .define('B', ModItems.CHISEL),
            ModItems.CHISEL
        )

        pickaxe(ModItems.PYRITE_INGOT, ModItems.PYRITE_PICKAXE)

        axe(ModItems.PYRITE_INGOT, ModItems.PYRITE_AXE)

        shovel(ModItems.PYRITE_INGOT, ModItems.PYRITE_SHOVEL)

        hoe(ModItems.PYRITE_INGOT, ModItems.PYRITE_HOE)

        sword(ModItems.NEPHRITE, ModItems.NEPHRITE_SWORD)

        pickaxe(ModItems.NEPHRITE, ModItems.NEPHRITE_PICKAXE)

        axe(ModItems.NEPHRITE, ModItems.NEPHRITE_AXE)

        shovel(ModItems.NEPHRITE, ModItems.NEPHRITE_SHOVEL)

        hoe(ModItems.NEPHRITE, ModItems.NEPHRITE_HOE)


        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModItems.PYRITE_HAMMER)
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT)
                .define('B', Items.STICK),
            ModItems.PYRITE_INGOT
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModItems.PYRITE_BATTLE_AXE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', ModItems.PYRITE_INGOT)
                .define('B', Items.STICK),
            ModItems.PYRITE_INGOT
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModItems.IRON_BATTLE_AXE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK),
            Items.IRON_INGOT
        )

        helmet(ModItems.PYRITE_INGOT, ModItems.PYRITE_HELMET)

        chestplate(ModItems.PYRITE_INGOT, ModItems.PYRITE_CHESTPLATE)

        leggings(ModItems.PYRITE_INGOT, ModItems.PYRITE_LEGGINGS)

        boots(ModItems.PYRITE_INGOT, ModItems.PYRITE_BOOTS)

        helmet(ModItems.NEPHRITE, ModItems.NEPHRITE_HELMET)

        chestplate(ModItems.NEPHRITE, ModItems.NEPHRITE_CHESTPLATE)

        leggings(ModItems.NEPHRITE, ModItems.NEPHRITE_LEGGINGS)

        boots(ModItems.NEPHRITE, ModItems.NEPHRITE_BOOTS)

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, ModItems.NEPHRITE, 9)
                .requires(ModBlocks.NEPHRITE_BLOCK),
            ModBlocks.NEPHRITE_BLOCK.asItem()
        )

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, ModItems.RAW_PYRITE, 9)
                .requires(ModBlocks.RAW_PYRITE_BLOCK),
            ModBlocks.RAW_PYRITE_BLOCK.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.FOOD, ModItems.SUSHI)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModItems.RICE)
                .define('B', Items.COOKED_SALMON),
            ModItems.RICE
        )

        planksFromLogs(ModBlocks.HINOKI_BAN, ModTags.Items.HINOKI_MARUTA, 4)

        woodFromLogs(ModBlocks.HINOKI_MOKUZAI, ModBlocks.HINOKI_MARUTA)

        smelting(this.output,
            PYRITE_SMELTABLES as MutableList<ItemLike>, RecipeCategory.MISC, ModItems.PYRITE_INGOT, 1f, 200, "pyrite")
        oreBlasting(this.output, PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.PYRITE_INGOT, 1f, 100, "pyrite")
        smoking(
            this.output,
            listOf<ItemLike>(ModItems.RAW_RICE) as MutableList<ItemLike>,
            RecipeCategory.FOOD,
            ModItems.RICE,
            1f,
            100,
            "rice"
        )

        stairBuilder(ModBlocks.PYRITE_STAIRS, Ingredient.of(ModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT), has(ModItems.PYRITE_INGOT)).save(this.output)

        buttonBuilder(ModBlocks.PYRITE_BUTTON, Ingredient.of(ModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT), has(ModItems.PYRITE_INGOT)).save(this.output)

        pressurePlate(ModBlocks.PYRITE_PRESSURE_PLATE, ModItems.PYRITE_INGOT)

        fenceBuilder(ModBlocks.PYRITE_FENCE, Ingredient.of(ModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT), has(ModItems.PYRITE_INGOT)).save(this.output)

        fenceGateBuilder(ModBlocks.PYRITE_FENCE_GATE, Ingredient.of(ModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT), has(ModItems.PYRITE_INGOT)).save(this.output)

        wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_WALL, ModItems.PYRITE_INGOT)

        doorBuilder(ModBlocks.PYRITE_DOOR, Ingredient.of(ModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT), has(ModItems.PYRITE_INGOT)).save(this.output)

        trapdoorBuilder(ModBlocks.PYRITE_TRAPDOOR.asItem(), Ingredient.of(ModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(ModItems.PYRITE_INGOT), has(ModItems.PYRITE_INGOT)).save(this.output)

        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_SLAB, ModItems.PYRITE_INGOT)

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, ModItems.KATANA)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK),
            Items.IRON_INGOT
        )

        smithingKanji().forEach { kanjiTemplate ->
            this.kanjiSmithing(
                kanjiTemplate.template,
                kanjiTemplate.patternId,
                kanjiTemplate.recipeId
            )
        }

        smithingBlade().forEach { bladeTemplate ->
            this.bladeSmithing(
                bladeTemplate.template,
                bladeTemplate.patternId,
                bladeTemplate.recipeId
            )
        }

        smithingWrapper().forEach { wrappingTemplate ->
            this.wrapSmithing(
                wrappingTemplate.template,
                wrappingTemplate.patternId,
                wrappingTemplate.recipeId
            )
        }

        smithingTrims().forEach { trimTemplate: TrimTemplate ->
            this.trimSmithing(
                trimTemplate.template,
                trimTemplate.patternId,
                trimTemplate.recipeId
            )
        }
    }


    protected fun smelting(
        recipeOutput: RecipeOutput, pIngredients: MutableList<ItemLike>, pCategory: RecipeCategory, pResult: ItemLike,
        pExperience: Float, pCookingTIme: Int, pGroup: String
    ) {
        cooking<SmeltingRecipe>(
            recipeOutput,
            RecipeSerializer.SMELTING_RECIPE,
            { p_250200_: String, p_251114_: CookingBookCategory, p_250340_: Ingredient, p_250306_: ItemStack, p_249577_: Float, p_250030_: Int ->
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
        pExperience: Float, pCookingTIme: Int, pGroup: String
    ) {
        cooking<SmokingRecipe>(
            recipeOutput,
            RecipeSerializer.SMOKING_RECIPE,
            { p_249312_: String, p_251017_: CookingBookCategory, p_252345_: Ingredient, p_250002_: ItemStack, p_250535_: Float, p_251222_: Int ->
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
        pExperience: Float, pCookingTime: Int, pGroup: String
    ) {
        cooking<BlastingRecipe>(
            recipeOutput,
            RecipeSerializer.BLASTING_RECIPE,
            { p_251053_: String, p_249936_: CookingBookCategory, p_251550_: Ingredient, p_251027_: ItemStack, p_250843_: Float, p_249841_: Int ->
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

    protected fun <T : AbstractCookingRecipe> cooking(
        recipeOutput: RecipeOutput,
        pCookingSerializer: RecipeSerializer<T>,
        factory: AbstractCookingRecipe.Factory<T>,
        pIngredients: MutableList<ItemLike>,
        pCategory: RecipeCategory,
        pResult: ItemLike,
        pExperience: Float,
        pCookingTime: Int,
        pGroup: String,
        pRecipeName: String
    ) {
        for (itemlike in pIngredients) {
            SimpleCookingRecipeBuilder.generic<T>(
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

    private fun helmet(ingredient: Item, result: Item) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ingredient),
            ingredient
        )
    }

    private fun chestplate(ingredient: Item, result: Item) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ingredient),
            ingredient
        )
    }

    private fun leggings(ingredient: Item, result: Item) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ingredient),
            ingredient
        )
    }

    private fun boots(ingredient: Item, result: Item) {
        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, result)
                .pattern("A A")
                .pattern("A A")
                .pattern("   ")
                .define('A', ingredient),
            ingredient
        )
    }

    private fun hoe(ingredient: Item, result: HoeItem) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ingredient)
                .define('B', Items.STICK),
            ingredient
        )
    }

    private fun shovel(ingredient: Item, result: ShovelItem) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ingredient)
                .define('B', Items.STICK),
            ingredient
        )
    }

    private fun axe(ingredient: Item, result: AxeItem) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ingredient)
                .define('B', Items.STICK),
            ingredient
        )
    }

    private fun pickaxe(ingredient: Item, result: Item) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ingredient)
                .define('B', Items.STICK),
            ingredient
        )
    }

    private fun sword(ingredient: Item, result: Item) {
        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, result)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ingredient)
                .define('B', Items.STICK),
            ingredient
        )
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ModRecipeProvider::class.java)

        fun smithingTrims(): Stream<TrimTemplate> {
            return Stream.of<Pair<SmithingTemplateItem, ResourceKey<TrimPattern>>>(
                Pair.of<SmithingTemplateItem, ResourceKey<TrimPattern>>(
                    ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE,
                    ModTrimPatterns.KOI_FISH
                )
            )
                .map<TrimTemplate> { registryObjectResourceKeyPair: Pair<SmithingTemplateItem, ResourceKey<TrimPattern>> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        ResourceLocation.fromNamespaceAndPath(
                            MOD_ID,
                            getItemName(item) + "_smithing_trim"
                        )
                    )
                    TrimTemplate(item, resourceKey, resourceKey1)
                }
        }

        fun smithingKanji(): Stream<KanjiTemplate> {
            return Stream.of<Pair<Item, ResourceKey<KanjiType>>>(
                Pair.of<Item, ResourceKey<KanjiType>>(
                    Items.FIRE_CHARGE,
                    KanjiTypes.FIRE
                ),
                Pair.of(
                    Items.WATER_BUCKET,
                    KanjiTypes.WATER
                ),
                Pair.of(
                    Items.TOTEM_OF_UNDYING,
                    KanjiTypes.SEAL
                ),
                Pair.of(
                    Items.LIGHTNING_ROD,
                    KanjiTypes.LIGHTNING
                )
            )
                .map<KanjiTemplate> { registryObjectResourceKeyPair: Pair<Item, ResourceKey<KanjiType>> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        ResourceLocation.fromNamespaceAndPath(
                            MOD_ID,
                            getItemName(item) + "_smithing_kanji"
                        )
                    )
                    KanjiTemplate(item, resourceKey, resourceKey1)
                }
        }

        fun smithingBlade(): Stream<BladeTemplate> {
            return Stream.of<Pair<Item, BladeType>>(
                Pair.of<Item, BladeType>(
                    ModItems.BLADE_STEEL,
                    BladeType.STEEL
                )
            )
                .map<BladeTemplate> { registryObjectResourceKeyPair: Pair<Item, BladeType> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        ResourceLocation.fromNamespaceAndPath(
                            MOD_ID,
                            getItemName(item) + "_smithing_blade"
                        )
                    )
                    BladeTemplate(item, resourceKey, resourceKey1)
                }
        }

        fun smithingWrapper(): Stream<WrappingTemplate> {
            return Stream.of<Pair<Item, Wrapping>>(
                Pair.of<Item, Wrapping>(
                    ModItems.WHITE_WRAP,
                    Wrapping.WHITE
                ),
                Pair.of(
                    ModItems.BLACK_WRAP,
                    Wrapping.BLACK
                ),
                Pair.of(
                    ModItems.RED_WRAP,
                    Wrapping.RED
                )
            )
                .map<WrappingTemplate> { registryObjectResourceKeyPair: Pair<Item, Wrapping> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        ResourceLocation.fromNamespaceAndPath(
                            MOD_ID,
                            getItemName(item) + "_smithing_wrapper"
                        )
                    )
                    WrappingTemplate(item, resourceKey, resourceKey1)
                }
        }
    }
}
