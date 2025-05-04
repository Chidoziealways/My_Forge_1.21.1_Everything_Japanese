package net.Chidoziealways.everythingjapanese.chakra;

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChakraProvider implements ICapabilityProvider {
    private final Chakra instance = new Chakra();
    private final LazyOptional<IChakra> optional = LazyOptional.of(() -> instance);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.CHAKRA_CAPABILITY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    public CompoundTag serializeNBT() {
        return (CompoundTag) Chakra.CODEC.encodeStart(NbtOps.INSTANCE, instance)
                .resultOrPartial(error -> System.err.println("Failed to Serialize Chakra: " + error))
                .orElse(new CompoundTag());
    }

    public void deserializeNBT(CompoundTag nbt) {
        Chakra.CODEC.decode(NbtOps.INSTANCE, nbt)
                .resultOrPartial(error -> System.err.println("Failed to Deserialize Chakra: " + error))
                .ifPresent(iChakraTagPair -> {
                    IChakra capability = iChakraTagPair.getFirst();
                    instance.setChakra(capability.getChakra());
                    instance.setMaxChakra(capability.getMaxChakra());
                });
    }
}
