package net.Chidoziealways.everythingjapanese.event;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.effect.ModEffects;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.potion.ModPotions;
import net.Chidoziealways.everythingjapanese.villager.ModVillagers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.Chidoziealways.everythingjapanese.item.custom.HammerItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = EverythingJapanese.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(4, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingInteract(PlayerInteractEvent.EntityInteract event){
        if(event.getEntity() instanceof Player player){
            Entity touchedEntity = event.getTarget();
            if(event.getTarget() instanceof Animal) {
                if(player.getMainHandItem() == Items.AIR.getDefaultInstance()) {
                    player.sendSystemMessage(Component.literal(player.getName().getString() + " Just Right Clicked " + touchedEntity.getName().getString()));
                }
            }

        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event){
        if(event.getSource().getDirectEntity() instanceof Player player) {
            if (event.getEntity() instanceof Animal) {
                LivingEntity hitEntity = event.getEntity();
                Item murderItem = player.getMainHandItem().getItem();
                player.sendSystemMessage(Component.literal(player.getName().getString() + " Just Hit a Fricking " + hitEntity.getName().getString() + " with " + murderItem.getName(murderItem.getDefaultInstance()).getString()));
                hitEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 5));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event){
        if(event.getEntity() instanceof Player player){
            player = (Player) event.getEntity();
            if(player.getHealth() <= 4.0F){
                player.addEffect(new MobEffectInstance((ModEffects.ADRENALINE_EFFECT.getHolder().get()), 1000, 2, true, true));            }
        }
    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event){
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.ADRENALINE_POTION.getHolder().get());
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.ARMORER) {
            var trades = event.getTrades();

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.PYRITE_SWORD.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.PYRITE_AXE.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.PYRITE_SHOVEL.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.PYRITE_HOE.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 15),
                    new ItemStack(ModItems.PYRITE_BATTLE_AXE.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.PYRITE_HAMMER.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.PYRITE_HORSE_ARMOR.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(ModItems.PYRITE_HELMET.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(ModItems.PYRITE_CHESTPLATE.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(ModItems.PYRITE_LEGGINGS.get(), 1), 6, 4, 0.05f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(ModItems.PYRITE_BOOTS.get(), 1), 6, 4, 0.05f));


        }
        if (event.getType() == ModVillagers.FURNITURE_MAKER.get()) {
            var trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.DIAMOND, 18),
                    new ItemStack(ModBlocks.CHAIR.get(), 20), 6, 4, 0.6f));
        }
    }

    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.DIAMOND, 12),
                new ItemStack(ModItems.RADIATION_STAFF.get(), 1), 1, 10, 0.2f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.NETHERITE_INGOT, 8),
                new ItemStack(ModItems.AO_TO_NATSU_MUSIC_DISC.get(), 1), 1 ,10, 0.2f));
    }

}
