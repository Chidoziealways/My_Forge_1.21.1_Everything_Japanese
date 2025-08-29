package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.minecraft.world.entity.player.Player
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object PlayerEvents {
    private val log: Logger = LoggerFactory.getLogger(PlayerEvents::class.java)

    fun saveCapabilityData(player: Player) {
        checkNotNull(player)
        log.info("Saving data")
        val chakra =  player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
        val chakraTag = chakra!!.serializeNBT()
        log.info("Saving Chakra")
        player.persistentData.put("everythingjapanese:chakra_data", chakraTag!!) // Save Chakra data to persistent NBT

        val stamina =  player.getCapability(ModCapabilities.STAMINA_CAPABILITY)
        val staminaTag = stamina!!.serializeNBT()
        log.info("Saving Stamina")
        player.persistentData.put("everythingjapanese:stamina_data", staminaTag) // Save Chakra data to persistent NBT

        val quests = player.getCapability(ModCapabilities.QUEST_CAPABILITY)
        val questTag = quests!!.serializeNBT()
        log.info("Saving Quest")
        player.persistentData.put("everythingjapanese:quest", questTag)

        val jutsu = player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
        val jutsuTag = jutsu!!.serializeNBT()
        log.info("Saving Jutsus")
        player.persistentData.put("everythingjapanese:jutsu_data", jutsuTag) // Save Jutsu data to persistent NBT

    }
    @SubscribeEvent
    fun onPlayerDeath(event: LivingDeathEvent) {
        if (event.entity is Player) {
            val player = event.entity as Player
            saveCapabilityData(player)
        }
    }
    
    @SubscribeEvent
    fun onPlayerLogout(event: PlayerEvent.PlayerLoggedOutEvent) {
        val player = event.entity

        // Save Chakra and Jutsu data to persistent NBT
        saveCapabilityData(player)
    }
    
    @SubscribeEvent
    fun onPlayerLogin(event: PlayerEvent.PlayerLoggedInEvent) {
        val player = event.entity

        // Retrieve persistent data
        if (player.persistentData.contains("everythingjapanese:chakra_data")) {
            val chakraTag = player.persistentData.getCompoundOrEmpty("everythingjapanese:chakra_data")
            val chakra = player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
            log.info("Loading Chakra data from persistent NBT")
            chakra!!.deserializeNBT(chakraTag)
        }

        if (player.persistentData.contains("everythingjapanese:stamina_data")) {
            val staminaTag = player.persistentData.getCompoundOrEmpty("everythingjapanese:stamina_data")
            val stamina = player.getCapability(ModCapabilities.STAMINA_CAPABILITY)
            log.info("Loading Stamina data from persistent NBT")
            stamina!!.deserializeNBT(staminaTag)
        }

        // Retrieve persistent data
        if (player.persistentData.contains("everythingjapanese:quest")) {
            val questTag = player.persistentData.getCompoundOrEmpty("everythingjapanese:quest")
            val quests = player.getCapability(ModCapabilities.QUEST_CAPABILITY)
            log.info("Loading Quest data from persistent NBT")
            quests!!.deserializeNBT(questTag)
        }

        // Retrieve persistent data
        if (player.persistentData.contains("everythingjapanese:jutsu_data")) {
            val jutsuTag = player.persistentData.getCompoundOrEmpty("everythingjapanese:jutsu_data")
            val jutsu = player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
            log.info("Loading Jutsu data from persistent NBT")
            jutsu!!.deserializeNBT(jutsuTag)
        }
    }

    
    @SubscribeEvent
    fun onPlayerChangedDimension(event: PlayerEvent.PlayerChangedDimensionEvent) {
        val player = event.entity

        // Save Chakra and Jutsu data to persistent NBT
        saveCapabilityData(player)
    }


    
    @SubscribeEvent
    fun onPlayerClone(event: PlayerEvent.Clone) {
        log.info("Loading data")
        val oldPlayer = event.original
        val newPlayer = event.entity

        // Restore Chakra data
        if (oldPlayer.persistentData.contains("everythingjapanese:chakra_data")) {
            val chakraTag = oldPlayer.persistentData.getCompoundOrEmpty("everythingjapanese:chakra_data")
            val chakra =  newPlayer.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
            log.info("Restoring Chakra Data")
            chakra!!.deserializeNBT(chakraTag)
        } else {
            log.error("Doesn't contain chakra_data")
        }

        if (oldPlayer.persistentData.contains("everythingjapanese:stamina_data")) {
            val staminaTag = oldPlayer.persistentData.getCompoundOrEmpty("everythingjapanese:stamina_data")
            val stamina =  newPlayer.getCapability(ModCapabilities.STAMINA_CAPABILITY)
            log.info("Restoring Stamina Data")
            stamina!!.deserializeNBT(staminaTag)
        } else {
            log.error("Doesn't contain stamina_data")
        }

        // Restore Jutsu data
        if (oldPlayer.persistentData.contains("everythingjapanese:quest")) {
            val questTag = oldPlayer.persistentData.getCompoundOrEmpty("everythingjapanese:quest")
            val quests = newPlayer.getCapability(ModCapabilities.QUEST_CAPABILITY)
            log.info("Restoring Quest Data")
            quests!!.deserializeNBT(questTag)
        } else {
            log.error("Doesn't contain quest")
        }

        // Restore Jutsu data
        if (oldPlayer.persistentData.contains("everythingjapanese:jutsu_data")) {
            val jutsuTag = oldPlayer.persistentData.getCompoundOrEmpty("everythingjapanese:jutsu_data")
            val jutsu = newPlayer.getCapability<IJutsuCapability?>(ModCapabilities.JUTSU_CAPABILITY)
            log.info("Restoring Jutsu Data")
            jutsu!!.deserializeNBT(jutsuTag)
        } else {
            log.error("Doesn't contain jutsu_data")
        }
    }
}
