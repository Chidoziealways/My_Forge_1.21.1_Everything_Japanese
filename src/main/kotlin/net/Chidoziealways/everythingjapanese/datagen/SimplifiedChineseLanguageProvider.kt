package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class SimplifiedChineseLanguageProvider(output: PackOutput): LanguageProvider(output, JAPANESE_MOD_ID, "zh_cn") {
    override fun addTranslations() {
        // ADVANCEMENTS
        add("advancements.hell.root.title", "地狱")
        add("advancements.hell.root.description", "无处可逃")

        // ARGUMENTS
        add("argument.jutsu.id.invalid", "未知的术 '%s'")
        add("arguments.jutsu.component.unknown", "未知的术组件 '%s'")
        add("arguments.jutsu.component.malformed", "格式错误的 '%s' 组件: '%s'")
        add("arguments.jutsu.component.expected", "预期的术组件")
        add("arguments.jutsu.component.repeated", "术组件 '%s' 重复，但只能指定一次")
        add("arguments.jutsu.malformed", "格式错误的术: '%s'")

        // BLOCKS
        add(JModBlocks.PYRITE_BLOCK, "黄铁矿方块")
        add(JModBlocks.NEPHRITE_BLOCK, "软玉方块")
        add(JModBlocks.RAW_PYRITE_BLOCK, "未加工黄铁矿方块")
        add(JModBlocks.PYRITE_ORE, "黄铁矿矿石")
        add(JModBlocks.PYRITE_DEEPSLATE_ORE, "深板岩黄铁矿矿石")
        add(JModBlocks.NEPHRITE_ORE, "软玉矿石")
        add(JModBlocks.NEPHRITE_DEEPSLATE_ORE, "深板岩软玉矿石")
        add(JModBlocks.TRANSFORMER_BLOCK, "转换方块")
        add(JModBlocks.HINOKI_MARUTA, "扁柏原木")
        add(JModBlocks.HINOKI_MOKUZAI, "扁柏木材")
        add(JModBlocks.STRIPPED_HINOKI_MARUTA, "剥皮扁柏原木")
        add(JModBlocks.STRIPPED_HINOKI_MOKUZAI, "剥皮扁柏木材")
        add(JModBlocks.HINOKI_BAN, "扁柏木板")
        add(JModBlocks.HINOKI_HA, "扁柏叶")
        add(JModBlocks.HINOKI_NAEGI, "扁柏树苗")
        add(JModBlocks.CHOCOLATE_CAKE, "巧克力蛋糕")
        add(JModBlocks.JAPANESE_CHEESECAKE, "日式起司蛋糕")
        add(JModBlocks.PYRITE_STAIRS, "黄铁矿楼梯")
        add(JModBlocks.PYRITE_SLAB, "黄铁矿台阶")
        add(JModBlocks.PYRITE_BUTTON, "黄铁矿按钮")
        add(JModBlocks.PYRITE_PRESSURE_PLATE, "黄铁矿压力板")
        add(JModBlocks.PYRITE_FENCE, "黄铁矿栅栏")
        add(JModBlocks.PYRITE_FENCE_GATE, "黄铁矿栅栏门")

        // HINOKI
        add(JModBlocks.HINOKI_STAIRS, "扁柏楼梯")
        add(JModBlocks.HINOKI_SLAB, "扁柏半砖")
        add(JModBlocks.HINOKI_BUTTON, "扁柏按钮")
        add(JModBlocks.HINOKI_PRESSURE_PLATE, "扁柏压力板")
        add(JModBlocks.HINOKI_FENCE, "扁柏栅栏")
        add(JModBlocks.HINOKI_FENCE_GATE, "扁柏栅栏门")

        add(JModBlocks.PYRITE_WALL, "黄铁矿墙")
        add(JModBlocks.PYRITE_DOOR, "黄铁矿门")
        add(JModBlocks.PYRITE_TRAPDOOR, "黄铁矿活板门")
        add(JModBlocks.PYRITE_LAMP, "黄铁矿灯")
        add(JModBlocks.WASHI_WINDOW_PANE, "和纸窗格")
        add(JModBlocks.WASHI_WINDOW, "和纸窗")
        add(JModBlocks.SHOJI_WINDOW_PANE, "障子窗格")
        add(JModBlocks.SHOJI_WINDOW, "障子窗")
        add(JModBlocks.SHOJI_DOOR, "障子门")
        add(JModBlocks.CHABUDAI, "茶几")
        add(JModBlocks.FUSUMA_DOOR, "襖门")
        add(JModBlocks.RICE_CROP, "稻作")
        add(JModBlocks.YAMAZAKI_BERRY_BUSH, "山崎莓灌木")
        add(JModBlocks.CHAIR, "椅子")
        add(JModBlocks.PEDESTAL, "基座")
        add(JModBlocks.HANGING_SCROLL, "挂轴")
        add(JModBlocks.GROWTH_CHAMBER, "成长室")
        add(JModBlocks.TATAMI_MAT, "榻榻米")
        add(JModBlocks.ZABUTON_BLUE, "蓝色座布团")
        add(JModBlocks.ZABUTON_RED, "红色座布团")
        add(JModBlocks.ZABUTON_GREEN, "绿色座布团")
        add(JModBlocks.MONEY_VAULT_BLOCK, "金库")
        //add(JModBlocks.CALLIGRAPHY_TABLE, "书法台")
        add(JModBlocks.JAPANESE_FLAG, "日本国旗")

        // CONTAINERS
        add("container.calligraphy_table", "书法台")

        // CREATIVE TABS
        add("creativetab.everythingjapanese.japanese_items", "日本物品")
        add("creativetab.everythingjapanese.japanese_blocks", "日本方块")
        add("creativetab.everythingjapanese.japanese_weapons", "日本武器")
        add("creativetab.everythingjapanese.japanese_food", "日本食物")
        add("creativetab.everythingjapanese.japanese_drinks", "日本饮料")
        add("creativetab.everythingjapanese.japanese_fuel", "日本燃料")
        add("creativetab.everythingjapanese.japanese_tools", "日本工具")
        add("creativetab.everythingjapanese.japanese_armor", "日本盔甲")
        add("creativetab.everythingjapanese.japanese_armor_trims", "日本盔甲饰边")
        add("creativetab.everythingjapanese.japanese_music", "日本音乐")
        add("creativetab.everythingjapanese.japanese_entities", "日本实体")
        add("creativetab.everythingjapanese.japanese_furniture", "日本家具")
        add("creativetab.everythingjapanese.hell", "地狱")
        add("creativetab.everythingjapanese.katana_addons", "武士刀附加物")

        // EFFECTS
        add(ModEffects.ADRENALINE_EFFECT.get(), "肾上腺素")

        // ENCHANTMENTS
        add("enchantment.everythingjapanese.lightning_striker", "雷击者")

        // ENTITIES
        add(ModEntities.TRICERATOPS, "三角龙")
        add(ModEntities.CURSED_SAMURAI, "被诅咒的武士")
        add(ModEntities.SIKA_DEER, "梅花鹿")
        add(ModEntities.IRON_BATTLE_AXE, "铁战斧")
        add("entity.minecraft.villager.everythingjapanese.furniture_maker", "家具制造者")

        // GUI


        // ITEMS
        add(JModItems.PYRITE_INGOT, "黄铁矿锭")
        add(JModItems.NEPHRITE, "软玉")
        add(JModItems.RAW_PYRITE, "未加工黄铁矿")
        add(JModItems.CHISEL, "凿子")
        add(JModItems.PYRITE_SWORD, "黄铁矿剑")
        add(JModItems.NEPHRITE_SWORD, "软玉剑")
        add(JModItems.SUSHI, "寿司")
        add(JModItems.RICE, "米")
        add(JModItems.RICE_SEEDS, "稻种")
        add(JModItems.RAW_RICE, "生米")
        add(JModItems.GREEN_TEA, "绿茶")
        add(JModItems.DIESEL, "柴油")
        add(JModItems.INCENSE, "线香")
        add(JModItems.UDON, "乌冬")
        add(JModItems.HELL_PORTAL_ACTIVATOR, "地狱传送门激活器")
        add(JModItems.YA, "弓箭")
        add(JModItems.TALISMAN_ITEM, "符咒")
        add(JModItems.PYRITE_AXE, "黄铁矿斧")
        add(JModItems.PYRITE_PICKAXE, "黄铁矿镐")
        add(JModItems.PYRITE_SHOVEL, "黄铁矿锹")
        add(JModItems.PYRITE_HOE, "黄铁矿锄")
        add(JModItems.NEPHRITE_AXE, "软玉斧")
        add(JModItems.NEPHRITE_PICKAXE, "软玉镐")
        add(JModItems.NEPHRITE_SHOVEL, "软玉锹")
        add(JModItems.NEPHRITE_HOE, "软玉锄")
        add(JModItems.PYRITE_HELMET, "黄铁矿头盔")
        add(JModItems.PYRITE_CHESTPLATE, "黄铁矿胸甲")
        add(JModItems.CHIRETSU_SHO_SCROLL, "地裂掌卷轴")
        add(JModItems.EKIRETSU_SHO_SCROLL, "液裂掌卷轴")
        add(JModItems.FIREBALL_SCROLL, "火球术卷轴")
        add(JModItems.RAMEN, "拉面")
        add(JModItems.WINDBALL_SCROLL, "风球术卷轴")
        //add(JModItems.SCROLL, "卷轴")
        add(JModItems.PYRITE_LEGGINGS, "黄铁矿护腿")
        add(JModItems.PYRITE_BOOTS, "黄铁矿靴子")
        add(JModItems.NEPHRITE_HELMET, "软玉头盔")
        add(JModItems.NEPHRITE_CHESTPLATE, "软玉胸甲")
        add(JModItems.NEPHRITE_LEGGINGS, "软玉护腿")
        add(JModItems.NEPHRITE_BOOTS, "软玉靴子")
        add(JModItems.SAMURAI_HELMET, "武士头盔")
        add(JModItems.SAMURAI_CHESTPLATE, "武士胸甲")
        add(JModItems.SAMURAI_LEGGINGS, "武士护腿")
        add(JModItems.SAMURAI_BOOTS, "武士靴子")
        add(JModItems.PYRITE_HAMMER, "黄铁矿锤")
        add(JModItems.PYRITE_HORSE_ARMOR, "黄铁矿马甲")
        add(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "锦鲤盔甲饰边模版")
        add(JModItems.DAIKYU, "长弓")
        add(JModItems.AO_TO_NATSU_MUSIC_DISC, "《青と夏》音乐唱片")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - 青と夏")
        add("item.minecraft.potion.effect.adrenaline_potion", "肾上腺素药水")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "肾上腺素喷溅药水")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "肾上腺素滞留药水")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "肾上腺素附魔箭")
        add(JModItems.YAMAZAKI_BERRIES, "山崎莓果")
        add(JModItems.TRICERATOPS_SPAWN_EGG, "三角龙生成蛋")
        add(JModItems.CURSED_SAMURAI_SPAWN_EGG, "被诅咒的武士生成蛋")
        add(JModItems.SIKA_DEER_SPAWN_EGG, "梅花鹿生成蛋")
        add(JModItems.PYRITE_BATTLE_AXE, "黄铁矿战斧")
        add(JModItems.IRON_BATTLE_AXE, "铁战斧")
        add(JModItems.RADIATION_STAFF, "辐射法杖")
        add(JModItems.KATANA, "刀")
        add(JModItems.SOUL_DAGGER, "灵魂匕首")
        add(JModItems.BLADE_STEEL, "武士刀钢刃")
        add(JModItems.BLACK_WRAP, "黑色刀柄缠绕")
        add(JModItems.RED_WRAP, "红色刀柄缠绕")
        add(JModItems.WHITE_WRAP, "白色刀柄缠绕")
        add(JModItems.CREDIT_CARD_ITEM, "信用卡")
        //add(JModItems.SOUL_GUITAR, "灵魂吉他")
        add(JModItems.GUN, "枪")
        add(JModItems.BULLET, "子弹")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "风球术")
        add("jutsu.everythingjapanese.fireball", "火球术")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "地裂掌")

        // KEYS
        add("key.category.everythingjapanese.jutsu", "术")
        add("key.everythingjapanese.cast_jutsu", "施放术")
        add("key.everythingjapanese.cycle_jutsu", "切换术")
        add("key.category.everythingjapanese.chakra", "查克拉");
        add("key.everythingjapanese.regen_chakra", "查克拉回复");

        // PAINTINGS
        add("painting.everythingjapanese.samurai.title", "武士")
        add("painting.everythingjapanese.samurai.author", "Chidoziealways")

        // SOUNDS
        add("sounds.everythingjapanese.chisel_use", "使用凿子")
        add("sounds.everythingjapanese.magic_block_break", "魔法方块被破坏")
        add("sounds.everythingjapanese.magic_block_fall", "掉落魔法方块")
        add("sounds.everythingjapanese.magic_block_hit", "魔法方块被击中")
        add("sounds.everythingjapanese.magic_block_place", "魔法方块被放置")
        add("sounds.everythingjapanese.magic_block_step", "踩在魔法方块上")

        // TOOLTIPS
        add("tooltip.everythingjapanese.magic_block.tooltip", "一个能将投掷到其上的物品转换成完全不同物品的方块")
        add("tooltip.everythingjapanese.chisel_item.shift_down", "按住 Shift 查看更多此物品信息！")
        add("tooltip.everythingjapanese.chisel_item", "一个能将某些物品转换为其他物品的工具")
        add("tooltip.everythingjapanese.sushi", "非常受欢迎的日本料理。")
        add("tooltip.everythingjapanese.earth_katana", "木制武士刀。拥有无法言喻的力量，甚至能召唤未知的存在，包括地狱的恶魔。")
        add("tooltip.everythingjapanese.earth_katana.no_shift", "按住 Shift 查看此神物的更多信息")

        // TRIM PATTERNS & MATERIALS
        add("trim_pattern.everythingjapanese.koi_fish", "锦鲤盔甲饰边")
        add("trim_material.everythingjapanese.pyrite", "黄铁矿材料")
    }
}
