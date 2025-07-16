package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters
import net.minecraft.world.item.ItemStack
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModCreativeModeTabs {
    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab?> =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID)

    val JAPANESE_ITEMS: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_items",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.PYRITE_INGOT!!.get()) }
                .title(Component.translatable("creativetab.everythingjapanese.japanese_items"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_INGOT!!.get())
                    output.accept(ModItems.RAW_PYRITE!!.get())
                    output.accept(ModItems.NEPHRITE!!.get())
                    output.accept(ModItems.CHISEL!!.get())
                    output.accept(ModItems.RADIATION_STAFF!!.get())
                }
                .build()
        })

    val JUTSU: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "jutsu",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.SMALL_FIREBALL_SCROLL!!.get()) })
                .title(Component.literal("Jutsu"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.SMALL_FIREBALL_SCROLL!!.get())
                    output.accept(ModItems.WINDBALL_SCROLL!!.get())
                    output.accept(ModItems.LARGE_FIREBALL_SCROLL!!.get())
                })
                .build()
        })

    val HELL: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "hell",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.HELL_PORTAL_ACTIVATOR!!.get()) })
                .title(Component.translatable("creativetab.everythingjapanese.hell"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.HELL_PORTAL_ACTIVATOR!!.get())
                })
                .build()
        })

    val JAPANESE_ENTITIES: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_entities",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.TRICERATOPS_SPAWN_EGG!!.get()) })
                .title(Component.translatable("creativetab.everythingjapanese.japanese_entities"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.TRICERATOPS_SPAWN_EGG!!.get())
                    output.accept(ModItems.SIKA_DEER_SPAWN_EGG!!.get())
                })
                .build()
        })

    val JAPANESE_BLOCKS: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_blocks",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModBlocks.PYRITE_BLOCK.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_blocks"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModBlocks.PYRITE_BLOCK.get())
                    output.accept(ModBlocks.RAW_PYRITE_BLOCK.get())
                    output.accept(ModBlocks.PYRITE_ORE.get())
                    output.accept(ModBlocks.PYRITE_DEEPSLATE_ORE.get())
                    output.accept(ModBlocks.NEPHRITE_ORE.get())
                    output.accept(ModBlocks.NEPHRITE_DEEPSLATE_ORE.get())
                    //  output.accept(ModBlocks.TRANSFORMER_BLOCK.get());
                    output.accept(ModBlocks.HINOKI_MARUTA.get())
                    output.accept(ModBlocks.PYRITE_BUTTON.get())
                    output.accept(ModBlocks.PYRITE_DOOR.get())
                    output.accept(ModBlocks.PYRITE_FENCE.get())
                    output.accept(ModBlocks.PYRITE_FENCE_GATE.get())
                    output.accept(ModBlocks.PYRITE_PRESSURE_PLATE.get())
                    output.accept(ModBlocks.PYRITE_SLAB.get())
                    output.accept(ModBlocks.PYRITE_STAIRS.get())
                    output.accept(ModBlocks.PYRITE_TRAPDOOR.get())
                    output.accept(ModBlocks.PYRITE_WALL.get())
                    output.accept(ModBlocks.PYRITE_LAMP.get())
                    output.accept(ModBlocks.HINOKI_MARUTA.get())
                    output.accept(ModBlocks.HINOKI_MOKUZAI.get())
                    output.accept(ModBlocks.STRIPPED_HINOKI_MARUTA.get())
                    output.accept(ModBlocks.STRIPPED_HINOKI_MOKUZAI.get())
                    output.accept(ModBlocks.HINOKI_BAN.get())
                    output.accept(ModBlocks.HINOKI_HA.get())
                    output.accept(ModBlocks.HINOKI_NAEGI.get())
                })
                .build()
        })

    val JAPANESE_WEAPONS: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_weapons",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.PYRITE_SWORD!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_weapons"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_SWORD!!.get())
                    output.accept(ModItems.YA!!.get())
                    output.accept(ModItems.WOODEN_KATANA!!.get())
                    output.accept(ModItems.DAIKYU!!.get())
                    output.accept(ModItems.PYRITE_BATTLE_AXE!!.get())
                    output.accept(ModItems.NEPHRITE_SWORD!!.get())
                    output.accept(ModItems.IRON_BATTLE_AXE!!.get())
                })
                .build()
        })

    val JAPANESE_MUSIC: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_music",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.AO_TO_NATSU_MUSIC_DISC!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_music"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.AO_TO_NATSU_MUSIC_DISC!!.get())
                })
                .build()
        })

    val JAPANESE_ARMOR: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_armor",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.PYRITE_HELMET!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_armor"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_HELMET!!.get())
                    output.accept(ModItems.PYRITE_CHESTPLATE!!.get())
                    output.accept(ModItems.PYRITE_LEGGINGS!!.get())
                    output.accept(ModItems.PYRITE_BOOTS!!.get())
                    output.accept(ModItems.NEPHRITE_HELMET!!.get())
                    output.accept(ModItems.NEPHRITE_CHESTPLATE!!.get())
                    output.accept(ModItems.NEPHRITE_LEGGINGS!!.get())
                    output.accept(ModItems.NEPHRITE_BOOTS!!.get())
                    output.accept(ModItems.PYRITE_HORSE_ARMOR!!.get())
                    output.accept(ModItems.SAMURAI_HELMET!!.get())
                    output.accept(ModItems.SAMURAI_CHESTPLATE!!.get())
                    output.accept(ModItems.SAMURAI_LEGGINGS!!.get())
                    output.accept(ModItems.SAMURAI_BOOTS!!.get())
                })
                .build()
        })


    val JAPANESE_ARMOR_TRIMS: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_armor_trims",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_armor_trims"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE!!.get())
                })
                .build()
        })


    val JAPANESE_FOOD: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_food",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.SUSHI!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_food"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.SUSHI!!.get())
                    output.accept(ModItems.UDON!!.get())
                    output.accept(ModBlocks.CHOCOLATE_CAKE.get())
                    output.accept(ModBlocks.JAPANESE_CHEESECAKE.get())
                    output.accept(ModItems.RICE_SEEDS!!.get())
                    output.accept(ModItems.RICE!!.get())
                    output.accept(ModItems.RAW_RICE!!.get())
                    output.accept(ModItems.YAMAZAKI_BERRIES!!.get())
                })
                .build()
        })

    val JAPANESE_DRINKS: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_drinks",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.GREEN_TEA!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_drinks"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.GREEN_TEA!!.get())
                })
                .build()
        })

    val JAPANESE_FUEL: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_fuel",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.DIESEL!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_fuel"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.DIESEL!!.get())
                    output.accept(ModItems.INCENSE!!.get())
                })
                .build()
        })

    val JAPANESE_TOOLS: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_tools",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModItems.PYRITE_AXE!!.get()) })
                .withTabsBefore(JAPANESE_ITEMS.getId())
                .title(Component.translatable("creativetab.everythingjapanese.japanese_tools"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_AXE!!.get())
                    output.accept(ModItems.PYRITE_PICKAXE!!.get())
                    output.accept(ModItems.PYRITE_SHOVEL!!.get())
                    output.accept(ModItems.PYRITE_HOE!!.get())
                    output.accept(ModItems.NEPHRITE_AXE!!.get())
                    output.accept(ModItems.NEPHRITE_PICKAXE!!.get())
                    output.accept(ModItems.NEPHRITE_SHOVEL!!.get())
                    output.accept(ModItems.NEPHRITE_HOE!!.get())
                    output.accept(ModItems.PYRITE_HAMMER!!.get())
                })
                .build()
        })

    val JAPANESE_FURNITURE: RegistryObject<CreativeModeTab?> = CREATIVE_MODE_TABS.register<CreativeModeTab?>(
        "japanese_furniture",
        Supplier {
            CreativeModeTab.builder().icon(Supplier { ItemStack(ModBlocks.CHAIR.get()) })
                .title(Component.translatable("creativetab.everythingjapanese.japanese_furniture"))
                .displayItems(DisplayItemsGenerator { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModBlocks.CHAIR.get())
                    output.accept(ModBlocks.PEDESTAL.get())
                    output.accept(ModBlocks.GROWTH_CHAMBER.get())
                }).build()
        })

    fun register(eventBus: BusGroup?) {
        CREATIVE_MODE_TABS.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE CREATIVEMODETAB IN MODCREATIVEMODETABS")
    }
}
