package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.entity.ModEntities;
import net.Chidoziealways.everythingjapanese.item.custom.*;
import net.Chidoziealways.everythingjapanese.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EverythingJapanese.MOD_ID);

    public static final RegistryObject<Item> PYRITE_INGOT = ITEMS.register("pyrite_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_PYRITE = ITEMS.register("raw_pyrite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(100).stacksTo(1)));

    public static final RegistryObject<Item> PYRITE_SWORD = ITEMS.register("pyrite_sword",
            () -> new SwordItem(ModToolTiers.PYRITE,  new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.PYRITE, 3, -2.4f))));

    public static final RegistryObject<Item> PYRITE_PICKAXE = ITEMS.register("pyrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.PYRITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.PYRITE, 1, -2.8f))));

    public static final RegistryObject<Item> PYRITE_SHOVEL = ITEMS.register("pyrite_shovel",
            () -> new ShovelItem(ModToolTiers.PYRITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.PYRITE, 1.5f, -3.0f))));

    public static final RegistryObject<Item> PYRITE_AXE = ITEMS.register("pyrite_axe",
            () -> new AxeItem(ModToolTiers.PYRITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.PYRITE, 6, -3.2f))));

    public static final RegistryObject<Item> PYRITE_HOE = ITEMS.register("pyrite_hoe",
            () -> new HoeItem(ModToolTiers.PYRITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.PYRITE, 0, -3.0f))));

    public static final RegistryObject<Item> SUSHI = ITEMS.register("sushi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SUSHI)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                   pTooltipComponents.add(Component.translatable("tooltip.everythingjapanese.sushi"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> GREEN_TEA = ITEMS.register("green_tea",
            () -> new Drinks(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DIESEL = ITEMS.register("diesel",
            () -> new FuelItem(new Item.Properties().stacksTo(1), 1200));

    public static final RegistryObject<Item> UDON = ITEMS.register("udon",
            () -> new Item(new Item.Properties().food(ModFoodProperties.UDON).stacksTo(1)));

    public static final RegistryObject<Item> INCENSE = ITEMS.register("incense",
            () -> new FuelItem(new Item.Properties().stacksTo(1), 20000));

    public static final RegistryObject<Item> YA = ITEMS.register("ya",
            () -> new ArrowItem(new Item.Properties()));
    
    public static final RegistryObject<Item> WOODEN_KATANA = ITEMS.register("wooden_katana",
            () -> new Katana(Tiers.WOOD, new Item.Properties().durability(1000).stacksTo(1), new MobEffects()));

    public static final RegistryObject<Item> NEPHRITE = ITEMS.register("nephrite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NEPHRITE_SWORD = ITEMS.register("nephrite_sword",
            () -> new SwordItem(ModToolTiers.NEPHRITE,  new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.NEPHRITE, 7, -5.0f))));

    public static final RegistryObject<Item> NEPHRITE_PICKAXE = ITEMS.register("nephrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NEPHRITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.NEPHRITE, 5, -4.5f))));

    public static final RegistryObject<Item> NEPHRITE_SHOVEL = ITEMS.register("nephrite_shovel",
            () -> new ShovelItem(ModToolTiers.NEPHRITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.NEPHRITE, 4.0f, -4.0f))));

    public static final RegistryObject<Item> NEPHRITE_AXE = ITEMS.register("nephrite_axe",
            () -> new AxeItem(ModToolTiers.NEPHRITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.NEPHRITE, 11, -7.0f))));

    public static final RegistryObject<Item> NEPHRITE_HOE = ITEMS.register("nephrite_hoe",
            () -> new HoeItem(ModToolTiers.NEPHRITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.NEPHRITE, 1, -2.0f))));

    public static final RegistryObject<Item> PYRITE_HAMMER = ITEMS.register("pyrite_hammer",
            () -> new HammerItem(ModToolTiers.PYRITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.PYRITE, 7, -3.5f))));

    public static final RegistryObject<Item> PYRITE_HELMET = ITEMS.register("pyrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.PYRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));

    public static final RegistryObject<Item> PYRITE_CHESTPLATE = ITEMS.register("pyrite_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.PYRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))));

    public static final RegistryObject<Item> PYRITE_LEGGINGS = ITEMS.register("pyrite_leggings",
            () -> new ModArmorItem(ModArmorMaterials.PYRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))));

    public static final RegistryObject<Item> PYRITE_BOOTS = ITEMS.register("pyrite_boots",
            () -> new ModArmorItem(ModArmorMaterials.PYRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))));

    public static final RegistryObject<Item> NEPHRITE_HELMET = ITEMS.register("nephrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.NEPHRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(50))));

    public static final RegistryObject<Item> NEPHRITE_CHESTPLATE = ITEMS.register("nephrite_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.NEPHRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(50))));

    public static final RegistryObject<Item> NEPHRITE_LEGGINGS = ITEMS.register("nephrite_leggings",
            () -> new ModArmorItem(ModArmorMaterials.NEPHRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(50))));

    public static final RegistryObject<Item> NEPHRITE_BOOTS = ITEMS.register("nephrite_boots",
            () -> new ModArmorItem(ModArmorMaterials.NEPHRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(50))));

    public static final RegistryObject<Item> PYRITE_HORSE_ARMOR = ITEMS.register("pyrite_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.PYRITE_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> KOI_FISH_SMITHING_TEMPLATE = ITEMS.register("koi_fish_armor_trim_smithing_template",
            () -> SmithingTemplateItem.createArmorTrimTemplate(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "lion")));

    public static final RegistryObject<Item> DAIKYU = ITEMS.register("daikyu",
            () -> new BowItem(new Item.Properties().durability(600)));

    public static final RegistryObject<Item> AO_TO_NATSU_MUSIC_DISC = ITEMS.register("ao_to_natsu_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.AO_TO_NATSU_KEY).stacksTo(1)));

    public static final RegistryObject<Item> RICE_SEEDS = ITEMS.register("rice_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RICE_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> RICE = ITEMS.register("rice",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RICE)));

    public static final RegistryObject<Item> RAW_RICE = ITEMS.register("raw_rice",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> YAMAZAKI_BERRIES = ITEMS.register("yamazaki_berries",
            () -> new ItemNameBlockItem(ModBlocks.YAMAZAKI_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.YAMAZAKI_BERRIES)));

    public static final RegistryObject<Item> SAMURAI_HELMET = ITEMS.register("samurai_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(2))));

    public static final RegistryObject<Item> SAMURAI_CHESTPLATE = ITEMS.register("samurai_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(2))));

    public static final RegistryObject<Item> SAMURAI_LEGGINGS = ITEMS.register("samurai_leggings",
            () -> new ModArmorItem(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(2))));

    public static final RegistryObject<Item> SAMURAI_BOOTS = ITEMS.register("samurai_boots",
            () -> new ModArmorItem(ModArmorMaterials.SAMURAI_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(2))));

    public static final RegistryObject<Item> TRICERATOPS_SPAWN_EGG = ITEMS.register("triceratops_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TRICERATOPS, 0x53524b, 0xdac741, new Item.Properties()));

    public static final RegistryObject<Item> SIKA_DEER_SPAWN_EGG = ITEMS.register("sika_deer_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SIKA_DEER, 0x8b4513, 0x8b0000, new Item.Properties()));

    public static final RegistryObject<Item> PYRITE_BATTLE_AXE = ITEMS.register("pyrite_battle_axe",
            () -> new MaceItem(new Item.Properties()));

    public static final RegistryObject<Item> IRON_BATTLE_AXE = ITEMS.register("iron_battle_axe",
            () -> new IronBattleAxeItem(new Item.Properties().stacksTo(16)) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    Component.literal("Richt Click to Throw this Item!");
                }
            });
    
    public static final RegistryObject<Item> RADIATION_STAFF = ITEMS.register("radiation_staff",
            () -> new Item(new Item.Properties().stacksTo(1)) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    Component.literal("This Item does literally NOTHING");
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        logInfo("REGISTERING EVERY SINGLE ITEM IN MODITEMS");
    }
}
