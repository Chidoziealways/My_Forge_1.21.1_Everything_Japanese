package net.Chidoziealways.everythingjapanese.util

import com.mojang.blaze3d.platform.InputConstants
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.client.KeyMapping
import net.minecraft.resources.ResourceLocation
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import org.lwjgl.glfw.GLFW
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID, value = [Dist.CLIENT])
object ModKeyBinds {
    lateinit var CATEGORY_JUTSU: KeyMapping.Category
    lateinit var CAST_JUTSU: KeyMapping
    lateinit var CYCLE_JUTSU: KeyMapping
    lateinit var CATEGORY_CHAKRA: KeyMapping.Category
    lateinit var REGEN_CHAKRA: KeyMapping

    @SubscribeEvent
    fun registerKeyMappings(event: RegisterKeyMappingsEvent) {
        CATEGORY_JUTSU = KeyMapping.Category(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "jutsu"))

        CAST_JUTSU = KeyMapping(
            "key.everythingjapanese.cast_jutsu",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_J,
            CATEGORY_JUTSU
        )

        CYCLE_JUTSU = KeyMapping(
            "key.everythingjapanese.cycle_jutsu",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            CATEGORY_JUTSU
        )

        CATEGORY_CHAKRA = KeyMapping.Category(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "chakra"))

        REGEN_CHAKRA = KeyMapping(
            "key.everythingjapanese.regen_chakra",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            CATEGORY_CHAKRA
        )

        event.registerCategory(CATEGORY_JUTSU)
        event.register(CYCLE_JUTSU)
        event.register(CAST_JUTSU)
        event.registerCategory(CATEGORY_CHAKRA)
        event.register(REGEN_CHAKRA)
    }
}
