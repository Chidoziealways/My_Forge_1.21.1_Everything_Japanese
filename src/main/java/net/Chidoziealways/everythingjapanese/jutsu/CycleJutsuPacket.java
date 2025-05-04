package net.Chidoziealways.everythingjapanese.jutsu;

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.function.Supplier;

public record CycleJutsuPacket() {

    public CycleJutsuPacket() {

    }

    public void encode(FriendlyByteBuf buf) {

    }

    public static CycleJutsuPacket decode(FriendlyByteBuf buf) {
        return new CycleJutsuPacket();
    }

    public static void handle(CycleJutsuPacket msg, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if(player != null) {
                player.getCapability(ModCapabilities.JUTSU_CAPABILITY).ifPresent(iJutsuCapability -> {
                    iJutsuCapability.cycleJutsu();
                    player.displayClientMessage(Component.literal("Selected Jutsu: " + iJutsuCapability.getSelectedJutsu()), true);
                });
            }
        });
        ctx.setPacketHandled(true);
    }
}
