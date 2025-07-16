package net.Chidoziealways.everythingjapanese.entity.client.sikadeer

import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.animation.KeyframeAnimations

// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 4.12.3
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author uzowu
 */
object SikaDeerAnimations {
    val WALK: AnimationDefinition = AnimationDefinition.Builder.withLength(0.75f).looping()
        .addAnimation(
            "Head", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.125f,
                    KeyframeAnimations.degreeVec(0.0f, -7.5f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.25f,
                    KeyframeAnimations.degreeVec(0.0f, 12.5f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    KeyframeAnimations.degreeVec(0.0f, 2.5f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.625f,
                    KeyframeAnimations.degreeVec(-2.5f, 2.5f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "AntlerRight", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.4583f,
                    KeyframeAnimations.degreeVec(0.0f, 5.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "AntlerLeft", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.4583f,
                    KeyframeAnimations.degreeVec(0.0f, -2.5f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "FrontLeftLeg", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.5f,
                    KeyframeAnimations.degreeVec(-10.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(0.75f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)
            )
        )
        .addAnimation(
            "BottomLeftLeg", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.25f,
                    KeyframeAnimations.degreeVec(-10.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "FrontRightLeg", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.25f,
                    KeyframeAnimations.degreeVec(-10.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(0.75f, KeyframeAnimations.degreeVec(-2.5f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)
            )
        )
        .addAnimation(
            "BottomRightLeg", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.5f,
                    KeyframeAnimations.degreeVec(-10.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(0.75f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)
            )
        )
        .build()

    val IDLE: AnimationDefinition = AnimationDefinition.Builder.withLength(0.4583f).looping()
        .addAnimation(
            "Neck", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(7.5f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.1667f,
                    KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "Head", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.3333f,
                    KeyframeAnimations.degreeVec(0.0f, -12.5f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4583f,
                    KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "AntlerLeft", AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
                Keyframe(
                    0.4583f,
                    KeyframeAnimations.degreeVec(0.0f, -2.5f, 0.0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .build()
}