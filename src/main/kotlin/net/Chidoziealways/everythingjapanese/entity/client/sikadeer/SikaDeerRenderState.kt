package net.Chidoziealways.everythingjapanese.entity.client.sikadeer

import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.world.entity.AnimationState

/**
 * Render state for the SikaDeerEntity.
 * Holds data necessary for rendering the SikaDeerEntity.
 * @author Chidozie Derek Chidozie-Uzowulu
 */
class SikaDeerRenderState : LivingEntityRenderState() {
    val idleAnimationState: AnimationState = AnimationState()
    /**
     * Retrieves the associated SikaDeerEntity.
     *
     * @return The SikaDeerEntity instance.
     */
    /**
     * Sets the associated SikaDeerEntity.
     *
     * @param entity The SikaDeerEntity instance.
     */
    var entity: SikaDeerEntity? = null
    var limbSwing: Float = 0f
    var limbSwingAmount: Float = 0f
    var ageInTicks: Float = 0f
    var netHeadYaw: Float = 0f
    var headPitch: Float = 0f

    val isBaby: Boolean
        /**
         * Determines if the associated SikaDeerEntity is a baby.
         *
         * @return True if the entity is a baby; otherwise, false.
         */
        get() = entity != null && entity!!.isBaby()
}