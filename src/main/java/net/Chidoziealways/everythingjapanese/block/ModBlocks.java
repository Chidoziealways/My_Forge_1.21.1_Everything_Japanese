package net.Chidoziealways.everythingjapanese.block;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.custom.*;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.Chidoziealways.everythingjapanese.sound.ModSounds;
import net.Chidoziealways.everythingjapanese.worldgen.tree.ModTreeGrowers;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.client.model.obj.ObjMaterialLibrary;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.Chidoziealways.everythingjapanese.EverythingJapanese.logError;
import static net.Chidoziealways.everythingjapanese.EverythingJapanese.logInfo;
import static net.Chidoziealways.everythingjapanese.EverythingJapanese.logDebug;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EverythingJapanese.MOD_ID);

    // Basic Blocks
    public static final RegistryObject<Block> PYRITE_BLOCK = registerBlock("pyrite_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> NEPHRITE_BLOCK = registerBlock("nephrite_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));


    public static final RegistryObject<Block> RAW_PYRITE_BLOCK = registerBlock("raw_pyrite_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));

    // Ore Blocks
    public static final RegistryObject<Block> PYRITE_ORE = registerBlock("pyrite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 10), BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PYRITE_DEEPSLATE_ORE = registerBlock("pyrite_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 10), BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NEPHRITE_ORE = registerBlock("nephrite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 10), BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NEPHRITE_DEEPSLATE_ORE = registerBlock("nephrite_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 10), BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    // Custom Blocks
    public static final RegistryObject<Block> TRANSFORMER_BLOCK = registerBlock("transformer_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2).noLootTable().sound(ModSounds.MAGIC_BLOCK_SOUNDS)));

   public static final RegistryObject<Block> CHOCOLATE_CAKE = registerBlock("chocolate_cake",
           () -> new ModdedCakeBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> JAPANESE_CHEESECAKE = registerBlock("japanese_cheesecake",
            () -> new CakeBlock(BlockBehaviour.Properties.of().noLootTable()));

    public static final RegistryObject<Block> PEDESTAL = registerBlock("pedestal",
            () -> new PedestalBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final RegistryObject<Block> GROWTH_CHAMBER = registerBlock("growth_chamber",
            () -> new GrowthChamberBlock(BlockBehaviour.Properties.of()));

    //Food/Crop Blocks
    public static final RegistryObject<Block> RICE_CROP = BLOCKS.register("rice_crop",
            () -> new RiceCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final RegistryObject<Block> YAMAZAKI_BERRY_BUSH = BLOCKS.register("yamazaki_berry_bush",
            () -> new YamazakiBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    // Hinoki Wood Blocks
    public static final RegistryObject<RotatedPillarBlock> HINOKI_MARUTA = registerBlock("hinoki_maruta",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> HINOKI_MOKUZAI = registerBlock("hinoki_mokuzai",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_HINOKI_MARUTA = registerBlock("stripped_hinoki_maruta",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_HINOKI_MOKUZAI = registerBlock("stripped_hinoki_mokuzai",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> HINOKI_BAN = registerBlock("hinoki_ban",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return 20;

                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return 5;

                }
            });


    public static final RegistryObject<Block> HINOKI_HA = registerBlock("hinoki_ha",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> HINOKI_NAEGI = registerBlock("hinoki_naegi",
            () -> new SaplingBlock(ModTreeGrowers.HINOKI, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    // Pyrite Decor Blocks
    public static final RegistryObject<StairBlock> PYRITE_STAIRS = registerBlock("pyrite_stairs",
            () -> new StairBlock(PYRITE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<SlabBlock> PYRITE_SLAB = registerBlock("pyrite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<PressurePlateBlock> PYRITE_PRESSURE_PLATE = registerBlock("pyrite_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<ButtonBlock> PYRITE_BUTTON = registerBlock("pyrite_button",
            () -> new ButtonBlock(BlockSetType.IRON, 30, BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops().noCollission()));

    public static final RegistryObject<FenceBlock> PYRITE_FENCE = registerBlock("pyrite_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<FenceGateBlock> PYRITE_FENCE_GATE = registerBlock("pyrite_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<WallBlock> PYRITE_WALL = registerBlock("pyrite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<DoorBlock> PYRITE_DOOR = registerBlock("pyrite_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<TrapDoorBlock> PYRITE_TRAPDOOR = registerBlock("pyrite_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> PYRITE_LAMP = registerBlock("pyrite_lamp",
            () -> new PyriteLampBlock(BlockBehaviour.Properties.of().strength(10f)
                    .lightLevel(state -> state.getValue(PyriteLampBlock.CLICKED) ? 1000 : 0)));

    public static final RegistryObject<Block> CHAIR = registerBlock("chair",
            () -> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion()));


    // Register Blocks
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        logInfo("Attempting to register block: " + name);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        logInfo("Registering BlockItem for: " + name);
        //System.out.println(" Is Chocolate Cake Present? true or false? " + CHOCOLATE_CAKE.isPresent());
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        logInfo("REGISTERING EVERY SINGLE BLOCK IN MODBLOCKS");
        for (RegistryObject<Block> blockentry : BLOCKS.getEntries()) {
            if (blockentry.isPresent()) {
                logInfo(blockentry.getId() + " : True");
            } else {
                logError(blockentry.getId() + " : False");
            }
        }
    }

}
