package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.RegisterCommandsEvent
import thedarkcolour.kotlinforforge.common.KotlinMod
import java.util.function.Predicate

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object SetChakraCommand {
    @SubscribeEvent
    fun onRegisterCommands(event: RegisterCommandsEvent) {
        val dispatcher = event.dispatcher

        dispatcher.register(
            Commands.literal("setChakra")
                .requires(Predicate { commandSourceStack: CommandSourceStack? -> commandSourceStack!!.hasPermission(4) })
                .then(
                    Commands.argument<EntitySelector?>("target", EntityArgument.player())
                        .then(
                            Commands.argument<Int?>("amount", IntegerArgumentType.integer(0))
                                .executes(Command { context: CommandContext<CommandSourceStack?>? ->
                                    SetChakraCommand.setChakra(
                                        context!!.getSource()!!,
                                        EntityArgument.getPlayer(context, "target"),
                                        IntegerArgumentType.getInteger(context, "amount")
                                    )
                                })
                        )
                )
        )
    }


    private fun setChakra(source: CommandSourceStack, target: Player, amount: Int): Int {
        if (target is ServerPlayer) {
            val chakra = target.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
            chakra!!.setCurrentChakra(amount.toFloat(), target)
            source.sendSuccess({
                Component.literal(
                    "Set " + target.displayName?.string + "'s Chakra to " + amount
                )
            }, true)
        }
        return Command.SINGLE_SUCCESS
    }
}
