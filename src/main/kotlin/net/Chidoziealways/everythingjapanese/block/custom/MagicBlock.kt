package net.Chidoziealways.everythingjapanese.custom

import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.particle.ModParticles
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class MagicBlock(properties: Properties) : Block(properties) {
    override fun useWithoutItem(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHitResult: BlockHitResult
    ): InteractionResult {
        pLevel.addParticle(
            ModParticles.PYRITE_PARTICLES, pPos.x + 0.5, (pPos.y + 1).toDouble(), pPos.z + 0.5,
            0.0, 1.0, 0.0
        )

        pLevel.playSound(pPlayer, pPos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1f, 1f)
        return InteractionResult.SUCCESS
    }

    override fun stepOn(pLevel: Level, pPos: BlockPos, pState: BlockState, pEntity: Entity) {
        if (pEntity is ItemEntity) {
            if (isValidItem(pEntity.item)) {
                pEntity.item = ItemStack(Items.DIAMOND, pEntity.item.count)
            }
            if (pEntity.item.item === Items.DIAMOND) {
                pEntity.item = ItemStack(JModItems.PYRITE_INGOT, pEntity.item.count)
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity)
    }

    private fun isValidItem(item: ItemStack): Boolean {
        return item.`is`(ModTags.Items.TRANSFORMABLE_ITEMS)
    }
}
