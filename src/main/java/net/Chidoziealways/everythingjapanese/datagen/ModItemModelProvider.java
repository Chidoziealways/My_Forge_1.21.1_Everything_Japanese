package net.Chidoziealways.everythingjapanese.datagen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static{
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }


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
        //basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.INCENSE.get());
        basicItem(ModItems.UDON.get());
        basicItem(ModItems.WOODEN_KATANA.get());
        basicItem(ModItems.YA.get());
        basicItem(ModItems.AO_TO_NATSU_MUSIC_DISC.get());
        basicItem(ModItems.RICE_SEEDS.get());
        basicItem(ModItems.RAW_RICE.get());
        basicItem(ModItems.RICE.get());
        basicItem(ModItems.YAMAZAKI_BERRIES.get());
        basicItem(ModItems.NEPHRITE.get());

        buttonItem(ModBlocks.PYRITE_BUTTON, ModBlocks.PYRITE_BLOCK);
        wallItem(ModBlocks.PYRITE_WALL, ModBlocks.PYRITE_BLOCK);
        fenceItem(ModBlocks.PYRITE_FENCE, ModBlocks.PYRITE_BLOCK);

        simpleBlockItem(ModBlocks.PYRITE_DOOR);

        handHeldItem(ModItems.PYRITE_SWORD);
        handHeldItem(ModItems.PYRITE_PICKAXE);
        handHeldItem(ModItems.PYRITE_SHOVEL);
        handHeldItem(ModItems.PYRITE_AXE);
        handHeldItem(ModItems.PYRITE_HOE);
        handHeldItem(ModItems.NEPHRITE_SWORD);
        handHeldItem(ModItems.NEPHRITE_PICKAXE);
        handHeldItem(ModItems.NEPHRITE_SHOVEL);
        handHeldItem(ModItems.NEPHRITE_AXE);
        handHeldItem(ModItems.NEPHRITE_HOE);
        handHeldItem(ModItems.PYRITE_HAMMER);
        handHeldItem(ModItems.PYRITE_BATTLE_AXE);

        trimmedArmorItem(ModItems.PYRITE_HELMET);
        trimmedArmorItem(ModItems.PYRITE_CHESTPLATE);
        trimmedArmorItem(ModItems.PYRITE_LEGGINGS);
        trimmedArmorItem(ModItems.PYRITE_BOOTS);
        trimmedArmorItem(ModItems.NEPHRITE_HELMET);
        trimmedArmorItem(ModItems.NEPHRITE_CHESTPLATE);
        trimmedArmorItem(ModItems.NEPHRITE_LEGGINGS);
        trimmedArmorItem(ModItems.NEPHRITE_BOOTS);

        basicItem(ModItems.SAMURAI_HELMET.get());
        basicItem(ModItems.SAMURAI_CHESTPLATE.get());
        basicItem(ModItems.SAMURAI_LEGGINGS.get());
        basicItem(ModItems.SAMURAI_BOOTS.get());

        withExistingParent(ModItems.TRICERATOPS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SIKA_DEER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        basicItem(ModItems.PYRITE_HORSE_ARMOR.get());
        basicItem(ModItems.KOI_FISH_SMITHING_TEMPLATE.get());

        saplingItem(ModBlocks.HINOKI_NAEGI);
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "block/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = EverythingJapanese.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }


    private ItemModelBuilder handHeldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "item/" + item.getId().getPath()));
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
                .texture("wall", ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "item/" + item.getId().getPath()));
    }
}
