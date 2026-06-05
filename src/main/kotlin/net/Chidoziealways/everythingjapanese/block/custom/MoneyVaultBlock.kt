package net.Chidoziealways.everythingjapanese.block.custom

import com.mojang.serialization.MapCodec
import net.Chidoziealways.everythingjapanese.block.entity.custom.MoneyVaultBlockEntity
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.BlockHitResult
import org.apache.logging.log4j.LogManager

class MoneyVaultBlock(props: Properties): BaseEntityBlock(props) {
    init {
        registerDefaultState(this.stateDefinition.any().setValue(SECTION, VaultSection.SINGLE))
    }

    override fun codec(): MapCodec<out BaseEntityBlock> = CODEC

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(SECTION)
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return MoneyVaultBlockEntity(pos, state)
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (level !is ServerLevel || player !is ServerPlayer || stack.isEmpty.not())
            return InteractionResult.PASS

        val vault = level.getBlockEntity(pos) as? MoneyVaultBlockEntity ?: return InteractionResult.PASS
        val moneyCap = player.getCapability(ModCapabilities.MONEY_CAPABILITY_ENTITY) ?: return InteractionResult.PASS

        if (player.isShiftKeyDown) {
            val withdrawn = vault.withdrawFromPlayer(moneyCap, 50, player)
            player.sendOverlayMessage(
                Component.literal(if (withdrawn) "Withdrew ¥50 from the vault!" else "Vault is empty"),

            )
        } else {
            val deposited = vault.depositFromPlayer(moneyCap, 50, player)
            player.sendOverlayMessage(
                Component.literal(deposited),
            )
        }

        return InteractionResult.SUCCESS
    }

    override fun setPlacedBy(level: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        super.setPlacedBy(level, pos, state, placer, stack)
        val be = level.getBlockEntity(pos) as? MoneyVaultBlockEntity ?: return
        val belowBE = level.getBlockEntity(pos.below()) as? MoneyVaultBlockEntity
        be.setController(belowBE?.getControllerBE()?.getWorldPositionEX() ?: pos)
        be.getControllerBE()?.updateConnectivity()
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return defaultBlockState()
            .setValue(SECTION, VaultSection.SINGLE)
    }

    companion object {
        val CODEC: MapCodec<MoneyVaultBlock> = simpleCodec { MoneyVaultBlock(it) }
        val log = LogManager.getLogger(MoneyVaultBlock::class.java)

        val SECTION: EnumProperty<VaultSection> = EnumProperty.create("section", VaultSection::class.java)
    }

    enum class VaultSection : StringRepresentable {
        SINGLE,

        // Rows × Cols × Depth
        TOP_LEFT_FRONT, TOP_MIDDLE_FRONT, TOP_RIGHT_FRONT,
        TOP_LEFT_MIDDLE, TOP_MIDDLE_MIDDLE, TOP_RIGHT_MIDDLE,
        TOP_LEFT_BACK, TOP_MIDDLE_BACK, TOP_RIGHT_BACK,

        MIDDLE_LEFT_FRONT, MIDDLE_MIDDLE_FRONT, MIDDLE_RIGHT_FRONT,
        MIDDLE_LEFT_MIDDLE, MIDDLE_MIDDLE_MIDDLE, MIDDLE_RIGHT_MIDDLE,
        MIDDLE_LEFT_BACK, MIDDLE_MIDDLE_BACK, MIDDLE_RIGHT_BACK,

        BOTTOM_LEFT_FRONT, BOTTOM_MIDDLE_FRONT, BOTTOM_RIGHT_FRONT,
        BOTTOM_LEFT_MIDDLE, BOTTOM_MIDDLE_MIDDLE, BOTTOM_RIGHT_MIDDLE,
        BOTTOM_LEFT_BACK, BOTTOM_MIDDLE_BACK, BOTTOM_RIGHT_BACK;

        override fun getSerializedName(): String = name.lowercase()
    }
}