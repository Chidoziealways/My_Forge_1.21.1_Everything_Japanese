package net.Chidoziealways.everythingjapanese.structure;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.structure.custom.hellTemple.HellTempleStructurePieces;
import net.Chidoziealways.everythingjapanese.util.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.AncientCityStructurePieces;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModStructuresGen {
    private static final Logger log = LoggerFactory.getLogger(ModStructuresGen.class);

    public static void bootstrap(BootstrapContext<Structure> context) {
        HolderGetter<Biome> holdergetter = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> holdergetter1 = context.lookup(Registries.TEMPLATE_POOL);
        log.info("Bootstrapping Structures: {}", ModStructures.HELL_TEMPLE.location());
        context.register(
                ModStructures.HELL_TEMPLE,
                new JigsawStructure(
                    new Structure.StructureSettings.Builder(holdergetter.getOrThrow(ModTags.Biomes.HAS_HELL_TEMPLE))
                            .spawnOverrides(
                                    Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, NetherFortressStructure.FORTRESS_ENEMIES))
                            )
                            .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                            .build(),
                        holdergetter1.getOrThrow(HellTempleStructurePieces.START),
                        Optional.of(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "hell_temple")),
                        7,
                        ConstantHeight.of(VerticalAnchor.absolute(-27)),
                        false,
                        Optional.empty(),
                        116,
                        List.of(),
                        JigsawStructure.DEFAULT_DIMENSION_PADDING,
                        JigsawStructure.DEFAULT_LIQUID_SETTINGS
                )
                );

        log.info("Finished with Bootstrap");
    }
}
