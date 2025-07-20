package net.Chidoziealways.everythingjapanese.block

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.MOD_ID
import net.Chidoziealways.everythingjapanese.block.custom.ChairBlock
import net.Chidoziealways.everythingjapanese.custom.GrowthChamberBlock
import net.Chidoziealways.everythingjapanese.custom.HellPortalBlock
import net.Chidoziealways.everythingjapanese.custom.MagicBlock
import net.Chidoziealways.everythingjapanese.custom.ModFlammableRotatedPillarBlock
import net.Chidoziealways.everythingjapanese.custom.ModdedCakeBlock
import net.Chidoziealways.everythingjapanese.custom.PedestalBlock
import net.Chidoziealways.everythingjapanese.custom.PyriteLampBlock
import net.Chidoziealways.everythingjapanese.custom.RiceCropBlock
import net.Chidoziealways.everythingjapanese.custom.YamazakiBerryBushBlock
import net.Chidoziealways.everythingjapanese.item.ModItems
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.Chidoziealways.everythingjapanese.worldgen.tree.ModTreeGrowers
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.minecraftforge.eventbus.api.bus.BusGroup
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.ObjectHolderDelegate
import thedarkcolour.kotlinforforge.forge.registerObject
import java.util.function.Supplier
import java.util.function.ToIntFunction

object ModBlocks {
    val BLOCKS: DeferredRegister<Block?> =
        DeferredRegister.create<Block?>(ForgeRegistries.BLOCKS, MOD_ID)

