package net.Chidoziealways.everythingjapanese.block.custom;

import net.Chidoziealways.everythingjapanese.block.state.properties.ModBlockStateProperties;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class RiceCropBlock extends CropBlock {
    public static final int MAX_AGE = 4;

    public RiceCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.RICE_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return ModBlockStateProperties.AGE_4;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ModBlockStateProperties.AGE_4);
    }
}
