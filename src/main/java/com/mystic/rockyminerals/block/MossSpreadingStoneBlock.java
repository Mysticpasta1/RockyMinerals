package com.mystic.rockyminerals.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Supplier;

public class MossSpreadingStoneBlock extends Block {

    private final Supplier<Block> baseBlock;

    public MossSpreadingStoneBlock(BlockBehaviour.Properties properties, Supplier<Block> baseBlock) {
        super(properties.randomTicks());
        this.baseBlock = baseBlock;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        // Spread chance (10%)
        if (random.nextInt(10) == 0) {
            for (BlockPos nearby : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                BlockState neighbor = world.getBlockState(nearby);
                if (neighbor.is(this.baseBlock.get()) && isAdjacentToWater(world, nearby)) {
                    world.setBlock(nearby, this.defaultBlockState(), 2);
                }
            }
        }
    }

    private boolean isAdjacentToWater(ServerLevel world, BlockPos pos) {
        for (BlockPos offset : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            if (!offset.equals(pos)) {
                if (world.getFluidState(offset).getType() == Fluids.WATER) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }
}
