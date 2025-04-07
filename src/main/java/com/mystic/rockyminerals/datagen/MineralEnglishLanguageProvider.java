package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
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

    }

    private void addBlocksFromType(BlockType type, String base) {
        addBlock(type.block(), base);
        if(type.button() != null) addBlock(type.button(), base + " Button");
        if(type.slab() != null) addBlock(type.slab(), base + " Slab");
        if(type.stairs() != null) addBlock(type.stairs(), base + " Stairs");
        if(type.pressurePlate() != null) addBlock(type.pressurePlate(), base + " Pressure Plate");
        if(type.wall() != null) addBlock(type.wall(), base + " Wall");
    }

    private void addItem(RegistryObject<Item> registryObject) {
        addItem(registryObject, WordUtils.capitalizeFully(registryObject.getId().getPath().replace("_", " ")));
    }

    private <T extends Block> void addBlock(RegistryObject< T> registryObject) {
        addBlock(registryObject, WordUtils.capitalizeFully(registryObject.getId().getPath().replace("_", " ")));
    }


    private void add(RegistryObject<CreativeModeTab> tab, String entry) {
        this.add("itemGroup." + tab.getId().toLanguageKey(), entry);
    }
}
