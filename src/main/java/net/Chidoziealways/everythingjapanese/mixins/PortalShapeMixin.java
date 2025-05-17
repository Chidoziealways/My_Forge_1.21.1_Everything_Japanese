package net.Chidoziealways.everythingjapanese.mixins;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import org.apache.commons.lang3.mutable.MutableInt;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = {PortalShape.class},
        priority = 6000
)
public abstract class PortalShapeMixin {

    @Shadow @Final private static BlockBehaviour.StatePredicate FRAME;

    @Shadow @Final private BlockPos bottomLeft;

    @Shadow @Final private int width;

    @Shadow @Final private Direction rightDir;

    @Shadow @Final private Direction.Axis axis;

    @Shadow @Final private int height;

    @Redirect(method = "getDistanceUntilTop", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/portal/PortalShape;isEmpty(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private static boolean redirectIsEmpty(BlockState pState) {
        return pState.isAir() || pState.is(BlockTags.FIRE) || pState.is(ModBlocks.HELL_PORTAL.get());
    }


    @Inject(method = "getDistanceUntilTop", at = @At("TAIL"))
    private static void mgetDistanceUntilTop(
            BlockGetter pLevel, BlockPos pPos, Direction pDirection, BlockPos.MutableBlockPos pCheckPos, int pWidth, MutableInt pPortalBlocks, CallbackInfoReturnable<Integer> cir
    ) {
        for (int i = 0; i < 21; i++) {
            pCheckPos.set(pPos).move(Direction.UP, i).move(pDirection, -1);
            if (!FRAME.test(pLevel.getBlockState(pCheckPos), pLevel, pCheckPos)) {
                cir.setReturnValue(i);
            }

            pCheckPos.set(pPos).move(Direction.UP, i).move(pDirection, pWidth);
            if (!FRAME.test(pLevel.getBlockState(pCheckPos), pLevel, pCheckPos)) {
                cir.setReturnValue(i);
            }

            for (int j = 0; j < pWidth; j++) {
                pCheckPos.set(pPos).move(Direction.UP, i).move(pDirection, j);
                BlockState blockstate = pLevel.getBlockState(pCheckPos);
                if (!redirectIsEmpty(blockstate)) {
                    cir.setReturnValue(i);
                }

                if (blockstate.is(ModBlocks.HELL_PORTAL.get())) {
                    pPortalBlocks.increment();
                }
            }
        }

        cir.setReturnValue(21);
    }

    @Inject(method = "createPortalBlocks", at = @At("TAIL"), cancellable = true)
    private void modifyCreatePortalBlocks(LevelAccessor pLevel, CallbackInfo ci) {
        if (isFrameNetherrack(pLevel)) {
            BlockState hellPortalState = ModBlocks.HELL_PORTAL.get().defaultBlockState();
            BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1))
                    .forEach(pos -> pLevel.setBlock(pos, hellPortalState, 18));
            ci.cancel();
        }
    }

    private boolean isFrameNetherrack(LevelAccessor pLevel) {
        for (BlockPos pos : BlockPos.betweenClosed(
                this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1))) {
            if (!pLevel.getBlockState(pos).is(Blocks.NETHERRACK)) {
                return false;
            }
        }
        return true;
    }


}
