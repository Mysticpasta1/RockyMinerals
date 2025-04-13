package com.mystic.rockyminerals.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

public class HalfTransparentLampVariantBlock extends RedstoneLampBlock {
    public static final BooleanProperty LIT = RedstoneLampBlock.LIT;

    public HalfTransparentLampVariantBlock() {
        super(Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion().lightLevel((blockState) -> blockState.getValue(LIT) ? 15 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    public boolean skipRendering(@NotNull BlockState p_53972_, BlockState p_53973_, @NotNull Direction p_53974_) {
        return p_53973_.is(this) || p_53973_.getBlock() instanceof HalfTransparentBlock || p_53973_.getBlock() instanceof HalfTransparentRotatedPillarBlock || super.skipRendering(p_53972_, p_53973_, p_53974_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(LIT);
    }
}
