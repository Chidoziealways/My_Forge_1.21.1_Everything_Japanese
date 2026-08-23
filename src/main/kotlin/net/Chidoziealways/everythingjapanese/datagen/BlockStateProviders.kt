package net.Chidoziealways.everythingjapanese.datagen

import com.mojang.math.Quadrant
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.block.custom.TsukubaiBlock
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.BlockModelGenerators.plainVariant
import net.minecraft.client.data.models.MultiVariant
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator
import net.minecraft.client.data.models.blockstates.PropertyDispatch
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.client.data.models.model.TextureMapping
import net.minecraft.client.data.models.model.TextureSlot
import net.minecraft.core.Direction
import net.minecraft.resources.Identifier
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.neoforged.neoforge.client.model.generators.blockstate.CompositeBlockStateModelBuilder

/*fun BlockModelGenerators.createCalligraphyTable() {
    val textureMapping = TextureMapping()
        .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side3"))
        .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(Blocks.DARK_OAK_PLANKS))
        .put(TextureSlot.UP, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_top"))
        .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side3"))
        .put(TextureSlot.EAST, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side3"))
        .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side1"))
        .put(TextureSlot.WEST, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side2"))
    this.blockStateOutput
        .accept(
            BlockModelGenerators.createSimpleBlock(
                JModBlocks.CALLIGRAPHY_TABLE,
                plainVariant(ModelTemplates.CUBE.create(JModBlocks.CALLIGRAPHY_TABLE, textureMapping, this.modelOutput))
            )
        )
}*/

fun BlockModelGenerators.createTsukubai() {
    val tsukubai = JModBlocks.TSUKUBAI
    this.blockStateOutput.accept(
        MultiVariantGenerator.dispatch(tsukubai)
            .with(PropertyDispatch.initial(TsukubaiBlock.FILLED)
                .select(false, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/tsukubai")))
                .select(true, plainVariant(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/tsukubai_water")))
            )

    )
}