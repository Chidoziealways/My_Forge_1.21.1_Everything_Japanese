package net.Chidoziealways.everythingjapanese.fixers

import com.mojang.datafixers.DSL
import com.mojang.datafixers.DataFix
import com.mojang.datafixers.TypeRewriteRule
import com.mojang.datafixers.schemas.Schema
import com.mojang.serialization.Dynamic
import net.minecraft.util.datafix.fixes.References
import java.util.LinkedHashSet

class JutsuRenameAndDeleteFix(schema: Schema, versionKey: Boolean) : DataFix(schema, versionKey) {
    override fun makeRule(): TypeRewriteRule {
        return fixTypeEverywhereTyped(
            "RenameLargeFireball_And_Delete_SmallFireball",
            this.inputSchema.getType(References.PLAYER),
        ) { typed ->
            val root: Dynamic<*> = typed.get(DSL.remainderFinder())

            val updated = root.update("NeoForgeData") { neo ->
                neo.update("everythingjapanese:jutsu_data") { jutsuData ->
                    // Capture T via the extension so generics match exactly
                    jutsuData.renameLargeAndPruneSmall()
                }
            }

            typed.set(DSL.remainderFinder(), updated)
        }
    }
}

/** Do all the work with a consistent backend T so Kotlin stops complaining. */
private fun <T> Dynamic<T>.renameLargeAndPruneSmall(): Dynamic<T> {
    // ---- masteryMap ----
    val emptyMapDyn = Dynamic(this.ops, this.ops.emptyMap())
    val masteryMap = this.get("masteryMap").result().orElse(emptyMapDyn)

    var map = masteryMap

    // If large_fireball exists, move/override to fireball, then remove large_fireball
    map.get("large_fireball").result().ifPresent { value ->
        map = map.set("fireball", value).remove("large_fireball")
    }

    // Always remove small_fireball
    map = map.remove("small_fireball")

    var newData = this.set("masteryMap", map)

    // ---- learnedJutsus ----
    newData = newData.update("learnedJutsus") { learned ->
        val elems = learned.asList { it } // List<Dynamic<T>>
        val out = LinkedHashSet<String>() // preserve order, dedupe

        for (e in elems) {
            when (val s = e.asString("")) {
                "large_fireball" -> out.add("fireball") // rename
                "small_fireball" -> { /* drop */ }
                "" -> { /* skip invalid */ }
                else -> out.add(s) // keep others, including existing "fireball"
            }
        }

        val dyns = out.map { learned.createString(it) }
        learned.createList(dyns.stream())
    }

    return newData
}
