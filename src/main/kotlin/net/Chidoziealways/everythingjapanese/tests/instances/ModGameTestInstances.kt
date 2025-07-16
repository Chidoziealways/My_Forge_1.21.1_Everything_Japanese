package net.Chidoziealways.everythingjapanese.tests.instances

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.tests.ModGameTests
import net.Chidoziealways.everythingjapanese.tests.environments.ModGameTestEnvironments
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.gametest.framework.*
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import java.util.function.Consumer

object ModGameTestInstances {
    val POWDER_SNOW: ResourceKey<GameTestInstance?> = create("powdersnow")

    fun bootstrap(pContext: BootstrapContext<GameTestInstance?>) {
        val holdergetter = pContext.lookup<Consumer<GameTestHelper?>?>(Registries.TEST_FUNCTION)
        val holdergetter1 = pContext.lookup<TestEnvironmentDefinition?>(Registries.TEST_ENVIRONMENT)
        pContext.register(
            POWDER_SNOW,
            FunctionGameTestInstance(
                ModGameTests.POWDER_SNOW!!.getKey(),
                TestData<Holder<TestEnvironmentDefinition?>?>(
                    holdergetter1.getOrThrow(ModGameTestEnvironments.POWDER_SNOW),
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdersnow"),
                    200,
                    40,
                    true
                )
            )
        )
    }

    private fun create(pKey: String): ResourceKey<GameTestInstance?> {
        return ResourceKey.create<GameTestInstance?>(
            Registries.TEST_INSTANCE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, pKey)
        )
    }
}
