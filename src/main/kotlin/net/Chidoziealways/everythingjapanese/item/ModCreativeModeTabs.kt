package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters
import net.minecraft.world.item.ItemStack
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModCreativeModeTabs {
    val CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID)

    val JAPANESE_ITEMS = CREATIVE_MODE_TABS.register(
        "japanese_items",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.PYRITE_INGOT) }
                .title(Component.translatable("creativetab.everythingjapanese.japanese_items"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_INGOT)
                    output.accept(ModItems.RAW_PYRITE)
                    output.accept(ModItems.NEPHRITE)
                    output.accept(ModItems.CHISEL)
                    output.accept(ModItems.RADIATION_STAFF)
                    output.accept { ModItems.TALISMAN_ITEM }
                }
                .build()
        })

    val JUTSU = CREATIVE_MODE_TABS.register(
        "jutsu",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.FIREBALL_SCROLL) }
                .title(Component.literal("Jutsu"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters, output: CreativeModeTab.Output ->
                    output.accept(ModItems.WINDBALL_SCROLL)
                    output.accept(ModItems.CHIRETSU_SHO_SCROLL)
                    output.accept(ModItems.FIREBALL_SCROLL)
                }
                .build()
        })

    val HELL = CREATIVE_MODE_TABS.register(
        "hell",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.HELL_PORTAL_ACTIVATOR) }
                .title(Component.translatable("creativetab.everythingjapanese.hell"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.HELL_PORTAL_ACTIVATOR)
                }
                .build()
        })

    val JAPANESE_ENTITIES = CREATIVE_MODE_TABS.register(
        "japanese_entities",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.TRICERATOPS_SPAWN_EGG) }
                .title(Component.translatable("creativetab.everythingjapanese.japanese_entities"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.TRICERATOPS_SPAWN_EGG)
                    output.accept(ModItems.SIKA_DEER_SPAWN_EGG)
                    output.accept(ModItems.CURSED_SAMURAI_SPAWN_EGG)
                }
                .build()
        })

    val JAPANESE_BLOCKS = CREATIVE_MODE_TABS.register(
        "japanese_blocks",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModBlocks.PYRITE_BLOCK) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_blocks"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModBlocks.PYRITE_BLOCK)
                    output.accept(ModBlocks.RAW_PYRITE_BLOCK)
                    output.accept(ModBlocks.PYRITE_ORE)
                    output.accept(ModBlocks.PYRITE_DEEPSLATE_ORE)
                    output.accept(ModBlocks.NEPHRITE_ORE)
                    output.accept(ModBlocks.NEPHRITE_DEEPSLATE_ORE)
                    output.accept(ModBlocks.HINOKI_MARUTA)
                    output.accept(ModBlocks.PYRITE_BUTTON)
                    output.accept(ModBlocks.PYRITE_DOOR)
                    output.accept(ModBlocks.PYRITE_FENCE)
                    output.accept(ModBlocks.PYRITE_FENCE_GATE)
                    output.accept(ModBlocks.PYRITE_PRESSURE_PLATE)
                    output.accept(ModBlocks.PYRITE_SLAB)
                    output.accept(ModBlocks.PYRITE_STAIRS)
                    output.accept(ModBlocks.PYRITE_TRAPDOOR)
                    output.accept(ModBlocks.PYRITE_WALL)
                    output.accept(ModBlocks.PYRITE_LAMP)
                    output.accept(ModBlocks.HINOKI_MARUTA)
                    output.accept(ModBlocks.HINOKI_MOKUZAI)
                    output.accept(ModBlocks.STRIPPED_HINOKI_MARUTA)
                    output.accept(ModBlocks.STRIPPED_HINOKI_MOKUZAI)
                    output.accept(ModBlocks.HINOKI_BAN)
                    output.accept(ModBlocks.HINOKI_HA)
                    output.accept(ModBlocks.HINOKI_NAEGI)
                }
                .build()
        })

    val KATANA_ADDONS = CREATIVE_MODE_TABS.register(
        "katana_addons",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.BLADE_STEEL) }
                .withTabsBefore(JAPANESE_WEAPONS.id)
                .title(Component.translatable("creativetab.everythingjapanese.katana_addons"))
                .displayItems { itemDisplayParameters, output ->
                    output.accept { ModItems.BLADE_STEEL }
                    output.accept { ModItems.BLACK_WRAP }
                    output.accept { ModItems.RED_WRAP }
                    output.accept { ModItems.WHITE_WRAP }
                }
                .build()
        }
    )

    val JAPANESE_WEAPONS = CREATIVE_MODE_TABS.register(
        "japanese_weapons",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.PYRITE_SWORD) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_weapons"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_SWORD)
                    output.accept(ModItems.YA)
                    output.accept(ModItems.DAIKYU)
                    output.accept(ModItems.PYRITE_BATTLE_AXE)
                    output.accept(ModItems.NEPHRITE_SWORD)
                    output.accept(ModItems.IRON_BATTLE_AXE)
                    output.accept(ModItems.KATANA)
                }
                .build()
        })

    val JAPANESE_MUSIC = CREATIVE_MODE_TABS.register(
        "japanese_music",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.AO_TO_NATSU_MUSIC_DISC) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_music"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.AO_TO_NATSU_MUSIC_DISC)
                }
                .build()
        })

    val JAPANESE_ARMOR = CREATIVE_MODE_TABS.register(
        "japanese_armor",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.PYRITE_HELMET) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_armor"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_HELMET)
                    output.accept(ModItems.PYRITE_CHESTPLATE)
                    output.accept(ModItems.PYRITE_LEGGINGS)
                    output.accept(ModItems.PYRITE_BOOTS)
                    output.accept(ModItems.NEPHRITE_HELMET)
                    output.accept(ModItems.NEPHRITE_CHESTPLATE)
                    output.accept(ModItems.NEPHRITE_LEGGINGS)
                    output.accept(ModItems.NEPHRITE_BOOTS)
                    output.accept(ModItems.PYRITE_HORSE_ARMOR)
                    output.accept(ModItems.SAMURAI_HELMET)
                    output.accept(ModItems.SAMURAI_CHESTPLATE)
                    output.accept(ModItems.SAMURAI_LEGGINGS)
                    output.accept(ModItems.SAMURAI_BOOTS)
                }
                .build()
        })


    val JAPANESE_ARMOR_TRIMS = CREATIVE_MODE_TABS.register(
        "japanese_armor_trims",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_armor_trims"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE)
                }
                .build()
        })


    val JAPANESE_FOOD = CREATIVE_MODE_TABS.register(
        "japanese_food",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.SUSHI) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_food"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.SUSHI)
                    output.accept(ModItems.UDON)
                    output.accept(ModBlocks.CHOCOLATE_CAKE)
                    output.accept(ModBlocks.JAPANESE_CHEESECAKE)
                    output.accept(ModItems.RICE_SEEDS)
                    output.accept(ModItems.RICE)
                    output.accept(ModItems.RAW_RICE)
                    output.accept(ModItems.YAMAZAKI_BERRIES)
                }
                .build()
        })

    val JAPANESE_DRINKS = CREATIVE_MODE_TABS.register(
        "japanese_drinks",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.GREEN_TEA) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_drinks"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.GREEN_TEA)
                }
                .build()
        })

    val JAPANESE_FUEL = CREATIVE_MODE_TABS.register(
        "japanese_fuel",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.DIESEL) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_fuel"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.DIESEL)
                    output.accept(ModItems.INCENSE)
                }
                .build()
        })

    val JAPANESE_TOOLS = CREATIVE_MODE_TABS.register(
        "japanese_tools",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModItems.PYRITE_AXE) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_tools"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters?, output: CreativeModeTab.Output? ->
                    output!!.accept(ModItems.PYRITE_AXE)
                    output.accept(ModItems.PYRITE_PICKAXE)
                    output.accept(ModItems.PYRITE_SHOVEL)
                    output.accept(ModItems.PYRITE_HOE)
                    output.accept(ModItems.NEPHRITE_AXE)
                    output.accept(ModItems.NEPHRITE_PICKAXE)
                    output.accept(ModItems.NEPHRITE_SHOVEL)
                    output.accept(ModItems.NEPHRITE_HOE)
                    output.accept(ModItems.PYRITE_HAMMER)
                }
                .build()
        })

    val JAPANESE_FURNITURE = CREATIVE_MODE_TABS.register(
        "japanese_furniture",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(ModBlocks.CHAIR) }
                .title(Component.translatable("creativetab.everythingjapanese.japanese_furniture"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters, output: CreativeModeTab.Output ->
                    output.accept(ModBlocks.CHAIR)
                    output.accept(ModBlocks.PEDESTAL)
                    output.accept(ModBlocks.ZABUTON_BLUE)
                    output.accept(ModBlocks.ZABUTON_RED)
                    output.accept(ModBlocks.ZABUTON_GREEN)
                    output.accept(ModBlocks.TATAMI_MAT)
                    output.accept(ModBlocks.MONEY_VAULT_BLOCK)
                    output.accept(ModItems.CREDIT_CARD_ITEM)
                    output.accept(ModBlocks.WASHI_WINDOW)
                    output.accept(ModBlocks.WASHI_WINDOW_PANE)
                    output.accept(ModBlocks.SHOJI_DOOR)
                    output.accept(ModBlocks.CHABUDAI)
                    output.accept(ModBlocks.FUSUMA_DOOR)
                    output.accept(ModBlocks.GROWTH_CHAMBER)
                }.build()
        })

    fun register(eventBus: IEventBus) {
        CREATIVE_MODE_TABS.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE CREATIVEMODETAB IN MODCREATIVEMODETABS")
    }
}
