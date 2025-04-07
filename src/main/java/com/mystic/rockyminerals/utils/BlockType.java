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

public record BlockType(RegistryObject<Block> block, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall, RegistryObject<StairBlock> stairs, RegistryObject<ButtonBlock> button, RegistryObject<PressurePlateBlock> pressurePlate,
                        RegistryObject<Block> cobbled, RegistryObject<Block> chiseled, RegistryObject<Block> polished, RegistryObject<Block> bricks, RegistryObject<Block> cracked, RegistryObject<Block> tiles) {
    private static final Map<BlockType, Supplier<BlockFamily>> MAP = Maps.newHashMap();

    public static @NotNull Stream<BlockFamily> getAllFamilies() {return MAP.values().stream().map(Supplier::get);}

    public static BlockType of(RegistryObject<Block> blockBase, RegistryObject<SlabBlock> blockSlab, RegistryObject<WallBlock> blockWall, RegistryObject<StairBlock> blockStair, RegistryObject<ButtonBlock> blockButton, RegistryObject<PressurePlateBlock> pressurePlate,
                               RegistryObject<Block> cobbled, RegistryObject<Block> chiseled, RegistryObject<Block> polished, RegistryObject<Block> bricks, RegistryObject<Block> cracked, RegistryObject<Block> tiles) {
        var blockType = new BlockType(blockBase, blockSlab, blockWall, blockStair, blockButton, pressurePlate, cobbled, chiseled, polished, bricks, cracked, tiles);
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
        if(type.cobbled != null) family.fence(type.cobbled.get());
        if(type.chiseled != null) family.fence(type.chiseled.get());
        if(type.polished != null) family.fence(type.polished.get());
        if(type.bricks != null) family.fence(type.bricks.get());
        if(type.cracked != null) family.fence(type.cracked.get());
        if(type.tiles != null) family.fence(type.tiles.get());
        return family.getFamily();
    }
}