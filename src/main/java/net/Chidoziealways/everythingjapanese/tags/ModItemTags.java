package net.Chidoziealways.everythingjapanese.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


public final class ModItemTags {
    public static final TagKey<Item> PYRITE = bind("pyrite");

    private ModItemTags() {
    }

    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(pName));
    }

    public static TagKey<Item> create(String namepsace, String path) {
        return create(ResourceLocation.fromNamespaceAndPath(namepsace, path));
    }

    public static TagKey<Item> create(ResourceLocation name) {
        return TagKey.create(Registries.ITEM, name);
    }
}
