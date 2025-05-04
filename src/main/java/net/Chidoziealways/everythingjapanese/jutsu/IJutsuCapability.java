package net.Chidoziealways.everythingjapanese.jutsu;

import net.minecraft.nbt.CompoundTag;

import java.util.Set;

public interface IJutsuCapability {
    // Adds a Jutsu to the learned list
    void learnJutsu(String jutsuId);

    // Checks if a Jutsu has been learned
    boolean hasLearnedJutsu(String jutsuId);

    // Gets all learned Jutsus
    Set<String> getLearnedJutsus();

    CompoundTag serializeNBT();

    void deserializeNBT(CompoundTag nbt);

    String getSelectedJutsu();

    void cycleJutsu();
}
