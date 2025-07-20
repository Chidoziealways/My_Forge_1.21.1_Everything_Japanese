package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.EverythingJapanese
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.item.custom.HammerItem
import net.Chidoziealways.everythingjapanese.potion.ModPotions
import net.Chidoziealways.everythingjapanese.villager.ModVillagers
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.RandomSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.item.trading.ItemCost
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract
import net.minecraftforge.event.level.BlockEvent.BreakEvent
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.event.village.WandererTradesEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.FORGE)
object ModEvents {
    private val HARVESTED_BLOCKS: MutableSet<BlockPos?> = HashSet<BlockPos?>()

    @JvmStatic
    @SubscribeEvent
    fun onHammerUsage(event: BreakEvent) {
        val player = event.player
        val mainHandItem = player.mainHandItem

        if (mainHandItem.item is HammerItem && player is ServerPlayer) {
            val initialBlockPos = event.pos
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return
            }

            for (pos in HammerItem.Companion.getBlocksToBeDestroyed(4, initialBlockPos, player)) {
                if (pos === initialBlockPos) {
                    continue
                }

                HARVESTED_BLOCKS.add(pos)
                player.gameMode.destroyBlock(pos)
                HARVESTED_BLOCKS.remove(pos)
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onLivingInteract(event: EntityInteract) {
        if (event.entity is Player) {
            val player = event.entity
            val touchedEntity = event.target
            if (event.target is Animal) {
                if (player.mainHandItem == Items.AIR.defaultInstance) {
                    player.displayClientMessage(
                        Component.literal(
                            player.name.string + " Just Right Clicked " + touchedEntity!!.getName()
                                .string
                        ), true
                    )
                }
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onLivingDamage(event: LivingDamageEvent) {
        if (event.source.directEntity is Player) {
            val player: Player = event.source.directEntity as Player
            if (event.entity is Animal) {
                val hitEntity = event.entity
                val murderItem: Item = player.mainHandItem.item
                player.displayClientMessage(
                    Component.literal(
                        player.name.string + " Just Hit a Fricking " + hitEntity.getName()
                            .string + " with " + murderItem.getName(murderItem.defaultInstance).string
                    ), true
                )
                hitEntity.addEffect(MobEffectInstance(MobEffects.POISON, 600, 5))
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerHurt(event: LivingHurtEvent) {
        if (event.entity is Player) {
            val player: Player = event.entity as Player
            if (player.health <= 4.0f) {
                player.addEffect(
                    MobEffectInstance(
                        (ModEffects.ADRENALINE_EFFECT!!.registryObject.holder.get()),
                        1000,
                        2,
                        true,
                        true
                    )
                )
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onBrewingRecipeRegister(event: BrewingRecipeRegisterEvent) {
        val builder = event.builder

        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.ADRENALINE_POTION!!.getHolder().get())
    }

    @JvmStatic
    @SubscribeEvent
    fun addCustomTrades(event: VillagerTradesEvent) {
        if (event.getType() === VillagerProfession.ARMORER) {
            val trades = event.getTrades()

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(ModItems.PYRITE_SWORD!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(ModItems.PYRITE_AXE!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(ModItems.PYRITE_SHOVEL!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(ModItems.PYRITE_HOE!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 15),
                    ItemStack(ModItems.PYRITE_BATTLE_AXE!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(ModItems.PYRITE_HAMMER!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(ModItems.PYRITE_HORSE_ARMOR!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(ModItems.PYRITE_HELMET!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(ModItems.PYRITE_CHESTPLATE!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(ModItems.PYRITE_LEGGINGS!!.get(), 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(ModItems.PYRITE_BOOTS!!.get(), 1), 6, 4, 0.05f
                )
            })
        }
        if (event.getType() === ModVillagers.FURNITURE_MAKER.getKey()) {
            val trades = event.getTrades()

            trades.get(1)!!.add(ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.DIAMOND, 18),
                    ItemStack(ModBlocks.CHAIR.get(), 20), 6, 4, 0.6f
                )
            })
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun addWanderingTrades(event: WandererTradesEvent) {
        for (pool in event.getPools()) {
            pool.getEntries().add((ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.DIAMOND, 12),
                    ItemStack(ModItems.RADIATION_STAFF!!.get(), 1), 1, 10, 0.2f
                )
            }))

            pool.getEntries().add((ItemListing { pTrader: Entity?, pRandom: RandomSource? ->
                MerchantOffer(
                    ItemCost(Items.NETHERITE_INGOT, 8),
                    ItemStack(ModItems.AO_TO_NATSU_MUSIC_DISC!!.get(), 1), 1, 10, 0.2f
                )
            }))
        }
    }
}
