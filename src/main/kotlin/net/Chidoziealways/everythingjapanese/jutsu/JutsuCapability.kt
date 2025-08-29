package net.Chidoziealways.everythingjapanese.jutsu

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.PacketDistributor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.Set
import java.util.function.Consumer
import java.util.function.Function

class JutsuCapability : IJutsuCapability {
    private var learnedJutsus: MutableSet<String> = HashSet()
    private var selectedJutsu: String = ""
    private var jutsuMastery: MutableMap<String, Float> = HashMap()

    constructor() {
        learnedJutsus = HashSet()
        selectedJutsu = ""
        jutsuMastery = HashMap()
    }

    constructor(learnedJutsus: MutableSet<String>, selectedJutsu: String?, masteryMap: MutableMap<String, Float> = HashMap()) {
        this.learnedJutsus = learnedJutsus
        this.jutsuMastery = masteryMap
        this.selectedJutsu = if (selectedJutsu != null && Objects.requireNonNull<MutableSet<String>>(learnedJutsus)
                .contains(selectedJutsu)
        ) selectedJutsu else (if (!this.learnedJutsus.isEmpty()) this.learnedJutsus.iterator().next() else " ")
        log.debug("Capability Instance During Deserialization: {}", this)
    }

    override fun learnJutsu(jutsuId: String, player: ServerPlayer) {
        learnedJutsus.add(jutsuId)
        jutsuMastery.putIfAbsent(jutsuId, 0f)
        selectedJutsu = jutsuId
        PacketDistributor.sendToPlayer(player, JutsuSyncPacket(learnedJutsus, jutsuMastery, selectedJutsu))
        log.info("learnt jutsu: {}", jutsuId)
    }

    override fun hasLearnedJutsu(jutsuId: String): Boolean {
        return learnedJutsus.contains(jutsuId)
    }

    override fun getLearnedJutsus(): MutableSet<String> {
        return learnedJutsus
    }

    override fun setLearnedJutsus(jutsus: MutableSet<String>) {
        learnedJutsus = jutsus
    }

    override fun getMastery(jutsuId: String): Float {
        return jutsuMastery[jutsuId] ?: 0f
    }

    override fun addMastery(jutsuId: String, amount: Float, player: ServerPlayer) {
        val current = jutsuMastery.getOrDefault(jutsuId, 0f)
        val updated = (current + amount).coerceAtMost(1000f)
        jutsuMastery[jutsuId] = updated
        PacketDistributor.sendToPlayer(player, JutsuSyncPacket(learnedJutsus, jutsuMastery, selectedJutsu))
    }

    override fun getMastery(): Map<String, Float> {
        return jutsuMastery
    }

    override fun setMastery(masteryMap: MutableMap<String, Float>) {
        jutsuMastery = masteryMap
    }

    override fun getSelectedJutsu(): String {
        return selectedJutsu
    }

    override fun setSelectedJutsu(jutsu: String) {
        selectedJutsu = jutsu
    }

    override fun cycleJutsu(player: ServerPlayer) {
        log.info("Hello from Before Return")
        log.debug("Capability Instance During Cycle Jutsu: {}", this)

        if (learnedJutsus.isEmpty()) {
            log.warn("No Learnt Jutsus, returning! {}", learnedJutsus)
            return
        }

        log.info("Hello from cycle")

        val jutsuList: MutableList<String> = ArrayList<String>(learnedJutsus)

        if (!getLearnedJutsus().contains(selectedJutsu)) {
            selectedJutsu = jutsuList.first() // Default to first Jutsu if null
            log.info("selectedJutsu was null, setting to first Jutsu: {}", selectedJutsu)
            return
        }

        var index = jutsuList.indexOf(selectedJutsu)
        index = (index + 1) % jutsuList.size
        selectedJutsu = jutsuList[index]
        log.info("Cycled to Jutsu: {}", selectedJutsu)
        PacketDistributor.sendToPlayer(player, JutsuSyncPacket(learnedJutsus, jutsuMastery, selectedJutsu))
    }

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart<Tag>(NbtOps.INSTANCE, this)
            .resultOrPartial { error: String -> System.err.println("Failed to Serialize JutsuCapability: $error") }
            .orElse(CompoundTag()) as CompoundTag
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        log.info("Deserializing Jutsu NBT: {}", nbt)

        CODEC.decode(NbtOps.INSTANCE, nbt)
            .resultOrPartial { error -> log.error("Failed to deserialize JutsuCapability: {}", error) }
            .ifPresent { pair ->
                val cap = pair.first ?: run {
                    log.error("Decoded capability is null! Keeping old data.")
                    return@ifPresent
                }
                this.learnedJutsus = HashSet(cap.getLearnedJutsus())
                this.jutsuMastery = HashMap(cap.getMastery())
                this.selectedJutsu = if (cap.getSelectedJutsu().isNotBlank() && learnedJutsus.contains(cap.getSelectedJutsu()))
                    cap.getSelectedJutsu() else learnedJutsus.firstOrNull() ?: ""
            }

    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JutsuCapability::class.java)
        val CODEC: Codec<IJutsuCapability> =
            RecordCodecBuilder.create<IJutsuCapability>(Function { instance: RecordCodecBuilder.Instance<IJutsuCapability> ->
                instance.group(
                    Codec.STRING.listOf().fieldOf("learnedJutsus").forGetter<IJutsuCapability> { o: IJutsuCapability ->
                        o.getLearnedJutsus().stream().toList()
                        },
                    Codec.STRING.fieldOf("selectedJutsu").forGetter<IJutsuCapability> { o: IJutsuCapability -> o.getSelectedJutsu() },
                    Codec.unboundedMap(Codec.STRING, Codec.FLOAT).fieldOf("masteryMap")
                        .forGetter { it.getMastery() }
                ).apply<IJutsuCapability>(
                    instance
                ) { learnedJutsus: MutableList<String>, selectedJutsu: String, mastery: Map<String, Float> ->
                    JutsuCapability(
                        Set.copyOf<String>(learnedJutsus), selectedJutsu, mastery.toMutableMap()
                    )
                }
            })
    }
}
