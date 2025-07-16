package net.Chidoziealways.everythingjapanese.state.properties

import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty

object ModBlockStateProperties {
    val BITES: IntegerProperty = IntegerProperty.create("bites", 0, 16)
    val CLICKED: BooleanProperty = BooleanProperty.create("clicked")
    val AGE_4: IntegerProperty = IntegerProperty.create("age", 0, 4)
}
