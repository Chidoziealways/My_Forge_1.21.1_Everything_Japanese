package net.Chidoziealways.everythingjapanese.villager

import com.google.common.collect.ImmutableSet
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.entity.npc.VillagerProfession
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModVillagers {
    val POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, JAPANESE_MOD_ID)
    val VILLAGER_PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, JAPANESE_MOD_ID)

    val FURNITURE_POI = POI_TYPES.register(
        "furniture_poi",
        Supplier {
            PoiType(
                ImmutableSet.copyOf(JModBlocks.CHAIR.getStateDefinition().getPossibleStates()),
                1, 1
            )
        })

    val FURNITURE_MAKER = VILLAGER_PROFESSIONS.register(
        "furniture_maker",
        Supplier {
            VillagerProfession(
                Component.literal("§cFurniture Maker"),
                { holder: Holder<PoiType> -> holder.value() === FURNITURE_POI.get() },
                { holder: Holder<PoiType> -> holder.value() === FURNITURE_POI.get() },
                ImmutableSet.of(),
                ImmutableSet.of(),
                ModSounds.MAGIC_BLOCK_HIT.get()
            )
        })

    fun register(eventBus: IEventBus) {
        POI_TYPES.register(eventBus)
        VILLAGER_PROFESSIONS.register(eventBus)
    }
}
