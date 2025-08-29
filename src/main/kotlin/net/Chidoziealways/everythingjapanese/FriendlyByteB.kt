package net.Chidoziealways.everythingjapanese

import net.Chidoziealways.everythingjapanese.quest.Quest
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.network.FriendlyByteBuf

fun <T> FriendlyByteBuf.writeSet(set: Set<T>, writeElement: (FriendlyByteBuf, T) -> Unit) {
    this.writeVarInt(set.size)
    for (element in set) {
        writeElement(this, element)
    }
}

fun <T> FriendlyByteBuf.readSet(readElement: (FriendlyByteBuf) -> T): MutableSet<T> {
    val size = this.readVarInt()
    val result = mutableSetOf<T>()
    repeat(size) {
        result += readElement(this)
    }
    return result
}

fun FriendlyByteBuf.writeQuest(quest: Quest) {
    val result = Quest.QUEST_CODEC.encodeStart(NbtOps.INSTANCE, quest)
    if (result.result().isPresent) {
        this.writeNbt(result.result().get())
    } else {
        throw IllegalStateException("Failed to encode Quest to NBT")
    }
}

fun FriendlyByteBuf.readQuest(): Quest {
    val tag: CompoundTag = this.readNbt() ?: throw IllegalStateException("No NBT found in buffer")
    val result = Quest.QUEST_CODEC.parse(NbtOps.INSTANCE, tag)
    return result.result().orElseThrow { IllegalStateException("Failed to decode Quest from NBT") }
}
