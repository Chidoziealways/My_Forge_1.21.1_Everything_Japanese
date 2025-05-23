package net.Chidoziealways.everythingjapanese.tests.instances;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.tests.ModGameTests;
import net.Chidoziealways.everythingjapanese.tests.environments.ModGameTestEnvironments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.gametest.framework.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class ModGameTestInstances {
    public static final ResourceKey<GameTestInstance> POWDER_SNOW = create("powdersnow");

    public static void bootstrap(BootstrapContext<GameTestInstance> pContext) {
        HolderGetter<Consumer<GameTestHelper>> holdergetter = pContext.lookup(Registries.TEST_FUNCTION);
        HolderGetter<TestEnvironmentDefinition> holdergetter1 = pContext.lookup(Registries.TEST_ENVIRONMENT);
        pContext.register(
                POWDER_SNOW,
                new FunctionGameTestInstance(
                        ModGameTests.POWDER_SNOW.getKey(),
                        new TestData<>(holdergetter1.getOrThrow(ModGameTestEnvironments.POWDER_SNOW), ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "powdersnow"), 200, 40, true)
                )
        );
    }

    private static ResourceKey<GameTestInstance> create(String pKey) {
        return ResourceKey.create(Registries.TEST_INSTANCE, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pKey));
    }
}
