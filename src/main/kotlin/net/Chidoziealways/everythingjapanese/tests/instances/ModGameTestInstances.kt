package net.Chidoziealways.everythingjapanese.tests.instances

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.tests.ModGameTests
import net.Chidoziealways.everythingjapanese.tests.environments.ModGameTestEnvironments
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.gametest.framework.*
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

object ModGameTestInstances {
    val POWDER_SNOW: ResourceKey<GameTestInstance> = create("powdersnow")

    fun bootstrap(pContext: BootstrapContext<GameTestInstance>) {
        val holdergetter = pContext.lookup(Registries.TEST_FUNCTION)
        val holdergetter1 = pContext.lookup(Registries.TEST_ENVIRONMENT)
        pContext.register(
            POWDER_SNOW,
            FunctionGameTestInstance(
                ModGameTests.POWDER_SNOW.key,
                TestData(
                    holdergetter1.getOrThrow(ModGameTestEnvironments.POWDER_SNOW),
                    ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "powdersnow"),
                    200,
                    40,
                    true
                )
            )
        )
    }

    private fun create(pKey: String): ResourceKey<GameTestInstance> {
        return ResourceKey.create(
            Registries.TEST_INSTANCE,
            ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, pKey)
        )
    }
}
