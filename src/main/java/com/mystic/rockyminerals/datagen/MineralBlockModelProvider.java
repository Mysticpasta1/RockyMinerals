package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MineralBlockModelProvider extends BlockModelProvider {
    public MineralBlockModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, Main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {}

    private <T extends Block> void cubeAll(RegistryObject<T> block) {
        this.cubeAll(block.getId().getPath(), blockTexture(block.getId()));
    }

    private <T extends Block> void cubeAll(RegistryObject<T> block, String name) {
        var texture = block.getId().withSuffix(name);
        this.cubeAll(texture.getPath(), blockTexture(texture));
    }

    private ResourceLocation blockTexture(ResourceLocation loc) {
        if (loc.getPath().contains("waxed")) {
            return new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath().replace("waxed_", ""));
        }
        return new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath());
    }
}
