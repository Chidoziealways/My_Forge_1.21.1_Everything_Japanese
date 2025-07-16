package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers
import net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe.IronBattleAxeProjectileModel
import net.Chidoziealways.everythingjapanese.entity.client.sikadeer.SikaDeerModel
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsModel
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity
import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.SpawnPlacementTypes
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod
import java.util.function.Supplier

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.MOD)
object ModEventBusEvents {
    @JvmStatic
    @SubscribeEvent
    fun registerLayers(event: RegisterLayerDefinitions) {
        val sikaDeerLayer: LayerDefinition = SikaDeerModel.Companion.createBodyLayer()
        val triceratopsLayer: LayerDefinition = TriceratopsModel.Companion.createBodyLayer()

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
    }

    @JvmStatic
    @SubscribeEvent
    fun registerAttribute(event: EntityAttributeCreationEvent) {
        event.put(ModEntities.TRICERATOPS!!.get(), TriceratopsEntity.Companion.createAttributes().build())
        event.put(ModEntities.SIKA_DEER!!.get(), SikaDeerEntity.Companion.createAttributes().build())
    }

    @JvmStatic
    @SubscribeEvent
    fun registerSpawnPlacements(event: SpawnPlacementRegisterEvent) {
        event.register<TriceratopsEntity?>(
            ModEntities.TRICERATOPS!!.get(),
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            SpawnPlacements.SpawnPredicate { p_218105_: EntityType<TriceratopsEntity?>?, p_218106_: ServerLevelAccessor?, p_367954_: EntitySpawnReason?, p_218108_: BlockPos?, p_218109_: RandomSource? ->
                Animal.checkAnimalSpawnRules(
                    p_218105_,
                    p_218106_,
                    p_367954_,
                    p_218108_,
                    p_218109_
                )
            },
            SpawnPlacementRegisterEvent.Operation.REPLACE
        )
        event.register<SikaDeerEntity?>(
            ModEntities.SIKA_DEER!!.get(),
            SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            SpawnPlacements.SpawnPredicate { p_218105_: EntityType<SikaDeerEntity?>?, p_218106_: ServerLevelAccessor?, p_367954_: EntitySpawnReason?, p_218108_: BlockPos?, p_218109_: RandomSource? ->
                Animal.checkAnimalSpawnRules(
                    p_218105_,
                    p_218106_,
                    p_367954_,
                    p_218108_,
                    p_218109_
                )
            },
            SpawnPlacementRegisterEvent.Operation.REPLACE
        )
    }
}
