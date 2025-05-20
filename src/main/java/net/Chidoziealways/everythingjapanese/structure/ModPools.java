package net.Chidoziealways.everythingjapanese.structure;

import net.Chidoziealways.everythingjapanese.structure.custom.hellTemple.HellTempleStructurePieces;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class ModPools {
    public static void bootstrap(BootstrapContext<StructureTemplatePool> pContext) {
        HellTempleStructurePieces.bootstrap(pContext);
    }
}
