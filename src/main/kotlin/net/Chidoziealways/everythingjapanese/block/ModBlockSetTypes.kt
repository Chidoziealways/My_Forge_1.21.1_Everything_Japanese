package net.Chidoziealways.everythingjapanese.block

import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.properties.BlockSetType

object ModBlockSetTypes {
    val PAPER = BlockSetType.register(
        BlockSetType(
            "paper",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            SoundType.GRASS,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_PLACE
        )
    )
}