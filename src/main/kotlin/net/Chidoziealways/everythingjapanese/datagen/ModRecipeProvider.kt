package net.Chidoziealways.everythingjapanese.datagen

import com.mojang.datafixers.util.Pair
import net.Chidoziealways.everythingcore.datagen.*
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.bladeSmithing
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.item.JModItems
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
import net.minecraft.resources.Identifier
import net.minecraft.tags.ItemTags
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
            JModItems.RAW_PYRITE,
            JModBlocks.RAW_PYRITE_BLOCK,
            JModBlocks.PYRITE_ORE,
            JModBlocks.PYRITE_DEEPSLATE_ORE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, JModBlocks.NEPHRITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', JModItems.NEPHRITE),
            JModItems.NEPHRITE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModItems.POWERED_SWORD)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', Items.DIAMOND)
                .define('C', Items.STICK),
            Items.NETHERITE_INGOT
        )

        saveShapedRecipe(
            shaped(RecipeCategory.DECORATIONS, JModBlocks.ANDON)
                .pattern("SPS")
                .pattern("STS")
                .pattern("SPS")
                .define('S', Items.STICK)
                .define('P', Items.PAPER)
                .define('T', Items.TORCH),
            Items.PAPER
        )

        saveShapedRecipe(
            shaped(RecipeCategory.DECORATIONS, JModBlocks.STONE_LANTERN)
                .pattern(" C ")
                .pattern("STS")
                .pattern(" C ")
                .define('C', Items.STONE_SLAB)
                .define('S', Items.STONE)
                .define('T', Items.TORCH),
            Items.STONE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.DECORATIONS, JModBlocks.TSUKUBAI)
                .pattern("   ")
                .pattern("S S")
                .pattern("SSS")
                .define('S', Items.STONE),
            Items.STONE
        )

        saveShapelessRecipe(shapeless(RecipeCategory.TOOLS, JModItems.TALISMAN_ITEM, 5).requires(Items.BAMBOO).requires(Items.INK_SAC),
            Items.INK_SAC)

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, JModBlocks.RAW_PYRITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', JModItems.RAW_PYRITE),
            JModItems.RAW_PYRITE
        )

        /*saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModItems.SOUL_GUITAR)
                .pattern(" T ")
                .pattern("RIR")
                .pattern("KSN")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.SOUL_SAND)
                .define('T', Items.STICK)
                .define('N', Items.NOTE_BLOCK)
                .define('K', Items.SCULK_CATALYST)
                .define('R', Items.STRING),
            Items.IRON_INGOT
        )*/

        saveShapedRecipe(
            shaped(RecipeCategory.DECORATIONS, JModBlocks.MONEY_VAULT_BLOCK)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('G', Blocks.IRON_BLOCK)
                .define('C', Blocks.CHEST),
            Blocks.CHEST.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.TOOLS, JModItems.CREDIT_CARD_ITEM)
                .pattern("III")
                .pattern("SPS")
                .pattern("   ")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.PAPER)
                .define('P', Items.REDSTONE),
            Items.PAPER
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, JModBlocks.TATAMI_MAT, 2)
                .pattern("HHH")
                .pattern("WWW")
                .pattern("HHH")
                .define('H', ModTags.Items.ANIMAL_SKIN)
                .define('W', Items.WHEAT),
            Items.WHEAT
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, JModBlocks.PYRITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', JModItems.PYRITE_INGOT),
            JModItems.PYRITE_INGOT
        )

        this.copySmithingTemplate(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, JModBlocks.PYRITE_BLOCK)

        saveShapedRecipe(
            shaped(RecipeCategory.MISC, JModBlocks.GROWTH_CHAMBER)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.REDSTONE)
                .define('C', Items.CRAFTING_TABLE),
            Items.CRAFTING_TABLE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.MISC, JModBlocks.PEDESTAL)
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', Blocks.CHEST)
                .define('B', Blocks.STONE),
            Blocks.CHEST.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, JModBlocks.HANGING_SCROLL)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ItemTags.PLANKS)
                .define('B', Items.PAPER),
            Items.PAPER
        )

        /*saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, JModItems.SCROLL)
                .pattern(" P ")
                .pattern(" P ")
                .pattern(" S ")
                .define('P', Items.PAPER)
                .define('S', Items.STICK),
            Items.PAPER
        )

        saveShapedRecipe(
            shaped(RecipeCategory.BUILDING_BLOCKS, JModBlocks.CALLIGRAPHY_TABLE)
                .pattern("PPP")
                .pattern(" S ")
                .pattern("WDW")
                .define('P', Items.PAPER)
                .define('S', JModItems.SCROLL)
                .define('W', ItemTags.PLANKS)
                .define('D', Items.BLACK_DYE),
            Items.PAPER
        )*/



        saveShapedRecipe(
            shaped(RecipeCategory.FOOD, JModBlocks.CHOCOLATE_CAKE)
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
            shaped(RecipeCategory.MISC, JModItems.CHISEL)
                .pattern("ABC")
                .pattern("EDE")
                .pattern("CBA")
                .define('A', Items.WIND_CHARGE)
                .define('B', JModItems.PYRITE_INGOT)
                .define('C', Items.BLAZE_POWDER)
                .define('D', Items.STICK)
                .define('E', Items.GHAST_TEAR),

            Items.GHAST_TEAR
        )

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, JModItems.PYRITE_INGOT, 9)
                .requires(JModBlocks.PYRITE_BLOCK),
            JModBlocks.PYRITE_BLOCK.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModBlocks.TRANSFORMER_BLOCK)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', JModBlocks.PYRITE_BLOCK)
                .define('B', JModItems.CHISEL),
            JModItems.CHISEL
        )

        pickaxe(JModItems.PYRITE_INGOT, JModItems.PYRITE_PICKAXE)

        axe(JModItems.PYRITE_INGOT, JModItems.PYRITE_AXE)

        shovel(JModItems.PYRITE_INGOT, JModItems.PYRITE_SHOVEL)

        hoe(JModItems.PYRITE_INGOT, JModItems.PYRITE_HOE)

        sword(JModItems.NEPHRITE, JModItems.NEPHRITE_SWORD)

        pickaxe(JModItems.NEPHRITE, JModItems.NEPHRITE_PICKAXE)

        axe(JModItems.NEPHRITE, JModItems.NEPHRITE_AXE)

        shovel(JModItems.NEPHRITE, JModItems.NEPHRITE_SHOVEL)

        hoe(JModItems.NEPHRITE, JModItems.NEPHRITE_HOE)


        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModItems.PYRITE_HAMMER)
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" B ")
                .define('A', JModItems.PYRITE_INGOT)
                .define('B', Items.STICK),
            JModItems.PYRITE_INGOT
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModItems.PYRITE_BATTLE_AXE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', JModItems.PYRITE_INGOT)
                .define('B', Items.STICK),
            JModItems.PYRITE_INGOT
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModItems.IRON_BATTLE_AXE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK),
            Items.IRON_INGOT
        )

        helmet(JModItems.PYRITE_INGOT, JModItems.PYRITE_HELMET)

        chestplate(JModItems.PYRITE_INGOT, JModItems.PYRITE_CHESTPLATE)

        leggings(JModItems.PYRITE_INGOT, JModItems.PYRITE_LEGGINGS)

        boots(JModItems.PYRITE_INGOT, JModItems.PYRITE_BOOTS)

        helmet(JModItems.NEPHRITE, JModItems.NEPHRITE_HELMET)

        chestplate(JModItems.NEPHRITE, JModItems.NEPHRITE_CHESTPLATE)

        leggings(JModItems.NEPHRITE, JModItems.NEPHRITE_LEGGINGS)

        boots(JModItems.NEPHRITE, JModItems.NEPHRITE_BOOTS)

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, JModItems.NEPHRITE, 9)
                .requires(JModBlocks.NEPHRITE_BLOCK),
            JModBlocks.NEPHRITE_BLOCK.asItem()
        )

        saveShapelessRecipe(
            shapeless(RecipeCategory.MISC, JModItems.RAW_PYRITE, 9)
                .requires(JModBlocks.RAW_PYRITE_BLOCK),
            JModBlocks.RAW_PYRITE_BLOCK.asItem()
        )

        saveShapedRecipe(
            shaped(RecipeCategory.FOOD, JModItems.SUSHI)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', JModItems.RICE)
                .define('B', Items.COOKED_SALMON),
            JModItems.RICE
        )

        saveShapedRecipe(
            shaped(RecipeCategory.FOOD, JModItems.RAMEN)
                .pattern(" K ")
                .pattern(" A ")
                .pattern("WB ")
                .define('B', Items.BOWL)
                .define('A', Items.WHEAT)
                .define('K', Items.KELP)
                .define('W', Items.POTION),
            Items.BOWL
        )

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModItems.SOUL_DAGGER)
                .pattern("  S")
                .pattern(" S ")
                .pattern("T  ")
                .define('S', Items.SOUL_SAND)
                .define('T', Items.STICK),
            Items.STICK
        )

        planksFromLogs(JModBlocks.HINOKI_BAN, ModTags.Items.HINOKI_MARUTA, 4)

        woodFromLogs(JModBlocks.HINOKI_MOKUZAI, JModBlocks.HINOKI_MARUTA)

        smelting(this.output,
            PYRITE_SMELTABLES as MutableList<ItemLike>, RecipeCategory.MISC, CookingBookCategory.MISC, JModItems.PYRITE_INGOT, 1f, 200, "pyrite")
        oreBlasting(this.output, PYRITE_SMELTABLES, RecipeCategory.MISC, JModItems.PYRITE_INGOT, 1f, 100, "pyrite",
            CookingBookCategory.MISC)
        smoking(
            this.output,
            listOf<ItemLike>(JModItems.RAW_RICE) as MutableList<ItemLike>,
            RecipeCategory.FOOD,
            JModItems.RICE,
            1f,
            100,
            "rice",
            CookingBookCategory.FOOD
        )

        stairBuilder(JModBlocks.PYRITE_STAIRS, Ingredient.of(JModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(JModItems.PYRITE_INGOT), has(JModItems.PYRITE_INGOT)).save(this.output)

        stairBuilder(JModBlocks.HINOKI_STAIRS, Ingredient.of(JModBlocks.HINOKI_BAN)).group("hinoki")
            .unlockedBy(getHasName(JModBlocks.HINOKI_BAN), has(JModBlocks.HINOKI_BAN)).save(this.output)

        buttonBuilder(JModBlocks.PYRITE_BUTTON, Ingredient.of(JModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(JModItems.PYRITE_INGOT), has(JModItems.PYRITE_INGOT)).save(this.output)

        buttonBuilder(JModBlocks.HINOKI_BUTTON, Ingredient.of(JModBlocks.HINOKI_BAN)).group("hinoki")
            .unlockedBy(getHasName(JModBlocks.HINOKI_BAN), has(JModBlocks.HINOKI_BAN)).save(this.output)

        pressurePlate(JModBlocks.PYRITE_PRESSURE_PLATE, JModItems.PYRITE_INGOT)

        pressurePlate(JModBlocks.HINOKI_PRESSURE_PLATE, JModBlocks.HINOKI_BAN)

        fenceBuilder(JModBlocks.PYRITE_FENCE, Ingredient.of(JModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(JModItems.PYRITE_INGOT), has(JModItems.PYRITE_INGOT)).save(this.output)

        fenceGateBuilder(JModBlocks.PYRITE_FENCE_GATE, Ingredient.of(JModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(JModItems.PYRITE_INGOT), has(JModItems.PYRITE_INGOT)).save(this.output)

        fenceBuilder(JModBlocks.HINOKI_FENCE, Ingredient.of(JModBlocks.HINOKI_BAN)).group("hinoki")
            .unlockedBy(getHasName(JModBlocks.HINOKI_BAN), has(JModBlocks.HINOKI_BAN)).save(this.output)

        fenceGateBuilder(JModBlocks.HINOKI_FENCE_GATE, Ingredient.of(JModBlocks.HINOKI_BAN)).group("pyrite")
            .unlockedBy(getHasName(JModBlocks.HINOKI_BAN), has(JModBlocks.HINOKI_BAN)).save(this.output)

        wall(RecipeCategory.BUILDING_BLOCKS, JModBlocks.PYRITE_WALL, JModItems.PYRITE_INGOT)

        doorBuilder(JModBlocks.PYRITE_DOOR, Ingredient.of(JModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(JModItems.PYRITE_INGOT), has(JModItems.PYRITE_INGOT)).save(this.output)

        trapdoorBuilder(JModBlocks.PYRITE_TRAPDOOR.asItem(), Ingredient.of(JModItems.PYRITE_INGOT)).group("pyrite")
            .unlockedBy(getHasName(JModItems.PYRITE_INGOT), has(JModItems.PYRITE_INGOT)).save(this.output)

        slab(RecipeCategory.BUILDING_BLOCKS, JModBlocks.PYRITE_SLAB, JModItems.PYRITE_INGOT)

        slab(RecipeCategory.BUILDING_BLOCKS, JModBlocks.HINOKI_SLAB, JModBlocks.HINOKI_BAN)

        saveShapedRecipe(
            shaped(RecipeCategory.COMBAT, JModItems.KATANA)
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
        recipeOutput: RecipeOutput, pIngredients: MutableList<ItemLike>, pCategory: RecipeCategory, pCookingCategory: CookingBookCategory,  pResult: ItemLike,
        pExperience: Float, pCookingTIme: Int, pGroup: String
    ) {
        cooking<SmeltingRecipe>(
            recipeOutput,
            SmeltingRecipe.SERIALIZER,
            { p_250200_, p_251114_, p_250340_, p_250306_, p_249577_, p_250030_ ->
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
            pCookingCategory,
            pResult,
            pExperience,
            pCookingTIme,
            pGroup,
            "_from_smelting"
        )
    }

    protected fun smoking(
        recipeOutput: RecipeOutput, pIngredients: MutableList<ItemLike>, pCategory: RecipeCategory, pResult: ItemLike,
        pExperience: Float, pCookingTIme: Int, pGroup: String, pCookingCategory: CookingBookCategory
    ) {
        cooking<SmokingRecipe>(
            recipeOutput,
            SmokingRecipe.SERIALIZER,
            { p_249312_, p_251017_, p_252345_, p_250002_, p_250535_: Float, p_251222_: Int ->
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
            pCookingCategory,
            pResult,
            pExperience,
            pCookingTIme,
            pGroup,
            "_from_smelting"
        )
    }

    protected fun oreBlasting(
        recipeOutput: RecipeOutput, pIngredients: MutableList<ItemLike>, pCategory: RecipeCategory, pResult: ItemLike,
        pExperience: Float, pCookingTime: Int, pGroup: String, pCookingCategory: CookingBookCategory
    ) {
        cooking<BlastingRecipe>(
            recipeOutput,
            BlastingRecipe.SERIALIZER,
            { p_251053_, p_249936_, p_251550_, p_251027_, p_250843_: Float, p_249841_: Int ->
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
            pCookingCategory,
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
        pCookingCategory: CookingBookCategory,
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
                pCookingCategory,
                pResult,
                pExperience,
                pCookingTime,
                factory
            ).group(pGroup).unlockedBy(
                getHasName(itemlike), has(itemlike)
            )
                .save(
                    recipeOutput,
                    JAPANESE_MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike)
                )
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ModRecipeProvider::class.java)

        fun smithingTrims(): Stream<TrimTemplate> {
            return Stream.of<Pair<SmithingTemplateItem, ResourceKey<TrimPattern>>>(
                Pair.of<SmithingTemplateItem, ResourceKey<TrimPattern>>(
                    JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE,
                    ModTrimPatterns.KOI_FISH
                )
            )
                .map<TrimTemplate> { registryObjectResourceKeyPair: Pair<SmithingTemplateItem, ResourceKey<TrimPattern>> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        Identifier.fromNamespaceAndPath(
                            JAPANESE_MOD_ID,
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
                    Items.LIGHTNING_ROD.asList().first(),
                    KanjiTypes.LIGHTNING
                )
            )
                .map<KanjiTemplate> { registryObjectResourceKeyPair: Pair<Item, ResourceKey<KanjiType>> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        Identifier.fromNamespaceAndPath(
                            JAPANESE_MOD_ID,
                            getItemName(item) + "_smithing_kanji"
                        )
                    )
                    KanjiTemplate(item, resourceKey, resourceKey1)
                }
        }

        fun smithingBlade(): Stream<BladeTemplate> {
            return Stream.of<Pair<Item, BladeType>>(
                Pair.of<Item, BladeType>(
                    JModItems.BLADE_STEEL,
                    BladeType.STEEL
                )
            )
                .map<BladeTemplate> { registryObjectResourceKeyPair: Pair<Item, BladeType> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        Identifier.fromNamespaceAndPath(
                            JAPANESE_MOD_ID,
                            getItemName(item) + "_smithing_blade"
                        )
                    )
                    BladeTemplate(item, resourceKey, resourceKey1)
                }
        }

        fun smithingWrapper(): Stream<WrappingTemplate> {
            return Stream.of<Pair<Item, Wrapping>>(
                Pair.of<Item, Wrapping>(
                    JModItems.WHITE_WRAP,
                    Wrapping.WHITE
                ),
                Pair.of(
                    JModItems.BLACK_WRAP,
                    Wrapping.BLACK
                ),
                Pair.of(
                    JModItems.RED_WRAP,
                    Wrapping.RED
                )
            )
                .map<WrappingTemplate> { registryObjectResourceKeyPair: Pair<Item, Wrapping> ->
                    val item = registryObjectResourceKeyPair.getFirst()
                    val resourceKey = registryObjectResourceKeyPair.getSecond()
                    val resourceKey1 = ResourceKey.create(
                        Registries.RECIPE,
                        Identifier.fromNamespaceAndPath(
                            JAPANESE_MOD_ID,
                            getItemName(item) + "_smithing_wrapper"
                        )
                    )
                    WrappingTemplate(item, resourceKey, resourceKey1)
                }
        }
    }
}
