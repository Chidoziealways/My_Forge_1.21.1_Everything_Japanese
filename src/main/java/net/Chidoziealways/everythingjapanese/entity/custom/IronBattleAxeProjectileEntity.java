package net.Chidoziealways.everythingjapanese.entity.custom;

import net.Chidoziealways.everythingjapanese.entity.ModEntities;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;

public class IronBattleAxeProjectileEntity extends AbstractArrow {
    private float rotation;
    public Vec2 groundedOffset;

    public IronBattleAxeProjectileEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public IronBattleAxeProjectileEntity(LivingEntity shooter, Level level) {
        super(ModEntities.IRON_BATTLE_AXE.get(), shooter, level, new ItemStack(ModItems.IRON_BATTLE_AXE.get()), null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.IRON_BATTLE_AXE.get());
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if(result.getDirection() == Direction.SOUTH) {
            groundedOffset = new Vec2(215f,180f);
        }
        if(result.getDirection() == Direction.NORTH) {
            groundedOffset = new Vec2(215f, 0f);
        }
        if(result.getDirection() == Direction.EAST) {
            groundedOffset = new Vec2(215f,-90f);
        }
        if(result.getDirection() == Direction.WEST) {
            groundedOffset = new Vec2(215f,90f);
        }

        if(result.getDirection() == Direction.DOWN) {
            groundedOffset = new Vec2(115f,180f);
        }
        if(result.getDirection() == Direction.UP) {
            groundedOffset = new Vec2(285f,180f);
        }
    }
}
