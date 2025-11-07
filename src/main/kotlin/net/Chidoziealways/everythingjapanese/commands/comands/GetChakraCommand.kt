package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.RegisterCommandsEvent
import thedarkcolour.kotlinforforge.common.KotlinMod
import java.util.function.Predicate
import java.util.function.Supplier

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object GetChakraCommand {
    @SubscribeEvent
    fun onRegisterCommands(event: RegisterCommandsEvent) {
        val dispatcher = event.dispatcher

        dispatcher.register(
            Commands.literal("getChakra")
                .requires(Predicate { commandSourceStack: CommandSourceStack? -> commandSourceStack!!.hasPermission(1) })
                .then(
                    Commands.argument<EntitySelector?>("target", EntityArgument.player())
                        .executes(Command { context: CommandContext<CommandSourceStack?>? ->
                            GetChakraCommand.getChakra(
                                context!!.getSource()!!,
                                EntityArgument.getPlayer(context, "target")
                            )
                        })
                )
        )
    }

    private fun getChakra(sourceStack: CommandSourceStack, target: Player): Int {
        val chakra = target.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
        sourceStack.sendSuccess(
            Supplier {
                Component.literal(
                    target.displayName?.string + "'s Current Chakra: " + chakra!!.getCurrentChakra()
                )
            }, true
        )
        return Command.SINGLE_SUCCESS
    }
}
