package net.Chidoziealways.everythingjapanese.item

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.item.custom.*
import net.Chidoziealways.everythingjapanese.sound.ModSounds
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
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.List
import java.util.function.Supplier

object ModItems {
    private val log: Logger? = LoggerFactory.getLogger(ModItems::class.java)

    val ITEMS: DeferredRegister<Item?> =
        DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)

    val PYRITE_INGOT: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_ingot",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_ingot")
                    )
                )
            )
        })

    val SMALL_FIREBALL_SCROLL: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "small_fireball_scroll",
        Supplier {
            JutsuScrollItem(
                "small_fireball",
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_fireball_scroll")
                    )
                )
            )
        })

    val LARGE_FIREBALL_SCROLL: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "large_fireball_scroll",
        Supplier {
            JutsuScrollItem(
                "large_fireball",
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "large_fireball_scroll")
                    )
                )
            )
        })

    val WINDBALL_SCROLL: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "windball_scroll",
        Supplier {
            JutsuScrollItem(
                "small_windball",
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "windball_scroll")
                    )
                )
            )
        })

    val HELL_PORTAL_ACTIVATOR: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "hell_portal_activator",
        Supplier {
            HellPortalItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell_portal_activator")
                    )
                )
            )
        })

    val NEPHRITE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite")
                    )
                )
            )
        })

    val RAW_PYRITE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "raw_pyrite",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_pyrite")
                    )
                )
            )
        })

    val CHISEL: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "chisel",
        Supplier {
            ChiselItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "chisel")
                    )
                )
                    .durability(100).stacksTo(1)
            )
        })

    val PYRITE_SWORD: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_sword",
        Supplier {
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
                            List.of<DamageReduction?>(
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
        })

    val PYRITE_PICKAXE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_pickaxe",
        Supplier {
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
        })

    val PYRITE_SHOVEL: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_shovel",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_shovel")
                    )
                )
                    .shovel(ModToolMaterials.PYRITE, 1.5f, -3.0f)
            )
        })

    val PYRITE_AXE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_axe",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_axe")
                    )
                )
                    .axe(ModToolMaterials.PYRITE, 6f, -3.2f)
            )
        })

    val PYRITE_HOE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_hoe",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_hoe")
                    )
                )
                    .hoe(ModToolMaterials.PYRITE, 0f, -3.0f)
            )
        })

    val SUSHI: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "sushi",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "sushi")
                    )
                )
                    .food(ModFoodProperties.SUSHI, ModFoodProperties.SUSHI_EFFECT).usingConvertsTo(Items.BOWL)
            )
        })

    val GREEN_TEA: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "green_tea",
        Supplier {
            Drinks(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "green_tea")
                    )
                )
                    .stacksTo(1)
            )
        })

    val DIESEL: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "diesel",
        Supplier {
            FuelItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "diesel")
                    )
                )
                    .stacksTo(1), 1200
            )
        })

    val UDON: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "udon",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "udon")
                    )
                )
                    .food(ModFoodProperties.UDON, ModFoodProperties.UDON_EFFECT).usingConvertsTo(Items.BOWL).stacksTo(1)
            )
        })

    val INCENSE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "incense",
        Supplier {
            FuelItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "incense")
                    )
                )
                    .stacksTo(1), 20000
            )
        })

    val YA: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "ya",
        Supplier {
            ArrowItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "ya")
                    )
                )
            )
        })

    val WOODEN_KATANA: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "wooden_katana",
        Supplier {
            Katana(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "wooden_katana")
                    )
                )
                    .sword(ToolMaterial.WOOD, 3.0f, -2.4f), MobEffects()
            )
        })

    val NEPHRITE_SWORD: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_sword",
        Supplier {
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
                            List.of<DamageReduction?>(
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
        })

    val NEPHRITE_PICKAXE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_pickaxe",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_pickaxe")
                    )
                )
                    .pickaxe(ModToolMaterials.NEPHRITE, 5f, -2.5f)
            )
        })

    val NEPHRITE_SHOVEL: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_shovel",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_shovel")
                    )
                )
                    .shovel(ModToolMaterials.NEPHRITE, 4.0f, -2.0f)
            )
        })

    val NEPHRITE_AXE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_axe",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_axe")
                    )
                )
                    .axe(ModToolMaterials.NEPHRITE, 11f, -2.9f)
            )
        })

    val NEPHRITE_HOE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_hoe",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_hoe")
                    )
                )
                    .hoe(ModToolMaterials.NEPHRITE, 1f, -3.0f)
            )
        })

    val PYRITE_HAMMER: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_hammer",
        Supplier {
            HammerItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_hammer")
                    )
                )
                    .pickaxe(ModToolMaterials.PYRITE, 7f, -2.5f)
            )
        })

    val PYRITE_HELMET: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_helmet",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_helmet")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.HELMET)
            )
        })

    val PYRITE_CHESTPLATE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_chestplate",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_chestplate")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.CHESTPLATE)
            )
        })

    val PYRITE_LEGGINGS: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_leggings",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_leggings")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.LEGGINGS)
            )
        })

    val PYRITE_BOOTS: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_boots",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_boots")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.BOOTS)
            )
        })

    val NEPHRITE_HELMET: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_helmet",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_helmet")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.HELMET)
            )
        })

    val NEPHRITE_CHESTPLATE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_chestplate",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_chestplate")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.CHESTPLATE)
            )
        })

    val NEPHRITE_LEGGINGS: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_leggings",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_leggings")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.LEGGINGS)
            )
        })

    val NEPHRITE_BOOTS: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "nephrite_boots",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_boots")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.BOOTS)
            )
        })

    val PYRITE_HORSE_ARMOR: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_horse_armor",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_horse_armor")
                    )
                )
                    .horseArmor(ModArmorMaterials.PYRITE)
            )
        })

    val KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "koi_fish_armor_trim_smithing_template",
        Supplier {
            SmithingTemplateItem.createArmorTrimTemplate(
                Item.Properties().setId(
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
        })

    val DAIKYU: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "daikyu",
        Supplier {
            BowItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "daikyu")
                    )
                )
                    .durability(600)
            )
        })

    val AO_TO_NATSU_MUSIC_DISC: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "ao_to_natsu_music_disc",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "ao_to_natsu_music_disc")
                    )
                )
                    .jukeboxPlayable(ModSounds.AO_TO_NATSU_KEY).stacksTo(1)
            )
        })

    val RICE_SEEDS: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "rice_seeds",
        Supplier {
            BlockItem(
                ModBlocks.RICE_CROP.get(), Item.Properties().useItemDescriptionPrefix()
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "rice_seeds")
                        )
                    )
            )
        })

    val RICE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "rice",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "rice")
                    )
                )
                    .food(ModFoodProperties.RICE).usingConvertsTo(Items.BOWL)
            )
        })

    val RAW_RICE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "raw_rice",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_rice")
                    )
                )
            )
        })

    val YAMAZAKI_BERRIES: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "yamazaki_berries",
        Supplier {
            BlockItem(
                ModBlocks.YAMAZAKI_BERRY_BUSH.get(), Item.Properties().useItemDescriptionPrefix()
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "yamazaki_berries")
                        )
                    )
                    .food(ModFoodProperties.YAMAZAKI_BERRIES)
            )
        })

    val SAMURAI_HELMET: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "samurai_helmet",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_helmet")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.HELMET)
            )
        })

    val SAMURAI_CHESTPLATE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "samurai_chestplate",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_chestplate")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.CHESTPLATE)
            )
        })

    val SAMURAI_LEGGINGS: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "samurai_leggings",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_leggings")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.LEGGINGS)
            )
        })

    val SAMURAI_BOOTS: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "samurai_boots",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "samurai_boots")
                    )
                )
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.BOOTS)
            )
        })

    val TRICERATOPS_SPAWN_EGG: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "triceratops_spawn_egg",
        Supplier {
            SpawnEggItem(
                ModEntities.TRICERATOPS!!.get(),
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "triceratops_spawn_egg")
                    )
                )
            )
        })

    val SIKA_DEER_SPAWN_EGG: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "sika_deer_spawn_egg",
        Supplier {
            SpawnEggItem(
                ModEntities.SIKA_DEER!!.get(),
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "sika_deer_spawn_egg")
                    )
                )
            )
        })

    val PYRITE_BATTLE_AXE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "pyrite_battle_axe",
        Supplier {
            MaceItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_battle_axe")
                    )
                )
            )
        })

    val IRON_BATTLE_AXE: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "iron_battle_axe",
        Supplier {
            IronBattleAxeItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "iron_battle_axe")
                    )
                )
                    .stacksTo(16)
            )
        })

    val RADIATION_STAFF: RegistryObject<Item?>? = ITEMS.register<Item?>(
        "radiation_staff",
        Supplier {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "radiation_staff")
                    )
                )
                    .stacksTo(1)
            )
        })

    fun register(eventBus: BusGroup?) {
        ITEMS.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE ITEM IN MODITEMS")
    }
}
