package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
    }
}
