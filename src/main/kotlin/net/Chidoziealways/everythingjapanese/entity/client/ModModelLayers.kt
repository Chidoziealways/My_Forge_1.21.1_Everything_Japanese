package net.Chidoziealways.everythingjapanese.entity.client

import com.google.common.collect.Sets
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.resources.ResourceLocation

object ModModelLayers {
    private val ALL_MODELS: MutableSet<ModelLayerLocation?> = Sets.newHashSet<ModelLayerLocation?>()
    val SIKA_DEER: ModelLayerLocation = register("sika_deer")
    val SIKA_DEER_BABY: ModelLayerLocation = register("sika_deer_baby")
    val TRICERATOPS: ModelLayerLocation = register("triceratops")
    val TRICERATOPS_BABY: ModelLayerLocation = register("triceratops_baby")


    private fun register(pPath: String, pModel: String = "main"): ModelLayerLocation {
        val modellayerlocation = createLocation(pPath, pModel)
        check(ALL_MODELS.add(modellayerlocation)) { "Duplicate registration for " + modellayerlocation }
        return modellayerlocation
    }

    private fun createLocation(pPath: String, pModel: String): ModelLayerLocation {
        return ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, pPath), pModel)
    }
}
