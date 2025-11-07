package net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

object Designs {
    val FIRE = createKey("fire")
    val PEOPLE = createKey("people")
    val HUMANITY = createKey("humanity")

    val JAPAN_KOREA = createKey("japan_korea")

    val EMPTY = createKey("empty")

    fun bootstrap(context: BootstrapContext<Design>) {
        context.register(
            FIRE,
            Design(
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/design/fire.png")
            )
        )
        context.register(
            PEOPLE,
            Design(
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/design/people.png")
            )
        )
        context.register(
            HUMANITY,
            Design(
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/design/humanity.png")
            )
        )
        context.register(
            JAPAN_KOREA,
            Design(
                ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "textures/design/jp_kr.png")
            )
        )
    }

    private fun createKey(name: String): ResourceKey<Design> {
        return ResourceKey.create(ModRegistries.DESIGN, ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, name))
    }
}