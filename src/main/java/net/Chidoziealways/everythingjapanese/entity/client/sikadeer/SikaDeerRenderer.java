package net.Chidoziealways.everythingjapanese.entity.client.sikadeer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsModel;
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity;
import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SikaDeerRenderer extends MobRenderer<SikaDeerEntity, SikaDeerModel<SikaDeerEntity>> {
    public SikaDeerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SikaDeerModel<>(pContext.bakeLayer(SikaDeerModel.LAYER_LOCATION)), 0.86f);
    }

    @Override
    public ResourceLocation getTextureLocation(SikaDeerEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/entity/sikadeer/sika_deer.png");
    }

    @Override
    public void render(SikaDeerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.7f, 0.7f, 0.7f);
        }else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

}
