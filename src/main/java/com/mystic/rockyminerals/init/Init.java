package com.mystic.rockyminerals.init;

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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.mystic.rockyminerals.Main.MODID;

public class Init {
    public static final List<Supplier<? extends ItemLike>> MAIN_BLOCKS = new ArrayList<>();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    //Saltstone Variants
    public static final BlockType SALTSTONE = registerBlockType("saltstone", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType COBBLED_SALTSTONE = registerBlockType("cobbled_saltstone", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType CHISELED_SALTSTONE = registerBlockType("chiseled_saltstone", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType CRACKED_SALTSTONE = registerBlockType("cracked_saltstone", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType SALTSTONE_BRICK = registerBlockType("saltstone_brick", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType SALTSTONE_TILE = registerBlockType("saltstone_tile", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType POLISHED_SALTSTONE = registerBlockType("polished_saltstone", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType SALTSTONE_PILLAR = registerBlockType("saltstone_pillar", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType SALTSTONE_MOSAIC = registerBlockType("saltstone_mosaic", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);
    //public static final BlockType CUT_SALTSTONE = registerBlockType("cut_saltstone", HalfTransparentBlock::new, BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE), BlockSetType.STONE, 40, true);



    public static <T extends Block> BlockType registerBlockType(String name, Function<BlockBehaviour.Properties, Block> block, BlockBehaviour.Properties properties, BlockSetType blockSetType, int pTicksToStayPressed, boolean pArrowsCanPress) {
        var blockBase = registerMainTabBlock(name, () -> block.apply(properties), tRegistryObject -> () -> new BlockItem(tRegistryObject.get(), new Item.Properties()));
        var blockSlab = registerMainTabBlock(name + "_slab", blockBase, block1 -> new SlabBlock(BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockWall = registerMainTabBlock(name + "_wall", blockBase, block1 -> new WallBlock(BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockStairs = registerMainTabBlock(name + "_stairs", blockBase, block1 -> new StairBlock(block1.defaultBlockState(), BlockBehaviour.Properties.copy(block1)), block2 -> new BlockItem(block2, new Item.Properties()));
        var blockButton = registerMainTabBlock(name + "_button", blockBase, block1 -> new ButtonBlock(BlockBehaviour.Properties.copy(block1), blockSetType, pTicksToStayPressed, pArrowsCanPress), block2 -> new BlockItem(block2, new Item.Properties()));
        var pressurePlate = registerMainTabBlock(name + "_pressure_plate", blockBase, block1 -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(block1), blockSetType), block2 -> new BlockItem(block2, new Item.Properties()));

        return BlockType.of(blockBase, blockSlab, blockWall, blockStairs, blockButton, pressurePlate);
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

    public static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        var register = ITEMS.register(name, item);
        addToMainTab(register);
        return register;
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
            .title(Component.translatable("itemGroup.rockyminerals.general"))
            .icon(() -> SALTSTONE.block().get().asItem().getDefaultInstance()
            ).displayItems((parameters, output) -> {
                MAIN_BLOCKS.forEach(itemLike -> output.accept(itemLike.get()));
            }).build());
}
