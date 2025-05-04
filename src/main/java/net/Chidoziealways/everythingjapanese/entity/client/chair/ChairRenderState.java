package net.Chidoziealways.everythingjapanese.entity.client.chair;

import net.Chidoziealways.everythingjapanese.entity.custom.ChairEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ChairRenderState extends LivingEntityRenderState {
    ChairEntity entity;

    public ChairEntity getEntity() {
        return entity;
    }

    public void setEntity(ChairEntity entity) {
        this.entity = entity;
    }
}
