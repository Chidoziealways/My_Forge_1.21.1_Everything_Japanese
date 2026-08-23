package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.references.BlockItemId
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item

object JModItemsIds {
    val PYRITE_INGOT = create("pyrite_ingot")
    val RAW_PYRITE = create("raw_pyrite")
    val PYRITE_SWORD = create("pyrite_sword")
    val YA = create("ya")
    val NEPHRITE = create("nephrite")
    val BULLET = create("bullet")
    val PYRITE_HELMET = create("pyrite_helmet")
    val PYRITE_CHESTPLATE = create("pyrite_chestplate")
    val PYRITE_LEGGINGS = create("pyrite_leggings")
    val PYRITE_BOOTS = create("pyrite_boots")
    val NEPHRITE_HELMET = create("nephrite_helmet")
    val NEPHRITE_CHESTPLATE = create("nephrite_chestplate")
    val NEPHRITE_LEGGINGS = create("nephrite_leggings")
    val NEPHRITE_BOOTS = create("nephrite_boots")
    val KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE = create("koi_fish_armor_trim_smithing_template")
    val HINOKI_BAN = BlockItemId.create(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_ban"), Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_ban"))

    private fun create(name: String) : ResourceKey<Item> {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, name))
    }
}