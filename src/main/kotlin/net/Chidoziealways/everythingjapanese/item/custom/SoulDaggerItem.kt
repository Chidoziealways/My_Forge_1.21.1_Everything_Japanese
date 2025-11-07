package net.Chidoziealways.everythingjapanese.item.custom

import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.world.item.ItemStack
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Item
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult

class SoulDaggerItem(props: Properties) : Item(props) {

    override fun onEntitySwing(stack: ItemStack, entity: LivingEntity, hand: InteractionHand): Boolean {
        if (!entity.level().isClientSide) {
            val level = entity.level() as ServerLevel
            val startPos = entity.eyePosition
            val look = entity.lookAngle.normalize()
            val steps = 40
            val arcAngle = PI / 2
            val mode = Random.nextInt(3)

            val maxForward = 100.0
            val duration = 100 // ticks the slash moves forward

            val end = startPos.add(look.scale(maxForward))

            for (tick in 0 until duration) {
                val forwardDist = (tick.toDouble() / duration) * maxForward

                level.server.execute {
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
                        level.sendParticles(
                            ParticleTypes.SOUL, particlePos.x, particlePos.y, particlePos.z, 3, 0.05, 0.05, 0.05,0.0
                        )

                        if (entity is Player) {

                            val player: Player = entity

                            // Step 1: block raycast
                            val blockHit = level.clip(
                                ClipContext(startPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)
                            )

                            // Step 2: entity raycast
                            val aabb = player.boundingBox.expandTowards(look.scale(maxForward)).inflate(1.0)
                            val entities = level.getEntities(player, aabb) { it.isPickable }

                            var closestEntityHit: EntityHitResult? = null
                            var closestDistance = maxForward * maxForward

                            for (entity in entities) {
                                val aabbHit = entity.boundingBox.clip(startPos, end)
                                if (aabbHit.isPresent) {
                                    val hitVec = aabbHit.get()
                                    val distance = startPos.distanceToSqr(hitVec)
                                    if (distance < closestDistance) {
                                        closestDistance = distance
                                        closestEntityHit = EntityHitResult(entity, hitVec)
                                    }
                                }
                            }

                            // Step 3: pick nearest hit
                            val finalHit: HitResult? = when {
                                closestEntityHit != null && (blockHit.type == HitResult.Type.MISS ||
                                        startPos.distanceToSqr(blockHit.location) > closestDistance) -> closestEntityHit

                                blockHit.type != HitResult.Type.MISS -> blockHit
                                else -> null
                            }

                            // Step 4: apply effect
                            when (finalHit) {
                                is EntityHitResult -> finalHit.entity.hurt(
                                    level.damageSources().playerAttack(player),
                                    8f
                                )

                                is BlockHitResult -> {
                                    val pos = finalHit.blockPos
                                    if (!level.getBlockState(pos).`is`(Blocks.BEDROCK)) {
                                        level.destroyBlock(pos, false)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            level.playSound(null, entity.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1f, 1f)
        }

        return super.onEntitySwing(stack, entity, hand)
    }

    private fun Vec3.with(x: Double = this.x, y: Double = this.y, z: Double = this.z) =
        Vec3(x, y, z)
}
