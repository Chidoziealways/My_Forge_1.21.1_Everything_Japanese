package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.IntegerArgumentType
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
object SetChakraCommand {
    @JvmStatic
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
        target.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
            .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                iChakra!!.chakra = amount.toFloat()
                source.sendSuccess(Supplier {
                    Component.literal(
                        "Set " + target.displayName?.string + "'s Chakra to " + amount
                    )
                }, true)
            })
        return Command.SINGLE_SUCCESS
    }
}
