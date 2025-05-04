package net.Chidoziealways.everythingjapanese.entity.client.ya;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.custom.YaProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class YaRenderer extends EntityRenderer<YaProjectileEntity, YaRenderState> {
    public YaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(YaRenderState state, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        ResourceLocation texture = determineTexture(state.getEntity());

        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(state.getPartialTicks(), state.getEntity().yRotO, state.getEntity().getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(state.getPartialTicks(), state.getEntity().xRotO, state.getEntity().getXRot())));
        int i = 0;
        float f = 0.0F;
        float f1 = 0.5F;
        float f2 = 0.0F;
        float f3 = 0.15625F;
        float f4 = 0.0F;
        float f5 = 0.15625F;
        float f6 = 0.15625F;
        float f7 = 0.3125F;
        float f8 = 0.05625F;
        float f9 = (float)state.getEntity().shakeTime - state.getPartialTicks();
        if (f9 > 0.0F) {
            float f10 = -Mth.sin(f9 * 3.0F) * f9;
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(f10));
        }

        pPoseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
        pPoseStack.scale(0.05625F, 0.05625F, 0.05625F);
        pPoseStack.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(texture));
        PoseStack.Pose posestack$pose = pPoseStack.last();
        this.vertex(posestack$pose, vertexconsumer, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, pPackedLight);

        for (int j = 0; j < 4; j++) {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.vertex(posestack$pose, vertexconsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, pPackedLight);
            this.vertex(posestack$pose, vertexconsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, pPackedLight);
            this.vertex(posestack$pose, vertexconsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, pPackedLight);
            this.vertex(posestack$pose, vertexconsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, pPackedLight);
        }

        pPoseStack.popPose();
        super.render(state, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public YaRenderState createRenderState() {
        return new YaRenderState();
    }

    @Override
    public void extractRenderState(YaProjectileEntity pEntity, YaRenderState pReusedState, float pPartialTick) {
        pReusedState.setEntity(pEntity);
        super.extractRenderState(pEntity, pReusedState, pPartialTick);
    }

    public void vertex(
            PoseStack.Pose pPose,
            VertexConsumer pConsumer,
            int pX,
            int pY,
            int pZ,
            float pU,
            float pV,
            int pNormalX,
            int pNormalY,
            int pNormalZ,
            int pPackedLight
    ) {
        pConsumer.addVertex(pPose, (float)pX, (float)pY, (float)pZ)
                .setColor(-1)
                .setUv(pU, pV)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(pPackedLight)
                .setNormal(pPose, (float)pNormalX, (float)pNormalZ, (float)pNormalY);
    }

    private ResourceLocation determineTexture(YaProjectileEntity entity) {
        // Logic to determine the appropriate texture based on the entity's state
        return ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/entity/ya/ya.png");
    }
}