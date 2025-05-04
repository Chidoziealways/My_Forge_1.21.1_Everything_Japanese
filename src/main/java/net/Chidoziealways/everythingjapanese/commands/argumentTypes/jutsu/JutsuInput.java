package net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.serialization.DynamicOps;
import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu;
import net.Chidoziealways.everythingjapanese.jutsu.ModJutsus;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JutsuInput {
    private static final SimpleCommandExceptionType INVALID_JUTSU =
            new SimpleCommandExceptionType(Component.literal("Invalid Jutsu Name!"));

    private final Holder<Jutsu> jutsu;
    private final DataComponentPatch components;

    public JutsuInput(Holder<Jutsu> jutsu, DataComponentPatch pComponents) throws CommandSyntaxException {
        if (jutsu == null || !ModJutsus.isValidJutsu(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, jutsu.value().getName()))) {
            throw INVALID_JUTSU.create();
        }
        this.jutsu = jutsu;
        this.components = pComponents;
    }

    public Holder<Jutsu> getJutsu() {
        return jutsu;
    }

    public String serialize(HolderLookup.Provider pLevelRegistry) {
        StringBuilder stringbuilder = new StringBuilder(this.getJutsuName());
        String s = this.serializeComponents(pLevelRegistry);
        if (!s.isEmpty()) {
            stringbuilder.append('[');
            stringbuilder.append(s);
            stringbuilder.append(']');
        }

        return stringbuilder.toString();
    }

    private String serializeComponents(HolderLookup.Provider pLevelRegistries) {
        DynamicOps<Tag> dynamicops = pLevelRegistries.createSerializationContext(NbtOps.INSTANCE);
        return this.components.entrySet().stream().flatMap(p_340970_ -> {
            DataComponentType<?> datacomponenttype = p_340970_.getKey();
            ResourceLocation resourcelocation = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(datacomponenttype);
            if (resourcelocation == null) {
                return Stream.empty();
            } else {
                Optional<?> optional = p_340970_.getValue();
                if (optional.isPresent()) {
                    TypedDataComponent<?> typeddatacomponent = TypedDataComponent.createUnchecked(datacomponenttype, optional.get());
                    return typeddatacomponent.encodeValue(dynamicops).result().stream().map(p_340968_ -> resourcelocation.toString() + "=" + p_340968_);
                } else {
                    return Stream.of("!" + resourcelocation.toString());
                }
            }
        }).collect(Collectors.joining(String.valueOf(',')));
    }

    private String getJutsuName() {
        return this.jutsu.unwrapKey().<Object>map(ResourceKey::location).orElseGet(() -> "unknown[" + this.jutsu + "]").toString();
    }

}
