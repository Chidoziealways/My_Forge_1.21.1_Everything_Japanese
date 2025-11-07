package net.Chidoziealways.everythingjapanese

import net.Chidoziealways.everythingjapanese.attachments.ModAttachments
import net.Chidoziealways.everythingjapanese.block.JModBlocks
import net.Chidoziealways.everythingjapanese.block.entity.renderer.hanging_scroll.HangingScrollBlockEntityRenderer
import net.Chidoziealways.everythingjapanese.block.entity.renderer.shoji_door.ShojiDoorRenderer
import net.Chidoziealways.everythingjapanese.block.entity.renderer.fusuma_door.FusumaDoorRenderer
import net.Chidoziealways.everythingjapanese.block.entity.renderer.pedestal.PedestalBlockEntityRenderer
import net.Chidoziealways.everythingjapanese.entity.ModBlockEntities
import net.Chidoziealways.everythingjapanese.commands.ModArgumentTypes
import net.Chidoziealways.everythingjapanese.component.ModDataComponentTypes
import net.Chidoziealways.everythingjapanese.effect.ModEffects
import net.Chidoziealways.everythingjapanese.enchantment.ModEnchantmentEffects
import net.Chidoziealways.everythingjapanese.entity.ModEntities
import net.Chidoziealways.everythingjapanese.entity.client.bullet.BulletRenderer
import net.Chidoziealways.everythingjapanese.entity.client.chair.ChairRenderer
import net.Chidoziealways.everythingjapanese.entity.client.chiretsusho.ChiretsuShoProjectileRenderer
import net.Chidoziealways.everythingjapanese.entity.client.cursed_samurai.CursedSamuraiRenderer
import net.Chidoziealways.everythingjapanese.entity.client.ekiretsusho.EkiretsuShoProjectileRenderer
import net.Chidoziealways.everythingjapanese.entity.client.ironbattleaxe.IronBattleAxeProjectileRenderer
import net.Chidoziealways.everythingjapanese.entity.client.sikadeer.SikaDeerRenderer
import net.Chidoziealways.everythingjapanese.entity.client.triceratops.TriceratopsRenderer
import net.Chidoziealways.everythingjapanese.entity.client.ya.YaRenderer
import net.Chidoziealways.everythingjapanese.fluids.ModFluidTypes
import net.Chidoziealways.everythingjapanese.fluids.ModFluids
import net.Chidoziealways.everythingjapanese.item.JModCreativeModeTabs
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.jutsu.ModJutsus
import net.Chidoziealways.everythingjapanese.loot.ModLootModifiers
import net.Chidoziealways.everythingjapanese.particle.ModParticles
import net.Chidoziealways.everythingjapanese.particle.PyriteParticles
import net.Chidoziealways.everythingjapanese.poi.ModPoiTypes
import net.Chidoziealways.everythingjapanese.potion.ModPotions
import net.Chidoziealways.everythingjapanese.recipe.ModRecipes
import net.Chidoziealways.everythingjapanese.screen.ModMenuTypes
import net.Chidoziealways.everythingjapanese.screen.custom.calligraphytable.CalligraphyTableScreen
import net.Chidoziealways.everythingjapanese.screen.custom.growthchamber.GrowthChamberScreen
import net.Chidoziealways.everythingjapanese.screen.custom.pedestal.PedestalScreen
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.Chidoziealways.everythingjapanese.stats.ModStats
import net.Chidoziealways.everythingjapanese.tests.ModGameTests
import net.Chidoziealways.everythingjapanese.util.ModTags
import net.Chidoziealways.everythingjapanese.villager.ModVillagers
import net.minecraft.client.Minecraft
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.chunk.ChunkSectionLayer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.RegistryAccess
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.material.FluidState
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModList
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.client.NeoForgeRenderTypes
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.client.event.RegisterNamedRenderTypesEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.registries.RegisterEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.Marker
import org.apache.logging.log4j.MarkerManager
import org.spongepowered.asm.launch.MixinBootstrap
import thedarkcolour.kotlinforforge.common.KotlinMod
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_CONTEXT
import java.util.function.Consumer

