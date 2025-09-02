package net.Chidoziealways.everythingjapanese.datagen

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

class KoreanLanguageProvider(output: PackOutput) : LanguageProvider(output, MOD_ID, "ko_kr") {
    override fun addTranslations() {
        // ADVANCEMENTS
        add("advancements.hell.root.title", "지옥")
        add("advancements.hell.root.description", "탈출구가 없다")

        // ARGUMENTS
        add("argument.jutsu.id.invalid", "알 수 없는 주술 '%s'")
        add("arguments.jutsu.component.unknown", "알 수 없는 주술 구성 요소 '%s'")
        add("arguments.jutsu.component.malformed", "잘못된 '%s' 구성 요소: '%s'")
        add("arguments.jutsu.component.expected", "주술 구성 요소가 필요합니다")
        add("arguments.jutsu.component.repeated", "주술 구성 요소 '%s'가 중복되었습니다. 하나만 지정할 수 있습니다")
        add("arguments.jutsu.malformed", "잘못된 주술: '%s'")

        // BLOCKS
        add(ModBlocks.PYRITE_BLOCK, "황철석 블록")
        add(ModBlocks.NEPHRITE_BLOCK, "연옥 블록")
        add(ModBlocks.RAW_PYRITE_BLOCK, "원석 황철석 블록")
        add(ModBlocks.PYRITE_ORE, "황철석 광석")
        add(ModBlocks.PYRITE_DEEPSLATE_ORE, "심층 황철석 광석")
        add(ModBlocks.NEPHRITE_ORE, "연옥 광석")
        add(ModBlocks.NEPHRITE_DEEPSLATE_ORE, "심층 연옥 광석")
        add(ModBlocks.TRANSFORMER_BLOCK, "변환기 블록")
        add(ModBlocks.HINOKI_MARUTA, "히노키 통나무")
        add(ModBlocks.HINOKI_MOKUZAI, "히노키 목재")
        add(ModBlocks.STRIPPED_HINOKI_MARUTA, "껍질 벗긴 히노키 통나무")
        add(ModBlocks.STRIPPED_HINOKI_MOKUZAI, "껍질 벗긴 히노키 목재")
        add(ModBlocks.HINOKI_BAN, "히노키 판재")
        add(ModBlocks.HINOKI_HA, "히노키 잎")
        add(ModBlocks.HINOKI_NAEGI, "히노키 묘목")
        add(ModBlocks.CHOCOLATE_CAKE, "초콜릿 케이크")
        add(ModBlocks.JAPANESE_CHEESECAKE, "일본식 치즈케이크")
        add(ModBlocks.PYRITE_STAIRS, "황철석 계단")
        add(ModBlocks.PYRITE_SLAB, "황철석 슬래브")
        add(ModBlocks.PYRITE_BUTTON, "황철석 버튼")
        add(ModBlocks.PYRITE_PRESSURE_PLATE, "황철석 감압판")
        add(ModBlocks.PYRITE_FENCE, "황철석 울타리")
        add(ModBlocks.PYRITE_FENCE_GATE, "황철석 울타리 문")
        add(ModBlocks.PYRITE_WALL, "황철석 벽")
        add(ModBlocks.PYRITE_DOOR, "황철석 문")
        add(ModBlocks.PYRITE_TRAPDOOR, "황철석 함정문")
        add(ModBlocks.PYRITE_LAMP, "황철석 램프")
        add(ModBlocks.WASHI_WINDOW_PANE, "와시 창문 판")
        add(ModBlocks.WASHI_WINDOW, "와시 창문")
        add(ModBlocks.SHOJI_DOOR, "쇼지 문")
        add(ModBlocks.CHABUDAI, "차부다이")
        add(ModBlocks.FUSUMA_DOOR, "후스마 문")
        add(ModBlocks.RICE_CROP, "벼 작물")
        add(ModBlocks.YAMAZAKI_BERRY_BUSH, "야마자키 베리 덤불")
        add(ModBlocks.CHAIR, "의자")
        add(ModBlocks.PEDESTAL, "받침대")
        add(ModBlocks.GROWTH_CHAMBER, "성장실")
        add(ModBlocks.TATAMI_MAT, "다다미")
        add(ModBlocks.ZABUTON_BLUE, "청색 자부톤")
        add(ModBlocks.ZABUTON_RED, "적색 자부톤")
        add(ModBlocks.ZABUTON_GREEN, "녹색 자부톤")
        add(ModBlocks.MONEY_VAULT_BLOCK, "머니 금고")

        // CREATIVE TABS
        add("creativetab.everythingjapanese.japanese_items", "일본 아이템")
        add("creativetab.everythingjapanese.japanese_blocks", "일본 블록")
        add("creativetab.everythingjapanese.japanese_weapons", "일본 무기")
        add("creativetab.everythingjapanese.japanese_food", "일본 음식")
        add("creativetab.everythingjapanese.japanese_drinks", "일본 음료")
        add("creativetab.everythingjapanese.japanese_fuel", "일본 연료")
        add("creativetab.everythingjapanese.japanese_tools", "일본 도구")
        add("creativetab.everythingjapanese.japanese_armor", "일본 갑옷")
        add("creativetab.everythingjapanese.japanese_armor_trims", "일본 갑옷 장식")
        add("creativetab.everythingjapanese.japanese_music", "일본 음악")
        add("creativetab.everythingjapanese.japanese_entities", "일본 엔티티")
        add("creativetab.everythingjapanese.japanese_furniture", "일본 가구")
        add("creativetab.everythingjapanese.hell", "지옥")
        add("creativetab.everythingjapanese.katana_addons", "카타나 추가 부품")

        // EFFECTS
        add(ModEffects.ADRENALINE_EFFECT.get(), "아드레날린")

        // ENCHANTMENTS
        add("enchantment.everythingjapanese.lightning_striker", "번개 스트라이커")

        // ENTITIES
        add(ModEntities.TRICERATOPS, "트리케라톱스")
        add(ModEntities.CURSED_SAMURAI, "저주받은 사무라이")
        add(ModEntities.SIKA_DEER, "사슴")
        add(ModEntities.IRON_BATTLE_AXE, "철 전투 도끼")
        add("entity.minecraft.villager.everythingjapanese.furniture_maker", "가구 제작자")

        // GUI
        add("gui.quests", "퀘스트")

        // ITEMS
        add(ModItems.PYRITE_INGOT, "황철석 주괴")
        add(ModItems.NEPHRITE, "연옥")
        add(ModItems.RAW_PYRITE, "원석 황철석")
        add(ModItems.CHISEL, "끌")
        add(ModItems.PYRITE_SWORD, "황철석 검")
        add(ModItems.NEPHRITE_SWORD, "연옥 검")
        add(ModItems.SUSHI, "스시")
        add(ModItems.RICE, "쌀")
        add(ModItems.RICE_SEEDS, "벼 씨앗")
        add(ModItems.RAW_RICE, "생 쌀")
        add(ModItems.GREEN_TEA, "녹차")
        add(ModItems.DIESEL, "디젤")
        add(ModItems.INCENSE, "향")
        add(ModItems.UDON, "우동")
        add(ModItems.HELL_PORTAL_ACTIVATOR, "지옥 포탈 활성화기")
        add(ModItems.YA, "야")
        add(ModItems.TALISMAN_ITEM, "부적")
        add(ModItems.PYRITE_AXE, "황철석 도끼")
        add(ModItems.PYRITE_PICKAXE, "황철석 곡괭이")
        add(ModItems.PYRITE_SHOVEL, "황철석 삽")
        add(ModItems.PYRITE_HOE, "황철석 괭이")
        add(ModItems.NEPHRITE_AXE, "연옥 도끼")
        add(ModItems.NEPHRITE_PICKAXE, "연옥 곡괭이")
        add(ModItems.NEPHRITE_SHOVEL, "연옥 삽")
        add(ModItems.NEPHRITE_HOE, "연옥 괭이")
        add(ModItems.PYRITE_HELMET, "황철석 투구")
        add(ModItems.PYRITE_CHESTPLATE, "황철석 흉갑")
        add(ModItems.CHIRETSU_SHO_SCROLL, "지열장 주문서")
        add(ModItems.FIREBALL_SCROLL, "화염구 주문서")
        add(ModItems.WINDBALL_SCROLL, "바람구슬 주문서")
        add(ModItems.PYRITE_LEGGINGS, "황철석 다리갑옷")
        add(ModItems.PYRITE_BOOTS, "황철석 부츠")
        add(ModItems.NEPHRITE_HELMET, "연옥 투구")
        add(ModItems.NEPHRITE_CHESTPLATE, "연옥 흉갑")
        add(ModItems.NEPHRITE_LEGGINGS, "연옥 다리갑옷")
        add(ModItems.NEPHRITE_BOOTS, "연옥 부츠")
        add(ModItems.SAMURAI_HELMET, "사무라이 투구")
        add(ModItems.SAMURAI_CHESTPLATE, "사무라이 흉갑")
        add(ModItems.SAMURAI_LEGGINGS, "사무라이 다리갑옷")
        add(ModItems.SAMURAI_BOOTS, "사무라이 부츠")
        add(ModItems.PYRITE_HAMMER, "황철석 망치")
        add(ModItems.PYRITE_HORSE_ARMOR, "황철석 말 갑옷")
        add(ModItems.KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE, "잉어 갑옷 장식 제작템플릿")
        add(ModItems.DAIKYU, "긴 활")
        add(ModItems.AO_TO_NATSU_MUSIC_DISC, "아오토 나츠 음악 디스크")
        add("item.everythingjapanese.ao_to_natsu_music_disc.desc", "Mrs. Green Apple - Ao to Natsu")
        add("item.minecraft.potion.effect.adrenaline_potion", "아드레날린 포션")
        add("item.minecraft.splash_potion.effect.adrenaline_potion", "아드레날린 튀기는 포션")
        add("item.minecraft.lingering_potion.effect.adrenaline_potion", "아드레날린 지속 포션")
        add("item.minecraft.tipped_arrow.effect.adrenaline_potion", "아드레날린 묻힌 화살")
        add(ModItems.YAMAZAKI_BERRIES, "야마자키 베리")
        add(ModItems.TRICERATOPS_SPAWN_EGG, "트리케라톱스 생성 알")
        add(ModItems.CURSED_SAMURAI_SPAWN_EGG, "저주받은 사무라이 생성 알")
        add(ModItems.SIKA_DEER_SPAWN_EGG, "사슴 생성 알")
        add(ModItems.PYRITE_BATTLE_AXE, "황철석 전투 도끼")
        add(ModItems.IRON_BATTLE_AXE, "철 전투 도끼")
        add(ModItems.RADIATION_STAFF, "방사능 지팡이")
        add(ModItems.KATANA, "카타나")
        add(ModItems.BLADE_STEEL, "카타나 강철 칼날")
        add(ModItems.BLACK_WRAP, "검은색 칼자루 감싸기")
        add(ModItems.RED_WRAP, "빨간색 칼자루 감싸기")
        add(ModItems.WHITE_WRAP, "흰색 칼자루 감싸기")
        add(ModItems.CREDIT_CARD_ITEM, "신용 카드")

        // JUTSU
        add("jutsu.everythingjapanese.small_windball", "바람구슬")
        add("jutsu.everythingjapanese.fireball", "화염구")
        add("jutsu.everythingjapanese.chiretsu_sho_jutsu", "지열장")

        // KEYS
        add("key.everythingjapanese.cast_jutsu", "주술 시전")
        add("key.everythingjapanese.cycle_jutsu", "주술 변경")
        add("key.categories.jutsu", "주술")
        add("key.everythingjapanese.show_quests", "퀘스트 표시")
        add("key.categories.quest", "퀘스트")

        // PAINTINGS
        add("painting.everythingjapanese.samurai.title", "사무라이")
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
        add("tooltip.everythingjapanese.earth_katana", "나무 카타나. 말할 수 없는 힘을 가지고 있으며, 지옥의 악마를 소환할 수도 있다")
        add("tooltip.everythingjapanese.earth_katana.no_shift", "Shift 키를 눌러 더 많은 정보를 보기")

        // TRIM PATTERNS & MATERIALS
        add("trim_pattern.everythingjapanese.koi_fish", "잉어 갑옷 트림")
        add("trim_material.everythingjapanese.pyrite", "황철석 재료")
    }
}
