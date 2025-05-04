package net.Chidoziealways.everythingjapanese.jutsu;

import com.mojang.serialization.DataResult;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.Set;

public abstract class Jutsu {
    private final ResourceLocation id; // Unique identifier for the Jutsu
    protected final String name;         // Name of the Jutsu (localized)
    private final int chakraCost;      // Chakra cost to use the Jutsu
    private final int powerLevel;      // Power level or damage output
    private final JutsuType[] type;      // Type of Jutsu (Ninjutsu, Genjutsu, Taijutsu, etc.)

    public Jutsu(ResourceLocation id, String name, int chakraCost, int powerLevel, JutsuType... type) {
        this.id = id;
        this.name = name;
        this.chakraCost = chakraCost;
        this.powerLevel = powerLevel;
        this.type = type;
    }

    public static DataResult<Unit> validateComponents(DataComponentMap components) {
        return DataResult.success(Unit.INSTANCE);
    }

    @Nullable
    private DataComponentMap builtComponents = null;

    public DataComponentMap components() {
        if (builtComponents == null) {
            builtComponents = new DataComponentMap() {
                @Override
                public Set<DataComponentType<?>> keySet() {
                    return Set.of();
                }

                @Override
                public @org.jetbrains.annotations.Nullable <T> T get(DataComponentType<? extends T> p_395766_) {
                    return null;
                }
            };
        }

        return builtComponents;
    }

    public ResourceLocation getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getChakraCost() {
        return chakraCost;
    }

    public int getPowerLevel() {
        return powerLevel;
    }



    public JutsuType[] getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Jutsu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chakraCost=" + chakraCost +
                ", powerLevel=" + powerLevel +
                ", type=" + type +
                '}';
    }

    public abstract void cast(Player player);
}
