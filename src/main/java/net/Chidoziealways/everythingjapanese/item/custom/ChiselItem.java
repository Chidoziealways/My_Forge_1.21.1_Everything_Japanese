package net.Chidoziealways.everythingjapanese.item.custom;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes;
import net.Chidoziealways.everythingjapanese.particle.ModParticles;
import net.Chidoziealways.everythingjapanese.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.ofEntries(
                    Map.entry(Blocks.STONE, Blocks.STONE_BRICKS),
                    Map.entry(Blocks.END_STONE, Blocks.END_STONE_BRICKS),
                    Map.entry(Blocks.GRASS_BLOCK, Blocks.BEDROCK),
                    Map.entry(Blocks.IRON_BLOCK, Blocks.DIAMOND_BLOCK),
                    Map.entry(Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE),
                    Map.entry(Blocks.BEDROCK, Blocks.GRASS_BLOCK),
                    Map.entry(Blocks.END_STONE_BRICKS, Blocks.END_STONE),
                    Map.entry(Blocks.DIRT, ModBlocks.PYRITE_BLOCK.get())
                    //Map.entry(Blocks.CAKE, ModBlocks.CHOCOLATE_CAKE.get()),
                    //Map.entry(ModBlocks.CHOCOLATE_CAKE.get(), ModBlocks.JAPANESE_CHEESECAKE.get()),
                    //Map.entry(ModBlocks.WORKBENCH.get(), Blocks.CRAFTING_TABLE)
            );

    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1,((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                
                level.playSound(null, pContext.getClickedPos(), ModSounds.CHISEL_USE.get(), SoundSource.BLOCKS);

                ((ServerLevel) level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, clickedBlock.defaultBlockState()),
                        pContext.getClickedPos().getX() + 0.5, pContext.getClickedPos().getY() + 1.0,
                        pContext.getClickedPos().getZ() + 0.5, 10, 0, 0, 0, 1);

                ((ServerLevel) level).sendParticles(ParticleTypes.ENCHANT,
                        pContext.getClickedPos().getX() + 0.5, pContext.getClickedPos().getY() + 1.5,
                        pContext.getClickedPos().getZ() + 0.5, 10, 0, 0, 0, 3);

                ((ServerLevel) level).sendParticles(ModParticles.PYRITE_PARTICLES.get(),
                        pContext.getClickedPos().getX() + 0.5, pContext.getClickedPos().getY() + 1.5,
                        pContext.getClickedPos().getZ() + 0.5, 15, 0, 0, 0, 2);

                pContext.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), pContext.getClickedPos());
                pContext.getItemInHand().set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.everythingjapanese.chisel_item"));
        }else {
            pTooltipComponents.add(Component.translatable("tooltip.everythingjapanese.chisel_item.shift_down"));
        }

        if(pStack.get(ModDataComponentTypes.COORDINATES.get()) != null){
            pTooltipComponents.add(Component.literal("Last Block Changed at :" + pStack.get(ModDataComponentTypes.COORDINATES.get())));
        }
        if(pStack.get(DataComponents.BLOCK_STATE) != null){
            pTooltipComponents.add(Component.literal("Last Block Changed's state:" + pStack.get(DataComponents.BLOCK_STATE)));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
