package net.Chidoziealways.everythingjapanese

import net.Chidoziealways.everythingjapanese.block.ModBlocks
import net.Chidoziealways.everythingjapanese.block.entity.renderer.PedestalBlockEntityRenderer
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.entity.custom.PedestalBlockEntity
import net.Chidoziealways.everythingjapanese.chakra.ChakraRegenerationHandler
import net.Chidoziealways.everythingjapanese.commands.ModArgumentTypes
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.datagen.DataGenerators
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.enchantment.ModEnchantmentEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.client.chair.ChairRenderer
import net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe.IronBattleAxeProjectileRenderer
import net.Chidoziealways.everythingjapanese.entity.client.sikadeer.SikaDeerRenderer
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsRenderer
import net.Chidoziealways.everythingjapanese.entity.client.ya.YaRenderer
import net.Chidoziealways.everythingjapanese.entity.custom.*
import net.Chidoziealways.everythingjapanese.event.ModRegistryEvents
import net.Chidoziealways.everythingjapanese.item.ModCreativeModeTabs
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.jutsu.ModJutsus
import net.Chidoziealways.everythingjapanese.loot.ModLootModifiers
import net.Chidoziealways.everythingjapanese.network.ModNetwork
import net.Chidoziealways.everythingjapanese.particle.ModParticles
import net.Chidoziealways.everythingjapanese.particle.PyriteParticles
import net.Chidoziealways.everythingjapanese.poi.ModPoiTypes
import net.Chidoziealways.everythingjapanese.potion.ModPotions
import net.Chidoziealways.everythingjapanese.quest.Quest
import net.Chidoziealways.everythingjapanese.quest.QuestConditionRegistry
import net.Chidoziealways.everythingjapanese.quest.QuestStageProgressionHandler
import net.Chidoziealways.everythingjapanese.quest.conditions.CollectItemCondition
import net.Chidoziealways.everythingjapanese.quest.conditions.KillEntityCondition
import net.Chidoziealways.everythingjapanese.quest.conditions.LocatePlaceCondition
import net.Chidoziealways.everythingjapanese.recipe.ModRecipes
import net.Chidoziealways.everythingjapanese.screen.ModMenuTypes
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberScreen
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalScreen
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.Chidoziealways.everythingjapanese.structure.ModStructuresR
import net.Chidoziealways.everythingjapanese.tests.ModGameTests
import net.Chidoziealways.everythingjapanese.util.ModRegistries
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.villager.ModVillagers
import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.particle.ParticleEngine.SpriteParticleRegistration
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.chunk.ChunkSectionLayer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.core.Cloner
import net.minecraft.core.Holder
import net.minecraft.core.RegistryAccess
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.minecraft.server.MinecraftServer
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.ComposterBlock
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers
import net.minecraftforge.client.event.RegisterParticleProvidersEvent
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.event.TickEvent.ServerTickEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.event.server.ServerStartedEvent
import net.minecraftforge.event.server.ServerStartingEvent
import net.minecraftforge.eventbus.api.listener.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.registries.NewRegistryEvent
import net.minecraftforge.registries.RegisterEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.Marker
import org.apache.logging.log4j.MarkerManager
import org.spongepowered.asm.launch.MixinBootstrap
import thedarkcolour.common.KotlinBus
import thedarkcolour.common.KotlinMod
import thedarkcolour.kotlinforforge.KotlinModLoadingContext
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import java.lang.invoke.MethodHandles
import java.util.function.Consumer

// The value here should match an entry in the META-INF/mods.toml file
const val MOD_ID: String = "everythingjapanese"
private var LOGGER: Logger? = null
private var EVERYTHINGJAPANESE: Marker? = null
@KotlinMod(MOD_ID)
object EverythingJapanese {
    init {
        LOGGER = LogManager.getLogger(EverythingJapanese::class.java)
        EVERYTHINGJAPANESE = MarkerManager.getMarker("EVERYTHINGJAPANESE")

        // Register ourselves for server and other game events we are interested in
        MOD_BUS.register(MethodHandles.lookup(), this)
        MixinBootstrap.init()
        ModPoiTypes.register(MOD_BUS)
        ModGameTests.register(MOD_BUS)
        ModJutsus.register(MOD_BUS)
        logDebug("Registering Registries")
        ModBlocks.register(MOD_BUS)
        logDebug("Registering Blocks")
        ModDataComponentTypes.register(MOD_BUS)
        logDebug("Registering DataComponents")
        ModSounds.register(MOD_BUS)
        logDebug("Registering Sounds")
        ModEffects.register(MOD_BUS)
        logDebug("Registering Effects")

        ModItems.register(MOD_BUS)
        logDebug("Registering Items")

        ModCreativeModeTabs.register(MOD_BUS)
        logDebug("Registering CreativeMode Tabs")

        ModPotions.register(MOD_BUS)
        logDebug("Registering Potions")

        ModEnchantmentEffects.register(MOD_BUS)
        logDebug("Registering Enchantment Effects")

        ModEntities.register(MOD_BUS)
        logDebug("Registering Entities")

        ModVillagers.register(MOD_BUS)
        logDebug("Registering Villagers")

        ModParticles.register(MOD_BUS)
        logDebug("Registering Particles")

        ModLootModifiers.register(MOD_BUS)
        logDebug("Registering LootModifiers")

        ModBlockEntities.register(MOD_BUS)
        logDebug("Registering Block Entities")

        ModMenuTypes.register(MOD_BUS)
        logDebug("Registering MenuTypes")

        ModRecipes.register(MOD_BUS)
        logDebug("Registering Recipes")

        logDebug("Hello")

        ModArgumentTypes.register(MOD_BUS)
        ModStructuresR.register(MOD_BUS)

        //ModTerrablender.registerBiomes();

        // Register the commonSetup method for mod-loading

        ServerTickEvent.Post.BUS.addListener { event: ServerTickEvent.Post ->
            ChakraRegenerationHandler.onServerTick(
                event
            )
        }

        ServerTickEvent.Post.BUS.addListener { event: ServerTickEvent.Post ->
            QuestStageProgressionHandler.onServerTick(
                event
            )
        }

        LivingDeathEvent.BUS.addListener(KillEntityCondition::onEntityKilled)

        EntityItemPickupEvent.BUS.addListener(CollectItemCondition::onItemPickup)


        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        LOADING_CONTEXT.registerConfig(ModConfig.Type.COMMON, Config.SPEC)
    }

