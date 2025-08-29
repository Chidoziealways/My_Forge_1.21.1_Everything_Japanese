package net.Chidoziealways.everythingjapanese.commands

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu.JutsuArgument
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.synchronization.ArgumentTypeInfo
import net.minecraft.commands.synchronization.ArgumentTypeInfos
import net.minecraft.commands.synchronization.SingletonArgumentInfo
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
import java.util.function.Function

object ModArgumentTypes {
    val ARGUMENT_TYPES = DeferredRegister.create<ArgumentTypeInfo<*, *>?>(Registries.COMMAND_ARGUMENT_TYPE, MOD_ID)

    val JUTSU_ARGUMENT =
        ARGUMENT_TYPES.register(
            "jutsu")
            { ->
                ArgumentTypeInfos.registerByClass(
                    JutsuArgument::class.java,
                    SingletonArgumentInfo.contextAware<JutsuArgument?>(Function { context: CommandBuildContext? ->
                        JutsuArgument.Companion.jutsu(context)
                    })
                )
            }

    fun register(eventBus: IEventBus) {
        ARGUMENT_TYPES.register(eventBus)
    }
}
