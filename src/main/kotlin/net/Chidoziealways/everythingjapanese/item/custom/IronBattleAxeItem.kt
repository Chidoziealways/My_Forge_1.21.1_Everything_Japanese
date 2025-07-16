package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.entity.custom.IronBattleAxeProjectileEntity
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level

class IronBattleAxeItem(pProperties: Properties) : Item(pProperties) {
    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResult {
        val itemStack = pPlayer.getItemInHand(pUsedHand)
        pLevel.playSound(
            null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
            SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (pLevel.getRandom().nextFloat() * 0.4f + 0.8f)
        )
        if (!pLevel.isClientSide) {
            val ironBattleAxeProjectile = IronBattleAxeProjectileEntity(pPlayer, pLevel)
            ironBattleAxeProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0f, 1.5f, 0f)
            pLevel.addFreshEntity(ironBattleAxeProjectile)
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this))
        if (!pPlayer.getAbilities().instabuild) itemStack.shrink(1)

        return InteractionResult.SUCCESS
    }
}
