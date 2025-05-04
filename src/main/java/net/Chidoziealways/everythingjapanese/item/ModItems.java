package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.entity.ModEntities;
import net.Chidoziealways.everythingjapanese.item.custom.*;
import net.Chidoziealways.everythingjapanese.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo;

public class ModItems {

    private static final Logger log = LoggerFactory.getLogger(ModItems.class);

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EverythingJapanese.MOD_ID);

    public static final RegistryObject<Item> PYRITE_INGOT = ITEMS.register("pyrite_ingot",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_ingot")))));

    public static final RegistryObject<Item> SMALL_FIREBALL_SCROLL = ITEMS.register("small_fireball_scroll",
             () -> new JutsuScrollItem("small_fireball", new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "small_fireball_scroll")))));

    public static final RegistryObject<Item> LARGE_FIREBALL_SCROLL = ITEMS.register("large_fireball_scroll",
            () -> new JutsuScrollItem("large_fireball", new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "large_fireball_scroll")))));

    public static final RegistryObject<Item> WINDBALL_SCROLL = ITEMS.register("windball_scroll",
            () -> new JutsuScrollItem("small_windball", new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "windball_scroll")))));

    public static final RegistryObject<Item> NEPHRITE = ITEMS.register("nephrite",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite")))));

    public static final RegistryObject<Item> RAW_PYRITE = ITEMS.register("raw_pyrite",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "raw_pyrite")))));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "chisel")))
                    .durability(100).stacksTo(1)));

    public static final RegistryObject<Item> PYRITE_SWORD = ITEMS.register("pyrite_sword",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_sword")))
                    .sword(ModToolMaterials.PYRITE, 3, -2.4f)
                    .component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(0.25F,
                            1.0F,
                            List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 0.5F)),
                            new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                            Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                            Optional.of(SoundEvents.SHIELD_BLOCK),
                            Optional.of(SoundEvents.SHIELD_BREAK)))
            ));

    public static final RegistryObject<Item> PYRITE_PICKAXE = ITEMS.register("pyrite_pickaxe",
            () -> new Item(new Item.Properties()
                    .pickaxe(ModToolMaterials.PYRITE, 1, -2.8f)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_pickaxe"))
     )));

    public static final RegistryObject<Item> PYRITE_SHOVEL = ITEMS.register("pyrite_shovel",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_shovel")))
                    .shovel(ModToolMaterials.PYRITE, 1.5f, -3.0f)
                    ));

    public static final RegistryObject<Item> PYRITE_AXE = ITEMS.register("pyrite_axe",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_axe")))
                    .axe(ModToolMaterials.PYRITE, 6, -3.2f)
                    ));

    public static final RegistryObject<Item> PYRITE_HOE = ITEMS.register("pyrite_hoe",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_hoe")))
                    .hoe(ModToolMaterials.PYRITE, 0, -3.0f)
                    ));

    public static final RegistryObject<Item> SUSHI = ITEMS.register("sushi",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "sushi")))
                    .food(ModFoodProperties.SUSHI, ModFoodProperties.SUSHI_EFFECT).usingConvertsTo(Items.BOWL)));

    public static final RegistryObject<Item> GREEN_TEA = ITEMS.register("green_tea",
            () -> new Drinks(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "green_tea")))
                    .stacksTo(1)));

    public static final RegistryObject<Item> DIESEL = ITEMS.register("diesel",
            () -> new FuelItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "diesel")))
                    .stacksTo(1), 1200));

    public static final RegistryObject<Item> UDON = ITEMS.register("udon",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "udon")))
                    .food(ModFoodProperties.UDON, ModFoodProperties.UDON_EFFECT).usingConvertsTo(Items.BOWL).stacksTo(1)));

    public static final RegistryObject<Item> INCENSE = ITEMS.register("incense",
            () -> new FuelItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "incense")))
                    .stacksTo(1), 20000));

    public static final RegistryObject<Item> YA = ITEMS.register("ya",
            () -> new ArrowItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "ya")))));
    
    public static final RegistryObject<Item> WOODEN_KATANA = ITEMS.register("wooden_katana",
            () -> new Katana(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "wooden_katana")))
                    .sword(ToolMaterial.WOOD, 3.0f, -2.4f), new MobEffects()));

    public static final RegistryObject<Item> NEPHRITE_SWORD = ITEMS.register("nephrite_sword",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_sword")))
                    .sword(ModToolMaterials.NEPHRITE, 7, -1.9f)
                    .component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(0.1F,
                            1.4F,
                            List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 0.5F)),
                            new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                            Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                            Optional.of(SoundEvents.SHIELD_BLOCK),
                            Optional.of(SoundEvents.SHIELD_BREAK)))
            ));

    public static final RegistryObject<Item> NEPHRITE_PICKAXE = ITEMS.register("nephrite_pickaxe",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_pickaxe")))
                    .pickaxe(ModToolMaterials.NEPHRITE, 5, -2.5f)
            ));

    public static final RegistryObject<Item> NEPHRITE_SHOVEL = ITEMS.register("nephrite_shovel",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_shovel")))
                    .shovel(ModToolMaterials.NEPHRITE, 4.0f, -2.0f)
            ));

    public static final RegistryObject<Item> NEPHRITE_AXE = ITEMS.register("nephrite_axe",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_axe")))
                    .axe(ModToolMaterials.NEPHRITE, 11, -2.9f)
            ));

    public static final RegistryObject<Item> NEPHRITE_HOE = ITEMS.register("nephrite_hoe",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_hoe")))
                    .hoe(ModToolMaterials.NEPHRITE, 1, -3.0f)
            ));

    public static final RegistryObject<Item> PYRITE_HAMMER = ITEMS.register("pyrite_hammer",
            () -> new HammerItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_hammer")))
                    .pickaxe(ModToolMaterials.PYRITE, 7, -2.5f)
            ));

    public static final RegistryObject<Item> PYRITE_HELMET = ITEMS.register("pyrite_helmet",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_helmet")))
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.HELMET)
            ));

    public static final RegistryObject<Item> PYRITE_CHESTPLATE = ITEMS.register("pyrite_chestplate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_chestplate")))
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.CHESTPLATE)
            ));

    public static final RegistryObject<Item> PYRITE_LEGGINGS = ITEMS.register("pyrite_leggings",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_leggings")))
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.LEGGINGS)
            ));

    public static final RegistryObject<Item> PYRITE_BOOTS = ITEMS.register("pyrite_boots",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_boots")))
                    .humanoidArmor(ModArmorMaterials.PYRITE, ArmorType.BOOTS)
            ));

    public static final RegistryObject<Item> NEPHRITE_HELMET = ITEMS.register("nephrite_helmet",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_helmet")))
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.HELMET)
            ));

    public static final RegistryObject<Item> NEPHRITE_CHESTPLATE = ITEMS.register("nephrite_chestplate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_chestplate")))
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.CHESTPLATE)
            ));

    public static final RegistryObject<Item> NEPHRITE_LEGGINGS = ITEMS.register("nephrite_leggings",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_leggings")))
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.LEGGINGS)
            ));

    public static final RegistryObject<Item> NEPHRITE_BOOTS = ITEMS.register("nephrite_boots",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "nephrite_boots")))
                    .humanoidArmor(ModArmorMaterials.NEPHRITE, ArmorType.BOOTS)
            ));

    public static final RegistryObject<Item> PYRITE_HORSE_ARMOR = ITEMS.register("pyrite_horse_armor",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_horse_armor")))
                    .horseArmor(ModArmorMaterials.PYRITE)
            ));

    public static final RegistryObject<Item> KOI_FISH_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("koi_fish_armor_trim_smithing_template",
            () -> SmithingTemplateItem.createArmorTrimTemplate(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "koi_fish_armor_trim_smithing_template")))
                    .rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> DAIKYU = ITEMS.register("daikyu",
            () -> new BowItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "daikyu")))
                    .durability(600)));

    public static final RegistryObject<Item> AO_TO_NATSU_MUSIC_DISC = ITEMS.register("ao_to_natsu_music_disc",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "ao_to_natsu_music_disc")))
                    .jukeboxPlayable(ModSounds.AO_TO_NATSU_KEY).stacksTo(1)));

    public static final RegistryObject<Item> RICE_SEEDS = ITEMS.register("rice_seeds",
            () -> new BlockItem(ModBlocks.RICE_CROP.get(), new Item.Properties().useItemDescriptionPrefix()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "rice_seeds")))));

    public static final RegistryObject<Item> RICE = ITEMS.register("rice",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "rice")))
                    .food(ModFoodProperties.RICE).usingConvertsTo(Items.BOWL)));

    public static final RegistryObject<Item> RAW_RICE = ITEMS.register("raw_rice",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "raw_rice")))));

    public static final RegistryObject<Item> YAMAZAKI_BERRIES = ITEMS.register("yamazaki_berries",
            () -> new BlockItem(ModBlocks.YAMAZAKI_BERRY_BUSH.get(), new Item.Properties().useItemDescriptionPrefix()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "yamazaki_berries")))
                    .food(ModFoodProperties.YAMAZAKI_BERRIES)));

    public static final RegistryObject<Item> SAMURAI_HELMET = ITEMS.register("samurai_helmet",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "samurai_helmet")))
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.HELMET)));

    public static final RegistryObject<Item> SAMURAI_CHESTPLATE = ITEMS.register("samurai_chestplate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "samurai_chestplate")))
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));

    public static final RegistryObject<Item> SAMURAI_LEGGINGS = ITEMS.register("samurai_leggings",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "samurai_leggings")))
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.LEGGINGS)));

    public static final RegistryObject<Item> SAMURAI_BOOTS = ITEMS.register("samurai_boots",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "samurai_boots")))
                    .humanoidArmor(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> TRICERATOPS_SPAWN_EGG = ITEMS.register("triceratops_spawn_egg",
            () -> new SpawnEggItem(ModEntities.TRICERATOPS.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "triceratops_spawn_egg")))));

    public static final RegistryObject<Item> SIKA_DEER_SPAWN_EGG = ITEMS.register("sika_deer_spawn_egg",
            () -> new SpawnEggItem(ModEntities.SIKA_DEER.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "sika_deer_spawn_egg")))));

    public static final RegistryObject<Item> PYRITE_BATTLE_AXE = ITEMS.register("pyrite_battle_axe",
            () -> new MaceItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "pyrite_battle_axe")))));

    public static final RegistryObject<Item> IRON_BATTLE_AXE = ITEMS.register("iron_battle_axe",
            () -> new IronBattleAxeItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "iron_battle_axe")))
                    .stacksTo(16)));
    
    public static final RegistryObject<Item> RADIATION_STAFF = ITEMS.register("radiation_staff",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "radiation_staff")))
                    .stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        logInfo("REGISTERING EVERY SINGLE ITEM IN MODITEMS");
    }
}
