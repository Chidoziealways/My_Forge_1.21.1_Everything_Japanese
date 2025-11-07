package net.Chidoziealways.everythingjapanese.screen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.screen.custom.calligraphytable.CalligraphyTableMenu
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberMenu
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalMenu
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.function.Supplier

object ModMenuTypes {
    val MENUS = DeferredRegister.create(Registries.MENU, JAPANESE_MOD_ID)

    val PEDESTAL_MENU by MENUS.register(
        "pedestal_menu",
        Supplier {
            IMenuTypeExtension.create { pContainerId: Int, inv: Inventory, extraData: FriendlyByteBuf ->
                PedestalMenu(
                    pContainerId,
                    inv,
                    extraData
                )
            }
        })

    val CALLIGRAPHY_TABLE_MENU by MENUS.register(
        "calligraphy_table_menu") { ->
        IMenuTypeExtension.create { containerId, inv, _ ->
            CalligraphyTableMenu(
                containerId,
                inv
            )
        }
    }

    val GROWTH_CHAMBER_MENU by
        MENUS.register(
            "growth_chamber_menu",
            Supplier {
                IMenuTypeExtension.create<GrowthChamberMenu?> { pContainerId: Int, inv: Inventory, extraData: FriendlyByteBuf ->
                    GrowthChamberMenu(
                        pContainerId,
                        inv,
                        extraData
                    )
                }
            })

    fun register(eventBus: IEventBus) {
        MENUS.register(eventBus)
    }
}
