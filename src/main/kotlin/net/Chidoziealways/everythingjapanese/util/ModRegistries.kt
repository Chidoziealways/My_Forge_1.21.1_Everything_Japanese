package net.Chidoziealways.everythingjapanese.util

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.registries.RegistryBuilder

object ModRegistries {
    val JUTSU_KEY: ResourceKey<Registry<Jutsu>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "jutsu"))

    val JUTSU: Registry<Jutsu> = RegistryBuilder(JUTSU_KEY)
        .sync(true)
        .defaultKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_fireball"))
        .maxId(256)
        .create()

    val QUEST: ResourceKey<Registry<Quest>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "quest"))

    val KANJI: ResourceKey<Registry<KanjiType>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "kanji"))
}
