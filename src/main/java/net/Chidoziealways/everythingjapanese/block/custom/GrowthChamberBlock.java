package net.Chidoziealways.everythingjapanese.block.custom;

import com.mojang.serialization.MapCodec;
import net.Chidoziealways.everythingjapanese.block.entity.ModBlockEntities;
import net.Chidoziealways.everythingjapanese.block.entity.custom.GrowthChamberBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class GrowthChamberBlock extends BaseEntityBlock {
    public static final MapCodec<GrowthChamberBlock> CODEC = simpleCodec(GrowthChamberBlock::new);

    public GrowthChamberBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GrowthChamberBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        // Check if the block is actually being replaced (like on block break)
        if (pState.getBlock() != pLevel.getBlockState(pPos).getBlock()) {
            // Get the block entity at the position and check if it's an instance of your GrowthChamberBlockEntity
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof GrowthChamberBlockEntity growthChamberBlockEntity) {
                growthChamberBlockEntity.preRemoveSideEffects(pPos, pState);  // Call drops() method on the GrowthChamberBlockEntity
            }
        }
        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof GrowthChamberBlockEntity growthChamberBlockEntity) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(growthChamberBlockEntity, Component.literal("Growth Chamber")), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.GROWTH_CHAMBER_BE.get(),
                (level, blockPos, blockState, growthChamberBlockEntity) -> growthChamberBlockEntity.tick(level, blockPos, blockState));
    }
}
