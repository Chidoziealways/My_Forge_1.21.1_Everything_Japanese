package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.chakra.Chakra
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCapability
import net.Chidoziealways.everythingjapanese.quest.QuestCapability
import net.Chidoziealways.everythingjapanese.seal.SealLock
import net.Chidoziealways.everythingjapanese.stamina.StaminaCapability
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object CapabilityAttacher {

    private val chakraMap = mutableMapOf<Player, Chakra>()
    private val staminaMap = mutableMapOf<Player, StaminaCapability>()
    private val jutsuMap = mutableMapOf<Player, JutsuCapability>()
    private val questMap = mutableMapOf<Player, QuestCapability>()
    private val lockMap = mutableMapOf<BlockPos, SealLock>()

    @SubscribeEvent
    fun onAttachCapabilities(event: RegisterCapabilitiesEvent) {
        event.registerEntity(
            ModCapabilities.CHAKRA_CAPABILITY,
            EntityType.PLAYER) { entity, _ ->
            chakraMap.getOrPut(entity) { Chakra() }
        }
        event.registerEntity(
            ModCapabilities.STAMINA_CAPABILITY,
            EntityType.PLAYER) { entity, _ ->
            staminaMap.getOrPut(entity) { StaminaCapability() }
        }
        event.registerEntity(
            ModCapabilities.JUTSU_CAPABILITY,
            EntityType.PLAYER) { entity, _ ->
            jutsuMap.getOrPut(entity) { JutsuCapability() }
        }
        event.registerEntity(
            ModCapabilities.QUEST_CAPABILITY,
            EntityType.PLAYER) { entity, _ ->
            questMap.getOrPut(entity) { QuestCapability() }
        }
        event.registerBlock(
            ModCapabilities.LOCK_CAPABILITY,
            { level, pos, state, entity, side ->
                lockMap.getOrPut(pos) { SealLock() }
            },
            Blocks.CHEST, Blocks.ENDER_CHEST, Blocks.BARREL
        )
    }
}
