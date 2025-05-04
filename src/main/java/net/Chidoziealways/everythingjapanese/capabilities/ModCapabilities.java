package net.Chidoziealways.everythingjapanese.capabilities;

import net.Chidoziealways.everythingjapanese.chakra.IChakra;
import net.Chidoziealways.everythingjapanese.jutsu.IJutsuCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ModCapabilities {
    public static final Capability<IChakra> CHAKRA_CAPABILITY = CapabilityManager.get(new CapabilityToken<IChakra>() {});
    public static final Capability<IJutsuCapability> JUTSU_CAPABILITY = CapabilityManager.get(new CapabilityToken<IJutsuCapability>() {});
}
