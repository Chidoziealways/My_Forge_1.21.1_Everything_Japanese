package net.Chidoziealways.everythingjapanese.tests.environments;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class ModGameTestEnvironments {
    public static ResourceKey<TestEnvironmentDefinition> POWDER_SNOW = create("powdersnow");

    private static ResourceKey<TestEnvironmentDefinition> create(String pName) {
        return ResourceKey.create(Registries.TEST_ENVIRONMENT, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pName));
    }

    public static void bootstrap(BootstrapContext<TestEnvironmentDefinition> pContext) {
        pContext.register(POWDER_SNOW, new TestEnvironmentDefinition.Weather(TestEnvironmentDefinition.Weather.Type.CLEAR));
    }
}
