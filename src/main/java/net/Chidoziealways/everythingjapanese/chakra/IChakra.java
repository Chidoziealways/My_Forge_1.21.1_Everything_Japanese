package net.Chidoziealways.everythingjapanese.chakra;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IChakra {
    int getChakra();
    void setChakra(int amount);
    void addChakra(int amount);
    void subtractChakra(int amount);
    int getMaxChakra();
    void setMaxChakra(int max);
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag nbt);
    void updateMaxChakraBasedOnXP(int xpLevel);
}
