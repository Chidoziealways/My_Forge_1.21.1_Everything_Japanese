package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.Chidoziealways.everythingjapanese.quest.IQuestCapability
import net.Chidoziealways.everythingjapanese.seal.ISealLock
import net.Chidoziealways.everythingjapanese.stamina.IStaminaCapability
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.capabilities.BlockCapability
import net.neoforged.neoforge.capabilities.EntityCapability

object ModCapabilities {
    @JvmField
    val CHAKRA_CAPABILITY: EntityCapability<IChakra, Void?> =
        EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(MOD_ID, "chakra"), IChakra::class.java)
    val STAMINA_CAPABILITY: EntityCapability<IStaminaCapability, Void?> =
        EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(MOD_ID, "stamina"), IStaminaCapability::class.java)
    val JUTSU_CAPABILITY: EntityCapability<IJutsuCapability, Void?> =
        EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(MOD_ID, "jutsu"), IJutsuCapability::class.java)
    val QUEST_CAPABILITY: EntityCapability<IQuestCapability, Void?> =
        EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(MOD_ID, "quests"), IQuestCapability::class.java)
    @JvmField
    val LOCK_CAPABILITY: BlockCapability<ISealLock, Void?> =
        BlockCapability.createVoid(ResourceLocation.fromNamespaceAndPath(MOD_ID, "seal"), ISealLock::class.java)
}
