package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MineralItemModelProvider extends ItemModelProvider {
    public MineralItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, Main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    private <T extends Block> void block(RegistryObject<T> block) {
        withExistingParent(block.getId().getPath(), block(block.getId()));
    }

    private <T extends Item> void item(RegistryObject<T> block) {
        try {
            getBuilder(block.getId().getPath())
                    .parent(getExistingFile(mcLoc("item/generated")))
                    .texture("layer0", items(block.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ResourceLocation block(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), "block/" + location.getPath());
    }


    private ResourceLocation items(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), "item/" + location.getPath());
    }
}
