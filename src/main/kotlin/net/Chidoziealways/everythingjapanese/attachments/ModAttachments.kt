package net.Chidoziealways.everythingjapanese.attachments

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.minecraft.core.Holder
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.function.Supplier

object ModAttachments {
    val ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID)

    val ACTIVE_TALISMAN: AttachmentType<Holder<KanjiType>> by
        ATTACHMENT_TYPES.register("active_tailsman") { -> AttachmentType.builder<Holder<KanjiType>>(Supplier { null }).build() }

    fun register(eventBus: IEventBus) {
        ATTACHMENT_TYPES.register(eventBus)
    }
}