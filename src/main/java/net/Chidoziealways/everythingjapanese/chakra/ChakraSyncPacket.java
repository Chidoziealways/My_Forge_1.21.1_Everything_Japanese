package net.Chidoziealways.everythingjapanese.chakra;

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public record ChakraSyncPacket(int chakra) {

    public static void encode(ChakraSyncPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.chakra);
    }

    public static ChakraSyncPacket decode(FriendlyByteBuf buffer) {
        return new ChakraSyncPacket(buffer.readInt());
    }

    public static void handle(ChakraSyncPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.getCapability(ModCapabilities.CHAKRA_CAPABILITY)
                    .ifPresent(iChakra -> iChakra.setChakra(packet.chakra));
        });
        context.setPacketHandled(true);
    }
}
