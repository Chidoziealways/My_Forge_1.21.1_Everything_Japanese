package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.custom.PyriteLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EverythingJapanese.MOD_ID ,exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PYRITE_BLOCK);
        blockWithItem(ModBlocks.TRANSFORMER_BLOCK);
        blockWithItem(ModBlocks.RAW_PYRITE_BLOCK);
        blockWithItem(ModBlocks.HINOKI_MARUTA);
        blockWithItem(ModBlocks.PYRITE_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.PYRITE_ORE);
        blockWithItem(ModBlocks.STRIPPED_HINOKI_MARUTA);
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
