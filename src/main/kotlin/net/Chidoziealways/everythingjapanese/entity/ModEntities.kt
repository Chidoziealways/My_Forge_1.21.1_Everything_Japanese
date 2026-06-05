package net.Chidoziealways.everythingjapanese.entity

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.*
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.Level
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import net.Chidoziealways.everythingjapanese.entity.custom.ChiretsuShōProjectileEntity
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModEntities {
    val ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, JAPANESE_MOD_ID)
    
    val TRICERATOPS by ENTITY_TYPES.register("triceratops") { ->
            EntityType.Builder.of<TriceratopsEntity>({ pEntityType: EntityType<TriceratopsEntity>, pLevel: Level ->
                TriceratopsEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.CREATURE)
                .sized(1.5f, 1.5f).build(
                    ResourceKey.create(
                        Registries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "triceratops")
                    )
                )
        }

    val ASPIRATION by ENTITY_TYPES.register("aspiration") { ->
        EntityType.Builder.of({ _, level ->
            AspirationEntity(
                level
            )
        }, MobCategory.AMBIENT)
            .sized(0.5f, 0.5f).build(
                ResourceKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "aspiration")
                )
            )
    }

    val CURSED_SAMURAI by ENTITY_TYPES.register("cursed_samurai") { ->
        EntityType.Builder.of<CursedSamurai>( { entityType, level ->
            CursedSamurai(
                level
            )
        }, MobCategory.MONSTER )
            .sized(1f, 2f).build(
                ResourceKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "cursed_samurai")
                )
            )
    }

    val BULLET by ENTITY_TYPES.register("bullet") { ->
        EntityType.Builder.of({ _, level ->
            BulletEntity(
                level
            )
        }, MobCategory.MISC)
            .sized(1f, 2f).build(
                ResourceKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "bullet")
                )
            )
    }

    val SIKA_DEER by ENTITY_TYPES.register("sika_deer") { ->
            EntityType.Builder.of<SikaDeerEntity>({ pEntityType: EntityType<SikaDeerEntity>, pLevel: Level ->
                SikaDeerEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.CREATURE)
                .sized(3.0f, 10.0f).build(
                    ResourceKey.create(
                        Registries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "sika_deer")
                    )
                )
        }

    val IRON_BATTLE_AXE by ENTITY_TYPES.register("iron_battle_axe") { ->
            EntityType.Builder.of({ pEntityType: EntityType<IronBattleAxeProjectileEntity>, pLevel: Level ->
                IronBattleAxeProjectileEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.MISC)
                .sized(0.5f, 1.15f).build(
                    ResourceKey.create(
                        Registries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "iron_battle_axe")
                    )
                )
        }

    val CHIRETSU_SHO_PROJECTILE by ENTITY_TYPES.register("chiretsu_sho_projectile") { ->
        EntityType.Builder.of({ pEntityType: EntityType<ChiretsuShōProjectileEntity>, pLevel: Level ->
            ChiretsuShōProjectileEntity(
                pLevel
            )
        }, MobCategory.MISC)
            .sized(1f, 1f).build(
                ResourceKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "chiretsu_sho_projectile")
                )
            )
    }

    val EKIRETSU_SHO_PROJECTILE by ENTITY_TYPES.register("ekiretsu_sho_projectile") { ->
        EntityType.Builder.of({ _, level ->
            EkiretsuShōProjectileEntity(
                level
            )
        }, MobCategory.MISC)
            .sized(1f, 1f).build (
                ResourceKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "ekiretsu_sho_projectile")
                )
            )
    }

    val YA by ENTITY_TYPES.register("ya") { ->
            EntityType.Builder.of<YaProjectileEntity>({ entityType: EntityType<YaProjectileEntity>, level: Level ->
                YaProjectileEntity(
                    entityType,
                    level
                )
            }, MobCategory.MISC)
                .sized(1.15f, 1f).build(
                    ResourceKey.create(
                        Registries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "ya")
                    )
                )
        }

    val CHAIR by ENTITY_TYPES.register("chair_entity") { ->
            EntityType.Builder.of<ChairEntity>({ pEntityType: EntityType<ChairEntity>, pLevel: Level ->
                ChairEntity(
                    pEntityType,
                    pLevel
                )
            }, MobCategory.MISC)
                .sized(0.5f, 0.5f).build(
                    ResourceKey.create(
                        Registries.ENTITY_TYPE,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "chair_entity")
                    )
                )
        }


    fun register(eventBus: IEventBus) {
        ENTITY_TYPES.register(eventBus)
    }
}
