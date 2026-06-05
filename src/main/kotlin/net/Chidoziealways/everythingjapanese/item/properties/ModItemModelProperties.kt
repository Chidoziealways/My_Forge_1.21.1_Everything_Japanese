package net.Chidoziealways.everythingjapanese.item.properties

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.item.katana.BladeTypeProperty
import net.Chidoziealways.everythingjapanese.item.katana.BladeWrapProperty
import net.Chidoziealways.everythingjapanese.item.katana.WrappingProperty
import net.Chidoziealways.everythingjapanese.kanji.KanjiProperty
import net.Chidoziealways.everythingjapanese.karma.KarmaProperty
import net.minecraft.resources.Identifier
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID, value = [Dist.CLIENT])
object ModItemModelProperties {

    @SubscribeEvent
    fun onBootstrapSelectModelProperties(event: RegisterSelectItemModelPropertyEvent) {
        event.register(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "kanji"), KanjiProperty.TYPE)
        event.register(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "blade_type"), BladeTypeProperty.TYPE)
        event.register(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "wrap"), WrappingProperty.TYPE)
        event.register(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "blade_wrap"), BladeWrapProperty.TYPE)
    }

    @SubscribeEvent
    fun onBootstrapRangeSelectModelProperties(event: RegisterRangeSelectItemModelPropertyEvent) {
        event.register(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "karma"), KarmaProperty.MAP_CODEC)
    }
}