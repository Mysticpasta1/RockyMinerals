package com.mystic.rockyminerals.utils;

import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public record BlockType(RegistryObject<Block> block, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall, RegistryObject<StairBlock> stairs, RegistryObject<ButtonBlock> button, RegistryObject<PressurePlateBlock> pressurePlate) {
    private static final Map<BlockType, Supplier<BlockFamily>> MAP = Maps.newHashMap();

    public static @NotNull Stream<BlockFamily> getAllFamilies() {return MAP.values().stream().map(Supplier::get);}

    public static BlockType of(RegistryObject<Block> blockBase, RegistryObject<SlabBlock> blockSlab, RegistryObject<WallBlock> blockWall, RegistryObject<StairBlock> blockStair, RegistryObject<ButtonBlock> blockButton, RegistryObject<PressurePlateBlock> pressurePlate) {
        var blockType = new BlockType(blockBase, blockSlab, blockWall, blockStair, blockButton, pressurePlate);
        MAP.computeIfAbsent(blockType, blockType1 -> Suppliers.memoize(() -> BlockType.family(blockType1)));
        return blockType;
    }

    public static BlockFamily family(BlockType type) {
        var family = new BlockFamily.Builder(type.block.get());
        if(type.slab != null) family.slab(type.slab.get());
        if(type.stairs != null) family.stairs(type.stairs.get());
        if(type.pressurePlate != null) family.pressurePlate(type.pressurePlate.get());
        if(type.button != null) family.button(type.button.get());
        if(type.wall != null) family.wall(type.wall.get());
        return family.getFamily();
    }
}