    // Basic Blocks
    val PYRITE_BLOCK = registerBlock<Block?>(
        "pyrite_block"
    ) {
        Block(
            BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_block")
                    )
                )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)
        )
    }

    val NEPHRITE_BLOCK = registerBlock<Block?>(
        "nephrite_block"
    ) {
        Block(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_block")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)
        )
    }


    val RAW_PYRITE_BLOCK = registerBlock<Block?>(
        "raw_pyrite_block"
    ) {
        Block(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_pyrite_block")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)
        )
    }

    // Ore Blocks
    @JvmField
    val PYRITE_ORE = registerBlock<Block?>(
        "pyrite_ore"
    ) {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)
        )
    }

    @JvmField
    val PYRITE_DEEPSLATE_ORE = registerBlock<Block?>(
        "pyrite_deepslate_ore"
    ) {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_deepslate_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)
        )
    }

    @JvmField
    val NEPHRITE_ORE = registerBlock<Block?>(
        "nephrite_ore"
    ) {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops()
        )
    }

    @JvmField
    val NEPHRITE_DEEPSLATE_ORE = registerBlock<Block?>(
        "nephrite_deepslate_ore"
    ) {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "nephrite_deepslate_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops()
        )
    }

    // Custom Blocks
    val TRANSFORMER_BLOCK = registerBlock<Block?>(
        "transformer_block"
    ) {
        MagicBlock(
            BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "transformer_block")
                    )
                )
                .strength(2f).noLootTable().sound(ModSounds.MAGIC_BLOCK_SOUNDS).requiresCorrectToolForDrops()
        )
    }

    val CHOCOLATE_CAKE = registerBlock<Block?>(
        "chocolate_cake"
    ) {
        ModdedCakeBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "chocolate_cake")
                )
            )
                .forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)
        )
    }

    val JAPANESE_CHEESECAKE = registerBlock<Block?>(
        "japanese_cheesecake"
    ) {
        CakeBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "japanese_cheesecake")
                )
            )
                .noLootTable()
        )
    }

    @JvmField
    val PEDESTAL = registerBlock<Block?>(
        "pedestal"
    ) {
        PedestalBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pedestal")
                )
            )
                .noOcclusion()
        )
    }

    @JvmField
    val GROWTH_CHAMBER = registerBlock<Block?>(
        "growth_chamber"
    ) {
        GrowthChamberBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "growth_chamber")
                )
            )
        )
    }

    //Food/Crop Blocks
    val RICE_CROP = BLOCKS.register<Block?>(
        "rice_crop",
        Supplier {
            RiceCropBlock(
                BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .setId(
                        ResourceKey.create<Block?>(
                            Registries.BLOCK,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "rice_crop")
                        )
                    )
                    .noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)
            )
        })

    @JvmField
    val YAMAZAKI_BERRY_BUSH = BLOCKS.register<Block?>(
        "yamazaki_berry_bush",
        Supplier {
            YamazakiBerryBushBlock(
                BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)
                    .pushReaction(PushReaction.DESTROY)
                    .setId(
                        ResourceKey.create<Block?>(
                            Registries.BLOCK,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "yamazaki_berry_bush")
                        )
                    )
            )
        })

    // Hinoki Wood Blocks
    @JvmField
    val HINOKI_MARUTA = registerBlock<RotatedPillarBlock?>(
        "hinoki_maruta"
    ) {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hinoki_maruta")
                    )
                )
        )
    }

    val HINOKI_MOKUZAI = registerBlock<RotatedPillarBlock?>(
        "hinoki_mokuzai"
    ) {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hinoki_mokuzai")
                    )
                )
        )
    }

    val STRIPPED_HINOKI_MARUTA = registerBlock<RotatedPillarBlock?>(
        "stripped_hinoki_maruta"
    ) {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "stripped_hinoki_maruta")
                    )
                )
        )
    }

    val STRIPPED_HINOKI_MOKUZAI = registerBlock<RotatedPillarBlock?>(
        "stripped_hinoki_mokuzai"
    ) {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "stripped_hinoki_mokuzai")
                    )
                )
        )
    }

    val HINOKI_BAN = registerBlock<Block?>(
        "hinoki_ban"
    ) {
        object : Block(
            Properties.ofFullCopy(Blocks.OAK_PLANKS)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hinoki_ban")
                    )
                )
        ) {
            override fun isFlammable(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Int {
                return 20
            }

            override fun getFireSpreadSpeed(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Int {
                return 5
            }
        }
    }


    @JvmField
    val HINOKI_HA = registerBlock<Block?>(
        "hinoki_ha"
    ) {
        TintedParticleLeavesBlock(
            0.1f, leavesProperties(SoundType.GRASS)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hinoki_ha")
                    )
                )
        )
    }

    @JvmField
    val HINOKI_NAEGI = registerBlock<Block?>(
        "hinoki_naegi"
    ) {
        SaplingBlock(
            ModTreeGrowers.HINOKI, BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS)
                .pushReaction(PushReaction.DESTROY)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hinoki_naegi")
                    )
                )
        )
    }

    val POTTED_HINOKI_NAEGI = registerBlock<Block?>(
        "potted_hinoki_naegi"
    ) {
        FlowerPotBlock(
            HINOKI_NAEGI.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "hinoki_naegi")
                    )
                )
        )
    }


    // Pyrite Decor Blocks
    val PYRITE_STAIRS = registerBlock<StairBlock?>(
        "pyrite_stairs"
    ) {
        StairBlock(
            PYRITE_BLOCK.get()!!.defaultBlockState(), BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create<Block?>(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_stairs")
                    )
                )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_SLAB = registerBlock<SlabBlock?>(
        "pyrite_slab"
    ) {
        SlabBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_slab")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_PRESSURE_PLATE = registerBlock<PressurePlateBlock?>(
        "pyrite_pressure_plate"
    ) {
        PressurePlateBlock(
            BlockSetType.IRON, BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_pressure_plate")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_BUTTON = registerBlock<ButtonBlock?>(
        "pyrite_button"
    ) {
        ButtonBlock(
            BlockSetType.IRON, 30, BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_button")
                )
            )
                .strength(10f).requiresCorrectToolForDrops().noCollission()
        )
    }

    val PYRITE_FENCE = registerBlock<FenceBlock?>(
        "pyrite_fence"
    ) {
        FenceBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_fence")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_FENCE_GATE = registerBlock<FenceGateBlock?>(
        "pyrite_fence_gate"
    ) {
        FenceGateBlock(
            WoodType.ACACIA,
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_fence_gate")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_WALL = registerBlock<WallBlock?>(
        "pyrite_wall"
    ) {
        WallBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_wall")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_DOOR = registerBlock<DoorBlock?>(
        "pyrite_door"
    ) {
        DoorBlock(
            BlockSetType.IRON,
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_door")
                )
            )
                .strength(10f).requiresCorrectToolForDrops().noOcclusion()
        )
    }

    val PYRITE_TRAPDOOR = registerBlock<TrapDoorBlock?>(
        "pyrite_trapdoor"
    ) {
        TrapDoorBlock(
            BlockSetType.IRON, BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_trapdoor")
                )
            )
                .strength(10f).requiresCorrectToolForDrops().noOcclusion()
        )
    }

    val PYRITE_LAMP = registerBlock<Block?>(
        "pyrite_lamp") {
        PyriteLampBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "pyrite_lamp")
                )
            )
                .strength(10f)
                .lightLevel { state: BlockState? -> if (state!!.getValue<Boolean>(PyriteLampBlock.CLICKED)) 1000 else 0 }
        )
    }

    @JvmField
    val CHAIR = registerBlock<Block?>(
        "chair" ) {
        ChairBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create<Block?>(
                    Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "chair")
                )
            )
                .noOcclusion()
        )
    }

    @JvmField
    val HELL_PORTAL: RegistryObject<Block?> = BLOCKS.register<Block?>(
        "hell_portal",
        Supplier {
            HellPortalBlock(
                BlockBehaviour.Properties.of()
                    .setId(
                        ResourceKey.create<Block?>(
                            Registries.BLOCK,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "hell_portal")
                        )
                    )
                    .noCollission()
                    .randomTicks()
                    .strength(-1.0f)
                    .sound(SoundType.GLASS)
                    .lightLevel { p_50884_: BlockState? -> 11 }
                    .pushReaction(PushReaction.BLOCK)
                    .noLootTable()
            )
        })


    // Register Blocks
    private fun <T : Block?> registerBlock(name: String, block: Function0<T>): ObjectHolderDelegate<T> {
        val toReturn = BLOCKS.registerObject(name, block)
        logInfo("Attempting to register block: $name")
        registerBlockItem(name, toReturn)
        return toReturn
    }

    private fun <T : Block?> registerBlockItem(name: String, block: ObjectHolderDelegate<T>) {
        logInfo("Registering BlockItem for: $name")
        //System.out.println(" Is Chocolate Cake Present? true or false? " + CHOCOLATE_CAKE.isPresent());
        ModItems.ITEMS.registerObject(name) {
            BlockItem(
                block.get(), Item.Properties()
                    .useBlockDescriptionPrefix()
                    .setId(
                        ResourceKey.create<Item?>(
                            Registries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
                        )
                    )
            )
        }
    }

    fun register(eventBus: BusGroup?) {
        BLOCKS.register(eventBus)
    }

    private fun leavesProperties(pSound: SoundType): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2f)
            .randomTicks()
            .sound(pSound)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
    }
}
