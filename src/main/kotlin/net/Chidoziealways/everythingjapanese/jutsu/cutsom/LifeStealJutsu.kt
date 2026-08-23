package net.Chidoziealways.everythingjapanese.jutsu.cutsom

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu
import net.Chidoziealways.everythingjapanese.jutsu.JutsuType
import net.minecraft.core.BlockPos
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import java.util.function.Predicate

class LifeStealJutsu : Jutsu(
    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "life_steal_jutsu"),
    "LifeSteal",
    50f,
    4,
    JutsuType.JUJUTSU

) {

    fun raycast(player: Player, maxDistance: Double = 5.0): HitResult {
        val eyePos: Vec3 = player.getEyePosition(1.0f)
        val lookVec = player.lookAngle
        val reachVec: Vec3 = eyePos.add(lookVec.scale(maxDistance))

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

        return if (nearestEntity != null && nearestDist < maxDistance) {
            EntityHitResult(nearestEntity)
        } else {
            blockHit
        }
    }

    override fun cast(player: ServerPlayer): Boolean {
        val hit = raycast(player, 100.0)
        if (hit !is EntityHitResult) return false;
        val ent = hit.entity
        if (ent !is Player) return false
        if (ent.health > 10) return false
        val amtToRemoveAdd = 2f;
        val attr = player.getAttribute(Attributes.MAX_HEALTH) ?: return false
        val tattr = ent.getAttribute(Attributes.MAX_HEALTH) ?: return false
        attr.baseValue += amtToRemoveAdd; tattr.baseValue -= amtToRemoveAdd
        val chakr = player.getCapability(ModCapabilities.CHAKRA_CAPABILITY) ?: return false
        chakr.subtractChakra(amtToRemoveAdd * 10, player)

        return true
    }
}