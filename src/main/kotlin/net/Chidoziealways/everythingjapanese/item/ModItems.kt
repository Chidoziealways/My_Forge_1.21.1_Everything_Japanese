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
import thedarkcolour.kotlinforforge.forge.registerObject
import java.util.*
import java.util.List
import java.util.function.Supplier

object ModItems {
    private val log: Logger? = LoggerFactory.getLogger(ModItems::class.java)

    val ITEMS: DeferredRegister<Item?> =
        DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)

    val PYRITE_INGOT = ITEMS.registerObject(
        "pyrite_ingot")
        {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_ingot")
                    )
                )
            )
        }

    val SMALL_FIREBALL_SCROLL = ITEMS.registerObject(
        "small_fireball_scroll")
        {
            JutsuScrollItem(
                "small_fireball",
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_fireball_scroll")
                    )
                )
            )
        }

    val LARGE_FIREBALL_SCROLL = ITEMS.registerObject(
        "large_fireball_scroll")
        {
            JutsuScrollItem(
                "large_fireball",
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "large_fireball_scroll")
                    )
                )
            )
        }

    val WINDBALL_SCROLL = ITEMS.registerObject(
        "windball_scroll")
        {
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

    val HELL_PORTAL_ACTIVATOR = ITEMS.registerObject(
        "hell_portal_activator")
        {
            HellPortalItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell_portal_activator")
                    )
                )
            )
        }

    val NEPHRITE = ITEMS.registerObject(
        "nephrite")
        {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite")
                    )
                )
            )
        }

    val RAW_PYRITE = ITEMS.registerObject(
        "raw_pyrite")
        {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_pyrite")
                    )
                )
            )
        }

    val CHISEL = ITEMS.registerObject(
        "chisel")
        {
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

    val PYRITE_SWORD = ITEMS.registerObject(
        "pyrite_sword")
        {
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

    val PYRITE_PICKAXE = ITEMS.registerObject(
        "pyrite_pickaxe")
        {
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

    val PYRITE_SHOVEL = ITEMS.registerObject(
        "pyrite_shovel")
        {
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

    val PYRITE_AXE = ITEMS.registerObject(
        "pyrite_axe")
        {
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

    val PYRITE_HOE = ITEMS.registerObject(
        "pyrite_hoe")
        {
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

    val SUSHI = ITEMS.registerObject(
        "sushi")
        {
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

    val GREEN_TEA = ITEMS.registerObject(
        "green_tea")
        {
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

    val DIESEL = ITEMS.registerObject(
        "diesel")
        {
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

    val UDON = ITEMS.registerObject(
        "udon")
        {
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

    val INCENSE = ITEMS.registerObject(
        "incense")
        {
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

    val YA = ITEMS.registerObject(
        "ya")
        {
            ArrowItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "ya")
                    )
                )
            )
        }

    val WOODEN_KATANA = ITEMS.registerObject(
        "wooden_katana")
        {
            Katana(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "wooden_katana")
                    )
                )
                    .sword(ToolMaterial.WOOD, 3.0f, -2.4f), MobEffects()
            )
        }

    val NEPHRITE_SWORD = ITEMS.registerObject(
        "nephrite_sword")
        {
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

    val NEPHRITE_PICKAXE = ITEMS.registerObject(
        "nephrite_pickaxe")
        {
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

    val NEPHRITE_SHOVEL = ITEMS.registerObject(
        "nephrite_shovel")
        {
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

    val NEPHRITE_AXE = ITEMS.registerObject(
        "nephrite_axe")
        {
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

    val NEPHRITE_HOE = ITEMS.registerObject(
        "nephrite_hoe")
        {
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

    val PYRITE_HAMMER = ITEMS.registerObject(
        "pyrite_hammer")
        {
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

    val PYRITE_HELMET = ITEMS.registerObject(
        "pyrite_helmet")
        {
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

    val PYRITE_CHESTPLATE = ITEMS.registerObject(
        "pyrite_chestplate")
        {
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

    val PYRITE_LEGGINGS = ITEMS.registerObject(
        "pyrite_leggings")
        {
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

    val PYRITE_BOOTS = ITEMS.registerObject(
        "pyrite_boots")
        {
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

    val NEPHRITE_HELMET = ITEMS.registerObject(
        "nephrite_helmet")
        {
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

    val NEPHRITE_CHESTPLATE = ITEMS.registerObject(
        "nephrite_chestplate")
        {
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

    val NEPHRITE_LEGGINGS = ITEMS.registerObject(
        "nephrite_leggings")
        {
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

    val NEPHRITE_BOOTS = ITEMS.registerObject(
        "nephrite_boots")
        {
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

    val PYRITE_HORSE_ARMOR = ITEMS.registerObject(
        "pyrite_horse_armor")
        {
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

    val KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.registerObject(
        "koi_fish_armor_trim_smithing_template")
        {
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
        }

    val DAIKYU = ITEMS.registerObject(
        "daikyu")
        {
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

    val AO_TO_NATSU_MUSIC_DISC = ITEMS.registerObject(
        "ao_to_natsu_music_disc")
        {
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

    val RICE_SEEDS = ITEMS.registerObject(
        "rice_seeds")
        {
            BlockItem(
                ModBlocks.RICE_CROP.get(), Item.Properties().useItemDescriptionPrefix()
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "rice_seeds")
                        )
                    )
            )
        }

    val RICE = ITEMS.registerObject(
        "rice")
        {
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

    val RAW_RICE = ITEMS.registerObject(
        "raw_rice")
        {
            Item(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_rice")
                    )
                )
            )
        }

    val YAMAZAKI_BERRIES = ITEMS.registerObject(
        "yamazaki_berries")
        {
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
        }

    val SAMURAI_HELMET = ITEMS.registerObject(
        "samurai_helmet")
        {
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

    val SAMURAI_CHESTPLATE = ITEMS.registerObject(
        "samurai_chestplate")
         {
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

    val SAMURAI_LEGGINGS = ITEMS.registerObject(
        "samurai_leggings")
        {
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

    val SAMURAI_BOOTS = ITEMS.registerObject(
        "samurai_boots")
        {
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

    val TRICERATOPS_SPAWN_EGG = ITEMS.registerObject(
        "triceratops_spawn_egg")
        {
            SpawnEggItem(
                ModEntities.TRICERATOPS!!.get(),
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "triceratops_spawn_egg")
                    )
                )
            )
        }

    val SIKA_DEER_SPAWN_EGG = ITEMS.registerObject(
        "sika_deer_spawn_egg")
        {
            SpawnEggItem(
                ModEntities.SIKA_DEER!!.get(),
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "sika_deer_spawn_egg")
                    )
                )
            )
        }

    val PYRITE_BATTLE_AXE = ITEMS.registerObject(
        "pyrite_battle_axe")
        {
            MaceItem(
                Item.Properties().setId(
                    ResourceKey.create<Item?>(
                        Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_battle_axe")
                    )
                )
            )
        }

    val IRON_BATTLE_AXE = ITEMS.registerObject(
        "iron_battle_axe")
        {
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

    val RADIATION_STAFF = ITEMS.registerObject(
        "radiation_staff")
        {
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

    fun register(eventBus: BusGroup?) {
        ITEMS.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE ITEM IN MODITEMS")
    }
}
