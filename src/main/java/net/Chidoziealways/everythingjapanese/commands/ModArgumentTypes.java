package net.Chidoziealways.everythingjapanese.commands;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu.JutsuArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModArgumentTypes {
    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ARGUMENT_TYPES =
            DeferredRegister.create(Registries.COMMAND_ARGUMENT_TYPE, EverythingJapanese.MOD_ID);

    public static final RegistryObject<ArgumentTypeInfo<JutsuArgument, ?>> JUTSU_ARGUMENT =
            ARGUMENT_TYPES.register("jutsu", () -> ArgumentTypeInfos.registerByClass(JutsuArgument.class, SingletonArgumentInfo.contextAware(JutsuArgument::jutsu)));

    public static void register(IEventBus eventBus) {
        ARGUMENT_TYPES.register(eventBus);
    }
}