// The value here should match an entry in the META-INF/neoforge.mods.toml file
const val JAPANESE_MOD_ID: String = "everythingjapanese"
private var LOGGER: Logger? = null
private var EVERYTHINGJAPANESE: Marker? = null
@KotlinMod(JAPANESE_MOD_ID)
object EverythingJapanese {
    init {
        LOGGER = LogManager.getLogger(EverythingJapanese::class.java)
        EVERYTHINGJAPANESE = MarkerManager.getMarker("EVERYTHINGJAPANESE")

        MOD_BUS.register(this)
        MixinBootstrap.init()
        ModPoiTypes.register(MOD_BUS)
        ModGameTests.register(MOD_BUS)
        ModJutsus.register(MOD_BUS)
        logDebug("Registering Registries")
        JModBlocks.register(MOD_BUS)
        logDebug("Registering Blocks")
        ModStats.register(MOD_BUS)
        ModDataComponentTypes.register(MOD_BUS)
        logDebug("Registering DataComponents")
        ModSounds.register(MOD_BUS)
        logDebug("Registering Sounds")
        ModEffects.register(MOD_BUS)
        logDebug("Registering Effects")

        JModItems.register(MOD_BUS)
        logDebug("Registering Items")

        JModCreativeModeTabs.register(MOD_BUS)
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

        ModFluidTypes.register(MOD_BUS)
        ModFluids.register(MOD_BUS)
        ModAttachments.register(MOD_BUS)

        if (ModList.get().isLoaded("everythingkorean")) {
            logInfo("EVERYTHING KOREAN LOCKED AND LOADED!")
        }

        logDebug("Hello")

        ModArgumentTypes.register(MOD_BUS)

        //ModTerrablender.registerBiomes();

        // Register the commonSetup method for mod-loading

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        MOD_CONTEXT.container.registerConfig(ModConfig.Type.COMMON, Config.SPEC)
    }

    @SubscribeEvent
    private fun commonSetup(event: FMLCommonSetupEvent) {
    }

