package net.Chidoziealways.everythingjapanese.commands.argumentTypes.jutsu

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.core.HolderLookup.RegistryLookup
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.Marker
import org.apache.logging.log4j.MarkerManager
import java.util.concurrent.CompletableFuture

class JutsuArgument(context: CommandBuildContext) : ArgumentType<JutsuInput?> {
    private val parser: JutsuParser = JutsuParser(context)

    @Throws(CommandSyntaxException::class)
    override fun parse(reader: StringReader): JutsuInput {
        val jutsuResult = this.parser.parse(reader)
        return JutsuInput(jutsuResult.jutsu, jutsuResult.components)
    }

    override fun <S> listSuggestions(
        context: CommandContext<S?>?,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions?>? {
        return this.parser.fillSuggestions(builder)
    }

    override fun getExamples(): MutableCollection<String?> {
        return EXAMPLES
    }

    companion object {
        private val EXAMPLES: MutableCollection<String?> = mutableListOf<String?>(
            "everythingjapanese:small_fireball",
            "everythingjapanese:large_fireball",
            "everythingjapanese:small_windball"
        )
        private val log: Logger = LogManager.getLogger(JutsuArgument::class.java)
        private val marker: Marker? = MarkerManager.getMarker("JUTSUARGUMENT")
        fun jutsu(context: CommandBuildContext?): JutsuArgument {
            context!!.listRegistries()
            log.info(marker, "Creating Jutsu")
            // Debug registry lookup
            context.listRegistries().forEach { registryLookup: RegistryLookup<*>? ->
                log.info(marker, "Registries: {}", registryLookup!!.key())
            }
            return JutsuArgument(context)
        }

        fun getJutsu(context: CommandContext<CommandSourceStack?>, name: String?): JutsuInput? {
            return context.getArgument<JutsuInput?>(name, JutsuInput::class.java)
        }
    }
}
