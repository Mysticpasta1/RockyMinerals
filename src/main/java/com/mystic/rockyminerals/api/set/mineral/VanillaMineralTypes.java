package com.mystic.rockyminerals.api.set.mineral;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class VanillaMineralTypes {

    public static final MineralType AMETHYST = MineralTypeRegistry.INSTANCE.register(
            new MineralType(ResourceLocation.withDefaultNamespace("amethyst"), Blocks.AMETHYST_BLOCK));

}
