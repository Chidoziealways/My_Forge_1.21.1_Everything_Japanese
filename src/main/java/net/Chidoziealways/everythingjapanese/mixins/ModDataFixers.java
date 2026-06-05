package net.Chidoziealways.everythingjapanese.mixins;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.Chidoziealways.everythingjapanese.fixers.ForgeToNeoForgeCapabilityFix;
import net.Chidoziealways.everythingjapanese.fixers.JutsuRenameAndDeleteFix;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.filefix.FileFixerUpper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(value = DataFixers.class, priority = 6000)
public abstract class ModDataFixers {

    @Shadow(remap = false) @Final
    private static BiFunction<Integer, Schema, Schema> SAME;

    @Inject(method = "addFixers", at = @At("TAIL"), remap = false)
    private static void onAddFixers(DataFixerBuilder fixerUpper, FileFixerUpper.Builder fileFixerUpper, CallbackInfo ci) {
        Schema schema = fixerUpper.addSchema(4774, SAME);
        fixerUpper.addFixer(new ForgeToNeoForgeCapabilityFix(schema, true));
        Schema schema1 = fixerUpper.addSchema(4775, SAME);
        fixerUpper.addFixer(new JutsuRenameAndDeleteFix(schema1, true));

        System.out.println("Registered Fixers");
    }
}
