package com.mystic.rockyminerals.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class MineralLampVariantBlock extends RedstoneLampBlock {
    public static final BooleanProperty LIT = RedstoneLampBlock.LIT;

    public MineralLampVariantBlock() {
        super(Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion().lightLevel((blockState) -> blockState.getValue(LIT) ? 15 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(LIT);
    }
}
