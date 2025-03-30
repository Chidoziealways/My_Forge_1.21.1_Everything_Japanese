package net.Chidoziealways.everythingjapanese;

import com.mojang.logging.LogUtils;
import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.entity.ModBlockEntities;
import net.Chidoziealways.everythingjapanese.block.entity.renderer.PedestalBlockEntityRenderer;
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes;
import net.Chidoziealways.everythingjapanese.effect.ModEffects;
import net.Chidoziealways.everythingjapanese.enchantment.ModEnchantmentEffects;
import net.Chidoziealways.everythingjapanese.entity.ModEntities;
import net.Chidoziealways.everythingjapanese.entity.client.ChairRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe.IronBattleAxeProjectileRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.sikadeer.SikaDeerRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.ya.YaRenderer;
import net.Chidoziealways.everythingjapanese.item.ModCreativeModeTabs;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.loot.ModLootModifiers;
import net.Chidoziealways.everythingjapanese.particle.ModParticles;
import net.Chidoziealways.everythingjapanese.particle.PyriteParticles;
import net.Chidoziealways.everythingjapanese.potion.ModPotions;
import net.Chidoziealways.everythingjapanese.screen.ModMenuTypes;
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberScreen;
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalScreen;
import net.Chidoziealways.everythingjapanese.sound.ModSounds;
import net.Chidoziealways.everythingjapanese.util.ModItemProperties;
import net.Chidoziealways.everythingjapanese.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;



// The value here should match an entry in the META-INF/mods.toml file
@Mod(EverythingJapanese.MOD_ID)
public class EverythingJapanese {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "everythingjapanese";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();



    public EverythingJapanese(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        try {
            logInfo("Initializing block registrations...");
            ModBlocks.register(modEventBus);


        } catch (NullPointerException e) {
            logError("Exception from The Registration of the ModBlocks Class");
        }

        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);


        ModDataComponentTypes.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModEntities.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModParticles.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register the commonSetup method for mod-loading
        modEventBus.addListener(this::commonSetup);

        /*
        HEllo {@Link SubscribeEvent} annoted methods
         <dl>
     *     <dt>Object Instance</dt>
     *     <dd>Scanned for <em>non-static</em> methods annotated with {@link SubscribeEvent} and creates listeners for
     *     each method found.</dd>
     *     <dt>Class Instance</dt>
     *     <dd>Scanned for <em>static</em> methods annotated with {@link SubscribeEvent} and creates listeners for
     *     each method found.</dd>
     * </dl>
         */

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.RICE_SEEDS.get(), 0.6f);
            ComposterBlock.COMPOSTABLES.put(ModItems.RAW_RICE.get(), 0.85f);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.PYRITE_INGOT);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.PYRITE_BLOCK);
        }

        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(ModItems.CHISEL);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    public static void logInfo(Object message) {
        LOGGER.info(String.valueOf(message));
    }

    public static void logError(Object message) {
        LOGGER.error(String.valueOf(message));
    }

    public static void logDebug(Object message) {
        LOGGER.debug(String.valueOf(message));
    }

        // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
        @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
        public static class ClientModEvents {
            @SubscribeEvent
            public static void onClientSetup(FMLClientSetupEvent event) {
                ModItemProperties.addCustomItemProperties();

                EntityRenderers.register(ModEntities.TRICERATOPS.get(), TriceratopsRenderer::new);
                EntityRenderers.register(ModEntities.SIKA_DEER.get(), SikaDeerRenderer::new);
                EntityRenderers.register(ModEntities.IRON_BATTLE_AXE.get(), IronBattleAxeProjectileRenderer::new);
                EntityRenderers.register(ModEntities.CHAIR.get(), ChairRenderer::new);
                EntityRenderers.register(ModEntities.YA.get(), YaRenderer::new);

                MenuScreens.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
                MenuScreens.register(ModMenuTypes.GROWTH_CHAMBER_MENU.get(), GrowthChamberScreen::new);
            }

            @SubscribeEvent
            public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
                event.registerSpriteSet(ModParticles.PYRITE_PARTICLES.get(), PyriteParticles.Provider::new);
            }

            @SubscribeEvent
            public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
                event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
            }
        }
}
