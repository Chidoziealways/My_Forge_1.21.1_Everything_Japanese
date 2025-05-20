package net.Chidoziealways.everythingjapanese.structure;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class ModStructureSets {
    public static ResourceKey<StructureSet> HELL_TEMPLE = register("hell_temple");

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> holdergetter = context.lookup(Registries.STRUCTURE);
        HolderGetter<Biome> holdergetter1 = context.lookup(Registries.BIOME);

        context.register(
                ModStructureSets.HELL_TEMPLE,
                new StructureSet(holdergetter.getOrThrow(ModStructures.HELL_TEMPLE), new RandomSpreadStructurePlacement(24, 8, RandomSpreadType.LINEAR, 20083232))
        );
    }

    private static ResourceKey<StructureSet> register(String pName) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, pName));
    }
}
