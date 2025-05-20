package net.Chidoziealways.everythingjapanese.mixins;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.custom.HellPortalBlock;
import net.Chidoziealways.everythingjapanese.poi.ModPoiTypes;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalForcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Comparator;
import java.util.Optional;

@Mixin(
        value = {PortalForcer.class},
        priority = 6000
)
public abstract class PortalForcerMixin {

    @Unique
    private static final Logger myForgeForModding_mdk$log = LoggerFactory.getLogger(PortalForcerMixin.class);
    @Unique
    private static final Marker myForgeForModding_mdk$mark = MarkerFactory.getMarker("PORTALFORCERMIX");
    @Shadow
    @Final
    protected ServerLevel level;

    @Shadow protected abstract boolean canHostFrame(BlockPos pOriginalPos, BlockPos.MutableBlockPos pOffsetPos, Direction pDirection, int pOffsetScale);

    @Shadow protected abstract boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pPos);

    @Inject(method = "findClosestPortalPosition", at = @At("TAIL"), cancellable = true)
    private void modifyFindClosestPortalPosition(BlockPos pExitPos, boolean isHell, WorldBorder pWorldBorder, CallbackInfoReturnable<Optional<BlockPos>> cir) {
        PoiManager poimanager = this.level.getPoiManager();
        int searchRadius = isHell ? 16 : 128;

        if(ModPoiTypes.HELL_PORTAL.getKey() != null) {
            myForgeForModding_mdk$log.info(myForgeForModding_mdk$mark, "HellPortal POI Key PRESENT");
            Optional<BlockPos> foundPortal = poimanager.getInSquare(
                            poi -> poi.is(ModPoiTypes.HELL_PORTAL.getKey()),
                            pExitPos, searchRadius, PoiManager.Occupancy.ANY)
                    .map(PoiRecord::getPos)
                    .filter(pWorldBorder::isWithinBounds)
                    .min(Comparator.comparingDouble(p -> p.distSqr(pExitPos)));

            if (foundPortal.isPresent()) {
                myForgeForModding_mdk$log.info(myForgeForModding_mdk$mark, "We're entering our portal!!");
                cir.setReturnValue(foundPortal); // Return your portal if found
            }
        }
    }

    @Inject(method = "createPortal", at = @At("TAIL"), cancellable = true)
    private void modifyCreatePortal(BlockPos pPos, Direction.Axis pAxis, CallbackInfoReturnable<Optional<BlockUtil.FoundRectangle>> cir) {
        if (isFrameNetherrack(pPos)) {
            myForgeForModding_mdk$log.info(myForgeForModding_mdk$mark, "The Frame is NETHERRACK!, Proceeding to create the portal");
            BlockState hellPortalState = ModBlocks.HELL_PORTAL.get().defaultBlockState().setValue(HellPortalBlock.AXIS, pAxis);
            placePortalBlocks(pPos, hellPortalState);
            cir.setReturnValue(Optional.of(new BlockUtil.FoundRectangle(pPos, 2, 3)));
        }
    }

    private boolean isFrameNetherrack(BlockPos pPos) {
        for (BlockPos pos : BlockPos.betweenClosed(pPos, pPos.relative(Direction.UP, 4).relative(Direction.EAST, 3))) {
            if (!level.getBlockState(pos).is(Blocks.NETHERRACK)) {
                return false;
            }
        }
        return true;
    }


    private void placePortalBlocks(BlockPos pPos, BlockState portalState) {
        BlockPos.betweenClosed(pPos, pPos.relative(Direction.UP, 2).relative(Direction.EAST, 1))
                .forEach(pos -> level.setBlock(pos, portalState, 3));
    }

}
