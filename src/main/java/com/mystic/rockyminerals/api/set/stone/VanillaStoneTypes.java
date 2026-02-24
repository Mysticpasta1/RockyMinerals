package com.mystic.rockyminerals.api.set.stone;

import com.mystic.rockyminerals.registry.Init;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class VanillaStoneTypes {

    // Vanilla
    public static final StoneType STONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("stone"), Blocks.STONE));

    // Rocky Mineral
    public static final StoneType SALTSTONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.parse("rockyminerals:saltstone"), Init.SALTSTONE.block().get()));
}
