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
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture
import java.util.function.Function


class ModItemTagProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider?>,
    helper: ExistingFileHelper?
) : IntrinsicHolderTagsProvider<Item?>(
    output,
    Registries.ITEM,
    lookupProvider,
    Function { item: Item? -> item!!.builtInRegistryHolder().key() },
    MOD_ID,
    helper
) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
            .add(ModItems.PYRITE_INGOT!!.get())
            .add(ModItems.RAW_PYRITE!!.get())
            .add(ModItems.PYRITE_SWORD!!.get())
            .add(ModItems.YA!!.get())

        tag(ModTags.Items.REPAIRS_PYRITE_ARMOR).add(ModItems.PYRITE_INGOT.get())

        tag(ModTags.Items.REPAIRS_NEPHRITE_ARMOR).add(ModItems.NEPHRITE!!.get())

        tag(ModTags.Items.PYRITE_TOOL_MATERIALS).add(ModItems.PYRITE_INGOT.get())

        tag(ModTags.Items.NEPHRITE_TOOL_MATERIALS).add(ModItems.NEPHRITE.get())

        tag(ItemTags.TRIMMABLE_ARMOR)
            .add(ModItems.PYRITE_HELMET!!.get())
            .add(ModItems.PYRITE_CHESTPLATE!!.get())
            .add(ModItems.PYRITE_LEGGINGS!!.get())
            .add(ModItems.PYRITE_BOOTS!!.get())

        tag(ItemTags.TRIM_MATERIALS)
            .add(ModItems.PYRITE_INGOT.get())

        tag(TRIM_TEMPLATES)
            .add(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE!!.get())

        tag(ItemTags.LOGS_THAT_BURN)
            .add(ModBlocks.HINOKI_MARUTA.get()!!.asItem())
            .add(ModBlocks.HINOKI_MOKUZAI.get()!!.asItem())
            .add(ModBlocks.STRIPPED_HINOKI_MARUTA.get()!!.asItem())
            .add(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get()!!.asItem())

        tag(ItemTags.PLANKS)
            .add(ModBlocks.HINOKI_BAN.get()!!.asItem())

        tag(ModTags.Items.HINOKI_MARUTA)
            .add(ModBlocks.HINOKI_MARUTA.get()!!.asItem())
            .add(ModBlocks.STRIPPED_HINOKI_MARUTA.get()!!.asItem())

        tag(ItemTags.ARROWS)
            .add(ModItems.YA.get())
    }

    companion object {
        val TRIM_TEMPLATES: TagKey<Item?> = TagKey.create<Item?>(
            Registries.ITEM,
            ResourceLocation.withDefaultNamespace("trim_templates")
        )
    }
}
