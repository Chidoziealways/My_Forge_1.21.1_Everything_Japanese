package net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import com.mojang.serialization.DataResult
import it.unimi.dsi.fastutil.objects.ReferenceArraySet
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.commands.SharedSuggestionProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponentPatch
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.component.PatchedDataComponentMap
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.nbt.TagParser
import net.minecraft.network.chat.Component
import net.minecraft.resources.RegistryOps
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.util.Unit
import org.apache.commons.lang3.mutable.MutableObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.Marker
import org.slf4j.MarkerFactory
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier

class JutsuParser(registries: HolderLookup.Provider) {
    val jutsus: RegistryLookup<Jutsu>
    val registryOps: RegistryOps<Tag>
    val tagParser: TagParser<Tag>


    init {
        log.info(marker, "Available Registries: ")
        registries.listRegistryKeys()
            .forEach { resourceKey: ResourceKey<out Registry<*>> -> log.info(marker, "Registry: {}", resourceKey) }

        this.jutsus = registries.lookupOrThrow<Jutsu>(ModRegistries.JUTSU_KEY)
        this.registryOps = registries.createSerializationContext<Tag>(NbtOps.INSTANCE)
        this.tagParser = TagParser.create<Tag>(this.registryOps)
    }

    @Throws(CommandSyntaxException::class)
    fun parse(reader: StringReader): JutsuResult {
        val mutableobject = MutableObject<Holder<Jutsu>>()
        val builder = DataComponentPatch.builder()
        this.parse(reader, object : Visitor {
            override fun visitJutsu(p_328041_: Holder<Jutsu>) {
                mutableobject.setValue(p_328041_)
            }

            override fun <T : Any> visitComponent(pComponentType: DataComponentType<T>, pValue: T) {
                builder.set(pComponentType as DataComponentType<Any>, pValue)
            }

            @Suppress("UNCHECKED_CAST")
            override fun visitRemovedComponent(pComponentType: DataComponentType<*>) {
                builder.remove(pComponentType as DataComponentType<Any>)
            }
        })

        val holder = Objects.requireNonNull<Holder<Jutsu>>(mutableobject.getValue(), "Parser gave no Jutsu")
        val components = builder.build()
        validateComponents(reader, holder, components)
        return JutsuResult(holder, components)
    }

    @Throws(CommandSyntaxException::class)
    fun parse(pReader: StringReader, pVisitor: Visitor) {
        val i = pReader.getCursor()

        try {
            this.State(pReader, pVisitor).parse()
        } catch (commandsyntaxexception: CommandSyntaxException) {
            pReader.setCursor(i)
            throw commandsyntaxexception
        }
    }

    fun fillSuggestions(pBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
        val stringreader = StringReader(pBuilder.getInput())
        stringreader.setCursor(pBuilder.getStart())
        val `itemparser$suggestionsvisitor` = SuggestionsVisitor()
        val `itemparser$state`: State = this.State(stringreader, `itemparser$suggestionsvisitor`)

        try {
            `itemparser$state`.parse()
        } catch (commandsyntaxexception: CommandSyntaxException) {
        }

        return `itemparser$suggestionsvisitor`.resolveSuggestions(pBuilder, stringreader)
    }

    data class JutsuResult(val jutsu: Holder<Jutsu>, val components: DataComponentPatch)

