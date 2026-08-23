package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.item.JModItemsIds
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.references.ItemIds
import net.minecraft.resources.Identifier
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.neoforged.neoforge.common.data.ItemTagsProvider
import java.util.concurrent.CompletableFuture
import java.util.function.Function


class ModItemTagProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : ItemTagsProvider(
    output,
    lookupProvider,
    JAPANESE_MOD_ID
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
            .add(JModItemsIds.PYRITE_INGOT)
            .add(JModItemsIds.RAW_PYRITE)
            .add(JModItemsIds.PYRITE_SWORD)
            .add(JModItemsIds.YA)

        tag(ModTags.Items.ANIMAL_SKIN)
            .add(ItemIds.LEATHER)
            .add(ItemIds.RABBIT_HIDE)

        tag(ModTags.Items.REPAIRS_PYRITE_ARMOR).add(JModItemsIds.PYRITE_INGOT)

        tag(ModTags.Items.REPAIRS_NEPHRITE_ARMOR).add(JModItemsIds.NEPHRITE)

        tag(ModTags.Items.PYRITE_TOOL_MATERIALS).add(JModItemsIds.PYRITE_INGOT)

        tag(ModTags.Items.NEPHRITE_TOOL_MATERIALS).add(JModItemsIds.NEPHRITE)

        tag(ModTags.Items.BULLETS)
            .add(JModItemsIds.BULLET)

        tag(ItemTags.TRIMMABLE_ARMOR)
            .add(JModItemsIds.PYRITE_HELMET)
            .add(JModItemsIds.PYRITE_CHESTPLATE)
            .add(JModItemsIds.PYRITE_LEGGINGS)
            .add(JModItemsIds.PYRITE_BOOTS)
            .add(JModItemsIds.NEPHRITE_HELMET)
            .add(JModItemsIds.NEPHRITE_CHESTPLATE)
            .add(JModItemsIds.NEPHRITE_LEGGINGS)
            .add(JModItemsIds.NEPHRITE_BOOTS)

        tag(ItemTags.TRIM_MATERIALS)
            .add(JModItemsIds.PYRITE_INGOT)

        tag(TRIM_TEMPLATES)
            .add(JModItemsIds.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE)

        tag(ItemTags.ARROWS)
            .add(JModItemsIds.YA)
    }

    companion object {
        val TRIM_TEMPLATES: TagKey<Item> = TagKey.create<Item>(
            Registries.ITEM,
            Identifier.withDefaultNamespace("trim_templates")
        )
    }
}
