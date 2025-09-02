package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class JapaneseLanguageProvider(output: PackOutput) : LanguageProvider(output, MOD_ID, "ja_jp") {
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
        add(ModBlocks.PYRITE_BLOCK, "黄鉄鉱のブロック")
        add(ModBlocks.NEPHRITE_BLOCK, "軟玉のブロック")
        add(ModBlocks.RAW_PYRITE_BLOCK, "未精錬黄鉄鉱のブロック")
        add(ModBlocks.PYRITE_ORE, "黄鉄鉱鉱石")
        add(ModBlocks.PYRITE_DEEPSLATE_ORE, "深層黄鉄鉱鉱石")
        add(ModBlocks.NEPHRITE_ORE, "軟玉鉱石")
        add(ModBlocks.NEPHRITE_DEEPSLATE_ORE, "深層軟玉鉱石")
        add(ModBlocks.TRANSFORMER_BLOCK, "変換ブロック")
        add(ModBlocks.HINOKI_MARUTA, "ヒノキの丸太")
        add(ModBlocks.HINOKI_MOKUZAI, "ヒノキの木材")
        add(ModBlocks.STRIPPED_HINOKI_MARUTA, "皮むきヒノキの丸太")
        add(ModBlocks.STRIPPED_HINOKI_MOKUZAI, "皮むきヒノキの木材")
        add(ModBlocks.HINOKI_BAN, "ヒノキの板材")
        add(ModBlocks.HINOKI_HA, "ヒノキの葉")
        add(ModBlocks.HINOKI_NAEGI, "ヒノキの苗木")
        add(ModBlocks.CHOCOLATE_CAKE, "チョコレートケーキ")
        add(ModBlocks.JAPANESE_CHEESECAKE, "日本風チーズケーキ")
        add(ModBlocks.PYRITE_STAIRS, "黄鉄鉱の階段")
        add(ModBlocks.PYRITE_SLAB, "黄鉄鉱のスラブ")
        add(ModBlocks.PYRITE_BUTTON, "黄鉄鉱のボタン")
        add(ModBlocks.PYRITE_PRESSURE_PLATE, "黄鉄鉱の感圧板")
        add(ModBlocks.PYRITE_FENCE, "黄鉄鉱の柵")
        add(ModBlocks.PYRITE_FENCE_GATE, "黄鉄鉱の柵門")
        add(ModBlocks.PYRITE_WALL, "黄鉄鉱の壁")
        add(ModBlocks.PYRITE_DOOR, "黄鉄鉱のドア")
        add(ModBlocks.PYRITE_TRAPDOOR, "黄鉄鉱のトラップドア")
        add(ModBlocks.PYRITE_LAMP, "黄鉄鉱のランプ")
        add(ModBlocks.WASHI_WINDOW_PANE, "和紙の窓ガラス")
        add(ModBlocks.WASHI_WINDOW, "和紙の窓")
        add(ModBlocks.SHOJI_DOOR, "障子戸")
        add(ModBlocks.CHABUDAI, "ちゃぶ台")
        add(ModBlocks.FUSUMA_DOOR, "襖戸")
        add(ModBlocks.RICE_CROP, "稲作物")
        add(ModBlocks.YAMAZAKI_BERRY_BUSH, "ヤマザキのベリーの茂み")
        add(ModBlocks.CHAIR, "椅子")
        add(ModBlocks.PEDESTAL, "台座")
        add(ModBlocks.GROWTH_CHAMBER, "成長チャンバー")
        add(ModBlocks.TATAMI_MAT, "畳")
        add(ModBlocks.ZABUTON_BLUE, "座布団 青")
        add(ModBlocks.ZABUTON_RED, "座布団 赤")
        add(ModBlocks.ZABUTON_GREEN, "座布団 緑")
        add(ModBlocks.MONEY_VAULT_BLOCK, "マネーヴォールト")

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
        add("gui.quests", "クエスト")

        // ITEMS
        add(ModItems.PYRITE_INGOT, "黄鉄鉱のインゴット")
        add(ModItems.NEPHRITE, "軟玉")
        add(ModItems.RAW_PYRITE, "未精錬黄鉄鉱")
        add(ModItems.CHISEL, "のみ")
        add(ModItems.PYRITE_SWORD, "黄鉄鉱の剣")
        add(ModItems.NEPHRITE_SWORD, "軟玉の剣")
        add(ModItems.SUSHI, "寿司")
        add(ModItems.RICE, "米")
        add(ModItems.RICE_SEEDS, "稲の種")
        add(ModItems.RAW_RICE, "生米")
        add(ModItems.GREEN_TEA, "緑茶")
        add(ModItems.DIESEL, "ディーゼル")
        add(ModItems.INCENSE, "お香")
        add(ModItems.UDON, "うどん")
        add(ModItems.HELL_PORTAL_ACTIVATOR, "地獄のポータル起動器")
        add(ModItems.YA, "ヤー")
        add(ModItems.TALISMAN_ITEM, "護符")
        add(ModItems.PYRITE_AXE, "黄鉄鉱の斧")
        add(ModItems.PYRITE_PICKAXE, "黄鉄鉱のつるはし")
        add(ModItems.PYRITE_SHOVEL, "黄鉄鉱のシャベル")
        add(ModItems.PYRITE_HOE, "黄鉄鉱のクワ")
        add(ModItems.NEPHRITE_AXE, "軟玉の斧")
        add(ModItems.NEPHRITE_PICKAXE, "軟玉のつるはし")
        add(ModItems.NEPHRITE_SHOVEL, "軟玉のシャベル")
        add(ModItems.NEPHRITE_HOE, "軟玉のクワ")
        add(ModItems.PYRITE_HELMET, "黄鉄鉱のヘルメット")
        add(ModItems.PYRITE_CHESTPLATE, "黄鉄鉱のチェストプレート")
        add(ModItems.CHIRETSU_SHO_SCROLL, "地裂掌の巻物")
        add(ModItems.FIREBALL_SCROLL, "火の玉術の巻物")
        add(ModItems.WINDBALL_SCROLL, "風の玉術の巻物")
        add(ModItems.PYRITE_LEGGINGS, "黄鉄鉱のレギンス")
        add(ModItems.PYRITE_BOOTS, "黄鉄鉱のブーツ")
        add(ModItems.NEPHRITE_HELMET, "軟玉のヘルメット")
        add(ModItems.NEPHRITE_CHESTPLATE, "軟玉のチェストプレート")
        add(ModItems.NEPHRITE_LEGGINGS, "軟玉のレギンス")
        add(ModItems.NEPHRITE_BOOTS, "軟玉のブーツ")
        add(ModItems.SAMURAI_HELMET, "侍のヘルメット")
        add(ModItems.SAMURAI_CHESTPLATE, "侍のチェストプレート")
        add(ModItems.SAMURAI_LEGGINGS, "侍のレギンス")
        add(ModItems.SAMURAI_BOOTS, "侍のブーツ")
        add(ModItems.PYRITE_HAMMER, "黄鉄鉱のハンマー")
        add(ModItems.PYRITE_HORSE_ARMOR, "黄鉄鉱の馬鎧")
        add(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "鯉の魚の鍛冶テンプレート")
        add(ModItems.DAIKYU, "長弓")
        add(ModItems.AO_TO_NATSU_MUSIC_DISC, "青と夏の音楽ディスク")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - 青と夏")
        add("item.minecraft.potion.effect.adrenaline_potion", "アドレナリンのポーション")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "アドレナリンスプラッシュポーション")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "アドレナリン残留ポーション")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "アドレナリン付き矢")
        add(ModItems.YAMAZAKI_BERRIES, "ヤマザキのベリー")
        add(ModItems.TRICERATOPS_SPAWN_EGG, "トリケラトプススポーンエッグ")
        add(ModItems.CURSED_SAMURAI_SPAWN_EGG, "呪われた侍のスポーンエッグ")
        add(ModItems.SIKA_DEER_SPAWN_EGG, "シカスポーンエッグ")
        add(ModItems.PYRITE_BATTLE_AXE, "黄鉄鉱の戦斧")
        add(ModItems.IRON_BATTLE_AXE, "鉄の戦斧")
        add(ModItems.RADIATION_STAFF, "放射能の杖")
        add(ModItems.KATANA, "刀")
        add(ModItems.BLADE_STEEL, "刀の鋼の刃")
        add(ModItems.BLACK_WRAP, "黒の柄巻き")
        add(ModItems.RED_WRAP, "赤の柄巻き")
        add(ModItems.WHITE_WRAP, "白の柄巻き")
        add(ModItems.CREDIT_CARD_ITEM, "クレジットカード")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "風の玉")
        add("jutsu.everythingjapanese.fireball", "火の玉")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "地裂掌")

        // KEYS
        add("key.everythingjapanese.cast_jutsu", "術を唱える")
        add("key.everythingjapanese.cycle_jutsu", "術を切り替える")
        add("key.categories.jutsu", "術")
        add("key.everythingjapanese.show_quests", "クエストを表示")
        add("key.categories.quest", "クエスト")

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
