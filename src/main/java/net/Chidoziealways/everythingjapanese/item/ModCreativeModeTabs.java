package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EverythingJapanese.MOD_ID);

    public static final RegistryObject<CreativeModeTab> JAPANESE_ITEMS = CREATIVE_MODE_TABS.register("japanese_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PYRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PYRITE_INGOT.get());
                        output.accept(ModItems.RAW_PYRITE.get());
                        output.accept(ModItems.CHISEL.get());

                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> JAPANESE_BLOCKS = CREATIVE_MODE_TABS.register("japanese_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PYRITE_BLOCK.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.PYRITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_PYRITE_BLOCK.get());
                        output.accept(ModBlocks.PYRITE_ORE.get());
                        output.accept(ModBlocks.PYRITE_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.TRANSFORMER_BLOCK.get());
                        output.accept(ModBlocks.HINOKI_MARUTA.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_WEAPONS = CREATIVE_MODE_TABS.register("japanese_weapons",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PYRITE_SWORD.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_weapons"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PYRITE_SWORD.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_FOOD = CREATIVE_MODE_TABS.register("japanese_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SUSHI.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_food"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SUSHI.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_DRINKS = CREATIVE_MODE_TABS.register("japanese_drinks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GREEN_TEA.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_drinks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GREEN_TEA.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_FUEL = CREATIVE_MODE_TABS.register("japanese_fuel",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIESEL.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_fuel"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.DIESEL.get());
                        output.accept(ModItems.INCENSE.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
