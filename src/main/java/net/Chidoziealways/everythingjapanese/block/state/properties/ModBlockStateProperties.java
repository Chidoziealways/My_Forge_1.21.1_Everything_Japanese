package net.Chidoziealways.everythingjapanese.block.state.properties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties {
    public static final IntegerProperty BITES  = IntegerProperty.create("bites", 0, 16);
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    public static final IntegerProperty AGE_4 = IntegerProperty.create("age", 0, 4);
}
