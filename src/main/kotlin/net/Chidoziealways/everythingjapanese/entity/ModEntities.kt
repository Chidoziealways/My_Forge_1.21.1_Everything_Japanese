package net.Chidoziealways.everythingjapanese.entity

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.*
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityType.EntityFactory
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.Level
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.registerObject
import java.util.function.Supplier

object ModEntities {
    val ENTITY_TYPES: DeferredRegister<EntityType<*>?> =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID)

    @JvmField
    val TRICERATOPS = ENTITY_TYPES.registerObject("triceratops") {
            EntityType.Builder.of<TriceratopsEntity?>(EntityFactory { pEntityType: EntityType<TriceratopsEntity?>, pLevel: Level ->
                TriceratopsEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.CREATURE)
                .sized(1.5f, 1.5f).build(
                    ResourceKey.create<EntityType<*>?>(
                        Registries.ENTITY_TYPE,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "triceratops")
                    )
                )
        }

    @JvmField
    val SIKA_DEER = ENTITY_TYPES.registerObject("sika_deer") {
            EntityType.Builder.of<SikaDeerEntity?>(EntityFactory { pEntityType: EntityType<SikaDeerEntity?>, pLevel: Level ->
                SikaDeerEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.CREATURE)
                .sized(3.0f, 10.0f).build(
                    ResourceKey.create<EntityType<*>?>(
                        Registries.ENTITY_TYPE,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "sika_deer")
                    )
                )
        }

    val IRON_BATTLE_AXE = ENTITY_TYPES.registerObject("iron_battle_axe") {
            EntityType.Builder.of<IronBattleAxeProjectileEntity?>(EntityFactory { pEntityType: EntityType<IronBattleAxeProjectileEntity?>, pLevel: Level ->
                IronBattleAxeProjectileEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.MISC)
                .sized(0.5f, 1.15f).build(
                    ResourceKey.create<EntityType<*>?>(
                        Registries.ENTITY_TYPE,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "iron_battle_axe")
                    )
                )
        }

    val YA = ENTITY_TYPES.registerObject("ya") {
            EntityType.Builder.of<YaProjectileEntity?>(EntityFactory { entityType: EntityType<YaProjectileEntity?>, level: Level ->
                YaProjectileEntity(
                    entityType,
                    level
                )
            }, MobCategory.MISC)
                .sized(1.15f, 1f).build(
                    ResourceKey.create<EntityType<*>?>(
                        Registries.ENTITY_TYPE,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "ya")
                    )
                )
        }

    val CHAIR = ENTITY_TYPES.registerObject("chair_entity") {
            EntityType.Builder.of<ChairEntity?>(EntityFactory { pEntityType: EntityType<ChairEntity?>, pLevel: Level ->
                ChairEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.MISC)
                .sized(0.5f, 0.5f).build(
                    ResourceKey.create<EntityType<*>?>(
                        Registries.ENTITY_TYPE,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "chair_entity")
                    )
                )
        }


    fun register(eventBus: BusGroup?) {
        ENTITY_TYPES.register(eventBus)
    }
}
