package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod
import java.util.function.Predicate
import java.util.function.Supplier

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE)
object GetChakraCommand {
    @JvmStatic
    @SubscribeEvent
    fun onRegisterCommands(event: RegisterCommandsEvent) {
        val dispatcher = event.getDispatcher()

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
        target.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
            .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                sourceStack.sendSuccess(
                    Supplier {
                        Component.literal(
                            target.getName().toString() + "'s Current Chakra: " + iChakra!!.chakra
                        )
                    }, true
                )
            })
        return Command.SINGLE_SUCCESS
    }
}
