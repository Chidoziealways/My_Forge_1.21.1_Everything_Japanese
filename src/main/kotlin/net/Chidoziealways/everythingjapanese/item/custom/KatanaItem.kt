package net.Chidoziealways.everythingjapanese.item.custom

import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.item.ModToolMaterials
import net.Chidoziealways.everythingjapanese.item.katana.BladeType
import net.Chidoziealways.everythingjapanese.item.katana.Wrapping
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemAttributeModifiers

class KatanaItem(props: Properties): Item(props.sword(ModToolMaterials.STEEL, 4f, 3f).stacksTo(1)) {

    override fun onCraftedBy(stack: ItemStack, player: Player) {
        super.onCraftedBy(stack, player)
        setBlade(stack, BladeType.STEEL)
        setWrap(stack, Wrapping.WHITE)
    }

    companion object {
        private val DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "katana_damage")
        private val SPEED_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "katana_speed")

        fun setBlade(stack: ItemStack, type: BladeType) {
            stack.set(ModDataComponentTypes.BLADE.get(), type)
        }

        fun getBlade(stack: ItemStack): BladeType? {
            return stack.get(ModDataComponentTypes.BLADE.get())
        }

        fun setWrap(stack: ItemStack, wrap: Wrapping) {
            stack.set(ModDataComponentTypes.WRAPPING.get(), wrap)
        }

        fun getWrap(stack: ItemStack): Wrapping? {
            return stack.get(ModDataComponentTypes.WRAPPING.get())
        }
    }

    override fun hurtEnemy(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
        val blade = stack.get(ModDataComponentTypes.BLADE.get())
        if (blade?.id == "nephrite") {
            target.igniteForSeconds(3f)
        }
        super.hurtEnemy(stack, target, attacker)
    }

    override fun getMaxDamage(stack: ItemStack): Int {
        val blade = stack.get(ModDataComponentTypes.BLADE.get())
        return blade?.material?.durability ?: super.getMaxDamage(stack)
    }

    override fun getDefaultAttributeModifiers(stack: ItemStack): ItemAttributeModifiers {
        val blade = stack.get(ModDataComponentTypes.BLADE.get())
        val wrap = stack.get(ModDataComponentTypes.WRAPPING.get())

        val damage = (blade?.material?.attackDamageBonus ?: 3.0f) + 2.0f
        val speed = -2.4f + (wrap?.speedBonus ?: 0f)

        return ItemAttributeModifiers.builder()
            .add(
                Attributes.ATTACK_DAMAGE,
                AttributeModifier(DAMAGE_ID, damage.toDouble(), AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
            .add(
                Attributes.ATTACK_SPEED,
                AttributeModifier(SPEED_ID, speed.toDouble(), AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            ).build()
    }


    override fun getName(stack: ItemStack): Component {
        val blade = stack.get(ModDataComponentTypes.BLADE.get())
        val wrap = stack.get(ModDataComponentTypes.WRAPPING.get())
        val name = buildString {
            append(blade?.displayName ?: "Steel")
            append(" Katana")
            if (wrap != null) append(" (${wrap.id} wrap)")
        }
        return Component.literal(name)
    }
}