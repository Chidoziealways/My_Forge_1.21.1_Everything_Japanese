package net.Chidoziealways.everythingjapanese.entity.client.sikadeer;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class SikaDeerAnimations {

    public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.0f).looping()
            .addAnimation("front_left",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.0f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.0f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("front_right",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.0f, KeyframeAnimations.degreeVec(30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.0f, KeyframeAnimations.degreeVec(30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("back_left",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.0f, KeyframeAnimations.degreeVec(30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.0f, KeyframeAnimations.degreeVec(30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("back_right",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.0f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.0f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .build();

    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(1.0f).looping()
            .addAnimation("neck",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(5.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0.0f, 5.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .build();
}
