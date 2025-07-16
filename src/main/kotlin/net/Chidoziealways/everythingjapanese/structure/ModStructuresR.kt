package net.Chidoziealways.everythingjapanese.structure

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.StructureType
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType.ContextlessType
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModStructuresR {
    val STRUCTURES: DeferredRegister<StructureType<*>?> =
        DeferredRegister.create(Registries.STRUCTURE_TYPE, MOD_ID)

    val PIECES: DeferredRegister<StructurePieceType?> =
        DeferredRegister.create(Registries.STRUCTURE_PIECE, MOD_ID)

    private fun setPieceId(pType: ContextlessType?, pKey: String): RegistryObject<StructurePieceType?>? {
        return PIECES.register<StructurePieceType?>(pKey.lowercase(), Supplier { pType })
    }


    fun register(eventBus: BusGroup?) {
        STRUCTURES.register(eventBus)
        PIECES.register(eventBus)
    }
}

