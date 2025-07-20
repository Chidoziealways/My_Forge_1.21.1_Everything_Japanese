package net.Chidoziealways.everythingjapanese.commands

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu.JutsuArgument
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.arguments.ResourceArgument.Info
import net.minecraft.commands.synchronization.ArgumentTypeInfo
import net.minecraft.commands.synchronization.ArgumentTypeInfos
import net.minecraft.commands.synchronization.SingletonArgumentInfo
import net.minecraft.core.registries.Registries
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.registerObject
import java.util.function.Supplier
import java.util.function.Function

object ModArgumentTypes {
    val ARGUMENT_TYPES: DeferredRegister<ArgumentTypeInfo<*, *>?> =
        DeferredRegister.create<ArgumentTypeInfo<*, *>?>(Registries.COMMAND_ARGUMENT_TYPE, MOD_ID)

    val JUTSU_ARGUMENT =
        ARGUMENT_TYPES.registerObject(
            "jutsu")
            {
                ArgumentTypeInfos.registerByClass(
                    JutsuArgument::class.java,
                    SingletonArgumentInfo.contextAware<JutsuArgument?>(Function { context: CommandBuildContext? ->
                        JutsuArgument.Companion.jutsu(context)
                    })
                )
            }

    fun register(eventBus: BusGroup?) {
        ARGUMENT_TYPES.register(eventBus)
    }
}
