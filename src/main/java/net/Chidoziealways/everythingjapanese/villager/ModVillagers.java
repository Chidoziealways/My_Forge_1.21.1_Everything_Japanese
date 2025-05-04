package net.Chidoziealways.everythingjapanese.villager;

import com.google.common.collect.ImmutableSet;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, EverythingJapanese.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, EverythingJapanese.MOD_ID);

    public static final RegistryObject<PoiType> FURNITURE_POI = POI_TYPES.register("furniture_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.CHAIR.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> FURNITURE_MAKER = VILLAGER_PROFESSIONS.register("furniture_maker",
            () -> new VillagerProfession(Component.literal("§cFurniture Maker"), holder -> holder.value() == FURNITURE_POI.get(),
                    holder -> holder.value() == FURNITURE_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    ModSounds.MAGIC_BLOCK_HIT.get()));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
