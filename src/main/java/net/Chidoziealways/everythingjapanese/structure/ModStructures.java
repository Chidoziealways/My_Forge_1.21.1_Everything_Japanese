package net.Chidoziealways.everythingjapanese.structure;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModStructures {
    public static final ResourceKey<Structure> HELL_TEMPLE = createKey("hell_temple");

    private static ResourceKey<Structure> createKey(String pName) {
        return ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pName));
    }
}
