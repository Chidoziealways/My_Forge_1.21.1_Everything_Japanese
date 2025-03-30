package net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.custom.IronBattleAxeProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class IronBattleAxeProjectileRenderer extends EntityRenderer<IronBattleAxeProjectileEntity> {
    private IronBattleAxeProjectileModel model;

    public IronBattleAxeProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new IronBattleAxeProjectileModel(pContext.bakeLayer(IronBattleAxeProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(IronBattleAxeProjectileEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {
        pPoseStack.pushPose();

        if (!pEntity.isGrounded()) {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.yRotO, pEntity.getYRot())));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getRenderingRotation() * 5f));
            pPoseStack.translate(0, -1.0f, 0);
        } else {
            pPoseStack.mulPose(Axis.YP.rotationDegrees((float) pEntity.groundedOffset.y));
            pPoseStack.mulPose(Axis.XP.rotationDegrees((float) pEntity.groundedOffset.x));
            pPoseStack.translate(0, -1.0f, 0);
        }

        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(
                pBufferSource, this.model.renderType(this.getTextureLocation(pEntity)), false, false);
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBufferSource, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(IronBattleAxeProjectileEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/entity/ironbattleaxe/iron_battle_axe.png");
    }
}
