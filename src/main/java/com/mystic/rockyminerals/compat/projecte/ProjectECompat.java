package com.mystic.rockyminerals.compat.projecte;

import com.mystic.rockyminerals.registry.Init;
import moze_intel.projecte.api.imc.CustomEMCRegistration;
import moze_intel.projecte.api.imc.IMCMethods;
import moze_intel.projecte.api.imc.WorldTransmutationEntry;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.api.nss.NormalizedSimpleStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Supplier;

public class ProjectECompat {

    public static void init(InterModEnqueueEvent event) {
        addEMCValues();
        addTransmutation();
    }

    private static void addEMCValues() {
        for (Supplier<? extends ItemLike> itemSupplier : Init.MAIN_BLOCKS) {
            Item item = itemSupplier.get().asItem();
            if (item instanceof BlockItem) {
                Block block = ((BlockItem) item).getBlock();
                String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
                long emc = getEMCForName(name);
                if (emc > 0) {
                    NormalizedSimpleStack stack = NSSItem.createItem(item);
                    InterModComms.sendTo("projecte", IMCMethods.REGISTER_CUSTOM_EMC, () -> new CustomEMCRegistration(stack, emc));
                }
            }
        }
    }

    private static void addTransmutation() {
        for (Supplier<Block> block : Init.TagsInit.BlockTag.getRockyStoneVariants()) {
            InterModComms.sendTo("projecte", IMCMethods.REGISTER_WORLD_TRANSMUTATION, () -> new WorldTransmutationEntry(block.get().defaultBlockState(), Blocks.STONE.defaultBlockState(), Blocks.COBBLESTONE.defaultBlockState()));
        }
    }

    private static long getEMCForName(String name) {
        int coreEMC = 1;
        if (isMineralFamily(name)) {
            coreEMC = 128;
        }

        if (name.contains("_stairs")) {
            return 16 * coreEMC;
        }
        if (name.contains("_slab")) {
            return 8 * coreEMC;
        }
        if (name.contains("_wall")) {
            return 16 * coreEMC;
        }
        if (name.contains("_button")) {
            return coreEMC;
        }
        if (name.contains("_pressure_plate")) {
            return 2 * coreEMC;
        }
        if (name.contains("_pillar")) {
            return 32 * coreEMC;
        }
        if (name.startsWith("mossy_") || name.contains("_mossy")) {
            return 12 + (coreEMC * 4);
        }
        if (name.contains("_cut") || name.startsWith("cut_")) {
            return 4 * coreEMC;
        }
        if (name.contains("_smooth") || name.startsWith("smooth_")) {
            return 4 * coreEMC;
        }
        if (name.startsWith("cobbled_") || name.contains("_brick") || name.startsWith("cracked_") || name.contains("_tile")) {
            return coreEMC;
        }
        if (name.contains("chiseled_")) {
            return 2 * coreEMC;
        }
        if (name.contains("_mosaic")) {
            return 2 * coreEMC;
        }

        if (name.endsWith("_redstone_lamp")) {
            long glowstoneBlockEMC = 1536;
            long redstoneEMC = 64;
            long lampEMC = (coreEMC * 4) + glowstoneBlockEMC;
            return (4 * redstoneEMC) + lampEMC;
        }

        if (name.endsWith("_lamp")) {
            long glowstoneBlockEMC = 1536;
            return (coreEMC * 4) + glowstoneBlockEMC;
        }

        return 1;
    }

    private static boolean isMineralFamily(String name) {
        return name.contains("anhydrite") || name.contains("olivine") || name.contains("blue_calcite") ||
                name.contains("halite") || name.contains("opal");
    }
}
