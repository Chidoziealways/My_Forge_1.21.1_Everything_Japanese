package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.item.ModItems
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
    MOD_ID
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
            .add(ModItems.PYRITE_INGOT)
            .add(ModItems.RAW_PYRITE)
            .add(ModItems.PYRITE_SWORD)
            .add(ModItems.YA)

        tag(ModTags.Items.ANIMAL_SKIN)
            .add(Items.LEATHER)
            .add(Items.RABBIT_HIDE)

        tag(ModTags.Items.REPAIRS_PYRITE_ARMOR).add(ModItems.PYRITE_INGOT)

        tag(ModTags.Items.REPAIRS_NEPHRITE_ARMOR).add(ModItems.NEPHRITE)

        tag(ModTags.Items.PYRITE_TOOL_MATERIALS).add(ModItems.PYRITE_INGOT)

        tag(ModTags.Items.NEPHRITE_TOOL_MATERIALS).add(ModItems.NEPHRITE)

        tag(ItemTags.TRIMMABLE_ARMOR)
            .add(ModItems.PYRITE_HELMET)
            .add(ModItems.PYRITE_CHESTPLATE)
            .add(ModItems.PYRITE_LEGGINGS)
            .add(ModItems.PYRITE_BOOTS)
            .add(ModItems.NEPHRITE_HELMET)
            .add(ModItems.NEPHRITE_CHESTPLATE)
            .add(ModItems.NEPHRITE_LEGGINGS)
            .add(ModItems.NEPHRITE_BOOTS)

        tag(ItemTags.TRIM_MATERIALS)
            .add(ModItems.PYRITE_INGOT)

        tag(TRIM_TEMPLATES)
            .add(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE)

        tag(ItemTags.LOGS_THAT_BURN)
            .add(ModBlocks.HINOKI_MARUTA.asItem())
            .add(ModBlocks.HINOKI_MOKUZAI.asItem())
            .add(ModBlocks.STRIPPED_HINOKI_MARUTA.asItem())
            .add(ModBlocks.STRIPPED_HINOKI_MOKUZAI.asItem())

        tag(ItemTags.PLANKS)
            .add(ModBlocks.HINOKI_BAN.asItem())

        tag(ModTags.Items.HINOKI_MARUTA)
            .add(ModBlocks.HINOKI_MARUTA.asItem())
            .add(ModBlocks.STRIPPED_HINOKI_MARUTA.asItem())

        tag(ItemTags.ARROWS)
            .add(ModItems.YA)
    }

    companion object {
        val TRIM_TEMPLATES: TagKey<Item?> = TagKey.create<Item?>(
            Registries.ITEM,
            ResourceLocation.withDefaultNamespace("trim_templates")
        )
    }
}
