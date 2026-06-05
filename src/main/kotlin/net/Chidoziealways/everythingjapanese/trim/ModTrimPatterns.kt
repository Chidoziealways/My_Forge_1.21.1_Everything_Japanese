package net.Chidoziealways.everythingjapanese.trim

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.util.Util
import net.minecraft.world.item.equipment.trim.TrimPattern
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ModTrimPatterns {
    val KOI_FISH: ResourceKey<TrimPattern> = registryKey("koi_fish")
    private val log: Logger = LoggerFactory.getLogger(ModTrimPatterns::class.java)

    fun bootstrap(context: BootstrapContext<TrimPattern>) {
        log.info("Registering all Trim Patterns into DataPack Registry")
        register(context, KOI_FISH)
    }

    private fun register(context: BootstrapContext<TrimPattern>, key: ResourceKey<TrimPattern>) {
        val trimPattern = TrimPattern(
            defaultAssetId(key),
            Component.translatable(Util.makeDescriptionId("trim_pattern", key.registry())), false
        )
        context.register(key, trimPattern)
    }

    private fun registryKey(pName: String): ResourceKey<TrimPattern> {
        return ResourceKey.create(
            Registries.TRIM_PATTERN,
            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, pName)
        )
    }

    fun defaultAssetId(p_394517_: ResourceKey<TrimPattern>): Identifier {
        return p_394517_.registry()
    }
}
