package net.Chidoziealways.everythingjapanese.chakra;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;

public class Chakra implements IChakra{
    private float chakra = 100; // Default Chakra amount
    private int maxChakra = 100;

    public Chakra(float chakra, int maxChakra) {
        this.chakra = chakra;
        this.maxChakra = maxChakra;
    }

    public Chakra() {
    }

    @Override
    public float getChakra() {
        return chakra;
    }

    @Override
    public void setChakra(float amount) {
        chakra = Math.min(amount, maxChakra);
    }

    @Override
    public void addChakra(float amount) {
        setChakra(chakra + amount);
    }

    @Override
    public void subtractChakra(float amount) {
        setChakra(chakra - amount);
    }

    @Override
    public int getMaxChakra() {
        return maxChakra;
    }

    @Override
    public void setMaxChakra(int max) {
        this.maxChakra = max;
    }

    public void updateMaxChakraBasedOnXP(int xpLevel) {
        this.maxChakra = 100 + (xpLevel * 10);
    }

    @Override
    public CompoundTag serializeNBT() {
        return (CompoundTag) Chakra.CODEC.encodeStart(NbtOps.INSTANCE, this)
                .resultOrPartial(error -> System.err.println("Failed to Serialize Chakra: " + error))
                .orElse(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        Chakra.CODEC.decode(NbtOps.INSTANCE, nbt)
                .resultOrPartial(error -> System.err.println("Failed to Deserialize Chakra: " + error))
                .ifPresent(iChakraTagPair -> {
                    IChakra capability = iChakraTagPair.getFirst();
                    this.chakra = capability.getChakra();
                    this.maxChakra = capability.getMaxChakra();
                });
    }

    public static final Codec<IChakra> CODEC = RecordCodecBuilder.create(iChakraInstance -> iChakraInstance.group(
            Codec.FLOAT.fieldOf("chakra").forGetter(IChakra::getChakra),
            Codec.INT.fieldOf("maxChakra").forGetter(IChakra::getMaxChakra)
    ).apply(iChakraInstance, Chakra::new));
}
