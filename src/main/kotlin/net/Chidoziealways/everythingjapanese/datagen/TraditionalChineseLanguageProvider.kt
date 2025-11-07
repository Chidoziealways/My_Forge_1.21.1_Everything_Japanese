package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class TraditionalChineseLanguageProvider(output: PackOutput): LanguageProvider(output, JAPANESE_MOD_ID, "zh_tw") {
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
        add(JModBlocks.PYRITE_BLOCK, "黃鐵礦方塊")
        add(JModBlocks.NEPHRITE_BLOCK, "軟玉方塊")
        add(JModBlocks.RAW_PYRITE_BLOCK, "未加工黃鐵礦方塊")
        add(JModBlocks.PYRITE_ORE, "黃鐵礦礦石")
        add(JModBlocks.PYRITE_DEEPSLATE_ORE, "深板岩黃鐵礦礦石")
        add(JModBlocks.NEPHRITE_ORE, "軟玉礦石")
        add(JModBlocks.NEPHRITE_DEEPSLATE_ORE, "深板岩軟玉礦石")
        add(JModBlocks.TRANSFORMER_BLOCK, "轉換方塊")
        add(JModBlocks.HINOKI_MARUTA, "扁柏原木")
        add(JModBlocks.HINOKI_MOKUZAI, "扁柏木材")
        add(JModBlocks.STRIPPED_HINOKI_MARUTA, "剝皮扁柏原木")
        add(JModBlocks.STRIPPED_HINOKI_MOKUZAI, "剝皮扁柏木材")
        add(JModBlocks.HINOKI_BAN, "扁柏木板")
        add(JModBlocks.HINOKI_HA, "扁柏葉")
        add(JModBlocks.HINOKI_NAEGI, "扁柏樹苗")
        add(JModBlocks.CHOCOLATE_CAKE, "巧克力蛋糕")
        add(JModBlocks.JAPANESE_CHEESECAKE, "日式起司蛋糕")
        add(JModBlocks.PYRITE_STAIRS, "黃鐵礦階梯")
        add(JModBlocks.PYRITE_SLAB, "黃鐵礦台階")
        add(JModBlocks.PYRITE_BUTTON, "黃鐵礦按鈕")
        add(JModBlocks.PYRITE_PRESSURE_PLATE, "黃鐵礦壓力板")
        add(JModBlocks.PYRITE_FENCE, "黃鐵礦柵欄")
        add(JModBlocks.PYRITE_FENCE_GATE, "黃鐵礦柵欄門")

        // HINOKI
        add(JModBlocks.HINOKI_STAIRS, "扁柏樓梯")
        add(JModBlocks.HINOKI_SLAB, "扁柏半磚")
        add(JModBlocks.HINOKI_BUTTON, "扁柏按鈕")
        add(JModBlocks.HINOKI_PRESSURE_PLATE, "扁柏壓力板")
        add(JModBlocks.HINOKI_FENCE, "扁柏柵欄")
        add(JModBlocks.HINOKI_FENCE_GATE, "扁柏柵欄門")

        add(JModBlocks.PYRITE_WALL, "黃鐵礦牆")
        add(JModBlocks.PYRITE_DOOR, "黃鐵礦門")
        add(JModBlocks.PYRITE_TRAPDOOR, "黃鐵礦活板門")
        add(JModBlocks.PYRITE_LAMP, "黃鐵礦燈")
        add(JModBlocks.WASHI_WINDOW_PANE, "和紙窗格")
        add(JModBlocks.WASHI_WINDOW, "和紙窗")
        add(JModBlocks.SHOJI_WINDOW_PANE, "障子窗格")
        add(JModBlocks.SHOJI_WINDOW, "障子窗")
        add(JModBlocks.SHOJI_DOOR, "障子門")
        add(JModBlocks.CHABUDAI, "茶几")
        add(JModBlocks.FUSUMA_DOOR, "襖門")
        add(JModBlocks.RICE_CROP, "稻作")
        add(JModBlocks.YAMAZAKI_BERRY_BUSH, "山崎莓灌木")
        add(JModBlocks.CHAIR, "椅子")
        add(JModBlocks.PEDESTAL, "基座")
        add(JModBlocks.HANGING_SCROLL, "掛軸")
        add(JModBlocks.GROWTH_CHAMBER, "成長室")
        add(JModBlocks.TATAMI_MAT, "榻榻米")
        add(JModBlocks.ZABUTON_BLUE, "藍色座布團")
        add(JModBlocks.ZABUTON_RED, "紅色座布團")
        add(JModBlocks.ZABUTON_GREEN, "綠色座布團")
        add(JModBlocks.MONEY_VAULT_BLOCK, "金庫")
        //add(JModBlocks.CALLIGRAPHY_TABLE, "書法台")
        add(JModBlocks.JAPANESE_FLAG, "日本國旗")

        // CONTAINERS
        add("container.calligraphy_table", "書法台")

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


        // ITEMS
        add(JModItems.PYRITE_INGOT, "黃鐵礦錠")
        add(JModItems.NEPHRITE, "軟玉")
        add(JModItems.RAW_PYRITE, "未加工黃鐵礦")
        add(JModItems.CHISEL, "鑿子")
        add(JModItems.PYRITE_SWORD, "黃鐵礦劍")
        add(JModItems.NEPHRITE_SWORD, "軟玉劍")
        add(JModItems.SUSHI, "壽司")
        add(JModItems.RICE, "米")
        add(JModItems.RICE_SEEDS, "稻種")
        add(JModItems.RAW_RICE, "生米")
        add(JModItems.GREEN_TEA, "綠茶")
        add(JModItems.DIESEL, "柴油")
        add(JModItems.INCENSE, "線香")
        add(JModItems.UDON, "烏冬")
        add(JModItems.HELL_PORTAL_ACTIVATOR, "地獄傳送門激活器")
        add(JModItems.YA, "弓箭")
        add(JModItems.TALISMAN_ITEM, "符咒")
        add(JModItems.PYRITE_AXE, "黃鐵礦斧")
        add(JModItems.PYRITE_PICKAXE, "黃鐵礦鎬")
        add(JModItems.PYRITE_SHOVEL, "黃鐵礦鏟")
        add(JModItems.PYRITE_HOE, "黃鐵礦鋤")
        add(JModItems.NEPHRITE_AXE, "軟玉斧")
        add(JModItems.NEPHRITE_PICKAXE, "軟玉鎬")
        add(JModItems.NEPHRITE_SHOVEL, "軟玉鏟")
        add(JModItems.RAMEN, "拉麵")
        add(JModItems.NEPHRITE_HOE, "鋤")
        add(JModItems.PYRITE_HELMET, "黃鐵礦頭盔")
        add(JModItems.PYRITE_CHESTPLATE, "黃鐵礦胸甲")
        add(JModItems.CHIRETSU_SHO_SCROLL, "地裂掌卷軸")
        add(JModItems.EKIRETSU_SHO_SCROLL, "液裂掌卷軸")
        add(JModItems.FIREBALL_SCROLL, "火球術卷軸")
        add(JModItems.WINDBALL_SCROLL, "風球術卷軸")
        //add(JModItems.SCROLL, "卷軸")
        add(JModItems.PYRITE_LEGGINGS, "黃鐵礦護腿")
        add(JModItems.PYRITE_BOOTS, "黃鐵礦靴子")
        add(JModItems.NEPHRITE_HELMET, "軟玉頭盔")
        add(JModItems.NEPHRITE_CHESTPLATE, "軟玉胸甲")
        add(JModItems.NEPHRITE_LEGGINGS, "軟玉護腿")
        add(JModItems.NEPHRITE_BOOTS, "軟玉靴子")
        add(JModItems.SAMURAI_HELMET, "武士頭盔")
        add(JModItems.SAMURAI_CHESTPLATE, "武士胸甲")
        add(JModItems.SAMURAI_LEGGINGS, "武士護腿")
        add(JModItems.SAMURAI_BOOTS, "武士靴子")
        add(JModItems.PYRITE_HAMMER, "黃鐵礦錘")
        add(JModItems.PYRITE_HORSE_ARMOR, "黃鐵礦馬甲")
        add(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "錦鯉裝甲飾邊模版")
        add(JModItems.DAIKYU, "長弓")
        add(JModItems.AO_TO_NATSU_MUSIC_DISC, "《青と夏》音樂唱片")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - 青と夏")
        add("item.minecraft.potion.effect.adrenaline_potion", "腎上腺素藥水")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "腎上腺素飛濺藥水")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "腎上腺素殘留藥水")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "腎上腺素附魔箭")
        add(JModItems.YAMAZAKI_BERRIES, "山崎莓果")
        add(JModItems.TRICERATOPS_SPAWN_EGG, "三角龍生成蛋")
        add(JModItems.CURSED_SAMURAI_SPAWN_EGG, "被詛咒的武士生成蛋")
        add(JModItems.SIKA_DEER_SPAWN_EGG, "梅花鹿生成蛋")
        add(JModItems.PYRITE_BATTLE_AXE, "黃鐵礦戰斧")
        add(JModItems.IRON_BATTLE_AXE, "鐵戰斧")
        add(JModItems.RADIATION_STAFF, "輻射法杖")
        add(JModItems.KATANA, "刀")
        add(JModItems.SOUL_DAGGER, "靈魂匕首")
        add(JModItems.BLADE_STEEL, "武士刀鋼刃")
        add(JModItems.BLACK_WRAP, "黑色刀柄纏繞")
        add(JModItems.RED_WRAP, "紅色刀柄纏繞")
        add(JModItems.WHITE_WRAP, "白色刀柄纏繞")
        add(JModItems.CREDIT_CARD_ITEM, "信用卡")
        //add(JModItems.SOUL_GUITAR, "靈魂吉他")
        add(JModItems.GUN, "槍")
        add(JModItems.BULLET, "子彈")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "風球術")
        add("jutsu.everythingjapanese.fireball", "火球術")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "地裂掌")

        // KEYS
        add("key.category.everythingjapanese.jutsu", "術")
        add("key.everythingjapanese.cast_jutsu", "施放術")
        add("key.everythingjapanese.cycle_jutsu", "切換術")
        add("key.category.everythingjapanese.chakra", "查克拉");
        add("key.everythingjapanese.regen_chakra", "查克拉回復");

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
