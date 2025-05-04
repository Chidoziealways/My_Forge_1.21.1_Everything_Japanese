package net.Chidoziealways.everythingjapanese.commands.comands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu.JutsuArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LearnJutsuCommand {

     @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {

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

    private static int learnJutsu(CommandSourceStack source, Player target, ResourceLocation jutsuId) {
        target.getCapability(ModCapabilities.JUTSU_CAPABILITY).ifPresent(iJutsuCapability -> {
            iJutsuCapability.learnJutsu(jutsuId.getPath());
            source.sendSuccess(() -> Component.literal("You have learned the Jutsu: " + jutsuId.getPath()), true);
        });
        return Command.SINGLE_SUCCESS;
    }
}
