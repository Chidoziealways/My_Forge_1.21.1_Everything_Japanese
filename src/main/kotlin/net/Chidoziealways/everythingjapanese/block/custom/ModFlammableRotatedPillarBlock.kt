package net.Chidoziealways.everythingjapanese.custom

import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ToolAction

class ModFlammableRotatedPillarBlock(properties: Properties) : RotatedPillarBlock(properties) {
    override fun isFlammable(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Int {
        return 5
    }

    override fun getFireSpreadSpeed(
        state: BlockState?,
        level: BlockGetter?,
        pos: BlockPos?,
        direction: Direction?
    ): Int {
        return 5
    }

    override fun getToolModifiedState(
        state: BlockState,
        context: UseOnContext,
        toolAction: ToolAction?,
        simulate: Boolean
    ): BlockState? {
        if (context.itemInHand.item is AxeItem) {
            if (state.`is`(ModBlocks.HINOKI_MARUTA.get())) {
                return ModBlocks.STRIPPED_HINOKI_MARUTA.get()!!.defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS))
            }

            if (state.`is`(ModBlocks.HINOKI_MOKUZAI.get())) {
                return ModBlocks.STRIPPED_HINOKI_MOKUZAI.get()!!.defaultBlockState().setValue(
                    AXIS, state.getValue(
                        AXIS
                    )
                )
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate)
    }
}
