package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.quest.packets.FinishQuestPacket
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.ResourceLocationArgument
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.RegisterCommandsEvent
import net.neoforged.neoforge.network.PacketDistributor
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object FinishQuestCommand {
    @SubscribeEvent
    fun onRegisterCommand(event: RegisterCommandsEvent) {
        val dispatcher = event.dispatcher

        dispatcher.register(
            Commands.literal("finishQuest")
                .requires { commandSourceStack -> commandSourceStack.hasPermission(4) }
                .then(
                    Commands.argument("quest", ResourceLocationArgument.id())
                        .executes { context ->  finishQuest(
                            context.source,
                            ResourceLocationArgument.getId(context, "quest")
                        )}
                )
        )
    }

    private fun finishQuest(source: CommandSourceStack, quest: ResourceLocation): Int {
        val player: ServerPlayer = source.playerOrException

        val quests = player.getCapability(ModCapabilities.QUEST_CAPABILITY)
        quests!!.finishQuest(quest, player)
        source.sendSuccess( { Component.literal("Finished Quest ${quest.path}") }, true)
        PacketDistributor.sendToPlayer(player, FinishQuestPacket(quest))

        return Command.SINGLE_SUCCESS
    }
}