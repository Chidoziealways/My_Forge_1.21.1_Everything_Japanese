package net.Chidoziealways.everythingjapanese.entity.client.sikadeer

import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity
import net.minecraft.client.animation.KeyframeAnimation
import net.minecraft.client.model.BabyModelTransform
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.*
import net.minecraft.util.Mth
import java.util.function.Consumer

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


class SikaDeerModel<T : SikaDeerEntity?>(root: ModelPart) : EntityModel<SikaDeerRenderState?>(root) {
    private val walkAnimation: KeyframeAnimation
    private val idleAnimation: KeyframeAnimation

    private val root: ModelPart
    private val Body: ModelPart
    private val Tail: ModelPart
    private val Neck: ModelPart
    private val Head: ModelPart
    private val Antlers: ModelPart
    private val AntlerRight: ModelPart
    private val AntlerLeft: ModelPart
    private val Legs: ModelPart
    private val FrontLeftLeg: ModelPart
    private val FrontLeftLowerLeg: ModelPart
    private val BottomLeftLeg: ModelPart
    private val BottomLeftLowerLeg: ModelPart
    private val FrontRightLeg: ModelPart
    private val FrontRightLowerLeg: ModelPart
    private val BottomRightLeg: ModelPart
    private val BottomRightLowerLeg: ModelPart

    init {
        this.root = root.getChild("root")
        this.Body = this.root.getChild("Body")
        this.Tail = this.Body.getChild("Tail")
        this.Neck = this.root.getChild("Neck")
        this.Head = this.Neck.getChild("Head")
        this.Antlers = this.Head.getChild("Antlers")
        this.AntlerRight = this.Antlers.getChild("AntlerRight")
        this.AntlerLeft = this.Antlers.getChild("AntlerLeft")
        this.Legs = this.root.getChild("Legs")
        this.FrontLeftLeg = this.Legs.getChild("FrontLeftLeg")
        this.FrontLeftLowerLeg = this.FrontLeftLeg.getChild("FrontLeftLowerLeg")
        this.BottomLeftLeg = this.Legs.getChild("BottomLeftLeg")
        this.BottomLeftLowerLeg = this.BottomLeftLeg.getChild("BottomLeftLowerLeg")
        this.FrontRightLeg = this.Legs.getChild("FrontRightLeg")
        this.FrontRightLowerLeg = this.FrontRightLeg.getChild("FrontRightLowerLeg")
        this.BottomRightLeg = this.Legs.getChild("BottomRightLeg")
        this.BottomRightLowerLeg = this.BottomRightLeg.getChild("BottomRightLowerLeg")
        walkAnimation = SikaDeerAnimations.WALK.bake(root)
        idleAnimation = SikaDeerAnimations.IDLE.bake(root)
    }

