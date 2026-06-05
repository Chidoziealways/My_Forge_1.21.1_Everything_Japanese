package net.Chidoziealways.everythingjapanese.trim

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TextColor
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.util.Util
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup
import net.minecraft.world.item.equipment.trim.TrimMaterial
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ModTrimMaterials {
    val PYRITE = registryKey("pyrite")
    private val log: Logger? = LoggerFactory.getLogger(ModTrimMaterials::class.java)

    fun registryKey(name: String): ResourceKey<TrimMaterial> {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, name))
    }

    fun bootstrap(context: BootstrapContext<TrimMaterial>) {
        register(
            context,
            PYRITE,
            Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()),
            ModMaterialAssetGroup.PYRITE
        )
    }

    private fun register(
        context: BootstrapContext<TrimMaterial>, trimKey: ResourceKey<TrimMaterial>,
        style: Style, materialAssetGroup: MaterialAssetGroup
    ) {
        val component: Component =
            Component.translatable(Util.makeDescriptionId("trim_materials", trimKey.registry())).withStyle(style)
        context.register(trimKey, TrimMaterial(materialAssetGroup, component))
    }
}
