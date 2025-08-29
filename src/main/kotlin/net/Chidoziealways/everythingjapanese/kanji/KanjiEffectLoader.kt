package net.Chidoziealways.everythingjapanese.kanji

import it.unimi.dsi.fastutil.Pair
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.packs.resources.Resource
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.PreparableReloadListener.PreparationBarrier
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.OneArgFunction
import org.luaj.vm2.lib.ThreeArgFunction
import org.luaj.vm2.lib.TwoArgFunction
import org.luaj.vm2.lib.jse.CoerceJavaToLua
import org.luaj.vm2.lib.jse.CoerceLuaToJava
import org.luaj.vm2.lib.jse.JsePlatform
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.BiFunction

object KanjiEffectLoader {

    val MODULES_PATH = ResourceLocation.fromNamespaceAndPath(MOD_ID, "scripts/modules")
    val BLOCK_EFFECTS_PATH = ResourceLocation.fromNamespaceAndPath(MOD_ID, "scripts/effects/block")
    val ENTITY_EFFECTS_PATH = ResourceLocation.fromNamespaceAndPath(MOD_ID, "scripts/effects/entity")

    private val globals by lazy { JsePlatform.standardGlobals() }

    private var KANJI_BLOCK_EFFECTS: Map<ResourceLocation, KanjiBlockEffect> = emptyMap()
    private var KANJI_ENTITY_EFFECTS: Map<ResourceLocation, KanjiEntityEffect> = emptyMap()

    fun getBlockEffect(effectId: ResourceLocation): KanjiBlockEffect? = KANJI_BLOCK_EFFECTS[effectId]
    fun getBlockEffectOrEmpty(effectId: ResourceLocation): KanjiBlockEffect = KANJI_BLOCK_EFFECTS[effectId] ?: KanjiBlockEffect { _, _, _-> }

    fun getEntityEffect(effectId: ResourceLocation): KanjiEntityEffect? = KANJI_ENTITY_EFFECTS[effectId]
    fun getEntityEffectOrEmpty(effectId: ResourceLocation): KanjiEntityEffect = KANJI_ENTITY_EFFECTS[effectId] ?: KanjiEntityEffect { _, _, _, _-> }

    data class KanjiBlockEffectDef(
        val id: ResourceLocation,
        val luaFunc: LuaValue
    )

    data class KanjiEntityEffectDef(
        val id: ResourceLocation,
        val luaFunc: LuaValue
    )

    fun reload(
        stage: PreparationBarrier,
        resourceManager: ResourceManager,
        backgroundExecutor: Executor,
        gameExecutor: Executor
    ): CompletableFuture<Void> {
        setupLuaGlobals()

        val modulesFuture = preloadModules(backgroundExecutor, resourceManager)
        val blockEffectsFuture = loadBlockEffects(backgroundExecutor, resourceManager)
        val entityEffectsFuture = loadEntityEffects(backgroundExecutor, resourceManager)

        return CompletableFuture.allOf(blockEffectsFuture, entityEffectsFuture, modulesFuture)
            .thenCompose(stage::wait)
            .thenRunAsync({
                val blockDefs = blockEffectsFuture.join()
                val entityDefs = entityEffectsFuture.join()

                // ✅ Safe: now we’re on the main thread
                KANJI_BLOCK_EFFECTS = blockDefs.mapValues { (id, def) ->
                    coerceToKanjiBlockEffect(id, def.luaFunc)
                }

                KANJI_ENTITY_EFFECTS = entityDefs.mapValues { (id, def) ->
                    coerceToKanjiEntityEffect(id, def.luaFunc)
                }

                println("✅ Loaded Kanji Lua Effects: $KANJI_BLOCK_EFFECTS")
            }, gameExecutor)
    }

    private fun preloadModules(executor: Executor, resourceManager: ResourceManager): CompletableFuture<Void> {
        return loadResources(executor, resourceManager, MODULES_PATH.path, "lua") { _, res -> res }
            .thenApply { modules ->
                val preload = globals.get("package").get("preload")
                modules.forEach { pair ->
                    val path = pair.first().path
                        .replace('\\', '/')
                        .removePrefix("scripts/modules/")
                        .removeSuffix(".lua")
                    val chunk = globals.load(readLua(pair.second()))
                    preload.set(path, chunk)
                }
                null
            }
    }

