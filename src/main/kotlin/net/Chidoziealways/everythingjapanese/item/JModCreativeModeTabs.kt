package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters
import net.minecraft.world.item.ItemStack
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object JModCreativeModeTabs {
    val CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JAPANESE_MOD_ID)

    val JAPANESE_ITEMS = CREATIVE_MODE_TABS.register(
        "japanese_items",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.PYRITE_INGOT) }
                .title(Component.translatable("creativetab.everythingjapanese.japanese_items"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.PYRITE_INGOT)
                    output.accept(JModItems.RAW_PYRITE)
                    output.accept(JModItems.NEPHRITE)
                    output.accept(JModItems.CHISEL)
                    output.accept(JModItems.RADIATION_STAFF)
                    output.accept { JModItems.TALISMAN_ITEM }
                    output.accept(JModItems.BLOOD_BUCKET)
                    //output.accept(JModItems.SCROLL)
                }
                .build()
        })

    val JUTSU = CREATIVE_MODE_TABS.register(
        "jutsu",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.FIREBALL_SCROLL) }
                .title(Component.literal("Jutsu"))
                .displayItems { itemDisplayParameters: ItemDisplayParameters, output: CreativeModeTab.Output ->
                    output.accept(JModItems.WINDBALL_SCROLL)
                    output.accept(JModItems.CHIRETSU_SHO_SCROLL)
                    output.accept(JModItems.EKIRETSU_SHO_SCROLL)
                    output.accept(JModItems.FIREBALL_SCROLL)
                }
                .build()
        })

    /*val HELL = CREATIVE_MODE_TABS.register(
        "hell",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.HELL_PORTAL_ACTIVATOR) }
                .title(Component.translatable("creativetab.everythingjapanese.hell"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    //output.accept(JModItems.HELL_PORTAL_ACTIVATOR)
                }
                .build()
        })*/

    val JAPANESE_ENTITIES = CREATIVE_MODE_TABS.register(
        "japanese_entities",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.TRICERATOPS_SPAWN_EGG) }
                .title(Component.translatable("creativetab.everythingjapanese.japanese_entities"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.TRICERATOPS_SPAWN_EGG)
                    output.accept(JModItems.SIKA_DEER_SPAWN_EGG)
                    output.accept(JModItems.CURSED_SAMURAI_SPAWN_EGG)
                }
                .build()
        })

    val JAPANESE_BLOCKS = CREATIVE_MODE_TABS.register(
        "japanese_blocks",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModBlocks.PYRITE_BLOCK) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_blocks"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModBlocks.PYRITE_BLOCK)
                    output.accept(JModBlocks.RAW_PYRITE_BLOCK)
                    output.accept(JModBlocks.PYRITE_ORE)
                    output.accept(JModBlocks.PYRITE_DEEPSLATE_ORE)
                    output.accept(JModBlocks.NEPHRITE_ORE)
                    output.accept(JModBlocks.NEPHRITE_DEEPSLATE_ORE)
                    output.accept(JModBlocks.HINOKI_MARUTA)
                    output.accept(JModBlocks.PYRITE_BUTTON)
                    output.accept(JModBlocks.PYRITE_DOOR)
                    output.accept(JModBlocks.PYRITE_FENCE)
                    output.accept(JModBlocks.PYRITE_FENCE_GATE)
                    output.accept(JModBlocks.PYRITE_PRESSURE_PLATE)
                    output.accept(JModBlocks.PYRITE_SLAB)
                    output.accept(JModBlocks.PYRITE_STAIRS)
                    output.accept(JModBlocks.PYRITE_TRAPDOOR)
                    output.accept(JModBlocks.PYRITE_WALL)
                    output.accept(JModBlocks.PYRITE_LAMP)
                    output.accept(JModBlocks.HINOKI_MARUTA)
                    output.accept(JModBlocks.HINOKI_MOKUZAI)
                    output.accept(JModBlocks.STRIPPED_HINOKI_MARUTA)
                    output.accept(JModBlocks.STRIPPED_HINOKI_MOKUZAI)
                    output.accept(JModBlocks.HINOKI_BAN)
                    output.accept(JModBlocks.HINOKI_HA)
                    output.accept(JModBlocks.HINOKI_NAEGI)
                    output.accept(JModBlocks.JAPANESE_FLAG)
                }
                .build()
        })

    val KATANA_ADDONS = CREATIVE_MODE_TABS.register(
        "katana_addons",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.BLADE_STEEL) }
                .withTabsBefore(JAPANESE_WEAPONS.id)
                .title(Component.translatable("creativetab.everythingjapanese.katana_addons"))
                .displayItems { _, output ->
                    output.accept { JModItems.BLADE_STEEL }
                    output.accept { JModItems.BLACK_WRAP }
                    output.accept { JModItems.RED_WRAP }
                    output.accept { JModItems.WHITE_WRAP }
                }
                .build()
        }
    )

    val JAPANESE_WEAPONS = CREATIVE_MODE_TABS.register(
        "japanese_weapons",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.PYRITE_SWORD) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_weapons"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.PYRITE_SWORD)
                    output.accept(JModItems.YA)
                    output.accept(JModItems.DAIKYU)
                    output.accept(JModItems.PYRITE_BATTLE_AXE)
                    output.accept(JModItems.NEPHRITE_SWORD)
                    output.accept(JModItems.IRON_BATTLE_AXE)
                    output.accept(JModItems.KATANA)
                    output.accept(JModItems.SOUL_DAGGER)
                    output.accept(JModItems.GUN)
                    output.accept(JModItems.BULLET)
                    output.accept(JModItems.POWERED_SWORD)
                }
                .build()
        })

    val JAPANESE_MUSIC = CREATIVE_MODE_TABS.register(
        "japanese_music",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.AO_TO_NATSU_MUSIC_DISC) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_music"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.AO_TO_NATSU_MUSIC_DISC)
                }
                .build()
        })

    val JAPANESE_ARMOR = CREATIVE_MODE_TABS.register(
        "japanese_armor",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.PYRITE_HELMET) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_armor"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.PYRITE_HELMET)
                    output.accept(JModItems.PYRITE_CHESTPLATE)
                    output.accept(JModItems.PYRITE_LEGGINGS)
                    output.accept(JModItems.PYRITE_BOOTS)
                    output.accept(JModItems.NEPHRITE_HELMET)
                    output.accept(JModItems.NEPHRITE_CHESTPLATE)
                    output.accept(JModItems.NEPHRITE_LEGGINGS)
                    output.accept(JModItems.NEPHRITE_BOOTS)
                    output.accept(JModItems.PYRITE_HORSE_ARMOR)
                    output.accept(JModItems.SAMURAI_HELMET)
                    output.accept(JModItems.SAMURAI_CHESTPLATE)
                    output.accept(JModItems.SAMURAI_LEGGINGS)
                    output.accept(JModItems.SAMURAI_BOOTS)
                }
                .build()
        })


    val JAPANESE_ARMOR_TRIMS = CREATIVE_MODE_TABS.register(
        "japanese_armor_trims",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_armor_trims"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE)
                }
                .build()
        })


    val JAPANESE_FOOD = CREATIVE_MODE_TABS.register(
        "japanese_food",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.SUSHI) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_food"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.SUSHI)
                    output.accept(JModItems.UDON)
                    output.accept(JModBlocks.CHOCOLATE_CAKE)
                    output.accept(JModBlocks.JAPANESE_CHEESECAKE)
                    output.accept(JModItems.RICE_SEEDS)
                    output.accept(JModItems.RICE)
                    output.accept(JModItems.RAW_RICE)
                    output.accept(JModItems.YAMAZAKI_BERRIES)
                }
                .build()
        })

    val JAPANESE_DRINKS = CREATIVE_MODE_TABS.register(
        "japanese_drinks",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.GREEN_TEA) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_drinks"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.GREEN_TEA)
                }
                .build()
        })

    val JAPANESE_FUEL = CREATIVE_MODE_TABS.register(
        "japanese_fuel",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.DIESEL) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_fuel"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.DIESEL)
                    output.accept(JModItems.INCENSE)
                }
                .build()
        })

    val JAPANESE_TOOLS = CREATIVE_MODE_TABS.register(
        "japanese_tools",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModItems.PYRITE_AXE) }
                .withTabsBefore(JAPANESE_ITEMS.id)
                .title(Component.translatable("creativetab.everythingjapanese.japanese_tools"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModItems.PYRITE_AXE)
                    output.accept(JModItems.PYRITE_PICKAXE)
                    output.accept(JModItems.PYRITE_SHOVEL)
                    output.accept(JModItems.PYRITE_HOE)
                    output.accept(JModItems.NEPHRITE_AXE)
                    output.accept(JModItems.NEPHRITE_PICKAXE)
                    output.accept(JModItems.NEPHRITE_SHOVEL)
                    output.accept(JModItems.NEPHRITE_HOE)
                    output.accept(JModItems.PYRITE_HAMMER)
                }
                .build()
        })

    val JAPANESE_FURNITURE = CREATIVE_MODE_TABS.register(
        "japanese_furniture",
        Supplier {
            CreativeModeTab.builder().icon { ItemStack(JModBlocks.CHAIR) }
                .title(Component.translatable("creativetab.everythingjapanese.japanese_furniture"))
                .displayItems { _, output: CreativeModeTab.Output ->
                    output.accept(JModBlocks.CHAIR)
                    output.accept(JModBlocks.PEDESTAL)
                    output.accept(JModBlocks.HANGING_SCROLL)
                    output.accept(JModBlocks.ZABUTON_BLUE)
                    output.accept(JModBlocks.ZABUTON_RED)
                    output.accept(JModBlocks.ZABUTON_GREEN)
                    output.accept(JModBlocks.TATAMI_MAT)
                    output.accept(JModBlocks.MONEY_VAULT_BLOCK)
                    output.accept(JModItems.CREDIT_CARD_ITEM)
                    output.accept(JModBlocks.WASHI_WINDOW)
                    output.accept(JModBlocks.WASHI_WINDOW_PANE)
                    output.accept(JModBlocks.SHOJI_WINDOW)
                    output.accept(JModBlocks.SHOJI_WINDOW_PANE)
                    output.accept(JModBlocks.SHOJI_DOOR)
                    output.accept(JModBlocks.CHABUDAI)
                    output.accept(JModBlocks.FUSUMA_DOOR)
                    output.accept(JModBlocks.GROWTH_CHAMBER)
                    output.accept(JModBlocks.PYRITE_BUTTON)
                    output.accept(JModBlocks.PYRITE_DOOR)
                    output.accept(JModBlocks.PYRITE_FENCE)
                    output.accept(JModBlocks.PYRITE_FENCE_GATE)
                    output.accept(JModBlocks.PYRITE_PRESSURE_PLATE)
                    output.accept(JModBlocks.PYRITE_SLAB)
                    output.accept(JModBlocks.PYRITE_STAIRS)
                    output.accept(JModBlocks.PYRITE_TRAPDOOR)
                    output.accept(JModBlocks.PYRITE_WALL)
                    output.accept(JModBlocks.PYRITE_LAMP)
                    output.accept(JModBlocks.HINOKI_BUTTON)
                    output.accept(JModBlocks.HINOKI_FENCE)
                    output.accept(JModBlocks.HINOKI_FENCE_GATE)
                    output.accept(JModBlocks.HINOKI_PRESSURE_PLATE)
                    output.accept(JModBlocks.HINOKI_SLAB)
                    output.accept(JModBlocks.HINOKI_STAIRS)
                    //output.accept(JModBlocks.CALLIGRAPHY_TABLE)
                }.build()
        })

    fun register(eventBus: IEventBus) {
        CREATIVE_MODE_TABS.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE CREATIVEMODETAB IN MODCREATIVEMODETABS")
    }
}
