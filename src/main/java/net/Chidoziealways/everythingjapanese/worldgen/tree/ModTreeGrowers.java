package net.Chidoziealways.everythingjapanese.worldgen.tree;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower HINOKI = new TreeGrower(EverythingJapanese.MOD_ID + ":hinoki",
            Optional.empty(), Optional.of(ModConfiguredFeatures.HINOKI_KEY), Optional.empty()
    );
}
