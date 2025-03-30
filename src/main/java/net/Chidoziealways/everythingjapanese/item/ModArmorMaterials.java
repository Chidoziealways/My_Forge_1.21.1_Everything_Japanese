package net.Chidoziealways.everythingjapanese.item;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> PYRITE_ARMOR_MATERIAL = register("pyrite", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
                   attribute.put(ArmorItem.Type.BOOTS, 1);
                   attribute.put(ArmorItem.Type.LEGGINGS, 3);
                   attribute.put(ArmorItem.Type.CHESTPLATE, 5);
                   attribute.put(ArmorItem.Type.HELMET, 2);
                   attribute.put(ArmorItem.Type.BODY, 7);
            }), 15, 10f, SoundEvents.ARMOR_EQUIP_GOLD,0.1f, () -> ModItems.PYRITE_INGOT.get());

    public static final Holder<ArmorMaterial> NEPHRITE_ARMOR_MATERIAL = register("nephrite", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 5);
                attribute.put(ArmorItem.Type.LEGGINGS, 6);
                attribute.put(ArmorItem.Type.CHESTPLATE, 8);
                attribute.put(ArmorItem.Type.HELMET, 7);
                attribute.put(ArmorItem.Type.BODY, 13);
            }), 20, 50f, SoundEvents.ARMOR_EQUIP_DIAMOND,0.1f, () -> ModItems.NEPHRITE.get());

    public static final Holder<ArmorMaterial> SAMURAI_ARMOR_MATERIAL = register("samurai", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 5);
                attribute.put(ArmorItem.Type.LEGGINGS, 7);
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);
                attribute.put(ArmorItem.Type.HELMET, 5);
                attribute.put(ArmorItem.Type.BODY, 11);
            }), 15, 4f, SoundEvents.ARMOR_EQUIP_IRON,0.1f, () -> Items.IRON_INGOT);

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, Holder<SoundEvent> soundEvent,float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, name);
        Holder<SoundEvent> equipSound = soundEvent;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for(ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}