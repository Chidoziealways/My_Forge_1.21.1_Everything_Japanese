package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level

class JutsuScrollItem(private val jutsuId: String, properties: Properties) : Item(properties) {
    override fun use(pLevel: Level, pPlayer: Player, pHand: InteractionHand): InteractionResult {
        if (!pLevel.isClientSide && pPlayer is ServerPlayer) {
            val jutsu = pPlayer.getCapability(ModCapabilities.JUTSU_CAPABILITY)
            println(jutsu)
            if (!jutsu!!.hasLearnedJutsu(jutsuId)) {
                jutsu.learnJutsu(jutsuId, pPlayer)
                pPlayer.sendSystemMessage(Component.literal("You have learnt the Jutsu: $jutsuId"))
                pPlayer.getItemInHand(pHand).shrink(1)
            } else {
                pPlayer.sendSystemMessage(Component.literal("You already know this Jutsu!"))
            }
        }
        return InteractionResult.SUCCESS
    }
}
