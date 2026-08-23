package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class KoreanLanguageProvider(output: PackOutput) : LanguageProvider(output, JAPANESE_MOD_ID, "ko_kr") {
    override fun addTranslations() {
        // ADVANCEMENTS
        add("advancements.hell.root.title", "地獄")
        add("advancements.hell.root.description", "탈출구가 없다")

        // ARGUMENTS
        add("argument.jutsu.id.invalid", "알수없는術 '%s'")
        add("arguments.jutsu.component.unknown", "알수없는術構成要素 '%s'")
        add("arguments.jutsu.component.malformed", "잘못된 '%s' 構成要素: '%s'")
        add("arguments.jutsu.component.expected", "術構成要素가必要합니다")
        add("arguments.jutsu.component.repeated", "術의構成要素 '%s'가重複되었습니다. 하나만指定할수있습니다")
        add("arguments.jutsu.malformed", "잘못된術: '%s'")

        // BLOCKS
        add(JModBlocks.PYRITE_BLOCK, "黃鐵石 블록")
        add(JModBlocks.NEPHRITE_BLOCK, "軟玉 블록")
        add(JModBlocks.RAW_PYRITE_BLOCK, "原石 黃鐵石 블록")
        add(JModBlocks.PYRITE_ORE, "黃鐵石 광석")
        add(JModBlocks.PYRITE_DEEPSLATE_ORE, "심층 黃鐵石 광석")
        add(JModBlocks.NEPHRITE_ORE, "軟玉 광석")
        add(JModBlocks.NEPHRITE_DEEPSLATE_ORE, "심층 軟玉 광석")
        add(JModBlocks.TRANSFORMER_BLOCK, "변환기 블록")
        add(JModBlocks.HINOKI_MARUTA, "檜 통나무")
        add(JModBlocks.HINOKI_MOKUZAI, "檜 목재")
        add(JModBlocks.STRIPPED_HINOKI_MARUTA, "껍질 벗긴 檜 통나무")
        add(JModBlocks.STRIPPED_HINOKI_MOKUZAI, "껍질 벗긴 檜 목재")
        add(JModBlocks.HINOKI_BAN, "檜 판재")
        add(JModBlocks.HINOKI_HA, "檜 잎")
        add(JModBlocks.HINOKI_NAEGI, "檜 묘목")
        add(JModBlocks.CHOCOLATE_CAKE, "초콜릿 케이크")
        add(JModBlocks.JAPANESE_CHEESECAKE, "일본식 치즈케이크")
        add(JModBlocks.PYRITE_STAIRS, "黃鐵石 계단")
        add(JModBlocks.PYRITE_SLAB, "黃鐵石 슬래브")
        add(JModBlocks.PYRITE_BUTTON, "黃鐵石 버튼")
        add(JModBlocks.PYRITE_PRESSURE_PLATE, "黃鐵石 감압판")
        add(JModBlocks.PYRITE_FENCE, "黃鐵石 울타리")
        add(JModBlocks.PYRITE_FENCE_GATE, "黃鐵石 울타리 문")

        // HINOKI
        add(JModBlocks.HINOKI_STAIRS, "檜 계단")
        add(JModBlocks.HINOKI_SLAB, "檜 반블록")
        add(JModBlocks.HINOKI_BUTTON, "檜 버튼")
        add(JModBlocks.HINOKI_PRESSURE_PLATE, "檜 압력판")
        add(JModBlocks.HINOKI_FENCE, "檜 울타리")
        add(JModBlocks.HINOKI_FENCE_GATE, "檜 울타리 문")

        add(JModBlocks.PYRITE_WALL, "黃鐵石 벽")
        add(JModBlocks.PYRITE_DOOR, "黃鐵石 문")
        add(JModBlocks.PYRITE_TRAPDOOR, "黃鐵石 함정문")
        add(JModBlocks.PYRITE_LAMP, "黃鐵石 램프")
        add(JModBlocks.WASHI_WINDOW_PANE, "和紙 창문 판")
        add(JModBlocks.WASHI_WINDOW, "和紙 창문")
        add(JModBlocks.SHOJI_WINDOW_PANE, "障子 창 패널")
        add(JModBlocks.SHOJI_WINDOW, "障子 창")
        add(JModBlocks.SHOJI_DOOR, "障子 문")
        add(JModBlocks.CHABUDAI, "차부다이")
        add(JModBlocks.FUSUMA_DOOR, "襖 문")
        add(JModBlocks.RICE_CROP, "벼 作物")
        add(JModBlocks.YAMAZAKI_BERRY_BUSH, "야마자키 베리 덤불")
        add(JModBlocks.CHAIR, "의자")
        add(JModBlocks.PEDESTAL, "받침대")
        add(JModBlocks.HANGING_SCROLL, "행잉 스크롤")
        add(JModBlocks.GROWTH_CHAMBER, "성장실")
        add(JModBlocks.TATAMI_MAT, "畳")
        add(JModBlocks.ZABUTON_BLUE, "청색 座布団")
        add(JModBlocks.ZABUTON_RED, "적색 座布団")
        add(JModBlocks.ZABUTON_GREEN, "녹색 座布団")
        add(JModBlocks.MONEY_VAULT_BLOCK, "머니 金庫")
        //add(JModBlocks.CALLIGRAPHY_TABLE, "書藝대")
        add(JModBlocks.JAPANESE_FLAG, "日本의旗")
        add(JModBlocks.CURSED_BLOCK, "呪詛의블록")
        add(JModBlocks.STONE_LANTERN, "石燈")
        add(JModBlocks.TSUKUBAI, "手水鉢(수수발, 수반)")
        add(JModBlocks.ANDON, "行燈(행등 일본의등실)")

        // CONTAINERS
        add("container.calligraphy_table", "書藝대")

        // CREATIVE TABS
        add("creativetab.everythingjapanese.japanese_items", "日本의 아이템")
        add("creativetab.everythingjapanese.japanese_blocks", "日本의 블록")
        add("creativetab.everythingjapanese.japanese_weapons", "日本의 武器")
        add("creativetab.everythingjapanese.japanese_food", "日本의 飮食")
        add("creativetab.everythingjapanese.japanese_drinks", "日本의 飮料")
        add("creativetab.everythingjapanese.japanese_fuel", "日本의 연료")
        add("creativetab.everythingjapanese.japanese_tools", "日本의 道具")
        add("creativetab.everythingjapanese.japanese_armor", "日本의 防具")
        add("creativetab.everythingjapanese.japanese_armor_trims", "日本의 防具 장식")
        add("creativetab.everythingjapanese.japanese_music", "日本의 音樂")
        add("creativetab.everythingjapanese.japanese_entities", "日本의 엔티티")
        add("creativetab.everythingjapanese.japanese_furniture", "日本의 가구")
        add("creativetab.everythingjapanese.hell", "地獄")
        add("creativetab.everythingjapanese.katana_addons", "카타나 추가 부품")

        // EFFECTS
        add(ModEffects.ADRENALINE_EFFECT.get(), "아드레날린")

        // ENCHANTMENTS
        add("enchantment.everythingjapanese.lightning_striker", "번개 스트라이커")

        // ENTITIES
        add(ModEntities.TRICERATOPS, "트리케라톱스")
        add(ModEntities.CURSED_SAMURAI, "저주받은 侍")
        add(ModEntities.SIKA_DEER, "鹿")
        add(ModEntities.IRON_BATTLE_AXE, "鐵 전투 도끼")
        add("entity.minecraft.villager.everythingjapanese.furniture_maker", "가구 제작자")

        // GUI

        // ITEMS
        add(JModItems.PYRITE_INGOT, "黃鐵石 주괴")
        add(JModItems.NEPHRITE, "軟玉")
        add(JModItems.RAW_PYRITE, "原石 黃鐵石")
        add(JModItems.CHISEL, "끌")
        add(JModItems.PYRITE_SWORD, "黃鐵石의劍")
        add(JModItems.NEPHRITE_SWORD, "軟玉의劍")
        add(JModItems.SUSHI, "스시")
        add(JModItems.RAMEN, "라멘")
        add(JModItems.RICE, "쌀")
        add(JModItems.RICE_SEEDS, "벼 씨앗")
        add(JModItems.RAW_RICE, "生 쌀")
        add(JModItems.GREEN_TEA, "녹차")
        add(JModItems.DIESEL, "디젤")
        add(JModItems.INCENSE, "향")
        add(JModItems.UDON, "우동")
        //add(JModItems.HELL_PORTAL_ACTIVATOR, "地獄 포탈 활성화기")
        add(JModItems.YA, "야")
        add(JModItems.TALISMAN_ITEM, "부적")
        add(JModItems.PYRITE_AXE, "黃鐵石의斧")
        add(JModItems.PYRITE_PICKAXE, "黃鐵石의곡괭이")
        add(JModItems.PYRITE_SHOVEL, "黃鐵石의삽")
        add(JModItems.PYRITE_HOE, "黃鐵石의괭이")
        add(JModItems.NEPHRITE_AXE, "軟玉의斧")
        add(JModItems.NEPHRITE_PICKAXE, "軟玉의곡괭이")
        add(JModItems.NEPHRITE_SHOVEL, "軟玉의삽")
        add(JModItems.NEPHRITE_HOE, "軟玉의괭이")
        add(JModItems.PYRITE_HELMET, "黃鐵石의투구")
        add(JModItems.PYRITE_CHESTPLATE, "黃鐵石의胸甲")
        add(JModItems.CHIRETSU_SHO_SCROLL, "地裂掌의術의주문서")
        add(JModItems.EKIRETSU_SHO_SCROLL, "液裂掌의術의주문서")
        add(JModItems.FIREBALL_SCROLL, "火炎球의術의주문서")
        add(JModItems.WINDBALL_SCROLL, "바람구슬의術의주문서")
        add(JModItems.LIFE_STEAL_JUTSU_SCROLL, "生命吸收의術의주문서")
        //add(JModItems.SCROLL, "두루마리")
        add(JModItems.PYRITE_LEGGINGS, "黃鐵石 다리갑옷")
        add(JModItems.PYRITE_BOOTS, "黃鐵石 부츠")
        add(JModItems.NEPHRITE_HELMET, "軟玉 투구")
        add(JModItems.NEPHRITE_CHESTPLATE, "軟玉 胸甲")
        add(JModItems.NEPHRITE_LEGGINGS, "軟玉 다리갑옷")
        add(JModItems.NEPHRITE_BOOTS, "軟玉 부츠")
        add(JModItems.SAMURAI_HELMET, "侍 투구")
        add(JModItems.SAMURAI_CHESTPLATE, "侍 胸甲")
        add(JModItems.SAMURAI_LEGGINGS, "侍 다리갑옷")
        add(JModItems.SAMURAI_BOOTS, "侍 부츠")
        add(JModItems.PYRITE_HAMMER, "黃鐵石 망치")
        add(JModItems.PYRITE_HORSE_ARMOR, "黃鐵石 馬 갑옷")
        add(JModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "鯉의 魚의 鍛冶 템플릿")
        add(JModItems.DAIKYU, "長弓")
        add(JModItems.AO_TO_NATSU_MUSIC_DISC, "아오토 나츠 음악 디스크")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - Ao to Natsu")
        add("item.minecraft.potion.effect.adrenaline_potion", "아드레날린 포션")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "아드레날린 튀기는 포션")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "아드레날린 지속 포션")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "아드레날린 묻힌 화살")
        add(JModItems.YAMAZAKI_BERRIES, "야마자키 베리")
        add(JModItems.TRICERATOPS_SPAWN_EGG, "트리케라톱스 생성 알")
        add(JModItems.CURSED_SAMURAI_SPAWN_EGG, "저주받은 侍 생성 알")
        add(JModItems.SIKA_DEER_SPAWN_EGG, "鹿 생성 알")
        add(JModItems.PYRITE_BATTLE_AXE, "黃鐵石 전투 도끼")
        add(JModItems.IRON_BATTLE_AXE, "鐵 전투 도끼")
        add(JModItems.RADIATION_STAFF, "방사능 지팡이")
        add(JModItems.KATANA, "카타나")
        add(JModItems.SOUL_DAGGER, "영혼 단검")
        add(JModItems.BLADE_STEEL, "카타나 강철 칼날")
        add(JModItems.BLACK_WRAP, "검은색 칼자루 감싸기")
        add(JModItems.RED_WRAP, "빨간색 칼자루 감싸기")
        add(JModItems.WHITE_WRAP, "흰색 칼자루 감싸기")
        add(JModItems.CREDIT_CARD_ITEM, "신용 카드")
        //add(JModItems.SOUL_GUITAR, "소울 기타")
        add(JModItems.GUN, "銃")
        add(JModItems.BULLET, "彈丸")
        add(JModItems.POWERED_SWORD, "動力劍")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "바람구슬")
        add("jutsu.everythingjapanese.fireball", "火炎球")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "地熱長")

        // KEYS
        add("key.category.everythingjapanese.jutsu", "주술")
        add("key.everythingjapanese.cast_jutsu", "주술 시전")
        add("key.everythingjapanese.cycle_jutsu", "주술 변경")
        add("key.category.everythingjapanese.chakra", "차크라")
        add("key.everythingjapanese.regen_chakra", "차크라 회복")

        // PAINTINGS
        add("painting.everythingjapanese.samurai.title", "侍")
        add("painting.everythingjapanese.samurai.author", "Chidoziealways")

        // SOUNDS
        add("sounds.everythingjapanese.chisel_use", "끌 사용")
        add("sounds.everythingjapanese.magic_block_break", "마법 블록 파괴")
        add("sounds.everythingjapanese.magic_block_fall", "마법 블록에 떨어짐")
        add("sounds.everythingjapanese.magic_block_hit", "마법 블록 공격")
        add("sounds.everythingjapanese.magic_block_place", "마법 블록 설치")
        add("sounds.everythingjapanese.magic_block_step", "마법 블록 밟음")

        // TOOLTIPS
        add("tooltip.everythingjapanese.magic_block.tooltip", "던진 아이템을 완전히 다른 아이템으로 변환하는 블록")
        add("tooltip.everythingjapanese.chisel_item.shift_down", "Shift 키를 눌러 더 보기")
        add("tooltip.everythingjapanese.chisel_item", "특정 아이템을 다른 아이템으로 바꾸는 도구")
        add("tooltip.everythingjapanese.sushi", "매우 인기 있는 일본 음식")
        add("tooltip.everythingjapanese.earth_katana", "나무 カタナ. 말할 수 없는 힘을 가지고 있으며, 地獄의 악마를 소환할 수도 있다")
        add("tooltip.everythingjapanese.earth_katana.no_shift", "Shift 키를 눌러 더 많은 정보를 보기")

        // TRIM PATTERNS & MATERIALS
        add("trim_pattern.everythingjapanese.koi_fish", "잉어 갑옷 트림")
        add("trim_material.everythingjapanese.pyrite", "黃鐵石 재료")

        // DIALOGS
        add("dialog.everythingjapanese.curse_confirm_dialog", "정말이플레이어를詛呪(저주)하시겠습니까?")
        add("dialog.everythingjapanese.curse_name", "이플레이어의一日(하루)를槌(망치)기前(전)에당신의플레이어名(이름)이必要(필요)합니다")
        add("dialog.everythingjapanese.curse_yes", "詛呪(저주)하라")
        add("dialog.everythingjapanese.curse_no", "容恕(용서)하라")

        // SCREENS
        add("screen.everythingjapanese.cursed_block", "플레이어를詛呪(저주)해")
    }
}
