package net.Chidoziealways.everythingjapanese.entity.client.sikadeer;

import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/**
 * Render state for the SikaDeerEntity.
 * Holds data necessary for rendering the SikaDeerEntity.
 * @author Chidozie Derek Chidozie-Uzowulu
 */
public class SikaDeerRenderState extends LivingEntityRenderState {
    private SikaDeerEntity entity;
    private float limbSwing;
    private float limbSwingAmount;
    private float ageInTicks;
    private float netHeadYaw;
    private float headPitch;

    /**
     * Sets the associated SikaDeerEntity.
     *
     * @param entity The SikaDeerEntity instance.
     */
    public void setEntity(SikaDeerEntity entity) {
        this.entity = entity;
    }

    /**
     * Retrieves the associated SikaDeerEntity.
     *
     * @return The SikaDeerEntity instance.
     */
    public SikaDeerEntity getEntity() {
        return this.entity;
    }

    /**
     * Determines if the associated SikaDeerEntity is a baby.
     *
     * @return True if the entity is a baby; otherwise, false.
     */
    public boolean isBaby() {
        return entity != null && entity.isBaby();
    }

    public float getLimbSwing() {
        return limbSwing;
    }

    public void setLimbSwing(float limbSwing) {
        this.limbSwing = limbSwing;
    }

    public float getLimbSwingAmount() {
        return limbSwingAmount;
    }

    public void setLimbSwingAmount(float limbSwingAmount) {
        this.limbSwingAmount = limbSwingAmount;
    }

    public float getAgeInTicks() {
        return ageInTicks;
    }

    public void setAgeInTicks(float ageInTicks) {
        this.ageInTicks = ageInTicks;
    }

    public float getNetHeadYaw() {
        return netHeadYaw;
    }

    public void setNetHeadYaw(float netHeadYaw) {
        this.netHeadYaw = netHeadYaw;
    }

    public float getHeadPitch() {
        return headPitch;
    }

    public void setHeadPitch(float headPitch) {
        this.headPitch = headPitch;
    }
}
