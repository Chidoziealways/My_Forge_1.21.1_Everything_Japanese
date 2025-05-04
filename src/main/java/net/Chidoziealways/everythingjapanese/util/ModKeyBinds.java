package net.Chidoziealways.everythingjapanese.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeyBinds {
    public static final String CATEGORY_JUTSU = "key.categories.jutsu";
    public static KeyMapping CAST_JUTSU;
    public static KeyMapping CYCLE_JUTSU;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        CAST_JUTSU = new KeyMapping(
                "key.everythingjapanese.cast_jutsu",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_J,
                CATEGORY_JUTSU
        );

        CYCLE_JUTSU = new KeyMapping(
                "key.everythingjapanese.cycle_jutsu",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                CATEGORY_JUTSU
        );
        event.register(CYCLE_JUTSU);
        event.register(CAST_JUTSU);
    }
}
