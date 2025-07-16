package net.Chidoziealways.everythingjapanese.entity.client.triceratops

import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.animation.KeyframeAnimations

object TriceratopsAnimations {
    val ANIM_TRICERATOPS_IDLE: AnimationDefinition = AnimationDefinition.Builder.withLength(2f).looping()
        .addAnimation(
            "tail",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(0f, 2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(0f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail2",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(0f, 5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(0f, -5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail3",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(0f, 7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail4",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(0f, 10f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(0f, -10f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail5",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(0f, 10f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(0f, -10f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "neck",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(-2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "head",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(-2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "jaw",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        ).build()
    val ANIM_TRICERATOPS_WALKING: AnimationDefinition = AnimationDefinition.Builder.withLength(4f).looping()
        .addAnimation(
            "body",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(2.5f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(2.5f, 2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(2.5f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(2.5f, 2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail2",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(5f, -5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(5f, 5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(5f, -5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(5f, 5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail3",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(5f, -5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(5f, 7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(5f, -5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(5f, 7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail4",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(7.5f, -7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(2.5f, 7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(7.5f, -7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(2.5f, 7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail5",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(-12.5f, -5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(-10f, 7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(-12.5f, -5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(-12.5f, 7.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "neck",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "head",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.posVec(-0.3f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.posVec(0.3f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.posVec(-0.3f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.posVec(0.1f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.posVec(0.3f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "jaw",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.5f, KeyframeAnimations.degreeVec(7.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.5f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "rearlegL",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.posVec(0f, 0.9f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.posVec(0f, 0.65f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.posVec(0f, 0.44f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.posVec(0f, 0.7f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.posVec(0f, 0.15f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "rearlegL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0.85f, -0.08f, -2.51f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0.75f, 0.01f, 0.91f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(1.36f, 0f, 1.12f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(1.63f, 0f, 0.39f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(-4.37f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "upperlegL",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "upperlegL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(40f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "lowerlegL",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "lowerlegL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(-0.62f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "footL",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "footL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(-30f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "armL",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, -0.1f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.posVec(0f, 0.2f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.posVec(0f, -0.12f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.posVec(0f, -0.13f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.posVec(0f, 0.39f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.posVec(0f, 0.19f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.posVec(0f, -0.23f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, -0.1f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "armL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(5.21f, 0.22f, -2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(4.98f, -0.02f, 0.19f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(-5.01f, -0.01f, 0.14f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(-3.9f, -0.01f, 0.13f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(-0.34f, -0.03f, -2.43f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(0.51f, -0.05f, 0.57f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(3.65f, -0.18f, 2.65f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "upperarmL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(-19.44f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "lowerarmL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(13.89f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "handL",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(10.56f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "rearlegR",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.posVec(0f, 0.1f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.posVec(0f, 0.46f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.posVec(0f, 0.29f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.posVec(0f, -0.2f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.posVec(0f, 0.7f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "rearlegR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(-2.28f, 0.21f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, -1.37f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(0f, 0f, 2f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(-5f, 0f, -2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(-2.28f, 0.21f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(-2.05f, 0.42f, 2.5f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(-2.28f, 0.21f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "upperlegR",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "upperlegR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(-25f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(-42.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(-40f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(-30f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(-25f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "lowerlegR",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "lowerlegR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "footR",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "footR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(37.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.degreeVec(30f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(30f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "armR",
            AnimationChannel(
                AnimationChannel.Targets.POSITION,
                Keyframe(
                    0f, KeyframeAnimations.posVec(0f, -0.3f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.posVec(0f, -0.18f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.posVec(0f, 0.36f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.posVec(0f, -0.13f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3f, KeyframeAnimations.posVec(0f, 0.04f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.posVec(0f, -0.01f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.posVec(0f, -0.3f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "armR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(-12.31f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.75f, KeyframeAnimations.degreeVec(-7.76f, 0.34f, 2.49f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(-6.57f, 0.02f, -0.28f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2.75f, KeyframeAnimations.degreeVec(-2.35f, -0.15f, -1.61f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    3.75f, KeyframeAnimations.degreeVec(3.77f, -0.16f, 2.47f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "upperarmR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(-20f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(-17.31f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "lowerarmR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(-2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(20f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(22.69f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(-2.5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "handR",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(7.31f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    4f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        ).build()
    val ANIM_TRICERATOPS_ATTACK: AnimationDefinition = AnimationDefinition.Builder.withLength(2f).looping()
        .addAnimation(
            "tail",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(2.47f, -4.96f, 0.65f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f, KeyframeAnimations.degreeVec(4.97f, -4.96f, 0.65f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail2",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(7.5f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f, KeyframeAnimations.degreeVec(7.5f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail3",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(7.5f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f, KeyframeAnimations.degreeVec(7.5f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail4",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f, KeyframeAnimations.degreeVec(7.52f, -7.5f, -0.22f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "tail5",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(-15f, -2.5f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f, KeyframeAnimations.degreeVec(-12.48f, -4.98f, -0.33f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "neck",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "head",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        )
        .addAnimation(
            "jaw",
            AnimationChannel(
                AnimationChannel.Targets.ROTATION,
                Keyframe(
                    0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    1f, KeyframeAnimations.degreeVec(30f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                ),
                Keyframe(
                    2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                    AnimationChannel.Interpolations.LINEAR
                )
            )
        ).build()
}
