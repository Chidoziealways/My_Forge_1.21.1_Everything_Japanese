package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.block.custom.MoneyVaultBlock
import net.Chidoziealways.everythingjapanese.block.custom.MoneyVaultBlock.VaultSection
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.BlockModelGenerators.plainVariant
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator
import net.minecraft.client.data.models.blockstates.PropertyDispatch
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.client.data.models.model.TextureMapping
import net.minecraft.client.data.models.model.TextureSlot
import net.minecraft.client.data.models.model.TexturedModel
import net.minecraft.client.resources.model.sprite.Material
import net.minecraft.resources.Identifier

fun BlockModelGenerators.createMoneyVault() {
    val block = JModBlocks.MONEY_VAULT_BLOCK

    // Base models
    val singleModel = TexturedModel.CUBE.create(block, modelOutput)

    val topLeftFront = ModelTemplates.CUBE.createWithSuffix(block, "_top_left_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_right")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val topLeftMiddle = TexturedModel.CUBE_TOP.updateTexture {
        it.put(TextureSlot.SIDE, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
    }.createWithSuffix(block, "_top_left_middle", modelOutput)

    val topLeftBack = ModelTemplates.CUBE.createWithSuffix(block, "_top_left_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_right")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_right")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val topRightFront = ModelTemplates.CUBE.createWithSuffix(block, "_top_right_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_right")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_right")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val topRightMiddle = TexturedModel.CUBE_TOP.updateTexture {
        it.put(TextureSlot.SIDE, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
    }.createWithSuffix(block, "_top_right_middle", modelOutput)

    val topRightBack = ModelTemplates.CUBE.createWithSuffix(block, "_top_right_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_right")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val topMiddleFront = ModelTemplates.CUBE.createWithSuffix(block, "_top_middle", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val topMiddleBack = ModelTemplates.CUBE.createWithSuffix(block, "_top_middle_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_top_middle")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val middleLeftFront = ModelTemplates.CUBE.createWithSuffix(block, "_middle_left_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_right")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val middleLeftMiddle = TexturedModel.CUBE_TOP.updateTexture {
        it.put(TextureSlot.SIDE, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
    }.createWithSuffix(block, "_middle_left_middle", modelOutput)

    val middleLeftBack = ModelTemplates.CUBE.createWithSuffix(block, "_middle_left_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_right")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_right")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val middleMiddleFront = ModelTemplates.CUBE.createWithSuffix(block, "_middle_middle_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val middleMiddleBack = ModelTemplates.CUBE.createWithSuffix(block, "_middle_middle_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val middleRightFront = ModelTemplates.CUBE.createWithSuffix(block, "_middle_right_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_right")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_right")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val middleRightMiddle = TexturedModel.CUBE_TOP.updateTexture {
        it.put(TextureSlot.SIDE, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_middle")))
    }.createWithSuffix(block, "_middle_right_middle", modelOutput)

    val middleRightBack = ModelTemplates.CUBE.createWithSuffix(block, "_middle_right_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_right")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_middle_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val bottomLeftFront = ModelTemplates.CUBE.createWithSuffix(block, "_bottom_left_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_right")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val bottomLeftMiddle = TexturedModel.CUBE_TOP_BOTTOM.updateTexture {
        it.put(TextureSlot.SIDE, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
    }.createWithSuffix(block, "_bottom_left_middle", modelOutput)

    val bottomLeftBack = ModelTemplates.CUBE.createWithSuffix(block, "_bottom_left_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_right")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_right")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val bottomMiddleFront = ModelTemplates.CUBE.createWithSuffix(block, "_bottom_middle_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val bottomMiddleBack = ModelTemplates.CUBE.createWithSuffix(block, "_bottom_middle_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val bottomRightFront = ModelTemplates.CUBE.createWithSuffix(block, "_bottom_right_front", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_right")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_right")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val bottomRightMiddle = TexturedModel.CUBE_TOP_BOTTOM.updateTexture {
        it.put(TextureSlot.SIDE, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_middle")))
    }.createWithSuffix(block, "_bottom_right_middle", modelOutput)

    val bottomRightBack = ModelTemplates.CUBE.createWithSuffix(block, "_bottom_right_back", TextureMapping()
        .put(TextureSlot.NORTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.SOUTH, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_right")))
        .put(TextureSlot.EAST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.WEST, Material(Identifier.parse("everythingjapanese:block/money_vault_block_side_bottom_left")))
        .put(TextureSlot.UP, Material(Identifier.parse("everythingjapanese:block/money_vault_block_top")))
        .put(TextureSlot.PARTICLE,  Material(Identifier.parse("everythingjapanese:block/money_vault_block")))
        .put(TextureSlot.DOWN, Material(Identifier.parse("everythingjapanese:block/money_vault_block_bottom")))
        ,modelOutput)

    val modelsBySection = mapOf(
        VaultSection.SINGLE to singleModel,

        VaultSection.TOP_LEFT_FRONT to topLeftFront,
        VaultSection.TOP_MIDDLE_FRONT to topMiddleFront,
        VaultSection.TOP_RIGHT_FRONT to topRightFront,
        VaultSection.MIDDLE_LEFT_FRONT to middleLeftFront,
        VaultSection.MIDDLE_MIDDLE_FRONT to middleMiddleFront,
        VaultSection.MIDDLE_RIGHT_FRONT to middleRightFront,
        VaultSection.BOTTOM_LEFT_FRONT to bottomLeftFront,
        VaultSection.BOTTOM_MIDDLE_FRONT to bottomMiddleFront,
        VaultSection.BOTTOM_RIGHT_FRONT to bottomRightFront,

        VaultSection.TOP_LEFT_BACK to topLeftBack,
        VaultSection.TOP_MIDDLE_BACK to topMiddleBack,
        VaultSection.TOP_RIGHT_BACK to topRightBack,
        VaultSection.MIDDLE_LEFT_BACK to middleLeftBack,
        VaultSection.MIDDLE_MIDDLE_BACK to middleMiddleBack,
        VaultSection.MIDDLE_RIGHT_BACK to middleRightBack,
        VaultSection.BOTTOM_LEFT_BACK to bottomLeftBack,
        VaultSection.BOTTOM_MIDDLE_BACK to bottomMiddleBack,
        VaultSection.BOTTOM_RIGHT_BACK to bottomRightBack,

        VaultSection.TOP_LEFT_MIDDLE to topLeftMiddle,
        VaultSection.TOP_MIDDLE_MIDDLE to topMiddleFront,
        VaultSection.TOP_RIGHT_MIDDLE to topRightMiddle,
        VaultSection.MIDDLE_LEFT_MIDDLE to middleLeftMiddle,
        VaultSection.MIDDLE_MIDDLE_MIDDLE to middleMiddleFront,
        VaultSection.MIDDLE_RIGHT_MIDDLE to middleRightMiddle,
        VaultSection.BOTTOM_LEFT_MIDDLE to bottomLeftMiddle,
        VaultSection.BOTTOM_MIDDLE_MIDDLE to bottomMiddleFront,
        VaultSection.BOTTOM_RIGHT_MIDDLE to bottomRightMiddle
    )

    this.blockStateOutput.accept(
        MultiVariantGenerator.dispatch(block).with(
            PropertyDispatch.initial(MoneyVaultBlock.SECTION).apply {
                for ((section, model) in modelsBySection) {
                    this.select(section, plainVariant(model))
                }
            }
        )
    )
}