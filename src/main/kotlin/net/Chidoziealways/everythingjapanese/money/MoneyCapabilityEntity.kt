package net.Chidoziealways.everythingjapanese.money

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.Chidoziealways.everythingjapanese.money.packets.MoneySyncPacket
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.PacketDistributor

class MoneyCapabilityEntity(): IMoneyCapability {
    var balance: Int = 20

    constructor(balance: Int): this() {
        this.balance = balance
    }

    override fun getMaxMoney(): Int? = null

    override fun setMaxMoney(amount: Int) {

    }

    override fun getMoney(): Int = balance

    override fun addMoney(amount: Int, player: ServerPlayer) {
        balance += amount
        PacketDistributor.sendToPlayer(player, MoneySyncPacket(balance))
    }

    override fun addMoney(amount: Int) {
        balance += amount
    }

    override fun setMoney(amount: Int) {
        balance = amount
    }

    override fun removeMoney(amount: Int, player: ServerPlayer) {
        balance -= amount
        PacketDistributor.sendToPlayer(player, MoneySyncPacket(balance))
    }

    override fun removeMoney(amount: Int) {
        balance -= amount
    }

    override fun setMoney(amount: Int, player: ServerPlayer) {
        balance = amount
        PacketDistributor.sendToPlayer(player, MoneySyncPacket(balance))
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        CODEC.decode(NbtOps.INSTANCE, nbt)
            .resultOrPartial { error -> System.err.println("Failed to Deserialize Money: $error") }
            .ifPresent { pair ->
                val capability = pair.first
                this.balance = capability.getMoney()
            }
    }

    override fun serializeNBT(): CompoundTag {
        return CODEC.encodeStart(NbtOps.INSTANCE, this)
            .resultOrPartial { error -> System.err.println("Failed to Serialize Money: $error") }
            .orElse(CompoundTag()) as CompoundTag
    }

    companion object {
        val CODEC: Codec<IMoneyCapability> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.INT.fieldOf("balance").forGetter{ it.getMoney() }
                ).apply(instance, ::MoneyCapabilityEntity)
            }
    }
}