package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.chakra.Chakra
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.jutsu.JutsuCapability
import net.Chidoziealways.everythingjapanese.karma.Karma
import net.Chidoziealways.everythingjapanese.money.MoneyCapabilityBlock
import net.Chidoziealways.everythingjapanese.money.MoneyCapabilityEntity
import net.Chidoziealways.everythingjapanese.money.MoneyCapabilityItem
import net.Chidoziealways.everythingjapanese.seal.SealLock
import net.Chidoziealways.everythingjapanese.stamina.StaminaCapability
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object CapabilityAttacher {

    private val chakraMap = mutableMapOf<Player, Chakra>()
    private val staminaMap = mutableMapOf<Player, StaminaCapability>()
    private val jutsuMap = mutableMapOf<Player, JutsuCapability>()
    private val lockMap = mutableMapOf<BlockPos, SealLock>()
    private val moneyEntityMap = mutableMapOf<Player, MoneyCapabilityEntity>()
    private val moneyBlockMap = mutableMapOf<BlockPos, MoneyCapabilityBlock>()
    private val moneyItemMap = mutableMapOf<ItemStack, MoneyCapabilityItem>()

    private val karmaMap = mutableMapOf<Player, Karma>()

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
        event.registerBlock(
            ModCapabilities.LOCK_CAPABILITY,
            { level, pos, state, entity, side ->
                lockMap.getOrPut(pos) { SealLock() }
            },
            Blocks.CHEST, Blocks.ENDER_CHEST, Blocks.BARREL
        )
        event.registerEntity(
            ModCapabilities.MONEY_CAPABILITY_ENTITY,
            EntityType.PLAYER) { entity, _ ->
            moneyEntityMap.getOrPut(entity) { MoneyCapabilityEntity() }
        }
        event.registerBlock(
            ModCapabilities.MONEY_CAPABILITY_BLOCK,
            { _, pos, _, _, _ ->
                moneyBlockMap.getOrPut(pos) { MoneyCapabilityBlock() }
            },
            JModBlocks.MONEY_VAULT_BLOCK
        )
        event.registerItem(
            ModCapabilities.MONEY_CAPABILITY_ITEM,
            {stack, _ -> moneyItemMap.getOrPut(stack) { MoneyCapabilityItem() } },
            JModItems.CREDIT_CARD_ITEM
        )
        event.registerEntity(
            ModCapabilities.KARMA_CAPABILITY,
            EntityType.PLAYER) { entity, _ ->
            karmaMap.getOrPut(entity) { Karma() }
        }
    }
}