    private fun loadBlockEffects(
        executor: Executor,
        resourceManager: ResourceManager
    ): CompletableFuture<Map<ResourceLocation, KanjiBlockEffectDef>> {
        return loadResources(executor, resourceManager, BLOCK_EFFECTS_PATH.path, "lua") { id, res -> id to res }
            .thenApply { list ->
                list.associate { pair ->
                    println("Loading Effects!")
                    val luaFunc = globals.load(readLua(pair.second().second))
                    println("After luaFunc")

                    stripPrefixAndSuffix(pair.first()) to KanjiBlockEffectDef(
                        stripPrefixAndSuffix(pair.first()),
                        luaFunc
                    )
                }
            }
    }

    private fun loadEntityEffects(
        executor: Executor,
        resourceManager: ResourceManager
    ): CompletableFuture<Map<ResourceLocation, KanjiEntityEffectDef>> {
        return loadResources(executor, resourceManager, ENTITY_EFFECTS_PATH.path, "lua") { id, res -> id to res }
            .thenApply { list ->
                list.associate { pair ->
                    println("Loading Effects!")
                    val luaFunc = globals.load(readLua(pair.second().second))
                    println("After luaFunc")

                    stripPrefixAndSuffix(pair.first()) to KanjiEntityEffectDef(
                        stripPrefixAndSuffix(pair.first()),
                        luaFunc
                    )
                }
            }
    }


    private fun <T> loadResources(
        executor: Executor,
        resourceManager: ResourceManager,
        assetPath: String,
        fileExtension: String,
        elementFactory: BiFunction<ResourceLocation, Resource, T>
    ): CompletableFuture<List<Pair<ResourceLocation, T>>> {
        val suffix = ".$fileExtension"

        println("Loading Resources!")

        return CompletableFuture.supplyAsync(
            { resourceManager.listResources(assetPath) { it.path.endsWith(suffix) } },
            executor
        ).thenCompose { resources ->
            val tasks = ObjectArrayList<CompletableFuture<Pair<ResourceLocation, T>>>(resources.size)

            println("Running .thenCompose")

            resources.forEach { (id, resource) ->
                tasks.add(CompletableFuture.supplyAsync({ Pair.of(id, elementFactory.apply(id, resource)) }, executor))
                println("Added Tasks")
            }

            // ✅ Wrap the `allOf` future in a typed future
            val allDoneFuture: CompletableFuture<List<Pair<ResourceLocation, T>>> =
                CompletableFuture.allOf(*tasks.toTypedArray())
                    .thenApply {
                        tasks.map { it.join() }
                    }

            allDoneFuture
        }
    }



    private fun readLua(resource: Resource): String {
        resource.open().use { input ->
            BufferedReader(InputStreamReader(input)).use { reader ->
                return reader.readText()
            }
        }
    }

    private fun coerceToKanjiBlockEffect(id: ResourceLocation, chunk: LuaValue): KanjiBlockEffect {
        println("Coercing $id")

        // DO NOT call the chunk here; defer execution until runtime
        return KanjiBlockEffect { level, pos, player ->
            try {
                // call the chunk at runtime
                val luaFunc = chunk.call()
                if (!luaFunc.isfunction()) throw IllegalArgumentException(
                    "Lua script at '$id' must return a function(level, pos, player, target)."
                )

                val args = LuaValue.varargsOf(
                    arrayOf(
                        CoerceJavaToLua.coerce(level),
                        CoerceJavaToLua.coerce(pos),
                        CoerceJavaToLua.coerce(player)
                    )
                )

                luaFunc.invoke(args)
            } catch (e: Exception) {
                println("❌ Error running Lua KanjiEffect '$id': ${e.message}")
            }
        }
    }

