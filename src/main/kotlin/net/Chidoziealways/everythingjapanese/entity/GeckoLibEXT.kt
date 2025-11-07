package net.Chidoziealways.everythingjapanese.entity

import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.processing.AnimationController
import software.bernie.geckolib.animatable.processing.AnimationTest
import java.lang.reflect.Field

fun <T : GeoAnimatable> AnimationController<T>.isAnimationPlaying(): Boolean {
    return this.currentRawAnimation!= null &&
            (this.animationState == AnimationController.State.RUNNING || this.animationState == AnimationController.State.TRANSITIONING)
}

fun <T : GeoAnimatable> AnimationTest<T>.isAnimationPlaying(): Boolean {
    return controller.isAnimationPlaying()
}

fun <T : GeoAnimatable> AnimationController<T>.getAnimationTick(): Double {
    // Returns ticks instead of seconds
    val field: Field = AnimationController::class.java.getDeclaredField("processedAnimationTick")
    field.isAccessible = true
    return field.getDouble(this)
}

fun <T : GeoAnimatable> AnimationController<T>.getAnimationSeconds(): Double {
    return this.currentAnimationSeconds
}


fun <T : GeoAnimatable> AnimationTest<T>.getAnimationTick(): Double {
    return controller.getAnimationTick()
}

fun <T : GeoAnimatable> AnimationTest<T>.getAnimationSeconds(): Double {
    return controller.getAnimationSeconds()
}
