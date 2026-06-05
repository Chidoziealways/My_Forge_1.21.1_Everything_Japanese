package net.Chidoziealways.everythingjapanese.entity.client.aspiration

import com.mojang.blaze3d.vertex.PoseStack
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.AspirationEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.resources.Identifier
import net.minecraft.world.phys.Vec3
import kotlin.math.sin

class AspirationRenderer(context: EntityRendererProvider.Context): EntityRenderer<AspirationEntity, AspirationRenderState>(context) {
    private val texture = Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/entity/aspiration/aspiration.png")

    override fun submit(
        state: AspirationRenderState,
        poseStack: PoseStack,
        collector: SubmitNodeCollector,
        cameraState: CameraRenderState
    ) {
        poseStack.pushPose()
        // float the talisman a bit
        poseStack.translate(0.0, 0.5 + sin(state.entity!!.tickCount * 0.1) * 0.05, 0.0)
        // rotate to face the camera
        poseStack.mulPose(cameraState.orientation)

        collector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(texture)) { pose, builder ->
            val color = 0xFFFFFFFF.toInt()    // white, full opacity

            builder.addVertex(-0.25f, -0.25f, 0f)
                .setColor(color)
                .setUv(0f, 1f)
                .setUv2(0, 0)  // <-- UV2 required
                .setOverlay(0)
                .setNormal(0f, 0f, 1f)

            builder.addVertex(0.25f, -0.25f, 0f)
                .setColor(color)
                .setUv(1f, 1f)
                .setUv2(0, 0)
                .setOverlay(0)
                .setNormal(0f, 0f, 1f)

            builder.addVertex(0.25f, 0.25f, 0f)
                .setColor(color)
                .setUv(1f, 0f)
                .setUv2(0, 0)
                .setOverlay(0)
                .setNormal(0f, 0f, 1f)


            builder.addVertex(-0.25f, 0.25f, 0f)
                .setColor(color)
                .setUv(0f, 0f)
                .setUv2(0, 0)
                .setOverlay(0)
                .setNormal(0f, 0f, 1f)
        }

        poseStack.popPose()
    }


    override fun createRenderState(): AspirationRenderState {
        return AspirationRenderState()
    }

    override fun extractRenderState(entity: AspirationEntity, reusedState: AspirationRenderState, partialTick: Float) {
        reusedState.entity = entity
        super.extractRenderState(entity, reusedState, partialTick)
    }
}