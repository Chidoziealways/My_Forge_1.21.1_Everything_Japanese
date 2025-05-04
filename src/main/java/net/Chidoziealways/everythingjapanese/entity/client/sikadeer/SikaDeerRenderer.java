package net.Chidoziealways.everythingjapanese.entity.client.sikadeer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers;
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SikaDeerRenderer extends MobRenderer<SikaDeerEntity, SikaDeerRenderState, SikaDeerModel<SikaDeerEntity>> {
    private final AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity>> models;

    public SikaDeerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SikaDeerModel<>(pContext.bakeLayer(ModModelLayers.SIKA_DEER)), 0.86f);
        this.models = bakeModel(pContext);
    }

    private static AdultAndBabyModelPair<SikaDeerModel<SikaDeerEntity>> bakeModel(EntityRendererProvider.Context context) {
        // Assume you have two ModelLayerLocations defined somewhere:
        //   SIKA_DEER_ADULT_LAYER  and  SIKA_DEER_BABY_LAYER
        SikaDeerModel<SikaDeerEntity> adult =
                new SikaDeerModel<>(context.bakeLayer(ModModelLayers.SIKA_DEER));
        SikaDeerModel<SikaDeerEntity> baby  =
                new SikaDeerModel<>(context.bakeLayer(ModModelLayers.SIKA_DEER_BABY));

        return new AdultAndBabyModelPair<>(adult, baby);
    }


    @Override
    public SikaDeerRenderState createRenderState() {
        return new SikaDeerRenderState();
    }

    @Override
    public void extractRenderState(SikaDeerEntity entity, SikaDeerRenderState renderState, float partialTicks) {
        renderState.setEntity(entity);
        super.extractRenderState(entity, renderState, partialTicks);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SikaDeerRenderState pEntity) {
        return ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/entity/sikadeer/sika_deer.png");
    }

    @Override
    public void render(SikaDeerRenderState sikaDeerRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115313_) {
        if (sikaDeerRenderState.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
            this.shadowRadius = 0.35f;
        } else {
            poseStack.scale(1.0f, 1.0f, 1.0f);
            this.shadowRadius = 0.7f;
        }
        super.render(sikaDeerRenderState, poseStack, multiBufferSource, p_115313_);
    }

}
