package net.Chidoziealways.everythingjapanese.commands.comands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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
public class SetChakraCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
                Commands.literal("setChakra")
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(4))
                        .then(Commands.argument("target", EntityArgument.player())
                            .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                    .executes(context -> setChakra(
                                        context.getSource(),
                                        EntityArgument.getPlayer(context, "target"),
                                        IntegerArgumentType.getInteger(context, "amount")
                        ))))
        );
    }


    private static int setChakra(CommandSourceStack source, Player target, int amount) {
        target.getCapability(ModCapabilities.CHAKRA_CAPABILITY).ifPresent(iChakra -> {
            iChakra.setChakra(amount);
            source.sendSuccess(() -> Component.literal("Set " + target.getName().getString() + "'s Chakra to " + amount), true);
        });
        return Command.SINGLE_SUCCESS;
    }
}