    internal inner class State(private val reader: StringReader, private val visitor: Visitor) {
        @Throws(CommandSyntaxException::class)
        fun parse() {
            this.visitor.visitSuggestions(Function { pBuilder: SuggestionsBuilder -> this.suggestJutsu(pBuilder) })
            this.readJutsu()
            this.visitor.visitSuggestions(Function { pBuilder: SuggestionsBuilder ->
                this.suggestStartComponents(
                    pBuilder
                )
            })
            if (this.reader.canRead() && this.reader.peek() == '[') {
                this.visitor.visitSuggestions(SUGGEST_NOTHING)
                this.readComponents()
            }
        }

        @Throws(CommandSyntaxException::class)
        private fun readJutsu() {
            val i = this.reader.getCursor()
            val Identifier = Identifier.read(this.reader)
            this.visitor.visitJutsu(
                this@JutsuParser.jutsus.get(ResourceKey.create<Jutsu>(ModRegistries.JUTSU_KEY, Identifier))
                    .orElseThrow<CommandSyntaxException>(
                        Supplier {
                            this.reader.setCursor(i)
                            ERROR_UNKNOWN_JUTSU.createWithContext(this.reader, Identifier)
                        })
            )
        }

        @Throws(CommandSyntaxException::class)
        private fun readComponents() {
            this.reader.expect('[')
            this.visitor.visitSuggestions(Function { pBuilder: SuggestionsBuilder ->
                this.suggestComponentAssignmentOrRemoval(
                    pBuilder
                )
            })
            val set: MutableSet<DataComponentType<*>> = ReferenceArraySet<DataComponentType<*>>()

            while (this.reader.canRead() && this.reader.peek() != ']') {
                this.reader.skipWhitespace()
                if (this.reader.canRead() && this.reader.peek() == '!') {
                    this.reader.skip()
                    this.visitor.visitSuggestions(Function { pBuilder: SuggestionsBuilder ->
                        this.suggestComponent(
                            pBuilder
                        )
                    })
                    val datacomponenttype1: DataComponentType<*> = StateCompanion.readComponentType(this.reader)
                    if (!set.add(datacomponenttype1)) {
                        throw ERROR_REPEATED_COMPONENT.create(datacomponenttype1)
                    }

                    this.visitor.visitRemovedComponent(datacomponenttype1 as DataComponentType<Any>)
                    this.visitor.visitSuggestions(SUGGEST_NOTHING)
                    this.reader.skipWhitespace()
                } else {
                    val datacomponenttype: DataComponentType<*> = StateCompanion.readComponentType(this.reader)
                    if (!set.add(datacomponenttype)) {
                        throw ERROR_REPEATED_COMPONENT.create(datacomponenttype)
                    }

                    this.visitor.visitSuggestions(Function { pBuilder: SuggestionsBuilder ->
                        this.suggestAssignment(
                            pBuilder
                        )
                    })
                    this.reader.skipWhitespace()
                    this.reader.expect('=')
                    this.visitor.visitSuggestions(SUGGEST_NOTHING)
                    this.reader.skipWhitespace()
                    this.readComponent(
                        this@JutsuParser.tagParser,
                        this@JutsuParser.registryOps,
                        datacomponenttype as DataComponentType<Any>
                    )
                    this.reader.skipWhitespace()
                }

                this.visitor.visitSuggestions(Function { pBuilder: SuggestionsBuilder ->
                    this.suggestNextOrEndComponents(
                        pBuilder
                    )
                })
                if (!this.reader.canRead() || this.reader.peek() != ',') {
                    break
                }

                this.reader.skip()
                this.reader.skipWhitespace()
                this.visitor.visitSuggestions(Function { pBuilder: SuggestionsBuilder ->
                    this.suggestComponentAssignmentOrRemoval(
                        pBuilder
                    )
                })
                if (!this.reader.canRead()) {
                    throw ERROR_EXPECTED_COMPONENT.createWithContext(this.reader)
                }
            }

            this.reader.expect(']')
            this.visitor.visitSuggestions(SUGGEST_NOTHING)
        }

        @Throws(CommandSyntaxException::class)
        @Suppress("UNCHECKED_CAST")
        private fun <T : Any> readComponent(
            p_397960_: TagParser<Tag>,
            p_394302_: RegistryOps<Tag>,
            p_330643_: DataComponentType<T>
        ) {
            val i = this.reader.getCursor()
            val o = p_397960_.parseAsArgument(this.reader)
            val dataresult = p_330643_.codecOrThrow().parse<Tag>(p_394302_, o)
            this.visitor.visitComponent<T>(
                p_330643_,
                dataresult.getOrThrow<CommandSyntaxException>(Function { p_335662_: String ->
                    this.reader.setCursor(i)
                    ERROR_MALFORMED_COMPONENT.createWithContext(this.reader, p_330643_.toString(), p_335662_)
                })
            )
        }

        private fun suggestStartComponents(pBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
            if (pBuilder.getRemaining().isEmpty()) {
                pBuilder.suggest('['.toString())
            }

            return pBuilder.buildFuture()
        }

        private fun suggestNextOrEndComponents(pBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
            if (pBuilder.getRemaining().isEmpty()) {
                pBuilder.suggest(','.toString())
                pBuilder.suggest(']'.toString())
            }

            return pBuilder.buildFuture()
        }

        private fun suggestAssignment(pBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
            if (pBuilder.getRemaining().isEmpty()) {
                pBuilder.suggest('='.toString())
            }

            return pBuilder.buildFuture()
        }

        private fun suggestJutsu(pBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
            return SharedSuggestionProvider.suggestResource(
                this@JutsuParser.jutsus.listElementIds()
                    .map<Identifier> { obj: ResourceKey<Jutsu> -> obj.registry() }, pBuilder
            )
        }

        private fun suggestComponentAssignmentOrRemoval(pBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
            pBuilder.suggest('!'.toString())
            return this.suggestComponent(pBuilder, '='.toString())
        }

        private fun suggestComponent(
            pBuilder: SuggestionsBuilder,
            pSuffix: String = ""
        ): CompletableFuture<Suggestions> {
            val s = pBuilder.getRemaining().lowercase()
            SharedSuggestionProvider.filterResources<MutableMap.MutableEntry<ResourceKey<DataComponentType<*>>, DataComponentType<*>>>(
                BuiltInRegistries.DATA_COMPONENT_TYPE.entrySet(),
                s,
                { p_328035_: MutableMap.MutableEntry<ResourceKey<DataComponentType<*>>, DataComponentType<*>> -> p_328035_.key.registry() },
                { p_340973_: MutableMap.MutableEntry<ResourceKey<DataComponentType<*>>, DataComponentType<*>> ->
                    val datacomponenttype: DataComponentType<*> = p_340973_.value
                    if (datacomponenttype.codec() != null) {
                        val Identifier = p_340973_.key.registry()
                        pBuilder.suggest(Identifier.toString() + pSuffix)
                    }
                })
            return pBuilder.buildFuture()
        }
    }

