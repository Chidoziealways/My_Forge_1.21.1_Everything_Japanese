package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.item.custom.ChiselItem;
import net.Chidoziealways.everythingjapanese.item.custom.FuelItem;
import net.Chidoziealways.everythingjapanese.item.custom.Sword;
import net.Chidoziealways.everythingjapanese.item.custom.Drinks;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EverythingJapanese.MOD_ID);

    public static final RegistryObject<Item> PYRITE_INGOT = ITEMS.register("pyrite_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_PYRITE = ITEMS.register("raw_pyrite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(100).stacksTo(1)));

    public static final RegistryObject<Item> PYRITE_SWORD = ITEMS.register("pyrite_sword",
            () -> new Sword(new Item.Properties().durability(100).stacksTo(1)));

    public static final RegistryObject<Item> SUSHI = ITEMS.register("sushi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SUSHI).stacksTo(1)));

    public static final RegistryObject<Item> GREEN_TEA = ITEMS.register("green_tea",
            () -> new Drinks(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DIESEL = ITEMS.register("diesel",
            () -> new FuelItem(new Item.Properties().stacksTo(1), 1200));

    public static final RegistryObject<Item> UDON = ITEMS.register("udon",
            () -> new Item(new Item.Properties().food(ModFoodProperties.UDON).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
