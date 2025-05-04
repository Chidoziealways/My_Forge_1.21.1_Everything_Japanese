package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;


public interface ModEquipmentAssets {
    ResourceKey<EquipmentAsset> PYRITE = createId("pyrite");
    ResourceKey<EquipmentAsset> NEPHRITE = createId("nephrite");
    ResourceKey<EquipmentAsset> SAMURAI = createId("samurai");

    static ResourceKey<EquipmentAsset> createId(String pName) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pName));
    }
}
