package net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe;

import net.Chidoziealways.everythingjapanese.entity.custom.IronBattleAxeProjectileEntity;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronBattleAxeRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
    public float shake;
    private IronBattleAxeProjectileEntity entity;
    private float pPartialTick;

    public IronBattleAxeProjectileEntity getEntity() {
        return entity;
    }

    public float getpPartialTick() {
        return pPartialTick;
    }

    public void setEntity(IronBattleAxeProjectileEntity entity) {
        this.entity = entity;
    }
}
