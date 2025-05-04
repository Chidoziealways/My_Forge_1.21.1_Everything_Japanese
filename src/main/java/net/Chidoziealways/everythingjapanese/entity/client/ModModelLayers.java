package net.Chidoziealways.everythingjapanese.entity.client;

import com.google.common.collect.Sets;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class ModModelLayers {
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation SIKA_DEER = register("sika_deer");
    public static final ModelLayerLocation SIKA_DEER_BABY = register("sika_deer_baby");
    public static final ModelLayerLocation TRICERATOPS = register("triceratops");
    public static final ModelLayerLocation TRICERATOPS_BABY = register("triceratops_baby");


    private static ModelLayerLocation register(String pPath) {
        return register(pPath, "main");
    }

    private static ModelLayerLocation register(String pPath, String pModel) {
        ModelLayerLocation modellayerlocation = createLocation(pPath, pModel);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pPath), pModel);
    }
}
