package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.Util
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import java.util.*
import java.util.function.Consumer

object ModArmorMaterials {
    val PYRITE: ArmorMaterial = ArmorMaterial(
        1200,
        Util.make(
            EnumMap<ArmorType, Int>(ArmorType::class.java),
            Consumer { attribute: EnumMap<ArmorType, Int> ->
                attribute.put(ArmorType.BOOTS, 1)
                attribute.put(ArmorType.LEGGINGS, 3)
                attribute.put(ArmorType.CHESTPLATE, 5)
                attribute.put(ArmorType.HELMET, 2)
                attribute.put(ArmorType.BODY, 7)
            }), 15, SoundEvents.ARMOR_EQUIP_GOLD,
        10f, 0.1f, ItemTags.REPAIRS_GOLD_ARMOR, ModEquipmentAssets.Companion.PYRITE
    )

    val NEPHRITE: ArmorMaterial = ArmorMaterial(
        1000000,
        Util.make(
            EnumMap<ArmorType, Int>(ArmorType::class.java),
            Consumer { attribute: EnumMap<ArmorType, Int> ->
                attribute.put(ArmorType.BOOTS, 5)
                attribute.put(ArmorType.LEGGINGS, 6)
                attribute.put(ArmorType.CHESTPLATE, 8)
                attribute.put(ArmorType.HELMET, 7)
                attribute.put(ArmorType.BODY, 13)
            }),
        20,
        SoundEvents.ARMOR_EQUIP_DIAMOND,
        50f,
        0.1f,
        ItemTags.REPAIRS_NETHERITE_ARMOR,
        ModEquipmentAssets.Companion.NEPHRITE
    )

    init {
        println("DEBUG: Tag for NEPHRITE_ARMOR_MATERIAL = " + ModTags.Items.REPAIRS_NEPHRITE_ARMOR)
    }

    val SAMURAI_ARMOR_MATERIAL: ArmorMaterial = ArmorMaterial(
        100000000,
        Util.make<EnumMap<ArmorType, Int>>(
            EnumMap<ArmorType, Int>(ArmorType::class.java),
            Consumer { attribute: EnumMap<ArmorType, Int> ->
                attribute.put(ArmorType.BOOTS, 5)
                attribute.put(ArmorType.LEGGINGS, 7)
                attribute.put(ArmorType.CHESTPLATE, 9)
                attribute.put(ArmorType.HELMET, 5)
                attribute.put(ArmorType.BODY, 11)
            }),
        15,
        SoundEvents.ARMOR_EQUIP_IRON,
        4f,
        0.1f,
        ItemTags.REPAIRS_IRON_ARMOR,
        ModEquipmentAssets.Companion.SAMURAI
    )
}