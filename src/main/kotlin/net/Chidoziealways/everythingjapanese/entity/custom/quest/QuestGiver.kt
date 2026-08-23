//package net.Chidoziealways.everythingjapanese.entity.custom.quest
//
//import net.Chidoziealways.everythingcore.capabilities.SharedCapabilities
//import net.Chidoziealways.everythingcore.quest.Quest
//import net.minecraft.client.Minecraft
//import net.minecraft.resources.Identifier
//import net.minecraft.world.InteractionHand
//import net.minecraft.world.InteractionResult
//import net.minecraft.world.entity.Entity
//import net.minecraft.world.entity.EntityType
//import net.minecraft.world.entity.Mob
//import net.minecraft.world.entity.npc.Npc
//import net.minecraft.world.entity.player.Player
//import net.minecraft.world.level.Level
//
//abstract class QuestGiver(
//    val questToGive: Identifier,
//    val text: String,
//    type: EntityType<out Mob>,
//    val world: Level
//) : Npc, Mob(type, world) {
//
//    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
//        if (!world.isClientSide) {
//            // Create the dialog lines
//            val textLines = listOf(text)
//            val cap = player.getCapability(SharedCapabilities.QUEST_CAPABILITY) ?: return InteractionResult.FAIL
//            val choices = listOf(
//                DialogScreen.DialogChoice("Yes, I will.") { cap.setQuest(questToGive) },
//                DialogScreen.DialogChoice("Not now.") { Minecraft.getInstance(). }
//            )
//
//            // Open the text box overlay
//            Minecraft.getInstance().setScreen(DialogScreen(textLines, choices))
//        }
//        return InteractionResult.SUCCESS
//    }
//}