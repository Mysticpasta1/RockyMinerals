package com.mystic.rockyminerals;

import com.mystic.rockyminerals.init.Init;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        registerBlockRenderLayers(RenderType.translucent(),
                Init.HALITE.block().get(),
                Init.HALITE.button().get(),
                Init.HALITE.wall().get(),
                Init.HALITE.slab().get(),
                Init.HALITE.stairs().get(),
                Init.HALITE.pressurePlate().get(),
                Init.COBBLED_HALITE.block().get(),
                Init.COBBLED_HALITE.button().get(),
                Init.COBBLED_HALITE.wall().get(),
                Init.COBBLED_HALITE.slab().get(),
                Init.COBBLED_HALITE.stairs().get(),
                Init.COBBLED_HALITE.pressurePlate().get(),
                Init.CHISELED_HALITE.block().get(),
                Init.CHISELED_HALITE.button().get(),
                Init.CHISELED_HALITE.wall().get(),
                Init.CHISELED_HALITE.slab().get(),
                Init.CHISELED_HALITE.stairs().get(),
                Init.CHISELED_HALITE.pressurePlate().get(),
                Init.POLISHED_HALITE.block().get(),
                Init.POLISHED_HALITE.button().get(),
                Init.POLISHED_HALITE.wall().get(),
                Init.POLISHED_HALITE.slab().get(),
                Init.POLISHED_HALITE.stairs().get(),
                Init.POLISHED_HALITE.pressurePlate().get(),
                Init.CUT_HALITE.block().get(),
                Init.CUT_HALITE.button().get(),
                Init.CUT_HALITE.wall().get(),
                Init.CUT_HALITE.slab().get(),
                Init.CUT_HALITE.stairs().get(),
                Init.CUT_HALITE.pressurePlate().get(),
                Init.HALITE_LAMP.block().get(),
                Init.HALITE_LAMP.button().get(),
                Init.HALITE_LAMP.wall().get(),
                Init.HALITE_LAMP.slab().get(),
                Init.HALITE_LAMP.stairs().get(),
                Init.HALITE_LAMP.pressurePlate().get(),
                Init.HALITE_BRICKS.block().get(),
                Init.HALITE_BRICKS.button().get(),
                Init.HALITE_BRICKS.wall().get(),
                Init.HALITE_BRICKS.slab().get(),
                Init.HALITE_BRICKS.stairs().get(),
                Init.HALITE_BRICKS.pressurePlate().get(),
                Init.HALITE_MOSAIC.block().get(),
                Init.HALITE_MOSAIC.button().get(),
                Init.HALITE_MOSAIC.wall().get(),
                Init.HALITE_MOSAIC.slab().get(),
                Init.HALITE_MOSAIC.stairs().get(),
                Init.HALITE_MOSAIC.pressurePlate().get(),
                Init.HALITE_PILLAR.block().get(),
                Init.HALITE_PILLAR.button().get(),
                Init.HALITE_PILLAR.wall().get(),
                Init.HALITE_PILLAR.slab().get(),
                Init.HALITE_PILLAR.stairs().get(),
                Init.HALITE_PILLAR.pressurePlate().get(),
                Init.HALITE_TILE.block().get(),
                Init.HALITE_TILE.button().get(),
                Init.HALITE_TILE.wall().get(),
                Init.HALITE_TILE.slab().get(),
                Init.HALITE_TILE.stairs().get(),
                Init.HALITE_TILE.pressurePlate().get(),
                Init.CRACKED_HALITE.block().get(),
                Init.CRACKED_HALITE.button().get(),
                Init.CRACKED_HALITE.wall().get(),
                Init.CRACKED_HALITE.slab().get(),
                Init.CRACKED_HALITE.stairs().get(),
                Init.CRACKED_HALITE.pressurePlate().get(),
                Init.HALITE_REDSTONE_LAMP.get()
                );
    }

    private static void registerBlockRenderLayers(RenderType layer, Block... blocks) {
        Stream.of(blocks).forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, layer));
    }
}
