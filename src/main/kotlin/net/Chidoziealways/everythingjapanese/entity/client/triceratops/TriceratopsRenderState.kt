package net.Chidoziealways.everythingjapanese.entity.client.triceratops

import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.world.entity.AnimationState


/**
 * Render state for the TriceratopsEntity.
 * Holds data necessary for rendering the TriceratopsEntity.
 * @author Chidozie Derek Chidozie-Uzowulu
 */
class TriceratopsRenderState : LivingEntityRenderState() {
    val idleAnimationState: AnimationState = AnimationState()
    /**
     * Retrieves the associated TriceratopsEntity.
     *
     * @return The TriceratopsEntity instance.
     */
    /**
     * Sets the associated TriceratopsEntity.
     *
     * @param entity The TriceratopsEntity instance.
     */
    var entity: TriceratopsEntity? = null
    var limbSwing: Float = 0f
    var limbSwingAmount: Float = 0f
    var ageInTicks: Float = 0f
    var netHeadYaw: Float = 0f
    var headPitch: Float = 0f

    val isBaby: Boolean
        /**
         * Determines if the associated TriceratopsEntity is a baby.
         *
         * @return True if the entity is a baby; otherwise, false.
         */
        get() = entity != null && entity!!.isBaby()
}
