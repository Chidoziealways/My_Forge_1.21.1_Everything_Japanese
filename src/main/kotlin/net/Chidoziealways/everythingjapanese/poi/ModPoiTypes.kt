package net.Chidoziealways.everythingjapanese.poi

import com.google.common.collect.ImmutableSet
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModPoiTypes {
    val POI_TYPES: DeferredRegister<PoiType?> = DeferredRegister.create(
            ForgeRegistries.POI_TYPES, MOD_ID
    )

    val HELL_PORTAL: RegistryObject<PoiType?>? = POI_TYPES.register<PoiType?>(
        "hell_portal",
        Supplier {
            PoiType(
                ImmutableSet.copyOf<BlockState?>(
                    ModBlocks.HELL_PORTAL.get().getStateDefinition().getPossibleStates()
                ), 1, 1
            )
        })

    fun register(eventBus: BusGroup?) {
        POI_TYPES.register(eventBus)
    }
}
