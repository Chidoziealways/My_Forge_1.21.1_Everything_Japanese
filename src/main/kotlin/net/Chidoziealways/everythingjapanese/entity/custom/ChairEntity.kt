package net.Chidoziealways.everythingjapanese.entity.custom

import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

class ChairEntity(pEntityType: EntityType<*>, pLevel: Level) : Entity(pEntityType, pLevel) {
    override fun defineSynchedData(pBuilder: SynchedEntityData.Builder) {
    }

    override fun hurtServer(pLevel: ServerLevel, pDamageSource: DamageSource, pAmount: Float): Boolean {
        return false
    }

    override fun readAdditionalSaveData(input: ValueInput) {
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
    }

    override fun removePassenger(pPassenger: Entity) {
        super.removePassenger(pPassenger)
        this.kill(this.level() as ServerLevel)
    }
}
