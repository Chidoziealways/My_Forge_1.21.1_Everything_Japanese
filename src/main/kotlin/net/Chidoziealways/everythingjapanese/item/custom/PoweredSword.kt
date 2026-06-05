package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.AABB

class PoweredSword(props: Properties): Item(props) {
    companion object{
        fun updateKarma(stack: ItemStack, holder: ServerPlayer) {
            val playerKarmaCap = holder.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: return
            val playerKarma = playerKarmaCap.getKarmaAmount()

            stack.set(ModDataComponentTypes.KARMA, playerKarma)
        }
    }

    override fun onEntitySwing( stack: ItemStack, entity: LivingEntity, hand: InteractionHand ): Boolean {
        val player = entity as? ServerPlayer ?: return super.onEntitySwing(stack, entity, hand)
        val karmaCap = player.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: return super.onEntitySwing(stack, entity, hand)
        val karmaVal = karmaCap.getKarmaAmount()
        val level = player.level()
        when {
            karmaVal > 0 -> {
                doSlash( level, player, ParticleTypes.END_ROD, 6f) // clean, divine
             }
            karmaVal < 0 -> {
                doSlash( level, player, ParticleTypes.SMOKE, 6f) // dirty, corrupt

             }
        }
        return super.onEntitySwing(stack, entity, hand)
    }

    private fun doSlash( level: ServerLevel, player: ServerPlayer, particle: ParticleOptions, damage: Float ) {
        val startPos = player.eyePosition
        val look = player.lookAngle.normalize()
        val steps = 400
        val arcAngle = Math.PI / 2
        val mode = kotlin.random.Random.nextInt(3)
        val hitEntities = mutableSetOf<LivingEntity>()
        val maxForward = 100.0
        val duration = 50 // ticks
        for (tick in 0 until duration) {
            val forwardDist = (tick.toDouble() / duration) * maxForward
            level.server.execute {
                for (i in 0..steps) {
                    val angle = -arcAngle / 2 + i * (arcAngle / steps)
                    val rotated = when (mode) {
                        0 -> look.with( x = look.x * kotlin.math.cos(angle) - look.z * kotlin.math.sin(angle), z = look.x * kotlin.math.sin(angle) + look.z * kotlin.math.cos(angle) )
                        1 -> look.with( y = look.y * kotlin.math.cos(angle) - look.z * kotlin.math.sin(angle), z = look.y * kotlin.math.sin(angle) + look.z * kotlin.math.cos(angle) )
                        else -> look.with( x = look.x * kotlin.math.cos(angle) - look.y * kotlin.math.sin(angle), y = look.x * kotlin.math.sin(angle) + look.y * kotlin.math.cos(angle) ) }
                    val pos = startPos.add(look.scale(forwardDist)).add(rotated.scale(2.0))
                    level.sendParticles( particle, pos.x, pos.y, pos.z, 3, 0.05, 0.05, 0.05, 0.0 )
                    val aabb = AABB(pos, pos).inflate(0.5)
                    val nearby = level.getEntitiesOfClass( LivingEntity::class.java, aabb ) { it != player }
                    for (target in nearby) {
                        if (hitEntities.add(target)) {
                            target.hurtServer(level, level.damageSources().playerAttack(player), damage )
                        }
                    }
                }
            }
        }
        level.playSound( null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1f, 1f )
    }

    override fun hurtEnemy(
        stack: ItemStack,
        target: LivingEntity,
        attacker: LivingEntity
    ) {
        val player = attacker as? ServerPlayer ?: return
        val karmaCap = player.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: return
        val karmaVal = karmaCap.getKarmaAmount()

        if (karmaVal > 0) {
            //println("Karma=Good")
            // bonus damage
            target.hurtServer(
                player.level(),
                target.damageSources().playerAttack(player),
                7.0f // +2 hearts
            )
        } else if (karmaVal < 0) {
            //println("Karma=Bad")
            // unstable backlash example
            player.hurtServer(
                player.level(),
                player.damageSources().magic(),
                4.0f
            )
        }

        super.hurtEnemy(stack, target, attacker)
    }
}