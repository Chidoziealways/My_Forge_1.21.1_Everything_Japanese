package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.level.storage.loot.LootTable

object ModLootTables {
    val DOJO: ResourceKey<LootTable> = register("chests/dojo")

    fun register(name: String): ResourceKey<LootTable> {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, name))
    }
}