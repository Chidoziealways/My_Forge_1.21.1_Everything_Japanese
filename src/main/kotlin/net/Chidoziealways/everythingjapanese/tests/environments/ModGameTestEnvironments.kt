package net.Chidoziealways.everythingjapanese.tests.environments

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.gametest.framework.TestEnvironmentDefinition
import net.minecraft.gametest.framework.TestEnvironmentDefinition.Weather
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier

object ModGameTestEnvironments {
    //var POWDER_SNOW: ResourceKey<TestEnvironmentDefinition<>> = create("powdersnow")

    private fun create(pName: String): ResourceKey<TestEnvironmentDefinition<*>> {
        return ResourceKey.create<TestEnvironmentDefinition<*>>(
            Registries.TEST_ENVIRONMENT,
            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, pName)
        )
    }

    fun bootstrap(pContext: BootstrapContext<TestEnvironmentDefinition<*>>) {
        //pContext.register(POWDER_SNOW, Weather(Weather.Type.CLEAR))
    }
}
