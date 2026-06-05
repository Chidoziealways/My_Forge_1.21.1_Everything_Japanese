package net.Chidoziealways.everythingjapanese.poi

import com.google.common.collect.ImmutableSet
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModPoiTypes {
    val POI_TYPES = DeferredRegister.create(
        Registries.POINT_OF_INTEREST_TYPE, JAPANESE_MOD_ID
    )

    val HELL_PORTAL = POI_TYPES.register(
        "hell_portal",
        Supplier {
            PoiType(
                ImmutableSet.copyOf<BlockState>(
                    JModBlocks.HELL_PORTAL.getStateDefinition().getPossibleStates()
                ), 1, 1
            )
        })

    fun register(eventBus: IEventBus) {
        POI_TYPES.register(eventBus)
    }
}
