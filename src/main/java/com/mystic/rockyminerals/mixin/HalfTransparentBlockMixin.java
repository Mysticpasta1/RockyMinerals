package com.mystic.rockyminerals.mixin;

import com.mystic.rockyminerals.block.HalfTransparentRotatedPillarBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HalfTransparentBlock.class)
public abstract class HalfTransparentBlockMixin {
    @Inject(method = "skipRendering", at = @At("RETURN"), cancellable = true)
    public void skipIfMatch(BlockState p_53972_, BlockState p_53973_, Direction p_53974_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(p_53973_.is((HalfTransparentBlock) (Object) this) || p_53973_.getBlock() instanceof HalfTransparentBlock || p_53973_.getBlock() instanceof HalfTransparentRotatedPillarBlock);
    }
}
