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
import net.minecraftforge.common.util.NonNullConsumer

class JutsuScrollItem(private val jutsuId: String?, properties: Properties) : Item(properties) {
    override fun use(pLevel: Level, pPlayer: Player, pHand: InteractionHand): InteractionResult {
        if (!pLevel.isClientSide && pPlayer is ServerPlayer) {
            pPlayer.getCapability<IJutsuCapability?>(ModCapabilities.JUTSU_CAPABILITY)
                .ifPresent(NonNullConsumer { iJutsuCapability: IJutsuCapability? ->
                    if (!iJutsuCapability!!.hasLearnedJutsu(jutsuId)) {
                        iJutsuCapability.learnJutsu(jutsuId)
                        pPlayer.sendSystemMessage(Component.literal("You have learnt the Jutsu: " + jutsuId))
                        pPlayer.getItemInHand(pHand).shrink(1)
                    } else {
                        pPlayer.sendSystemMessage(Component.literal("You already know this Jutsu!"))
                    }
                })
        }
        return InteractionResult.SUCCESS
    }
}
