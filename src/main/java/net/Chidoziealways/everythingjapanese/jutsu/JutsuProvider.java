package net.Chidoziealways.everythingjapanese.jutsu;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class JutsuProvider implements ICapabilityProvider {
    private final IJutsuCapability instance = new JutsuCapability();
    private final LazyOptional<IJutsuCapability> optional = LazyOptional.of(() -> instance);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ModCapabilities.JUTSU_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    // Optional serialization logic
    public CompoundTag serializeNBT() {
        return instance.serializeNBT();
    }

    public void deserializeNBT(CompoundTag nbt) {
        instance.deserializeNBT(nbt);
    }
}
