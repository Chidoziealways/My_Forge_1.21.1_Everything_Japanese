package net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.DataResult;
import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu;
import net.Chidoziealways.everythingjapanese.util.ModRegistries;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import org.apache.commons.lang3.mutable.MutableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class JutsuParser {
    static final DynamicCommandExceptionType ERROR_UNKNOWN_JUTSU = new DynamicCommandExceptionType(
            p_308407_ -> Component.translatableEscape("argument.jutsu.id.invalid", p_308407_)
    );
    static final DynamicCommandExceptionType ERROR_UNKNOWN_COMPONENT = new DynamicCommandExceptionType(
            p_308406_ -> Component.translatableEscape("arguments.jutsu.component.unknown", p_308406_)
    );

    static final Dynamic2CommandExceptionType ERROR_MALFORMED_COMPONENT = new Dynamic2CommandExceptionType(
            (p_325613_, p_325614_) -> Component.translatableEscape("arguments.jutsu.component.malformed", p_325613_, p_325614_));

    static final SimpleCommandExceptionType ERROR_EXPECTED_COMPONENT = new SimpleCommandExceptionType(Component.translatable("arguments.jutsu.component.expected"));
    static final DynamicCommandExceptionType ERROR_REPEATED_COMPONENT = new DynamicCommandExceptionType(
            p_325615_ -> Component.translatableEscape("arguments.jutsu.component.repeated", p_325615_)
    );
    private static final DynamicCommandExceptionType ERROR_MALFORMED_JUTSU = new DynamicCommandExceptionType(
            p_325616_ -> Component.translatableEscape("arguments.jutsu.malformed", p_325616_)
    );
    static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> SUGGEST_NOTHING = SuggestionsBuilder::buildFuture;

    public static final char SYNTAX_START_COMPONENTS = '[';
    public static final char SYNTAX_END_COMPONENTS = ']';
    public static final char SYNTAX_COMPONENT_SEPARATOR = ',';
    public static final char SYNTAX_COMPONENT_ASSIGNMENT = '=';
    public static final char SYNTAX_REMOVED_COMPONENT = '!';
    private static final Logger log = LoggerFactory.getLogger(JutsuParser.class);
    private static final Marker marker = MarkerFactory.getMarker("JUTSUPARSER");
    final HolderLookup.RegistryLookup<Jutsu> jutsus;
    final RegistryOps<Tag> registryOps;
    final TagParser<Tag> tagParser;


    public JutsuParser(HolderLookup.Provider registries) {
        log.info(marker, "Available Registries: ");
        registries.listRegistryKeys().forEach(resourceKey -> log.info(marker, "Registry: {}", resourceKey));

        this.jutsus = registries.lookupOrThrow(ModRegistries.JUTSU);
        this.registryOps = registries.createSerializationContext(NbtOps.INSTANCE);
        this.tagParser = TagParser.create(this.registryOps);
    }

    public JutsuResult parse(StringReader reader) throws CommandSyntaxException {
        final MutableObject<Holder<Jutsu>> mutableobject = new MutableObject<>();
        DataComponentPatch.Builder builder = DataComponentPatch.builder();
        this.parse(reader, new JutsuParser.Visitor() {
            @Override
            public void visitJutsu(Holder<Jutsu> p_328041_) {
                mutableobject.setValue(p_328041_);
            }

            @Override
            public <T> void visitComponent(DataComponentType<T> p_331133_, T p_330958_) {
                builder.set(p_331133_, p_330958_);
            }

            @Override
            public <T> void visitRemovedComponent(DataComponentType<T> p_342833_) {
                builder.remove(p_342833_);
            }
        });

        Holder<Jutsu> holder = Objects.requireNonNull(mutableobject.getValue(), "Parser gave no Jutsu");
        DataComponentPatch components = builder.build();
        validateComponents(reader, holder, components);
        return new JutsuResult(holder, components);
    }

    private static void validateComponents(StringReader pReader, Holder<Jutsu> jutsu, DataComponentPatch pComponents) throws CommandSyntaxException {
        DataComponentMap datacomponentmap = PatchedDataComponentMap.fromPatch(jutsu.value().components(), pComponents);
        DataResult<Unit> dataresult = Jutsu.validateComponents(datacomponentmap);
        dataresult.getOrThrow(p_325612_ -> ERROR_MALFORMED_JUTSU.createWithContext(pReader, p_325612_));
    }

    public void parse(StringReader pReader, JutsuParser.Visitor pVisitor) throws CommandSyntaxException {
        int i = pReader.getCursor();

        try {
            new JutsuParser.State(pReader, pVisitor).parse();
        } catch (CommandSyntaxException commandsyntaxexception) {
            pReader.setCursor(i);
            throw commandsyntaxexception;
        }
    }

    public CompletableFuture<Suggestions> fillSuggestions(SuggestionsBuilder pBuilder) {
        StringReader stringreader = new StringReader(pBuilder.getInput());
        stringreader.setCursor(pBuilder.getStart());
        JutsuParser.SuggestionsVisitor itemparser$suggestionsvisitor = new JutsuParser.SuggestionsVisitor();
        JutsuParser.State itemparser$state = new JutsuParser.State(stringreader, itemparser$suggestionsvisitor);

        try {
            itemparser$state.parse();
        } catch (CommandSyntaxException commandsyntaxexception) {
        }

        return itemparser$suggestionsvisitor.resolveSuggestions(pBuilder, stringreader);
    }


    public record JutsuResult(Holder<Jutsu> jutsu, DataComponentPatch components) {
    }

    class State {
        private final StringReader reader;
        private final JutsuParser.Visitor visitor;

        State(final StringReader pReader, final JutsuParser.Visitor pVisitor) {
            this.reader = pReader;
            this.visitor = pVisitor;
        }

        public void parse() throws CommandSyntaxException {
            this.visitor.visitSuggestions(this::suggestJutsu);
            this.readJutsu();
            this.visitor.visitSuggestions(this::suggestStartComponents);
            if (this.reader.canRead() && this.reader.peek() == '[') {
                this.visitor.visitSuggestions(JutsuParser.SUGGEST_NOTHING);
                this.readComponents();
            }
        }

        private void readJutsu() throws CommandSyntaxException {
            int i = this.reader.getCursor();
            ResourceLocation resourcelocation = ResourceLocation.read(this.reader);
            this.visitor.visitJutsu(JutsuParser.this.jutsus.get(ResourceKey.create(ModRegistries.JUTSU, resourcelocation)).orElseThrow(() -> {
                this.reader.setCursor(i);
                return JutsuParser.ERROR_UNKNOWN_JUTSU.createWithContext(this.reader, resourcelocation);
            }));
        }

        private void readComponents() throws CommandSyntaxException {
            this.reader.expect('[');
            this.visitor.visitSuggestions(this::suggestComponentAssignmentOrRemoval);
            Set<DataComponentType<?>> set = new ReferenceArraySet<>();

            while (this.reader.canRead() && this.reader.peek() != ']') {
                this.reader.skipWhitespace();
                if (this.reader.canRead() && this.reader.peek() == '!') {
                    this.reader.skip();
                    this.visitor.visitSuggestions(this::suggestComponent);
                    DataComponentType<?> datacomponenttype1 = readComponentType(this.reader);
                    if (!set.add(datacomponenttype1)) {
                        throw JutsuParser.ERROR_REPEATED_COMPONENT.create(datacomponenttype1);
                    }

                    this.visitor.visitRemovedComponent(datacomponenttype1);
                    this.visitor.visitSuggestions(JutsuParser.SUGGEST_NOTHING);
                    this.reader.skipWhitespace();
                } else {
                    DataComponentType<?> datacomponenttype = readComponentType(this.reader);
                    if (!set.add(datacomponenttype)) {
                        throw JutsuParser.ERROR_REPEATED_COMPONENT.create(datacomponenttype);
                    }

                    this.visitor.visitSuggestions(this::suggestAssignment);
                    this.reader.skipWhitespace();
                    this.reader.expect('=');
                    this.visitor.visitSuggestions(JutsuParser.SUGGEST_NOTHING);
                    this.reader.skipWhitespace();
                    this.readComponent(JutsuParser.this.tagParser, JutsuParser.this.registryOps, datacomponenttype);
                    this.reader.skipWhitespace();
                }

                this.visitor.visitSuggestions(this::suggestNextOrEndComponents);
                if (!this.reader.canRead() || this.reader.peek() != ',') {
                    break;
                }

                this.reader.skip();
                this.reader.skipWhitespace();
                this.visitor.visitSuggestions(this::suggestComponentAssignmentOrRemoval);
                if (!this.reader.canRead()) {
                    throw JutsuParser.ERROR_EXPECTED_COMPONENT.createWithContext(this.reader);
                }
            }

            this.reader.expect(']');
            this.visitor.visitSuggestions(JutsuParser.SUGGEST_NOTHING);
        }

        public static DataComponentType<?> readComponentType(StringReader pReader) throws CommandSyntaxException {
            if (!pReader.canRead()) {
                throw JutsuParser.ERROR_EXPECTED_COMPONENT.createWithContext(pReader);
            } else {
                int i = pReader.getCursor();
                ResourceLocation resourcelocation = ResourceLocation.read(pReader);
                DataComponentType<?> datacomponenttype = BuiltInRegistries.DATA_COMPONENT_TYPE.getValue(resourcelocation);
                if (datacomponenttype != null && !datacomponenttype.isTransient()) {
                    return datacomponenttype;
                } else {
                    pReader.setCursor(i);
                    throw JutsuParser.ERROR_UNKNOWN_COMPONENT.createWithContext(pReader, resourcelocation);
                }
            }
        }

        private <T, O> void readComponent(TagParser<O> p_397960_, RegistryOps<O> p_394302_, DataComponentType<T> p_330643_) throws CommandSyntaxException {
            int i = this.reader.getCursor();
            O o = p_397960_.parseAsArgument(this.reader);
            DataResult<T> dataresult = p_330643_.codecOrThrow().parse(p_394302_, o);
            this.visitor.visitComponent(p_330643_, dataresult.getOrThrow(p_335662_ -> {
                this.reader.setCursor(i);
                return JutsuParser.ERROR_MALFORMED_COMPONENT.createWithContext(this.reader, p_330643_.toString(), p_335662_);
            }));
        }

        private CompletableFuture<Suggestions> suggestStartComponents(SuggestionsBuilder pBuilder) {
            if (pBuilder.getRemaining().isEmpty()) {
                pBuilder.suggest(String.valueOf('['));
            }

            return pBuilder.buildFuture();
        }

        private CompletableFuture<Suggestions> suggestNextOrEndComponents(SuggestionsBuilder pBuilder) {
            if (pBuilder.getRemaining().isEmpty()) {
                pBuilder.suggest(String.valueOf(','));
                pBuilder.suggest(String.valueOf(']'));
            }

            return pBuilder.buildFuture();
        }

        private CompletableFuture<Suggestions> suggestAssignment(SuggestionsBuilder pBuilder) {
            if (pBuilder.getRemaining().isEmpty()) {
                pBuilder.suggest(String.valueOf('='));
            }

            return pBuilder.buildFuture();
        }

        private CompletableFuture<Suggestions> suggestJutsu(SuggestionsBuilder pBuilder) {
            return SharedSuggestionProvider.suggestResource(JutsuParser.this.jutsus.listElementIds().map(ResourceKey::location), pBuilder);
        }

        private CompletableFuture<Suggestions> suggestComponentAssignmentOrRemoval(SuggestionsBuilder pBuilder) {
            pBuilder.suggest(String.valueOf('!'));
            return this.suggestComponent(pBuilder, String.valueOf('='));
        }

        private CompletableFuture<Suggestions> suggestComponent(SuggestionsBuilder pBuilder) {
            return this.suggestComponent(pBuilder, "");
        }

        private CompletableFuture<Suggestions> suggestComponent(SuggestionsBuilder pBuilder, String pSuffix) {
            String s = pBuilder.getRemaining().toLowerCase(Locale.ROOT);
            SharedSuggestionProvider.filterResources(BuiltInRegistries.DATA_COMPONENT_TYPE.entrySet(), s, p_328035_ -> p_328035_.getKey().location(), p_340973_ -> {
                DataComponentType<?> datacomponenttype = p_340973_.getValue();
                if (datacomponenttype.codec() != null) {
                    ResourceLocation resourcelocation = p_340973_.getKey().location();
                    pBuilder.suggest(resourcelocation + pSuffix);
                }
            });
            return pBuilder.buildFuture();
        }
    }

    static class SuggestionsVisitor implements JutsuParser.Visitor {
        private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestions = JutsuParser.SUGGEST_NOTHING;

        @Override
        public void visitSuggestions(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> p_328999_) {
            this.suggestions = p_328999_;
        }

        public CompletableFuture<Suggestions> resolveSuggestions(SuggestionsBuilder pBuilder, StringReader pReader) {
            return this.suggestions.apply(pBuilder.createOffset(pReader.getCursor()));
        }
    }

    public interface Visitor {
        default void visitJutsu(Holder<Jutsu> pJutsu) {
        }

        default <T> void visitComponent(DataComponentType<T> pComponentType, T pValue) {
        }

        default <T> void visitRemovedComponent(DataComponentType<T> pComponentType) {
        }

        default void visitSuggestions(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> pSuggestions) {
        }
    }
}
