package net.Chidoziealways.everythingjapanese.entity.client.ya;

import net.Chidoziealways.everythingjapanese.entity.custom.YaProjectileEntity;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class YaRenderState extends EntityRenderState {
    private float partialTicks;
    private YaProjectileEntity entity;

    public float getPartialTicks() {
        return partialTicks;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public YaProjectileEntity getEntity() {
        return entity;
    }

    public void setEntity(YaProjectileEntity entity) {
        this.entity = entity;
    }
}
