package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.init.Init;
import com.mystic.rockyminerals.utils.BlockType;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class MineralEnglishLanguageProvider extends LanguageProvider {

    public MineralEnglishLanguageProvider(PackOutput generator) {
        super(generator, Main.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Anhydrite Variants
        addBaseBlocksFromType(Init.ANHYDRITE, "Anhydrite");
        addBlocksFromType(Init.COBBLED_ANHYDRITE, "Cobbled Anhydrite");
        addBlocksFromType(Init.CHISELED_ANHYDRITE, "Chiseled Anhydrite");
        addBlocksFromType(Init.CRACKED_ANHYDRITE, "Cracked Anhydrite");
        addBlocksFromType(Init.CUT_ANHYDRITE, "Cut Anhydrite");
        addBlocksFromType(Init.POLISHED_ANHYDRITE, "Polished Anhydrite");
        addBlocksFromBricksType(Init.ANHYDRITE_BRICKS, "Anhydrite Brick");
        addBlocksFromType(Init.ANHYDRITE_PILLAR, "Anhydrite Pillar");
        addBlocksFromType(Init.ANHYDRITE_MOSAIC, "Anhydrite Mosaic");
        addBlocksFromType(Init.ANHYDRITE_TILE, "Anhydrite Tile");
        addBlocksFromType(Init.ANHYDRITE_LAMP, "Anhydrite Lamp");
        addBlock(Init.ANHYDRITE_REDSTONE_LAMP, "Anhydrite Redstone Lamp");

        //Saltstone Variants
        addBaseBlocksFromType(Init.SALTSTONE, "Saltstone");
        addBlocksFromType(Init.COBBLED_SALTSTONE, "Cobbled Saltstone");
        addBlocksFromType(Init.CHISELED_SALTSTONE, "Chiseled Saltstone");
        addBlocksFromType(Init.CRACKED_SALTSTONE, "Cracked Saltstone");
        addBlocksFromType(Init.CUT_SALTSTONE, "Cut Saltstone");
        addBlocksFromType(Init.POLISHED_SALTSTONE, "Polished Saltstone");
        addBlocksFromBricksType(Init.SALTSTONE_BRICKS, "Saltstone Brick");
        addBlocksFromType(Init.SALTSTONE_PILLAR, "Saltstone Pillar");
        addBlocksFromType(Init.SALTSTONE_MOSAIC, "Saltstone Mosaic");
        addBlocksFromType(Init.SALTSTONE_TILE, "Saltstone Tile");
        addBlocksFromType(Init.SALTSTONE_LAMP, "Saltstone Lamp");
        addBlock(Init.SALTSTONE_REDSTONE_LAMP, "Saltstone Redstone Lamp");

        //Halite Variants
        addBaseBlocksFromType(Init.HALITE, "Halite");
        addBlocksFromType(Init.COBBLED_HALITE, "Cobbled Halite");
        addBlocksFromType(Init.CHISELED_HALITE, "Chiseled Halite");
        addBlocksFromType(Init.CRACKED_HALITE, "Cracked Halite");
        addBlocksFromType(Init.CUT_HALITE, "Cut Halite");
        addBlocksFromType(Init.POLISHED_HALITE, "Polished Halite");
        addBlocksFromBricksType(Init.HALITE_BRICKS, "Halite Brick");
        addBlocksFromType(Init.HALITE_PILLAR, "Halite Pillar");
        addBlocksFromType(Init.HALITE_MOSAIC, "Halite Mosaic");
        addBlocksFromType(Init.HALITE_TILE, "Halite Tile");
        addBlocksFromType(Init.HALITE_LAMP, "Halite Lamp");
        addBlock(Init.HALITE_REDSTONE_LAMP, "Halite Redstone Lamp");

        //Opal Variants
        addBaseBlocksFromType(Init.OPAL, "Opal");
        addBlocksFromType(Init.COBBLED_OPAL, "Cobbled Opal");
        addBlocksFromType(Init.CHISELED_OPAL, "Chiseled Opal");
        addBlocksFromType(Init.CRACKED_OPAL, "Cracked Opal");
        addBlocksFromType(Init.CUT_OPAL, "Cut Opal");
        addBlocksFromType(Init.POLISHED_OPAL, "Polished Opal");
        addBlocksFromBricksType(Init.OPAL_BRICKS, "Opal Brick");
        addBlocksFromType(Init.OPAL_PILLAR, "Opal Pillar");
        addBlocksFromType(Init.OPAL_MOSAIC, "Opal Mosaic");
        addBlocksFromType(Init.OPAL_TILE, "Opal Tile");
        addBlocksFromType(Init.OPAL_LAMP, "Opal Lamp");
        addBlock(Init.OPAL_REDSTONE_LAMP, "Opal Redstone Lamp");

        //Pumice Variants
        addBaseBlocksFromType(Init.PUMICE, "Pumice");
        addBlocksFromType(Init.COBBLED_PUMICE, "Cobbled Pumice");
        addBlocksFromType(Init.CHISELED_PUMICE, "Chiseled Pumice");
        addBlocksFromType(Init.CRACKED_PUMICE, "Cracked Pumice");
        addBlocksFromType(Init.CUT_PUMICE, "Cut Pumice");
        addBlocksFromType(Init.POLISHED_PUMICE, "Polished Pumice");
        addBlocksFromBricksType(Init.PUMICE_BRICKS, "Pumice Brick");
        addBlocksFromType(Init.PUMICE_PILLAR, "Pumice Pillar");
        addBlocksFromType(Init.PUMICE_MOSAIC, "Pumice Mosaic");
        addBlocksFromType(Init.PUMICE_TILE, "Pumice Tile");
        addBlocksFromType(Init.PUMICE_LAMP, "Pumice Lamp");
        addBlock(Init.PUMICE_REDSTONE_LAMP, "Pumice Redstone Lamp");

        //Rhyolite Variants
        addBaseBlocksFromType(Init.RHYOLITE, "Rhyolite");
        addBlocksFromType(Init.COBBLED_RHYOLITE, "Cobbled Rhyolite");
        addBlocksFromType(Init.CHISELED_RHYOLITE, "Chiseled Rhyolite");
        addBlocksFromType(Init.CRACKED_RHYOLITE, "Cracked Rhyolite");
        addBlocksFromType(Init.CUT_RHYOLITE, "Cut Rhyolite");
        addBlocksFromType(Init.POLISHED_RHYOLITE, "Polished Rhyolite");
        addBlocksFromBricksType(Init.RHYOLITE_BRICKS, "Opal Brick");
        addBlocksFromType(Init.RHYOLITE_PILLAR, "Rhyolite Pillar");
        addBlocksFromType(Init.RHYOLITE_MOSAIC, "Rhyolite Mosaic");
        addBlocksFromType(Init.RHYOLITE_TILE, "Rhyolite Tile");
        addBlocksFromType(Init.RHYOLITE_LAMP, "Rhyolite Lamp");
        addBlock(Init.RHYOLITE_REDSTONE_LAMP, "Rhyolite Redstone Lamp");

        //Blue Calcite Variants
        addBaseBlocksFromType(Init.BLUE_CALCITE, "Blue Calcite");
        addBlocksFromType(Init.COBBLED_BLUE_CALCITE, "Cobbled Blue Calcite");
        addBlocksFromType(Init.CHISELED_BLUE_CALCITE, "Chiseled Blue Calcite");
        addBlocksFromType(Init.CRACKED_BLUE_CALCITE, "Cracked Blue Calcite");
        addBlocksFromType(Init.CUT_BLUE_CALCITE, "Cut Blue Calcite");
        addBlocksFromType(Init.POLISHED_BLUE_CALCITE, "Polished Blue Calcite");
        addBlocksFromBricksType(Init.BLUE_CALCITE_BRICKS, "Opal Brick");
        addBlocksFromType(Init.BLUE_CALCITE_PILLAR, "Blue Calcite Pillar");
        addBlocksFromType(Init.BLUE_CALCITE_MOSAIC, "Blue Calcite Mosaic");
        addBlocksFromType(Init.BLUE_CALCITE_TILE, "Blue Calcite Tile");
        addBlocksFromType(Init.BLUE_CALCITE_LAMP, "Blue Calcite Lamp");
        addBlock(Init.BLUE_CALCITE_REDSTONE_LAMP, "Blue Calcite Redstone Lamp");

        //Worn Granite Variants
        addBaseBlocksFromType(Init.WORN_GRANITE, "Worn Granite");
        addBlocksFromType(Init.COBBLED_WORN_GRANITE, "Cobbled Worn Granite");
        addBlocksFromType(Init.CHISELED_WORN_GRANITE, "Chiseled Worn Granite");
        addBlocksFromType(Init.CRACKED_WORN_GRANITE, "Cracked Worn Granite");
        addBlocksFromType(Init.CUT_WORN_GRANITE, "Cut Worn Granite");
        addBlocksFromType(Init.POLISHED_WORN_GRANITE, "Polished Worn Granite");
        addBlocksFromBricksType(Init.WORN_GRANITE_BRICKS, "Worn Granite Brick");
        addBlocksFromType(Init.WORN_GRANITE_PILLAR, "Worn Granite Pillar");
        addBlocksFromType(Init.WORN_GRANITE_MOSAIC, "Worn Granite Mosaic");
        addBlocksFromType(Init.WORN_GRANITE_TILE, "Worn Granite Tile");
        addBlocksFromType(Init.WORN_GRANITE_LAMP, "Worn Granite Lamp");
        addBlock(Init.WORN_GRANITE_REDSTONE_LAMP, "Worn Granite Redstone Lamp");

        add("itemGroup." + Init.MAIN.getId().toLanguageKey(), "Rocky Minerals");
    }

    private void addBlocksFromType(BlockType type, String base) {
        addBlock(type.block(), base);
        if (type.button() != null) addBlock(type.button(), base + " Button");
        if (type.slab() != null) addBlock(type.slab(), base + " Slab");
        if (type.stairs() != null) addBlock(type.stairs(), base + " Stairs");
        if (type.pressurePlate() != null) addBlock(type.pressurePlate(), base + " Pressure Plate");
        if (type.wall() != null) addBlock(type.wall(), base + " Wall");
    }

    private void addBaseBlocksFromType(BlockType type, String base) {
        addBlock(type.block(), "Block of " + base);
        if (type.button() != null) addBlock(type.button(), base + " Button");
        if (type.slab() != null) addBlock(type.slab(), base + " Slab");
        if (type.stairs() != null) addBlock(type.stairs(), base + " Stairs");
        if (type.pressurePlate() != null) addBlock(type.pressurePlate(), base + " Pressure Plate");
        if (type.wall() != null) addBlock(type.wall(), base + " Wall");
    }

    private void addBlocksFromBricksType(BlockType type, String base) {
        addBlock(type.block(), base + "s");
        if (type.button() != null) addBlock(type.button(), base + " Button");
        if (type.slab() != null) addBlock(type.slab(), base + " Slab");
        if (type.stairs() != null) addBlock(type.stairs(), base + " Stairs");
        if (type.pressurePlate() != null) addBlock(type.pressurePlate(), base + " Pressure Plate");
        if (type.wall() != null) addBlock(type.wall(), base + " Wall");
    }
}
