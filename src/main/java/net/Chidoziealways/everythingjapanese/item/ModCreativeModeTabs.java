package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EverythingJapanese.MOD_ID);

    public static final RegistryObject<CreativeModeTab> JAPANESE_ITEMS = CREATIVE_MODE_TABS.register("japanese_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PYRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PYRITE_INGOT.get());
                        output.accept(ModItems.RAW_PYRITE.get());
                        output.accept(ModItems.NEPHRITE.get());
                        output.accept(ModItems.CHISEL.get());
                        output.accept(ModItems.RADIATION_STAFF.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_ENTITIES = CREATIVE_MODE_TABS.register("japanese_entities",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TRICERATOPS_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_entities"))
                    .displayItems((itemDisplayParameters, output) -> {
                       output.accept(ModItems.TRICERATOPS_SPAWN_EGG.get());
                       output.accept(ModItems.SIKA_DEER_SPAWN_EGG.get());

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
                        output.accept(ModBlocks.NEPHRITE_ORE.get());
                        output.accept(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get());
                      //  output.accept(ModBlocks.TRANSFORMER_BLOCK.get());
                        output.accept(ModBlocks.HINOKI_MARUTA.get());
                        output.accept(ModBlocks.PYRITE_BUTTON.get());
                        output.accept(ModBlocks.PYRITE_DOOR.get());
                        output.accept(ModBlocks.PYRITE_FENCE.get());
                        output.accept(ModBlocks.PYRITE_FENCE_GATE.get());
                        output.accept(ModBlocks.PYRITE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.PYRITE_SLAB.get());
                        output.accept(ModBlocks.PYRITE_STAIRS.get());
                        output.accept(ModBlocks.PYRITE_TRAPDOOR.get());
                        output.accept(ModBlocks.PYRITE_WALL.get());
                        output.accept(ModBlocks.PYRITE_LAMP.get());
                        output.accept(ModBlocks.HINOKI_MARUTA.get());
                        output.accept(ModBlocks.HINOKI_MOKUZAI.get());
                        output.accept(ModBlocks.STRIPPED_HINOKI_MARUTA.get());
                        output.accept(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get());
                        output.accept(ModBlocks.HINOKI_BAN.get());
                        output.accept(ModBlocks.HINOKI_HA.get());
                        output.accept(ModBlocks.HINOKI_NAEGI.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_WEAPONS = CREATIVE_MODE_TABS.register("japanese_weapons",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PYRITE_SWORD.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_weapons"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PYRITE_SWORD.get());
                        output.accept(ModItems.YA.get());
                        output.accept(ModItems.WOODEN_KATANA.get());
                        output.accept(ModItems.DAIKYU.get());
                        output.accept(ModItems.PYRITE_BATTLE_AXE.get());
                        output.accept(ModItems.NEPHRITE_SWORD.get());
                        output.accept(ModItems.IRON_BATTLE_AXE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_MUSIC = CREATIVE_MODE_TABS.register("japanese_music",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AO_TO_NATSU_MUSIC_DISC.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_music"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AO_TO_NATSU_MUSIC_DISC.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_ARMOR = CREATIVE_MODE_TABS.register("japanese_armor",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PYRITE_HELMET.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_armor"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PYRITE_HELMET.get());
                        output.accept(ModItems.PYRITE_CHESTPLATE.get());
                        output.accept(ModItems.PYRITE_LEGGINGS.get());
                        output.accept(ModItems.PYRITE_BOOTS.get());
                        output.accept(ModItems.NEPHRITE_HELMET.get());
                        output.accept(ModItems.NEPHRITE_CHESTPLATE.get());
                        output.accept(ModItems.NEPHRITE_LEGGINGS.get());
                        output.accept(ModItems.NEPHRITE_BOOTS.get());
                        output.accept(ModItems.PYRITE_HORSE_ARMOR.get());
                        output.accept(ModItems.SAMURAI_HELMET.get());
                        output.accept(ModItems.SAMURAI_CHESTPLATE.get());
                        output.accept(ModItems.SAMURAI_LEGGINGS.get());
                        output.accept(ModItems.SAMURAI_BOOTS.get());
                    })
                    .build());


    public static final RegistryObject<CreativeModeTab> JAPANESE_ARMOR_TRIMS = CREATIVE_MODE_TABS.register("japanese_armor_trims",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.KOI_FISH_SMITHING_TEMPLATE.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_armor_trims"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.KOI_FISH_SMITHING_TEMPLATE.get());
                    })
                    .build());


    public static final RegistryObject<CreativeModeTab> JAPANESE_FOOD = CREATIVE_MODE_TABS.register("japanese_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SUSHI.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_food"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SUSHI.get());
                        output.accept(ModItems.UDON.get());
                        output.accept(ModBlocks.CHOCOLATE_CAKE.get());
                        output.accept(ModBlocks.JAPANESE_CHEESECAKE.get());
                        output.accept(ModItems.RICE_SEEDS.get());
                        output.accept(ModItems.RICE.get());
                        output.accept(ModItems.RAW_RICE.get());
                        output.accept(ModItems.YAMAZAKI_BERRIES.get());
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

    public static final RegistryObject<CreativeModeTab> JAPANESE_TOOLS = CREATIVE_MODE_TABS.register("japanese_tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PYRITE_AXE.get()))
                    .withTabsBefore(JAPANESE_ITEMS.getId())
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_tools"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PYRITE_AXE.get());
                        output.accept(ModItems.PYRITE_PICKAXE.get());
                        output.accept(ModItems.PYRITE_SHOVEL.get());
                        output.accept(ModItems.PYRITE_HOE.get());
                        output.accept(ModItems.NEPHRITE_AXE.get());
                        output.accept(ModItems.NEPHRITE_PICKAXE.get());
                        output.accept(ModItems.NEPHRITE_SHOVEL.get());
                        output.accept(ModItems.NEPHRITE_HOE.get());
                        output.accept(ModItems.PYRITE_HAMMER.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> JAPANESE_FURNITURE = CREATIVE_MODE_TABS.register("japanese_furniture",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CHAIR.get()))
                    .title(Component.translatable("creativetab.everythingjapanese.japanese_furniture"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CHAIR.get());
                        output.accept(ModBlocks.PEDESTAL.get());
                        output.accept(ModBlocks.GROWTH_CHAMBER.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
        logInfo("REGISTERING EVERY SINGLE CREATIVEMODETAB IN MODCREATIVEMODETABS");
    }
}
