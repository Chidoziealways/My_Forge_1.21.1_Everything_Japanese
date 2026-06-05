package net.Chidoziealways.everythingjapanese.effect

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModEffects {
    val MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, JAPANESE_MOD_ID)

    val ADRENALINE_EFFECT = MOB_EFFECTS.register(
        "adrenaline")
        { ->
            AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x36ebab)
                .addAttributeModifier(
                    Attributes.MOVEMENT_SPEED,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "adrenaline"),
                    -0.25,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
                .addAttributeModifier(
                    Attributes.MINING_EFFICIENCY,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "adrenaline"),
                    2.0,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
                .addAttributeModifier(
                    Attributes.SUBMERGED_MINING_SPEED,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "adrenaline"),
                    2.0,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        }


    fun register(eventBus: IEventBus) {
        MOB_EFFECTS.register(eventBus)
    }
}
