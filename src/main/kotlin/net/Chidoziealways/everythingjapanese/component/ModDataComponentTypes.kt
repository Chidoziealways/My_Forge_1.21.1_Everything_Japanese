package net.Chidoziealways.everythingjapanese.component

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier
import java.util.function.UnaryOperator

object ModDataComponentTypes {
    val DATA_COMPONENT_TYPES: DeferredRegister<DataComponentType<*>?> =
        DeferredRegister.create<DataComponentType<*>?>(Registries.DATA_COMPONENT_TYPE, MOD_ID)

    val COORDINATES: RegistryObject<DataComponentType<BlockPos?>?>? = register<BlockPos?>(
        "coordinates",
        UnaryOperator { builder: DataComponentType.Builder<BlockPos?>? -> builder!!.persistent(BlockPos.CODEC) })

    private fun <T> register(
        name: String?,
        builderOperator: UnaryOperator<DataComponentType.Builder<T?>?>
    ): RegistryObject<DataComponentType<T?>?>? {
        return DATA_COMPONENT_TYPES.register<DataComponentType<T?>?>(
            name,
            Supplier { builderOperator.apply(DataComponentType.builder<T?>())!!.build() })
    }

    fun register(eventBus: BusGroup?) {
        DATA_COMPONENT_TYPES.register(eventBus)
        logInfo("REGISTERING EVERY SINGLE DATA COMPONENT IN MODDATACOMPONENTTYPES")
    }
}
