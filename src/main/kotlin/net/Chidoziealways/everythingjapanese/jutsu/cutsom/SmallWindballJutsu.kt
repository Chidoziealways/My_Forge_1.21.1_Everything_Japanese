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
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge
import net.minecraft.world.phys.Vec3

class SmallWindballJutsu : Jutsu(
    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "small_windball"),
    "Small Windball",
    20f,
    5,
    JutsuType.KUKINOJUTSU,
    JutsuType.NINJUTSU
) {
    override fun cast(player: ServerPlayer): Boolean {
        player.sendOverlayMessage(Component.literal("Casting: $name"), )
        val world = player.level()
        val lookVec = player.lookAngle
        val eyePos = player.getEyePosition(1.0f)
        val spawnPos = eyePos.add(lookVec.scale(0.5))

        val windball = WindCharge(player, world, lookVec.x, lookVec.y, lookVec.z)
        windball.setPos(spawnPos)
        windball.deltaMovement = spawnPos.add(Vec3(1.0, 0.0, 0.0))

        world.addFreshEntity(windball)

        world.playSound(
            player,
            player.blockPosBelowThatAffectsMyMovement,
            SoundEvents.WIND_CHARGE_BURST.value(),
            SoundSource.PLAYERS,
            1.0F,
            1.0F
        )

        MasteryHandler.addMastery(player, getID(), 0.01f)
        return true
    }
}
