package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.custom.*
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.Chidoziealways.everythingjapanese.trim.ModTrimMaterials
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.DamageTypeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.*
import net.minecraft.world.item.Item
import net.minecraft.world.item.component.BlocksAttacks
import net.minecraft.world.item.component.BlocksAttacks.DamageReduction
import net.minecraft.world.item.component.BlocksAttacks.ItemDamageFunction
import net.minecraft.world.item.equipment.ArmorType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.*

object ModItems {
    private val log: Logger? = LoggerFactory.getLogger(ModItems::class.java)

    val ITEMS = DeferredRegister.createItems(MOD_ID)

    val PYRITE_INGOT by ITEMS.register("pyrite_ingot")
        { ->
            Item(Item.Properties()
                .trimMaterial(ModTrimMaterials.PYRITE)
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_ingot"))))
        }

    val KATANA by ITEMS.register("katana")
        { ->
            KatanaItem(Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "katana"))))
        }

    val BLADE_STEEL by ITEMS.register("blade_steel")
    { ->
        Item(Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "blade_steel"))))
    }

    val BLACK_WRAP by ITEMS.register("wrap_black")
    { ->
        Item(Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "wrap_black"))))
    }

    val RED_WRAP by ITEMS.register("wrap_red")
    { ->
        Item(Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "wrap_red"))))
    }

    val WHITE_WRAP by ITEMS.register("wrap_white")
    { ->
        Item(Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "wrap_white"))))
    }

    val CHIRETSU_SHO_SCROLL by ITEMS.register("chiretsu_sho_scroll")
    { ->
        JutsuScrollItem(
            "chiretsu_sho_jutsu",
            Item.Properties().setId(
                ResourceKey.create(
                    Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "chiretsu_sho_scroll")
                )
            )
        )
    }

    val TALISMAN_ITEM by ITEMS.register("talisman_item")
    { ->
        TalismanItem(Item.Properties().setId(ResourceKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "talisman_item")
        )))
    }

    val FIREBALL_SCROLL by ITEMS.register(
        "fireball_scroll")
        { ->
            JutsuScrollItem(
                "fireball",
                Item.Properties().setId(
                    ResourceKey.create(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "fireball_scroll")
                    )
                )
            )
        }

    val WINDBALL_SCROLL by ITEMS.register(
        "windball_scroll")
        { ->
            JutsuScrollItem(
                "small_windball",
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "windball_scroll")
                    )
                )
            )
        }

    val HELL_PORTAL_ACTIVATOR by ITEMS.register(
        "hell_portal_activator")
        { ->
            HellPortalItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell_portal_activator")
                    )
                )
            )
        }

    val NEPHRITE by ITEMS.register(
        "nephrite")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite")
                    )
                )
            )
        }

    val RAW_PYRITE by ITEMS.register(
        "raw_pyrite")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_pyrite")
                    )
                )
            )
        }

    val CHISEL by ITEMS.register(
        "chisel")
        { ->
            ChiselItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "chisel")
                    )
                )
                    .durability(100).stacksTo(1)
            )
        }

    val PYRITE_SWORD by ITEMS.register(
        "pyrite_sword")
        { ->
            Item(
                Item.Properties()
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_sword")
                        )
                    )
                    .sword(ModToolMaterials.PYRITE, 3f, -2.4f)
                    .component<BlocksAttacks?>(
                        DataComponents.BLOCKS_ATTACKS, BlocksAttacks(
                            0.25f,
                            1.0f,
                            listOf<DamageReduction?>(
                                DamageReduction(
                                    90.0f,
                                    Optional.empty<HolderSet<DamageType?>?>(),
                                    0.0f,
                                    0.5f
                                )
                            ),
                            ItemDamageFunction(3.0f, 1.0f, 1.0f),
                            Optional.of<TagKey<DamageType?>?>(DamageTypeTags.BYPASSES_SHIELD),
                            Optional.of<Holder<SoundEvent?>?>(SoundEvents.SHIELD_BLOCK),
                            Optional.of<Holder<SoundEvent?>?>(SoundEvents.SHIELD_BREAK)
                        )
                    )
            )
        }

    val PYRITE_PICKAXE by ITEMS.register(
        "pyrite_pickaxe")
        { ->
            Item(
                Item.Properties()
                    .pickaxe(ModToolMaterials.PYRITE, 1f, -2.8f)
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_pickaxe")
                        )
                    )
            )
        }

    val PYRITE_SHOVEL by ITEMS.register(
        "pyrite_shovel")
        { ->
            ShovelItem(
                ModToolMaterials.PYRITE, 1.5f, -3.0f,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_shovel")
                    )
                )
            )
        }

    val PYRITE_AXE by ITEMS.register(
        "pyrite_axe")
        { ->
            AxeItem(
                ModToolMaterials.PYRITE, 6f, -3.2f,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_axe")
                    )
                )
            )
        }

    val PYRITE_HOE by ITEMS.register(
        "pyrite_hoe")
        { ->
            HoeItem(
                ModToolMaterials.PYRITE, 0f, -3.0f,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_hoe")
                    )
                )
            )
        }

    val SUSHI by ITEMS.register(
        "sushi")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "sushi")
                    )
                )
                    .food(ModFoodProperties.SUSHI, ModFoodProperties.SUSHI_EFFECT).usingConvertsTo(Items.BOWL)
            )
        }

    val GREEN_TEA by ITEMS.register(
        "green_tea")
        { ->
            Drinks(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "green_tea")
                    )
                )
                    .stacksTo(1)
            )
        }

    val DIESEL by ITEMS.register(
        "diesel")
        { ->
            FuelItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "diesel")
                    )
                )
                    .stacksTo(1), 1200
            )
        }

    val UDON by ITEMS.register(
        "udon")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "udon")
                    )
                )
                    .food(ModFoodProperties.UDON, ModFoodProperties.UDON_EFFECT).usingConvertsTo(Items.BOWL).stacksTo(1)
            )
        }

    val INCENSE by ITEMS.register(
        "incense")
        { ->
            FuelItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "incense")
                    )
                )
                    .stacksTo(1), 20000
            )
        }

    val YA by ITEMS.register(
        "ya")
        { ->
            ArrowItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "ya")
                    )
                )
            )
        }

    val NEPHRITE_SWORD by ITEMS.register(
        "nephrite_sword")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_sword")
                    )
                )
                    .sword(ModToolMaterials.NEPHRITE, 7f, -1.9f)
                    .component<BlocksAttacks?>(
                        DataComponents.BLOCKS_ATTACKS, BlocksAttacks(
                            0.1f,
                            1.4f,
                            listOf<DamageReduction?>(
                                DamageReduction(
                                    90.0f,
                                    Optional.empty<HolderSet<DamageType?>?>(),
                                    0.0f,
                                    0.5f
                                )
                            ),
                            ItemDamageFunction(3.0f, 1.0f, 1.0f),
                            Optional.of<TagKey<DamageType?>?>(DamageTypeTags.BYPASSES_SHIELD),
                            Optional.of<Holder<SoundEvent?>?>(SoundEvents.SHIELD_BLOCK),
                            Optional.of<Holder<SoundEvent?>?>(SoundEvents.SHIELD_BREAK)
                        )
                    )
            )
        }

    val NEPHRITE_PICKAXE by ITEMS.register(
        "nephrite_pickaxe")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_pickaxe")
                    )
                )
                    .pickaxe(ModToolMaterials.NEPHRITE, 5f, -2.5f)
            )
        }

    val NEPHRITE_SHOVEL by ITEMS.register(
        "nephrite_shovel")
        { ->
            ShovelItem(
                ModToolMaterials.NEPHRITE, 4.0f, -2.0f,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_shovel")
                    )
                )
            )
        }

    val NEPHRITE_AXE by ITEMS.register(
        "nephrite_axe")
        { ->
            AxeItem(
                ModToolMaterials.NEPHRITE, 11f, -2.9f,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_axe")
                    )
                )
            )
        }

    val NEPHRITE_HOE by ITEMS.register(
        "nephrite_hoe")
        { ->
            HoeItem(
                ModToolMaterials.NEPHRITE, 1f, -3.0f,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_hoe")
                    )
                )
            )
        }

    val PYRITE_HAMMER by ITEMS.register(
        "pyrite_hammer")
        { ->
            HammerItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_hammer")
                    )
                )
                    .pickaxe(ModToolMaterials.PYRITE, 7f, -2.5f)
            )
        }

    val PYRITE_HELMET by ITEMS.register(
        "pyrite_helmet")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_helmet")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.HELMET)
            )
        }

    val PYRITE_CHESTPLATE by ITEMS.register(
        "pyrite_chestplate")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_chestplate")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.CHESTPLATE)
            )
        }

    val PYRITE_LEGGINGS by ITEMS.register(
        "pyrite_leggings")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_leggings")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.LEGGINGS)
            )
        }

    val PYRITE_BOOTS by ITEMS.register(
        "pyrite_boots")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_boots")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.BOOTS)
            )
        }

    val NEPHRITE_HELMET by ITEMS.register(
        "nephrite_helmet")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_helmet")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.HELMET)
            )
        }

    val NEPHRITE_CHESTPLATE by ITEMS.register(
        "nephrite_chestplate")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_chestplate")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.CHESTPLATE)
            )
        }

    val NEPHRITE_LEGGINGS by ITEMS.register(
        "nephrite_leggings")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_leggings")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.LEGGINGS)
            )
        }

    val NEPHRITE_BOOTS by ITEMS.register(
        "nephrite_boots")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_boots")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.BOOTS)
            )
        }

    val PYRITE_HORSE_ARMOR by ITEMS.register(
        "pyrite_horse_armor")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_horse_armor")
                    )
                )
                    .horseArmor(ModArmorMaterials.PYRITE)
            )
        }

    val KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE by ITEMS.register(
        "koi_fish_armor_trim_smithing_template")
        { ->
            SmithingTemplateItem.createArmorTrimTemplate(
                Item.Properties()
                    .setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(
                            MOD_ID,
                            "koi_fish_armor_trim_smithing_template"
                        )
                    )
                )
                    .rarity(Rarity.UNCOMMON)
            )
        }

    val DAIKYU by ITEMS.register(
        "daikyu")
        { ->
            BowItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "daikyu")
                    )
                )
                    .durability(600)
            )
        }

    val AO_TO_NATSU_MUSIC_DISC by ITEMS.register(
        "ao_to_natsu_music_disc")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "ao_to_natsu_music_disc")
                    )
                )
                    .jukeboxPlayable(ModSounds.AO_TO_NATSU_KEY).stacksTo(1)
            )
        }

    val RICE_SEEDS by ITEMS.register(
        "rice_seeds")
        { ->
            BlockItem(
                ModBlocks.RICE_CROP, Item.Properties().useItemDescriptionPrefix()
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "rice_seeds")
                        )
                    )
            )
        }

    val RICE by ITEMS.register(
        "rice")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "rice")
                    )
                )
                    .food(ModFoodProperties.RICE).usingConvertsTo(Items.BOWL)
            )
        }

    val RAW_RICE by ITEMS.register(
        "raw_rice")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_rice")
                    )
                )
            )
        }

    val YAMAZAKI_BERRIES by ITEMS.register(
        "yamazaki_berries")
        { ->
            BlockItem(
                ModBlocks.YAMAZAKI_BERRY_BUSH, Item.Properties().useItemDescriptionPrefix()
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "yamazaki_berries")
                        )
                    )
                    .food(ModFoodProperties.YAMAZAKI_BERRIES)
            )
        }

    val SAMURAI_HELMET by ITEMS.register(
        "samurai_helmet")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_helmet")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.HELMET)
            )
        }

    val SAMURAI_CHESTPLATE by ITEMS.register(
        "samurai_chestplate")
         { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_chestplate")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.CHESTPLATE)
            )
        }

    val SAMURAI_LEGGINGS by ITEMS.register(
        "samurai_leggings")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_leggings")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.LEGGINGS)
            )
        }

    val SAMURAI_BOOTS by ITEMS.register(
        "samurai_boots")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_boots")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.BOOTS)
            )
        }

    val TRICERATOPS_SPAWN_EGG by ITEMS.register(
        "triceratops_spawn_egg")
        { ->
            SpawnEggItem(
                ModEntities.TRICERATOPS,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "triceratops_spawn_egg")
                    )
                )
            )
        }

    val CURSED_SAMURAI_SPAWN_EGG by ITEMS.register(
        "cursed_samurai_spawn_egg")
    { ->
        SpawnEggItem(
            ModEntities.CURSED_SAMURAI,
            Item.Properties().setId(
                ResourceKey.create(
                    Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "cursed_samurai_spawn_egg")
                )
            )
        )
    }

    val SIKA_DEER_SPAWN_EGG by ITEMS.register(
        "sika_deer_spawn_egg")
        { ->
            SpawnEggItem(
                ModEntities.SIKA_DEER,
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "sika_deer_spawn_egg")
                    )
                )
            )
        }

    val PYRITE_BATTLE_AXE by ITEMS.register(
        "pyrite_battle_axe")
        { ->
            MaceItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_battle_axe")
                    )
                )
            )
        }

    val IRON_BATTLE_AXE by ITEMS.register(
        "iron_battle_axe")
        { ->
            IronBattleAxeItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "iron_battle_axe")
                    )
                )
                    .stacksTo(16)
            )
        }

    val RADIATION_STAFF by ITEMS.register(
        "radiation_staff")
        { ->
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "radiation_staff")
                    )
                )
                    .stacksTo(1)
            )
        }

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE ITEM IN MODITEMS")
    }
}
