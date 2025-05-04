package net.Chidoziealways.everythingjapanese.util;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModRegistries {
    public static final ResourceKey<Registry<Jutsu>> JUTSU =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "jutsu"));
}
