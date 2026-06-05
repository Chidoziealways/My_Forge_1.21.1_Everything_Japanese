package net.Chidoziealways.everythingjapanese.dialog

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.server.dialog.Dialog

object JModDialog {
    //val QUEST_DIALOG

    private fun createKey(name: String): ResourceKey<Dialog> {
        return ResourceKey.create(
            Registries.DIALOG,
            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, name)
        )
    }

    fun bootstrap(context: BootstrapContext<Dialog>) {

    }
}