package net.Chidoziealways.everythingjapanese.event;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.entity.ModEntities;
import net.Chidoziealways.everythingjapanese.entity.client.ModModelLayers;
import net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe.IronBattleAxeProjectileModel;
import net.Chidoziealways.everythingjapanese.entity.client.sikadeer.SikaDeerModel;
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsModel;
import net.Chidoziealways.everythingjapanese.entity.custom.SikaDeerEntity;
import net.Chidoziealways.everythingjapanese.entity.custom.TriceratopsEntity;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        LayerDefinition sikaDeerLayer = SikaDeerModel.createBodyLayer();
        LayerDefinition triceratopsLayer = TriceratopsModel.createBodyLayer();

        event.registerLayerDefinition(ModModelLayers.SIKA_DEER, () -> sikaDeerLayer);
        event.registerLayerDefinition(ModModelLayers.SIKA_DEER_BABY, () -> sikaDeerLayer.apply(SikaDeerModel.BABY_TRANSFORMER));
        event.registerLayerDefinition(ModModelLayers.TRICERATOPS, () -> triceratopsLayer);
        event.registerLayerDefinition(ModModelLayers.TRICERATOPS_BABY, () -> triceratopsLayer.apply(TriceratopsModel.BABY_TRANSFORMER));
        event.registerLayerDefinition(IronBattleAxeProjectileModel.LAYER_LOCATION, IronBattleAxeProjectileModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttribute(EntityAttributeCreationEvent event){
        event.put(ModEntities.TRICERATOPS.get(), TriceratopsEntity.createAttributes().build());
        event.put(ModEntities.SIKA_DEER.get(), SikaDeerEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event){
        event.register(ModEntities.TRICERATOPS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.SIKA_DEER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
