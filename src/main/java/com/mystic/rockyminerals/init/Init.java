package com.mystic.rockyminerals.init;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.block.HalfTransparentLampVariantBlock;
import com.mystic.rockyminerals.block.HalfTransparentRotatedPillarBlock;
import com.mystic.rockyminerals.block.MineralLampVariantBlock;
import com.mystic.rockyminerals.block.StoneLampVariantBlock;
import com.mystic.rockyminerals.utils.BlockType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Init {
    public static final List<Supplier<? extends ItemLike>> MAIN_BLOCKS = new ArrayList<>();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);

    //Block Properties
    public static final Supplier<Block> BASE_BLOCK = () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).instrument(NoteBlockInstrument.BASEDRUM));
    public static final Supplier<Block> BASE_ROTATED_PILLAR_BLOCK = () -> new HalfTransparentRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE));
    public static final Supplier<Block> BASE_LAMP_BLOCK = () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(light -> 15).sound(SoundType.STONE));

    public static final Supplier<Block> BASE_MINERAL_BLOCK = () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST));
    public static final Supplier<Block> BASE_MINERAL_ROTATED_PILLAR_BLOCK = () -> new HalfTransparentRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST));
    public static final Supplier<Block> BASE_MINERAL_LAMP_BLOCK = () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).lightLevel(light -> 15).sound(SoundType.AMETHYST));

    public static final Supplier<Block> BASE_HALF_TRANSPARENT_MINERAL = () -> new HalfTransparentBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion().sound(SoundType.AMETHYST));
    public static final Supplier<Block> BASE_HALF_TRANSPARENT_ROTATED_PILLAR_BLOCK = () -> new HalfTransparentRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion().sound(SoundType.AMETHYST));
    public static final Supplier<Block> BASE_HALF_TRANSPARENT_LAMP_BLOCK = () -> new HalfTransparentBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion().lightLevel(light -> 15).sound(SoundType.AMETHYST));

    //Saltstone Variants
    public static final BlockType SALTSTONE = registerBlockType("saltstone", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType COBBLED_SALTSTONE = registerBlockType("cobbled_saltstone", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CHISELED_SALTSTONE = registerBlockType("chiseled_saltstone", BASE_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CRACKED_SALTSTONE = registerBlockType("cracked_saltstone", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType SALTSTONE_BRICKS = registerBricksBlockType("saltstone_brick", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType SALTSTONE_TILE = registerBlockType("saltstone_tile", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType POLISHED_SALTSTONE = registerBlockType("polished_saltstone", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType SALTSTONE_PILLAR = registerBlockType("saltstone_pillar", BASE_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType SALTSTONE_MOSAIC = registerBlockType("mosaic_saltstone", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CUT_SALTSTONE = registerBlockType("cut_saltstone", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType SALTSTONE_LAMP = registerBlockType("saltstone_lamp", BASE_LAMP_BLOCK, BlockSetType.STONE, 40, true);
    public static final RegistryObject<Block> SALTSTONE_REDSTONE_LAMP = registerBlock("saltstone_redstone_lamp", StoneLampVariantBlock::new);

    //Anhydrite Variants
    public static final BlockType ANHYDRITE = registerBlockType("anhydrite", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType COBBLED_ANHYDRITE = registerBlockType("cobbled_anhydrite", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CHISELED_ANHYDRITE = registerBlockType("chiseled_anhydrite", BASE_MINERAL_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CRACKED_ANHYDRITE = registerBlockType("cracked_anhydrite", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType ANHYDRITE_BRICKS = registerBricksBlockType("anhydrite_brick", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType ANHYDRITE_TILE = registerBlockType("anhydrite_tile", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType POLISHED_ANHYDRITE = registerBlockType("polished_anhydrite", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType ANHYDRITE_PILLAR = registerBlockType("anhydrite_pillar", BASE_MINERAL_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType ANHYDRITE_MOSAIC = registerBlockType("mosaic_anhydrite", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CUT_ANHYDRITE = registerBlockType("cut_anhydrite", BASE_MINERAL_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType ANHYDRITE_LAMP = registerBlockType("anhydrite_lamp", BASE_MINERAL_LAMP_BLOCK, BlockSetType.STONE, 40, true);
    public static final RegistryObject<Block> ANHYDRITE_REDSTONE_LAMP = registerBlock("anhydrite_redstone_lamp", MineralLampVariantBlock::new);

    //Worn Granite Variants
    public static final BlockType WORN_GRANITE = registerBlockType("worn_granite", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType COBBLED_WORN_GRANITE = registerBlockType("cobbled_worn_granite", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CHISELED_WORN_GRANITE = registerBlockType("chiseled_worn_granite", BASE_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CRACKED_WORN_GRANITE = registerBlockType("cracked_worn_granite", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType WORN_GRANITE_BRICKS = registerBricksBlockType("worn_granite_brick", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType WORN_GRANITE_TILE = registerBlockType("worn_granite_tile", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType POLISHED_WORN_GRANITE = registerBlockType("polished_worn_granite", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType WORN_GRANITE_PILLAR = registerBlockType("worn_granite_pillar", BASE_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType WORN_GRANITE_MOSAIC = registerBlockType("mosaic_worn_granite", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType CUT_WORN_GRANITE = registerBlockType("cut_worn_granite", BASE_BLOCK, BlockSetType.STONE, 40, true);
    public static final BlockType WORN_GRANITE_LAMP = registerBlockType("worn_granite_lamp", BASE_LAMP_BLOCK, BlockSetType.STONE, 40, true);
    public static final RegistryObject<Block> WORN_GRANITE_REDSTONE_LAMP = registerBlock("worn_granite_redstone_lamp", StoneLampVariantBlock::new);

    //Halite Variants
    public static final BlockType HALITE = registerBlockType("halite", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType COBBLED_HALITE = registerBlockType("cobbled_halite", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType CHISELED_HALITE = registerBlockType("chiseled_halite", BASE_HALF_TRANSPARENT_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 30, true);
    public static final BlockType CRACKED_HALITE = registerBlockType("cracked_halite", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType HALITE_BRICKS = registerBricksBlockType("halite_brick", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType HALITE_TILE = registerBlockType("halite_tile", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType POLISHED_HALITE = registerBlockType("polished_halite", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType HALITE_PILLAR = registerBlockType("halite_pillar", BASE_HALF_TRANSPARENT_ROTATED_PILLAR_BLOCK, BlockSetType.STONE, 30, true);
    public static final BlockType HALITE_MOSAIC = registerBlockType("mosaic_halite", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType CUT_HALITE = registerBlockType("cut_halite", BASE_HALF_TRANSPARENT_MINERAL, BlockSetType.STONE, 30, true);
    public static final BlockType HALITE_LAMP = registerBlockType("halite_lamp", BASE_HALF_TRANSPARENT_LAMP_BLOCK, BlockSetType.STONE, 30, true);
    public static final RegistryObject<Block> HALITE_REDSTONE_LAMP = registerBlock("halite_redstone_lamp", HalfTransparentLampVariantBlock::new);

    public static BlockType registerBlockType(String name, Supplier<Block> block, BlockSetType blockSetType, int pTicksToStayPressed, boolean pArrowsCanPress) {
        var blockBase = registerMainTabBlock(name, block, tRegistryObject -> () -> new BlockItem(tRegistryObject.get(), new Item.Properties()));
        var blockSlab = registerMainTabBlock(name + "_slab", blockBase, block1 -> new SlabBlock(BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockWall = registerMainTabBlock(name + "_wall", blockBase, block1 -> new WallBlock(BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockStairs = registerMainTabBlock(name + "_stairs", blockBase, block1 -> new StairBlock(block1::defaultBlockState, BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockButton = registerMainTabBlock(name + "_button", blockBase, block1 -> new ButtonBlock(BlockBehaviour.Properties.copy(block1), blockSetType, pTicksToStayPressed, pArrowsCanPress), block2 -> new BlockItem(block2, new Item.Properties()));
        var pressurePlate = registerMainTabBlock(name + "_pressure_plate", blockBase, block1 -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(block1), blockSetType), block2 -> new BlockItem(block2, new Item.Properties()));

        return BlockType.of(blockBase, blockSlab, blockWall, blockStairs, blockButton, pressurePlate);
    }

    public static BlockType registerBricksBlockType(String name, Supplier<Block> block, BlockSetType blockSetType, int pTicksToStayPressed, boolean pArrowsCanPress) {
        var blockBase = registerMainTabBlock(name + "s", block, tRegistryObject -> () -> new BlockItem(tRegistryObject.get(), new Item.Properties()));
        var blockSlab = registerMainTabBlock(name + "_slab", blockBase, block1 -> new SlabBlock(BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockWall = registerMainTabBlock(name + "_wall", blockBase, block1 -> new WallBlock(BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockStairs = registerMainTabBlock(name + "_stairs", blockBase, block1 -> new StairBlock(block1::defaultBlockState, BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockButton = registerMainTabBlock(name + "_button", blockBase, block1 -> new ButtonBlock(BlockBehaviour.Properties.copy(block1), blockSetType, pTicksToStayPressed, pArrowsCanPress), block2 -> new BlockItem(block2, new Item.Properties()));
        var pressurePlate = registerMainTabBlock(name + "_pressure_plate", blockBase, block1 -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(block1), blockSetType), block2 -> new BlockItem(block2, new Item.Properties()));

        return BlockType.of(blockBase, blockSlab, blockWall, blockStairs, blockButton, pressurePlate);
    }

    private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<B> block) {
        return registerMainTabBlock(name, block, b -> () -> new BlockItem(b.get(), new Item.Properties()));
    }

    private static <B extends Block, I extends BlockItem> RegistryObject<B> registerMainTabBlock(String name, Supplier<B> block, Function<RegistryObject<B>, Supplier<I>> item) {
        var reg = BLOCKS.register(name, block);
        addToMainTab(ITEMS.register(name, () -> item.apply(reg).get()));
        return reg;
    }

    private static <B extends Block, C extends Block, I extends BlockItem> RegistryObject<C> registerMainTabBlock(String name, Supplier<B> block, Function<B, C> blockFunction, Function<C, I> item) {
        var reg = BLOCKS.register(name, () -> blockFunction.apply(block.get()));
        addToMainTab(ITEMS.register(name, () ->
                item.apply(reg.get())));
        return reg;
    }

    public static <T extends Item> void addToMainTab(RegistryObject<T> itemLike) {
        MAIN_BLOCKS.add(itemLike);
    }

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
    }

    public static final RegistryObject<CreativeModeTab> MAIN = CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.rockyminerals.main"))
            .icon(() -> SALTSTONE.block().get().asItem().getDefaultInstance()
            ).displayItems((parameters, output) -> {
                MAIN_BLOCKS.forEach(itemLike -> output.accept(itemLike.get()));
            }).build());

}
