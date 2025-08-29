package net.Chidoziealways.everythingjapanese.block.custom

import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.custom.ChairEntity
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import java.util.function.Predicate

class ZabutonBlock(properties: Properties): Block(properties) {
    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide) {
            var entity: ChairEntity
            val entities = level.getEntities(
                ModEntities.CHAIR,
                AABB(pos),
                Predicate { chairEntity -> true }
            )
            entity = if (entities.isEmpty()) {
                ModEntities.CHAIR.spawn(level as ServerLevel, pos, EntitySpawnReason.TRIGGERED)!!
            } else {
                entities[0]
            }

            player.startRiding(entity)
        }

        return InteractionResult.SUCCESS
    }
}