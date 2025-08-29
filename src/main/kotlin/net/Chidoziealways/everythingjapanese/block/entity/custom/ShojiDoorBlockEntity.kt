package net.Chidoziealways.everythingjapanese.block.entity.custom

import net.Chidoziealways.everythingjapanese.block.custom.ShojiDoorBlock
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoorHingeSide
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.GeoBlockEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animatable.processing.AnimationController
import software.bernie.geckolib.animatable.processing.AnimationState
import software.bernie.geckolib.animatable.processing.AnimationTest
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

class ShojiDoorBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModBlockEntities.SHOJI_DOOR_BE, pos, state), GeoBlockEntity, GeoAnimatable{
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
                AnimationController.AnimationStateHandler{ event ->
                    val open = blockState.getValue(ShojiDoorBlock.OPEN) ?: false
                    val hinge = blockState.getValue(ShojiDoorBlock.HINGE)
                    return@AnimationStateHandler if (open && hinge == DoorHingeSide.LEFT) {
                        event.setAndContinue(OPEN_LEFT)
                    } else if (!open && hinge == DoorHingeSide.LEFT) {
                        event.setAndContinue(CLOSE_LEFT)
                    } else if (open && hinge == DoorHingeSide.RIGHT) {
                        event.setAndContinue(OPEN_RIGHT)
                    } else {
                        event.setAndContinue(CLOSE_RIGHT)
                    }
                }))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return cache
    }

}