package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.state.properties.ModBlockStateProperties;
import net.Chidoziealways.everythingjapanese.item.ModEquipmentAssets;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.trim.ModMaterialAssetGroup;
import net.Chidoziealways.everythingjapanese.trim.ModTrimMaterials;
import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.*;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output);
    }

    private class MyBlockModelGenerators extends BlockModelGenerators{

        static final List<Block> NON_ORIENTABLE_TRAPDOOR = List.of(Blocks.OAK_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR);

        public MyBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> pBlockStateOutput, ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput) {
            super(pBlockStateOutput, pItemModelOutput, pModelOutput);
        }

        public BlockFamilyProvider family(RegistryObject<Block> block) {
            return family(block.get());
        }

        private void door(Block pDoorBlock) {
            TextureMapping texturemapping = TextureMapping.door(pDoorBlock);
            MultiVariant multivariant = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.create(pDoorBlock, texturemapping, this.modelOutput));
            MultiVariant multivariant1 = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
            MultiVariant multivariant2 = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.create(pDoorBlock, texturemapping, this.modelOutput));
            MultiVariant multivariant3 = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
            MultiVariant multivariant4 = plainVariant(ModelTemplates.DOOR_TOP_LEFT.create(pDoorBlock, texturemapping, this.modelOutput));
            MultiVariant multivariant5 = plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
            MultiVariant multivariant6 = plainVariant(ModelTemplates.DOOR_TOP_RIGHT.create(pDoorBlock, texturemapping, this.modelOutput));
            MultiVariant multivariant7 = plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
            this.registerSimpleFlatItemModel(pDoorBlock.asItem());
            this.blockStateOutput
                    .accept(createDoor(pDoorBlock, multivariant, multivariant1, multivariant2, multivariant3, multivariant4, multivariant5, multivariant6, multivariant7));
        }

        public void trapDoor(Block pTrapdoorBlock) {
            if (NON_ORIENTABLE_TRAPDOOR.contains(pTrapdoorBlock)) {
                createTrapdoor(pTrapdoorBlock);
            } else {
                createOrientableTrapdoor(pTrapdoorBlock);
            }
        }

        protected void createOrientableTrapdoor(Block pOrientableTrapdoorBlock) {
            TextureMapping texturemapping = TextureMapping.defaultTexture(pOrientableTrapdoorBlock);
            MultiVariant multivariant = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create(pOrientableTrapdoorBlock, texturemapping, this.modelOutput));
            ResourceLocation resourcelocation = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create(pOrientableTrapdoorBlock, texturemapping, this.modelOutput);
            MultiVariant multivariant1 = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create(pOrientableTrapdoorBlock, texturemapping, this.modelOutput));
            this.blockStateOutput.accept(createOrientableTrapdoor(pOrientableTrapdoorBlock, multivariant, plainVariant(resourcelocation), multivariant1));
            this.registerSimpleItemModel(pOrientableTrapdoorBlock, resourcelocation);
        }

        protected void createTrapdoor(Block pTrapdoorBlock) {
            TextureMapping texturemapping = TextureMapping.defaultTexture(pTrapdoorBlock);
            MultiVariant multivariant = plainVariant(ModelTemplates.TRAPDOOR_TOP.create(pTrapdoorBlock, texturemapping, this.modelOutput));
            ResourceLocation resourcelocation = ModelTemplates.TRAPDOOR_BOTTOM.create(pTrapdoorBlock, texturemapping, this.modelOutput);
            MultiVariant multivariant1 = plainVariant(ModelTemplates.TRAPDOOR_OPEN.create(pTrapdoorBlock, texturemapping, this.modelOutput));
            this.blockStateOutput.accept(createTrapdoor(pTrapdoorBlock, multivariant, plainVariant(resourcelocation), multivariant1));
            this.registerSimpleItemModel(pTrapdoorBlock, resourcelocation);
        }

        @Override
        protected void createCropBlock(Block pCropBlock, Property<Integer> pAgeProperty, int... pAgeToVisualStageMapping) {
            super.createCropBlock(pCropBlock, pAgeProperty, pAgeToVisualStageMapping);
        }

        public void createYamazakiBerryBush() {
            this.registerSimpleFlatItemModel(ModItems.YAMAZAKI_BERRIES.get());
            this.blockStateOutput
                    .accept(
                            MultiVariantGenerator.dispatch(ModBlocks.YAMAZAKI_BERRY_BUSH.get())
                                    .with(
                                            PropertyDispatch.initial(BlockStateProperties.AGE_3)
                                                    .generate(
                                                            p_389159_ -> plainVariant(
                                                                    this.createSuffixedVariant(ModBlocks.YAMAZAKI_BERRY_BUSH.get(), "_stage" + p_389159_, ModelTemplates.CROSS, TextureMapping::cross)
                                                            )
                                                    )
                                    )
                    );
        }

        public void createLamp(){
            MultiVariant multivariant = plainVariant(TexturedModel.CUBE.create(ModBlocks.PYRITE_LAMP.get(), this.modelOutput));
            MultiVariant multivariant1 = plainVariant(this.createSuffixedVariant(ModBlocks.PYRITE_LAMP.get(), "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
            this.blockStateOutput
                    .accept(MultiVariantGenerator.dispatch(ModBlocks.PYRITE_LAMP.get()).with(createBooleanModelDispatch(ModBlockStateProperties.CLICKED, multivariant1, multivariant)));
        }

        public WoodProvider wood(RegistryObject<RotatedPillarBlock> wood) {
            return woodProvider(wood.get());
        }

        public void createSapling(Block pBlock, Block pPottedBlock, PlantType pPlantType) {
            createPlantWithDefaultItem(pBlock, pPottedBlock, pPlantType);
        }

    }

    @Override
    protected BlockModelGenerators getBlockModelGenerators(BlockStateGeneratorCollector blocks, ItemInfoCollector items, SimpleModelCollector models) {
        MyBlockModelGenerators generators = new MyBlockModelGenerators(blocks, items, models);

        generators.createTrivialCube(ModBlocks.TRANSFORMER_BLOCK.get());
        generators.createTrivialCube(ModBlocks.RAW_PYRITE_BLOCK.get());
        generators.createTrivialCube(ModBlocks.PYRITE_DEEPSLATE_ORE.get());
        generators.createTrivialCube(ModBlocks.PYRITE_ORE.get());
        generators.createTrivialCube(ModBlocks.NEPHRITE_BLOCK.get());
        generators.createTrivialCube(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get());
        generators.createTrivialCube(ModBlocks.NEPHRITE_ORE.get());
        generators.createTrivialCube(ModBlocks.HINOKI_BAN.get());
        generators.family(ModBlocks.PYRITE_BLOCK)
                .stairs(ModBlocks.PYRITE_STAIRS.get())
                .slab(ModBlocks.PYRITE_SLAB.get())
                .button(ModBlocks.PYRITE_BUTTON.get())
                .pressurePlate(ModBlocks.PYRITE_PRESSURE_PLATE.get())
                .fence(ModBlocks.PYRITE_FENCE.get())
                .fenceGate(ModBlocks.PYRITE_FENCE_GATE.get())
                .wall(ModBlocks.PYRITE_WALL.get());
        generators.wood(ModBlocks.HINOKI_MARUTA)
                .logWithHorizontal(ModBlocks.HINOKI_MARUTA.get())
                .wood(ModBlocks.HINOKI_MOKUZAI.get());
        generators.wood(ModBlocks.STRIPPED_HINOKI_MARUTA)
                .logWithHorizontal(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
                .wood(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get());
        generators.door(ModBlocks.PYRITE_DOOR.get());
        generators.trapDoor(ModBlocks.PYRITE_TRAPDOOR.get());
        generators.createCropBlock(ModBlocks.RICE_CROP.get(), ModBlockStateProperties.AGE_4, 0, 1, 2, 3, 4);
        generators.createYamazakiBerryBush();
        generators.createLamp();
        generators.createTintedLeaves(ModBlocks.HINOKI_HA.get(), TexturedModel.LEAVES, -12012264);
        generators.createSapling(ModBlocks.HINOKI_NAEGI.get(), ModBlocks.POTTED_HINOKI_NAEGI.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createTrivialCube(ModBlocks.GROWTH_CHAMBER.get());

        return generators;
    }

    private class MyItemModelGenerators extends ItemModelGenerators {
        public MyItemModelGenerators(ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput) {
            super(pItemModelOutput, pModelOutput);
        }

        public void generateFlatItem(RegistryObject<Item> item, ModelTemplate template) {
            generateFlatItem(item.get(), template);
        }

        public void generateBow(Item pBowItem) {
            ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(this.createFlatItemModel(pBowItem, ModelTemplates.BOW));
            ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.plainModel(this.createFlatItemModel(pBowItem, "_pulling_0", ModelTemplates.BOW));
            ItemModel.Unbaked itemmodel$unbaked2 = ItemModelUtils.plainModel(this.createFlatItemModel(pBowItem, "_pulling_1", ModelTemplates.BOW));
            ItemModel.Unbaked itemmodel$unbaked3 = ItemModelUtils.plainModel(this.createFlatItemModel(pBowItem, "_pulling_2", ModelTemplates.BOW));
            this.itemModelOutput
                    .accept(
                            pBowItem,
                            ItemModelUtils.conditional(
                                    ItemModelUtils.isUsingItem(),
                                    ItemModelUtils.rangeSelect(
                                            new UseDuration(false),
                                            0.05F,
                                            itemmodel$unbaked1,
                                            ItemModelUtils.override(itemmodel$unbaked2, 0.65F),
                                            ItemModelUtils.override(itemmodel$unbaked3, 0.9F)
                                    ),
                                    itemmodel$unbaked
                            )
                    );
        }

        public static final List<MyItemModelGenerators.MyTrimMaterialData> TRIM_MATERIAL_MODELS = List.of(
                new MyTrimMaterialData(ModMaterialAssetGroup.PYRITE, ModTrimMaterials.PYRITE),
                new MyTrimMaterialData(MaterialAssetGroup.QUARTZ, TrimMaterials.QUARTZ),
                new MyTrimMaterialData(MaterialAssetGroup.IRON, TrimMaterials.IRON),
                new MyTrimMaterialData(MaterialAssetGroup.NETHERITE, TrimMaterials.NETHERITE),
                new MyTrimMaterialData(MaterialAssetGroup.REDSTONE, TrimMaterials.REDSTONE),
                new MyTrimMaterialData(MaterialAssetGroup.COPPER, TrimMaterials.COPPER),
                new MyTrimMaterialData(MaterialAssetGroup.GOLD, TrimMaterials.GOLD),
                new MyTrimMaterialData(MaterialAssetGroup.EMERALD, TrimMaterials.EMERALD),
                new MyTrimMaterialData(MaterialAssetGroup.DIAMOND, TrimMaterials.DIAMOND),
                new MyTrimMaterialData(MaterialAssetGroup.LAPIS, TrimMaterials.LAPIS),
                new MyTrimMaterialData(MaterialAssetGroup.AMETHYST, TrimMaterials.AMETHYST),
                new MyTrimMaterialData(MaterialAssetGroup.RESIN, TrimMaterials.RESIN)
        );

        @Override
        protected void generateTrimmableItem(Item p_376312_, ResourceKey<EquipmentAsset> p_375739_, ResourceLocation p_396254_, boolean p_377962_) {
            ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(p_376312_);
            ResourceLocation resourcelocation1 = TextureMapping.getItemTexture(p_376312_);
            ResourceLocation resourcelocation2 = TextureMapping.getItemTexture(p_376312_, "_overlay");
            List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> list = new ArrayList<>(TRIM_MATERIAL_MODELS.size());

            for (MyItemModelGenerators.MyTrimMaterialData itemmodelgenerators$trimmaterialdata : TRIM_MATERIAL_MODELS) {
                ResourceLocation resourcelocation3 = resourcelocation.withSuffix(
                        "_" + itemmodelgenerators$trimmaterialdata.assets().base().suffix() + "_trim"
                );
                ResourceLocation resourcelocation4 = p_396254_.withSuffix("_" + itemmodelgenerators$trimmaterialdata.assets().assetId(p_375739_).suffix());
                ItemModel.Unbaked itemmodel$unbaked;
                if (p_377962_) {
                    this.generateLayeredItem(resourcelocation3, resourcelocation1, resourcelocation2, resourcelocation4);
                    itemmodel$unbaked = ItemModelUtils.tintedModel(resourcelocation3, new Dye(-6265536));
                } else {
                    this.generateLayeredItem(resourcelocation3, resourcelocation1, resourcelocation4);
                    itemmodel$unbaked = ItemModelUtils.plainModel(resourcelocation3);
                }

                list.add(ItemModelUtils.when(itemmodelgenerators$trimmaterialdata.materialKey, itemmodel$unbaked));
            }

            ItemModel.Unbaked itemmodel$unbaked1;
            if (p_377962_) {
                ModelTemplates.TWO_LAYERED_ITEM.create(resourcelocation, TextureMapping.layered(resourcelocation1, resourcelocation2), this.modelOutput);
                itemmodel$unbaked1 = ItemModelUtils.tintedModel(resourcelocation, new Dye(-6265536));
            } else {
                ModelTemplates.FLAT_ITEM.create(resourcelocation, TextureMapping.layer0(resourcelocation1), this.modelOutput);
                itemmodel$unbaked1 = ItemModelUtils.plainModel(resourcelocation);
            }

            this.itemModelOutput.accept(p_376312_, ItemModelUtils.select(new TrimMaterialProperty(), itemmodel$unbaked1, list));
        }

        @OnlyIn(Dist.CLIENT)
        public record MyTrimMaterialData(MaterialAssetGroup assets, ResourceKey<TrimMaterial> materialKey) {
        }
    }

    @Override
    protected ItemModelGenerators getItemModelGenerators(ItemInfoCollector items, SimpleModelCollector models) {
        MyItemModelGenerators generators = new MyItemModelGenerators(items, models);
        generators.generateFlatItem(ModItems.RAW_PYRITE, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_INGOT, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.DIESEL, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.GREEN_TEA, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.SUSHI, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.INCENSE, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.UDON, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.WOODEN_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.YA, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.AO_TO_NATSU_MUSIC_DISC, ModelTemplates.MUSIC_DISC);
        generators.generateFlatItem(ModItems.RAW_RICE, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.RICE, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.SMALL_FIREBALL_SCROLL, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.LARGE_FIREBALL_SCROLL, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.WINDBALL_SCROLL, ModelTemplates.FLAT_ITEM);
        generators.generateBow(ModItems.DAIKYU.get());
        generators.generateFlatItem(ModItems.NEPHRITE, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_BATTLE_AXE, ModelTemplates.FLAT_HANDHELD_MACE_ITEM);
        generators.generateFlatItem(ModItems.NEPHRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.NEPHRITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.NEPHRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.NEPHRITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.NEPHRITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateFlatItem(ModItems.PYRITE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        generators.generateTrimmableItem(ModItems.PYRITE_HELMET.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generators.generateTrimmableItem(ModItems.PYRITE_CHESTPLATE.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generators.generateTrimmableItem(ModItems.PYRITE_LEGGINGS.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generators.generateTrimmableItem(ModItems.PYRITE_BOOTS.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        generators.generateTrimmableItem(ModItems.NEPHRITE_HELMET.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generators.generateTrimmableItem(ModItems.NEPHRITE_CHESTPLATE.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generators.generateTrimmableItem(ModItems.NEPHRITE_LEGGINGS.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generators.generateTrimmableItem(ModItems.NEPHRITE_BOOTS.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        generators.generateTrimmableItem(ModItems.SAMURAI_HELMET.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        generators.generateTrimmableItem(ModItems.SAMURAI_CHESTPLATE.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        generators.generateTrimmableItem(ModItems.SAMURAI_LEGGINGS.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        generators.generateTrimmableItem(ModItems.SAMURAI_BOOTS.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        generators.generateFlatItem(ModItems.PYRITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.SIKA_DEER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(ModItems.TRICERATOPS_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        return generators;
    }
}
