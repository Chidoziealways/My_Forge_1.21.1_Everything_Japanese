package net.Chidoziealways.everythingjapanese.entity.client.triceratops

import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity
import net.minecraft.client.animation.KeyframeAnimation
import net.minecraft.client.model.BabyModelTransform
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.*
import net.minecraft.util.Mth
import java.util.function.Consumer

class TriceratopsModel<T : TriceratopsEntity?>(root: ModelPart) : EntityModel<TriceratopsRenderState?>(root) {
    private val walkAnimation: KeyframeAnimation
    private val idleAnimation: KeyframeAnimation

    private val body: ModelPart
    private val head: ModelPart

    init {
        this.body = root.getChild("body")
        this.head = body.getChild("upper").getChild("neck").getChild("head")
        walkAnimation = TriceratopsAnimations.ANIM_TRICERATOPS_WALKING.bake(root)
        idleAnimation = TriceratopsAnimations.ANIM_TRICERATOPS_IDLE.bake(root)
    }

    override fun setupAnim(state: TriceratopsRenderState?) {
        super.setupAnim(state!!)
        this.root().getAllParts().forEach(Consumer { obj: ModelPart? -> obj!!.resetPose() })
        this.applyHeadRotation(state.netHeadYaw, state.headPitch)
        this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0f, 2.5f)
        this.idleAnimation.apply(state.entity!!.idleAnimationState, state.ageInTicks)
    }

    private fun applyHeadRotation(pNetHeadYaw: Float, pHeadPitch: Float) {
        var pNetHeadYaw = pNetHeadYaw
        var pHeadPitch = pHeadPitch
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, 30.0f, 30.0f)
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0f, 45.0f)

        this.head.yRot = pNetHeadYaw * (Math.PI.toFloat() / 180f)
        this.head.xRot = pHeadPitch * (Math.PI.toFloat() / 180f)
    }

    companion object {
        val BABY_TRANSFORMER: MeshTransformer = BabyModelTransform(true, 8.0f, 6.0f, mutableSetOf<String?>("head"))
        fun createBodyLayer(): LayerDefinition {
            val meshdefinition = MeshDefinition()
            val partdefinition = meshdefinition.getRoot()

            val body = partdefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.0f, 9.0f, 6.0f, 0.0436f, 0.0f, 0.0f)
            )

            val lower = body.addOrReplaceChild(
                "lower",
                CubeListBuilder.create().texOffs(72, 103)
                    .addBox(-7.0f, -5.5f, -6.6667f, 14.0f, 11.0f, 14.0f, CubeDeformation(0.0f))
                    .texOffs(78, 0).addBox(-6.0f, -6.5f, -6.6667f, 12.0f, 1.0f, 13.0f, CubeDeformation(0.0f))
                    .texOffs(78, 44).addBox(-6.0f, 5.5f, -6.6667f, 12.0f, 1.0f, 13.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, 0.5f, -0.3333f)
            )

            val rearlegL = lower.addOrReplaceChild(
                "rearlegL",
                CubeListBuilder.create().texOffs(31, 54)
                    .addBox(-1.0f, -3.5f, -4.0f, 3.0f, 7.0f, 8.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(7.0f, -1.0f, 1.3333f, -0.0438f, -0.0872f, 0.0038f)
            )

            val upperlegL = rearlegL.addOrReplaceChild(
                "upperlegL",
                CubeListBuilder.create().texOffs(70, 63)
                    .addBox(-1.5f, -0.5f, -3.0f, 3.0f, 9.0f, 6.0f, CubeDeformation(-0.01f)),
                PartPose.offsetAndRotation(0.5f, 2.0f, -1.0f, -0.3491f, 0.0f, 0.0f)
            )

            val lowerlegL = upperlegL.addOrReplaceChild(
                "lowerlegL",
                CubeListBuilder.create().texOffs(30, 23)
                    .addBox(-1.5f, -0.5f, -2.5f, 3.0f, 6.0f, 5.0f, CubeDeformation(-0.02f)),
                PartPose.offsetAndRotation(0.0f, 8.0f, -0.5f, 0.3491f, 0.0f, 0.0f)
            )

            val footL = lowerlegL.addOrReplaceChild(
                "footL",
                CubeListBuilder.create().texOffs(73, 33)
                    .addBox(-1.5f, 0.0f, -3.0f, 3.0f, 2.0f, 6.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, 4.2f, -0.5f)
            )

            val rearlegR = lower.addOrReplaceChild(
                "rearlegR",
                CubeListBuilder.create().texOffs(46, 62).mirror()
                    .addBox(-2.0f, -3.5f, -4.0f, 3.0f, 7.0f, 8.0f, CubeDeformation(0.0f)).mirror(false),
                PartPose.offsetAndRotation(-7.0f, -1.0f, 1.3333f, -0.0436f, 0.0873f, 0.0f)
            )

            val upperlegR = rearlegR.addOrReplaceChild(
                "upperlegR",
                CubeListBuilder.create().texOffs(89, 63).mirror()
                    .addBox(-1.5f, -0.5f, -3.0f, 3.0f, 9.0f, 6.0f, CubeDeformation(-0.01f)).mirror(false),
                PartPose.offsetAndRotation(-0.5f, 2.0f, -1.0f, 0.4363f, 0.0f, 0.0f)
            )

            val lowerlegR = upperlegR.addOrReplaceChild(
                "lowerlegR",
                CubeListBuilder.create().texOffs(14, 54).mirror()
                    .addBox(-1.5f, -0.5f, -2.5f, 3.0f, 6.0f, 5.0f, CubeDeformation(-0.02f)).mirror(false),
                PartPose.offsetAndRotation(0.0f, 8.0f, -0.5f, 0.0873f, 0.0f, 0.0f)
            )

            val footR = lowerlegR.addOrReplaceChild(
                "footR",
                CubeListBuilder.create().texOffs(80, 93).mirror()
                    .addBox(-1.5f, 0.7f, -3.0f, 3.0f, 2.0f, 6.0f, CubeDeformation(0.0f)).mirror(false),
                PartPose.offsetAndRotation(0.0f, 3.5f, -0.5f, -0.5236f, 0.0f, 0.0f)
            )

            val tail = lower.addOrReplaceChild(
                "tail",
                CubeListBuilder.create().texOffs(0, 67)
                    .addBox(-5.0f, -4.0f, -1.0f, 10.0f, 8.0f, 6.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 0.0f, 7.3333f, -0.1745f, 0.0f, 0.0f)
            )

            val tail2 = tail.addOrReplaceChild(
                "tail2",
                CubeListBuilder.create().texOffs(18, 36)
                    .addBox(-4.0f, -4.0f, -1.0f, 8.0f, 8.0f, 8.0f, CubeDeformation(-0.01f)),
                PartPose.offsetAndRotation(0.0f, 0.0f, 5.0f, -0.2182f, 0.0f, 0.0f)
            )

            val tail3 = tail2.addOrReplaceChild(
                "tail3",
                CubeListBuilder.create().texOffs(100, 71)
                    .addBox(-3.0f, -3.5f, -1.0f, 6.0f, 7.0f, 8.0f, CubeDeformation(-0.02f)),
                PartPose.offsetAndRotation(0.0f, -0.5f, 7.0f, -0.1745f, 0.0f, 0.0f)
            )

            val tail4 = tail3.addOrReplaceChild(
                "tail4",
                CubeListBuilder.create().texOffs(52, 40)
                    .addBox(-2.0f, -3.0f, -1.0f, 4.0f, 6.0f, 9.0f, CubeDeformation(-0.03f)),
                PartPose.offsetAndRotation(0.0f, -0.5f, 7.0f, -0.0873f, 0.0f, 0.0f)
            )

            val tail5 = tail4.addOrReplaceChild(
                "tail5",
                CubeListBuilder.create().texOffs(0, 20)
                    .addBox(-2.0f, -2.5f, -1.0f, 4.0f, 5.0f, 9.0f, CubeDeformation(-0.04f)),
                PartPose.offsetAndRotation(0.0f, -0.5f, 8.0f, 0.3491f, 0.0f, 0.0f)
            )

            val upper = body.addOrReplaceChild(
                "upper",
                CubeListBuilder.create().texOffs(0, 103)
                    .addBox(-7.0f, -5.5f, -12.0f, 14.0f, 11.0f, 14.0f, CubeDeformation(-0.1f))
                    .texOffs(80, 30).addBox(-6.0f, -6.3f, -11.0f, 12.0f, 1.0f, 12.0f, CubeDeformation(-0.1f))
                    .texOffs(80, 16).addBox(-6.0f, 5.3f, -11.0f, 12.0f, 1.0f, 12.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(0.0f, 0.5f, -7.0f, -0.0436f, 0.0f, 0.0f)
            )

            val armL = upper.addOrReplaceChild(
                "armL",
                CubeListBuilder.create().texOffs(0, 46)
                    .addBox(-1.5f, -3.0f, -2.5f, 3.0f, 6.0f, 5.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(7.0f, 1.0f, -8.5f, 0.0f, -0.0873f, 0.0f)
            )

            val upperarmL = armL.addOrReplaceChild(
                "upperarmL",
                CubeListBuilder.create().texOffs(61, 57)
                    .addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, CubeDeformation(-0.01f)),
                PartPose.offsetAndRotation(0.0f, 1.5f, 0.5f, 0.3491f, 0.0f, 0.0f)
            )

            val lowerarmL = upperarmL.addOrReplaceChild(
                "lowerarmL",
                CubeListBuilder.create().texOffs(115, 107)
                    .addBox(-1.5f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f, CubeDeformation(-0.02f)),
                PartPose.offsetAndRotation(0.0f, 4.5f, -0.5f, -0.6109f, 0.0f, 0.0f)
            )

            val handL = lowerarmL.addOrReplaceChild(
                "handL",
                CubeListBuilder.create().texOffs(47, 82)
                    .addBox(-1.5f, 0.0f, -2.5f, 3.0f, 2.0f, 4.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 5.5f, 0.0f, 0.2618f, 0.0f, 0.0f)
            )

            val armR = upper.addOrReplaceChild(
                "armR",
                CubeListBuilder.create().texOffs(112, 59).mirror()
                    .addBox(-1.5f, -3.0f, -2.5f, 3.0f, 6.0f, 5.0f, CubeDeformation(0.0f)).mirror(false),
                PartPose.offsetAndRotation(-7.0f, 1.0f, -8.5f, 0.0f, 0.0873f, 0.0f)
            )

            val upperarmR = armR.addOrReplaceChild(
                "upperarmR",
                CubeListBuilder.create().texOffs(0, 89).mirror()
                    .addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, CubeDeformation(-0.01f)).mirror(false),
                PartPose.offsetAndRotation(0.0f, 1.5f, 0.5f, 0.3491f, 0.0f, 0.0f)
            )

            val lowerarmR = upperarmR.addOrReplaceChild(
                "lowerarmR",
                CubeListBuilder.create().texOffs(86, 81).mirror()
                    .addBox(-1.5f, 0.0f, -1.5f, 3.0f, 6.0f, 3.0f, CubeDeformation(-0.02f)).mirror(false),
                PartPose.offsetAndRotation(0.0f, 4.5f, -0.5f, -0.6109f, 0.0f, 0.0f)
            )

            val handR = lowerarmR.addOrReplaceChild(
                "handR",
                CubeListBuilder.create().texOffs(0, 82).mirror()
                    .addBox(-1.5f, 0.0f, -2.5f, 3.0f, 2.0f, 4.0f, CubeDeformation(0.0f)).mirror(false),
                PartPose.offsetAndRotation(0.0f, 5.5f, 0.0f, 0.2618f, 0.0f, 0.0f)
            )

            val neck = upper.addOrReplaceChild(
                "neck",
                CubeListBuilder.create().texOffs(56, 99)
                    .addBox(-4.0f, -4.0f, -4.2f, 8.0f, 8.0f, 5.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 0.0f, -11.8f, 0.1309f, 0.0f, 0.0f)
            )

            val head = neck.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(18, 82)
                    .addBox(-5.0f, -5.0475f, -8.0281f, 10.0f, 10.0f, 8.0f, CubeDeformation(0.0f))
                    .texOffs(100, 87).addBox(-3.0f, -4.0475f, -16.0281f, 6.0f, 7.0f, 8.0f, CubeDeformation(0.0f))
                    .texOffs(1, 107).addBox(-2.0f, -3.0475f, -17.0281f, 4.0f, 7.0f, 2.0f, CubeDeformation(0.0f))
                    .texOffs(16, 86).addBox(-1.5f, 3.9525f, -17.0281f, 3.0f, 1.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -0.8525f, -3.1719f, -0.0873f, 0.0f, 0.0f)
            )

            val HornL3_r1 = head.addOrReplaceChild(
                "HornL3_r1",
                CubeListBuilder.create().texOffs(9, 95).mirror()
                    .addBox(-0.5f, -0.5f, -5.0f, 1.0f, 1.0f, 6.0f, CubeDeformation(0.0f)).mirror(false),
                PartPose.offsetAndRotation(-4.2f, -7.6475f, -8.8281f, -0.3531f, 0.0578f, -0.073f)
            )

            val HornL2_r1 = head.addOrReplaceChild(
                "HornL2_r1",
                CubeListBuilder.create().texOffs(34, 71).mirror()
                    .addBox(-0.5f, -0.5f, -4.0f, 1.0f, 1.0f, 8.0f, CubeDeformation(0.2f)).mirror(false),
                PartPose.offsetAndRotation(-3.5f, -5.8475f, -5.0281f, -0.4437f, 0.1382f, -0.1069f)
            )

            val HornL2_r2 = head.addOrReplaceChild(
                "HornL2_r2",
                CubeListBuilder.create().texOffs(9, 95)
                    .addBox(-0.5f, -0.5f, -5.0f, 1.0f, 1.0f, 6.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(4.2f, -7.6475f, -8.8281f, -0.3531f, -0.0578f, 0.073f)
            )

            val HornL1_r1 = head.addOrReplaceChild(
                "HornL1_r1",
                CubeListBuilder.create().texOffs(34, 71)
                    .addBox(-0.5f, -0.5f, -4.0f, 1.0f, 1.0f, 8.0f, CubeDeformation(0.2f)),
                PartPose.offsetAndRotation(3.5f, -5.8475f, -5.0281f, -0.4437f, -0.1382f, 0.1069f)
            )

            val snouthorn_r1 = head.addOrReplaceChild(
                "snouthorn_r1",
                CubeListBuilder.create().texOffs(122, 72)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -4.8475f, -15.5281f, 0.2618f, 0.0f, 0.0f)
            )

            val crest3_r1 = head.addOrReplaceChild(
                "crest3_r1",
                CubeListBuilder.create().texOffs(58, 113)
                    .addBox(-2.5f, -8.0f, -0.5f, 5.0f, 14.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-7.5f, -3.0475f, 0.4719f, -0.1314f, 0.0865f, -0.0114f)
            )

            val crest2_r1 = head.addOrReplaceChild(
                "crest2_r1",
                CubeListBuilder.create().texOffs(43, 101)
                    .addBox(-2.5f, -8.0f, -0.5f, 5.0f, 14.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(7.5f, -3.0475f, 0.4719f, -0.1314f, -0.0865f, 0.0114f)
            )

            val crestspikeR1_r1 = head.addOrReplaceChild(
                "crestspikeR1_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false)
                    .texOffs(24, 13).addBox(2.3f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(-1.4f, -14.1432f, 1.7371f, -0.1309f, 0.0f, 0.0f)
            )

            val crestspikeR4_r1 = head.addOrReplaceChild(
                "crestspikeR4_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-8.4f, -10.2432f, 1.5371f, -0.1314f, 0.0865f, -0.0114f)
            )

            val crestspikeR2_r1 = head.addOrReplaceChild(
                "crestspikeR2_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false)
                    .texOffs(24, 13).addBox(6.3f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(-3.4f, -13.6432f, 1.6371f, -0.1309f, 0.0f, 0.0f)
            )

            val crestspikeR3_r1 = head.addOrReplaceChild(
                "crestspikeR3_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-6.4f, -10.7432f, 1.3371f, -0.1314f, 0.0865f, -0.0114f)
            )

            val crestspikeR5_r1 = head.addOrReplaceChild(
                "crestspikeR5_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-9.4f, -9.2432f, 1.4371f, -0.0873f, -0.1309f, -1.5708f)
            )

            val crestspikeR6_r1 = head.addOrReplaceChild(
                "crestspikeR6_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-9.9f, -7.2432f, 1.2371f, -0.0873f, -0.1309f, -1.5708f)
            )

            val crestspikeR7_r1 = head.addOrReplaceChild(
                "crestspikeR7_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-10.4f, -5.2432f, 0.9371f, -0.0873f, -0.1309f, -1.5708f)
            )

            val crestspikeR8_r1 = head.addOrReplaceChild(
                "crestspikeR8_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-10.4f, -3.2432f, 0.7371f, -0.0873f, -0.1309f, -1.5708f)
            )

            val crestspikeR9_r1 = head.addOrReplaceChild(
                "crestspikeR9_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-9.9f, -0.9432f, 0.3371f, -0.0873f, -0.1309f, -1.5708f)
            )

            val crestspikeR10_r1 = head.addOrReplaceChild(
                "crestspikeR10_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-9.4f, 1.2568f, 0.0371f, -0.0873f, -0.1309f, -1.5708f)
            )

            val crestspikeR12_r1 = head.addOrReplaceChild(
                "crestspikeR12_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-6.4f, 2.2568f, -0.3629f, -0.1314f, 0.0865f, -0.0114f)
            )

            val crestspikeR11_r1 = head.addOrReplaceChild(
                "crestspikeR11_r1",
                CubeListBuilder.create().texOffs(24, 13).mirror()
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(-8.4f, 2.2568f, -0.1629f, -0.1314f, 0.0865f, -0.0114f)
            )

            val crestspikeL12_r1 = head.addOrReplaceChild(
                "crestspikeL12_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(6.4f, 2.2568f, -0.3629f, -0.1314f, -0.0865f, 0.0114f)
            )

            val crestspikeL11_r1 = head.addOrReplaceChild(
                "crestspikeL11_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(8.4f, 2.2568f, -0.1629f, -0.1314f, -0.0865f, 0.0114f)
            )

            val crestspikeL10_r1 = head.addOrReplaceChild(
                "crestspikeL10_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(9.4f, 1.2568f, 0.0371f, -0.0873f, 0.1309f, 1.5708f)
            )

            val crestspikeL9_r1 = head.addOrReplaceChild(
                "crestspikeL9_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(9.9f, -0.9432f, 0.3371f, -0.0873f, 0.1309f, 1.5708f)
            )

            val crestspikeL8_r1 = head.addOrReplaceChild(
                "crestspikeL8_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(10.4f, -3.2432f, 0.7371f, -0.0873f, 0.1309f, 1.5708f)
            )

            val crestspikeL7_r1 = head.addOrReplaceChild(
                "crestspikeL7_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(10.4f, -5.2432f, 0.9371f, -0.0873f, 0.1309f, 1.5708f)
            )

            val crestspikeL6_r1 = head.addOrReplaceChild(
                "crestspikeL6_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(9.9f, -7.2432f, 1.2371f, -0.0873f, 0.1309f, 1.5708f)
            )

            val crestspikeL5_r1 = head.addOrReplaceChild(
                "crestspikeL5_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(9.4f, -9.2432f, 1.4371f, -0.0873f, 0.1309f, 1.5708f)
            )

            val crestspikeL4_r1 = head.addOrReplaceChild(
                "crestspikeL4_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(8.4f, -10.2432f, 1.5371f, -0.1314f, -0.0865f, 0.0114f)
            )

            val crestspikeL3_r1 = head.addOrReplaceChild(
                "crestspikeL3_r1",
                CubeListBuilder.create().texOffs(24, 13)
                    .addBox(-0.5f, -1.5f, -0.5f, 1.0f, 3.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(6.4f, -10.7432f, 1.3371f, -0.1314f, -0.0865f, 0.0114f)
            )

            val crest1_r1 = head.addOrReplaceChild(
                "crest1_r1",
                CubeListBuilder.create().texOffs(62, 79)
                    .addBox(-5.0f, -10.0f, -0.5f, 10.0f, 17.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -4.0475f, 0.3719f, -0.1309f, 0.0f, 0.0f)
            )

            val jaw = head.addOrReplaceChild(
                "jaw",
                CubeListBuilder.create().texOffs(59, 19)
                    .addBox(-2.0f, -0.1f, -8.0f, 4.0f, 2.0f, 11.0f, CubeDeformation(-0.1f))
                    .texOffs(2, 60).addBox(-1.5f, -1.2f, -8.2f, 3.0f, 3.0f, 2.0f, CubeDeformation(-0.1f))
                    .texOffs(121, 92).addBox(-1.0f, -2.0f, -8.2f, 2.0f, 1.0f, 1.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(0.0f, 2.9525f, -8.0281f, 0.0873f, 0.0f, 0.0f)
            )

            return LayerDefinition.create(meshdefinition, 128, 128)
        }
    }
}
