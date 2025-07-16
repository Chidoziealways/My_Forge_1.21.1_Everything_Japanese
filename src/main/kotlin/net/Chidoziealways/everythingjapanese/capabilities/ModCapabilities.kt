package net.Chidoziealways.everythingjapanese.capabilities

import net.Chidoziealways.everythingjapanese.chakra.IChakra
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken

object ModCapabilities {
    @JvmField
    val CHAKRA_CAPABILITY: Capability<IChakra> =
        CapabilityManager.get<IChakra>(object : CapabilityToken<IChakra?>() {})
    val JUTSU_CAPABILITY: Capability<IJutsuCapability> =
        CapabilityManager.get<IJutsuCapability>(object : CapabilityToken<IJutsuCapability?>() {})
}
