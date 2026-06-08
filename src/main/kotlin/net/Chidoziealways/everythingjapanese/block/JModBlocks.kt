package net.Chidoziealways.everythingjapanese.block

import net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo
import net.Chidoziealways.everythingjapanese.JAPANESE_MOD_ID
import net.Chidoziealways.everythingjapanese.block.custom.CalligraphyTableBlock
import net.Chidoziealways.everythingjapanese.block.custom.ChairBlock
import net.Chidoziealways.everythingjapanese.block.custom.CursedBlock
import net.Chidoziealways.everythingjapanese.block.custom.FusumaDoorBlock
import net.Chidoziealways.everythingjapanese.block.custom.JapaneseCheesecakeBlock
import net.Chidoziealways.everythingjapanese.block.custom.ShojiDoorBlock
import net.Chidoziealways.everythingjapanese.block.custom.PaperWindowBlock
import net.Chidoziealways.everythingjapanese.custom.GrowthChamberBlock
import net.Chidoziealways.everythingjapanese.block.custom.HellPortalBlock
import net.Chidoziealways.everythingjapanese.block.custom.MoneyVaultBlock
import net.Chidoziealways.everythingjapanese.block.custom.TatamiMatBlock
import net.Chidoziealways.everythingjapanese.block.custom.ZabutonBlock
import net.Chidoziealways.everythingjapanese.block.custom.hanging_scroll.HangingScrollBlock
import net.Chidoziealways.everythingjapanese.custom.MagicBlock
import net.Chidoziealways.everythingjapanese.custom.ModFlammableRotatedPillarBlock
import net.Chidoziealways.everythingjapanese.custom.ModdedCakeBlock
import net.Chidoziealways.everythingjapanese.custom.PedestalBlock
import net.Chidoziealways.everythingjapanese.custom.PyriteLampBlock
import net.Chidoziealways.everythingjapanese.custom.RiceCropBlock
import net.Chidoziealways.everythingjapanese.custom.YamazakiBerryBushBlock
import net.Chidoziealways.everythingjapanese.fluids.ModFluids
import net.Chidoziealways.everythingjapanese.item.JModItems
import net.Chidoziealways.everythingjapanese.sound.ModSounds
import net.Chidoziealways.everythingjapanese.worldgen.tree.ModTreeGrowers
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.function.Supplier

object JModBlocks {
    val BLOCKS = DeferredRegister.createBlocks(JAPANESE_MOD_ID)

