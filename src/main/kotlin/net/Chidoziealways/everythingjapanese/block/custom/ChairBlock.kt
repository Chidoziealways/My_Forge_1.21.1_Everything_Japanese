package net.Chidoziealways.everythingjapanese.block.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.custom.ChairEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Function
import java.util.function.Predicate

class ChairBlock(pProperties: Properties) : HorizontalDirectionalBlock(pProperties) {
    override fun useWithoutItem(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHitResult: BlockHitResult
    ): InteractionResult {
        if (!pLevel.isClientSide()) {
            var entity: Entity? = null
            val entities = pLevel.getEntities<ChairEntity?>(
                ModEntities.CHAIR?.get(),
                AABB(pPos),
                Predicate { chair: ChairEntity? -> true })
            if (entities.isEmpty()) {
                entity = ModEntities.CHAIR?.get()?.spawn(pLevel as ServerLevel, pPos, EntitySpawnReason.TRIGGERED)
            } else {
                entity = entities.get(0)
            }

            pPlayer.startRiding(entity)
        }

        return InteractionResult.SUCCESS
    }

    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun codec(): MapCodec<out HorizontalDirectionalBlock?> {
        return CODEC
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
        return this.defaultBlockState()
            .setValue<Direction?, Direction?>(FACING, pContext.getHorizontalDirection().getOpposite())
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block?, BlockState?>) {
        pBuilder.add(FACING)
    }

    companion object {
        val CODEC: MapCodec<ChairBlock?> = simpleCodec<ChairBlock?>(Function { pProperties: Properties? ->
            ChairBlock(
                pProperties!!
            )
        })
        val SHAPE: VoxelShape = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
    }
}
