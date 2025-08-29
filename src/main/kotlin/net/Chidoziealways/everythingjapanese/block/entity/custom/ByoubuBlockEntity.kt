package net.Chidoziealways.everythingjapanese.block.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import software.bernie.geckolib.animatable.GeoBlockEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

class ByoubuBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModBlockEntities.BYOUBU_BE, pos, state), GeoBlockEntity{
    val CLOSE = RawAnimation.begin().thenPlay("close")
    val OPEN = RawAnimation.begin().thenPlayAndHold("open")

    private val cache = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = cache
}