package net.Chidoziealways.everythingjapanese.entity.custom

import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.MoverType
import net.minecraft.world.level.Level
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import net.minecraft.world.phys.Vec3

class AspirationEntity(level: Level): Entity(ModEntities.ASPIRATION, level) {
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {

    }

    override fun tick() {
        super.tick()
        // gentle floating
        val yMotion = Math.sin(tickCount * 0.1) * 0.01
        this.move(MoverType.SELF, Vec3(0.0, yMotion, 0.0))
    }

    override fun hurtServer(
        level: ServerLevel,
        damageSource: DamageSource,
        amount: Float
    ): Boolean {
        return false
    }

    override fun readAdditionalSaveData(input: ValueInput) {

    }

    override fun addAdditionalSaveData(output: ValueOutput) {

    }
}