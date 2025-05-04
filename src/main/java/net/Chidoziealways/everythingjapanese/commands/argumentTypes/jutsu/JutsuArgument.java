package net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu;
import net.Chidoziealways.everythingjapanese.util.ModRegistries;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class JutsuArgument implements ArgumentType<JutsuInput> {
    private static final Collection<String> EXAMPLES = Arrays.asList("everythingjapanese:small_fireball", "everythingjapanese:large_fireball", "everythingjapanese:small_windball");
    private static final Logger log = LogManager.getLogger(JutsuArgument.class);
    private static final Marker marker = MarkerManager.getMarker("JUTSUARGUMENT");
    private final JutsuParser parser;

    public JutsuArgument(CommandBuildContext context) {
        this.parser = new JutsuParser(context);
    }

    public static JutsuArgument jutsu(CommandBuildContext context) {
        context.listRegistries();
        log.info(marker, "Creating Jutsu");
        // Debug registry lookup
        context.listRegistries().forEach(registryLookup -> {
            log.info(marker, "Registries: {}", registryLookup.key());
        });
        return new JutsuArgument(context);
    }

    public static JutsuInput getJutsu(CommandContext<CommandSourceStack> context, String name) {
        return context.getArgument(name, JutsuInput.class);
    }

    public JutsuInput parse(StringReader reader) throws CommandSyntaxException {
        JutsuParser.JutsuResult jutsuResult = this.parser.parse(reader);
        return new JutsuInput(jutsuResult.jutsu(), jutsuResult.components());
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return this.parser.fillSuggestions(builder);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}
