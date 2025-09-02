package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.block.entity.custom.MoneyVaultBlockEntity
import net.Chidoziealways.everythingjapanese.capabilities.ModCapabilities
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.UseOnContext
import org.apache.logging.log4j.LogManager
import kotlin.io.path.Path

class CreditCardItem(props: Properties): Item(props) {
    override fun useOn(context: UseOnContext): InteractionResult {
        val stack = context.itemInHand
        val player = context.player
        val level = context.level
        val pos = context.clickedPos
        val vault = level.getBlockEntity(pos) as? MoneyVaultBlockEntity
        if (vault == null) {
            log.info("VAULT BLOCK ENTITY IS NULL")
            return InteractionResult.PASS
        }
        if (player == null) {
            log.info("Player is NULL!")
            return InteractionResult.PASS
        }
        val moneyCap = stack.getCapability(ModCapabilities.MONEY_CAPABILITY_ITEM)
        if (moneyCap == null) {
            log.info("Money Capability is NULL")
            return InteractionResult.PASS
        }
        if (player.isShiftKeyDown) {
            val withdrawn = vault.withdraw(moneyCap, 50)
            player.displayClientMessage(
                Component.literal(if (withdrawn) "Withdrew ¥50 from the vault!" else "Vault is empty"),
                true
            )
        } else {
            val balance = moneyCap.getMoney()
            if (balance > 0)  vault.deposit(moneyCap, 50)
            player.displayClientMessage(
                Component.literal(if (balance > 0) "Deposited ¥50 into the vault!" else "You have no money to deposit!"),
                true
            )
        }

        return InteractionResult.SUCCESS
    }

    companion object {
        val log = LogManager.getLogger(CreditCardItem::class.java)
    }
}