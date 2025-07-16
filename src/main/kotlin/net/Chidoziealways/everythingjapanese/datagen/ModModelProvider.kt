package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.item.ModEquipmentAssets
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.state.properties.ModBlockStateProperties
import net.Chidoziealways.everythingjapanese.trim.ModMaterialAssetGroup
import net.Chidoziealways.everythingjapanese.trim.ModTrimMaterials
import net.minecraft.client.color.item.Dye
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.ItemModelOutput
import net.minecraft.client.data.models.ModelProvider
import net.minecraft.client.data.models.MultiVariant
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator
import net.minecraft.client.data.models.blockstates.PropertyDispatch
import net.minecraft.client.data.models.model.ItemModelUtils
import net.minecraft.client.data.models.model.ModelInstance
import net.minecraft.client.data.models.model.ModelLocationUtils
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.client.data.models.model.TextureMapping
import net.minecraft.client.data.models.model.TexturedModel
import net.minecraft.client.renderer.item.ItemModel
import net.minecraft.client.renderer.item.SelectItemModel
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty
import net.minecraft.core.Direction
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup
import net.minecraft.world.item.equipment.trim.TrimMaterial
import net.minecraft.world.item.equipment.trim.TrimMaterials
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import java.util.List
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Function
import kotlin.collections.ArrayList
import kotlin.collections.MutableList

