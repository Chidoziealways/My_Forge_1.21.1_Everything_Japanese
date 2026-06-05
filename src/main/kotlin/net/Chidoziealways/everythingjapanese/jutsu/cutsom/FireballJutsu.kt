package net.Chidoziealways.everythingjapanese.jutsu.cutsom

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.jutsu.JutsuType
import net.Chidoziealways.everythingjapanese.jutsu.MasteryHandler
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball

class FireballJutsu : Jutsu(
    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "fireball"),
    "Fireball",
    20f,
    2,
    JutsuType.HINOJUTSU,
    JutsuType.TAIJUTSU
) {
    override fun cast(player: ServerPlayer): Boolean {
        player.sendOverlayMessage(Component.literal("Casting: $name"), )

        val world = player.level()
        val lookVec = player.lookAngle
        val eyePos = player.getEyePosition(1.0f)
        val spawnPos = eyePos.add(lookVec.scale(0.5))

        val mastery = MasteryHandler.getMastery(player, getID())

        val fireball = if(mastery >= 10f) {
            LargeFireball(world, player, lookVec, getPowerLevel())
        } else {
            SmallFireball(world, player, lookVec)
        }
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

        MasteryHandler.addMastery(player, getID(), 0.01f)
        return true
    }
}
