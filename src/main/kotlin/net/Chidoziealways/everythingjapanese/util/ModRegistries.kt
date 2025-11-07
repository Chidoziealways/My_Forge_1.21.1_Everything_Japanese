package net.Chidoziealways.everythingjapanese.util

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.Design
import net.Chidoziealways.everythingjapanese.item.custom.fish_hook.MobMorph
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.registries.RegistryBuilder

object ModRegistries {
    val JUTSU_KEY: ResourceKey<Registry<Jutsu>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "jutsu"))

    val JUTSU: Registry<Jutsu> = RegistryBuilder(JUTSU_KEY)
        .sync(true)
        .defaultKey(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "small_fireball"))
        .maxId(256)
        .create()

    val KANJI: ResourceKey<Registry<KanjiType>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "kanji"))

    val DESIGN: ResourceKey<Registry<Design>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "design"))

    val MORPHS_KEY: ResourceKey<Registry<MobMorph>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "morphs"))

    val MORPHS: Registry<MobMorph> = RegistryBuilder(MORPHS_KEY)
        .sync(true)
        .defaultKey(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "blaze"))
        .maxId(256)
        .create()
}
