package net.Chidoziealways.everythingjapanese.stamina

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.stamina.packets.StaminaDecreasePacket
import net.Chidoziealways.everythingjapanese.stamina.packets.StaminaIncreasePacket
import net.minecraft.client.player.LocalPlayer
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.PacketDistributor
import kotlin.math.max

class StaminaCapability: IStaminaCapability {
    private var currentStamina = 0.0F
    private var currentMaxStamina = 100

    constructor()

    constructor(maxStamina: Int, stamina: Float) {
        currentMaxStamina = maxStamina
        currentStamina = stamina
    }

    override fun setMaxStamina(max: Int) {
        currentMaxStamina = max
    }

    override fun getMaxStamina(): Int = currentMaxStamina

    override fun setStamina(stamina: Float) {
        currentStamina = stamina.coerceAtLeast(0f).coerceAtMost(currentMaxStamina.toFloat())
    }

    override fun getStamina(): Float = currentStamina

    override fun decreaseStamina(amount: Float, player: ServerPlayer) {
        val delta = amount.coerceAtLeast(0f).coerceAtMost(currentMaxStamina.toFloat())
        if (delta == 0f) return
        currentStamina =  (currentStamina - delta).coerceAtLeast(0f)
        PacketDistributor.sendToPlayer(player, StaminaDecreasePacket(delta))
    }

    override fun increaseStamina(amount: Float, player: ServerPlayer) {
        val room = currentMaxStamina.toFloat() - currentStamina
        val delta = amount.coerceAtLeast(0f).coerceAtMost(room)
        if (delta == 0f) return
        currentStamina += delta
        PacketDistributor.sendToPlayer(player, StaminaIncreasePacket(delta))
    }

    override fun decreaseStamina(amount: Float, player: LocalPlayer) {
        val delta = amount.coerceAtLeast(0f).coerceAtMost(currentMaxStamina.toFloat())
        currentStamina =  (currentStamina - delta).coerceAtLeast(0f)
    }

    override fun increaseStamina(amount: Float, player: LocalPlayer) {
        val room = currentMaxStamina.toFloat() - currentStamina
        val delta = amount.coerceAtLeast(0f).coerceAtMost(room)
        currentStamina += delta
    }

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart(NbtOps.INSTANCE, this)
            .resultOrPartial { error -> System.err.println("Failed to serialize StaminaCapability: $error") }
            .orElse(CompoundTag()) as CompoundTag
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        CODEC.decode(NbtOps.INSTANCE, nbt)
            .resultOrPartial { error -> System.err.println("Failed to deserialize StaminaCapability: $error") }
            .ifPresent { pair ->
                this.currentMaxStamina = pair.first.getMaxStamina()
                this.currentStamina = pair.first.getStamina()
            }
    }

    companion object {
        val CODEC: Codec<StaminaCapability> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("maxStamina").forGetter { it.currentMaxStamina },
                Codec.FLOAT.fieldOf("stamina").forGetter { it.currentStamina }
            ).apply(instance, ::StaminaCapability)
        }
    }
}