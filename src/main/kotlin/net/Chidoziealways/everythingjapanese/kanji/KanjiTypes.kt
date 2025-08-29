package net.Chidoziealways.everythingjapanese.kanji

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

object KanjiTypes {
    val FIRE: ResourceKey<KanjiType> = register("fire")
    val WATER: ResourceKey<KanjiType> = register("water")
    val SEAL: ResourceKey<KanjiType> = register("seal")
    val LIGHTNING: ResourceKey<KanjiType> = register("lightning")

    fun register(name: String): ResourceKey<KanjiType> {
        return ResourceKey.create(ModRegistries.KANJI, ResourceLocation.fromNamespaceAndPath(MOD_ID, name))
    }

    fun bootstrap(context: BootstrapContext<KanjiType>) {
        context.register(
            FIRE,
            KanjiType(
                "fire",
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "fire"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "fire"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/talisman_fire_overlay")
            )
        )
        context.register(
            WATER,
            KanjiType(
                "water",
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "water"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "water"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/talisman_water_overlay")
            )
        )
        context.register(
            SEAL,
            KanjiType(
                "seal",
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "seal"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "seal"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/talisman_seal_overlay")
            )
        )
        context.register(
            LIGHTNING,
            KanjiType(
                "lightning",
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "lightning"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "lightning"),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/talisman_lightning_overlay")
            )
        )
    }
}