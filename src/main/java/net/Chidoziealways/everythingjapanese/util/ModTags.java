package net.Chidoziealways.everythingjapanese.util;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_PYRITE_TOOL = create("needs_pyrite_tool");
        public static final TagKey<Block> NEEDS_NEPHRITE_TOOL = create("needs_nephrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_PYRITE_TOOL = create("incorrect_for_pyrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_NEPHRITE_TOOL = create("incorrect_for_nephrite_tool");
        private Blocks() {
        }

        private static TagKey<Block> create(String pName) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(pName));
        }

        private static TagKey<Block> createtag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createtag("transformable_items");
        public static final TagKey<Item> PYRITE = bind("pyrite");
        public static final TagKey<Item> NEPHRITE = bind("nephrite");
        public static final TagKey<Item> HINOKI_MARUTA = bind("hinoki_maruta");

        private Items() {
        }

        private static TagKey<Item> bind(String pName) {
            return TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(pName));
        }


        private static TagKey<Item> createtag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name));
    }
    }
}
