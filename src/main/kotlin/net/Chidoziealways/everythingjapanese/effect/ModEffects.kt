package net.Chidoziealways.everythingjapanese.effect

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModEffects {
    val MOB_EFFECTS: DeferredRegister<MobEffect?> =
        DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID)

    @JvmField
    val ADRENALINE_EFFECT: RegistryObject<MobEffect?>? = MOB_EFFECTS.register<MobEffect?>(
        "adrenaline",
        Supplier {
            AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x36ebab)
                .addAttributeModifier(
                    Attributes.MOVEMENT_SPEED,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "adrenaline"),
                    -0.25,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
                .addAttributeModifier(
                    Attributes.MINING_EFFICIENCY,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "adrenaline"),
                    2.0,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
                .addAttributeModifier(
                    Attributes.SUBMERGED_MINING_SPEED,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "adrenaline"),
                    2.0,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        })


    fun register(eventBus: BusGroup?) {
        MOB_EFFECTS.register(eventBus)
    }
}
