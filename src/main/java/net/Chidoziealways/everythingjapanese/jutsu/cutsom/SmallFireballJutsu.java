package net.Chidoziealways.everythingjapanese.jutsu.cutsom;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.jutsu.Jutsu;
import net.Chidoziealways.everythingjapanese.jutsu.JutsuType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SmallFireballJutsu extends Jutsu {
    public SmallFireballJutsu() {
        super(ResourceLocation.fromNamespaceAndPath(EverythingJapanese.MOD_ID, "small_fireball"), "Small Fireball", 20, 5, JutsuType.HINOJUTSU, JutsuType.NINJUTSU);
    }

    @Override
    public void cast(Player player) {
        player.displayClientMessage(Component.literal("Casting: " + name), true);

        Level world = player.level();
        Vec3 lookVec = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 spawnPos = eyePos.add(lookVec.scale(0.5));

        SmallFireball fireball = new SmallFireball(world, player, lookVec);
        fireball.setPos(spawnPos);

        world.addFreshEntity(fireball);

        world.playSound(null, player.getBlockPosBelowThatAffectsMyMovement(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
