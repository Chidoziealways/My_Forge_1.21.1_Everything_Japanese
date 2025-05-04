package net.Chidoziealways.everythingjapanese.entity.client.triceratops;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.TriceratopsVariant;
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers;
import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity;
import net.minecraft.Util;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class TriceratopsRenderer extends MobRenderer<TriceratopsEntity, TriceratopsRenderState, TriceratopsModel<TriceratopsEntity>> {
    private static final Map<TriceratopsVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(TriceratopsVariant.class), map -> {
                map.put(TriceratopsVariant.GRAY,
                        ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/entity/triceratops/triceratops_gray.png"));
                map.put(TriceratopsVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/entity/triceratops/triceratops_green.png"));
            });
    private final AdultAndBabyModelPair<TriceratopsModel<TriceratopsEntity>> model;


    public TriceratopsRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TriceratopsModel<>(pContext.bakeLayer(ModModelLayers.TRICERATOPS)), 0.86f);
        this.model = bakeModel(pContext);
    }

    private static AdultAndBabyModelPair<TriceratopsModel<TriceratopsEntity>> bakeModel(EntityRendererProvider.Context context) {
        TriceratopsModel<TriceratopsEntity> adult =
                new TriceratopsModel<>(context.bakeLayer(ModModelLayers.TRICERATOPS));
        TriceratopsModel<TriceratopsEntity> baby =
                new TriceratopsModel<>(context.bakeLayer(ModModelLayers.TRICERATOPS_BABY));
        return new AdultAndBabyModelPair<>(adult, baby);
    }


    @Override
    public ResourceLocation getTextureLocation(TriceratopsRenderState state) {
        return LOCATION_BY_VARIANT.get(state.getEntity().getVariant());
    }

    @Override
    public void render(TriceratopsRenderState pEntity, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        }else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public TriceratopsRenderState createRenderState() {
        return new TriceratopsRenderState();
    }

    @Override
    public void extractRenderState(TriceratopsEntity p_368665_, TriceratopsRenderState p_363057_, float p_364497_) {
        p_363057_.setEntity(p_368665_);
        super.extractRenderState(p_368665_, p_363057_, p_364497_);
    }
}