    internal object StateCompanion {
        @Throws(CommandSyntaxException::class)
        fun readComponentType(pReader: StringReader): DataComponentType<*> {
            if (!pReader.canRead()) {
                throw ERROR_EXPECTED_COMPONENT.createWithContext(pReader)
            } else {
                val i = pReader.getCursor()
                val Identifier = Identifier.read(pReader)
                val datacomponenttype = BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(Identifier)
                if (datacomponenttype != null && !datacomponenttype.isTransient()) {
                    return datacomponenttype
                } else {
                    pReader.setCursor(i)
                    throw ERROR_UNKNOWN_COMPONENT.createWithContext(pReader, Identifier)
                }
            }
        }
    }

    internal class SuggestionsVisitor : Visitor {
        private var suggestions: Function<SuggestionsBuilder, CompletableFuture<Suggestions>> = SUGGEST_NOTHING

        override fun visitSuggestions(pSuggestions: Function<SuggestionsBuilder, CompletableFuture<Suggestions>>) {
            this.suggestions = pSuggestions
        }

        fun resolveSuggestions(pBuilder: SuggestionsBuilder, pReader: StringReader): CompletableFuture<Suggestions> {
            return this.suggestions.apply(pBuilder.createOffset(pReader.getCursor()))
        }
    }

    interface Visitor {
        fun visitJutsu(pJutsu: Holder<Jutsu>) {
        }

        fun <T : Any> visitComponent(pComponentType: DataComponentType<T>, pValue: T) {
        }

        fun visitRemovedComponent(pComponentType: DataComponentType<*>) {
        }

        fun visitSuggestions(pSuggestions: Function<SuggestionsBuilder, CompletableFuture<Suggestions>>) {
        }
    }

    companion object {
        val ERROR_UNKNOWN_JUTSU: DynamicCommandExceptionType = DynamicCommandExceptionType(
            Function { p_308407_: Any -> Component.translatableEscape("argument.jutsu.id.invalid", p_308407_) }
        )
        val ERROR_UNKNOWN_COMPONENT: DynamicCommandExceptionType = DynamicCommandExceptionType(
            Function { p_308406_: Any -> Component.translatableEscape("arguments.jutsu.component.unknown", p_308406_) }
        )

        val ERROR_MALFORMED_COMPONENT: Dynamic2CommandExceptionType = Dynamic2CommandExceptionType(
            Dynamic2CommandExceptionType.Function { p_325613_: Any, p_325614_: Any ->
                Component.translatableEscape(
                    "arguments.jutsu.component.malformed",
                    p_325613_,
                    p_325614_
                )
            })

        val ERROR_EXPECTED_COMPONENT: SimpleCommandExceptionType =
            SimpleCommandExceptionType(Component.translatable("arguments.jutsu.component.expected"))
        val ERROR_REPEATED_COMPONENT: DynamicCommandExceptionType = DynamicCommandExceptionType(
            Function { p_325615_: Any ->
                Component.translatableEscape(
                    "arguments.jutsu.component.repeated",
                    p_325615_
                )
            }
        )
        private val ERROR_MALFORMED_JUTSU = DynamicCommandExceptionType(
            Function { p_325616_: Any -> Component.translatableEscape("arguments.jutsu.malformed", p_325616_) }
        )
        val SUGGEST_NOTHING: Function<SuggestionsBuilder, CompletableFuture<Suggestions>> =
            Function { obj: SuggestionsBuilder -> obj.buildFuture() }

        const val SYNTAX_START_COMPONENTS: Char = '['
        const val SYNTAX_END_COMPONENTS: Char = ']'
        const val SYNTAX_COMPONENT_SEPARATOR: Char = ','
        const val SYNTAX_COMPONENT_ASSIGNMENT: Char = '='
        const val SYNTAX_REMOVED_COMPONENT: Char = '!'
        private val log: Logger = LoggerFactory.getLogger(JutsuParser::class.java)
        private val marker: Marker = MarkerFactory.getMarker("JUTSUPARSER")

        @Throws(CommandSyntaxException::class)
        private fun validateComponents(pReader: StringReader, jutsu: Holder<Jutsu>, pComponents: DataComponentPatch) {
            val datacomponentmap: DataComponentMap =
                PatchedDataComponentMap.fromPatch(jutsu.value().components(), pComponents)
            val dataresult: DataResult<Unit> = Jutsu.Companion.validateComponents(datacomponentmap)
            dataresult.getOrThrow<CommandSyntaxException>(Function { p_325612_: String ->
                ERROR_MALFORMED_JUTSU.createWithContext(
                    pReader,
                    p_325612_
                )
            })
        }
    }
}
