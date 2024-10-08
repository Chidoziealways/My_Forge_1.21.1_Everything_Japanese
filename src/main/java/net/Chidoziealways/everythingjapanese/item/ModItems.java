package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.item.custom.*;
import net.Chidoziealways.everythingjapanese.tiers.ModTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

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
            () -> new SwordItem(ModTiers.Pyrite,  new Item.Properties()));

    public static final RegistryObject<Item> PYRITE_AXE = ITEMS.register("pyrite_axe",
            () -> new ModAxeItem(ModTiers.Pyrite, new Item.Properties()));

    public static final RegistryObject<Item> SUSHI = ITEMS.register("sushi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SUSHI)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                   pTooltipComponents.add(Component.translatable("tooltip.everythingjapanese.sushi"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> GREEN_TEA = ITEMS.register("green_tea",
            () -> new Drinks(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DIESEL = ITEMS.register("diesel",
            () -> new FuelItem(new Item.Properties().stacksTo(1), 1200));

    public static final RegistryObject<Item> UDON = ITEMS.register("udon",
            () -> new Item(new Item.Properties().food(ModFoodProperties.UDON).stacksTo(1)));

    public static final RegistryObject<Item> INCENSE = ITEMS.register("incense",
            () -> new FuelItem(new Item.Properties().stacksTo(1), 20000));

    public static final RegistryObject<Item> YA = ITEMS.register("ya",
            () -> new ArrowItem(new Item.Properties()));
    
    public static final RegistryObject<Item> WOODEN_KATANA = ITEMS.register("wooden_katana",
            () -> new Katana(Tiers.WOOD, new Item.Properties().durability(1000).stacksTo(1), new MobEffects()));

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
