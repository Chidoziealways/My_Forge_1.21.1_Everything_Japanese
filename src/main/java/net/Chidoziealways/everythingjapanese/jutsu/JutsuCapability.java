package net.Chidoziealways.everythingjapanese.jutsu;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JutsuCapability implements IJutsuCapability{
    private static final Logger log = LoggerFactory.getLogger(JutsuCapability.class);
    private Set<String> learnedJutsus;
    private String selectedJutsu;

    public JutsuCapability() {
        learnedJutsus = new HashSet<>();
        selectedJutsu = null;
    }

    public JutsuCapability(Set<String> learnedJutsus, String selectedJutsu) {
        this.learnedJutsus = learnedJutsus != null ? learnedJutsus : new HashSet<>();
        this.selectedJutsu = selectedJutsu != null && Objects.requireNonNull(learnedJutsus).contains(selectedJutsu) ? selectedJutsu :
                (!this.learnedJutsus.isEmpty() ? this.learnedJutsus.iterator().next() : " ");
        log.debug("Capability Instance During Deserialization: {}", this);
    }
    
    @Override
    public void learnJutsu(String jutsuId) {
        learnedJutsus.add(jutsuId);
        log.info("learnt jutsu: {}", jutsuId);
        selectedJutsu = jutsuId;
    }

    public void learnJutsu(Jutsu jutsu) {
        learnedJutsus.add(jutsu.getId().getPath());
    }

    @Override
    public boolean hasLearnedJutsu(String jutsuId) {
        return learnedJutsus.contains(jutsuId);
    }

    @Override
    public Set<String> getLearnedJutsus() {
        return learnedJutsus;
    }

    @Override
    public String getSelectedJutsu() {
        return selectedJutsu;
    }

    @Override
    public void cycleJutsu() {
        log.info("Hello from Before Return");
        log.debug("Capability Instance During Cycle Jutsu: {}", this);

        if (learnedJutsus == null) {
            log.error("learned jutsus is NULL!");
            return;
        }

        if (learnedJutsus.isEmpty()) {
            log.warn("No Learnt Jutsus, returning! {}", learnedJutsus);
            return;
        }

        log.info("Hello from cycle");

        List<String> jutsuList = new ArrayList<>(learnedJutsus);

        if (selectedJutsu == null || !getLearnedJutsus().contains(selectedJutsu)) {
            selectedJutsu = jutsuList.getFirst(); // Default to first Jutsu if null
            log.info("selectedJutsu was null, setting to first Jutsu: {}", selectedJutsu);
            return;
        }

        int index = jutsuList.indexOf(selectedJutsu);
        index = (index + 1) % jutsuList.size();
        selectedJutsu = jutsuList.get(index);
        log.info("Cycled to Jutsu: {}", selectedJutsu);
    }

    @Override
    public CompoundTag serializeNBT() {
        return (CompoundTag) JutsuCapability.CODEC.encodeStart(NbtOps.INSTANCE, this)
                .resultOrPartial(error -> System.err.println("Failed to Serialize JutsuCapability: " + error))
                .orElse(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        log.info("Deserializing Jutsu NBT: {}", nbt);

        JutsuCapability.CODEC.decode(NbtOps.INSTANCE, nbt)
                .resultOrPartial(error -> System.err.println("Failed to Deserialize JutsuCapability: " + error))
                .ifPresent(iJutsuCapabilityTagPair -> {
                    IJutsuCapability capability = iJutsuCapabilityTagPair.getFirst();

                    log.info("Decoded JutsuCapability: {}", capability.getLearnedJutsus());

                    this.learnedJutsus = new HashSet<>(capability.getLearnedJutsus());
                    this.selectedJutsu = nbt.contains("selectedJutsu") ? nbt.getStringOr("selectedJutsu", " ") :
                            (!this.learnedJutsus.isEmpty() ? this.learnedJutsus.iterator().next() : " ");

                    log.info("Final Learned Jutsus after Deserialization: {}", this.learnedJutsus);
                });
    }

    public static final Codec<IJutsuCapability> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.listOf().fieldOf("learnedJutsus").forGetter(o -> o.getLearnedJutsus().stream().toList()),
            Codec.STRING.fieldOf("selectedJutsu").forGetter(o -> o.getSelectedJutsu() != null ? o.getSelectedJutsu() : " ")
    ).apply(instance, (learnedJutsus, selectedJutsu) -> new JutsuCapability(Set.copyOf(learnedJutsus), selectedJutsu)));
}
