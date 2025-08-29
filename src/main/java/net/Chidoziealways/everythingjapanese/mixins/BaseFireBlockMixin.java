package net.Chidoziealways.everythingjapanese.mixins;

import net.Chidoziealways.everythingjapanese.portal.HellPortalShape;
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {

    @Inject(method = "onPlace", at = @At("HEAD"), cancellable = true, remap = false)
    private void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
        // Only run if it’s our dimension / portal logic
        if (!oldState.is(state.getBlock()) && BaseFireBlock.inPortalDimension(level) || level.dimension() == ModDimensions.INSTANCE.getHELL_LEVEL_KEY()) {

            // Use your custom portal shape finder
            Optional<HellPortalShape> customPortal = HellPortalShape.findEmptyPortalShape(level, pos, Direction.Axis.X);

            if (customPortal.isPresent()) {
                customPortal.get().createPortalBlocks(level);
                ci.cancel(); // prevent vanilla onPlace from running
            }
        }
    }
}
