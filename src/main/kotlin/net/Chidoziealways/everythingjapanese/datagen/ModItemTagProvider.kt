package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture
import java.util.function.Function


class ModItemTagProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<Item>(
    output,
    Registries.ITEM,
    lookupProvider,
    Function { item: Item? -> item!!.builtInRegistryHolder().key() },
    JAPANESE_MOD_ID
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
            .add(JModItems.PYRITE_INGOT)
            .add(JModItems.RAW_PYRITE)
            .add(JModItems.PYRITE_SWORD)
            .add(JModItems.YA)

        tag(ModTags.Items.ANIMAL_SKIN)
            .add(Items.LEATHER)
            .add(Items.RABBIT_HIDE)

        tag(ModTags.Items.REPAIRS_PYRITE_ARMOR).add(JModItems.PYRITE_INGOT)

        tag(ModTags.Items.REPAIRS_NEPHRITE_ARMOR).add(JModItems.NEPHRITE)

        tag(ModTags.Items.PYRITE_TOOL_MATERIALS).add(JModItems.PYRITE_INGOT)

        tag(ModTags.Items.NEPHRITE_TOOL_MATERIALS).add(JModItems.NEPHRITE)

        tag(ModTags.Items.BULLETS)
            .add(JModItems.BULLET)

        tag(ItemTags.TRIMMABLE_ARMOR)
            .add(JModItems.PYRITE_HELMET)
            .add(JModItems.PYRITE_CHESTPLATE)
            .add(JModItems.PYRITE_LEGGINGS)
            .add(JModItems.PYRITE_BOOTS)
            .add(JModItems.NEPHRITE_HELMET)
            .add(JModItems.NEPHRITE_CHESTPLATE)
            .add(JModItems.NEPHRITE_LEGGINGS)
            .add(JModItems.NEPHRITE_BOOTS)

        tag(ItemTags.TRIM_MATERIALS)
            .add(JModItems.PYRITE_INGOT)

        tag(TRIM_TEMPLATES)
            .add(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE)

        tag(ItemTags.LOGS_THAT_BURN)
            .add(JModBlocks.HINOKI_MARUTA.asItem())
            .add(JModBlocks.HINOKI_MOKUZAI.asItem())
            .add(JModBlocks.STRIPPED_HINOKI_MARUTA.asItem())
            .add(JModBlocks.STRIPPED_HINOKI_MOKUZAI.asItem())

        tag(ItemTags.PLANKS)
            .add(JModBlocks.HINOKI_BAN.asItem())

        tag(ModTags.Items.HINOKI_MARUTA)
            .add(JModBlocks.HINOKI_MARUTA.asItem())
            .add(JModBlocks.STRIPPED_HINOKI_MARUTA.asItem())

        tag(ItemTags.ARROWS)
            .add(JModItems.YA)
    }

    companion object {
        val TRIM_TEMPLATES: TagKey<Item?> = TagKey.create<Item?>(
            Registries.ITEM,
            ResourceLocation.withDefaultNamespace("trim_templates")
        )
    }
}
