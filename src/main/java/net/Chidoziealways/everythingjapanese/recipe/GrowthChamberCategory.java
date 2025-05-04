package net.Chidoziealways.everythingjapanese.recipe;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.crafting.CraftingBookCategory;

import java.util.function.IntFunction;

public enum GrowthChamberCategory implements StringRepresentable {
    INGREDIENTS("ingredients", 0);

    public static final Codec<GrowthChamberCategory> CODEC = StringRepresentable.fromEnum(GrowthChamberCategory::values);
    public static final IntFunction<GrowthChamberCategory> BY_ID = ByIdMap.continuous(
            GrowthChamberCategory::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO
    );
    public static final StreamCodec<ByteBuf, GrowthChamberCategory> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, GrowthChamberCategory::id);

    private final String name;
    private final int id;

    GrowthChamberCategory(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    private int id() {
        return this.id;
    }
}
