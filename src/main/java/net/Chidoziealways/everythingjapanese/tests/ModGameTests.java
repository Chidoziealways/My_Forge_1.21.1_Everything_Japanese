package net.Chidoziealways.everythingjapanese.tests;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModGameTests {
    public static final DeferredRegister<Consumer<GameTestHelper>> TEST_FUNCTIONS =
            DeferredRegister.create(Registries.TEST_FUNCTION, EverythingJapanese.MOD_ID);

    public static final RegistryObject<Consumer<GameTestHelper>> POWDER_SNOW = TEST_FUNCTIONS.register("powdersnow",
            () -> (helper) -> {
                helper.pressButton(3, 1, 2);
                helper.succeedIf(() -> helper.assertBlockPresent(Blocks.POWDER_SNOW, 2, 2,2));
            });

    public static void register(IEventBus eventBus) {
        TEST_FUNCTIONS.register(eventBus);
    }

}
