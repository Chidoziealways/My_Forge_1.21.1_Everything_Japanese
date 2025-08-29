package net.Chidoziealways.everythingjapanese.datagen

import com.mojang.math.Quadrant
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.datagen.ModModelProvider.MyItemModelGenerators.Companion.TRIM_MATERIAL_MODELS
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.item.ModEquipmentAssets
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.BladeWrapProperty
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.Chidoziealways.everythingjapanese.kanji.KanjiProperty
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
import net.minecraft.client.renderer.block.model.VariantMutator
import net.minecraft.client.renderer.item.ItemModel
import net.minecraft.client.renderer.item.SelectItemModel
import net.minecraft.client.renderer.item.properties.numeric.CustomModelDataProperty
import net.minecraft.client.renderer.item.properties.select.DisplayContext
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
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

class ModModelProvider(output: PackOutput, val lookup: CompletableFuture<HolderLookup.Provider>) : ModelProvider(output, MOD_ID) {
    override fun registerModels(blockModels: BlockModelGenerators, itemModels: ItemModelGenerators) {
        /*---BLOCKS---*/
        blockModels.createTrivialCube(ModBlocks.TRANSFORMER_BLOCK )
        blockModels.createTrivialCube(ModBlocks.RAW_PYRITE_BLOCK )
        blockModels.createNonTemplateModelBlock(ModBlocks.BLOOD)
        blockModels.createTrivialCube(ModBlocks.PYRITE_DEEPSLATE_ORE )
        blockModels.createTrivialCube(ModBlocks.PYRITE_ORE )
        blockModels.createChocolateCake()
        blockModels.createChair()
        blockModels.createJapaneseCheeseCake()
        blockModels.createPedestal()
        blockModels.createChabudai()
        blockModels.createTatamiMat()
        blockModels.createZabuton(ModBlocks.ZABUTON_BLUE, "zabuton_blue")
        blockModels.createZabuton(ModBlocks.ZABUTON_RED, "zabuton_red")
        blockModels.createZabuton(ModBlocks.ZABUTON_GREEN, "zabuton_green")
        itemModels.createTatamiMat()
        blockModels.createShojiDoor()
        blockModels.createFusumaDoor()
        blockModels.createTrivialCube(ModBlocks.NEPHRITE_BLOCK )
        blockModels.createTrivialCube(ModBlocks.NEPHRITE_DEEPSLATE_ORE )
        blockModels.createGlassBlocks(ModBlocks.WASHI_WINDOW , ModBlocks.WASHI_WINDOW_PANE )
        blockModels.createTrivialCube(ModBlocks.NEPHRITE_ORE )
        blockModels.createTrivialCube(ModBlocks.HINOKI_BAN )
        blockModels.family(ModBlocks.PYRITE_BLOCK )
            .stairs(ModBlocks.PYRITE_STAIRS )
            .slab(ModBlocks.PYRITE_SLAB )
            .button(ModBlocks.PYRITE_BUTTON )
            .pressurePlate(ModBlocks.PYRITE_PRESSURE_PLATE )
            .fence(ModBlocks.PYRITE_FENCE )
            .fenceGate(ModBlocks.PYRITE_FENCE_GATE )
            .wall(ModBlocks.PYRITE_WALL )
        blockModels.woodProvider(ModBlocks.HINOKI_MARUTA )
            .logWithHorizontal(ModBlocks.HINOKI_MARUTA )
            .wood(ModBlocks.HINOKI_MOKUZAI )
        blockModels.woodProvider(ModBlocks.STRIPPED_HINOKI_MARUTA )
            .logWithHorizontal(ModBlocks.STRIPPED_HINOKI_MARUTA )
            .wood(ModBlocks.STRIPPED_HINOKI_MOKUZAI )
        blockModels.createDoor(ModBlocks.PYRITE_DOOR )
        blockModels.createTrapdoor(ModBlocks.PYRITE_TRAPDOOR )
        blockModels.createCropBlock(ModBlocks.RICE_CROP , ModBlockStateProperties.AGE_4, 0, 1, 2, 3, 4)
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
        blockModels.createTintedLeaves(ModBlocks.HINOKI_HA , TexturedModel.LEAVES, -12012264)
        blockModels.createPlantWithDefaultItem(
            ModBlocks.HINOKI_NAEGI ,
            ModBlocks.POTTED_HINOKI_NAEGI ,
            BlockModelGenerators.PlantType.NOT_TINTED
        )
        blockModels.createTrivialCube(ModBlocks.GROWTH_CHAMBER )
        blockModels.createHellPortalBlock()
        
        /*---ITEMS---*/
        itemModels.generateFlatItem(ModItems.RAW_PYRITE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_INGOT!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.DIESEL!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.GREEN_TEA!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.SUSHI!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.INCENSE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.UDON!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModBlocks.ZABUTON_BLUE.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModBlocks.ZABUTON_RED.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModBlocks.ZABUTON_GREEN.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.createChisel()
        itemModels.createIronBattleAxe()
        itemModels.createRadiationStaff()
        itemModels.generateFlatItem(ModItems.YA!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.AO_TO_NATSU_MUSIC_DISC!!, ModelTemplates.MUSIC_DISC)
        itemModels.generateFlatItem(ModItems.RAW_RICE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.RICE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.CHIRETSU_SHO_SCROLL, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.FIREBALL_SCROLL!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.WINDBALL_SCROLL!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.HELL_PORTAL_ACTIVATOR!!, ModelTemplates.FLAT_HANDHELD_ROD_ITEM)
        itemModels.generateBow(ModItems.DAIKYU!!)
        itemModels.generateFlatItem(ModItems.NEPHRITE!!, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_SWORD!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_PICKAXE!!, ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_AXE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_HOE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_SHOVEL!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_BATTLE_AXE!! , ModelTemplates.FLAT_HANDHELD_MACE_ITEM)
        itemModels.generateFlatItem(ModItems.NEPHRITE_SWORD!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.NEPHRITE_PICKAXE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.NEPHRITE_AXE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.NEPHRITE_SHOVEL!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.NEPHRITE_HOE!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.PYRITE_HAMMER!! , ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(ModItems.BLADE_STEEL, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.BLACK_WRAP, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.RED_WRAP, ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.WHITE_WRAP, ModelTemplates.FLAT_ITEM)
        itemModels.generateTrimmableItemE(
            ModItems.PYRITE_HELMET!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_HELMET,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.PYRITE_CHESTPLATE!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
            false
        )
        itemModels.createTalisman()
        itemModels.createKatana(ModItems.KATANA, false)
        itemModels.generateTrimmableItemE(
            ModItems.PYRITE_LEGGINGS!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.PYRITE_BOOTS!! ,
            ModEquipmentAssets.Companion.PYRITE,
            ItemModelGenerators.TRIM_PREFIX_BOOTS,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.NEPHRITE_HELMET!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_HELMET,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.NEPHRITE_CHESTPLATE!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.NEPHRITE_LEGGINGS!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.NEPHRITE_BOOTS!! ,
            ModEquipmentAssets.Companion.NEPHRITE,
            ItemModelGenerators.TRIM_PREFIX_BOOTS,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.SAMURAI_HELMET!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_HELMET,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.SAMURAI_CHESTPLATE!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.SAMURAI_LEGGINGS!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
            false
        )
        itemModels.generateTrimmableItemE(
            ModItems.SAMURAI_BOOTS!! ,
            ModEquipmentAssets.Companion.SAMURAI,
            ItemModelGenerators.TRIM_PREFIX_BOOTS,
            false
        )
        itemModels.generateFlatItem(ModItems.PYRITE_HORSE_ARMOR!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.SIKA_DEER_SPAWN_EGG!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.TRICERATOPS_SPAWN_EGG!! , ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(ModItems.CURSED_SAMURAI_SPAWN_EGG , ModelTemplates.FLAT_ITEM)
    }

    fun BlockModelGenerators.createYamazakiBerryBush() {
        this.registerSimpleFlatItemModel(ModItems.YAMAZAKI_BERRIES)
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(ModBlocks.YAMAZAKI_BERRY_BUSH )
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.AGE_3)
                            .generate { p_389159_: Int ->
                                plainVariant(
                                    this.createSuffixedVariant(
                                        ModBlocks.YAMAZAKI_BERRY_BUSH,
                                        "_stage$p_389159_",
                                        ModelTemplates.CROSS
                                    ) { p_378193_: ResourceLocation ->
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
                ModBlocks.PYRITE_LAMP,
                this.modelOutput
            )
        )
        val multivariant1: MultiVariant = plainVariant(
            this.createSuffixedVariant(
                ModBlocks.PYRITE_LAMP,
                "_on",
                ModelTemplates.CUBE_ALL
            ) { p_377582_: ResourceLocation -> TextureMapping.cube(p_377582_) }
        )
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(ModBlocks.PYRITE_LAMP).with(
                    BlockModelGenerators.createBooleanModelDispatch(
                        ModBlockStateProperties.CLICKED,
                        multivariant1,
                        multivariant
                    )
                )
            )
    }

    fun BlockModelGenerators.createChocolateCake() {
        this.registerSimpleFlatItemModel(ModBlocks.CHOCOLATE_CAKE.asItem()) // or your custom cake item if any

        this.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(ModBlocks.CHOCOLATE_CAKE)
                .with(
                    PropertyDispatch.initial(ModBlockStateProperties.BITES_16).run {
                        var dispatch = this.select(0, plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/chocolate_cake")))
                        for (bite in 1..16) {
                            dispatch = select(bite, plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/chocolate_cake_slice$bite")))
                        }
                        dispatch
                    }
                )
        )
    }

    fun BlockModelGenerators.createJapaneseCheeseCake() {
        registerSimpleFlatItemModel(ModBlocks.JAPANESE_CHEESECAKE.asItem())

        this.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(ModBlocks.JAPANESE_CHEESECAKE)
                .with(
                    PropertyDispatch.initial(ModBlockStateProperties.BITES_11).run {
                        var dispatch = this.select(0, plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/japanese_cheesecake")))
                        for (bite in 1..11) {
                            dispatch = select(bite, plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/japanese_cheesecake_slice$bite")))
                        }
                        dispatch
                    }
                )
        )
    }

    fun BlockModelGenerators.createShojiDoor() {
        val block = ModBlocks.SHOJI_DOOR

        registerSimpleFlatItemModel(ModBlocks.SHOJI_DOOR.asItem())

        // All variants use the same builtin/entity model
        val closedLeft = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))
        val closedRight = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))
        val openLeft = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))
        val openRight = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))

        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(
                    PropertyDispatch.initial(
                        BlockStateProperties.HORIZONTAL_FACING,
                        BlockStateProperties.OPEN,
                        BlockStateProperties.DOOR_HINGE
                    )
                        .select(Direction.NORTH, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.NORTH, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.NORTH, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.NORTH, true, DoorHingeSide.RIGHT, openRight)

                        .select(Direction.SOUTH, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.SOUTH, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.SOUTH, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.SOUTH, true, DoorHingeSide.RIGHT, openRight)

                        .select(Direction.EAST, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.EAST, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.EAST, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.EAST, true, DoorHingeSide.RIGHT, openRight)

                        .select(Direction.WEST, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.WEST, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.WEST, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.WEST, true, DoorHingeSide.RIGHT, openRight)
                )
        )
    }

    fun BlockModelGenerators.createFusumaDoor() {
        val block = ModBlocks.FUSUMA_DOOR

        registerSimpleFlatItemModel(ModBlocks.FUSUMA_DOOR.asItem())

        // All variants use the same builtin/entity model
        val closedLeft = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))
        val closedRight = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))
        val openLeft = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))
        val openRight = plainVariant(ResourceLocation.withDefaultNamespace("builtin/entity"))

        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(
                    PropertyDispatch.initial(
                        BlockStateProperties.HORIZONTAL_FACING,
                        BlockStateProperties.OPEN,
                        BlockStateProperties.DOOR_HINGE
                    )
                        .select(Direction.NORTH, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.NORTH, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.NORTH, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.NORTH, true, DoorHingeSide.RIGHT, openRight)

                        .select(Direction.SOUTH, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.SOUTH, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.SOUTH, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.SOUTH, true, DoorHingeSide.RIGHT, openRight)

                        .select(Direction.EAST, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.EAST, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.EAST, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.EAST, true, DoorHingeSide.RIGHT, openRight)

                        .select(Direction.WEST, false, DoorHingeSide.LEFT, closedLeft)
                        .select(Direction.WEST, false, DoorHingeSide.RIGHT, closedRight)
                        .select(Direction.WEST, true, DoorHingeSide.LEFT, openLeft)
                        .select(Direction.WEST, true, DoorHingeSide.RIGHT, openRight)
                )
        )
    }

    fun BlockModelGenerators.createPedestal() {
        val variant = plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/pedestal"))

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.PEDESTAL, variant))
    }

    fun BlockModelGenerators.createChabudai() {
        val variant = plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/chabudai"))

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.CHABUDAI, variant))
    }

    fun BlockModelGenerators.createTatamiMat() {
        val variant = plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/tatami_mat"))

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.TATAMI_MAT, variant))
    }

    fun ItemModelGenerators.createTatamiMat() {
        val model = ItemModelUtils.plainModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/tatami_mat"))
        this.itemModelOutput.accept(ModBlocks.TATAMI_MAT.asItem(), model)
    }

    fun rotationMutator(xRotation: Quadrant, yRotation: Quadrant): VariantMutator {
        return VariantMutator.X_ROT.withValue(xRotation)
            .then(VariantMutator.Y_ROT.withValue(yRotation))
    }

    fun BlockModelGenerators.createChair() {
        this.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(ModBlocks.CHAIR)
                .with(
                    PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING)
                        .select(Direction.NORTH, plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/chair")))
                        .select(Direction.SOUTH, plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/chair")).with(
                            rotationMutator(Quadrant.R0, Quadrant.R180)))
                        .select(Direction.EAST,  plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/chair")).with(
                            rotationMutator(Quadrant.R0, Quadrant.R90)
                        ))
                        .select(Direction.WEST,  plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/chair")).with(
                            rotationMutator(Quadrant.R0, Quadrant.R270)
                        ))
                )
        )
    }

    fun BlockModelGenerators.createZabuton(block: Block, name: String) {
        val variant = plainVariant(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/$name"))

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, variant))
    }

    fun BlockModelGenerators.createHellPortalBlock() {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(ModBlocks.HELL_PORTAL)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_AXIS)
                            .select(
                                Direction.Axis.X,
                                plainVariant(
                                    ModelLocationUtils.getModelLocation(
                                        ModBlocks.HELL_PORTAL,
                                        "_ns"
                                    )
                                )
                            )
                            .select(
                                Direction.Axis.Z,
                                plainVariant(
                                    ModelLocationUtils.getModelLocation(
                                        ModBlocks.HELL_PORTAL,
                                        "_ew"
                                    )
                                )
                            )
                    )
            )
    }

    fun ItemModelGenerators.createTalisman() {
        val base = ItemModelUtils.plainModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/talisman_base"))

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
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/talisman_${kanjiType.id}_overlay")
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

        this.itemModelOutput.accept(ModItems.TALISMAN_ITEM, composite)
    }

    fun ItemModelGenerators.createKatana(item: Item, hasTint: Boolean) {
        val modelLocation: ResourceLocation = ModelLocationUtils.getModelLocation(item)
        val baseTexture = TextureMapping.getItemTexture(item)

        val comboModels = mutableListOf<SelectItemModel.SwitchCase<Pair<BladeType, Wrapping>>>()

        for (blade in BladeType.entries) {
            for (wrap in Wrapping.entries) {
                val comboModelLocation = modelLocation.withSuffix("_${blade.id}_${wrap.id}")

                // Use the overlays provided by BladeType and Wrapping
                val bladeTexture = blade.overlay
                val wrapTexture = wrap.overlay

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
            ModelTemplates.TWO_LAYERED_ITEM.create(modelLocation, TextureMapping.layered(baseTexture, ResourceLocation.fromNamespaceAndPath(MOD_ID, "empty")), this.modelOutput)
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

    fun ItemModelGenerators.createChisel() {
        val baseModel = ItemModelUtils.plainModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/chisel"))
        val usedModel = ItemModelUtils.plainModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/chisel_used"))

        val property = CustomModelDataProperty(0)

        val overrideEntry = ItemModelUtils.override(usedModel, 1.0f)

        val model = ItemModelUtils.rangeSelect(property, baseModel, overrideEntry)

        this.itemModelOutput.accept(ModItems.CHISEL, model)
    }

    fun ItemModelGenerators.createRadiationStaff() {
        val model2d = ItemModelUtils.plainModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/radiation_staff_2d"))
        val model3d = ItemModelUtils.plainModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/radiation_staff_3d"))

        val property = DisplayContext()

        val case2d = ItemModelUtils.`when`(listOf(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED), model2d)

        val model = ItemModelUtils.select(property, model3d, case2d)

        this.itemModelOutput.accept(ModItems.RADIATION_STAFF, model)
    }

    fun ItemModelGenerators.createIronBattleAxe() {
        val model = ItemModelUtils.plainModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/iron_battle_axe"))
        this.itemModelOutput.accept(ModItems.IRON_BATTLE_AXE, model)
    }
    
    fun ItemModelGenerators.generateTrimmableItemE(p_376312_: Item, p_375739_: ResourceKey<EquipmentAsset?>, p_396254_: ResourceLocation, p_377962_: Boolean) {
        val resourcelocation: ResourceLocation = ModelLocationUtils.getModelLocation(p_376312_)
        val resourcelocation1: ResourceLocation = TextureMapping.getItemTexture(p_376312_)
        val resourcelocation2: ResourceLocation = TextureMapping.getItemTexture(p_376312_, "_overlay")
        val list: MutableList<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>?> =
            ArrayList(
                TRIM_MATERIAL_MODELS.size
            )

        for (`itemmodelgenerators$trimmaterialdata` in TRIM_MATERIAL_MODELS) {
            val resourcelocation3 = resourcelocation.withSuffix(
                "_" + `itemmodelgenerators$trimmaterialdata`.assets!!.base().suffix() + "_trim"
            )
            val resourcelocation4 = p_396254_.withSuffix(
                "_" + `itemmodelgenerators$trimmaterialdata`.assets.assetId(p_375739_).suffix()
            )
            val `itemmodel$unbaked`: ItemModel.Unbaked?
            if (p_377962_) {
                this.generateLayeredItem(resourcelocation3, resourcelocation1, resourcelocation2, resourcelocation4)
                `itemmodel$unbaked` = ItemModelUtils.tintedModel(resourcelocation3, Dye(-6265536))
            } else {
                this.generateLayeredItem(resourcelocation3, resourcelocation1, resourcelocation4)
                `itemmodel$unbaked` = ItemModelUtils.plainModel(resourcelocation3)
            }

            list.add(
                ItemModelUtils.`when`(
                    `itemmodelgenerators$trimmaterialdata`.materialKey,
                    `itemmodel$unbaked`
                )
            )
        }

        val `itemmodel$unbaked1`: ItemModel.Unbaked?
        if (p_377962_) {
            ModelTemplates.TWO_LAYERED_ITEM.create(
                resourcelocation,
                TextureMapping.layered(resourcelocation1, resourcelocation2),
                this.modelOutput
            )
            `itemmodel$unbaked1` = ItemModelUtils.tintedModel(resourcelocation, Dye(-6265536))
        } else {
            ModelTemplates.FLAT_ITEM.create(
                resourcelocation,
                TextureMapping.layer0(resourcelocation1),
                this.modelOutput
            )
            `itemmodel$unbaked1` = ItemModelUtils.plainModel(resourcelocation)
        }

        this.itemModelOutput.accept(
            p_376312_,
            ItemModelUtils.select(TrimMaterialProperty(), `itemmodel$unbaked1`, list)
        )
    }

    private class MyItemModelGenerators(
        pItemModelOutput: ItemModelOutput,
        pModelOutput: BiConsumer<ResourceLocation?, ModelInstance?>
    ) : ItemModelGenerators(pItemModelOutput, pModelOutput) {
        data class MyTrimMaterialData(val assets: MaterialAssetGroup?, val materialKey: ResourceKey<TrimMaterial>)

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
    }
}
