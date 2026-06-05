package net.Chidoziealways.everythingjapanese.block.entity.custom

import com.geckolib.animatable.GeoBlockEntity
import com.geckolib.animatable.instance.AnimatableInstanceCache
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.RawAnimation
import com.geckolib.util.GeckoLibUtil
import net.Chidoziealways.everythingjapanese.block.custom.FusumaDoorBlock
import net.Chidoziealways.everythingjapanese.block.custom.ShojiDoorBlock
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoorHingeSide

class FusumaDoorBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModBlockEntities.FUSUMA_DOOR_BE, pos, state),
    GeoBlockEntity {
    val OPEN_LEFT: RawAnimation = RawAnimation.begin().thenPlay("open_left")
    val CLOSE_LEFT: RawAnimation = RawAnimation.begin().thenPlay("close_left")
    val OPEN_RIGHT: RawAnimation = RawAnimation.begin().thenPlay("open_right")
    val CLOSE_RIGHT: RawAnimation = RawAnimation.begin().thenPlay("close_right")

    private val cache = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                "controller",
                0,
                AnimationController.AnimationStateHandler { event ->
                    val open = blockState.getValue(FusumaDoorBlock.OPEN) ?: false
                    val hinge = blockState.getValue(FusumaDoorBlock.HINGE)
                    return@AnimationStateHandler if (open && hinge == DoorHingeSide.LEFT) {
                        event.setAndContinue(OPEN_LEFT)
                    } else if (!open && hinge == DoorHingeSide.LEFT) {
                        event.setAndContinue(CLOSE_LEFT)
                    } else if (open && hinge == DoorHingeSide.RIGHT) {
                        event.setAndContinue(OPEN_RIGHT)
                    } else {
                        event.setAndContinue(CLOSE_RIGHT)
                    }
                })
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

}