package net.Chidoziealways.everythingjapanese.item.custom

import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.tags.BlockTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.*
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.gameevent.GameEvent
import java.util.function.Consumer

class Drinks(pProperties: Properties) : Item(pProperties) {
    init {
        println(DRINK_DURATION)
    }

    override fun getDefaultInstance(): ItemStack {
        val itemstack = super.getDefaultInstance()
        itemstack.set<PotionContents?>(DataComponents.POTION_CONTENTS, PotionContents(Potions.WATER))
        return itemstack
    }

    override fun finishUsingItem(pStack: ItemStack, pLevel: Level, pEntityLiving: LivingEntity): ItemStack {
        val player = if (pEntityLiving is Player) pEntityLiving else null
        if (player is ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, pStack)
        }

        if (!pLevel.isClientSide) {
            val potioncontents =
                pStack.getOrDefault<PotionContents>(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
            potioncontents.forEachEffect(Consumer { p_327729_: MobEffectInstance? ->
                if (p_327729_!!.getEffect().value().isInstantenous()) {
                    p_327729_.getEffect().value().applyInstantenousEffect(
                        (pLevel as ServerLevel),
                        player,
                        pEntityLiving,
                        player,
                        p_327729_.getAmplifier(),
                        1.0
                    )
                } else {
                    pEntityLiving.addEffect(p_327729_)
                }
            }, 2.0f)
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this))
            pStack.consume(1, player)
        }

        if (player == null || !player.hasInfiniteMaterials()) {
            if (pStack.isEmpty()) {
                return ItemStack(Items.GLASS_BOTTLE)
            }

            if (player != null) {
                player.getInventory().add(ItemStack(Items.GLASS_BOTTLE))
            }
        }

        pEntityLiving.gameEvent(GameEvent.DRINK)
        return pStack
    }

    override fun useOn(pContext: UseOnContext): InteractionResult {
        val level = pContext.getLevel()
        val blockpos = pContext.getClickedPos()
        val player = pContext.getPlayer()
        val itemstack = pContext.getItemInHand()
        val potioncontents =
            itemstack.getOrDefault<PotionContents>(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
        val blockstate = level.getBlockState(blockpos)
        if (pContext.getClickedFace() != Direction.DOWN && blockstate.`is`(BlockTags.CONVERTABLE_TO_MUD) && potioncontents.`is`(
                Potions.WATER
            )
        ) {
            level.playSound(null, blockpos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0f, 1.0f)
            player!!.setItemInHand(
                pContext.getHand(),
                ItemUtils.createFilledResult(itemstack, player, ItemStack(Items.GLASS_BOTTLE))
            )
            player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()))
            if (!level.isClientSide) {
                val serverlevel = level as ServerLevel

                for (i in 0..4) {
                    serverlevel.sendParticles<SimpleParticleType?>(
                        ParticleTypes.SPLASH,
                        blockpos.getX().toDouble() + level.random.nextDouble(),
                        (blockpos.getY() + 1).toDouble(),
                        blockpos.getZ().toDouble() + level.random.nextDouble(),
                        1,
                        0.0,
                        0.0,
                        0.0,
                        1.0
                    )
                }
            }

            level.playSound(null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f)
            level.gameEvent(null, GameEvent.FLUID_PLACE, blockpos)
            level.setBlockAndUpdate(blockpos, Blocks.MUD.defaultBlockState())
            return InteractionResult.SUCCESS
        } else {
            return InteractionResult.PASS
        }
    }

    override fun getUseDuration(pStack: ItemStack, pEntity: LivingEntity): Int {
        return 32
    }

    override fun getUseAnimation(pStack: ItemStack): ItemUseAnimation {
        return ItemUseAnimation.DRINK
    }

    override fun use(pLevel: Level, pPlayer: Player, pHand: InteractionHand): InteractionResult {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand)
    }

    companion object {
        private const val DRINK_DURATION = 32
    }
}