    private fun coerceToKanjiEntityEffect(id: ResourceLocation, chunk: LuaValue): KanjiEntityEffect {
        println("Coercing $id")

        // DO NOT call the chunk here; defer execution until runtime
        return KanjiEntityEffect { level, pos, player, target ->
            try {
                // call the chunk at runtime
                val luaFunc = chunk.call()
                if (!luaFunc.isfunction()) throw IllegalArgumentException(
                    "Lua script at '$id' must return a function(level, pos, player, target)."
                )

                val args = LuaValue.varargsOf(
                    arrayOf(
                        CoerceJavaToLua.coerce(level),
                        CoerceJavaToLua.coerce(pos),
                        CoerceJavaToLua.coerce(player),
                        CoerceJavaToLua.coerce(target)
                    )
                )

                luaFunc.invoke(args)
            } catch (e: Exception) {
                println("❌ Error running Lua KanjiEffect '$id': ${e.message}")
            }
        }
    }

    private fun stripPrefixAndSuffix(id: ResourceLocation): ResourceLocation {
        val parts = id.path.split("/")
        // Drop first 2 folders
        val strippedPath = if (parts.size == 2) parts.drop(2).joinToString("/") else if (parts.size == 3) parts.drop(3).joinToString("/") else parts.last()
        // Remove file extension if any
        val pathWithoutSuffix = strippedPath.substringBeforeLast(".")
        return id.withPath(pathWithoutSuffix)
    }

    private fun setupLuaGlobals() {
        // Expose utility functions and APIs to Lua scripts
        globals.set("getBlock", object : OneArgFunction(){
            override fun call(arg: LuaValue): LuaValue {
                val block = BuiltInRegistries.BLOCK.getValue(getResourceLocation(arg.checkjstring()))
                return CoerceJavaToLua.coerce(block)
            }
        })

        globals.set("getItem", object : OneArgFunction() {
            override fun call(arg: LuaValue): LuaValue {
                val item = BuiltInRegistries.ITEM.getValue(getResourceLocation(arg.checkjstring()))
                return CoerceJavaToLua.coerce(item)
            }
        })

        globals.set("getBlockLock", object : TwoArgFunction() {
            override fun call(arg1: LuaValue, arg2: LuaValue): LuaValue {
                val level = CoerceLuaToJava.coerce(arg1, Level::class.java) as Level
                val pos = CoerceLuaToJava.coerce(arg2, BlockPos::class.java) as BlockPos
                return CoerceJavaToLua.coerce(level.getCapability(ModCapabilities.LOCK_CAPABILITY, pos))
            }
        })

        globals.set("literalComponent", object : OneArgFunction() {
            override fun call(arg: LuaValue): LuaValue {
                val message = arg.checkjstring()
                return CoerceJavaToLua.coerce(Component.literal(message))
            }
        })

        globals.set("translatableComponent", object : OneArgFunction() {
            override fun call(arg: LuaValue): LuaValue {
                val key = arg.checkjstring()
                return CoerceJavaToLua.coerce(Component.translatable(key))
            }
        })

        globals.set("displayClientMessage", object : ThreeArgFunction() {
            override fun call(arg1: LuaValue, arg2: LuaValue, arg3: LuaValue): LuaValue {
                val player = CoerceLuaToJava.coerce(arg1, Player::class.java) as Player
                val message = CoerceLuaToJava.coerce(arg2, Component::class.java) as Component
                val showActionBar = arg3.checkboolean()
                player.displayClientMessage(message, showActionBar)
                return NIL
            }
        })

        globals.set("spawnEntity", object : ThreeArgFunction() {
            override fun call(arg1: LuaValue, arg2: LuaValue, arg3: LuaValue): LuaValue {
                val level = CoerceLuaToJava.coerce(arg1, Level::class.java) as Level
                val pos = CoerceLuaToJava.coerce(arg2, BlockPos::class.java) as BlockPos
                if (level !is ServerLevel) return NIL
                val type = BuiltInRegistries.ENTITY_TYPE.getValue(ResourceLocation.tryParse(arg3.checkjstring())!!) ?: return NIL
                type.spawn(level, null, null, pos, EntitySpawnReason.MOB_SUMMONED, true, true)
                return NIL
            }
        })

        globals.set("getEffect", object : OneArgFunction() {
            override fun call(arg: LuaValue): LuaValue {
                val effect = BuiltInRegistries.MOB_EFFECT.getValue(getResourceLocation(arg.checkjstring()))
                return CoerceJavaToLua.coerce(effect)
            }
        })
    }

    // Utility for ResourceLocation conversion
    private fun getResourceLocation(id: String) = ResourceLocation.tryParse(id)!!
}
