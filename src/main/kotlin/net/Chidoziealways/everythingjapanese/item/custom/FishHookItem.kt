package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.item.custom.fish_hook.MobMorph
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import kotlin.streams.toList

class FishHookItem(props: Properties): Item(props) {
    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        val morphNames = player.get(ModDataComponentTypes.MORPHS) ?: return InteractionResult.FAIL
        val morphs = ModRegistries.MORPHS.stream().toList()
        val availableMorphs = morphs.stream().filter { morphNames.contains(it.name) }.toList()

        if (availableMorphs.isEmpty()) return InteractionResult.FAIL

        val currentMorphKey = player.get(ModDataComponentTypes.CURRENT_MORPH)
        val currentIndex = availableMorphs.indexOfFirst { it.name == currentMorphKey }
        val nextIndex = (currentIndex + 1) % availableMorphs.size
        val nextMorph = availableMorphs[nextIndex]

        player.setComponent(ModDataComponentTypes.CURRENT_MORPH.get(), nextMorph.name)
        return InteractionResult.SUCCESS
    }
}