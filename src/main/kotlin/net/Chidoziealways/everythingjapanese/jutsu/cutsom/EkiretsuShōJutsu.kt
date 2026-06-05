package net.Chidoziealways.everythingjapanese.jutsu.cutsom

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.entity.custom.EkiretsuShōProjectileEntity
import net.Chidoziealways.everythingjapanese.jutsu.ChiretsuShoMastery
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.jutsu.JutsuType
import net.Chidoziealways.everythingjapanese.jutsu.MasteryHandler
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import java.util.function.Predicate
import kotlin.math.sqrt

class EkiretsuShōJutsu: Jutsu(
    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "ekiretsu_sho_jutsu"),
    "Ekiretsu Shō",
    15f,
    3,
    JutsuType.TAIJUTSU
) {

    fun raycastBlock(player: Player, maxDistance: Double = 5.0): BlockHitResult {
        val eyePos: Vec3 = player.getEyePosition(1.0f)
        val lookVec = player.lookAngle
        val reachVec: Vec3 = eyePos.add(lookVec.scale(maxDistance))

        val clipContext = ClipContext(
            eyePos,
            reachVec,
            ClipContext.Block.OUTLINE,
            ClipContext.Fluid.SOURCE_ONLY,
            player
        )

        val hitResult = player.level().clip(clipContext)
        return if (hitResult.type == HitResult.Type.BLOCK) hitResult as BlockHitResult
        else BlockHitResult(Vec3(BlockPos.ZERO.x.toDouble(), BlockPos.ZERO.y.toDouble(), BlockPos.ZERO.z.toDouble()), net.minecraft.core.Direction.UP, BlockPos.ZERO, false)
    }

    override fun cast(player: ServerPlayer): Boolean {
        if (!EkiretsuJutsuTracker.has(player)) {
            val mastery = MasteryHandler.getMastery(player, getID())
            val stage = ChiretsuShoMastery.fromMastery(mastery)
            val radius = stage.radius

            val centerHit = raycastBlock(player)
            if (centerHit.type != HitResult.Type.BLOCK) {
                player.sendOverlayMessage(Component.literal("No liquid block in sight!"), )
                return false
            }

            val centerPos = centerHit.blockPos
            val liquids = mutableListOf<BlockPos>()

            for (x in -radius..radius) {
                for (y in -radius..radius) {
                    for (z in -radius..radius) {
                        val pos = centerPos.offset(x, y, z)
                        val fluidState = player.level().getFluidState(pos)
                        if (!fluidState.isEmpty) {
                            liquids.add(pos)
                        }
                    }
                }
            }

            if (liquids.isEmpty()) {
                player.sendOverlayMessage(Component.literal("No liquids to manipulate"), )
                return false
            }

            for (pos in liquids) {
                val fluidState = player.level().getFluidState(pos)
                player.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3)

                val proj = EkiretsuShōProjectileEntity(player.level(), fluidState, player, pos) // You’d need a constructor for FluidState
                proj.setPos(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
                proj.setNoPhysics(true)
                proj.levitating = true
                proj.levitationTargetY = proj.y + 5.0
                player.level().addFreshEntity(proj)

                EkiretsuJutsuTracker.add(player, proj)
            }

            player.sendOverlayMessage(Component.literal("Liquids levitated! (${liquids.size} blocks, radius $radius)"), )
        } else {
            val projectiles = EkiretsuJutsuTracker.getAll(player)
            if (projectiles.isNotEmpty()) {
                val hitResult = getTargetHitResult(player, 50.0)
                val eyePos = player.eyePosition
                val targetPos = when (hitResult) {
                    is BlockHitResult -> hitResult.location
                    is EntityHitResult -> hitResult.location
                    else -> eyePos.add(player.lookAngle.scale(50.0))
                }

                for (proj in projectiles) {
                    if (!proj.isRemoved) {
                        proj.setFlying(true)
                        val dx = targetPos.x - proj.x
                        val dy = targetPos.y - proj.y
                        val dz = targetPos.z - proj.z
                        val distance = sqrt(dx * dx + dy * dy + dz * dz)
                        proj.shoot(dx / distance, dy / distance, dz / distance, 2.0f, 0f)
                    }
                }

                player.sendOverlayMessage(Component.literal("${projectiles.size} liquids launched!"), )
            } else {
                player.sendOverlayMessage(Component.literal("No levitating liquids found."), )
                return false
            }
            EkiretsuJutsuTracker.clear(player)
        }
        return true
    }

    fun getTargetHitResult(player: Player, maxDistance: Double): HitResult {
        val eyePos = player.eyePosition
        val lookVec = player.lookAngle
        val reachVec = eyePos.add(lookVec.scale(maxDistance))

        // 1. Block raycast
        val blockHit = player.level().clip(
            ClipContext(
                eyePos,
                reachVec,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
            )
        )

        val blockDist = blockHit.location.distanceTo(eyePos)

        // 2. Entity raycast
        val aabb = player.boundingBox.expandTowards(lookVec.scale(maxDistance)).inflate(1.0)
        val predicate: Predicate<Entity> = Predicate { e ->
            e.isPickable && e != player
        }

        var nearestEntity: Entity? = null
        var nearestDist = maxDistance

        for (entity in player.level().getEntities(player, aabb, predicate)) {
            val entityAABB = entity.boundingBox.inflate(entity.pickRadius.toDouble())
            val optionalHit = entityAABB.clip(eyePos, reachVec)
            if (optionalHit.isPresent) {
                val hitPos = optionalHit.get()
                val dist = hitPos.distanceTo(eyePos)
                if (dist < nearestDist) {
                    nearestDist = dist
                    nearestEntity = entity
                }
            }
        }

        return if (nearestEntity != null && nearestDist < blockDist) {
            EntityHitResult(nearestEntity)
        } else {
            blockHit
        }
    }
}