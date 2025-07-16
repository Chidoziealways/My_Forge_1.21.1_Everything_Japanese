package net.Chidoziealways.everythingjapanese.jutsu.cutsom

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.jutsu.JutsuType
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.LargeFireball

class LargeFireballJutsuLvel1 : Jutsu(
    ResourceLocation.fromNamespaceAndPath(MOD_ID, "large_fireball"),
    "Large Fireball",
    50f,
    2,
    JutsuType.HINOJUTSU,
    JutsuType.TAIJUTSU
) {
    override fun cast(player: Player) {
        player.displayClientMessage(Component.literal("Casting: $name"), true)

        val world = player.level()
        val lookVec = player.lookAngle
        val eyePos = player.getEyePosition(1.0f)
        val spawnPos = eyePos.add(lookVec.scale(0.5))

        val fireball = LargeFireball(world, player, lookVec, getPowerLevel())
        fireball.setPos(spawnPos)

        world.addFreshEntity(fireball)

        world.playSound(
            null,
            player.blockPosBelowThatAffectsMyMovement,
            SoundEvents.FIRECHARGE_USE,
            SoundSource.PLAYERS,
            1.0f,
            1.0f
        )
    }
}
