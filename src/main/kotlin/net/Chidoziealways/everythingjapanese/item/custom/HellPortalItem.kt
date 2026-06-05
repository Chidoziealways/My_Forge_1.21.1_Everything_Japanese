//package net.Chidoziealways.everythingjapanese.item.custom
//
//import net.Chidoziealways.everythingjapanese.portal.HellPortalShape
//import net.minecraft.core.Direction
//import net.minecraft.network.chat.Component
//import net.minecraft.sounds.SoundEvents
//import net.minecraft.sounds.SoundSource
//import net.minecraft.world.InteractionHand
//import net.minecraft.world.InteractionResult
//import net.minecraft.world.entity.player.Player
//import net.minecraft.world.item.Item
//import net.minecraft.world.level.Level
//import net.minecraft.world.phys.BlockHitResult
//import java.util.function.Predicate
//
//class HellPortalItem(properties: Properties) : Item(properties) {
//    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
//        if (!level.isClientSide) {
//            val pos = player.blockPosition()
//
//            // Try to get the block the player is looking at
//            val hitResult = player.pick(5.0, 0.0f, false) as BlockHitResult // 5 block reach
//            val targetPos = hitResult.blockPos
//            val axis = if (player.direction.axis.isHorizontal)
//                player.direction.counterClockWise.axis
//            else
//                Direction.Axis.X
//
//            val shape = HellPortalShape.findEmptyPortalShape(
//                level,
//                targetPos,
//                axis
//            )
//
//            if (shape.isPresent) {
//                shape.get().createPortalBlocks(level)
//                level.playSound(null, targetPos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0f, 1.0f)
//                return InteractionResult.SUCCESS
//            } else {
//                player.displayClientMessage(Component.literal("Invalid portal frame."), true)
//                return InteractionResult.FAIL
//            }
//        }
//
//        return InteractionResult.SUCCESS
//    }
//}
