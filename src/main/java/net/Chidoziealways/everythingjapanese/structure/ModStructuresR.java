package net.Chidoziealways.everythingjapanese.structure;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class ModStructuresR {
    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, EverythingJapanese.MOD_ID);

    public static final DeferredRegister<StructurePieceType> PIECES =
                DeferredRegister.create(Registries.STRUCTURE_PIECE, EverythingJapanese.MOD_ID);

    private static RegistryObject<StructurePieceType> setPieceId(StructurePieceType.ContextlessType pType, String pKey) {
        return PIECES.register(pKey.toLowerCase(Locale.ROOT), () -> pType);
    }


    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
        PIECES.register(eventBus);
    }
}

