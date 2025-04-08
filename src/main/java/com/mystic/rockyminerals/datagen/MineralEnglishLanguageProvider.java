package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.init.Init;
import com.mystic.rockyminerals.utils.BlockType;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

public class MineralEnglishLanguageProvider extends LanguageProvider {

    public MineralEnglishLanguageProvider(PackOutput generator) {
        super(generator, Main.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Saltstone Variants
        addBlocksFromType(Init.SALTSTONE, "Saltstone");
        addBlocksFromType(Init.COBBLED_SALTSTONE, "Cobbled Saltstone");
        addBlocksFromType(Init.CHISELED_SALTSTONE, "Chiseled Saltstone");
        addBlocksFromType(Init.CRACKED_SALTSTONE, "Cracked Saltstone");
        addBlocksFromType(Init.CUT_SALTSTONE, "Cut Saltstone");
        addBlocksFromType(Init.POLISHED_SALTSTONE, "Polished Saltstone");
        addBlocksFromType(Init.SALTSTONE_BRICK, "Saltstone Brick");
        addBlocksFromType(Init.SALTSTONE_PILLAR, "Saltstone Pillar");
        addBlocksFromType(Init.SALTSTONE_MOSAIC, "Saltstone Mosaic");
        addBlocksFromType(Init.SALTSTONE_TILE, "Saltstone Tile");
        addBlocksFromType(Init.SALTSTONE_LAMP, "Saltstone Lamp");
        addBlocksFromType(Init.SALTSTONE_REDSTONE_LAMP, "Saltstone Redstone Lamp");
        add(Init.MAIN, "Rocky Minerals");
    }

    private void addBlocksFromType(BlockType type, String base) {
        addBlock(type.block(), base);
        if (type.button() != null) addBlock(type.button(), base + " Button");
        if (type.slab() != null) addBlock(type.slab(), base + " Slab");
        if (type.stairs() != null) addBlock(type.stairs(), base + " Stairs");
        if (type.pressurePlate() != null) addBlock(type.pressurePlate(), base + " Pressure Plate");
        if (type.wall() != null) addBlock(type.wall(), base + " Wall");
    }

    private void add(RegistryObject<CreativeModeTab> tab, String entry) {
        this.add("itemGroup." + tab.getId().toLanguageKey(), entry);
    }
}
