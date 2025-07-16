package net.Chidoziealways.everythingjapanese.villager

import com.google.common.collect.ImmutableSet
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Predicate
import java.util.function.Supplier

object ModVillagers {
    val POI_TYPES: DeferredRegister<PoiType?> =
        DeferredRegister.create(ForgeRegistries.POI_TYPES, MOD_ID)
    val VILLAGER_PROFESSIONS: DeferredRegister<VillagerProfession?> =
        DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MOD_ID)

    val FURNITURE_POI: RegistryObject<PoiType?> = POI_TYPES.register<PoiType?>(
        "furniture_poi",
        Supplier {
            PoiType(
                ImmutableSet.copyOf<BlockState?>(ModBlocks.CHAIR.get().getStateDefinition().getPossibleStates()),
                1, 1
            )
        })

    val FURNITURE_MAKER: RegistryObject<VillagerProfession?> = VILLAGER_PROFESSIONS.register<VillagerProfession?>(
        "furniture_maker",
        Supplier {
            VillagerProfession(
                Component.literal("§cFurniture Maker"),
                { holder: Holder<PoiType?>? -> holder!!.value() === FURNITURE_POI.get() },
                { holder: Holder<PoiType?>? -> holder!!.value() === FURNITURE_POI.get() },
                ImmutableSet.of<Item?>(),
                ImmutableSet.of<Block?>(),
                ModSounds.MAGIC_BLOCK_HIT!!.get()
            )
        })

    fun register(eventBus: BusGroup?) {
        POI_TYPES.register(eventBus)
        VILLAGER_PROFESSIONS.register(eventBus)
    }
}
