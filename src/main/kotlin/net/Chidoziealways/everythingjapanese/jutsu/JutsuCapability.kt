package net.Chidoziealways.everythingjapanese.jutsu

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.Set
import java.util.function.BiFunction
import java.util.function.Consumer
import java.util.function.Function

class JutsuCapability : IJutsuCapability {
    private var learnedJutsus: MutableSet<String?>?
    private var selectedJutsu: String?

    constructor() {
        learnedJutsus = HashSet<String?>()
        selectedJutsu = null
    }

    constructor(learnedJutsus: MutableSet<String?>?, selectedJutsu: String?) {
        this.learnedJutsus = learnedJutsus ?: HashSet<String?>()
        this.selectedJutsu = if (selectedJutsu != null && Objects.requireNonNull<MutableSet<String?>?>(learnedJutsus)
                .contains(selectedJutsu)
        ) selectedJutsu else (if (!this.learnedJutsus!!.isEmpty()) this.learnedJutsus!!.iterator().next() else " ")
        log.debug("Capability Instance During Deserialization: {}", this)
    }

    override fun learnJutsu(jutsuId: String?) {
        learnedJutsus!!.add(jutsuId)
        log.info("learnt jutsu: {}", jutsuId)
        selectedJutsu = jutsuId
    }

    fun learnJutsu(jutsu: Jutsu) {
        learnedJutsus!!.add(jutsu.getId()!!.getPath())
    }

    override fun hasLearnedJutsu(jutsuId: String?): Boolean {
        return learnedJutsus!!.contains(jutsuId)
    }

    override fun getLearnedJutsus(): MutableSet<String?>? {
        return learnedJutsus
    }

    override fun getSelectedJutsu(): String? {
        return selectedJutsu
    }

    override fun cycleJutsu() {
        log.info("Hello from Before Return")
        log.debug("Capability Instance During Cycle Jutsu: {}", this)

        if (learnedJutsus == null) {
            log.error("learned jutsus is NULL!")
            return
        }

        if (learnedJutsus!!.isEmpty()) {
            log.warn("No Learnt Jutsus, returning! {}", learnedJutsus)
            return
        }

        log.info("Hello from cycle")

        val jutsuList: MutableList<String?> = ArrayList<String?>(learnedJutsus)

        if (selectedJutsu == null || !getLearnedJutsus()!!.contains(selectedJutsu)) {
            selectedJutsu = jutsuList.firstOrNull() // Default to first Jutsu if null
            log.info("selectedJutsu was null, setting to first Jutsu: {}", selectedJutsu)
            return
        }

        var index = jutsuList.indexOf(selectedJutsu)
        index = (index + 1) % jutsuList.size
        selectedJutsu = jutsuList.get(index)
        log.info("Cycled to Jutsu: {}", selectedJutsu)
    }

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart<Tag?>(NbtOps.INSTANCE, this)
            .resultOrPartial(Consumer { error: String? -> System.err.println("Failed to Serialize JutsuCapability: " + error) })
            .orElse(CompoundTag()) as CompoundTag
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        log.info("Deserializing Jutsu NBT: {}", nbt)

        CODEC.decode<Tag?>(NbtOps.INSTANCE, nbt)
            .resultOrPartial(Consumer { error: String? -> System.err.println("Failed to Deserialize JutsuCapability: " + error) })
            .ifPresent(Consumer { iJutsuCapabilityTagPair: Pair<IJutsuCapability?, Tag?>? ->
                val capability: IJutsuCapability = iJutsuCapabilityTagPair!!.getFirst()!!
                log.info("Decoded JutsuCapability: {}", capability.getLearnedJutsus())

                this.learnedJutsus = HashSet<String?>(capability.getLearnedJutsus())
                this.selectedJutsu = if (nbt.contains("selectedJutsu")) nbt.getStringOr(
                    "selectedJutsu",
                    " "
                ) else (if (!this.learnedJutsus!!.isEmpty()) this.learnedJutsus!!.iterator().next() else " ")
                log.info("Final Learned Jutsus after Deserialization: {}", this.learnedJutsus)
            })
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JutsuCapability::class.java)
        val CODEC: Codec<IJutsuCapability> =
            RecordCodecBuilder.create<IJutsuCapability>(Function { instance: RecordCodecBuilder.Instance<IJutsuCapability?>? ->
                instance!!.group<MutableList<String?>?, String?>(
                    Codec.STRING.listOf().fieldOf("learnedJutsus")
                        .forGetter<IJutsuCapability?>(Function { o: IJutsuCapability? ->
                            o!!.getLearnedJutsus()!!.stream().toList()
                        }),
                    Codec.STRING.fieldOf("selectedJutsu")
                        .forGetter<IJutsuCapability?>(Function { o: IJutsuCapability? -> if (o!!.getSelectedJutsu() != null) o.getSelectedJutsu() else " " })
                ).apply<IJutsuCapability?>(
                    instance
                ) { learnedJutsus: MutableList<String?>?, selectedJutsu: String? ->
                    JutsuCapability(
                        Set.copyOf<String?>(learnedJutsus), selectedJutsu
                    )
                }
            })
    }
}
