package net.Chidoziealways.everythingjapanese.entity.client.sikadeer;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Set;

public class SikaDeerModel<T extends SikaDeerEntity> extends EntityModel<SikaDeerRenderState> {
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true, 8.0F, 6.0F, Set.of("head"));

	private final ModelPart root;
	private final ModelPart Body;
	private final ModelPart Tail;
	private final ModelPart Neck;
	private final ModelPart Head;
	private final ModelPart Antlers;
	private final ModelPart AntlerRight;
	private final ModelPart AntlerLeft;
	private final ModelPart Legs;
	private final ModelPart FrontLeftLeg;
	private final ModelPart FrontLeftLowerLeg;
	private final ModelPart BottomLeftLeg;
	private final ModelPart BottomLeftLowerLeg;
	private final ModelPart FrontRightLeg;
	private final ModelPart FrontRightLowerLeg;
	private final ModelPart BottomRightLeg;
	private final ModelPart BottomRightLowerLeg;

	public SikaDeerModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
		this.Body = this.root.getChild("Body");
		this.Tail = this.Body.getChild("Tail");
		this.Neck = this.root.getChild("Neck");
		this.Head = this.Neck.getChild("Head");
		this.Antlers = this.Head.getChild("Antlers");
		this.AntlerRight = this.Antlers.getChild("AntlerRight");
		this.AntlerLeft = this.Antlers.getChild("AntlerLeft");
		this.Legs = this.root.getChild("Legs");
		this.FrontLeftLeg = this.Legs.getChild("FrontLeftLeg");
		this.FrontLeftLowerLeg = this.FrontLeftLeg.getChild("FrontLeftLowerLeg");
		this.BottomLeftLeg = this.Legs.getChild("BottomLeftLeg");
		this.BottomLeftLowerLeg = this.BottomLeftLeg.getChild("BottomLeftLowerLeg");
		this.FrontRightLeg = this.Legs.getChild("FrontRightLeg");
		this.FrontRightLowerLeg = this.FrontRightLeg.getChild("FrontRightLowerLeg");
		this.BottomRightLeg = this.Legs.getChild("BottomRightLeg");
		this.BottomRightLowerLeg = this.BottomRightLeg.getChild("BottomRightLowerLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -44.05F, 0.0F, 8.0F, 16.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(62, 40).addBox(-5.0F, -39.0F, 24.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Neck = root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 53).addBox(-2.1F, -0.05F, 0.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -54.0F, 0.0F));

		PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 40).addBox(-2.1F, -6.05F, -4.0F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(-1.0F, -3.1F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Antlers = Head.addOrReplaceChild("Antlers", CubeListBuilder.create(), PartPose.offset(4.0F, 54.0F, 0.0F));

		PartDefinition AntlerRight = Antlers.addOrReplaceChild("AntlerRight", CubeListBuilder.create(), PartPose.offset(-5.0F, -60.1F, 0.0F));

		PartDefinition ar_r1 = AntlerRight.addOrReplaceChild("ar_r1", CubeListBuilder.create().texOffs(46, 57).addBox(-3.0F, -6.1F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.9F, 0.0F, 0.0873F, 0.9599F, 0.0873F));

		PartDefinition AntlerLeft = Antlers.addOrReplaceChild("AntlerLeft", CubeListBuilder.create(), PartPose.offset(0.0F, -60.1F, 0.0F));

		PartDefinition al_r1 = AntlerLeft.addOrReplaceChild("al_r1", CubeListBuilder.create().texOffs(54, 57).addBox(-1.0F, -7.0F, 1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0873F, 0.9599F, 0.0873F));

		PartDefinition Legs = root.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition FrontLeftLeg = Legs.addOrReplaceChild("FrontLeftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, -28.0F, 0.0F));

		PartDefinition Up_r1 = FrontLeftLeg.addOrReplaceChild("Up_r1", CubeListBuilder.create().texOffs(22, 40).addBox(0.0F, -28.05F, -3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition FrontLeftLowerLeg = FrontLeftLeg.addOrReplaceChild("FrontLeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition Down_r1 = FrontLeftLowerLeg.addOrReplaceChild("Down_r1", CubeListBuilder.create().texOffs(14, 53).addBox(-3.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 14.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition BottomLeftLeg = Legs.addOrReplaceChild("BottomLeftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, -28.0F, 21.0F));

		PartDefinition Up_r2 = BottomLeftLeg.addOrReplaceChild("Up_r2", CubeListBuilder.create().texOffs(52, 40).addBox(0.0F, -28.05F, -3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition BottomLeftLowerLeg = BottomLeftLeg.addOrReplaceChild("BottomLeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition Down_r2 = BottomLeftLowerLeg.addOrReplaceChild("Down_r2", CubeListBuilder.create().texOffs(38, 57).addBox(-3.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 14.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition FrontRightLeg = Legs.addOrReplaceChild("FrontRightLeg", CubeListBuilder.create(), PartPose.offset(-6.0F, -28.0F, 0.0F));

		PartDefinition Up_r3 = FrontRightLeg.addOrReplaceChild("Up_r3", CubeListBuilder.create().texOffs(32, 40).addBox(0.0F, -28.05F, -3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition FrontRightLowerLeg = FrontRightLeg.addOrReplaceChild("FrontRightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition Down_r3 = FrontRightLowerLeg.addOrReplaceChild("Down_r3", CubeListBuilder.create().texOffs(22, 57).addBox(-3.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 14.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition BottomRightLeg = Legs.addOrReplaceChild("BottomRightLeg", CubeListBuilder.create(), PartPose.offset(-6.0F, -28.0F, 21.0F));

		PartDefinition Up_r4 = BottomRightLeg.addOrReplaceChild("Up_r4", CubeListBuilder.create().texOffs(42, 40).addBox(0.0F, -28.05F, -3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition BottomRightLowerLeg = BottomRightLeg.addOrReplaceChild("BottomRightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition Down_r4 = BottomRightLowerLeg.addOrReplaceChild("Down_r4", CubeListBuilder.create().texOffs(30, 57).addBox(-3.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 14.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(SikaDeerRenderState state) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(state.getNetHeadYaw(), state.getHeadPitch());

		this.animateWalk(SikaDeerAnimations.WALK, state.getLimbSwing(), state.getLimbSwingAmount(), 2f, 2.5f);
		this.animate(state.getEntity().idleAnimationState, SikaDeerAnimations.IDLE, state.getAgeInTicks(), 1f);
		super.setupAnim(state);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch){
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, 30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.Head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.Head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
}