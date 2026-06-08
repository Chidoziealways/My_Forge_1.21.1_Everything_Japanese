package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class EnglishLanguageProvider(output: PackOutput): LanguageProvider(output, JAPANESE_MOD_ID, "en_us") {
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
        add(JModBlocks.PYRITE_BLOCK, "Block of Pyrite")
        add(JModBlocks.NEPHRITE_BLOCK, "Block of Nephrite")
        add(JModBlocks.RAW_PYRITE_BLOCK, "Block of Raw Pyrite")
        add(JModBlocks.PYRITE_ORE, "Pyrite Ore")
        add(JModBlocks.PYRITE_DEEPSLATE_ORE, "Pyrite Deepslate Ore")
        add(JModBlocks.NEPHRITE_ORE, "Nephrite Ore")
        add(JModBlocks.NEPHRITE_DEEPSLATE_ORE, "Nephrite Deepslate Ore")
        add(JModBlocks.TRANSFORMER_BLOCK, "Transformer Block")
        add(JModBlocks.HINOKI_MARUTA, "Cypress Log")
        add(JModBlocks.HINOKI_MOKUZAI, "Cypress Wood")
        add(JModBlocks.STRIPPED_HINOKI_MARUTA, "Stripped Cypress Log")
        add(JModBlocks.STRIPPED_HINOKI_MOKUZAI, "Stripped Cypress Wood")
        add(JModBlocks.HINOKI_BAN, "Cypress Planks")
        add(JModBlocks.HINOKI_HA, "Cypress Leaves")
        add(JModBlocks.HINOKI_NAEGI, "Cypress Saplings")
        add(JModBlocks.CHOCOLATE_CAKE, "Chocolate Cake")
        add(JModBlocks.JAPANESE_CHEESECAKE, "Japanese Cheesecake")
        add(JModBlocks.PYRITE_STAIRS, "Pyrite Stairs")
        add(JModBlocks.PYRITE_SLAB, "Pyrite Slab")
        add(JModBlocks.PYRITE_BUTTON, "Pyrite Button")
        add(JModBlocks.PYRITE_PRESSURE_PLATE, "Pyrite Pressure Plate")
        add(JModBlocks.PYRITE_FENCE, "Pyrite Fence")
        add(JModBlocks.PYRITE_FENCE_GATE, "Pyrite Fence Gate")

        // HINOKI
        add(JModBlocks.HINOKI_STAIRS, "Cypress Stairs")
        add(JModBlocks.HINOKI_SLAB, "Cypress Slab")
        add(JModBlocks.HINOKI_BUTTON, "Cypress Button")
        add(JModBlocks.HINOKI_PRESSURE_PLATE, "Cypress Pressure Plate")
        add(JModBlocks.HINOKI_FENCE, "Cypress Fence")
        add(JModBlocks.HINOKI_FENCE_GATE, "Cypress Fence Gate")


        add(JModBlocks.PYRITE_WALL, "Pyrite Wall")
        add(JModBlocks.PYRITE_DOOR, "Pyrite Door")
        add(JModBlocks.PYRITE_TRAPDOOR, "Pyrite Trapdoor")
        add(JModBlocks.PYRITE_LAMP, "Pyrite Lamp")
        add(JModBlocks.WASHI_WINDOW_PANE, "Washi Window Pane")
        add(JModBlocks.WASHI_WINDOW, "Washi Window")
        add(JModBlocks.SHOJI_WINDOW_PANE, "Shoji Window Pane")
        add(JModBlocks.SHOJI_WINDOW, "Shoji Window")
        add(JModBlocks.SHOJI_DOOR, "Shoji Door")
        add(JModBlocks.CHABUDAI, "Chabudai")
        add(JModBlocks.FUSUMA_DOOR, "Fusuma Door")
        add(JModBlocks.RICE_CROP, "Rice Crop")
        add(JModBlocks.YAMAZAKI_BERRY_BUSH, "Yamazaki Berry Bush")
        add(JModBlocks.CHAIR, "Chair")
        add(JModBlocks.PEDESTAL, "Pedestal")
        add(JModBlocks.HANGING_SCROLL, "Hanging Scroll")
        add(JModBlocks.GROWTH_CHAMBER, "Growth Chamber")
        add(JModBlocks.TATAMI_MAT, "Tatami Mat")
        add(JModBlocks.ZABUTON_BLUE, "Zabuton Blue")
        add(JModBlocks.ZABUTON_RED, "Zabuton Red")
        add(JModBlocks.ZABUTON_GREEN, "Zabuton Green")
        add(JModBlocks.MONEY_VAULT_BLOCK, "Money Vault")
        //add(JModBlocks.CALLIGRAPHY_TABLE, "Calligraphy Table")
        add(JModBlocks.JAPANESE_FLAG, "Japanese Flag")
        add(JModBlocks.CURSED_BLOCK, "Cursed Block")


        // CONTAINERS
        add("container.calligraphy_table", "Calligraphy Table")

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

        // ITEMS
        add(JModItems.PYRITE_INGOT, "Pyrite Ingot")
        add(JModItems.NEPHRITE, "Nephrite")
        add(JModItems.RAW_PYRITE, "Raw Pyrite")
        add(JModItems.CHISEL, "Chisel")
        add(JModItems.PYRITE_SWORD, "Pyrite Sword")
        add(JModItems.NEPHRITE_SWORD, "Nephrite Sword")
        add(JModItems.SUSHI, "Sushi")
        add(JModItems.RICE, "Rice")
        add(JModItems.RICE_SEEDS, "Rice Seeds")
        add(JModItems.RAW_RICE, "Raw Rice")
        add(JModItems.GREEN_TEA, "Green Tea")
        add(JModItems.DIESEL, "Diesel")
        add(JModItems.INCENSE, "Incense")
        add(JModItems.UDON, "Udon")
        //add(JModItems.HELL_PORTAL_ACTIVATOR, "Hell Portal Activator")
        add(JModItems.YA, "Ya")
        add(JModItems.TALISMAN_ITEM, "Talisman")
        add(JModItems.PYRITE_AXE, "Pyrite Axe")
        add(JModItems.PYRITE_PICKAXE, "Pyrite Pickaxe")
        add(JModItems.PYRITE_SHOVEL, "Pyrite Shovel")
        add(JModItems.PYRITE_HOE, "Pyrite Hoe")
        add(JModItems.NEPHRITE_AXE, "Nephrite Axe")
        add(JModItems.NEPHRITE_PICKAXE, "Nephrite Pickaxe")
        add(JModItems.NEPHRITE_SHOVEL, "Nephrite Shovel")
        add(JModItems.NEPHRITE_HOE, "Nephrite Hoe")
        add(JModItems.PYRITE_HELMET, "Pyrite Helmet")
        add(JModItems.PYRITE_CHESTPLATE, "Pyrite Chestplate")
        add(JModItems.CHIRETSU_SHO_SCROLL, "Chiretsu Shō Jutsu Scroll")
        add(JModItems.EKIRETSU_SHO_SCROLL, "Ekiretsu Shō Jutsu Scroll")
        add(JModItems.FIREBALL_SCROLL, "Fireball Jutsu Scroll")
        add(JModItems.LIFE_STEAL_JUTSU_SCROLL, "LifeSteal Jutsu Scroll")
        add(JModItems.WINDBALL_SCROLL, "Windball Jutsu Scroll")
        //add(JModItems.SCROLL, "Scroll")
        add(JModItems.PYRITE_LEGGINGS, "Pyrite Leggings")
        add(JModItems.PYRITE_BOOTS, "Pyrite Boots")
        add(JModItems.NEPHRITE_HELMET, "Nephrite Helmet")
        add(JModItems.NEPHRITE_CHESTPLATE, "Nephrite Chestplate")
        add(JModItems.NEPHRITE_LEGGINGS, "Nephrite Leggings")
        add(JModItems.NEPHRITE_BOOTS, "Nephrite Boots")
        add(JModItems.SAMURAI_HELMET, "Samurai Helmet")
        add(JModItems.SAMURAI_CHESTPLATE, "Samurai Chestplate")
        add(JModItems.SAMURAI_LEGGINGS, "Samurai Leggings")
        add(JModItems.SAMURAI_BOOTS, "Samurai Boots")
        add(JModItems.PYRITE_HAMMER, "Pyrite Hammer")
        add(JModItems.PYRITE_HORSE_ARMOR, "Pyrite Horse Armor")
        add(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "Koi Fish Smithing Template")
        add(JModItems.DAIKYU, "Long Bow")
        add(JModItems.RAMEN, "Ramen")
        add(JModItems.POCKET_BLADE, "Pocket Blade")
        add(JModItems.AO_TO_NATSU_MUSIC_DISC, "Ao to Natsu Music Disc")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - Ao to Natsu")
        add("item.minecraft.potion.effect.adrenaline_potion", "Adrenaline Potion")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "Adrenaline Splash Potion")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "Adrenaline Lingering Potion")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "Adrenaline Tipped Arrow")
        add(JModItems.YAMAZAKI_BERRIES, "Yamazaki Berries")
        add(JModItems.TRICERATOPS_SPAWN_EGG, "Triceratops Spawn Egg")
        add(JModItems.CURSED_SAMURAI_SPAWN_EGG, "Cursed Samurai Spawn Egg")
        add(JModItems.SIKA_DEER_SPAWN_EGG, "Sika Deer Spawn Egg")
        add(JModItems.PYRITE_BATTLE_AXE, "Pyrite Battle Axe")
        add(JModItems.IRON_BATTLE_AXE, "Iron Battle Axe")
        add(JModItems.RADIATION_STAFF, "Radiation Staff")
        add(JModItems.KATANA, "Katana")
        add(JModItems.SOUL_DAGGER, "Soul Dagger")
        add(JModItems.BLADE_STEEL, "Katana Blade Steel")
        add(JModItems.BLACK_WRAP, "Katana Black Wrap")
        add(JModItems.RED_WRAP, "Katana Red Wrap")
        add(JModItems.WHITE_WRAP, "Katana White Wrap")
        add(JModItems.CREDIT_CARD_ITEM, "Credit Card")
        //add(JModItems.SOUL_GUITAR, "Soul Guitar")
        add(JModItems.GUN, "Gun")
        add(JModItems.BULLET, "Bullet")
        add(JModItems.POWERED_SWORD, "Powered Sword")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "Windball")
        add("jutsu.everythingjapanese.fireball", "Fireball")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "Chiretsu Shō")

        // KEYS
        add("key.category.everythingjapanese.jutsu", "Jutsu")
        add("key.everythingjapanese.cast_jutsu", "Cast Jutsu")
        add("key.everythingjapanese.cycle_jutsu", "Cycle Jutsu")
        add("key.category.everythingjapanese.chakra", "Chakra")
        add("key.everythingjapanese.regen_chakra", "Regen Chakra")

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