@OnlyIn(Dist.CLIENT)
class ModModelProvider(output: PackOutput) : ModelProvider(output) {
    private class MyBlockModelGenerators(
        pBlockStateOutput: Consumer<BlockModelDefinitionGenerator?>,
        pItemModelOutput: ItemModelOutput,
        pModelOutput: BiConsumer<ResourceLocation?, ModelInstance?>
    ) : BlockModelGenerators(pBlockStateOutput, pItemModelOutput, pModelOutput) {
        override fun run() {
            super.run()
            this.createTrivialCube(ModBlocks.TRANSFORMER_BLOCK.get())
            this.createTrivialCube(ModBlocks.RAW_PYRITE_BLOCK.get())
            this.createTrivialCube(ModBlocks.PYRITE_DEEPSLATE_ORE.get())
            this.createTrivialCube(ModBlocks.PYRITE_ORE.get())
            this.createTrivialCube(ModBlocks.NEPHRITE_BLOCK.get())
            this.createTrivialCube(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get())
            this.createTrivialCube(ModBlocks.NEPHRITE_ORE.get())
            this.createTrivialCube(ModBlocks.HINOKI_BAN.get())
            this.family(ModBlocks.PYRITE_BLOCK.get())
                .stairs(ModBlocks.PYRITE_STAIRS.get())
                .slab(ModBlocks.PYRITE_SLAB.get())
                .button(ModBlocks.PYRITE_BUTTON.get())
                .pressurePlate(ModBlocks.PYRITE_PRESSURE_PLATE.get())
                .fence(ModBlocks.PYRITE_FENCE.get())
                .fenceGate(ModBlocks.PYRITE_FENCE_GATE.get())
                .wall(ModBlocks.PYRITE_WALL.get())
            this.woodProvider(ModBlocks.HINOKI_MARUTA.get())
                .logWithHorizontal(ModBlocks.HINOKI_MARUTA.get())
                .wood(ModBlocks.HINOKI_MOKUZAI.get())
            this.woodProvider(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
                .logWithHorizontal(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
                .wood(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get())
            this.createDoor(ModBlocks.PYRITE_DOOR.get())
            this.createTrapdoor(ModBlocks.PYRITE_TRAPDOOR.get())
            this.createCropBlock(ModBlocks.RICE_CROP.get(), ModBlockStateProperties.AGE_4, 0, 1, 2, 3, 4)
            this.createYamazakiBerryBush()
            this.createLamp()
            this.createTintedLeaves(ModBlocks.HINOKI_HA.get(), TexturedModel.LEAVES, -12012264)
            this.createPlantWithDefaultItem(
                ModBlocks.HINOKI_NAEGI.get(),
                ModBlocks.POTTED_HINOKI_NAEGI.get(),
                BlockModelGenerators.PlantType.NOT_TINTED
            )
            this.createTrivialCube(ModBlocks.GROWTH_CHAMBER.get())
            this.createHellPortalBlock()
        }

        fun createYamazakiBerryBush() {
            this.registerSimpleFlatItemModel(ModItems.YAMAZAKI_BERRIES!!.get())
            this.blockStateOutput
                .accept(
                    MultiVariantGenerator.dispatch(ModBlocks.YAMAZAKI_BERRY_BUSH.get())
                        .with(
                            PropertyDispatch.initial<Int?>(BlockStateProperties.AGE_3)
                                .generate(
                                    Function { p_389159_: Int? ->
                                        BlockModelGenerators.plainVariant(
                                            this.createSuffixedVariant(
                                                ModBlocks.YAMAZAKI_BERRY_BUSH.get(),
                                                "_stage$p_389159_",
                                                ModelTemplates.CROSS,
                                                Function { p_378193_: ResourceLocation? ->
                                                    TextureMapping.cross(p_378193_)
                                                })
                                        )
                                    }
                                )
                        )
                )
        }

        fun createLamp() {
            val multivariant: MultiVariant = BlockModelGenerators.plainVariant(
                TexturedModel.CUBE.create(
                    ModBlocks.PYRITE_LAMP.get(),
                    this.modelOutput
                )
            )
            val multivariant1: MultiVariant = BlockModelGenerators.plainVariant(
                this.createSuffixedVariant(
                    ModBlocks.PYRITE_LAMP.get(),
                    "_on",
                    ModelTemplates.CUBE_ALL,
                    Function { p_377582_: ResourceLocation? -> TextureMapping.cube(p_377582_) })
            )
            this.blockStateOutput
                .accept(
                    MultiVariantGenerator.dispatch(ModBlocks.PYRITE_LAMP.get()).with(
                        BlockModelGenerators.createBooleanModelDispatch(
                            ModBlockStateProperties.CLICKED,
                            multivariant1,
                            multivariant
                        )
                    )
                )
        }

        fun createHellPortalBlock() {
            this.blockStateOutput
                .accept(
                    MultiVariantGenerator.dispatch(ModBlocks.HELL_PORTAL.get())
                        .with(
                            PropertyDispatch.initial<Direction.Axis?>(BlockStateProperties.HORIZONTAL_AXIS)
                                .select(
                                    Direction.Axis.X,
                                    BlockModelGenerators.plainVariant(
                                        ModelLocationUtils.getModelLocation(
                                            ModBlocks.HELL_PORTAL.get(),
                                            "_ns"
                                        )
                                    )
                                )
                                .select(
                                    Direction.Axis.Z,
                                    BlockModelGenerators.plainVariant(
                                        ModelLocationUtils.getModelLocation(
                                            ModBlocks.HELL_PORTAL.get(),
                                            "_ew"
                                        )
                                    )
                                )
                        )
                )
        }

        companion object {
            val NON_ORIENTABLE_TRAPDOOR: MutableList<Block?> =
                List.of<Block?>(Blocks.OAK_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR)
        }
    }

    override fun getBlockModelGenerators(
        blocks: BlockStateGeneratorCollector,
        items: ItemInfoCollector,
        models: SimpleModelCollector
    ): BlockModelGenerators {
        return MyBlockModelGenerators(blocks, items, models)
    }

    private class MyItemModelGenerators(
        pItemModelOutput: ItemModelOutput,
        pModelOutput: BiConsumer<ResourceLocation?, ModelInstance?>
    ) : ItemModelGenerators(pItemModelOutput, pModelOutput) {
        override fun run() {
            super.run()
            this.generateFlatItem(ModItems.RAW_PYRITE!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.PYRITE_INGOT!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.DIESEL!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.GREEN_TEA!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.SUSHI!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.INCENSE!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.UDON!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.WOODEN_KATANA!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.YA!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.AO_TO_NATSU_MUSIC_DISC!!.get(), ModelTemplates.MUSIC_DISC)
            this.generateFlatItem(ModItems.RAW_RICE!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.RICE!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.SMALL_FIREBALL_SCROLL!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.LARGE_FIREBALL_SCROLL!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.WINDBALL_SCROLL!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.HELL_PORTAL_ACTIVATOR!!.get(), ModelTemplates.FLAT_HANDHELD_ROD_ITEM)
            this.generateBow(ModItems.DAIKYU!!.get())
            this.generateFlatItem(ModItems.NEPHRITE!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.PYRITE_SWORD!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.PYRITE_PICKAXE!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.PYRITE_AXE!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.PYRITE_HOE!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.PYRITE_SHOVEL!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.PYRITE_BATTLE_AXE!!.get(), ModelTemplates.FLAT_HANDHELD_MACE_ITEM)
            this.generateFlatItem(ModItems.NEPHRITE_SWORD!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.NEPHRITE_PICKAXE!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.NEPHRITE_AXE!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.NEPHRITE_SHOVEL!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.NEPHRITE_HOE!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateFlatItem(ModItems.PYRITE_HAMMER!!.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
            this.generateTrimmableItem(
                ModItems.PYRITE_HELMET!!.get(),
                ModEquipmentAssets.Companion.PYRITE,
                ItemModelGenerators.TRIM_PREFIX_HELMET,
                false
            )
            this.generateTrimmableItem(
                ModItems.PYRITE_CHESTPLATE!!.get(),
                ModEquipmentAssets.Companion.PYRITE,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                false
            )
            this.generateTrimmableItem(
                ModItems.PYRITE_LEGGINGS!!.get(),
                ModEquipmentAssets.Companion.PYRITE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                false
            )
            this.generateTrimmableItem(
                ModItems.PYRITE_BOOTS!!.get(),
                ModEquipmentAssets.Companion.PYRITE,
                ItemModelGenerators.TRIM_PREFIX_BOOTS,
                false
            )
            this.generateTrimmableItem(
                ModItems.NEPHRITE_HELMET!!.get(),
                ModEquipmentAssets.Companion.NEPHRITE,
                ItemModelGenerators.TRIM_PREFIX_HELMET,
                false
            )
            this.generateTrimmableItem(
                ModItems.NEPHRITE_CHESTPLATE!!.get(),
                ModEquipmentAssets.Companion.NEPHRITE,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                false
            )
            this.generateTrimmableItem(
                ModItems.NEPHRITE_LEGGINGS!!.get(),
                ModEquipmentAssets.Companion.NEPHRITE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                false
            )
            this.generateTrimmableItem(
                ModItems.NEPHRITE_BOOTS!!.get(),
                ModEquipmentAssets.Companion.NEPHRITE,
                ItemModelGenerators.TRIM_PREFIX_BOOTS,
                false
            )
            this.generateTrimmableItem(
                ModItems.SAMURAI_HELMET!!.get(),
                ModEquipmentAssets.Companion.SAMURAI,
                ItemModelGenerators.TRIM_PREFIX_HELMET,
                false
            )
            this.generateTrimmableItem(
                ModItems.SAMURAI_CHESTPLATE!!.get(),
                ModEquipmentAssets.Companion.SAMURAI,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                false
            )
            this.generateTrimmableItem(
                ModItems.SAMURAI_LEGGINGS!!.get(),
                ModEquipmentAssets.Companion.SAMURAI,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                false
            )
            this.generateTrimmableItem(
                ModItems.SAMURAI_BOOTS!!.get(),
                ModEquipmentAssets.Companion.SAMURAI,
                ItemModelGenerators.TRIM_PREFIX_BOOTS,
                false
            )
            this.generateFlatItem(ModItems.PYRITE_HORSE_ARMOR!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.SIKA_DEER_SPAWN_EGG!!.get(), ModelTemplates.FLAT_ITEM)
            this.generateFlatItem(ModItems.TRICERATOPS_SPAWN_EGG!!.get(), ModelTemplates.FLAT_ITEM)
        }

        override fun generateTrimmableItem(
            p_376312_: Item,
            p_375739_: ResourceKey<EquipmentAsset?>,
            p_396254_: ResourceLocation,
            p_377962_: Boolean
        ) {
            val resourcelocation: ResourceLocation = ModelLocationUtils.getModelLocation(p_376312_)
            val resourcelocation1: ResourceLocation = TextureMapping.getItemTexture(p_376312_)
            val resourcelocation2: ResourceLocation = TextureMapping.getItemTexture(p_376312_, "_overlay")
            val list: MutableList<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial?>?>?> =
                ArrayList<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial?>?>?>(
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
                    ItemModelUtils.`when`<ResourceKey<TrimMaterial?>?>(
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
                ItemModelUtils.select<ResourceKey<TrimMaterial?>?>(TrimMaterialProperty(), `itemmodel$unbaked1`, list)
            )
        }

        @OnlyIn(Dist.CLIENT)
        class MyTrimMaterialData(assets: MaterialAssetGroup?, materialKey: ResourceKey<TrimMaterial?>?) {
            val assets: MaterialAssetGroup?
            val materialKey: ResourceKey<TrimMaterial?>?

            init {
                this.assets = assets
                this.materialKey = materialKey
            }
        }

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

    override fun getItemModelGenerators(items: ItemInfoCollector, models: SimpleModelCollector): ItemModelGenerators {
        return MyItemModelGenerators(items, models)
    }
}
