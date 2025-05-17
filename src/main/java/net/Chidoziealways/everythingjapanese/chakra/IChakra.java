package net.Chidoziealways.everythingjapanese.chakra;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IChakra {
    float getChakra();
    void setChakra(float amount);
    void addChakra(float amount);
    void subtractChakra(float amount);
    int getMaxChakra();
    void setMaxChakra(int max);
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag nbt);
    void updateMaxChakraBasedOnXP(int xpLevel);
}
