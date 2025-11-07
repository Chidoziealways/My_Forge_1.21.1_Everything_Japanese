package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers
import net.Chidoziealways.everythingjapanese.entity.client.bullet.BulletModel
import net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe.IronBattleAxeProjectileModel
import net.Chidoziealways.everythingjapanese.entity.client.sikadeer.SikaDeerModel
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsModel
import net.Chidoziealways.everythingjapanese.entity.custom.CursedSamurai
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity
import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.SpawnPlacementTypes
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.levelgen.Heightmap
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import thedarkcolour.kotlinforforge.common.KotlinMod
import java.util.function.Supplier

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object ModEventBusEvents {
    @SubscribeEvent
    fun registerLayers(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        val sikaDeerLayer: LayerDefinition = SikaDeerModel.createBodyLayer()
        val triceratopsLayer: LayerDefinition = TriceratopsModel.createBodyLayer()
        val bulletLayer: LayerDefinition = BulletModel.createBodyLayer()

        event.registerLayerDefinition(ModModelLayers.SIKA_DEER, Supplier { sikaDeerLayer })
        event.registerLayerDefinition(
            ModModelLayers.SIKA_DEER_BABY,
            Supplier { sikaDeerLayer.apply(SikaDeerModel.Companion.BABY_TRANSFORMER) })
        event.registerLayerDefinition(ModModelLayers.TRICERATOPS, Supplier { triceratopsLayer })
        event.registerLayerDefinition(
            ModModelLayers.TRICERATOPS_BABY,
            Supplier { triceratopsLayer.apply(TriceratopsModel.Companion.BABY_TRANSFORMER) })
        event.registerLayerDefinition(
            IronBattleAxeProjectileModel.Companion.LAYER_LOCATION,
            Supplier { IronBattleAxeProjectileModel.Companion.createBodyLayer() })
        event.registerLayerDefinition(
            ModModelLayers.BULLET) { bulletLayer }
    }

    
    @SubscribeEvent
    fun registerAttribute(event: EntityAttributeCreationEvent) {
        event.put(ModEntities.TRICERATOPS, TriceratopsEntity.Companion.createAttributes().build())
        event.put(ModEntities.SIKA_DEER, SikaDeerEntity.Companion.createAttributes().build())
        event.put(ModEntities.CURSED_SAMURAI, CursedSamurai.createAttributes().build())
    }

    
    @SubscribeEvent
    fun registerSpawnPlacements(event: RegisterSpawnPlacementsEvent) {
        event.register<TriceratopsEntity>(
            ModEntities.TRICERATOPS,
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            { p_218105_: EntityType<TriceratopsEntity?>?, p_218106_: ServerLevelAccessor?, p_367954_: EntitySpawnReason?, p_218108_: BlockPos?, p_218109_: RandomSource? ->
                Animal.checkAnimalSpawnRules(
                    p_218105_,
                    p_218106_,
                    p_367954_,
                    p_218108_,
                    p_218109_
                )
            },
            RegisterSpawnPlacementsEvent.Operation.REPLACE
        )
        event.register(
            ModEntities.CURSED_SAMURAI,
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            { entityType, level, spawnReason, pos, random ->
                Monster.checkMonsterSpawnRules(
                    entityType,
                    level,
                    spawnReason,
                    pos,
                    random
                )
            },
            RegisterSpawnPlacementsEvent.Operation.REPLACE
            )
        event.register<SikaDeerEntity>(
            ModEntities.SIKA_DEER,
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            { p_218105_: EntityType<SikaDeerEntity?>?, p_218106_: ServerLevelAccessor?, p_367954_: EntitySpawnReason?, p_218108_: BlockPos?, p_218109_: RandomSource? ->
                Animal.checkAnimalSpawnRules(
                    p_218105_,
                    p_218106_,
                    p_367954_,
                    p_218108_,
                    p_218109_
                )
            },
            RegisterSpawnPlacementsEvent.Operation.REPLACE
        )
    }
}
