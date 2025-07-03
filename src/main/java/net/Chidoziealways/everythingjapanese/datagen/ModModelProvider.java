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
import net.minecraft.core.Direction;
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
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output);
    }

    private static class MyBlockModelGenerators extends BlockModelGenerators{

        static final List<Block> NON_ORIENTABLE_TRAPDOOR = List.of(Blocks.OAK_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR);

        public MyBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> pBlockStateOutput, ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput) {
            super(pBlockStateOutput, pItemModelOutput, pModelOutput);
        }

        @Override
        public void run() {
            super.run();
            this.createTrivialCube(ModBlocks.TRANSFORMER_BLOCK.get());
            this.createTrivialCube(ModBlocks.RAW_PYRITE_BLOCK.get());
            this.createTrivialCube(ModBlocks.PYRITE_DEEPSLATE_ORE.get());
            this.createTrivialCube(ModBlocks.PYRITE_ORE.get());
            this.createTrivialCube(ModBlocks.NEPHRITE_BLOCK.get());
            this.createTrivialCube(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get());
            this.createTrivialCube(ModBlocks.NEPHRITE_ORE.get());
            this.createTrivialCube(ModBlocks.HINOKI_BAN.get());
            this.family(ModBlocks.PYRITE_BLOCK.get())
                    .stairs(ModBlocks.PYRITE_STAIRS.get())
                    .slab(ModBlocks.PYRITE_SLAB.get())
                    .button(ModBlocks.PYRITE_BUTTON.get())
                    .pressurePlate(ModBlocks.PYRITE_PRESSURE_PLATE.get())
                    .fence(ModBlocks.PYRITE_FENCE.get())
                    .fenceGate(ModBlocks.PYRITE_FENCE_GATE.get())
                    .wall(ModBlocks.PYRITE_WALL.get());
            this.woodProvider(ModBlocks.HINOKI_MARUTA.get())
                    .logWithHorizontal(ModBlocks.HINOKI_MARUTA.get())
                    .wood(ModBlocks.HINOKI_MOKUZAI.get());
            this.woodProvider(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
                    .logWithHorizontal(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
                    .wood(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get());
            this.createDoor(ModBlocks.PYRITE_DOOR.get());
            this.createTrapdoor(ModBlocks.PYRITE_TRAPDOOR.get());
            this.createCropBlock(ModBlocks.RICE_CROP.get(), ModBlockStateProperties.AGE_4, 0, 1, 2, 3, 4);
            this.createYamazakiBerryBush();
            this.createLamp();
            this.createTintedLeaves(ModBlocks.HINOKI_HA.get(), TexturedModel.LEAVES, -12012264);
            this.createPlantWithDefaultItem(ModBlocks.HINOKI_NAEGI.get(), ModBlocks.POTTED_HINOKI_NAEGI.get(), BlockModelGenerators.PlantType.NOT_TINTED);
            this.createTrivialCube(ModBlocks.GROWTH_CHAMBER.get());
            this.createHellPortalBlock();
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

        public void createHellPortalBlock() {
            this.blockStateOutput
                    .accept(
                            MultiVariantGenerator.dispatch(ModBlocks.HELL_PORTAL.get())
                                    .with(
                                            PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_AXIS)
                                                    .select(Direction.Axis.X, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HELL_PORTAL.get(), "_ns")))
                                                    .select(Direction.Axis.Z, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HELL_PORTAL.get(), "_ew")))
                                    )
                    );
        }

    }

    @Override
    protected @NotNull BlockModelGenerators getBlockModelGenerators(BlockStateGeneratorCollector blocks, ItemInfoCollector items, SimpleModelCollector models) {
        return new MyBlockModelGenerators(blocks, items, models);
    }

    private static class MyItemModelGenerators extends ItemModelGenerators {

        public MyItemModelGenerators(ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput) {
            super(pItemModelOutput, pModelOutput);
        }

        @Override
        public void run() {
            super.run();
            this.generateFlatItem(ModItems.RAW_PYRITE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.PYRITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.DIESEL.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.GREEN_TEA.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.SUSHI.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.INCENSE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.UDON.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.WOODEN_KATANA.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.YA.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.AO_TO_NATSU_MUSIC_DISC.get(), ModelTemplates.MUSIC_DISC);
            this.generateFlatItem(ModItems.RAW_RICE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.RICE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.SMALL_FIREBALL_SCROLL.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.LARGE_FIREBALL_SCROLL.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.WINDBALL_SCROLL.get(), ModelTemplates.FLAT_ITEM);
            this.generateBow(ModItems.DAIKYU.get());
            this.generateFlatItem(ModItems.NEPHRITE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.PYRITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.PYRITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.PYRITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.PYRITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.PYRITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.PYRITE_BATTLE_AXE.get(), ModelTemplates.FLAT_HANDHELD_MACE_ITEM);
            this.generateFlatItem(ModItems.NEPHRITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.NEPHRITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.NEPHRITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.NEPHRITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.NEPHRITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateFlatItem(ModItems.PYRITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
            this.generateTrimmableItem(ModItems.PYRITE_HELMET.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            this.generateTrimmableItem(ModItems.PYRITE_CHESTPLATE.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            this.generateTrimmableItem(ModItems.PYRITE_LEGGINGS.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            this.generateTrimmableItem(ModItems.PYRITE_BOOTS.get(), ModEquipmentAssets.PYRITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
            this.generateTrimmableItem(ModItems.NEPHRITE_HELMET.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            this.generateTrimmableItem(ModItems.NEPHRITE_CHESTPLATE.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            this.generateTrimmableItem(ModItems.NEPHRITE_LEGGINGS.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            this.generateTrimmableItem(ModItems.NEPHRITE_BOOTS.get(), ModEquipmentAssets.NEPHRITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
            this.generateTrimmableItem(ModItems.SAMURAI_HELMET.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
            this.generateTrimmableItem(ModItems.SAMURAI_CHESTPLATE.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
            this.generateTrimmableItem(ModItems.SAMURAI_LEGGINGS.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
            this.generateTrimmableItem(ModItems.SAMURAI_BOOTS.get(), ModEquipmentAssets.SAMURAI, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
            this.generateFlatItem(ModItems.PYRITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.SIKA_DEER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
            this.generateFlatItem(ModItems.TRICERATOPS_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
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
    protected @NotNull ItemModelGenerators getItemModelGenerators(ItemInfoCollector items, SimpleModelCollector models) {
        return new MyItemModelGenerators(items, models);
    }
}
