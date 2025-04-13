package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.init.Init;
import com.mystic.rockyminerals.utils.BlockType;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

public class MineralBlockStateProvider extends MainProvider.Proxied {
    public MineralBlockStateProvider(MainProvider provider) {
        super(provider);
    }

    @Override
    public void registerStatesAndModels() {
        BlockType.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach(this::registerBlockFamily);
        registerLamp(Init.SALTSTONE_REDSTONE_LAMP.get(), Init.SALTSTONE_REDSTONE_LAMP.get());
        registerLamp(Init.HALITE_REDSTONE_LAMP.get(), Init.HALITE_REDSTONE_LAMP.get());
    }

    private void registerBlockFamily(BlockFamily family) {
        registerBlockItem(family.getBaseBlock());
        family.getVariants().keySet().forEach(variant -> processVariant(variant, family));
    }

    private void registerBlockItem(Block block) {
        registerBlockItem(block, block);
    }

    private void registerBlockItem(Block block, Block texture) {
        if(block instanceof RotatedPillarBlock pillarBlock) {
            simpleBlockItem(block, models().cubeColumn(name(block), blockTexture(block), blockTexture(texture)));
            axisBlock(pillarBlock, blockTexture(block), extendPillar(blockTexture(texture)));
        } else {
            simpleBlockWithItem(block, cubeAll(texture));
        }
    }

    private String name(Block block) {
        return this.key(block).getPath();
    }

    private ResourceLocation extendPillar(ResourceLocation rl) {
        String var10002 = rl.getNamespace();
        String var10003 = rl.getPath();
        return ResourceLocation.fromNamespaceAndPath(var10002, var10003 + "_top");
    }

    private void processVariant(BlockFamily.Variant variant, BlockFamily family) {
        Block original = family.getBaseBlock();
        Block variantTarget = family.getVariants().get(variant);
        switch (variant) {
            case BUTTON -> registerButton(variantTarget, original);
            case SLAB -> registerSlab((SlabBlock) variantTarget, original);
            case STAIRS -> registerStairs((StairBlock) variantTarget, original);
            case PRESSURE_PLATE -> registerPressurePlate((PressurePlateBlock) variantTarget, original);
            case WALL -> registerWall((WallBlock) variantTarget, original);
        }
    }

    private void registerStairs(StairBlock stairs, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        stairsBlock(stairs, texture);
        simpleBlockItem(stairs, itemModels().stairs("block/" + key(stairs).getPath(), texture, texture, texture));
    }

    private void registerSlab(SlabBlock slab, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        slabBlock(slab, texture, texture);
        simpleBlockItem(slab, itemModels().slab("block/" + key(slab).getPath(), texture, texture, texture));
    }

    private void registerWall(WallBlock wall, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        wallBlock(wall, texture);
        simpleBlockItem(wall, itemModels().wallInventory("block/" + key(wall).getPath(), texture));
    }

    private void registerPressurePlate(PressurePlateBlock pressurePlate, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        pressurePlateBlock(pressurePlate, texture);
        itemModels().pressurePlate(key(pressurePlate).getPath(), texture);
    }

    private void registerButton(Block button, Block texturedBlock) {
        ResourceLocation buttonId = key(button);
        ResourceLocation textureBlockId = key(texturedBlock);

        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(textureBlockId.getNamespace(), "block/" + textureBlockId.getPath());
        buttonBlock((ButtonBlock) button, texture);
        itemModels().buttonInventory(buttonId.getPath(), texture);
    }


    private void registerLamp(Block lamp, Block texture) {
        var unlit = this.blockTexture(texture).withSuffix("_unlit");
        var lit = this.blockTexture(texture).withSuffix("_lit");

        this.getVariantBuilder(lamp)
                .partialState().with(RedstoneLampBlock.LIT, false).addModels(new ConfiguredModel(new ModelFile.UncheckedModelFile(unlit)))
                .partialState().with(RedstoneLampBlock.LIT, true).addModels(new ConfiguredModel(new ModelFile.UncheckedModelFile(lit)));
        simpleBlockItem(lamp, new ModelFile.UncheckedModelFile(unlit));
    }

}
