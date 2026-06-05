package net.Chidoziealways.everythingjapanese.datagen

import com.mojang.math.Quadrant
import net.Chidoziealways.everythingcore.datagen.createBlockStateDefinition
import net.Chidoziealways.everythingcore.datagen.createBlockstateWithRotation
import net.Chidoziealways.everythingcore.datagen.createItemDefiniton
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.item.ModEquipmentAssets
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.BladeWrapProperty
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.Chidoziealways.everythingjapanese.kanji.KanjiProperty
import net.Chidoziealways.everythingjapanese.karma.KarmaProperty
import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.Chidoziealways.everythingjapanese.trim.ModMaterialAssetGroup
import net.Chidoziealways.everythingjapanese.trim.ModTrimMaterials
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.client.color.item.Dye
import net.minecraft.client.data.models.*
import net.minecraft.client.data.models.BlockModelGenerators.plainVariant
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator
import net.minecraft.client.data.models.blockstates.PropertyDispatch
import net.minecraft.client.data.models.model.*
import net.minecraft.client.data.models.model.ItemModelUtils.select
import net.minecraft.client.renderer.block.dispatch.VariantMutator
import net.minecraft.client.renderer.item.ItemModel
import net.minecraft.client.renderer.item.SelectItemModel
import net.minecraft.client.renderer.item.properties.numeric.CustomModelDataProperty
import net.minecraft.client.renderer.item.properties.select.DisplayContext
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty
import net.minecraft.client.resources.model.sprite.Material
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup
import net.minecraft.world.item.equipment.trim.TrimMaterial
import net.minecraft.world.item.equipment.trim.TrimMaterials
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoorHingeSide
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class ModModelProvider(output: PackOutput, val lookup: CompletableFuture<HolderLookup.Provider>) : ModelProvider(output, JAPANESE_MOD_ID) {
    override fun registerModels(blockModels: BlockModelGenerators, itemModels: ItemModelGenerators) {
        /*---BLOCKS---*/
        blockModels.createTrivialCube(JModBlocks.TRANSFORMER_BLOCK )
        blockModels.createTrivialCube(JModBlocks.RAW_PYRITE_BLOCK )
        blockModels.createNonTemplateModelBlock(JModBlocks.BLOOD)
        blockModels.createTrivialCube(JModBlocks.PYRITE_DEEPSLATE_ORE )
        blockModels.createTrivialCube(JModBlocks.PYRITE_ORE )
        blockModels.createBlockStateDefinition(JModBlocks.JAPANESE_FLAG, "japanese_flag", JAPANESE_MOD_ID)
        blockModels.createChocolateCake()
        blockModels.createChair()
        blockModels.createJapaneseCheeseCake()
        blockModels.createPedestal()
        //blockModels.createCalligraphyTable()
        blockModels.createChabudai()
        blockModels.createTatamiMat()
        blockModels.createBlockstateWithRotation(JModBlocks.HANGING_SCROLL, "hanging_scroll", "everythingjapanese")
        blockModels.createZabuton(JModBlocks.ZABUTON_BLUE, "zabuton_blue")
        blockModels.createZabuton(JModBlocks.ZABUTON_RED, "zabuton_red")
        blockModels.createZabuton(JModBlocks.ZABUTON_GREEN, "zabuton_green")
        itemModels.createTatamiMat()
        blockModels.createShojiDoor()
        blockModels.createMoneyVault()
        blockModels.createFusumaDoor()
        blockModels.createTrivialCube(JModBlocks.NEPHRITE_BLOCK )
        blockModels.createTrivialCube(JModBlocks.NEPHRITE_DEEPSLATE_ORE )
        blockModels.createGlassBlocks(JModBlocks.WASHI_WINDOW , JModBlocks.WASHI_WINDOW_PANE )
        blockModels.createGlassBlocks(JModBlocks.SHOJI_WINDOW , JModBlocks.SHOJI_WINDOW_PANE )
        blockModels.createTrivialCube(JModBlocks.NEPHRITE_ORE )
        blockModels.family(JModBlocks.PYRITE_BLOCK )
            .stairs(JModBlocks.PYRITE_STAIRS )
            .slab(JModBlocks.PYRITE_SLAB )
            .button(JModBlocks.PYRITE_BUTTON )
            .pressurePlate(JModBlocks.PYRITE_PRESSURE_PLATE )
            .fence(JModBlocks.PYRITE_FENCE )
            .fenceGate(JModBlocks.PYRITE_FENCE_GATE )
            .wall(JModBlocks.PYRITE_WALL )
        blockModels.family(JModBlocks.HINOKI_BAN)
            .stairs(JModBlocks.HINOKI_STAIRS)
            .slab(JModBlocks.HINOKI_SLAB)
            .button(JModBlocks.HINOKI_BUTTON)
            .pressurePlate(JModBlocks.HINOKI_PRESSURE_PLATE)
            .fence(JModBlocks.HINOKI_FENCE)
            .fenceGate(JModBlocks.HINOKI_FENCE_GATE)
        blockModels.woodProvider(JModBlocks.HINOKI_MARUTA )
            .logWithHorizontal(JModBlocks.HINOKI_MARUTA )
            .wood(JModBlocks.HINOKI_MOKUZAI )
        blockModels.woodProvider(JModBlocks.STRIPPED_HINOKI_MARUTA )
            .logWithHorizontal(JModBlocks.STRIPPED_HINOKI_MARUTA )
            .wood(JModBlocks.STRIPPED_HINOKI_MOKUZAI )
        blockModels.createDoor(JModBlocks.PYRITE_DOOR )
        blockModels.createTrapdoor(JModBlocks.PYRITE_TRAPDOOR )
        blockModels.createCropBlock(JModBlocks.RICE_CROP , ModBlockStateProperties.AGE_4, 0, 1, 2, 3, 4)
        blockModels.createYamazakiBerryBush()
        /*blockModels.createTrivialBlock(
            ModBlocks.JAPANESE_CHEESECAKE,
            TexturedModel.createDefault (
                {block ->
                    TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block))},
                ExtendedModelTemplateBuilder.builder()
                    .customLoader(::ObjModelBuilder) {loader -> }
                    .requiredTextureSlot(TextureSlot.ALL).build()
        ))
         */
        blockModels.createLamp()
        blockModels.createTintedLeaves(JModBlocks.HINOKI_HA , TexturedModel.LEAVES, -12012264)
        blockModels.createPlantWithDefaultItem(
            JModBlocks.HINOKI_NAEGI ,
            JModBlocks.POTTED_HINOKI_NAEGI ,
            BlockModelGenerators.PlantType.NOT_TINTED
        )
        blockModels.createTrivialCube(JModBlocks.GROWTH_CHAMBER )
        blockModels.createHellPortalBlock()
        
        /*---ITEMS---*/
        itemModels.generateFlatItem(JModItems.RAW_PYRITE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_INGOT!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.DIESEL!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.GREEN_TEA!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.SUSHI!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.INCENSE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.UDON!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModBlocks.ZABUTON_BLUE.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModBlocks.ZABUTON_RED.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModBlocks.ZABUTON_GREEN.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.CREDIT_CARD_ITEM, ModelTemplates.FLAT_ITEM)
        itemModels.createChisel()
        itemModels.createIronBattleAxe()
        itemModels.createRadiationStaff()
        itemModels.generateFlatItem(JModItems.YA!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.AO_TO_NATSU_MUSIC_DISC!!, ModelTemplates.MUSIC_DISC)
        itemModels.generateFlatItem(JModItems.SOUL_DAGGER, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.RAW_RICE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.RICE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.CHIRETSU_SHO_SCROLL, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.EKIRETSU_SHO_SCROLL, ModelTemplates.FLAT_HANDHELD_ITEM)
        //itemModels.generateFlatItem(JModItems.SCROLL, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.RAMEN, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.FIREBALL_SCROLL!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.WINDBALL_SCROLL!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        //itemModels.generateFlatItem(JModItems.HELL_PORTAL_ACTIVATOR!!, ModelTemplates.FLAT_HANDHELD_ROD_ITEM)
        itemModels.generateBow(JModItems.DAIKYU!!)
        itemModels.generateFlatItem(JModItems.POCKET_BLADE, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.createPoweredSword()
        itemModels.generateFlatItem(JModItems.NEPHRITE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_SWORD!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_PICKAXE!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_AXE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_HOE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_SHOVEL!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_BATTLE_AXE!! , ModelTemplates.FLAT_HANDHELD_MACE_ITEM)
        itemModels.generateFlatItem(JModItems.NEPHRITE_SWORD!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.NEPHRITE_PICKAXE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.NEPHRITE_AXE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.NEPHRITE_SHOVEL!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.NEPHRITE_HOE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.PYRITE_HAMMER!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(JModItems.BLADE_STEEL, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.BLACK_WRAP, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.RED_WRAP, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.WHITE_WRAP, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.BLOOD_BUCKET, ModelTemplates.FLAT_ITEM)
        itemModels.generateTrimmableItem(
            JModItems.PYRITE_HELMET!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_HELMET,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.PYRITE_CHESTPLATE!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
            false
        )
        itemModels.createTalisman()
        itemModels.createKatana(JModItems.KATANA, false)
        itemModels.generateTrimmableItem(
            JModItems.PYRITE_LEGGINGS!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.PYRITE_BOOTS!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_BOOTS,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.NEPHRITE_HELMET!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_HELMET,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.NEPHRITE_CHESTPLATE!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.NEPHRITE_LEGGINGS!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.NEPHRITE_BOOTS!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_BOOTS,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.SAMURAI_HELMET!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_HELMET,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.SAMURAI_CHESTPLATE!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.SAMURAI_LEGGINGS!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
            false
        )
        itemModels.generateTrimmableItem(
            JModItems.SAMURAI_BOOTS!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_BOOTS,
            false
        )
        itemModels.generateFlatItem(JModItems.PYRITE_HORSE_ARMOR!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.SIKA_DEER_SPAWN_EGG!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.TRICERATOPS_SPAWN_EGG!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.CURSED_SAMURAI_SPAWN_EGG , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JModItems.BULLET, ModelTemplates.FLAT_ITEM)
        itemModels.createItemDefiniton(JModItems.GUN, "gun", JAPANESE_MOD_ID)
    }
    fun BlockModelGenerators.createYamazakiBerryBush() {
        this.registerSimpleFlatItemModel(JModItems.YAMAZAKI_BERRIES)
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(JModBlocks.YAMAZAKI_BERRY_BUSH )
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.AGE_3)
                            .generate { p_389159_: Int ->
                                plainVariant(
                                    this.createSuffixedVariant(
                                        JModBlocks.YAMAZAKI_BERRY_BUSH,
                                        "_stage$p_389159_",
                                        ModelTemplates.CROSS
                                    ) { p_378193_: Material ->
                                          TextureMapping.cross(p_378193_)
                                    }
                                )
                            }
                    )
            )
    }

    fun BlockModelGenerators.createLamp() {
        val multivariant: MultiVariant = plainVariant(
            TexturedModel.CUBE.create(
                JModBlocks.PYRITE_LAMP,
                this.modelOutput
            )
        )
        val multivariant1: MultiVariant = plainVariant(
            this.createSuffixedVariant(
                JModBlocks.PYRITE_LAMP,
                "_on",
                ModelTemplates.CUBE_ALL
            ) { p_377582_: Material -> TextureMapping.cube(p_377582_) }
        )
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(JModBlocks.PYRITE_LAMP).with(
                    BlockModelGenerators.createBooleanModelDispatch(
                        ModBlockStateProperties.CLICKED,
                        multivariant1,
                        multivariant
                    )
                )
            )
    }

    fun BlockModelGenerators.createChocolateCake() {
        this.registerSimpleFlatItemModel(JModBlocks.CHOCOLATE_CAKE.asItem()) // or your custom cake item if any

        this.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(JModBlocks.CHOCOLATE_CAKE)
                .with(
                    PropertyDispatch.initial(ModBlockStateProperties.BITES_16)
                        .select(0, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake")))
                        .select(1, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice1")))
                        .select(2, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice2")))
                        .select(3, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice3")))
                        .select(4, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice4")))
                        .select(5, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice5")))
                        .select(6, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice6")))
                        .select(7, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice7")))
                        .select(8, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice8")))
                        .select(9, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice9")))
                        .select(10, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice10")))
                        .select(11, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice11")))
                        .select(12, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice12")))
                        .select(13, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice13")))
                        .select(14, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice14")))
                        .select(15, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice15")))
                        .select(16, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chocolate_cake_slice16")))
                ))
    }

    fun BlockModelGenerators.createJapaneseCheeseCake() {
        registerSimpleFlatItemModel(JModBlocks.JAPANESE_CHEESECAKE.asItem())

        this.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(JModBlocks.JAPANESE_CHEESECAKE)
                .with(
                    PropertyDispatch.initial(ModBlockStateProperties.BITES_11)
                        .select(0, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake")))
                        .select(1, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice1")))
                        .select(2, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice2")))
                        .select(3, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice3")))
                        .select(4, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice4")))
                        .select(5, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice5")))
                        .select(6, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice6")))
                        .select(7, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice7")))
                        .select(8, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice8")))
                        .select(9, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice9")))
                        .select(10, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice10")))
                        .select(11, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/japanese_cheesecake_slice11")))))
    }

    fun BlockModelGenerators.createShojiDoor() {
        val block = JModBlocks.SHOJI_DOOR

        registerSimpleFlatItemModel(block.asItem())

        val closedLeft = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))
        val closedRight = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))
        val openLeft = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))
        val openRight = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))

        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(
                    PropertyDispatch.initial(
                        BlockStateProperties.HORIZONTAL_FACING,
                        BlockStateProperties.OPEN,
                        BlockStateProperties.DOOR_HINGE
                    )
                        // NORTH
                        .select(Direction.NORTH, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R0)))
                        .select(Direction.NORTH, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R0)))
                        .select(Direction.NORTH, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R0)))
                        .select(Direction.NORTH, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R0)))

                        // SOUTH
                        .select(Direction.SOUTH, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.SOUTH, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.SOUTH, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.SOUTH, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R180)))

                        // EAST
                        .select(Direction.EAST, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R90)))
                        .select(Direction.EAST, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R90)))
                        .select(Direction.EAST, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R90)))
                        .select(Direction.EAST, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R90)))

                        // WEST
                        .select(Direction.WEST, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                        .select(Direction.WEST, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                        .select(Direction.WEST, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                        .select(Direction.WEST, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                )
        )
    }

    fun BlockModelGenerators.createFusumaDoor() {
        val block = JModBlocks.FUSUMA_DOOR

        registerSimpleFlatItemModel(block.asItem())

        val closedLeft = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))
        val closedRight = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))
        val openLeft = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))
        val openRight = plainVariant(Identifier.withDefaultNamespace("builtin/entity"))

        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(
                    PropertyDispatch.initial(
                        BlockStateProperties.HORIZONTAL_FACING,
                        BlockStateProperties.OPEN,
                        BlockStateProperties.DOOR_HINGE
                    )
                        // NORTH
                        .select(Direction.NORTH, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R0)))
                        .select(Direction.NORTH, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R0)))
                        .select(Direction.NORTH, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R0)))
                        .select(Direction.NORTH, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R0)))

                        // SOUTH
                        .select(Direction.SOUTH, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.SOUTH, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.SOUTH, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.SOUTH, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R180)))

                        // EAST
                        .select(Direction.EAST, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R90)))
                        .select(Direction.EAST, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R90)))
                        .select(Direction.EAST, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R90)))
                        .select(Direction.EAST, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R90)))

                        // WEST
                        .select(Direction.WEST, false, DoorHingeSide.LEFT, closedLeft.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                        .select(Direction.WEST, false, DoorHingeSide.RIGHT, closedRight.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                        .select(Direction.WEST, true, DoorHingeSide.LEFT, openLeft.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                        .select(Direction.WEST, true, DoorHingeSide.RIGHT, openRight.with(rotationMutator(Quadrant.R0, Quadrant.R270)))
                )
        )
    }

    fun BlockModelGenerators.createPedestal() {
        val variant = plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/pedestal"))

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(JModBlocks.PEDESTAL, variant))
    }

    fun BlockModelGenerators.createChabudai() {
        val variant = plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chabudai"))

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(JModBlocks.CHABUDAI, variant))
    }

    fun BlockModelGenerators.createTatamiMat() {
        val variant = plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/tatami_mat"))

        this.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(JModBlocks.TATAMI_MAT)
                .with(
                    PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING)
                        .select(Direction.NORTH, variant)
                        .select(Direction.SOUTH, variant.with(
                            rotationMutator(Quadrant.R0, Quadrant.R180)
                        ))
                        .select(Direction.EAST, variant.with(
                            rotationMutator(Quadrant.R0, Quadrant.R90)
                        ))
                        .select(Direction.WEST, variant.with(
                            rotationMutator(Quadrant.R0, Quadrant.R270)
                        ))
                ))
    }

    fun ItemModelGenerators.createTatamiMat() {
        val model = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/tatami_mat"))
        this.itemModelOutput.accept(JModBlocks.TATAMI_MAT.asItem(), model)
    }

    fun rotationMutator(xRotation: Quadrant, yRotation: Quadrant): VariantMutator {
        return VariantMutator.X_ROT.withValue(xRotation)
            .then(VariantMutator.Y_ROT.withValue(yRotation))
    }

    fun BlockModelGenerators.createChair() {
        this.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(JModBlocks.CHAIR)
                .with(
                    PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING)
                        .select(Direction.NORTH, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chair")))
                        .select(Direction.SOUTH, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chair")).with(
                            rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.EAST,  plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chair")).with(
                            rotationMutator(Quadrant.R0, Quadrant.R90)
                        ))
                        .select(Direction.WEST,  plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/chair")).with(
                            rotationMutator(Quadrant.R0, Quadrant.R270)
                        ))
                )
        )
    }

    fun BlockModelGenerators.createZabuton(block: Block, name: String) {
        val variant = plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/$name"))

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, variant))
    }

    fun BlockModelGenerators.createHellPortalBlock() {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(JModBlocks.HELL_PORTAL)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_AXIS)
                            .select(
                                Direction.Axis.X,
                                plainVariant(
                                    ModelLocationUtils.getModelLocation(
                                        JModBlocks.HELL_PORTAL,
                                        "_ns"
                                    )
                                )
                            )
                            .select(
                                Direction.Axis.Z,
                                plainVariant(
                                    ModelLocationUtils.getModelLocation(
                                        JModBlocks.HELL_PORTAL,
                                        "_ew"
                                    )
                                )
                            )
                    )
            )
    }

    fun ItemModelGenerators.createTalisman() {
        val base = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/talisman_base"))

        // Property: the actual KanjiType from the component
        val kanjiProp = KanjiProperty()

        // Build overlay cases dynamically from registry
        val overlayCases = mutableListOf<SelectItemModel.SwitchCase<ResourceKey<KanjiType>>>()

        val lookup = this@ModModelProvider.lookup.get() // HolderLookup.Provider from model gen context
        val kanjiRegistry = lookup.lookupOrThrow(ModRegistries.KANJI)

        for (entry in kanjiRegistry.listElements()) {
            val key: ResourceKey<KanjiType> = entry.key()
            val kanjiType = entry.value()
            val overlayModel = ItemModelUtils.plainModel(
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/talisman_${kanjiType.id}_overlay")
            )
            overlayCases.add(ItemModelUtils.`when`(key, overlayModel))
        }

        // Selector: overlay based on KanjiType
        val kanjiSelect = ItemModelUtils.select(
            kanjiProp,
            base, // fallback if none match
            *overlayCases.toTypedArray()
        )

        // Composite: base + overlay
        val composite = ItemModelUtils.composite(base, kanjiSelect)

        this.itemModelOutput.accept(JModItems.TALISMAN_ITEM, composite)
    }

    fun ItemModelGenerators.createKatana(item: Item, hasTint: Boolean) {
        val modelLocation: Identifier = ModelLocationUtils.getModelLocation(item)
        val baseTexture = TextureMapping.getItemTexture(item)

        val comboModels = mutableListOf<SelectItemModel.SwitchCase<Pair<BladeType, Wrapping>>>()

        for (blade in BladeType.entries) {
            for (wrap in Wrapping.entries) {
                val comboModelLocation = modelLocation.withSuffix("_${blade.id}_${wrap.id}")

                // Use the overlays provided by BladeType and Wrapping
                val bladeTexture = Material(blade.overlay)
                val wrapTexture = Material(wrap.overlay)

                // Generate layered item for this blade+wrap combo
                this.generateLayeredItem(comboModelLocation, baseTexture, bladeTexture, wrapTexture)

                val unbaked = if (hasTint) {
                    ItemModelUtils.tintedModel(comboModelLocation, Dye(-6265536))
                } else {
                    ItemModelUtils.plainModel(comboModelLocation)
                }

                comboModels.add(ItemModelUtils.`when`(blade to wrap, unbaked))
            }
        }

        // Base model (fallback)
        val baseModel = if (hasTint) {
            ModelTemplates.TWO_LAYERED_ITEM.create(modelLocation, TextureMapping.layered(baseTexture, Material(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "empty"))), this.modelOutput)
            ItemModelUtils.tintedModel(modelLocation, Dye(-6265536))
        } else {
            ModelTemplates.FLAT_HANDHELD_ITEM.create(modelLocation, TextureMapping.layer0(baseTexture), this.modelOutput)
            ItemModelUtils.plainModel(modelLocation)
        }

        // Select model based on both blade and wrap
        val select = ItemModelUtils.select(
            BladeWrapProperty(),
            baseModel,
            *comboModels.toTypedArray()
        )

        this.itemModelOutput.accept(item, select)
    }

    fun ItemModelGenerators.createPoweredSword() {
        val baseModel = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/powered_sword"))
        val divineModel = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/powered_sword_divine"))
        val cursedModel = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/powered_sword_cursed"))

        val prop = KarmaProperty()

        val entries = listOf(
            ItemModelUtils.override(divineModel, 1f),
            ItemModelUtils.override(cursedModel, Float.MIN_VALUE)
        )

        val model = ItemModelUtils.rangeSelect(prop, baseModel, entries)

        itemModelOutput.accept(JModItems.POWERED_SWORD, model)
    }

    fun ItemModelGenerators.createChisel() {
        val baseModel = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/chisel"))
        val usedModel = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/chisel_used"))

        val property = CustomModelDataProperty(0)

        val overrideEntry = ItemModelUtils.override(usedModel, 1.0f)

        val model = ItemModelUtils.rangeSelect(property, baseModel, overrideEntry)

        this.itemModelOutput.accept(JModItems.CHISEL, model)
    }

    fun ItemModelGenerators.createRadiationStaff() {
        val model2d = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/radiation_staff_2d"))
        val model3d = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/radiation_staff_3d"))

        val property = DisplayContext()

        val case2d = ItemModelUtils.`when`(listOf(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED), model2d)

        val model = ItemModelUtils.select(property, model3d, case2d)

        this.itemModelOutput.accept(JModItems.RADIATION_STAFF, model)
    }

    fun ItemModelGenerators.createIronBattleAxe() {
        val model = ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "item/iron_battle_axe"))
        this.itemModelOutput.accept(JModItems.IRON_BATTLE_AXE, model)
    }

    /*fun ItemModelGenerators.generateTrimmableItemE(item: Item, resourceKey: ResourceKey<EquipmentAsset>, identifier: Identifier, p_377962_: Boolean) {
        val main: Identifier = ModelLocationUtils.getModelLocation(item)
        val itemTexture1: Identifier = TextureMapping.getItemTexture(item)
        val itemTexture2: Identifier = TextureMapping.getItemTexture(item, "_overlay")
        val list: MutableList<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> =
            ArrayList(
                TRIM_MATERIAL_MODELS.size
            )

        for (trimMaterialData in TRIM_MATERIAL_MODELS) {
            val Identifier3 = main.withSuffix(
                "_" + trimMaterialData.assets.base().suffix() + "_trim"
            )
            val Identifier4 = identifier.withSuffix(
                "_" + trimMaterialData.assets.assetId(resourceKey).suffix()
            )
            val `itemmodel$unbaked`: ItemModel.Unbaked?
            if (p_377962_) {
                this.generateLayeredItem(Identifier3, itemTexture1, itemTexture2, Identifier4)
                `itemmodel$unbaked` = ItemModelUtils.tintedModel(Identifier3, Dye(-6265536))
            } else {
                this.generateLayeredItem(Identifier3, itemTexture1, Identifier4)
                `itemmodel$unbaked` = ItemModelUtils.plainModel(Identifier3)
            }

            list.add(
                ItemModelUtils.`when`(
                    trimMaterialData.materialKey,
                    `itemmodel$unbaked`
                )
            )
        }

        val `itemmodel$unbaked1`: ItemModel.Unbaked?
        if (p_377962_) {
            ModelTemplates.TWO_LAYERED_ITEM.create(
                main,
                TextureMapping.layered(itemTexture1, itemTexture2),
                this.modelOutput
            )
            `itemmodel$unbaked1` = ItemModelUtils.tintedModel(main, Dye(-6265536))
        } else {
            ModelTemplates.FLAT_ITEM.create(
                main,
                TextureMapping.layer0(itemTexture1),
                this.modelOutput
            )
            `itemmodel$unbaked1` = ItemModelUtils.plainModel(main)
        }

        this.itemModelOutput.accept(
            item,
            ItemModelUtils.select(TrimMaterialProperty(), `itemmodel$unbaked1`, list)
        )
    }

    private class MyItemModelGenerators(
        pItemModelOutput: ItemModelOutput,
        pModelOutput: BiConsumer<Identifier, ModelInstance>
    ) : ItemModelGenerators(pItemModelOutput, pModelOutput) {
        data class MyTrimMaterialData(val assets: MaterialAssetGroup, val materialKey: ResourceKey<TrimMaterial>)

        companion object {
            val TRIM_MATERIAL_MODELS: MutableList<MyTrimMaterialData> = listOf<MyTrimMaterialData>(
                MyTrimMaterialData(ModMaterialAssetGroup.PYRITE, ModTrimMaterials.PYRITE),
                MyTrimMaterialData(MaterialAssetGroup.QUARTZ, TrimMaterials.QUARTZ),
                MyTrimMaterialData(MaterialAssetGroup.IRON, TrimMaterials.IRON),
                MyTrimMaterialData(MaterialAssetGroup.NETHERITE, TrimMaterials.NETHERITE),
                MyTrimMaterialData(MaterialAssetGroup.REDSTONE, TrimMaterials.REDSTONE),
                MyTrimMaterialData(MaterialAssetGroup.COPPER, TrimMaterials.COPPER),
                MyTrimMaterialData(MaterialAssetGroup.GOLD, TrimMaterials.GOLD),
                MyTrimMaterialData(MaterialAssetGroup.EMERALD, TrimMaterials.EMERALD),
                MyTrimMaterialData(MaterialAssetGroup.DIAMOND, TrimMaterials.DIAMOND),
                MyTrimMaterialData(MaterialAssetGroup.LAPIS, TrimMaterials.LAPIS),
                MyTrimMaterialData(MaterialAssetGroup.AMETHYST, TrimMaterials.AMETHYST),
                MyTrimMaterialData(MaterialAssetGroup.RESIN, TrimMaterials.RESIN)
            ) as MutableList<MyTrimMaterialData>
        }
    }*/
}
