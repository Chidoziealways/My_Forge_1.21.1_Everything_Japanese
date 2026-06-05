package net.Chidoziealways.everythingjapanese.entity

import com.geckolib.animatable.GeoAnimatable
import com.geckolib.animation.AnimationController
import com.geckolib.animation.`object`.PlayState
import com.geckolib.animation.state.AnimationTest
import java.lang.reflect.Field

fun <T : GeoAnimatable> AnimationController<T>.isAnimationPlaying(): Boolean {
    return this.currentRawAnimation!= null &&
            (this.playState == PlayState.PAUSE || this.playState == PlayState.CONTINUE)
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
    return this.getAnimationSeconds()
}


fun <T : GeoAnimatable> AnimationTest<T>.getAnimationTick(): Double {
    return controller.getAnimationTick()
}

fun <T : GeoAnimatable> AnimationTest<T>.getAnimationSeconds(): Double {
    return controller.getAnimationSeconds()
}
