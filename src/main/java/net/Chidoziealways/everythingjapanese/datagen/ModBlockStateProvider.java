package net.Chidoziealways.everythingjapanese.datagen;

import com.mojang.datafixers.types.Func;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.custom.PyriteLampBlock;
import net.Chidoziealways.everythingjapanese.block.custom.RiceCropBlock;
import net.Chidoziealways.everythingjapanese.block.custom.YamazakiBerryBushBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EverythingJapanese.MOD_ID ,exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PYRITE_BLOCK);
        blockWithItem(ModBlocks.TRANSFORMER_BLOCK);
        blockWithItem(ModBlocks.RAW_PYRITE_BLOCK);
        blockWithItem(ModBlocks.PYRITE_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.PYRITE_ORE);
        blockWithItem(ModBlocks.NEPHRITE_BLOCK);
        blockWithItem(ModBlocks.NEPHRITE_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.NEPHRITE_ORE);
        stairsBlock(ModBlocks.PYRITE_STAIRS.get(), blockTexture(ModBlocks.PYRITE_BLOCK.get()));
        slabBlock(ModBlocks.PYRITE_SLAB.get(), blockTexture(ModBlocks.PYRITE_BLOCK.get()), blockTexture(ModBlocks.PYRITE_BLOCK.get()));

        buttonBlock(ModBlocks.PYRITE_BUTTON.get(), blockTexture(ModBlocks.PYRITE_BLOCK.get()));
        pressurePlateBlock(ModBlocks.PYRITE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.PYRITE_BLOCK.get()));

        fenceBlock(ModBlocks.PYRITE_FENCE.get(), blockTexture(ModBlocks.PYRITE_BLOCK.get()));
        fenceGateBlock(ModBlocks.PYRITE_FENCE_GATE.get(), blockTexture(ModBlocks.PYRITE_BLOCK.get()));
        wallBlock(ModBlocks.PYRITE_WALL.get(), blockTexture(ModBlocks.PYRITE_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.PYRITE_DOOR.get(), modLoc("block/pyrite_door_bottom"), modLoc("block/pyrite_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.PYRITE_TRAPDOOR.get(), modLoc("block/pyrite_trapdoor"), true, "cutout");

        blockItem(ModBlocks.PYRITE_STAIRS);
        blockItem(ModBlocks.PYRITE_SLAB);
        blockItem(ModBlocks.PYRITE_PRESSURE_PLATE);
        blockItem(ModBlocks.PYRITE_FENCE);
        blockItem(ModBlocks.PYRITE_FENCE_GATE);
        blockItem(ModBlocks.PYRITE_TRAPDOOR, "_bottom");
        customLamp();

        makeCrop(((CropBlock) ModBlocks.RICE_CROP.get()), "rice_crop_stage", "rice_crop_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.YAMAZAKI_BERRY_BUSH.get()), "yamazaki_berry_bush_stage", "yamazaki_berry_bush_stage");

        logBlock(ModBlocks.HINOKI_MARUTA.get());
        axisBlock(ModBlocks.HINOKI_MOKUZAI.get(), blockTexture(ModBlocks.HINOKI_MARUTA.get()), blockTexture(ModBlocks.HINOKI_MARUTA.get()));
        logBlock(ModBlocks.STRIPPED_HINOKI_MARUTA.get());
        axisBlock(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get(), blockTexture(ModBlocks.HINOKI_MARUTA.get()), blockTexture(ModBlocks.STRIPPED_HINOKI_MARUTA.get()));

        blockItem(ModBlocks.HINOKI_MARUTA);
        blockItem(ModBlocks.HINOKI_MOKUZAI);
        blockItem(ModBlocks.STRIPPED_HINOKI_MARUTA);
        blockItem(ModBlocks.STRIPPED_HINOKI_MOKUZAI);

        blockWithItem(ModBlocks.HINOKI_BAN);

        leavesBlock(ModBlocks.HINOKI_HA);
        saplingBlock(ModBlocks.HINOKI_NAEGI);

        blockWithItem(ModBlocks.GROWTH_CHAMBER);
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject){
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName){
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName){
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((RiceCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "block/" + textureName + state.getValue(((RiceCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName){
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName){
     ConfiguredModel[] models = new ConfiguredModel[1];
     models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(YamazakiBerryBushBlock.AGE),
             ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "block/" + textureName + state.getValue(YamazakiBerryBushBlock.AGE))).renderType("cutout"));

     return models;
    }

    private void customLamp(){
        getVariantBuilder(ModBlocks.PYRITE_LAMP.get()).forAllStates(state -> {
            if(state.getValue(PyriteLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel((models().cubeAll("pyrite_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "block/" + "pyrite_lamp_on"))))};
            }else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("pyrite_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "block/" + "pyrite_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.PYRITE_LAMP.get(), models().cubeAll("pyrite_lamp_on",
                ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "block/" + "pyrite_lamp_on")));
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject ) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("everythingjapanese:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("everythingjapanese:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
