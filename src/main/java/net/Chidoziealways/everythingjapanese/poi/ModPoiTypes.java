package net.Chidoziealways.everythingjapanese.poi;

import com.google.common.collect.ImmutableSet;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPoiTypes {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(
            ForgeRegistries.POI_TYPES, EverythingJapanese.MOD_ID
    );

    public static final RegistryObject<PoiType> HELL_PORTAL = POI_TYPES.register("hell_portal",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.HELL_PORTAL.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }
}
