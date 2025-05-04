package net.Chidoziealways.everythingjapanese;

import net.Chidoziealways.everythingjapanese.block.ModBlocks;
import net.Chidoziealways.everythingjapanese.block.entity.ModBlockEntities;
import net.Chidoziealways.everythingjapanese.block.entity.renderer.PedestalBlockEntityRenderer;
import net.Chidoziealways.everythingjapanese.chakra.ChakraRegenerationHandler;
import net.Chidoziealways.everythingjapanese.commands.ModArgumentTypes;
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes;
import net.Chidoziealways.everythingjapanese.effect.ModEffects;
import net.Chidoziealways.everythingjapanese.enchantment.ModEnchantmentEffects;
import net.Chidoziealways.everythingjapanese.entity.ModEntities;
import net.Chidoziealways.everythingjapanese.entity.client.chair.ChairRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe.IronBattleAxeProjectileRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.sikadeer.SikaDeerRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsRenderer;
import net.Chidoziealways.everythingjapanese.entity.client.ya.YaRenderer;
import net.Chidoziealways.everythingjapanese.item.ModCreativeModeTabs;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.jutsu.ModJutsus;
import net.Chidoziealways.everythingjapanese.loot.ModLootModifiers;
import net.Chidoziealways.everythingjapanese.network.ModNetwork;
import net.Chidoziealways.everythingjapanese.particle.ModParticles;
import net.Chidoziealways.everythingjapanese.particle.PyriteParticles;
import net.Chidoziealways.everythingjapanese.potion.ModPotions;
import net.Chidoziealways.everythingjapanese.recipe.ModRecipes;
import net.Chidoziealways.everythingjapanese.screen.ModMenuTypes;
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberScreen;
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalScreen;
import net.Chidoziealways.everythingjapanese.sound.ModSounds;
import net.Chidoziealways.everythingjapanese.tests.ModGameTests;
import net.Chidoziealways.everythingjapanese.util.KeyPressHandler;
import net.Chidoziealways.everythingjapanese.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Optional;

import static net.Chidoziealways.everythingjapanese.util.ModTags.Items.REPAIRS_NEPHRITE_ARMOR;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(EverythingJapanese.MOD_ID)
public class EverythingJapanese {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "everythingjapanese";
    private static final Logger LOGGER = LogManager.getLogger(EverythingJapanese.class);
    private static final Marker EVERYTHINGJAPANESE = MarkerManager.getMarker("EVERYTHINGJAPANESE");

    public EverythingJapanese(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        MixinBootstrap.init();
        ModGameTests.register(modEventBus);
        ModJutsus.register(modEventBus);
        logDebug("Registering Registries");
        ModBlocks.register(modEventBus);
        logDebug("Registering Blocks");
        ModDataComponentTypes.register(modEventBus);
        logDebug("Registering DataComponents");
        ModSounds.register(modEventBus);
        logDebug("Registering Sounds");
        ModEffects.register(modEventBus);
        logDebug("Registering Effects");
        ModItems.register(modEventBus);
        logDebug("Registering Items");
        ModCreativeModeTabs.register(modEventBus);
        logDebug("Registering CreativeMode Tabs");
        ModPotions.register(modEventBus);
        logDebug("Registering Potions");
        ModEnchantmentEffects.register(modEventBus);
        logDebug("Registering Enchantment Effects");
        ModEntities.register(modEventBus);
        logDebug("Registering Entities");
        ModVillagers.register(modEventBus);
        logDebug("Registering Villagers");
        ModParticles.register(modEventBus);
        logDebug("Registering Particles");
        ModLootModifiers.register(modEventBus);
        logDebug("Registering LootModifiers");
        ModBlockEntities.register(modEventBus);
        logDebug("Registering Block Entities");
        ModMenuTypes.register(modEventBus);
        logDebug("Registering MenuTypes");
        ModRecipes.register(modEventBus);
        logDebug("Registering Recipes");
        logDebug("Hello");
        ModArgumentTypes.register(modEventBus);

        // Register the commonSetup method for mod-loading
        modEventBus.addListener(this::commonSetup);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, ChakraRegenerationHandler::onServerTick);


        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModJutsus.buildLookup();
        ModNetwork.registerPackets();
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
        logInfo("The Server is Starting");
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        debugTagContent(event.getServer());
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

    private static void debugTagContent(MinecraftServer server) {
        RegistryAccess access = server.registryAccess();
        Registry<Item> itemRegistry = access.lookupOrThrow(Registries.ITEM);
        Optional<HolderSet.Named<Item>> optionalTag = itemRegistry.get(REPAIRS_NEPHRITE_ARMOR);

        if (optionalTag.isPresent()) {
            HolderSet.Named<Item> tagSet = optionalTag.get();
            logDebug("Tag is LOADED and contains: " + tagSet.size() + " entries");
            tagSet.forEach(itemHolder -> logDebug(" - " + itemHolder.value()));
        } else {
            logError("Tag is NOT present");
        }
    }

        // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
        @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
        public static class ClientModEvents {
            @SubscribeEvent
            public static void onClientSetup(FMLClientSetupEvent event) {
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.HINOKI_NAEGI.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.YAMAZAKI_BERRY_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP.get(), RenderType.cutout());

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
            public static void onRegisterEvent(RegisterEvent event) {
                /*Optional<HolderSet.Named<Block>> optionalTag = BuiltInRegistries.BLOCK.getTags().filter(namedHolderSet -> namedHolderSet.key().location().equals(ModTags.Blocks.INCORRECT_FOR_NEPHRITE_TOOL.location()))
                        .findFirst();

                if (optionalTag.isPresent()) {
                    LOGGER.info("Tag INCORRECT_FOR_NEPHRITE_TOOL is Bound!");
                    if (optionalTag.isEmpty()) {
                        LOGGER.warn("Tag INCORRECT_FOR_NEPHRITE_TOOL is Empty!");
                    }
                } else {
                    LOGGER.error("Tag INCORRECT_FOR_NEPHRITE_TOOL is Unbound");
                }*/
            }

            @SubscribeEvent
            public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
                event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
            }
        }
}
