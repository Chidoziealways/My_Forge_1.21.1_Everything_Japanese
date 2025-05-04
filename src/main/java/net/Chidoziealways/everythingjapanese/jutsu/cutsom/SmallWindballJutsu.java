package net.Chidoziealways.everythingjapanese.jutsu.cutsom;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu;
import net.Chidoziealways.everythingjapanese.jutsu.JutsuType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SmallWindballJutsu extends Jutsu {
    public SmallWindballJutsu() {
        super(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "small_windball"), "Small Windball", 20, 5, JutsuType.KUKINOJUTSU, JutsuType.NINJUTSU);
    }

    @Override
    public void cast(Player player) {
        player.displayClientMessage(Component.literal("Casting: " + name), true);
            Level world = player.level();
            Vec3 lookVec = player.getLookAngle();
            Vec3 eyePos = player.getEyePosition(1.0F);
            Vec3 spawnPos = eyePos.add(lookVec.scale(0.5));

            WindCharge windball = new WindCharge(player, world, lookVec.x, lookVec.y, lookVec.z);
            windball.setPos(spawnPos);
            windball.setDeltaMovement(spawnPos.add(new Vec3(1, 0, 0)));

            world.addFreshEntity(windball);

            world.playSound(null, player.getBlockPosBelowThatAffectsMyMovement(), SoundEvents.WIND_CHARGE_BURST.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