    override fun setupAnim(state: SikaDeerRenderState?) {
        super.setupAnim(state!!)
        this.root().getAllParts().forEach(Consumer { obj: ModelPart? -> obj!!.resetPose() })
        this.applyHeadRotation(state!!.netHeadYaw, state.headPitch)
        this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0f, 2.5f)
        this.idleAnimation.apply(state.entity!!.idleAnimationState, state.ageInTicks)
    }

    private fun applyHeadRotation(pNetHeadYaw: Float, pHeadPitch: Float) {
        var pNetHeadYaw = pNetHeadYaw
        var pHeadPitch = pHeadPitch
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, 30.0f, 30.0f)
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0f, 45.0f)

        this.Head.yRot = pNetHeadYaw * (Math.PI.toFloat() / 180f)
        this.Head.xRot = pHeadPitch * (Math.PI.toFloat() / 180f)
    }

    companion object {
        private const val MAX_WALK_ANIMATION_SPEED = 2.0f
        private const val WALK_ANIMATION_SCALE_FACTOR = 2.5f
        val BABY_TRANSFORMER: MeshTransformer = BabyModelTransform(true, 8.0f, 6.0f, mutableSetOf<String?>("head"))
        fun createBodyLayer(): LayerDefinition {
            val meshdefinition = MeshDefinition()
            val partdefinition = meshdefinition.getRoot()

            val root =
                partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0f, 24.0f, 0.0f))

            val Body = root.addOrReplaceChild(
                "Body",
                CubeListBuilder.create().texOffs(0, 0)
                    .addBox(-8.0f, -44.05f, 0.0f, 8.0f, 16.0f, 24.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )

            val Tail = Body.addOrReplaceChild(
                "Tail",
                CubeListBuilder.create().texOffs(62, 40)
                    .addBox(-5.0f, -39.0f, 24.0f, 2.0f, 2.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )

            val Neck = root.addOrReplaceChild(
                "Neck",
                CubeListBuilder.create().texOffs(0, 53)
                    .addBox(-2.1f, -0.05f, 0.0f, 4.0f, 10.0f, 3.0f, CubeDeformation(0.0f)),
                PartPose.offset(-4.0f, -54.0f, 0.0f)
            )

            val Head = Neck.addOrReplaceChild(
                "Head",
                CubeListBuilder.create().texOffs(0, 40)
                    .addBox(-2.1f, -6.05f, -4.0f, 4.0f, 6.0f, 7.0f, CubeDeformation(0.0f))
                    .texOffs(1, 1).addBox(-1.0f, -3.1f, -5.0f, 2.0f, 1.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )

            val Antlers =
                Head.addOrReplaceChild("Antlers", CubeListBuilder.create(), PartPose.offset(4.0f, 54.0f, 0.0f))

            val AntlerRight =
                Antlers.addOrReplaceChild("AntlerRight", CubeListBuilder.create(), PartPose.offset(-5.0f, -60.1f, 0.0f))

            val ar_r1 = AntlerRight.addOrReplaceChild(
                "ar_r1",
                CubeListBuilder.create().texOffs(46, 57)
                    .addBox(-3.0f, -6.1f, -2.0f, 2.0f, 8.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -0.9f, 0.0f, 0.0873f, 0.9599f, 0.0873f)
            )

            val AntlerLeft =
                Antlers.addOrReplaceChild("AntlerLeft", CubeListBuilder.create(), PartPose.offset(0.0f, -60.1f, 0.0f))

            val al_r1 = AntlerLeft.addOrReplaceChild(
                "al_r1",
                CubeListBuilder.create().texOffs(54, 57)
                    .addBox(-1.0f, -7.0f, 1.0f, 2.0f, 8.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-3.0f, 0.0f, 0.0f, 0.0873f, 0.9599f, 0.0873f)
            )

            val Legs = root.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0f, 0.0f, 0.0f))

            val FrontLeftLeg =
                Legs.addOrReplaceChild("FrontLeftLeg", CubeListBuilder.create(), PartPose.offset(0.0f, -28.0f, 0.0f))

            val Up_r1 = FrontLeftLeg.addOrReplaceChild(
                "Up_r1",
                CubeListBuilder.create().texOffs(22, 40)
                    .addBox(0.0f, -28.05f, -3.0f, 2.0f, 14.0f, 3.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 28.0f, 0.0f, 0.0f, 3.1416f, 0.0f)
            )

            val FrontLeftLowerLeg = FrontLeftLeg.addOrReplaceChild(
                "FrontLeftLowerLeg",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 14.0f, 0.0f)
            )

            val Down_r1 = FrontLeftLowerLeg.addOrReplaceChild(
                "Down_r1",
                CubeListBuilder.create().texOffs(14, 53)
                    .addBox(-3.0f, -14.0f, -1.0f, 2.0f, 14.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-1.0f, 14.0f, 0.0f, 0.0f, 1.5708f, 0.0f)
            )

            val BottomLeftLeg =
                Legs.addOrReplaceChild("BottomLeftLeg", CubeListBuilder.create(), PartPose.offset(0.0f, -28.0f, 21.0f))

            val Up_r2 = BottomLeftLeg.addOrReplaceChild(
                "Up_r2",
                CubeListBuilder.create().texOffs(52, 40)
                    .addBox(0.0f, -28.05f, -3.0f, 2.0f, 14.0f, 3.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 28.0f, 0.0f, 0.0f, 3.1416f, 0.0f)
            )

            val BottomLeftLowerLeg = BottomLeftLeg.addOrReplaceChild(
                "BottomLeftLowerLeg",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 14.0f, 0.0f)
            )

            val Down_r2 = BottomLeftLowerLeg.addOrReplaceChild(
                "Down_r2",
                CubeListBuilder.create().texOffs(38, 57)
                    .addBox(-3.0f, -14.0f, -1.0f, 2.0f, 14.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-1.0f, 14.0f, 0.0f, 0.0f, 1.5708f, 0.0f)
            )

            val FrontRightLeg =
                Legs.addOrReplaceChild("FrontRightLeg", CubeListBuilder.create(), PartPose.offset(-6.0f, -28.0f, 0.0f))

            val Up_r3 = FrontRightLeg.addOrReplaceChild(
                "Up_r3",
                CubeListBuilder.create().texOffs(32, 40)
                    .addBox(0.0f, -28.05f, -3.0f, 2.0f, 14.0f, 3.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 28.0f, 0.0f, 0.0f, 3.1416f, 0.0f)
            )

            val FrontRightLowerLeg = FrontRightLeg.addOrReplaceChild(
                "FrontRightLowerLeg",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 14.0f, 0.0f)
            )

            val Down_r3 = FrontRightLowerLeg.addOrReplaceChild(
                "Down_r3",
                CubeListBuilder.create().texOffs(22, 57)
                    .addBox(-3.0f, -14.0f, -1.0f, 2.0f, 14.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-1.0f, 14.0f, 0.0f, 0.0f, 1.5708f, 0.0f)
            )

            val BottomRightLeg = Legs.addOrReplaceChild(
                "BottomRightLeg",
                CubeListBuilder.create(),
                PartPose.offset(-6.0f, -28.0f, 21.0f)
            )

            val Up_r4 = BottomRightLeg.addOrReplaceChild(
                "Up_r4",
                CubeListBuilder.create().texOffs(42, 40)
                    .addBox(0.0f, -28.05f, -3.0f, 2.0f, 14.0f, 3.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 28.0f, 0.0f, 0.0f, 3.1416f, 0.0f)
            )

            val BottomRightLowerLeg = BottomRightLeg.addOrReplaceChild(
                "BottomRightLowerLeg",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 14.0f, 0.0f)
            )

            val Down_r4 = BottomRightLowerLeg.addOrReplaceChild(
                "Down_r4",
                CubeListBuilder.create().texOffs(30, 57)
                    .addBox(-3.0f, -14.0f, -1.0f, 2.0f, 14.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-1.0f, 14.0f, 0.0f, 0.0f, 1.5708f, 0.0f)
            )

            return LayerDefinition.create(meshdefinition, 128, 128)
        }
    }
}