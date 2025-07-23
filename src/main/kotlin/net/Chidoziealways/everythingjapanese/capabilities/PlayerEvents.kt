package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.minecraft.world.entity.player.Player
import net.minecraftforge.common.util.NonNullConsumer
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.event.entity.player.PlayerEvent.*
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID)
object PlayerEvents {
    private val log: Logger = LoggerFactory.getLogger(PlayerEvents::class.java)

    fun saveChakraData(player: Player) {
        checkNotNull(player)
        log.info("Saving data")
        player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
            .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                val chakraTag = iChakra!!.serializeNBT()
                log.info("Saving Chakra")
                player.persistentData
                    .put("everythingjapanese:chakra_data", chakraTag) // Save Chakra data to persistent NBT
            })
        player.getCapability(ModCapabilities.QUEST_CAPABILITY)
            .ifPresent { iQuestCapability ->
                val questTag = iQuestCapability.serializeNBT()
                log.info("Saving Quest")
                player.persistentData
                    .put("everythingjapanese:quest", questTag)
            }
        player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
            .ifPresent(NonNullConsumer { iJutsu: IJutsuCapability? ->
                val jutsuTag = iJutsu!!.serializeNBT()
                log.info("Saving Jutsus")
                player.persistentData
                    .put("everythingjapanese:jutsu_data", jutsuTag) // Save Jutsu data to persistent NBT
            })
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerDeath(event: LivingDeathEvent) {
        if (event.entity is Player) {
            val player = event.entity as Player
            saveChakraData(player)
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerLogout(event: PlayerLoggedOutEvent) {
        val player = event.entity

        // Save Chakra and Jutsu data to persistent NBT
        saveChakraData(player)
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerLogin(event: PlayerLoggedInEvent) {
        val player = event.entity

        // Retrieve persistent data
        if (player.persistentData.contains("everythingjapanese:chakra_data")) {
            val jutsuTag = player.persistentData.getCompoundOrEmpty("everythingjapanese:chakra_data")
            player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
                .ifPresent(NonNullConsumer { iJutsu: IChakra? ->
                    log.info("Loading Chakra data from persistent NBT")
                    iJutsu!!.deserializeNBT(jutsuTag)
                })
        }

        // Retrieve persistent data
        if (player.persistentData.contains("everythingjapanese:quest")) {
            val questTag = player.persistentData.getCompoundOrEmpty("everythingjapanese:quest")
            player.getCapability(ModCapabilities.QUEST_CAPABILITY)
                .ifPresent { iQuestCapability ->
                    log.info("Loading Quest data from persistent NBT")
                    iQuestCapability.deserializeNBT(questTag)
                }
        }

        // Retrieve persistent data
        if (player.persistentData.contains("everythingjapanese:jutsu_data")) {
            val jutsuTag = player.persistentData.getCompoundOrEmpty("everythingjapanese:jutsu_data")
            player.getCapability(ModCapabilities.JUTSU_CAPABILITY)
                .ifPresent(NonNullConsumer { iJutsu: IJutsuCapability? ->
                    log.info("Loading Jutsu data from persistent NBT")
                    iJutsu!!.deserializeNBT(jutsuTag)
                })
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerChangedDimension(event: PlayerChangedDimensionEvent) {
        val player = event.getEntity()

        // Save Chakra and Jutsu data to persistent NBT
        saveChakraData(player)
    }


    @JvmStatic
    @SubscribeEvent
    fun onPlayerClone(event: PlayerEvent.Clone) {
        log.info("Loading data")
        val oldPlayer = event.getOriginal()
        val newPlayer = event.getEntity()

        // Restore Chakra data
        if (oldPlayer.getPersistentData().contains("everythingjapanese:chakra_data")) {
            val chakraTag = oldPlayer.getPersistentData().getCompoundOrEmpty("everythingjapanese:chakra_data")
            newPlayer.getCapability<IChakra?>(ModCapabilities.CHAKRA_CAPABILITY)
                .ifPresent(NonNullConsumer { iChakra: IChakra? ->
                    log.info("Restoring Chakra Data")
                    iChakra!!.deserializeNBT(chakraTag)
                })
        } else {
            log.error("Doesn't contain chakra_data")
        }

        // Restore Jutsu data
        if (oldPlayer.getPersistentData().contains("everythingjapanese:jutsu_data")) {
            val jutsuTag = oldPlayer.getPersistentData().getCompoundOrEmpty("everythingjapanese:jutsu_data")
            newPlayer.getCapability<IJutsuCapability?>(ModCapabilities.JUTSU_CAPABILITY)
                .ifPresent(NonNullConsumer { iJutsu: IJutsuCapability? ->
                    log.info("Restoring Jutsu Data")
                    iJutsu!!.deserializeNBT(jutsuTag)
                })
        } else {
            log.error("Doesn't contain jutsu_data")
        }
    }
}
