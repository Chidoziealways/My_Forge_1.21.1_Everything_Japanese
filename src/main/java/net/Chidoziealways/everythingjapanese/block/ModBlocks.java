package net.Chidoziealways.everythingjapanese.block;

import net.Chidoziealways.everythingjapanese.EverythingJapanese;
import net.Chidoziealways.everythingjapanese.block.custom.MagicBlock;
import net.Chidoziealways.everythingjapanese.block.custom.ModdedCakeBlock;
import net.Chidoziealways.everythingjapanese.block.custom.PyriteLampBlock;
import net.Chidoziealways.everythingjapanese.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EverythingJapanese.MOD_ID);

    public static final RegistryObject<Block> PYRITE_BLOCK = registerBlock("pyrite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> RAW_PYRITE_BLOCK = registerBlock("raw_pyrite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> PYRITE_ORE = registerBlock("pyrite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,10),BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PYRITE_DEEPSLATE_ORE = registerBlock("pyrite_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,10),BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TRANSFORMER_BLOCK = registerBlock("transformer_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2).noLootTable()));

    public static final RegistryObject<Block> HINOKI_MARUTA = registerBlock("hinoki_maruta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> STRIPPED_HINOKI_MARUTA = registerBlock("stripped_hinoki_maruta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHOCOLATE_CAKE = registerBlock("chocolate_cake",
            () -> new ModdedCakeBlock(BlockBehaviour.Properties.of()));

    public static final RegistryObject<Block> JAPANESE_CHEESECAKE = registerBlock("japanese_cheesecake",
            () -> new CakeBlock(BlockBehaviour.Properties.of()));

    public static final RegistryObject<Block> WORKBENCH = registerBlock("workbench",
            () -> new Block(BlockBehaviour.Properties.of().noOcclusion()));

    public static final RegistryObject<StairBlock> PYRITE_STAIRS = registerBlock("pyrite_stairs",
            () -> new StairBlock(ModBlocks.PYRITE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<SlabBlock> PYRITE_SLAB = registerBlock("pyrite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<PressurePlateBlock> PYRITE_PRESSURE_PLATE = registerBlock("pyrite_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<ButtonBlock> PYRITE_BUTTON = registerBlock("pyrite_button",
            () -> new ButtonBlock(BlockSetType.IRON, 5, BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops().noCollission()));

    public static final RegistryObject<FenceBlock> PYRITE_FENCE = registerBlock("pyrite_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<FenceGateBlock> PYRITE_FENCE_GATE = registerBlock("pyrite_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA,
                    BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<WallBlock> PYRITE_WALL = registerBlock("pyrite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<DoorBlock> PYRITE_DOOR = registerBlock("pyrite_door",
            () -> new DoorBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<TrapDoorBlock> PYRITE_TRAPDOOR = registerBlock("pyrite_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(10f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> PYRITE_LAMP = registerBlock("pyrite_lamp",
            () -> new PyriteLampBlock(BlockBehaviour.Properties.of().strength(10f)
                    .lightLevel(state -> state.getValue(PyriteLampBlock.CLICKED) ?  50 : 0)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
