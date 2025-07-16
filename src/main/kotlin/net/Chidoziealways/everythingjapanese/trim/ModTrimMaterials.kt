package net.Chidoziealways.everythingjapanese.trim

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.Util
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TextColor
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup
import net.minecraft.world.item.equipment.trim.TrimMaterial
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ModTrimMaterials {
    val PYRITE: ResourceKey<TrimMaterial?> = ResourceKey.create<TrimMaterial?>(
        Registries.TRIM_MATERIAL,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite")
    )
    private val log: Logger? = LoggerFactory.getLogger(ModTrimMaterials::class.java)

    fun bootstrap(context: BootstrapContext<TrimMaterial?>) {
        register(
            context,
            PYRITE,
            Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()),
            ModMaterialAssetGroup.PYRITE
        )
    }

    private fun register(
        context: BootstrapContext<TrimMaterial?>, trimKey: ResourceKey<TrimMaterial?>,
        style: Style, materialAssetGroup: MaterialAssetGroup
    ) {
        val component: Component =
            Component.translatable(Util.makeDescriptionId("trim_materials", trimKey.location())).withStyle(style)
        context.register(trimKey, TrimMaterial(materialAssetGroup, component))
    }
}
