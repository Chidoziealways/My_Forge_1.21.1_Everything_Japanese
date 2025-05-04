package net.Chidoziealways.everythingjapanese.trim;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> PYRITE =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite"));
    private static final Logger log = LoggerFactory.getLogger(ModTrimMaterials.class);

    public static void bootstrap(BootstrapContext<TrimMaterial> context){
        register(context, PYRITE, Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()), ModMaterialAssetGroup.PYRITE);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey,
                                 Style style, MaterialAssetGroup materialAssetGroup){
        Component component = Component.translatable(Util.makeDescriptionId("trim_materials", trimKey.location())).withStyle(style);
        context.register(trimKey, new TrimMaterial(materialAssetGroup, component));
    }
}
