package net.Chidoziealways.everythingjapanese.entity.client.sikadeer;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SikaDeerModel<T extends SikaDeerEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "sika_deer"), "main");
	private final ModelPart SikaDeer;
	private final ModelPart Body;
	private final ModelPart Legs;
	private final ModelPart Leg1;
	private final ModelPart Bottom;
	private final ModelPart TOp;
	private final ModelPart Leg2;
	private final ModelPart Bottom2;
	private final ModelPart Top2;
	private final ModelPart Leg3;
	private final ModelPart Bottom3;
	private final ModelPart Top3;
	private final ModelPart Leg4;
	private final ModelPart Bottom4;
	private final ModelPart Top4;
	private final ModelPart Neck;
	private final ModelPart Head;
	private final ModelPart Antlers;
	private final ModelPart Antler1;
	private final ModelPart Antler2;
	private final ModelPart Nose;
	private final ModelPart Ears;
	private final ModelPart Tail;
	private final ModelPart tail1;

	public SikaDeerModel(ModelPart root) {
		this.SikaDeer = root.getChild("SikaDeer");
		this.Body = this.SikaDeer.getChild("Body");
		this.Legs = this.SikaDeer.getChild("Legs");
		this.Leg1 = this.Legs.getChild("Leg1");
		this.Bottom = this.Leg1.getChild("Bottom");
		this.TOp = this.Leg1.getChild("TOp");
		this.Leg2 = this.Legs.getChild("Leg2");
		this.Bottom2 = this.Leg2.getChild("Bottom2");
		this.Top2 = this.Leg2.getChild("Top2");
		this.Leg3 = this.Legs.getChild("Leg3");
		this.Bottom3 = this.Leg3.getChild("Bottom3");
		this.Top3 = this.Leg3.getChild("Top3");
		this.Leg4 = this.Legs.getChild("Leg4");
		this.Bottom4 = this.Leg4.getChild("Bottom4");
		this.Top4 = this.Leg4.getChild("Top4");
		this.Neck = this.SikaDeer.getChild("Neck");
		this.Head = this.SikaDeer.getChild("Head");
		this.Antlers = this.Head.getChild("Antlers");
		this.Antler1 = this.Antlers.getChild("Antler1");
		this.Antler2 = this.Antlers.getChild("Antler2");
		this.Nose = this.Head.getChild("Nose");
		this.Ears = this.Head.getChild("Ears");
		this.Tail = this.SikaDeer.getChild("Tail");
		this.tail1 = this.Tail.getChild("tail1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition SikaDeer = partdefinition.addOrReplaceChild("SikaDeer", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Body = SikaDeer.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body_r1 = Body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-23.0F, -29.0F, 1.0F, 24.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -15.0F, -14.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Legs = SikaDeer.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Leg1 = Legs.addOrReplaceChild("Leg1", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 3.0F));
		PartDefinition Bottom = Leg1.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(34, 41).addBox(2.0F, -14.0F, 4.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition TOp = Leg1.addOrReplaceChild("TOp", CubeListBuilder.create().texOffs(22, 24).addBox(2.0F, -28.0F, 3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Leg2 = Legs.addOrReplaceChild("Leg2", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, -18.0F));
		PartDefinition Bottom2 = Leg2.addOrReplaceChild("Bottom2", CubeListBuilder.create().texOffs(50, 24).addBox(2.0F, -14.0F, 4.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Top2 = Leg2.addOrReplaceChild("Top2", CubeListBuilder.create().texOffs(24, 41).addBox(2.0F, -28.0F, 3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Leg3 = Legs.addOrReplaceChild("Leg3", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, -18.0F));
		PartDefinition Bottom3 = Leg3.addOrReplaceChild("Bottom3", CubeListBuilder.create().texOffs(42, 24).addBox(2.0F, -14.0F, 4.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Top3 = Leg3.addOrReplaceChild("Top3", CubeListBuilder.create().texOffs(32, 24).addBox(2.0F, -28.0F, 3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Leg4 = Legs.addOrReplaceChild("Leg4", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 3.0F));
		PartDefinition Bottom4 = Leg4.addOrReplaceChild("Bottom4", CubeListBuilder.create().texOffs(42, 40).addBox(2.0F, -14.0F, 4.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Top4 = Leg4.addOrReplaceChild("Top4", CubeListBuilder.create().texOffs(0, 37).addBox(2.0F, -28.0F, 3.0F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Neck = SikaDeer.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(10, 41).addBox(0.0F, -44.0F, -21.52F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 8.0F));
		PartDefinition Head = SikaDeer.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -56.0F, -13.52F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -4.0F));
		PartDefinition Antlers = Head.addOrReplaceChild("Antlers", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Antler1 = Antlers.addOrReplaceChild("Antler1", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 1.0F));
		PartDefinition cube_r1 = Antler1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(50, 52).addBox(-6.0F, -2.0F, -0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0783F, -57.6771F, -13.02F, 0.0F, 0.0F, 1.8326F));
		PartDefinition cube_r2 = Antler1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(50, 49).addBox(-6.0F, -2.0F, -0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9063F, -54.4226F, -13.02F, 0.0F, 0.0F, 0.4363F));
		PartDefinition Antler2 = Antlers.addOrReplaceChild("Antler2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 1.0F, -24.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = Antler2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 54).addBox(-21.4548F, 2.1411F, -0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0783F, -41.6771F, -13.02F, 0.0F, 0.0F, 1.8326F));
		PartDefinition cube_r4 = Antler2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(50, 46).addBox(-12.7619F, -16.5009F, -0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9063F, -38.4226F, -13.02F, 0.0F, 0.0F, 0.4363F));
		PartDefinition Nose = Head.addOrReplaceChild("Nose", CubeListBuilder.create().texOffs(10, 37).addBox(1.0F, -53.0F, -14.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Ears = Head.addOrReplaceChild("Ears", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Tail = SikaDeer.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(6.0F, 0.0F, 4.0F));
		PartDefinition tail1 = Tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(50, 40).addBox(-5.5F, -41.0F, 5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	@Override
	public void setupAnim(SikaDeerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(SikaDeerAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, SikaDeerAnimations.IDLE, ageInTicks, 1f);
	}

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch){
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, 30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.Head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.Head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }


    @Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		SikaDeer.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

    @Override
    public ModelPart root() {
        return SikaDeer;
    }

}