    // Add the example block item to the building blocks tab
    @SubscribeEvent
    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey === CreativeModeTabs.INGREDIENTS) {
            event.accept(JModItems.PYRITE_INGOT)
        }

        if (event.tabKey === CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(JModBlocks.PYRITE_BLOCK)
        }

        if (event.tabKey === CreativeModeTabs.OP_BLOCKS) {
            event.accept(JModItems.CHISEL)
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @KotlinMod.KotlinEventBusSubscriber(modId = JAPANESE_MOD_ID, value = [Dist.CLIENT])
    object ClientModEvents {
        @SubscribeEvent
        fun onRegisterRender(event: RegisterNamedRenderTypesEvent) {
            event.register(ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "washi_window"),
                ChunkSectionLayer.TRANSLUCENT,
                NeoForgeRenderTypes.ITEM_LAYERED_TRANSLUCENT.get()
            )
        }

        @SubscribeEvent
        fun onRegisterClientExtensions(event: RegisterClientExtensionsEvent) {
            event.registerFluidType(object : IClientFluidTypeExtensions {
                val BLOOD_STILL = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/blood_still")
                val BLOOD_FLOW = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/blood_flow")
                val BLOOD_OVERLAY = ResourceLocation.fromNamespaceAndPath(JAPANESE_MOD_ID, "block/blood_overlay")

                override fun getStillTexture(): ResourceLocation = BLOOD_STILL

                override fun getFlowingTexture(): ResourceLocation = BLOOD_FLOW

                override fun getOverlayTexture(): ResourceLocation? = BLOOD_OVERLAY

                override fun getRenderOverlayTexture(mc: Minecraft): ResourceLocation? {
                    return ResourceLocation.withDefaultNamespace("textures/misc/underwater.png")
                }

                override fun getTintColor(): Int = 0xFFFF0000.toInt()

                override fun getTintColor(state: FluidState, getter: BlockAndTintGetter, pos: BlockPos): Int = 0xFFFF0000.toInt()

            }, ModFluidTypes.BLOOD_TYPE)
        }

        @SubscribeEvent
        fun onRegisterMenuScreens(event: RegisterMenuScreensEvent) {
            event.register(
                ModMenuTypes.PEDESTAL_MENU
            ) { menu, inventory, title ->
                PedestalScreen(menu, inventory, title)
            }

            event.register(
                ModMenuTypes.CALLIGRAPHY_TABLE_MENU
            ) { menu, inventory, title ->
                CalligraphyTableScreen(menu, inventory, title)
            }

            event.register(
                ModMenuTypes.GROWTH_CHAMBER_MENU
            ) { menu, inventory, title ->
                GrowthChamberScreen(menu, inventory, title)
            }
        }

        
        @SubscribeEvent
        fun onClientSetup(event: FMLClientSetupEvent?) {
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.HINOKI_NAEGI, ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.YAMAZAKI_BERRY_BUSH, ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.RICE_CROP, ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.PYRITE_DOOR, ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.PYRITE_TRAPDOOR, ChunkSectionLayer.CUTOUT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.WASHI_WINDOW, ChunkSectionLayer.TRANSLUCENT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.WASHI_WINDOW_PANE, ChunkSectionLayer.TRANSLUCENT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.SHOJI_WINDOW, ChunkSectionLayer.TRANSLUCENT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.SHOJI_WINDOW_PANE, ChunkSectionLayer.TRANSLUCENT)
            ItemBlockRenderTypes.setRenderLayer(JModBlocks.SHOJI_DOOR, ChunkSectionLayer.TRANSLUCENT)
        }

        
        @SubscribeEvent
        fun registerParticleProvider(event: RegisterParticleProvidersEvent) {
            event.registerSpriteSet(
                ModParticles.PYRITE_PARTICLES
            ) { spriteSet: SpriteSet -> PyriteParticles.Provider(spriteSet) }
        }

        
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

        
        @SubscribeEvent
        fun registerER(event: EntityRenderersEvent.RegisterRenderers) {
            // ENTITIES
            event.registerEntityRenderer(
                ModEntities.TRICERATOPS
            ) { pContext: EntityRendererProvider.Context -> TriceratopsRenderer(pContext) }

            event.registerEntityRenderer(
                ModEntities.SIKA_DEER
            ) { pContext: EntityRendererProvider.Context -> SikaDeerRenderer(pContext) }

            event.registerEntityRenderer(ModEntities.CURSED_SAMURAI
            ) { pContext -> CursedSamuraiRenderer(pContext) }

            event.registerEntityRenderer(
                ModEntities.IRON_BATTLE_AXE
            ) { pContext: EntityRendererProvider.Context ->
                IronBattleAxeProjectileRenderer(pContext)
            }

            event.registerEntityRenderer(
                ModEntities.CHAIR
            ) { pContext: EntityRendererProvider.Context -> ChairRenderer(pContext) }

            event.registerEntityRenderer(
                ModEntities.CHIRETSU_SHO_PROJECTILE
            ) { pContext: EntityRendererProvider.Context -> ChiretsuShoProjectileRenderer(pContext) }

            event.registerEntityRenderer(
                ModEntities.EKIRETSU_SHO_PROJECTILE
            ) { EkiretsuShoProjectileRenderer(it) }

            event.registerEntityRenderer(
                ModEntities.YA
            ) { pContext: EntityRendererProvider.Context -> YaRenderer(pContext) }

            event.registerEntityRenderer(
                ModEntities.BULLET
            ) { context ->
                BulletRenderer(context)
            }

            // BLOCK ENTITIES
            event.registerBlockEntityRenderer(
                ModBlockEntities.PEDESTAL_BE
            ) { context ->
                PedestalBlockEntityRenderer(context)
            }

            event.registerBlockEntityRenderer(
                ModBlockEntities.SHOJI_DOOR_BE
            ) { context ->
                ShojiDoorRenderer(context)
            }

            event.registerBlockEntityRenderer(
                ModBlockEntities.FUSUMA_DOOR_BE
            ) { context ->
                FusumaDoorRenderer(context)
            }

            event.registerBlockEntityRenderer(
                ModBlockEntities.HANGING_SCROLL_BE
            ) { context ->
                HangingScrollBlockEntityRenderer(context)
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
