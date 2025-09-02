package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class TraditionalChineseLanguageProvider(output: PackOutput): LanguageProvider(output, MOD_ID, "zh_tw") {
    override fun addTranslations() {
        // ADVANCEMENTS
        add("advancements.hell.root.title", "地獄")
        add("advancements.hell.root.description", "無處可逃")

        // ARGUMENTS
        add("argument.jutsu.id.invalid", "未知的術 '%s'")
        add("arguments.jutsu.component.unknown", "未知的術組件 '%s'")
        add("arguments.jutsu.component.malformed", "格式錯誤的 '%s' 組件: '%s'")
        add("arguments.jutsu.component.expected", "預期的術組件")
        add("arguments.jutsu.component.repeated", "術組件 '%s' 重複，但只能指定一次")
        add("arguments.jutsu.malformed", "格式錯誤的術: '%s'")

        // BLOCKS
        add(ModBlocks.PYRITE_BLOCK, "黃鐵礦方塊")
        add(ModBlocks.NEPHRITE_BLOCK, "軟玉方塊")
        add(ModBlocks.RAW_PYRITE_BLOCK, "未加工黃鐵礦方塊")
        add(ModBlocks.PYRITE_ORE, "黃鐵礦礦石")
        add(ModBlocks.PYRITE_DEEPSLATE_ORE, "深板岩黃鐵礦礦石")
        add(ModBlocks.NEPHRITE_ORE, "軟玉礦石")
        add(ModBlocks.NEPHRITE_DEEPSLATE_ORE, "深板岩軟玉礦石")
        add(ModBlocks.TRANSFORMER_BLOCK, "轉換方塊")
        add(ModBlocks.HINOKI_MARUTA, "扁柏原木")
        add(ModBlocks.HINOKI_MOKUZAI, "扁柏木材")
        add(ModBlocks.STRIPPED_HINOKI_MARUTA, "剝皮扁柏原木")
        add(ModBlocks.STRIPPED_HINOKI_MOKUZAI, "剝皮扁柏木材")
        add(ModBlocks.HINOKI_BAN, "扁柏木板")
        add(ModBlocks.HINOKI_HA, "扁柏葉")
        add(ModBlocks.HINOKI_NAEGI, "扁柏樹苗")
        add(ModBlocks.CHOCOLATE_CAKE, "巧克力蛋糕")
        add(ModBlocks.JAPANESE_CHEESECAKE, "日式起司蛋糕")
        add(ModBlocks.PYRITE_STAIRS, "黃鐵礦階梯")
        add(ModBlocks.PYRITE_SLAB, "黃鐵礦台階")
        add(ModBlocks.PYRITE_BUTTON, "黃鐵礦按鈕")
        add(ModBlocks.PYRITE_PRESSURE_PLATE, "黃鐵礦壓力板")
        add(ModBlocks.PYRITE_FENCE, "黃鐵礦柵欄")
        add(ModBlocks.PYRITE_FENCE_GATE, "黃鐵礦柵欄門")
        add(ModBlocks.PYRITE_WALL, "黃鐵礦牆")
        add(ModBlocks.PYRITE_DOOR, "黃鐵礦門")
        add(ModBlocks.PYRITE_TRAPDOOR, "黃鐵礦活板門")
        add(ModBlocks.PYRITE_LAMP, "黃鐵礦燈")
        add(ModBlocks.WASHI_WINDOW_PANE, "和紙窗格")
        add(ModBlocks.WASHI_WINDOW, "和紙窗")
        add(ModBlocks.SHOJI_DOOR, "障子門")
        add(ModBlocks.CHABUDAI, "茶几")
        add(ModBlocks.FUSUMA_DOOR, "襖門")
        add(ModBlocks.RICE_CROP, "稻作")
        add(ModBlocks.YAMAZAKI_BERRY_BUSH, "山崎莓灌木")
        add(ModBlocks.CHAIR, "椅子")
        add(ModBlocks.PEDESTAL, "基座")
        add(ModBlocks.GROWTH_CHAMBER, "成長室")
        add(ModBlocks.TATAMI_MAT, "榻榻米")
        add(ModBlocks.ZABUTON_BLUE, "藍色座布團")
        add(ModBlocks.ZABUTON_RED, "紅色座布團")
        add(ModBlocks.ZABUTON_GREEN, "綠色座布團")
        add(ModBlocks.MONEY_VAULT_BLOCK, "金庫")

        // CREATIVE TABS
        add("creativetab.everythingjapanese.japanese_items", "日本物品")
        add("creativetab.everythingjapanese.japanese_blocks", "日本方塊")
        add("creativetab.everythingjapanese.japanese_weapons", "日本武器")
        add("creativetab.everythingjapanese.japanese_food", "日本食物")
        add("creativetab.everythingjapanese.japanese_drinks", "日本飲料")
        add("creativetab.everythingjapanese.japanese_fuel", "日本燃料")
        add("creativetab.everythingjapanese.japanese_tools", "日本工具")
        add("creativetab.everythingjapanese.japanese_armor", "日本盔甲")
        add("creativetab.everythingjapanese.japanese_armor_trims", "日本盔甲飾邊")
        add("creativetab.everythingjapanese.japanese_music", "日本音樂")
        add("creativetab.everythingjapanese.japanese_entities", "日本實體")
        add("creativetab.everythingjapanese.japanese_furniture", "日本家具")
        add("creativetab.everythingjapanese.hell", "地獄")
        add("creativetab.everythingjapanese.katana_addons", "武士刀附加物")

        // EFFECTS
        add(ModEffects.ADRENALINE_EFFECT.get(), "腎上腺素")

        // ENCHANTMENTS
        add("enchantment.everythingjapanese.lightning_striker", "雷擊者")

        // ENTITIES
        add(ModEntities.TRICERATOPS, "三角龍")
        add(ModEntities.CURSED_SAMURAI, "被詛咒的武士")
        add(ModEntities.SIKA_DEER, "梅花鹿")
        add(ModEntities.IRON_BATTLE_AXE, "鐵戰斧")
        add("entity.minecraft.villager.everythingjapanese.furniture_maker", "家具製造者")

        // GUI
        add("gui.quests", "任務")

        // ITEMS
        add(ModItems.PYRITE_INGOT, "黃鐵礦錠")
        add(ModItems.NEPHRITE, "軟玉")
        add(ModItems.RAW_PYRITE, "未加工黃鐵礦")
        add(ModItems.CHISEL, "鑿子")
        add(ModItems.PYRITE_SWORD, "黃鐵礦劍")
        add(ModItems.NEPHRITE_SWORD, "軟玉劍")
        add(ModItems.SUSHI, "壽司")
        add(ModItems.RICE, "米")
        add(ModItems.RICE_SEEDS, "稻種")
        add(ModItems.RAW_RICE, "生米")
        add(ModItems.GREEN_TEA, "綠茶")
        add(ModItems.DIESEL, "柴油")
        add(ModItems.INCENSE, "線香")
        add(ModItems.UDON, "烏冬")
        add(ModItems.HELL_PORTAL_ACTIVATOR, "地獄傳送門激活器")
        add(ModItems.YA, "弓箭")
        add(ModItems.TALISMAN_ITEM, "符咒")
        add(ModItems.PYRITE_AXE, "黃鐵礦斧")
        add(ModItems.PYRITE_PICKAXE, "黃鐵礦鎬")
        add(ModItems.PYRITE_SHOVEL, "黃鐵礦鏟")
        add(ModItems.PYRITE_HOE, "黃鐵礦鋤")
        add(ModItems.NEPHRITE_AXE, "軟玉斧")
        add(ModItems.NEPHRITE_PICKAXE, "軟玉鎬")
        add(ModItems.NEPHRITE_SHOVEL, "軟玉鏟")
        add(ModItems.NEPHRITE_HOE, "軟玉鋤")
        add(ModItems.PYRITE_HELMET, "黃鐵礦頭盔")
        add(ModItems.PYRITE_CHESTPLATE, "黃鐵礦胸甲")
        add(ModItems.CHIRETSU_SHO_SCROLL, "地裂掌卷軸")
        add(ModItems.FIREBALL_SCROLL, "火球術卷軸")
        add(ModItems.WINDBALL_SCROLL, "風球術卷軸")
        add(ModItems.PYRITE_LEGGINGS, "黃鐵礦護腿")
        add(ModItems.PYRITE_BOOTS, "黃鐵礦靴子")
        add(ModItems.NEPHRITE_HELMET, "軟玉頭盔")
        add(ModItems.NEPHRITE_CHESTPLATE, "軟玉胸甲")
        add(ModItems.NEPHRITE_LEGGINGS, "軟玉護腿")
        add(ModItems.NEPHRITE_BOOTS, "軟玉靴子")
        add(ModItems.SAMURAI_HELMET, "武士頭盔")
        add(ModItems.SAMURAI_CHESTPLATE, "武士胸甲")
        add(ModItems.SAMURAI_LEGGINGS, "武士護腿")
        add(ModItems.SAMURAI_BOOTS, "武士靴子")
        add(ModItems.PYRITE_HAMMER, "黃鐵礦錘")
        add(ModItems.PYRITE_HORSE_ARMOR, "黃鐵礦馬甲")
        add(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "錦鯉裝甲飾邊模版")
        add(ModItems.DAIKYU, "長弓")
        add(ModItems.AO_TO_NATSU_MUSIC_DISC, "《青と夏》音樂唱片")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - 青と夏")
        add("item.minecraft.potion.effect.adrenaline_potion", "腎上腺素藥水")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "腎上腺素飛濺藥水")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "腎上腺素殘留藥水")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "腎上腺素附魔箭")
        add(ModItems.YAMAZAKI_BERRIES, "山崎莓果")
        add(ModItems.TRICERATOPS_SPAWN_EGG, "三角龍生成蛋")
        add(ModItems.CURSED_SAMURAI_SPAWN_EGG, "被詛咒的武士生成蛋")
        add(ModItems.SIKA_DEER_SPAWN_EGG, "梅花鹿生成蛋")
        add(ModItems.PYRITE_BATTLE_AXE, "黃鐵礦戰斧")
        add(ModItems.IRON_BATTLE_AXE, "鐵戰斧")
        add(ModItems.RADIATION_STAFF, "輻射法杖")
        add(ModItems.KATANA, "刀")
        add(ModItems.BLADE_STEEL, "武士刀鋼刃")
        add(ModItems.BLACK_WRAP, "黑色刀柄纏繞")
        add(ModItems.RED_WRAP, "紅色刀柄纏繞")
        add(ModItems.WHITE_WRAP, "白色刀柄纏繞")
        add(ModItems.CREDIT_CARD_ITEM, "信用卡")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "風球術")
        add("jutsu.everythingjapanese.fireball", "火球術")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "地裂掌")

        // KEYS
        add("key.everythingjapanese.cast_jutsu", "施放術")
        add("key.everythingjapanese.cycle_jutsu", "切換術")
        add("key.categories.jutsu", "術")
        add("key.everythingjapanese.show_quests", "顯示任務")
        add("key.categories.quest", "任務")

        // PAINTINGS
        add("painting.everythingjapanese.samurai.title", "武士")
        add("painting.everythingjapanese.samurai.author", "Chidoziealways")

        // SOUNDS
        add("sounds.everythingjapanese.chisel_use", "使用鑿子")
        add("sounds.everythingjapanese.magic_block_break", "魔法方塊被破壞")
        add("sounds.everythingjapanese.magic_block_fall", "跌落魔法方塊")
        add("sounds.everythingjapanese.magic_block_hit", "魔法方塊被擊中")
        add("sounds.everythingjapanese.magic_block_place", "魔法方塊被放置")
        add("sounds.everythingjapanese.magic_block_step", "踏在魔法方塊上")

        // TOOLTIPS
        add("tooltip.everythingjapanese.magic_block.tooltip", "一個能將投擲到其上的物品轉換成完全不同物品的方塊")
        add("tooltip.everythingjapanese.chisel_item.shift_down", "按住 Shift 查看更多此物品資訊！")
        add("tooltip.everythingjapanese.chisel_item", "一個能將某些物品轉換為其他物品的工具")
        add("tooltip.everythingjapanese.sushi", "非常受歡迎的日本料理。")
        add("tooltip.everythingjapanese.earth_katana", "木製武士刀。擁有無法言喻的力量，甚至能召喚未知的存在，包括地獄的惡魔。")
        add("tooltip.everythingjapanese.earth_katana.no_shift", "按住 Shift 查看此神物的更多資訊")

        // TRIM PATTERNS & MATERIALS
        add("trim_pattern.everythingjapanese.koi_fish", "錦鯉盔甲飾邊")
        add("trim_material.everythingjapanese.pyrite", "黃鐵礦材料")
    }
}
