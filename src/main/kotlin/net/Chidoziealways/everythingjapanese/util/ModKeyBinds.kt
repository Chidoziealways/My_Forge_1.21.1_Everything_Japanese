package net.Chidoziealways.everythingjapanese.util

import com.mojang.blaze3d.platform.InputConstants
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.client.KeyMapping
import net.minecraftforge.client.event.RegisterKeyMappingsEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import org.lwjgl.glfw.GLFW
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.MOD)
object ModKeyBinds {
    const val CATEGORY_JUTSU: String = "key.categories.jutsu"
    var CAST_JUTSU: KeyMapping? = null
    var CYCLE_JUTSU: KeyMapping? = null

    @JvmStatic
    @SubscribeEvent
    fun registerKeyMappings(event: RegisterKeyMappingsEvent) {
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
        event.register(CYCLE_JUTSU)
        event.register(CAST_JUTSU)
    }
}
