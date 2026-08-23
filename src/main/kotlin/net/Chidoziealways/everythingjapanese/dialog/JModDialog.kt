package net.Chidoziealways.everythingjapanese.dialog

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.server.dialog.ActionButton
import net.minecraft.server.dialog.CommonButtonData
import net.minecraft.server.dialog.CommonDialogData
import net.minecraft.server.dialog.ConfirmationDialog
import net.minecraft.server.dialog.Dialog
import net.minecraft.server.dialog.DialogAction
import net.minecraft.server.dialog.Input
import net.minecraft.server.dialog.action.Action
import net.minecraft.server.dialog.action.CustomAll
import net.minecraft.server.dialog.input.InputControl
import net.minecraft.server.dialog.input.InputControlTypes
import net.minecraft.server.dialog.input.TextInput
import java.util.Optional

object JModDialog {
    val CURSE_CONFIRM_DIALOG = createKey("curse_confirm_dialog")

    private fun createKey(name: String): ResourceKey<Dialog> {
        return ResourceKey.create(
            Registries.DIALOG,
            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, name)
        )
    }

    fun bootstrap(context: BootstrapContext<Dialog>) {
        context.register(CURSE_CONFIRM_DIALOG,
            ConfirmationDialog(
                CommonDialogData(Component.translatable("dialog.everythingjapanese.curse_confirm_dialog"), Optional.empty(), false, true,
                    DialogAction.CLOSE, mutableListOf(), mutableListOf()),
                ActionButton(
                    CommonButtonData(Component.translatable("dialog.everythingjapanese.curse_yes"), 200),
                    Optional.of(
                        CustomAll(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "curse_confirm"), Optional.empty())
                    )
                ),
                ActionButton(
                    CommonButtonData(Component.translatable("dialog.everythingjapanese.curse_no"), 200),
                    Optional.empty())
            ))
    }
}