    // Basic Blocks
    val PYRITE_BLOCK by registerBlock(
        "pyrite_block"
    ) {
        Block(
            BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_block")
                    )
                )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)
        )
    }

    val CURSED_BLOCK by registerBlock(
        "cursed_block"
    ) {
        CursedBlock(
            BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "cursed_block")
                    )
                )
        )
    }

    /*val CALLIGRAPHY_TABLE by registerBlock(
        "calligraphy_table"
    ) {
        CalligraphyTableBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.CARTOGRAPHY_TABLE)
                .setId(ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "calligraphy_table")
                ))
        )
    }*/

    val NEPHRITE_BLOCK by registerBlock(
        "nephrite_block"
    ) {
        Block(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "nephrite_block")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)
        )
    }


    val RAW_PYRITE_BLOCK by registerBlock(
        "raw_pyrite_block"
    ) {
        Block(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "raw_pyrite_block")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)
        )
    }

    // FURNITURE
    val WASHI_WINDOW by registerBlock("washi_window") {
        PaperWindowBlock(
            BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.HAT)
                .strength(0.15F)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "washi_window")))
        )
    }

    val WASHI_WINDOW_PANE by registerBlock("washi_window_pane") {
        IronBarsBlock(
            BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.HAT)
                .strength(0.13F)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .noLootTable()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "washi_window_pane")))
        )
    }

    val SHOJI_WINDOW by registerBlock("shoji_window") {
        PaperWindowBlock(
            BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.HAT)
                .strength(0.15F)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_window")))
        )
    }

    val SHOJI_WINDOW_PANE by registerBlock("shoji_window_pane") {
        IronBarsBlock(
            BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.HAT)
                .strength(0.13F)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .noLootTable()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_window_pane")))
        )
    }

    val SHOJI_DOOR by registerBlock("shoji_door") {
        ShojiDoorBlock(
            BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.HAT)
                .sound(SoundType.GLASS)
                .strength(0.2F)
                .noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "shoji_door")))
        )
    }

    val CHABUDAI by registerBlock("chabudai") {
        Block(BlockBehaviour.Properties.of()
            .noOcclusion()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD)
            .strength(0.5F)
            .setId(ResourceKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "chabudai")
            )))
    }

    val FUSUMA_DOOR by registerBlock("fusuma_door") {
        FusumaDoorBlock(
            BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.HAT)
                .sound(SoundType.GLASS)
                .strength(0.2F)
                .noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "fusuma_door")))
        )
    }

    val PEDESTAL by registerBlock("pedestal") {
        PedestalBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pedestal")
                )
            )
                .noOcclusion()
        )
    }

    val HANGING_SCROLL by registerBlock("hanging_scroll") {
        HangingScrollBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hanging_scroll")))
                .noOcclusion()
        )
    }

    /*val BYOUBU by registerBlock(
        "byoubu"
    ) {
        ByoubuBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .sound(SoundType.WOOL)
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "byoubu")))
        )
    }
     */

    // Ore Blocks
    val PYRITE_ORE by registerBlock("pyrite_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)
        )
    }

    val PYRITE_DEEPSLATE_ORE by registerBlock("pyrite_deepslate_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_deepslate_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)
        )
    }

    val NEPHRITE_ORE by registerBlock("nephrite_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "nephrite_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops()
        )
    }

    val NEPHRITE_DEEPSLATE_ORE by registerBlock("nephrite_deepslate_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 10), BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "nephrite_deepslate_ore")
                )
            )
                .strength(4f).requiresCorrectToolForDrops()
        )
    }

    // MISC
    val MONEY_VAULT_BLOCK by registerBlock("money_vault_block") {
        MoneyVaultBlock(
            BlockBehaviour.Properties.of()
                .sound(SoundType.IRON)
                .strength(0.5f)
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "money_vault_block")))
        )
    }

    val TRANSFORMER_BLOCK by registerBlock("transformer_block") {
        MagicBlock(
            BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "transformer_block")
                    )
                )
                .strength(2f).noLootTable().sound(ModSounds.MAGIC_BLOCK_SOUNDS).requiresCorrectToolForDrops()
        )
    }

    val GROWTH_CHAMBER by registerBlock("growth_chamber") {
        GrowthChamberBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "growth_chamber")
                )
            )
        )
    }

    // CAKE
    val CHOCOLATE_CAKE by registerBlock("chocolate_cake") {
        ModdedCakeBlock(
            BlockBehaviour.Properties.of()
                .sound(SoundType.WOOL)
                .setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "chocolate_cake")
                )
            )
                .forceSolidOn().strength(0.5f).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)
        )
    }

    val JAPANESE_CHEESECAKE by registerBlock("japanese_cheesecake") {
        JapaneseCheesecakeBlock(
            BlockBehaviour.Properties.of()
                .sound(SoundType.WOOL)
                .setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "japanese_cheesecake")
                )
            )
                .noLootTable()
        )
    }

    // Food/Crop Blocks
    val RICE_CROP by BLOCKS.register("rice_crop") { ->
            RiceCropBlock(
                BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .setId(
                        ResourceKey.create(
                            Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "rice_crop")
                        )
                    )
                    .randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)
            )
        }

    val YAMAZAKI_BERRY_BUSH by BLOCKS.register("yamazaki_berry_bush") { ->
            YamazakiBerryBushBlock(
                BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH)
                    .pushReaction(PushReaction.DESTROY)
                    .setId(
                        ResourceKey.create(
                            Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "yamazaki_berry_bush")
                        )
                    )
            )
        }

    // Hinoki Block Family
    val HINOKI_MARUTA by registerBlock("hinoki_maruta") {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_maruta")
                    )
                )
        )
    }

    val HINOKI_BAN by registerBlock("hinoki_ban") {
        object : Block(
            Properties.ofFullCopy(Blocks.OAK_PLANKS)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_ban")
                    )
                )
        ) {
            override fun isFlammable(
                state: BlockState,
                level: BlockGetter,
                pos: BlockPos,
                direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState,
                level: BlockGetter,
                pos: BlockPos,
                direction: Direction
            ): Int {
                return 20
            }

            override fun getFireSpreadSpeed(
                state: BlockState,
                level: BlockGetter,
                pos: BlockPos,
                direction: Direction
            ): Int {
                return 5
            }
        }
    }

    val HINOKI_STAIRS by registerBlock("hinoki_stairs") {
        StairBlock(
            HINOKI_BAN.defaultBlockState(), BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_stairs")
                    )
                )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val HINOKI_SLAB by registerBlock("hinoki_slab") {
        SlabBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_slab")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val HINOKI_PRESSURE_PLATE by registerBlock("hinoki_pressure_plate") {
        PressurePlateBlock(
            BlockSetType.IRON, BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_pressure_plate")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val HINOKI_BUTTON by registerBlock("hinoki_button") {
        ButtonBlock(
            BlockSetType.IRON, 30, BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_button")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val HINOKI_FENCE by registerBlock("hinoki_fence") {
        FenceBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_fence")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val HINOKI_FENCE_GATE by registerBlock("hinoki_fence_gate") {
        FenceGateBlock(
            WoodType.ACACIA,
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_fence_gate")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val HINOKI_MOKUZAI by registerBlock("hinoki_mokuzai") {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_mokuzai")
                    )
                )
        )
    }

    val STRIPPED_HINOKI_MARUTA by registerBlock("stripped_hinoki_maruta") {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "stripped_hinoki_maruta")
                    )
                )
        )
    }

    val STRIPPED_HINOKI_MOKUZAI by registerBlock("stripped_hinoki_mokuzai") {
        ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "stripped_hinoki_mokuzai")
                    )
                )
        )
    }

    val HINOKI_HA by registerBlock("hinoki_ha") {
        TintedParticleLeavesBlock(
            0.1f, leavesProperties(SoundType.GRASS)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_ha")
                    )
                )
        )
    }

    val HINOKI_NAEGI by registerBlock("hinoki_naegi") {
        SaplingBlock(
            ModTreeGrowers.HINOKI, BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS)
                .pushReaction(PushReaction.DESTROY)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_naegi")
                    )
                )
        )
    }

    val POTTED_HINOKI_NAEGI by registerBlock("potted_hinoki_naegi") {
        FlowerPotBlock(
            HINOKI_NAEGI, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hinoki_naegi")
                    )
                )
        )
    }

    // Pyrite Decor Blocks
    val PYRITE_STAIRS by registerBlock(
        "pyrite_stairs"
    ) {
        StairBlock(
            PYRITE_BLOCK.defaultBlockState(), BlockBehaviour.Properties.of()
                .setId(
                    ResourceKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_stairs")
                    )
                )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_SLAB by registerBlock(
        "pyrite_slab"
    ) {
        SlabBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_slab")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_PRESSURE_PLATE by registerBlock(
        "pyrite_pressure_plate"
    ) {
        PressurePlateBlock(
            BlockSetType.IRON, BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_pressure_plate")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_BUTTON by registerBlock(
        "pyrite_button"
    ) {
        ButtonBlock(
            BlockSetType.IRON, 30, BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_button")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_FENCE by registerBlock(
        "pyrite_fence"
    ) {
        FenceBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_fence")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_FENCE_GATE by registerBlock(
        "pyrite_fence_gate"
    ) {
        FenceGateBlock(
            WoodType.ACACIA,
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_fence_gate")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_WALL by registerBlock(
        "pyrite_wall"
    ) {
        WallBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_wall")
                )
            )
                .strength(10f).requiresCorrectToolForDrops()
        )
    }

    val PYRITE_DOOR by registerBlock(
        "pyrite_door"
    ) {
        DoorBlock(
            BlockSetType.IRON,
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_door")
                )
            )
                .strength(10f).requiresCorrectToolForDrops().noOcclusion()
        )
    }

    val PYRITE_TRAPDOOR by registerBlock(
        "pyrite_trapdoor"
    ) {
        TrapDoorBlock(
            BlockSetType.IRON, BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_trapdoor")
                )
            )
                .strength(10f).requiresCorrectToolForDrops().noOcclusion()
        )
    }

    val PYRITE_LAMP by registerBlock(
        "pyrite_lamp") {
        PyriteLampBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "pyrite_lamp")
                )
            )
                .strength(10f)
                .lightLevel { state: BlockState? -> if (state!!.getValue(PyriteLampBlock.CLICKED)) 1000 else 0 }
        )
    }

    val CHAIR by registerBlock(
        "chair") {
        ChairBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "chair")
                )
            )
                .noOcclusion()
        )
    }

    val ZABUTON_BLUE by registerBlock(
        "zabuton_blue") {
        ZabutonBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "zabuton_blue")
                )
            )
                .noOcclusion()
        )
    }

    val ZABUTON_GREEN by registerBlock(
        "zabuton_green") {
        ZabutonBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "zabuton_green")
                )
            )
                .noOcclusion()
        )
    }

    val ZABUTON_RED by registerBlock(
        "zabuton_red") {
        ZabutonBlock(
            BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "zabuton_red")
                )
            )
                .noOcclusion()
        )
    }

    val TATAMI_MAT by registerBlock(
        "tatami_mat") {
        TatamiMatBlock(
            BlockBehaviour.Properties.of().noOcclusion().setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "tatami_mat")
                )
            )
        )
    }

    val JAPANESE_FLAG by registerBlock(
        "japanese_flag") {
        Block(BlockBehaviour.Properties.of()
            .setId(
                ResourceKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "japanese_flag")
                )
            )
            .noOcclusion()
        )
    }

    val HELL_PORTAL by BLOCKS.register(
        "hell_portal",
        Supplier {
            HellPortalBlock(
                BlockBehaviour.Properties.of()
                    .setId(
                        ResourceKey.create(
                            Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "hell_portal")
                        )
                    )
                    
                    .randomTicks()
                    .strength(-1.0f)
                    .sound(SoundType.GLASS)
                    .lightLevel { p_50884_: BlockState -> 11 }
                    .pushReaction(PushReaction.BLOCK)
                    .noLootTable()
                    .noCollision()
            )
        })

    //FLUIDS
    val BLOOD by BLOCKS.register("blood") { ->
        object : LiquidBlock(ModFluids.BLOOD.get(), Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, "blood")))
            
            .replaceable()
            .pushReaction(PushReaction.DESTROY)
            .noLootTable()
            .liquid()
            .sound(SoundType.EMPTY)
            .strength(100f)
            .noLootTable()
        ){}
    }


    // Register Blocks
    private fun <T : Block> registerBlock(name: String, block: Function0<T>): DeferredBlock<T> {
        val toReturn = BLOCKS.register(name, block)
        logInfo("Attempting to register block: $name")
        registerBlockItem(name, toReturn)
        return toReturn
    }

    private fun <T : Block> registerBlockItem(name: String, block: DeferredBlock<T>) {
        logInfo("Registering BlockItem for: $name")
        //System.out.println(" Is Chocolate Cake Present? true or false? " + CHOCOLATE_CAKE.isPresent());
        JModItems.ITEMS.register(name) { ->
            BlockItem(
                block.get(), Item.Properties()
                    .useBlockDescriptionPrefix()
                    .setId(
                        ResourceKey.create(
                            Registries.ITEM,
                            Identifier.fromNamespaceAndPath(JAPANESE_MOD_ID, name)
                        )
                    )
            )
        }
    }

    fun register(eventBus: IEventBus) {
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
