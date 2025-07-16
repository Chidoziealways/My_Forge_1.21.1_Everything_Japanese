package net.Chidoziealways.everythingjapanese.screen

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberMenu
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalMenu
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.MenuType
import net.minecraftforge.common.extensions.IForgeMenuType
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.network.IContainerFactory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModMenuTypes {
    val MENUS: DeferredRegister<MenuType<*>?> =
        DeferredRegister.create(Registries.MENU, MOD_ID)

    val PEDESTAL_MENU: RegistryObject<MenuType<PedestalMenu?>?>? = MENUS.register<MenuType<PedestalMenu?>?>(
        "pedestal_menu",
        Supplier {
            IForgeMenuType.create<PedestalMenu?>(IContainerFactory { pContainerId: Int, inv: Inventory, extraData: FriendlyByteBuf ->
                PedestalMenu(
                    pContainerId,
                    inv,
                    extraData
                )
            })
        })

    val GROWTH_CHAMBER_MENU: RegistryObject<MenuType<GrowthChamberMenu?>?>? =
        MENUS.register<MenuType<GrowthChamberMenu?>?>(
            "growth_chamber_menu",
            Supplier {
                IForgeMenuType.create<GrowthChamberMenu?>(IContainerFactory { pContainerId: Int, inv: Inventory, extraData: FriendlyByteBuf ->
                    GrowthChamberMenu(
                        pContainerId,
                        inv,
                        extraData
                    )
                })
            })

    fun register(eventBus: BusGroup?) {
        MENUS.register(eventBus)
    }
}
