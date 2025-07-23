package net.Chidoziealways.everythingjapanese.util

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

object ModRegistries {
    val JUTSU: ResourceKey<Registry<Jutsu>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "jutsu"))

    val QUEST: ResourceKey<Registry<Quest>> =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "quest"))
}
