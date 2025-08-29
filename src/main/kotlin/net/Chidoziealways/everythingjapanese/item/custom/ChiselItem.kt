package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.particle.ModParticles
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.BlockItemStateProperties
import net.minecraft.world.item.component.TooltipDisplay
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import java.util.Map
import java.util.function.Consumer

class ChiselItem(pProperties: Properties) : Item(pProperties) {
    override fun useOn(context: UseOnContext): InteractionResult {
        val level = context.level
        val clickedBlock = level.getBlockState(context.clickedPos).block

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(context.clickedPos, CHISEL_MAP[clickedBlock]!!.defaultBlockState())

                context.itemInHand.hurtAndBreak(
                    1, (level as ServerLevel), (context.player as ServerPlayer?)
                ) { item: Item ->
                    context.player!!.onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                }

                level.playSound(null, context.clickedPos, ModSounds.CHISEL_USE.get(), SoundSource.BLOCKS)

                level.sendParticles<BlockParticleOption?>(
                    BlockParticleOption(ParticleTypes.BLOCK, clickedBlock.defaultBlockState()),
                    context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.0,
                    context.getClickedPos().getZ() + 0.5, 10, 0.0, 0.0, 0.0, 1.0
                )

                level.sendParticles<SimpleParticleType?>(
                    ParticleTypes.ENCHANT,
                    context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.5,
                    context.getClickedPos().getZ() + 0.5, 10, 0.0, 0.0, 0.0, 3.0
                )

                level.sendParticles<SimpleParticleType?>(
                    ModParticles.PYRITE_PARTICLES,
                    context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.5,
                    context.getClickedPos().getZ() + 0.5, 15, 0.0, 0.0, 0.0, 2.0
                )

                context.itemInHand
                    .set(ModDataComponentTypes.COORDINATES, context.clickedPos)
                context.itemInHand
                    .set<BlockItemStateProperties?>(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY)
            }
        }

        return InteractionResult.SUCCESS
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pContext: TooltipContext,
        display: TooltipDisplay,
        pTooltipComponents: Consumer<Component?>,
        pTooltipFlag: TooltipFlag
    ) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.accept(Component.translatable("tooltip.everythingjapanese.chisel_item"))
        } else {
            pTooltipComponents.accept(Component.translatable("tooltip.everythingjapanese.chisel_item.shift_down"))
        }

        if (pStack.get<BlockPos?>(ModDataComponentTypes.COORDINATES) != null) {
            pTooltipComponents.accept(
                Component.literal(
                    "Last Block Changed at :" + pStack.get<BlockPos?>(
                        ModDataComponentTypes.COORDINATES
                    )
                )
            )
        }
        if (pStack.get<BlockItemStateProperties?>(DataComponents.BLOCK_STATE) != null) {
            pTooltipComponents.accept(
                Component.literal(
                    "Last Block Changed's state:" + pStack.get<BlockItemStateProperties?>(
                        DataComponents.BLOCK_STATE
                    )
                )
            )
        }

        super.appendHoverText(pStack, pContext, display, pTooltipComponents, pTooltipFlag)
    }

    companion object {
        private val CHISEL_MAP: MutableMap<Block, Block> = Map.ofEntries<Block, Block>(
            Map.entry<Block, Block>(Blocks.STONE, Blocks.STONE_BRICKS),
            Map.entry<Block, Block>(Blocks.END_STONE, Blocks.END_STONE_BRICKS),
            Map.entry<Block, Block>(Blocks.GRASS_BLOCK, Blocks.BEDROCK),
            Map.entry<Block, Block>(Blocks.IRON_BLOCK, Blocks.DIAMOND_BLOCK),
            Map.entry<Block, Block>(Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE),
            Map.entry<Block, Block>(Blocks.BEDROCK, Blocks.GRASS_BLOCK),
            Map.entry<Block, Block>(Blocks.END_STONE_BRICKS, Blocks.END_STONE),
            Map.entry<Block, Block>(
                Blocks.DIRT,
                ModBlocks.PYRITE_BLOCK
            ) //Map.entry(Blocks.CAKE, ModBlocks.CHOCOLATE_CAKE.get()),
            //Map.entry(ModBlocks.CHOCOLATE_CAKE.get(), ModBlocks.JAPANESE_CHEESECAKE.get()),
            //Map.entry(ModBlocks.WORKBENCH.get(), Blocks.CRAFTING_TABLE)
        )
    }
}
