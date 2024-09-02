package net.Chidoziealways.everythingjapanese.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModBLockTags {

    public static final TagKey<Block> INCORRECT_FOR_PYRITE_TOOL = create("incorrect_for_pyrite_tool");

    private ModBLockTags() {
    }

    private static TagKey<Block> create(String pName) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(pName));
    }

    public static TagKey<Block> create(String namepsace, String path) {
        return create(ResourceLocation.fromNamespaceAndPath(namepsace, path));
    }

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }
}
