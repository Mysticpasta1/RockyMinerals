package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.init.Init;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MineralBlockModelProvider extends BlockModelProvider {

    public MineralBlockModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        cubeAll(Init.ANHYDRITE_REDSTONE_LAMP, "_unlit");
        cubeAll(Init.ANHYDRITE_REDSTONE_LAMP, "_lit");

        cubeAll(Init.SALTSTONE_REDSTONE_LAMP, "_unlit");
        cubeAll(Init.SALTSTONE_REDSTONE_LAMP, "_lit");

        cubeAll(Init.HALITE_REDSTONE_LAMP, "_unlit");
        cubeAll(Init.HALITE_REDSTONE_LAMP, "_lit");

        cubeAll(Init.WORN_GRANITE_REDSTONE_LAMP, "_unlit");
        cubeAll(Init.WORN_GRANITE_REDSTONE_LAMP, "_lit");

        cubeAll(Init.OPAL_REDSTONE_LAMP, "_unlit");
        cubeAll(Init.OPAL_REDSTONE_LAMP, "_lit");

        cubeAll(Init.PUMICE_REDSTONE_LAMP, "_unlit");
        cubeAll(Init.PUMICE_REDSTONE_LAMP, "_lit");

        cubeAll(Init.RHYOLITE_REDSTONE_LAMP, "_unlit");
        cubeAll(Init.RHYOLITE_REDSTONE_LAMP, "_lit");
    }

    private <T extends Block> void cubeAll(RegistryObject<T> block, String name) {
        var texture = block.getId().withSuffix(name);
        this.cubeAll(texture.getPath(), blockTexture(texture));
    }

    private ResourceLocation blockTexture(ResourceLocation loc) {
        return ResourceLocation.fromNamespaceAndPath(loc.getNamespace(), "block/" + loc.getPath());
    }
}
