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

public class IronBattleAxeProjectileRenderer extends EntityRenderer<IronBattleAxeProjectileEntity, IronBattleAxeRenderState> {
    private IronBattleAxeProjectileModel model;

    public IronBattleAxeProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new IronBattleAxeProjectileModel(pContext.bakeLayer(IronBattleAxeProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(IronBattleAxeRenderState state, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {
        pPoseStack.pushPose();

        // Determine the texture based on the entity's state
        ResourceLocation texture = determineTexture(state.getEntity());

        // Apply transformations based on the entity's movement
        if (!state.getEntity().isGrounded()) {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(state.getpPartialTick(), state.getEntity().yRotO, state.getEntity().getYRot())));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(state.getEntity().getRenderingRotation() * 5f));
            pPoseStack.translate(0, -1.0f, 0);
        } else {
            pPoseStack.mulPose(Axis.YP.rotationDegrees((float) state.getEntity().groundedOffset.y));
            pPoseStack.mulPose(Axis.XP.rotationDegrees((float) state.getEntity().groundedOffset.x));
            pPoseStack.translate(0, -1.0f, 0);
        }

        // Render the model with the determined texture
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(
                pBufferSource, this.model.renderType(texture), false, false);
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY);

        pPoseStack.popPose();

        super.render(state, pPoseStack, pBufferSource, pPackedLight);
    }

    private ResourceLocation determineTexture(IronBattleAxeProjectileEntity entity) {
        // Logic to determine the appropriate texture based on the entity's state
        return ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "textures/entity/ironbattleaxe/iron_battle_axe.png");
    }


    @Override
    public IronBattleAxeRenderState createRenderState() {
        return new IronBattleAxeRenderState();
    }

    @Override
    public void extractRenderState(IronBattleAxeProjectileEntity pEntity, IronBattleAxeRenderState pReusedState, float pPartialTick) {
        pReusedState.setEntity(pEntity);
        super.extractRenderState(pEntity, pReusedState, pPartialTick);
    }
}
