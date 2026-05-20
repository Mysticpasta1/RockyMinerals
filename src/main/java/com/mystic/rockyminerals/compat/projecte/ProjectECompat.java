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
        long coreEMC = 0;
        if (isStoneFamily(name)) {
            coreEMC = 64;
        } else if (isMineralFamily(name)) {
            coreEMC = 128;
        }

        if (coreEMC == 0) {
            return 0;
        }

        if (name.contains("_stairs")) {
            return (coreEMC * 3) / 2;
        }
        if (name.contains("_slab")) {
            return coreEMC / 2;
        }
        if (name.contains("_wall")) {
            return coreEMC;
        }
        if (name.contains("_button")) {
            return coreEMC;
        }
        if (name.contains("_pressure_plate")) {
            return coreEMC * 2;
        }
        if (name.endsWith("_redstone_lamp")) {
            long glowstoneDustEMC = 384;
            long redstoneEMC = 64;
            long lampEMC = coreEMC + (glowstoneDustEMC / 4);
            return (lampEMC + 4 * redstoneEMC) / 4;
        }
        if (name.endsWith("_lamp")) {
            long glowstoneDustEMC = 384;
            return coreEMC + (glowstoneDustEMC / 4);
        }
        if (name.contains("_pillar")) {
            return coreEMC * 2;
        }
        if (name.startsWith("mossy_")) {
            return coreEMC;
        }
        if (name.contains("_cut")) {
            return coreEMC;
        }

        if (name.startsWith("cobbled_") || name.contains("_brick") || name.startsWith("cracked_") || name.contains("_tile")) {
            return 1;
        }
        if (name.contains("chiseled_")) {
            return 2;
        }
        if (name.contains("_mosaic")) {
            return 2;
        }

        return coreEMC;
    }

    private static boolean isStoneFamily(String name) {
        return name.contains("marble") || name.contains("crystallized_marble") || name.contains("jadeite") || name.contains("saltstone") ||
               name.contains("duskmire") || name.contains("pumice") || name.contains("rhyolite") ||
               name.contains("worn_granite") || name.contains("terra_rossa");
    }

    private static boolean isMineralFamily(String name) {
        return name.contains("anhydrite") || name.contains("olivine") || name.contains("blue_calcite") ||
               name.contains("halite") || name.contains("opal");
    }
}