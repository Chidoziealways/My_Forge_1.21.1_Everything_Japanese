package net.Chidoziealways.everythingjapanese.state.properties

import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty

object ModBlockStateProperties {
    val BITES_16: IntegerProperty = IntegerProperty.create("bites", 0, 16)
    val BITES_11: IntegerProperty = IntegerProperty.create("bites", 0, 11)
    val CLICKED: BooleanProperty = BooleanProperty.create("clicked")
    val AGE_4: IntegerProperty = IntegerProperty.create("age", 0, 4)
    val OPEN: BooleanProperty = BooleanProperty.create("open")
}
