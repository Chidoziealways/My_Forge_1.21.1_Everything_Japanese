package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.attachments.ModAttachments
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.kanji.KanjiType
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks

class TalismanItem(props: Properties): Item(props) {
    companion object {
        private const val KANJI_KEY = "KanjiType"

        fun setKanji(stack: ItemStack, type: Holder<KanjiType>) {
            stack.set(ModDataComponentTypes.TALISMAN_KANJI.get(), type)
        }

        fun getKanji(stack: ItemStack): Holder<KanjiType>? {
            return stack.get(ModDataComponentTypes.TALISMAN_KANJI.get())
        }
    }

    override fun useOn(context: UseOnContext): InteractionResult {
        val level = context.level
        val pos = context.clickedPos
        val player = context.player

        if (player == null) {
            println("Player is Null")
            return InteractionResult.PASS
        }

        val stack = context.itemInHand
        val kanji = getKanji(stack)

        println("Using")

        if (kanji == null) {
            println("No kanji Attached!")
            return InteractionResult.PASS
        }

        if (!level.isClientSide) {
            println("Level isn't Client Side")
            val effect = kanji.value().blockEffect
            if (effect != null) {
                println("Effect isn't null")
                effect.apply(level, pos, player)
            } else {
                println("Effect is null")
            }
            stack.shrink(1)
            println("Shrunk")
        }
        return InteractionResult.SUCCESS
    }

    override fun interactLivingEntity(
        stack: ItemStack,
        player: Player,
        interactionTarget: LivingEntity,
        usedHand: InteractionHand
    ): InteractionResult {
        val kanji = getKanji(stack) ?: return InteractionResult.PASS

        if (!player.level().isClientSide) {
            interactionTarget.setData(ModAttachments.ACTIVE_TALISMAN, kanji)
            kanji.value().entityEffect?.apply(player.level() as ServerLevel, interactionTarget.blockPosition(), player, interactionTarget)
            /*when(kanji) {
                KanjiType.FIRE -> interactionTarget.igniteForSeconds(5f)
                KanjiType.WATER -> interactionTarget.hurtServer(player.level() as ServerLevel, player.damageSources().drown(), 4f)
                KanjiType.SEAL -> {}
                KanjiType.LIGHTNING -> {
                    val lightning = EntityType.LIGHTNING_BOLT.spawn(player.level() as ServerLevel, null, interactionTarget.blockPosition(),
                        EntitySpawnReason.MOB_SUMMONED, false, false)
                    lightning?.setPos(interactionTarget.x, interactionTarget.y, interactionTarget.z)
                }
            }*/
            stack.shrink(1)
        }
        return InteractionResult.SUCCESS
    }

    private fun fireEffect(level: Level, pos: BlockPos, player: Player) {
        val target = pos.relative(player.direction)
        if (level.isEmptyBlock(target)) {
            level.setBlock(target, Blocks.FIRE.defaultBlockState(), 11)
        }
    }

    private fun waterEffect(level: Level, pos: BlockPos, player: Player) {
        val target = pos.relative(player.direction)
        if (level.isEmptyBlock(target)) {
            level.setBlock(target, Blocks.WATER.defaultBlockState(), 11)
        }
    }

    private fun sealEffect(level: Level, pos: BlockPos, player: Player) {
        val lock = level.getCapability(ModCapabilities.LOCK_CAPABILITY, pos) ?: return
        lock.setLocked(true)
        lock.setOwner(player.uuid)
        player.displayClientMessage(Component.literal("Sealed Block! Owner is ${player.uuid}"), true)
    }

    private fun lightningEffect(level: Level, pos: BlockPos, player: Player) {
        if (level is ServerLevel) {
            val lightning = EntityType.LIGHTNING_BOLT.spawn(level, pos, EntitySpawnReason.MOB_SUMMONED)
            lightning?.setPos(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5)
        }
    }
}