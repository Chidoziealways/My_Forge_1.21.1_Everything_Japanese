package net.Chidoziealways.everythingjapanese.util;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Objects;
import java.util.function.Supplier;

public class ModTags {

    public static class Blocks{
        public static final TagKey<Block> NEEDS_PYRITE_TOOL = createBlockTag("needs_pyrite_tool");
        public static final TagKey<Block> NEEDS_NEPHRITE_TOOL = createBlockTag("needs_nephrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_PYRITE_TOOL = createBlockTag("incorrect_for_pyrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_NEPHRITE_TOOL = createBlockTag("incorrect_for_nephrite_tool");
        public static final TagKey<Block> INFINIBURN_HELL = createBlockTag("infiniburn_hell");

        private Blocks() {
        }

        private static TagKey<Block> createBlockTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createItemTag("transformable_items");
        public static final TagKey<Item> PYRITE_TOOL_MATERIALS = createItemTag("pyrite_tool_materials");
        public static final TagKey<Item> NEPHRITE_TOOL_MATERIALS = createItemTag("nephrite_tool_materials");
        public static final TagKey<Item> REPAIRS_PYRITE_ARMOR = createItemTag("repairs_pyrite_armor");
        public static final TagKey<Item> REPAIRS_NEPHRITE_ARMOR = createItemTag("repairs_nephrite_armor");
        public static final TagKey<Item> HINOKI_MARUTA = createItemTag("hinoki_maruta");

        private Items() {
        }

        private static TagKey<Item> createItemTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
        }
    }

    public static class Biomes{
        private Biomes() {
        }

        public static final TagKey<Biome> HAS_HELL_TEMPLE = createBiomeTag("has_structure/hell_temple");
        public static final TagKey<Biome> IS_HELL = createBiomeTag("is_hell");

        private static TagKey<Biome> createBiomeTag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
        }
    }
}
