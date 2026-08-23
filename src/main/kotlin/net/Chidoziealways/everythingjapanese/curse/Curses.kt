package net.Chidoziealways.everythingjapanese.curse

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object Curses {
    val CURSES = DeferredRegister.create(ModRegistries.CURSES_KEY, JAPANESE_MOD_ID)
    val POISON by CURSES.register("poison") { ->
        Curse("Poison", 5F) { player ->
            player.addEffect(MobEffectInstance(MobEffects.POISON, 100))
        }
    }
    val DEATH by CURSES.register("death") { ->
        Curse("Death", 20F) { player ->
            player.kill(player.level())
        }
    }
    fun register(eventBus: IEventBus) {
        CURSES.register(eventBus)
    }
}