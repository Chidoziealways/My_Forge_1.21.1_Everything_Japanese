package net.Chidoziealways.everythingjapanese.entity.client.triceratops;


import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/**
 * Render state for the TriceratopsEntity.
 * Holds data necessary for rendering the TriceratopsEntity.
 * @author Chidozie Derek Chidozie-Uzowulu
 */
public class TriceratopsRenderState extends LivingEntityRenderState {
    private TriceratopsEntity entity;
    private float limbSwing;
    private float limbSwingAmount;
    private float ageInTicks;
    private float netHeadYaw;
    private float headPitch;

    /**
     * Sets the associated TriceratopsEntity.
     *
     * @param entity The TriceratopsEntity instance.
     */
    public void setEntity(TriceratopsEntity entity) {
        this.entity = entity;
    }

    /**
     * Retrieves the associated TriceratopsEntity.
     *
     * @return The TriceratopsEntity instance.
     */
    public TriceratopsEntity getEntity() {
        return this.entity;
    }

    /**
     * Determines if the associated TriceratopsEntity is a baby.
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
