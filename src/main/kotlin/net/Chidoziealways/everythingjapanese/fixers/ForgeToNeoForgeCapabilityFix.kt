package net.Chidoziealways.everythingjapanese.fixers

import com.mojang.datafixers.DSL
import com.mojang.datafixers.DataFix
import com.mojang.datafixers.TypeRewriteRule
import com.mojang.datafixers.schemas.Schema
import com.mojang.serialization.Dynamic
import net.minecraft.util.datafix.fixes.References

class ForgeToNeoForgeCapabilityFix(schema: Schema, versionKey: Boolean): DataFix(schema, versionKey) {

    override fun makeRule(): TypeRewriteRule {
        return fixTypeEverywhereTyped(
            "ForgeCapsToNeoForgeDataMigration",
            this.getInputSchema().getType(References.PLAYER),
        ) { typed ->
            // Grab the player compound tag as a Dynamic
            val dynamic: Dynamic<*> = typed.write().result().orElse(null)

            val updated = dynamic.update("ForgeData") { forgeData ->
                if (dynamic.get("NeoForgeData").result().isPresent) {
                    // Already migrated, skip
                    dynamic
                } else {
                    // Copy ForgeData keys under NeoForgeData and remove ForgeData
                    val neoForgeData = forgeData
                    dynamic.remove("ForgeData").set("NeoForgeData", neoForgeData)
                }
            }
            typed.update(DSL.remainderFinder()) { _ -> updated }
        }
    }
}