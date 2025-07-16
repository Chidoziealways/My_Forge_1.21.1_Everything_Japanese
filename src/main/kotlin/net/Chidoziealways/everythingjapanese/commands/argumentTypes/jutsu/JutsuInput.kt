package net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import com.mojang.serialization.DynamicOps
import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.jutsu.ModJutsus
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponentPatch
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.component.TypedDataComponent
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import java.util.*
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collectors
import java.util.stream.Stream

class JutsuInput(jutsu: Holder<Jutsu?>?, pComponents: DataComponentPatch?) {
    private val jutsu: Holder<Jutsu?>
    private val components: DataComponentPatch

    init {
        if (jutsu == null || !ModJutsus.isValidJutsu(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, jutsu.value()?.name)
            )
        ) {
            throw INVALID_JUTSU.create()
        }
        this.jutsu = jutsu
        this.components = pComponents!!
    }

    fun getJutsu(): Holder<Jutsu?> {
        return jutsu
    }

    fun serialize(pLevelRegistry: HolderLookup.Provider): String {
        val stringbuilder = StringBuilder(this.getJutsuName())
        val s = this.serializeComponents(pLevelRegistry)
        if (!s.isEmpty()) {
            stringbuilder.append('[')
            stringbuilder.append(s)
            stringbuilder.append(']')
        }

        return stringbuilder.toString()
    }

    private fun serializeComponents(pLevelRegistries: HolderLookup.Provider): String {
        val dynamicops: DynamicOps<Tag?> = pLevelRegistries.createSerializationContext<Tag?>(NbtOps.INSTANCE)
        return this.components.entrySet().stream()
            .flatMap<String?> { p_340970_: MutableMap.MutableEntry<DataComponentType<*>?, Optional<*>?>? ->
                val datacomponenttype: DataComponentType<*> = p_340970_!!.key!!
                val resourcelocation = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(datacomponenttype)
                if (resourcelocation == null) {
                    return@flatMap Stream.empty<String?>()
                } else {
                    val optional: Optional<*> = p_340970_.value!!
                    if (optional.isPresent()) {
                        val typeddatacomponent: TypedDataComponent<*> =
                            TypedDataComponent.createUnchecked(datacomponenttype, optional.get())
                        return@flatMap typeddatacomponent.encodeValue<Tag?>(dynamicops).result().stream()
                            .map<String?> { p_340968_: Tag? -> resourcelocation.toString() + "=" + p_340968_ }
                    } else {
                        return@flatMap Stream.of<String?>("!" + resourcelocation.toString())
                    }
                }
            }.collect(Collectors.joining(','.toString()))
    }

    private fun getJutsuName(): String {
        return this.jutsu.unwrapKey().map<Any?>(Function { obj: ResourceKey<Jutsu?>? -> obj!!.location() }).orElseGet(
            Supplier { "unknown[" + this.jutsu + "]" }).toString()
    }

    companion object {
        private val INVALID_JUTSU = SimpleCommandExceptionType(Component.literal("Invalid Jutsu Name!"))
    }
}
