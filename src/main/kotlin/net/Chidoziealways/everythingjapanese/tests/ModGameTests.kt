package net.Chidoziealways.everythingjapanese.tests

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.gametest.framework.GameTestHelper
import net.minecraft.world.level.block.Blocks
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Consumer

object ModGameTests {
    val TEST_FUNCTIONS = DeferredRegister.create(
            Registries.TEST_FUNCTION, JAPANESE_MOD_ID
        )

    val POWDER_SNOW = TEST_FUNCTIONS.register(
        "powdersnow") { ->
            Consumer { helper: GameTestHelper ->
                helper.pressButton(3, 1, 2)
                helper.succeedIf { helper.assertBlockPresent(Blocks.POWDER_SNOW, 2, 2, 2) }
            }
        }

    fun register(eventBus: IEventBus) {
        TEST_FUNCTIONS.register(eventBus)
    }
}