    fun registerQuestConditions() {
        QuestConditionRegistry.register("collect", CollectItemCondition)
        QuestConditionRegistry.register("kill", KillEntityCondition)
        QuestConditionRegistry.register("locate", LocatePlaceCondition)
    }

    @SubscribeEvent
    private fun commonSetup(event: FMLCommonSetupEvent) {
        ModJutsus.buildLookup()
        ModNetwork.registerPackets()
        event.enqueueWork {
            ComposterBlock.COMPOSTABLES.put(ModItems.RICE_SEEDS!!.get(), 0.6f)
            ComposterBlock.COMPOSTABLES.put(ModItems.RAW_RICE!!.get(), 0.85f)
            registerQuestConditions()
        }
    }

    // Add the example block item to the building blocks tab
    @SubscribeEvent
    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey === CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.PYRITE_INGOT)
        }

        if (event.tabKey === CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.PYRITE_BLOCK)
        }

        if (event.tabKey === CreativeModeTabs.OP_BLOCKS) {
            event.accept(ModItems.CHISEL)
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    fun onServerStarting(event: ServerStartingEvent?) {
        logInfo("The Server is Starting")
    }

    @SubscribeEvent
    fun onServerStarted(event: ServerStartedEvent) {
        debugTagContent(event.getServer())
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @KotlinMod.KotlinEventBusSubscriber(modId = MOD_ID, bus = KotlinBus.MOD, value = [Dist.CLIENT])
    object ClientModEvents {
        @JvmStatic
        @SubscribeEvent
        fun onClientSetup(event: FMLClientSetupEvent?) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HINOKI_NAEGI.get(), ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.YAMAZAKI_BERRY_BUSH.get(), ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.RICE_CROP.get(), ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PYRITE_DOOR.get(), ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PYRITE_TRAPDOOR.get(), ChunkSectionLayer.CUTOUT)

            EntityRenderers.register(
                ModEntities.TRICERATOPS!!.get()
            ) { pContext: EntityRendererProvider.Context -> TriceratopsRenderer(pContext) }
            EntityRenderers.register(
                ModEntities.SIKA_DEER!!.get()
            ) { pContext: EntityRendererProvider.Context -> SikaDeerRenderer(pContext) }
            EntityRenderers.register(
                ModEntities.IRON_BATTLE_AXE!!.get()
            ) { pContext: EntityRendererProvider.Context ->
                IronBattleAxeProjectileRenderer(pContext)
            }
            EntityRenderers.register(
                ModEntities.CHAIR!!.get()
            ) { pContext: EntityRendererProvider.Context -> ChairRenderer(pContext) }
            EntityRenderers.register(
                ModEntities.YA!!.get()
            ) { pContext: EntityRendererProvider.Context -> YaRenderer(pContext) }

            MenuScreens.register(
                ModMenuTypes.PEDESTAL_MENU!!.get()
            ) { menu, inventory, title ->
                PedestalScreen(menu!!, inventory, title)
            }

            MenuScreens.register(
                ModMenuTypes.GROWTH_CHAMBER_MENU!!.get()
            ) { menu, inventory, title ->
                GrowthChamberScreen(menu!!, inventory, title)
            }
        }

        @JvmStatic
        @SubscribeEvent
        fun registerParticleProvider(event: RegisterParticleProvidersEvent) {
            event.registerSpriteSet(
                ModParticles.PYRITE_PARTICLES!!.get()
            ) { spriteSet: SpriteSet -> PyriteParticles.Provider(spriteSet) }
        }

        @JvmStatic
        @SubscribeEvent
        fun onRegisterEvent(event: RegisterEvent?) {
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

        @JvmStatic
        @SubscribeEvent
        fun registerBER(event: RegisterRenderers) {
            event.registerBlockEntityRenderer<PedestalBlockEntity>(
                ModBlockEntities.PEDESTAL_BE!!.get()
            ) { context ->
                PedestalBlockEntityRenderer(context)
            }
        }
    }

    // Define mod id in a common place for everything to referenc
    fun logInfo(message: Any?) {
        LOGGER!!.info(message.toString())
    }

    fun logError(message: Any?) {
        LOGGER!!.error(message.toString())
    }

    fun logDebug(message: Any?) {
        LOGGER!!.debug(message.toString())
    }

    private fun debugTagContent(server: MinecraftServer) {
        val access: RegistryAccess = server.registryAccess()
        val itemRegistry = access.lookupOrThrow(Registries.ITEM)
        val optionalTag = itemRegistry.get(ModTags.Items.REPAIRS_NEPHRITE_ARMOR)

        if (optionalTag.isPresent) {
            val tagSet = optionalTag.get()
            logDebug("Tag is LOADED and contains: " + tagSet.size() + " entries")
            tagSet.forEach(Consumer { itemHolder: Holder<Item?>? -> logDebug(" - " + itemHolder!!.value()) })
        } else {
            logError("Tag is NOT present")
        }
    }
}
