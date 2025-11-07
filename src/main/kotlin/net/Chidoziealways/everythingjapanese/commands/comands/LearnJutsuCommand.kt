package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.RegisterCommandsEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object LearnJutsuCommand {
    @SubscribeEvent
    fun onRegisterCommands(event: RegisterCommandsEvent?) {
        //        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
//        dispatcher.register(
//                Commands.literal("learnJutsu")
//                        .requires(commandSourceStack -> commandSourceStack.hasPermission(4))
//                        .then(Commands.argument("target", EntityArgument.player())
//                                .then(Commands.argument("jutsu", JutsuArgument.jutsu(event.getBuildContext()))
//                                        .executes(context -> learnJutsu(
//                                                context.getSource(),
//                                                EntityArgument.getPlayer(context, "target"),
//                                                JutsuArgument.getJutsu(context, "jutsu").getJutsu().get().getId()
//                                        ))))
//        );
    }

    private fun learnJutsu(source: CommandSourceStack, target: Player, jutsuId: ResourceLocation): Int {
        val jutsu = target.getCapability<IJutsuCapability?>(ModCapabilities.JUTSU_CAPABILITY)
        //jutsu!!.learnJutsu(jutsuId.path)
        source.sendSuccess(
            { Component.literal("You have learned the Jutsu: " + jutsuId.path) },
            true
        )
        return Command.SINGLE_SUCCESS
    }
}
