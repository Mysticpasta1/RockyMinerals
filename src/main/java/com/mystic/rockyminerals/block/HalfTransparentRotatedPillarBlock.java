package com.mystic.rockyminerals.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;

public class HalfTransparentRotatedPillarBlock extends RotatedPillarBlock {
    public HalfTransparentRotatedPillarBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean skipRendering(@NotNull BlockState p_53972_, BlockState p_53973_, @NotNull Direction p_53974_) {
        return p_53973_.is(this) || p_53973_.getBlock() instanceof HalfTransparentBlock || p_53973_.getBlock() instanceof HalfTransparentRotatedPillarBlock || super.skipRendering(p_53972_, p_53973_, p_53974_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55933_) {
        p_55933_.add(AXIS);
    }

    public @NotNull BlockState getStateForPlacement(BlockPlaceContext p_55928_) {
        return this.defaultBlockState().setValue(AXIS, p_55928_.getClickedFace().getAxis());
    }
}
