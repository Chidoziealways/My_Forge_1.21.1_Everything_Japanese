package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class EnglishLanguageProvider(output: PackOutput): LanguageProvider(output, MOD_ID, "en_us") {
    override fun addTranslations() {
        // ADVANCEMENTS
        add("advancements.hell.root.title", "Hell")
        add("advancements.hell.root.description", "There's no escape")

        // ARGUMENTS
        add("argument.jutsu.id.invalid", "Unknown jutsu '%s'")
        add("arguments.jutsu.component.unknown", "Unknown jutsu component '%s'")
        add("arguments.jutsu.component.malformed", "Malformed '%s' component: '%s'")
        add("arguments.jutsu.component.expected", "Expected jutsu component")
        add("arguments.jutsu.component.repeated", "Jutsu component '%s' was repeated, but only one value can be specified")
        add("arguments.jutsu.malformed", "Malformed jutsu: '%s'")

        // BLOCKS
        add(ModBlocks.PYRITE_BLOCK, "Block of Pyrite")
        add(ModBlocks.NEPHRITE_BLOCK, "Block of Nephrite")
        add(ModBlocks.RAW_PYRITE_BLOCK, "Block of Raw Pyrite")
        add(ModBlocks.PYRITE_ORE, "Pyrite Ore")
        add(ModBlocks.PYRITE_DEEPSLATE_ORE, "Pyrite Deepslate Ore")
        add(ModBlocks.NEPHRITE_ORE, "Nephrite Ore")
        add(ModBlocks.NEPHRITE_DEEPSLATE_ORE, "Nephrite Deepslate Ore")
        add(ModBlocks.TRANSFORMER_BLOCK, "Transformer Block")
        add(ModBlocks.HINOKI_MARUTA, "Cypress Log")
        add(ModBlocks.HINOKI_MOKUZAI, "Cypress Wood")
        add(ModBlocks.STRIPPED_HINOKI_MARUTA, "Stripped Cypress Log")
        add(ModBlocks.STRIPPED_HINOKI_MOKUZAI, "Stripped Cypress Wood")
        add(ModBlocks.HINOKI_BAN, "Cypress Planks")
        add(ModBlocks.HINOKI_HA, "Cypress Leaves")
        add(ModBlocks.HINOKI_NAEGI, "Cypress Saplings")
        add(ModBlocks.CHOCOLATE_CAKE, "Chocolate Cake")
        add(ModBlocks.JAPANESE_CHEESECAKE, "Japanese Cheesecake")
        add(ModBlocks.PYRITE_STAIRS, "Pyrite Stairs")
        add(ModBlocks.PYRITE_SLAB, "Pyrite Slab")
        add(ModBlocks.PYRITE_BUTTON, "Pyrite Button")
        add(ModBlocks.PYRITE_PRESSURE_PLATE, "Pyrite Pressure Plate")
        add(ModBlocks.PYRITE_FENCE, "Pyrite Fence")
        add(ModBlocks.PYRITE_FENCE_GATE, "Pyrite Fence Gate")
        add(ModBlocks.PYRITE_WALL, "Pyrite Wall")
        add(ModBlocks.PYRITE_DOOR, "Pyrite Door")
        add(ModBlocks.PYRITE_TRAPDOOR, "Pyrite Trapdoor")
        add(ModBlocks.PYRITE_LAMP, "Pyrite Lamp")
        add(ModBlocks.WASHI_WINDOW_PANE, "Washi Window Pane")
        add(ModBlocks.WASHI_WINDOW, "Washi Window")
        add(ModBlocks.SHOJI_DOOR, "Shoji Door")
        add(ModBlocks.CHABUDAI, "Chabudai")
        add(ModBlocks.FUSUMA_DOOR, "Fusuma Door")
        add(ModBlocks.RICE_CROP, "Rice Crop")
        add(ModBlocks.YAMAZAKI_BERRY_BUSH, "Yamazaki Berry Bush")
        add(ModBlocks.CHAIR, "Chair")
        add(ModBlocks.PEDESTAL, "Pedestal")
        add(ModBlocks.GROWTH_CHAMBER, "Growth Chamber")
        add(ModBlocks.TATAMI_MAT, "Tatami Mat")
        add(ModBlocks.ZABUTON_BLUE, "Zabuton Blue")
        add(ModBlocks.ZABUTON_RED, "Zabuton Red")
        add(ModBlocks.ZABUTON_GREEN, "Zabuton Green")

        // CREATIVE TABS
        add("creativetab.everythingjapanese.japanese_items", "Japanese Items")
        add("creativetab.everythingjapanese.japanese_blocks", "Japanese Blocks")
        add("creativetab.everythingjapanese.japanese_weapons", "Japanese Weapons")
        add("creativetab.everythingjapanese.japanese_food", "Japanese Food")
        add("creativetab.everythingjapanese.japanese_drinks", "Japanese Drinks")
        add("creativetab.everythingjapanese.japanese_fuel", "Japanese Fuel")
        add("creativetab.everythingjapanese.japanese_tools", "Japanese Tools")
        add("creativetab.everythingjapanese.japanese_armor", "Japanese Armor")
        add("creativetab.everythingjapanese.japanese_armor_trims", "Japanese Armor Trims")
        add("creativetab.everythingjapanese.japanese_music", "Japanese Music")
        add("creativetab.everythingjapanese.japanese_entities", "Japanese Entities")
        add("creativetab.everythingjapanese.japanese_furniture", "Japanese Furniture")
        add("creativetab.everythingjapanese.hell", "Hell")
        add("creativetab.everythingjapanese.katana_addons", "Katana Addons")

        // EFFECTS
        add(ModEffects.ADRENALINE_EFFECT.get(), "Adrenaline")

        // ENCHANTMENTS
        add("enchantment.everythingjapanese.lightning_striker", "Lightning Striker")

        // ENTITIES
        add(ModEntities.TRICERATOPS, "Triceratops")
        add(ModEntities.CURSED_SAMURAI, "Cursed Samurai")
        add(ModEntities.SIKA_DEER, "Sika Deer")
        add(ModEntities.IRON_BATTLE_AXE, "Iron Battle Axe")
        add("entity.minecraft.villager.everythingjapanese.furniture_maker", "Furniture Maker")

        // GUI
        add("gui.quests", "Quests")

        // ITEMS
        add(ModItems.PYRITE_INGOT, "Pyrite Ingot")
        add(ModItems.NEPHRITE, "Nephrite")
        add(ModItems.RAW_PYRITE, "Raw Pyrite")
        add(ModItems.CHISEL, "Chisel")
        add(ModItems.PYRITE_SWORD, "Pyrite Sword")
        add(ModItems.NEPHRITE_SWORD, "Nephrite Sword")
        add(ModItems.SUSHI, "Sushi")
        add(ModItems.RICE, "Rice")
        add(ModItems.RICE_SEEDS, "Rice Seeds")
        add(ModItems.RAW_RICE, "Raw Rice")
        add(ModItems.GREEN_TEA, "Green Tea")
        add(ModItems.DIESEL, "Diesel")
        add(ModItems.INCENSE, "Incense")
        add(ModItems.UDON, "Udon")
        add(ModItems.HELL_PORTAL_ACTIVATOR, "Hell Portal Activator")
        add(ModItems.YA, "Ya")
        add(ModItems.TALISMAN_ITEM, "Talisman")
        add(ModItems.PYRITE_AXE, "Pyrite Axe")
        add(ModItems.PYRITE_PICKAXE, "Pyrite Pickaxe")
        add(ModItems.PYRITE_SHOVEL, "Pyrite Shovel")
        add(ModItems.PYRITE_HOE, "Pyrite Hoe")
        add(ModItems.NEPHRITE_AXE, "Nephrite Axe")
        add(ModItems.NEPHRITE_PICKAXE, "Nephrite Pickaxe")
        add(ModItems.NEPHRITE_SHOVEL, "Nephrite Shovel")
        add(ModItems.NEPHRITE_HOE, "Nephrite Hoe")
        add(ModItems.PYRITE_HELMET, "Pyrite Helmet")
        add(ModItems.PYRITE_CHESTPLATE, "Pyrite Chestplate")
        add(ModItems.CHIRETSU_SHO_SCROLL, "Chiretsu Shō Jutsu Scroll")
        add(ModItems.FIREBALL_SCROLL, "Fireball Jutsu Scroll")
        add(ModItems.WINDBALL_SCROLL, "Windball Jutsu Scroll")
        add(ModItems.PYRITE_LEGGINGS, "Pyrite Leggings")
        add(ModItems.PYRITE_BOOTS, "Pyrite Boots")
        add(ModItems.NEPHRITE_HELMET, "Nephrite Helmet")
        add(ModItems.NEPHRITE_CHESTPLATE, "Nephrite Chestplate")
        add(ModItems.NEPHRITE_LEGGINGS, "Nephrite Leggings")
        add(ModItems.NEPHRITE_BOOTS, "Nephrite Boots")
        add(ModItems.SAMURAI_HELMET, "Samurai Helmet")
        add(ModItems.SAMURAI_CHESTPLATE, "Samurai Chestplate")
        add(ModItems.SAMURAI_LEGGINGS, "Samurai Leggings")
        add(ModItems.SAMURAI_BOOTS, "Samurai Boots")
        add(ModItems.PYRITE_HAMMER, "Pyrite Hammer")
        add(ModItems.PYRITE_HORSE_ARMOR, "Pyrite Horse Armor")
        add(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "Koi Fish Smithing Template")
        add(ModItems.DAIKYU, "Long Bow")
        add(ModItems.AO_TO_NATSU_MUSIC_DISC, "Ao to Natsu Music Disc")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - Ao to Natsu")
        add("item.minecraft.potion.effect.adrenaline_potion", "Adrenaline Potion")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "Adrenaline Splash Potion")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "Adrenaline Lingering Potion")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "Adrenaline Tipped Arrow")
        add(ModItems.YAMAZAKI_BERRIES, "Yamazaki Berries")
        add(ModItems.TRICERATOPS_SPAWN_EGG, "Triceratops Spawn Egg")
        add(ModItems.CURSED_SAMURAI_SPAWN_EGG, "Cursed Samurai Spawn Egg")
        add(ModItems.SIKA_DEER_SPAWN_EGG, "Sika Deer Spawn Egg")
        add(ModItems.PYRITE_BATTLE_AXE, "Pyrite Battle Axe")
        add(ModItems.IRON_BATTLE_AXE, "Iron Battle Axe")
        add(ModItems.RADIATION_STAFF, "Radiation Staff")
        add(ModItems.KATANA, "Katana")
        add(ModItems.BLADE_STEEL, "Katana Blade Steel")
        add(ModItems.BLACK_WRAP, "Katana Black Wrap")
        add(ModItems.RED_WRAP, "Katana Red Wrap")
        add(ModItems.WHITE_WRAP, "Katana White Wrap")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "Windball")
        add("jutsu.everythingjapanese.fireball", "Fireball")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "Chiretsu Shō")

        // KEYS
        add("key.everythingjapanese.cast_jutsu", "Cast Jutsu")
        add("key.everythingjapanese.cycle_jutsu", "Cycle Jutsu")
        add("key.categories.jutsu", "Jutsu")
        add("key.everythingjapanese.show_quests", "Show Quest")
        add("key.categories.quest", "Quest")

        // PAINTINGS
        add("painting.everythingjapanese.samurai.title", "Samurai")
        add("painting.everythingjapanese.samurai.author", "Chidoziealways")

        // SOUNDS
        add("sounds.everythingjapanese.chisel_use", "Chisel Used")
        add("sounds.everythingjapanese.magic_block_break", "Magic Block Broken")
        add("sounds.everythingjapanese.magic_block_fall", "Fell on Magic Block")
        add("sounds.everythingjapanese.magic_block_hit", "Magic Block was Hit")
        add("sounds.everythingjapanese.magic_block_place", "Magic Block was Placed")
        add("sounds.everythingjapanese.magic_block_step", "Magic Block was Stepped on")

        // TOOLTIPS
        add("tooltip.everythingjapanese.magic_block.tooltip", "A block that can transform any item thrown on it to another completely different item")
        add("tooltip.everythingjapanese.chisel_item.shift_down", "Press Shift to see more of this item!")
        add("tooltip.everythingjapanese.chisel_item", "An item that turns certain items to other items")
        add("tooltip.everythingjapanese.sushi", "A very popular Japanese food.")
        add("tooltip.everythingjapanese.earth_katana", "The Wooden Katana. Capable of unspeakable things. Even capable of summoning unknown things including THE VERY DEMONS OF HELL")
        add("tooltip.everythingjapanese.earth_katana.no_shift", "Press Shift to see more of this awesome item")

        // TRIM PATTERNS & MATERIALS
        add("trim_pattern.everythingjapanese.koi_fish", "Koi Fish Armor Trim")
        add("trim_material.everythingjapanese.pyrite", "Pyrite Material")
    }
}
