package net.Chidoziealways.everythingjapanese.screen;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberMenu;
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, EverythingJapanese.MOD_ID);

    public static final RegistryObject<MenuType<PedestalMenu>> PEDESTAL_MENU =
            MENUS.register("pedestal_menu", () -> IForgeMenuType.create(PedestalMenu::new));

    public static final RegistryObject<MenuType<GrowthChamberMenu>> GROWTH_CHAMBER_MENU =
            MENUS.register("growth_chamber_menu", () -> IForgeMenuType.create(GrowthChamberMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
