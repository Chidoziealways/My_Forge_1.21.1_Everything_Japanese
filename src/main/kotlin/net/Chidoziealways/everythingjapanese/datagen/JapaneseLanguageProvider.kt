package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class JapaneseLanguageProvider(output: PackOutput) : LanguageProvider(output, JAPANESE_MOD_ID, "ja_jp") {
    override fun addTranslations() {
        // ADVANCEMENTS
        add("advancements.hell.root.title", "地獄")
        add("advancements.hell.root.description", "逃げ場はない")

        // ARGUMENTS
        add("argument.jutsu.id.invalid", "不明な術 '%s'")
        add("arguments.jutsu.component.unknown", "不明な術の構成要素 '%s'")
        add("arguments.jutsu.component.malformed", "不正な '%s' 構成要素: '%s'")
        add("arguments.jutsu.component.expected", "術の構成要素が必要です")
        add("arguments.jutsu.component.repeated", "術の構成要素 '%s' が重複しています。一つだけ指定可能です")
        add("arguments.jutsu.malformed", "不正な術: '%s'")

        // BLOCKS
        add(JModBlocks.PYRITE_BLOCK, "黄鉄鉱のブロック")
        add(JModBlocks.NEPHRITE_BLOCK, "軟玉のブロック")
        add(JModBlocks.RAW_PYRITE_BLOCK, "未精錬黄鉄鉱のブロック")
        add(JModBlocks.PYRITE_ORE, "黄鉄鉱鉱石")
        add(JModBlocks.PYRITE_DEEPSLATE_ORE, "深層黄鉄鉱鉱石")
        add(JModBlocks.NEPHRITE_ORE, "軟玉鉱石")
        add(JModBlocks.NEPHRITE_DEEPSLATE_ORE, "深層軟玉鉱石")
        add(JModBlocks.TRANSFORMER_BLOCK, "変換ブロック")
        add(JModBlocks.HINOKI_MARUTA, "ヒノキの丸太")
        add(JModBlocks.HINOKI_MOKUZAI, "ヒノキの木材")
        add(JModBlocks.STRIPPED_HINOKI_MARUTA, "皮むきヒノキの丸太")
        add(JModBlocks.STRIPPED_HINOKI_MOKUZAI, "皮むきヒノキの木材")
        add(JModBlocks.HINOKI_BAN, "ヒノキの板材")
        add(JModBlocks.HINOKI_HA, "ヒノキの葉")
        add(JModBlocks.HINOKI_NAEGI, "ヒノキの苗木")
        add(JModBlocks.CHOCOLATE_CAKE, "チョコレートケーキ")
        add(JModBlocks.JAPANESE_CHEESECAKE, "日本風チーズケーキ")
        add(JModBlocks.PYRITE_STAIRS, "黄鉄鉱の階段")
        add(JModBlocks.PYRITE_SLAB, "黄鉄鉱のスラブ")
        add(JModBlocks.PYRITE_BUTTON, "黄鉄鉱のボタン")
        add(JModBlocks.PYRITE_PRESSURE_PLATE, "黄鉄鉱の感圧板")
        add(JModBlocks.PYRITE_FENCE, "黄鉄鉱の柵")
        add(JModBlocks.PYRITE_FENCE_GATE, "黄鉄鉱の柵門")

        // HINOKI
        add(JModBlocks.HINOKI_STAIRS, "ヒノキの階段")
        add(JModBlocks.HINOKI_SLAB, "ヒノキのハーフブロック")
        add(JModBlocks.HINOKI_BUTTON, "ヒノキのボタン")
        add(JModBlocks.HINOKI_PRESSURE_PLATE, "ヒノキの感圧板")
        add(JModBlocks.HINOKI_FENCE, "ヒノキの柵")
        add(JModBlocks.HINOKI_FENCE_GATE, "ヒノキの柵の門")

        add(JModBlocks.PYRITE_WALL, "黄鉄鉱の壁")
        add(JModBlocks.PYRITE_DOOR, "黄鉄鉱のドア")
        add(JModBlocks.PYRITE_TRAPDOOR, "黄鉄鉱のトラップドア")
        add(JModBlocks.PYRITE_LAMP, "黄鉄鉱のランプ")
        add(JModBlocks.WASHI_WINDOW_PANE, "和紙の窓ガラス")
        add(JModBlocks.WASHI_WINDOW, "和紙の窓")
        add(JModBlocks.SHOJI_WINDOW_PANE, "障子窓パネル")
        add(JModBlocks.SHOJI_WINDOW, "障子窓")
        add(JModBlocks.SHOJI_DOOR, "障子戸")
        add(JModBlocks.CHABUDAI, "ちゃぶ台")
        add(JModBlocks.FUSUMA_DOOR, "襖戸")
        add(JModBlocks.RICE_CROP, "稲作物")
        add(JModBlocks.YAMAZAKI_BERRY_BUSH, "ヤマザキのベリーの茂み")
        add(JModBlocks.CHAIR, "椅子")
        add(JModBlocks.PEDESTAL, "台座")
        add(JModBlocks.HANGING_SCROLL, "掛け軸")
        add(JModBlocks.GROWTH_CHAMBER, "成長チャンバー")
        add(JModBlocks.TATAMI_MAT, "畳")
        add(JModBlocks.ZABUTON_BLUE, "座布団 青")
        add(JModBlocks.ZABUTON_RED, "座布団 赤")
        add(JModBlocks.ZABUTON_GREEN, "座布団 緑")
        add(JModBlocks.MONEY_VAULT_BLOCK, "マネーヴォールト")
        //add(JModBlocks.CALLIGRAPHY_TABLE, "書道台")
        add(JModBlocks.JAPANESE_FLAG, "日本の旗")

        // CONTAINERS
        add("container.calligraphy_table", "書道台")

        // CREATIVE TABS
        add("creativetab.everythingjapanese.japanese_items", "日本のアイテム")
        add("creativetab.everythingjapanese.japanese_blocks", "日本のブロック")
        add("creativetab.everythingjapanese.japanese_weapons", "日本の武器")
        add("creativetab.everythingjapanese.japanese_food", "日本の食べ物")
        add("creativetab.everythingjapanese.japanese_drinks", "日本の飲み物")
        add("creativetab.everythingjapanese.japanese_fuel", "日本の燃料")
        add("creativetab.everythingjapanese.japanese_tools", "日本の道具")
        add("creativetab.everythingjapanese.japanese_armor", "日本の防具")
        add("creativetab.everythingjapanese.japanese_armor_trims", "日本の防具トリム")
        add("creativetab.everythingjapanese.japanese_music", "日本の音楽")
        add("creativetab.everythingjapanese.japanese_entities", "日本のエンティティ")
        add("creativetab.everythingjapanese.japanese_furniture", "日本の家具")
        add("creativetab.everythingjapanese.hell", "地獄")
        add("creativetab.everythingjapanese.katana_addons", "刀の追加")

        // EFFECTS
        add(ModEffects.ADRENALINE_EFFECT.get(), "アドレナリン")

        // ENCHANTMENTS
        add("enchantment.everythingjapanese.lightning_striker", "雷撃士")

        // ENTITIES
        add(ModEntities.TRICERATOPS, "トリケラトプス")
        add(ModEntities.CURSED_SAMURAI, "呪われた侍")
        add(ModEntities.SIKA_DEER, "シカ")
        add(ModEntities.IRON_BATTLE_AXE, "鉄の戦斧")
        add("entity.minecraft.villager.everythingjapanese.furniture_maker", "家具職人")

        // GUI

        // ITEMS
        add(JModItems.PYRITE_INGOT, "黄鉄鉱のインゴット")
        add(JModItems.NEPHRITE, "軟玉")
        add(JModItems.RAW_PYRITE, "未精錬黄鉄鉱")
        add(JModItems.CHISEL, "のみ")
        add(JModItems.PYRITE_SWORD, "黄鉄鉱の剣")
        add(JModItems.NEPHRITE_SWORD, "軟玉の剣")
        add(JModItems.SUSHI, "寿司")
        add(JModItems.RICE, "米")
        add(JModItems.RICE_SEEDS, "稲の種")
        add(JModItems.RAW_RICE, "生米")
        add(JModItems.GREEN_TEA, "緑茶")
        add(JModItems.DIESEL, "ディーゼル")
        add(JModItems.INCENSE, "お香")
        add(JModItems.UDON, "うどん")
        add(JModItems.HELL_PORTAL_ACTIVATOR, "地獄のポータル起動器")
        add(JModItems.YA, "ヤー")
        add(JModItems.RAMEN, "ラーメン")
        add(JModItems.TALISMAN_ITEM, "護符")
        add(JModItems.PYRITE_AXE, "黄鉄鉱の斧")
        add(JModItems.PYRITE_PICKAXE, "黄鉄鉱のつるはし")
        add(JModItems.PYRITE_SHOVEL, "黄鉄鉱のシャベル")
        add(JModItems.PYRITE_HOE, "黄鉄鉱のクワ")
        add(JModItems.NEPHRITE_AXE, "軟玉の斧")
        add(JModItems.NEPHRITE_PICKAXE, "軟玉のつるはし")
        add(JModItems.NEPHRITE_SHOVEL, "軟玉のシャベル")
        add(JModItems.NEPHRITE_HOE, "軟玉のクワ")
        add(JModItems.PYRITE_HELMET, "黄鉄鉱のヘルメット")
        add(JModItems.PYRITE_CHESTPLATE, "黄鉄鉱の胸甲")
        add(JModItems.CHIRETSU_SHO_SCROLL, "地裂掌の巻物")
        add(JModItems.EKIRETSU_SHO_SCROLL, "液裂掌の巻物")
        add(JModItems.FIREBALL_SCROLL, "火の玉術の巻物")
        add(JModItems.WINDBALL_SCROLL, "風の玉術の巻物")
        //add(JModItems.SCROLL, "巻物")
        add(JModItems.PYRITE_LEGGINGS, "黄鉄鉱のレギンス")
        add(JModItems.PYRITE_BOOTS, "黄鉄鉱のブーツ")
        add(JModItems.NEPHRITE_HELMET, "軟玉の胸甲")
        add(JModItems.NEPHRITE_CHESTPLATE, "軟玉のチェストプレート")
        add(JModItems.NEPHRITE_LEGGINGS, "軟玉のレギンス")
        add(JModItems.NEPHRITE_BOOTS, "軟玉のブーツ")
        add(JModItems.SAMURAI_HELMET, "侍の胸甲")
        add(JModItems.SAMURAI_CHESTPLATE, "侍のチェストプレート")
        add(JModItems.SAMURAI_LEGGINGS, "侍のレギンス")
        add(JModItems.SAMURAI_BOOTS, "侍のブーツ")
        add(JModItems.PYRITE_HAMMER, "黄鉄鉱のハンマー")
        add(JModItems.PYRITE_HORSE_ARMOR, "黄鉄鉱の馬鎧")
        add(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "鯉の魚の鍛冶テンプレート")
        add(JModItems.DAIKYU, "長弓")
        add(JModItems.AO_TO_NATSU_MUSIC_DISC, "青と夏の音楽ディスク")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - 青と夏")
        add("item.minecraft.potion.effect.adrenaline_potion", "アドレナリンのポーション")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "アドレナリンスプラッシュポーション")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "アドレナリン残留ポーション")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "アドレナリン付き矢")
        add(JModItems.YAMAZAKI_BERRIES, "ヤマザキのベリー")
        add(JModItems.TRICERATOPS_SPAWN_EGG, "トリケラトプススポーンエッグ")
        add(JModItems.CURSED_SAMURAI_SPAWN_EGG, "呪われた侍のスポーンエッグ")
        add(JModItems.SIKA_DEER_SPAWN_EGG, "シカスポーンエッグ")
        add(JModItems.PYRITE_BATTLE_AXE, "黄鉄鉱の戦斧")
        add(JModItems.IRON_BATTLE_AXE, "鉄の戦斧")
        add(JModItems.RADIATION_STAFF, "放射能の杖")
        add(JModItems.KATANA, "刀")
        add(JModItems.SOUL_DAGGER, "魂の短剣")
        add(JModItems.BLADE_STEEL, "刀の鋼の刃")
        add(JModItems.BLACK_WRAP, "黒の柄巻き")
        add(JModItems.RED_WRAP, "赤の柄巻き")
        add(JModItems.WHITE_WRAP, "白の柄巻き")
        add(JModItems.CREDIT_CARD_ITEM, "クレジットカード")
        //add(JModItems.SOUL_GUITAR, "ソウルギター")
        add(JModItems.GUN, "銃")
        add(JModItems.BULLET, "弾丸")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "風の玉")
        add("jutsu.everythingjapanese.fireball", "火の玉")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "地裂掌")

        // KEYS
        add("key.category.everythingjapanese.jutsu", "術")
        add("key.everythingjapanese.cast_jutsu", "術を唱える")
        add("key.everythingjapanese.cycle_jutsu", "術を切り替える")
        add("key.category.everythingjapanese.chakra", "チャクラ")
        add("key.everythingjapanese.regen_chakra", "チャクラ回復")

        // PAINTINGS
        add("painting.everythingjapanese.samurai.title", "侍")
        add("painting.everythingjapanese.samurai.author", "Chidoziealways")

        // SOUNDS
        add("sounds.everythingjapanese.chisel_use", "のみ使用")
        add("sounds.everythingjapanese.magic_block_break", "魔法のブロック破壊")
        add("sounds.everythingjapanese.magic_block_fall", "魔法のブロックに落下")
        add("sounds.everythingjapanese.magic_block_hit", "魔法のブロックを攻撃")
        add("sounds.everythingjapanese.magic_block_place", "魔法のブロック設置")
        add("sounds.everythingjapanese.magic_block_step", "魔法のブロックを踏む")

        // TOOLTIPS
        add("tooltip.everythingjapanese.magic_block.tooltip", "投げたアイテムを別のアイテムに変えるブロック")
        add("tooltip.everythingjapanese.chisel_item.shift_down", "Shiftキーを押すと詳細表示")
        add("tooltip.everythingjapanese.chisel_item", "特定のアイテムを別のアイテムに変える道具")
        add("tooltip.everythingjapanese.sushi", "とても人気のある日本の食べ物")
        add("tooltip.everythingjapanese.earth_katana", "木製の刀。言葉にできない力を秘め、地獄の悪魔さえ召喚可能")
        add("tooltip.everythingjapanese.earth_katana.no_shift", "Shiftキーを押すと詳細を見ることができます")

        // TRIM PATTERNS & MATERIALS
        add("trim_pattern.everythingjapanese.koi_fish", "鯉の甲冑トリム")
        add("trim_material.everythingjapanese.pyrite", "黄鉄鉱素材")
    }
}
