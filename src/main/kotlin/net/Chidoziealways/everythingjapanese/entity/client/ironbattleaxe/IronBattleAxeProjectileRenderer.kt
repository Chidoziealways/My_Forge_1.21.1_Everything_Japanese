package net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.IronBattleAxeProjectileEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth

class IronBattleAxeProjectileRenderer(pContext: EntityRendererProvider.Context) :
    EntityRenderer<IronBattleAxeProjectileEntity, IronBattleAxeRenderState>(pContext) {
    private val model: IronBattleAxeProjectileModel

    init {
        this.model =
            IronBattleAxeProjectileModel(pContext.bakeLayer(IronBattleAxeProjectileModel.Companion.LAYER_LOCATION))
    }

    override fun submit(
        state: IronBattleAxeRenderState,
        pPoseStack: PoseStack,
        collector: SubmitNodeCollector,
        cameraRenderState: CameraRenderState
    ) {
        super.submit(state, pPoseStack, collector, cameraRenderState)

        val entity = state.getEntity() ?: return

        pPoseStack.pushPose()

        // Determine the texture for the entity
        val texture = determineTexture(entity)

        // Apply transformations based on whether the entity is grounded
        if (!entity.isGrounded) {
            pPoseStack.mulPose(
                Axis.YP.rotationDegrees(
                    Mth.lerp(
                        state.getpPartialTick(),
                        entity.yRotO,
                        entity.yRot
                    )
                )
            )
            pPoseStack.mulPose(Axis.XP.rotationDegrees(entity.renderingRotation * 5f))
            pPoseStack.translate(0f, -1.0f, 0f)
        } else {
            entity.groundedOffset?.let {
                pPoseStack.mulPose(Axis.YP.rotationDegrees(it.y))
                pPoseStack.mulPose(Axis.XP.rotationDegrees(it.x))
            }
            pPoseStack.translate(0f, -1.0f, 0f)
        }

        // Submit the model for rendering through the collector
        collector.submitCustomGeometry(pPoseStack, this.model.renderType(texture)) { _, consumer ->
            this.model.renderToBuffer(
                pPoseStack,
                consumer,
                state.lightCoords,          // use your render state's light coords
                OverlayTexture.NO_OVERLAY,
                0xFF11FFFF.toInt()
            )
        }

        pPoseStack.popPose()
    }


    private fun determineTexture(entity: IronBattleAxeProjectileEntity?): Identifier {
        // Logic to determine the appropriate texture based on the entity's state
        return Identifier.fromNamespaceAndPath(
            JAPANESE_MOD_ID,
            "textures/entity/ironbattleaxe/iron_battle_axe.png"
        )
    }


    override fun createRenderState(): IronBattleAxeRenderState {
        return IronBattleAxeRenderState()
    }

    override fun extractRenderState(
        pEntity: IronBattleAxeProjectileEntity,
        pReusedState: IronBattleAxeRenderState,
        pPartialTick: Float
    ) {
        pReusedState.setEntity(pEntity)
        super.extractRenderState(pEntity, pReusedState, pPartialTick)
    }
}
