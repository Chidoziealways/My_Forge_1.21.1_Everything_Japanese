package net.Chidoziealways.everythingjapanese.util

import com.mojang.blaze3d.platform.InputConstants
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.client.KeyMapping
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import org.lwjgl.glfw.GLFW
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, value = [Dist.CLIENT])
object ModKeyBinds {
    const val CATEGORY_JUTSU: String = "key.categories.jutsu"
    lateinit var CAST_JUTSU: KeyMapping
    lateinit var CYCLE_JUTSU: KeyMapping

    const val CATEGORY_QUEST: String = "key.categories.quest"
    lateinit var SHOW_QUESTS: KeyMapping

    const val CATEGORY_CHAKRA: String = "ket.categories.chakra"
    lateinit var REGEN_CHAKRA: KeyMapping

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

        SHOW_QUESTS = KeyMapping(
            "key.everythingjapanese.show_quests",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            CATEGORY_QUEST
        )

        REGEN_CHAKRA = KeyMapping(
            "key.everythingjapanese.regen_chakra",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            CATEGORY_CHAKRA
        )

        event.register(CYCLE_JUTSU)
        event.register(CAST_JUTSU)
        event.register(SHOW_QUESTS)
        event.register(REGEN_CHAKRA)
    }
}
