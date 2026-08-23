package net.Chidoziealways.everythingjapanese.karma

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.item.custom.PoweredSword
import net.minecraft.commands.arguments.EntityArgument.players
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityTypes
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent
import net.neoforged.neoforge.event.tick.LevelTickEvent
import net.neoforged.neoforge.event.tick.ServerTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object KarmaTick {
    val kanpeki = Int.MAX_VALUE downTo 101
    val high = 100 downTo 51
    val reasonable = 50 downTo 1
    val bad = 0 downTo -49
    val worse = -50 downTo -99
    val horrible = -100 downTo -199
    val saiaku = -200 downTo Int.MIN_VALUE

    val innocentMobs = listOf(
        EntityTypes.VILLAGER,
        EntityTypes.IRON_GOLEM,
        EntityTypes.BEE,
        EntityTypes.CAMEL,
        EntityTypes.HORSE,
        EntityTypes.SNIFFER,
        EntityTypes.ARMADILLO
    )

    val badMobs = listOf(
        EntityTypes.ZOMBIE,
        EntityTypes.ZOMBIE_VILLAGER,
        EntityTypes.SKELETON,
        EntityTypes.WITHER,
        EntityTypes.WITCH,
        EntityTypes.PILLAGER,
        EntityTypes.CREEPER,
        EntityTypes.SPIDER,
        EntityTypes.CAVE_SPIDER
    )

    @SubscribeEvent
    fun onWorldTick(event: LevelTickEvent.Post) {
        val players = event.level.players()
        val level = event.level
        for  (player in players) {
            val karma = player.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: continue

            if (karma.getKarmaAmount() in worse) {
                if (level.random.nextInt(100) < 4) {
                    val zombie = EntityTypes.ZOMBIE.create(level, EntitySpawnReason.TRIGGERED) ?: return
                    val skeleton = EntityTypes.SKELETON.create(level, EntitySpawnReason.TRIGGERED) ?: return

                    // Equip armor for zombie
                    zombie.setItemSlot(EquipmentSlot.HEAD, ItemStack(Items.IRON_HELMET))
                    zombie.setItemSlot(EquipmentSlot.CHEST, ItemStack(Items.IRON_CHESTPLATE))
                    zombie.setItemSlot(EquipmentSlot.LEGS, ItemStack(Items.IRON_LEGGINGS))
                    zombie.setItemSlot(EquipmentSlot.FEET, ItemStack(Items.IRON_BOOTS))

                    // Optional: weapon
                    zombie.setItemSlot(EquipmentSlot.MAINHAND, ItemStack(Items.IRON_SWORD))

                    // Equip armor for skeleton
                    skeleton.setItemSlot(EquipmentSlot.HEAD, ItemStack(Items.LEATHER_HELMET))
                    skeleton.setItemSlot(EquipmentSlot.CHEST, ItemStack(Items.LEATHER_CHESTPLATE))
                    skeleton.setItemSlot(EquipmentSlot.LEGS, ItemStack(Items.LEATHER_LEGGINGS))
                    skeleton.setItemSlot(EquipmentSlot.FEET, ItemStack(Items.LEATHER_BOOTS))
                    skeleton.setItemSlot(EquipmentSlot.MAINHAND, ItemStack(Items.BOW))

                    val spawnPos = player.blockPosition().offset(level.random.nextInt(-5,5),0,level.random.nextInt(-5,5))
                    zombie.setPos(spawnPos.x + 0.5, spawnPos.y.toDouble(), spawnPos.z + 0.5)
                    skeleton.setPos(spawnPos.x + 0.5, spawnPos.y.toDouble(), spawnPos.z + 0.5)

                    // Now add them to the world
                    level.addFreshEntity(zombie)
                    level.addFreshEntity(skeleton)
                }
            }
        }
    }

    @SubscribeEvent
    fun onServerTick(event: ServerTickEvent.Post) {
        for (player in event.server.playerList.players) {
            player.inventory.forEach { stack ->
                if (stack.item == JModItems.POWERED_SWORD) {
                    PoweredSword.updateKarma(stack, player)
                }
            }
        }
    }

    @SubscribeEvent
    fun onKillEntity(event: LivingDeathEvent) {
        val player = event.source.entity as? ServerPlayer ?: return
        val killed = event.entity
        val karmaCap = player.getCapability(ModCapabilities.KARMA_CAPABILITY) ?: return

        when (killed.type) {
            in innocentMobs -> {
                karmaCap.removeKarma(10)
            }
            in badMobs -> {
                karmaCap.addKarma(10)
            }
        }

        syncKarmaToPlayer(player, karmaCap)
    }

    fun syncKarmaToPlayer(player: ServerPlayer, karma: IKarma) {
        PacketDistributor.sendToPlayer(player, KarmaSync(karma.getKarmaAmount()))
    }
}