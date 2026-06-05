package net.Chidoziealways.everythingjapanese.karma

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty
import net.minecraft.world.entity.ItemOwner
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

class KarmaProperty: RangeSelectItemModelProperty {
    companion object {
        val MAP_CODEC = MapCodec.unit(KarmaProperty())
    }

    override fun get(
        stack: ItemStack,
        client: ClientLevel?,
        owner: ItemOwner?,
        int: Int
    ): Float {
        if (client == null) return 0f
        if (owner == null) return 0f

        val player = owner as? Player ?: return 0f
        val karma = player.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: return 0f
        return karma.getKarmaAmount().toFloat()
    }

    override fun type(): MapCodec<out RangeSelectItemModelProperty> = MAP_CODEC
}