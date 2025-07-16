package net.Chidoziealways.everythingjapanese.tests

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.gametest.framework.GameTestHelper
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Consumer
import java.util.function.Supplier

object ModGameTests {
    val TEST_FUNCTIONS: DeferredRegister<Consumer<GameTestHelper?>?> =
        DeferredRegister.create(
            Registries.TEST_FUNCTION, MOD_ID
        )

    val POWDER_SNOW: RegistryObject<Consumer<GameTestHelper?>?>? = TEST_FUNCTIONS.register<Consumer<GameTestHelper?>?>(
        "powdersnow",
        Supplier {
            Consumer { helper: GameTestHelper? ->
                helper!!.pressButton(3, 1, 2)
                helper.succeedIf(Runnable { helper.assertBlockPresent(Blocks.POWDER_SNOW, 2, 2, 2) })
            }
        })

    fun register(eventBus: BusGroup?) {
        TEST_FUNCTIONS.register(eventBus)
    }
}
