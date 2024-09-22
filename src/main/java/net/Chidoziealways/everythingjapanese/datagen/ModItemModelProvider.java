package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EverythingJapanese.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_PYRITE.get());
        basicItem(ModItems.PYRITE_INGOT.get());
        basicItem(ModItems.DIESEL.get());
        basicItem(ModItems.GREEN_TEA.get());
        basicItem(ModItems.SUSHI.get());
        basicItem(ModItems.PYRITE_SWORD.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.INCENSE.get());
        basicItem(ModItems.PYRITE_AXE.get());
        basicItem(ModItems.UDON.get());
        basicItem(ModItems.WOODEN_KATANA.get());
        basicItem(ModItems.YA.get());

        buttonItem(ModBlocks.PYRITE_BUTTON, ModBlocks.PYRITE_BLOCK);
        wallItem(ModBlocks.PYRITE_WALL, ModBlocks.PYRITE_BLOCK);
        fenceItem(ModBlocks.PYRITE_FENCE, ModBlocks.PYRITE_BLOCK);

        simpleBlockItem(ModBlocks.PYRITE_DOOR);
    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "item/" + item.getId().getPath()));
    }
}
