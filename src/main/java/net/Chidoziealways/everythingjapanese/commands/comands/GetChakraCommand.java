package net.Chidoziealways.everythingjapanese.commands.comands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GetChakraCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
                Commands.literal("getChakra")
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(1))
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(context -> getChakra(
                                        context.getSource(),
                                        EntityArgument.getPlayer(context, "target")
                                )))
        );
    }

    private static int getChakra(CommandSourceStack sourceStack, Player target) {
        target.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iChakra -> sourceStack.sendSuccess(() -> Component.literal(target.getName().toString() + "'s Current Chakra: " + iChakra.getChakra()), true));
        return Command.SINGLE_SUCCESS;
    }

}
