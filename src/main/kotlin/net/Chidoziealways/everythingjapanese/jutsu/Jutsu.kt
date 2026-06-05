package net.Chidoziealways.everythingjapanese.jutsu

import com.mojang.serialization.DataResult
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponentType
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.Unit
import net.minecraft.world.entity.player.Player

abstract class Jutsu(// Unique identifier for the Jutsu
    private val id: Identifier, // Name of the Jutsu (localized)
    val name: String?, // Chakra cost to use the Jutsu
    private val chakraCost: Float, // Power level or damage output
    private val powerLevel: Int, vararg type: JutsuType?
) {
    private val type: Array<JutsuType?>? =
        type as Array<JutsuType?>? // Type of Jutsu (Ninjutsu, Genjutsu, Taijutsu, etc.)

    private var builtComponents: DataComponentMap? = null

    fun components(): DataComponentMap {
        if (builtComponents == null) {
            builtComponents = object : DataComponentMap {
                override fun keySet(): MutableSet<DataComponentType<*>> {
                    return mutableSetOf<DataComponentType<*>>()
                }

                override fun <T : Any> get(p_395766_: DataComponentType<out T>): T? {
                    return null
                }
            }
        }

        return builtComponents!!
    }

    fun getID(): Identifier {
        return id
    }

    fun getChakraCost(): Float {
        return chakraCost
    }

    fun getPowerLevel(): Int {
        return powerLevel
    }


    fun getType(): Array<JutsuType?>? {
        return type
    }

    override fun toString(): String {
        return "Jutsu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chakraCost=" + chakraCost +
                ", powerLevel=" + powerLevel +
                ", type=" + type +
                '}'
    }

    abstract fun cast(player: ServerPlayer): Boolean

    companion object {
        fun validateComponents(components: DataComponentMap): DataResult<Unit> {
            return DataResult.success<Unit?>(Unit.INSTANCE)
        }
    }
}
