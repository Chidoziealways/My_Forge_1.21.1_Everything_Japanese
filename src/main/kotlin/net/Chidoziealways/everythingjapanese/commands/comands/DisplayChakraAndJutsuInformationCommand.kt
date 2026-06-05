package net.Chidoziealways.everythingjapanese.commands.comands

import com.mojang.brigadier.Command
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.gui.HUDManager.hudEnabled
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraft.server.permissions.Permission
import net.minecraft.server.permissions.PermissionLevel
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent
import thedarkcolour.kotlinforforge.common.KotlinMod


@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID, value = [Dist.CLIENT])
object DisplayChakraAndJutsuInformationCommand {
    @SubscribeEvent
    fun onRegisterClientCommands(event: RegisterClientCommandsEvent) {
        val dispatcher = event.dispatcher

        dispatcher.register(
            Commands.literal("toggleEJGuiInfo")
                .requires { commandSourceStack -> commandSourceStack.permissions().hasPermission(Permission.HasCommandLevel(
                    PermissionLevel.ALL)) }
                .executes { context ->
                    toggleGui(context.source)
                }
        )
    }

    private fun toggleGui(sourceStack: CommandSourceStack): Int {
        hudEnabled = !hudEnabled

        sourceStack.sendSystemMessage(
            Component.literal(
                "Jutsu HUD: " + (if (hudEnabled) "ON" else "OFF")
            )
        )
        return Command.SINGLE_SUCCESS
    }
}