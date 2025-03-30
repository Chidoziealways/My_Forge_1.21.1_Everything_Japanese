package net.Chidoziealways.everythingjapanese.trim;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTrimPatterns {
    public static final ResourceKey<TrimPattern> KOI_FISH = ResourceKey.create(Registries.TRIM_PATTERN,
            ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "koi_fish"));

    public static void bootstrap(BootstrapContext<TrimPattern> context){
        register(context, ModItems.KOI_FISH_SMITHING_TEMPLATE.get(), KOI_FISH);
    }

    private static void register(BootstrapContext<TrimPattern> context, Item item, ResourceKey<TrimPattern> key){
        TrimPattern trimPattern = new TrimPattern(key.location(), ForgeRegistries.ITEMS.getHolder(item).get(),
                Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false);
        context.register(key, trimPattern);

    }
}
