package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.tags.EntityTypeTags
import net.minecraft.world.entity.EntityType
import java.util.concurrent.CompletableFuture
import java.util.function.Function

class ModEntityTagProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<EntityType<*>>(
    output,
    Registries.ENTITY_TYPE,
    lookupProvider,
    Function { entity -> entity!!.builtInRegistryHolder().key() },
    JAPANESE_MOD_ID
) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(EntityTypeTags.ZOMBIES)
            .add(ModEntities.CURSED_SAMURAI)

        tag(EntityTypeTags.DEFLECTS_PROJECTILES)
            .add(ModEntities.TRICERATOPS)

        tag(EntityTypeTags.FOLLOWABLE_FRIENDLY_MOBS)
            .add(ModEntities.SIKA_DEER)
    }
}