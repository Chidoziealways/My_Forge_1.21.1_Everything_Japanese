package net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.resources.Identifier

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


class IronBattleAxeProjectileModel(root: ModelPart) : EntityModel<IronBattleAxeRenderState>(root) {
    private val tomahawk: ModelPart

    init {
        this.tomahawk = root.getChild("tomahawk")
    }

    override fun setupAnim(renderState: IronBattleAxeRenderState) {
    }

    companion object {
        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        val LAYER_LOCATION: ModelLayerLocation = ModelLayerLocation(
            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "iron_battle_axe"),
            "main"
        )

        fun createBodyLayer(): LayerDefinition {
            val meshdefinition = MeshDefinition()
            val partdefinition = meshdefinition.getRoot()

            val tomahawk = partdefinition.addOrReplaceChild(
                "tomahawk",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 16.5f, 0.0f)
            )

            val cube_r1 = tomahawk.addOrReplaceChild(
                "cube_r1",
                CubeListBuilder.create().texOffs(8, 7)
                    .addBox(1.5f, 2.5f, -0.5f, 1.0f, 1.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -7.0f, -4.0f, 0.0f, -1.5708f, 0.0f)
            )

            val cube_r2 = tomahawk.addOrReplaceChild(
                "cube_r2",
                CubeListBuilder.create().texOffs(7, 9)
                    .addBox(0.5f, -1.5f, -0.5f, 2.0f, 5.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -7.0f, -5.0f, 0.0f, -1.5708f, 0.0f)
            )

            val cube_r3 = tomahawk.addOrReplaceChild(
                "cube_r3",
                CubeListBuilder.create().texOffs(3, 10)
                    .addBox(-2.5f, -1.5f, -0.5f, 1.0f, 4.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -7.0f, 5.0f, 0.0f, -1.5708f, 0.0f)
            )

            val cube_r4 = tomahawk.addOrReplaceChild(
                "cube_r4",
                CubeListBuilder.create().texOffs(1, 4)
                    .addBox(-2.5f, -1.5f, 0.0f, 5.0f, 3.0f, 0.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -7.0f, 0.0f, 0.0f, -1.5708f, 0.0f)
            )

            val cube_r5 = tomahawk.addOrReplaceChild(
                "cube_r5",
                CubeListBuilder.create().texOffs(18, 1)
                    .addBox(-0.5f, -9.0f, -0.5f, 1.0f, 18.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -1.5f, 0.0f, 0.0f, -0.7854f, 0.0f)
            )

            return LayerDefinition.create(meshdefinition, 32, 32)
        }
    }
}