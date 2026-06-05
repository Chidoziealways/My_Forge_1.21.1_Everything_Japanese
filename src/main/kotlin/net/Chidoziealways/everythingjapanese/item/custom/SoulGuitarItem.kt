package net.Chidoziealways.everythingjapanese.item.custom

import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class SoulGuitarItem(props: Properties) : Item(props) {

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        if (level.isClientSide) return InteractionResult.PASS
        val serverLevel = level as ServerLevel

        val startPos = player.eyePosition
        val look = player.lookAngle.normalize()
        val steps = 40
        val arcAngle = PI / 2
        val mode = Random.nextInt(3)

        val maxForward = 100.0
        val duration = 40 // ticks the slash moves forward

        val end = startPos.add(look.scale(maxForward))

        for (tick in 0 until duration) {
            val forwardDist = (tick.toDouble() / duration) * maxForward

            serverLevel.server.execute {
                for (i in 0..steps) {
                    val angle = -arcAngle / 2 + i * (arcAngle / steps)
                    val rotated = when (mode) {
                        0 -> { // Horizontal
                            val x = look.x * cos(angle) - look.z * sin(angle)
                            val z = look.x * sin(angle) + look.z * cos(angle)
                            look.with(x = x, z = z)
                        }
                        1 -> { // Vertical
                            val y = look.y * cos(angle) - look.z * sin(angle)
                            val z = look.y * sin(angle) + look.z * cos(angle)
                            look.with(y = y, z = z)
                        }
                        else -> { // Diagonal
                            val x = look.x * cos(angle) - look.y * sin(angle)
                            val y = look.x * sin(angle) + look.y * cos(angle)
                            look.with(x = x, y = y)
                        }
                    }

                    val particlePos = startPos.add(look.scale(forwardDist)).add(rotated.scale(2.0))
                    serverLevel.sendParticles(
                        ParticleTypes.SONIC_BOOM,
                        particlePos.x, particlePos.y, particlePos.z,
                        1, 0.0, 0.0, 0.0, 0.0
                    )

                    // --- Hit detection (block + entity) ---
                    val blockHit = serverLevel.clip(
                        ClipContext(startPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)
                    )

                    val aabb = player.boundingBox.expandTowards(look.scale(maxForward)).inflate(1.0)
                    val entities = serverLevel.getEntities(player, aabb) { it.isPickable }

                    var closestEntityHit: EntityHitResult? = null
                    var closestDistance = maxForward * maxForward

                    for (e in entities) {
                        val aabbHit = e.boundingBox.clip(startPos, end)
                        if (aabbHit.isPresent) {
                            val hitVec = aabbHit.get()
                            val distance = startPos.distanceToSqr(hitVec)
                            if (distance < closestDistance) {
                                closestDistance = distance
                                closestEntityHit = EntityHitResult(e, hitVec)
                            }
                        }
                    }

                    val finalHit: HitResult? = when {
                        closestEntityHit != null && (blockHit.type == HitResult.Type.MISS ||
                                startPos.distanceToSqr(blockHit.location) > closestDistance) -> closestEntityHit
                        blockHit.type != HitResult.Type.MISS -> blockHit
                        else -> null
                    }

                    // --- Apply Sonic Boom Effect ---
                    when (finalHit) {
                        is EntityHitResult -> {
                            val target = finalHit.entity
                            if (target is LivingEntity) {
                                // Damage like Warden sonic boom
                                if (target.hurtServer(level, serverLevel.damageSources().sonicBoom(player), 10.0f)) {
                                    val knockbackRes = target.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE)
                                    val d1 = 0.5 * (1.0 - knockbackRes)
                                    val d0 = 2.5 * (1.0 - knockbackRes)
                                    target.push(look.x * d0, look.y * d1, look.z * d0)
                                }
                            }
                        }
                        is BlockHitResult -> {
                            val pos = finalHit.blockPos
                            if (!serverLevel.getBlockState(pos).`is`(Blocks.BEDROCK)) {
                                serverLevel.destroyBlock(pos, false)
                            }
                        }
                    }
                }
            }
        }

        serverLevel.playSound(null, player.blockPosition(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 3f, 1f)
        return InteractionResult.SUCCESS
    }
}

fun Vec3.with(x: Double = this.x, y: Double = this.y, z: Double = this.z): Vec3 =
    Vec3(x, y, z)
