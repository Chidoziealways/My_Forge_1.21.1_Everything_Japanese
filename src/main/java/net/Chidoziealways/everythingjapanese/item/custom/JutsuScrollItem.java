package net.Chidoziealways.everythingjapanese.item.custom;

import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class JutsuScrollItem extends Item {
    private final String jutsuId;

    public JutsuScrollItem(String jutsuId, Properties properties) {
        super(properties);
        this.jutsuId = jutsuId;
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer) {
            pPlayer.getCapability(ModCapabilities.JUTSU_CAPABILITY).ifPresent(iJutsuCapability -> {
                if (!iJutsuCapability.hasLearnedJutsu(jutsuId)) {
                    iJutsuCapability.learnJutsu(jutsuId);
                    ((ServerPlayer) pPlayer).sendSystemMessage(Component.literal("You have learnt the Jutsu: " + jutsuId));
                    pPlayer.getItemInHand(pHand).shrink(1);
                } else {
                    ((ServerPlayer) pPlayer).sendSystemMessage(Component.literal("You already know this Jutsu!"));
                }
            });
        }
        return InteractionResult.SUCCESS;
    }
}
