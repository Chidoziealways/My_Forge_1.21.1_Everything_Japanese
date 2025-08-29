package net.Chidoziealways.everythingjapanese.block.entity.renderer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.Chidoziealways.everythingjapanese.block.custom.ShojiDoorBlock
import net.Chidoziealways.everythingjapanese.block.entity.custom.FusumaDoorBlockEntity
import net.Chidoziealways.everythingjapanese.block.entity.custom.ShojiDoorBlockEntity
import net.Chidoziealways.everythingjapanese.block.model.FusumaDoorModel
import net.Chidoziealways.everythingjapanese.block.model.ShojiDoorModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.Direction
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.renderer.GeoBlockRenderer

class FusumaDoorRenderer(ctx: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<FusumaDoorBlockEntity>(FusumaDoorModel()) {
}
