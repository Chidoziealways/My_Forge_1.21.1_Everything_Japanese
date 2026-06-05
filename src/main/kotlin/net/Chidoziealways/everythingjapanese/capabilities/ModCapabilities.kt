package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.Chidoziealways.everythingjapanese.karma.IKarma
import net.Chidoziealways.everythingjapanese.money.IMoneyCapability
import net.Chidoziealways.everythingjapanese.seal.ISealLock
import net.Chidoziealways.everythingjapanese.stamina.IStaminaCapability
import net.minecraft.resources.Identifier
import net.neoforged.neoforge.capabilities.BlockCapability
import net.neoforged.neoforge.capabilities.EntityCapability
import net.neoforged.neoforge.capabilities.ItemCapability

object ModCapabilities {
    @JvmField
    val CHAKRA_CAPABILITY: EntityCapability<IChakra, Void?> =
        EntityCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "chakra"), IChakra::class.java)
    val STAMINA_CAPABILITY: EntityCapability<IStaminaCapability, Void?> =
        EntityCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "stamina"), IStaminaCapability::class.java)
    val JUTSU_CAPABILITY: EntityCapability<IJutsuCapability, Void?> =
        EntityCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "jutsu"), IJutsuCapability::class.java)
    @JvmField
    val LOCK_CAPABILITY: BlockCapability<ISealLock, Void?> =
        BlockCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "seal"), ISealLock::class.java)
    val MONEY_CAPABILITY_ENTITY: EntityCapability<IMoneyCapability, Void?> =
        EntityCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "money_entity"), IMoneyCapability::class.java)
    val MONEY_CAPABILITY_BLOCK: BlockCapability<IMoneyCapability, Void?> =
        BlockCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "money_block"), IMoneyCapability::class.java)
    val MONEY_CAPABILITY_ITEM: ItemCapability<IMoneyCapability, Void?> =
        ItemCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "money_item"), IMoneyCapability::class.java)

    val KARMA_CAPABILITY: EntityCapability<IKarma, Void?> =
        EntityCapability.createVoid(Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "karma"), IKarma::class.java)
}
