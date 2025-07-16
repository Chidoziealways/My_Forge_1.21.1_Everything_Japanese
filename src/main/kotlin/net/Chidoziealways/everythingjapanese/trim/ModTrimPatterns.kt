package net.Chidoziealways.everythingjapanese.trim

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.Util
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.equipment.trim.TrimPattern
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ModTrimPatterns {
    val KOI_FISH: ResourceKey<TrimPattern?> = registryKey("koi_fish")
    private val log: Logger = LoggerFactory.getLogger(ModTrimPatterns::class.java)

    fun bootstrap(context: BootstrapContext<TrimPattern?>) {
        log.info("Registering all Trim Patterns into DataPack Registry")
        register(context, KOI_FISH)
    }

    private fun register(context: BootstrapContext<TrimPattern?>, key: ResourceKey<TrimPattern?>) {
        val trimPattern = TrimPattern(
            defaultAssetId(key),
            Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false
        )
        context.register(key, trimPattern)
    }

    private fun registryKey(pName: String): ResourceKey<TrimPattern?> {
        return ResourceKey.create<TrimPattern?>(
            Registries.TRIM_PATTERN,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, pName)
        )
    }

    fun defaultAssetId(p_394517_: ResourceKey<TrimPattern?>): ResourceLocation {
        return p_394517_.location()
    }
}
