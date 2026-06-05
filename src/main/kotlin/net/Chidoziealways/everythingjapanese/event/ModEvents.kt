package net.Chidoziealways.everythingjapanese.event

import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.item.JModItems
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
import net.minecraft.world.entity.npc.villager.VillagerProfession
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.item.trading.ItemCost
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraft.world.item.trading.VillagerTrades
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent
import net.neoforged.neoforge.event.level.BlockEvent
import thedarkcolour.kotlinforforge.common.KotlinMod

@KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID)
object ModEvents {
    private val HARVESTED_BLOCKS: MutableSet<BlockPos?> = HashSet<BlockPos?>()

    @SubscribeEvent
    fun onHammerUsage(event: BlockEvent.BreakEvent) {
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

    @SubscribeEvent
    fun onLivingInteract(event: PlayerInteractEvent.EntityInteract) {
        if (event.entity is Player) {
            val player = event.entity
            val touchedEntity = event.target
            if (event.target is Animal) {
                if (player.mainHandItem == Items.AIR.defaultInstance) {
                    player.sendOverlayMessage(
                        Component.literal(
                            player.name.string + " Just Right Clicked " + touchedEntity!!.getName()
                                .string
                        )
                    )
                }
            }
        }
    }

    @SubscribeEvent
    fun onLivingDamage(event: LivingDamageEvent.Pre) {
        if (event.source.entity is Player) {
            val player: Player = event.source.entity as Player
            if (event.entity is Animal) {
                val hitEntity = event.entity
                val murderItem: Item = player.mainHandItem.item
                player.sendOverlayMessage(
                    Component.literal(
                        player.name.string + " Just Hit a Fricking " + hitEntity.getName()
                            .string + " with " + murderItem.getName(murderItem.defaultInstance).string
                    )
                )
                hitEntity.addEffect(MobEffectInstance(MobEffects.POISON, 600, 5))
            }
        }
    }
    
    @SubscribeEvent
    fun onPlayerHurt(event: LivingDamageEvent.Pre) {
        if (event.entity is Player) {
            val player: Player = event.entity as Player
            if (player.health <= 4.0f) {
                player.addEffect(
                    MobEffectInstance(
                        (ModEffects.ADRENALINE_EFFECT.delegate),
                        1000,
                        2,
                        true,
                        true
                    )
                )
            }
        }
    }

    
    @SubscribeEvent
    fun onBrewingRecipeRegister(event: RegisterBrewingRecipesEvent) {
        val builder = event.builder

        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.ADRENALINE_POTION)
    }

    
    /*@SubscribeEvent
    fun addCustomTrades(event: TradesEvent) {
        if (event.getType() === VillagerProfession.ARMORER) {
            val trades = event.getTrades()

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(JModItems.PYRITE_SWORD, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(JModItems.PYRITE_AXE, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(JModItems.PYRITE_SHOVEL, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(JModItems.PYRITE_HOE, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 15),
                    ItemStack(JModItems.PYRITE_BATTLE_AXE, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(JModItems.PYRITE_HAMMER, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 10),
                    ItemStack(JModItems.PYRITE_HORSE_ARMOR, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(JModItems.PYRITE_HELMET, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(JModItems.PYRITE_CHESTPLATE, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(JModItems.PYRITE_LEGGINGS, 1), 6, 4, 0.05f
                )
            })

            trades.get(2)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.EMERALD, 16),
                    ItemStack(JModItems.PYRITE_BOOTS, 1), 6, 4, 0.05f
                )
            })
        }
        if (event.getType() === ModVillagers.FURNITURE_MAKER.getKey()) {
            val trades = event.getTrades()

            trades.get(1)!!.add(VillagerTrades.ItemListing { level, pTrader: Entity, pRandom: RandomSource ->
                MerchantOffer(
                    ItemCost(Items.DIAMOND, 18),
                    ItemStack(JModBlocks.CHAIR, 20), 6, 4, 0.6f
                )
            })
        }
    }

    
    @SubscribeEvent
    fun addWanderingTrades(event: WandererTradesEvent) {
        val genericTrades = event.genericTrades
        val rareTrades = event.rareTrades

        genericTrades.add { level, entity, randomSource -> MerchantOffer(
            ItemCost(Items.DIAMOND, 12),
            ItemStack(JModItems.RADIATION_STAFF, 1), 1, 10, 0.2f
        ) }

        rareTrades.add { level, entity, randomSource -> MerchantOffer(
            ItemCost(Items.NETHERITE_INGOT, 8),
            ItemStack(JModItems.AO_TO_NATSU_MUSIC_DISC, 1), 1, 10, 0.2f
        ) }
    }*/
}
