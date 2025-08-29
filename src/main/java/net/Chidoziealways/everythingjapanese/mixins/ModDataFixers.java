package net.Chidoziealways.everythingjapanese.mixins;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.Chidoziealways.everythingjapanese.fixers.ForgeToNeoForgeCapabilityFix;
import net.Chidoziealways.everythingjapanese.fixers.JutsuRenameAndDeleteFix;
import net.minecraft.util.datafix.DataFixers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.function.BiFunction;

@Mixin(value = DataFixers.class, priority = 6000)
public abstract class ModDataFixers {

    @Shadow(remap = false) @Final
    private static BiFunction<Integer, Schema, Schema> SAME;

    @Inject(method = "addFixers", at = @At("TAIL"), remap = false)
    private static void onAddFixers(DataFixerBuilder builder, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        Schema schema = builder.addSchema(4425, SAME);
        builder.addFixer(new ForgeToNeoForgeCapabilityFix(schema, true));
        Schema schema1 = builder.addSchema(4426, SAME);
        builder.addFixer(new JutsuRenameAndDeleteFix(schema1, true));

        System.out.println("Registered Fixers");
    }
}
