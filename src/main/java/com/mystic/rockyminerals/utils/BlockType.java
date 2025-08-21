package com.mystic.rockyminerals.utils;

import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public record BlockType(DeferredHolder<Block, Block> block, DeferredHolder<Block, SlabBlock> slab, DeferredHolder<Block, WallBlock> wall,
                        DeferredHolder<Block, StairBlock> stairs, DeferredHolder<Block, ButtonBlock> button,
                        DeferredHolder<Block, PressurePlateBlock> pressurePlate) {
    private static final Map<BlockType, Supplier<BlockFamily>> MAP = Maps.newHashMap();

    public static @NotNull Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream().map(Supplier::get);
    }

    public static BlockType of(DeferredHolder<Block, Block> blockBase, DeferredHolder<Block, SlabBlock> blockSlab, DeferredHolder<Block, WallBlock> blockWall, DeferredHolder<Block, StairBlock> blockStair, DeferredHolder<Block, ButtonBlock> blockButton, DeferredHolder<Block, PressurePlateBlock> pressurePlate) {
        var blockType = new BlockType(blockBase, blockSlab, blockWall, blockStair, blockButton, pressurePlate);
        MAP.computeIfAbsent(blockType, blockType1 -> Suppliers.memoize(() -> BlockType.family(blockType1)));
        return blockType;
    }

    public static BlockFamily family(BlockType type) {
        var family = new BlockFamily.Builder(type.block.get());
        if (type.slab != null) family.slab(type.slab.get());
        if (type.stairs != null) family.stairs(type.stairs.get());
        if (type.pressurePlate != null) family.pressurePlate(type.pressurePlate.get());
        if (type.button != null) family.button(type.button.get());
        if (type.wall != null) family.wall(type.wall.get());
        return family.getFamily();
    }
}