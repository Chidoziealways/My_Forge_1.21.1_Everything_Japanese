package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject ) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
