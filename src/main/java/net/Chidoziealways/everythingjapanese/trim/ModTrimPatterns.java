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
import net.minecraft.world.item.equipment.trim.TrimPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModTrimPatterns {
    public static final ResourceKey<TrimPattern> KOI_FISH = registryKey("koi_fish");
    private static final Logger log = LoggerFactory.getLogger(ModTrimPatterns.class);

    public static void bootstrap(BootstrapContext<TrimPattern> context){
        log.info("Registering all Trim Patterns into DataPack Registry");
        register(context, KOI_FISH);
    }

    private static void register(BootstrapContext<TrimPattern> context, ResourceKey<TrimPattern> key){
        TrimPattern trimPattern = new TrimPattern(defaultAssetId(key),
                Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false);
        context.register(key, trimPattern);

    }

    private static ResourceKey<TrimPattern> registryKey(String pName) {
        return ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pName));
    }

    public static ResourceLocation defaultAssetId(ResourceKey<TrimPattern> p_394517_) {
        return p_394517_.location();
    }
}
