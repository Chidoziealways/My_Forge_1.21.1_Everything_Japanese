package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.EquipmentAssets

interface ModEquipmentAssets {
    companion object {
        fun createId(pName: String): ResourceKey<EquipmentAsset> {
            return ResourceKey.create<EquipmentAsset>(
                EquipmentAssets.ROOT_ID,
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, pName)
            )
        }

        val PYRITE: ResourceKey<EquipmentAsset> = createId("pyrite")
        val NEPHRITE: ResourceKey<EquipmentAsset> = createId("nephrite")
        val SAMURAI: ResourceKey<EquipmentAsset> = createId("samurai")
    }
}
