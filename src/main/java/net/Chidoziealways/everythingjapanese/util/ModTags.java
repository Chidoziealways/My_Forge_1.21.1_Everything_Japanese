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

        public static final TagKey<Block> INCORRECT_FOR_PYRITE_TOOL = create("incorrect_for_pyrite_tool");

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
