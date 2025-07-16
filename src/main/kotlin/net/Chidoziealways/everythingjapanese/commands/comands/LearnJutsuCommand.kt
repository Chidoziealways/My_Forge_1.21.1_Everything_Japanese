package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod
import java.util.function.Supplier

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE)
object LearnJutsuCommand {
    @JvmStatic
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
        target.getCapability<IJutsuCapability?>(ModCapabilities.JUTSU_CAPABILITY)
            .ifPresent(NonNullConsumer { iJutsuCapability: IJutsuCapability? ->
                iJutsuCapability!!.learnJutsu(jutsuId.getPath())
                source.sendSuccess(
                    Supplier { Component.literal("You have learned the Jutsu: " + jutsuId.getPath()) },
                    true
                )
            })
        return Command.SINGLE_SUCCESS
    }
}
