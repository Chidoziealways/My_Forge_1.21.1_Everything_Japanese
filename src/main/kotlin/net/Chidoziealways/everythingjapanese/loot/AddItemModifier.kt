package net.Chidoziealways.everythingjapanese.loot

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.neoforged.neoforge.common.loot.IGlobalLootModifier
import net.neoforged.neoforge.common.loot.LootModifier
import java.util.function.BiFunction
import java.util.function.Function

class AddItemModifier(conditionsIn: Array<LootItemCondition?>, private val item: Item) : LootModifier(conditionsIn) {
    override fun doApply(
        generatedLoot: ObjectArrayList<ItemStack>,
        lootContext: LootContext
    ): ObjectArrayList<ItemStack> {
        for (condition in this.conditions) {
            if (!condition.test(lootContext)) {
                return generatedLoot
            }
        }
        generatedLoot.add(ItemStack(this.item))

        return generatedLoot
    }

    override fun codec(): MapCodec<out IGlobalLootModifier?> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<AddItemModifier?> =
            RecordCodecBuilder.mapCodec<AddItemModifier?>(Function { inst: RecordCodecBuilder.Instance<AddItemModifier?>? ->
                codecStart<AddItemModifier?>(inst).and<Item?>(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item")
                        .forGetter<AddItemModifier?>(Function { e: AddItemModifier? -> e!!.item })
                ).apply<AddItemModifier?>(inst, BiFunction { conditionsIn: Array<LootItemCondition?>?, item: Item? ->
                    AddItemModifier(
                        conditionsIn!!, item!!
                    )
                })
            })
    }
}
