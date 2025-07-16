package net.Chidoziealways.everythingjapanese.item.custom

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.HitResult

class HammerItem(pProperties: Properties) : Item(pProperties) {
    companion object {
        fun getBlocksToBeDestroyed(
            range: Int,
            initialBlockPos: BlockPos,
            player: ServerPlayer
        ): MutableList<BlockPos?> {
            val positions: MutableList<BlockPos?> = ArrayList<BlockPos?>()

            val traceResult = player.level().clip(
                ClipContext(
                    player.getEyePosition(1f),
                    player.getEyePosition(1f).add(player.getViewVector(1f).scale(6.0)),
                    ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player
                )
            )
            if (traceResult.getType() == HitResult.Type.MISS) {
                return positions
            }

            val direction = traceResult.getDirection()
            for (x in -range..range) {
                for (y in -range..range) {
                    for (z in -range..range) {
                        if (direction == Direction.DOWN || direction == Direction.UP) {
                            positions.add(
                                BlockPos(
                                    initialBlockPos.getX() + x,
                                    initialBlockPos.getY() + z,
                                    initialBlockPos.getZ() + y
                                )
                            )
                        } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                            positions.add(
                                BlockPos(
                                    initialBlockPos.getX() + x,
                                    initialBlockPos.getY() + y,
                                    initialBlockPos.getZ() + z
                                )
                            )
                        } else if (direction == Direction.EAST || direction == Direction.WEST) {
                            positions.add(
                                BlockPos(
                                    initialBlockPos.getX() + z,
                                    initialBlockPos.getY() + y,
                                    initialBlockPos.getZ() + x
                                )
                            )
                        }
                    }
                }
            }

            return positions
        }
    }
}
