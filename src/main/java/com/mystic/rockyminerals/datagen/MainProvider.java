package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainProvider extends BlockStateProvider {
    private final PackOutput packOutput;
    private final ExistingFileHelper existingFileHelper;
    List<Proxied> providers;

    @SafeVarargs
    public MainProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper, Function<MainProvider, Proxied>... providers) {
        super(packOutput, Main.MODID, existingFileHelper);
        this.packOutput = packOutput;
        this.existingFileHelper = existingFileHelper;
        this.providers = Stream.of(providers).map(provider -> provider.apply(this)).collect(Collectors.toList());
    }

    @Override
    protected void registerStatesAndModels() {
        for (Proxied provider : providers) {
            provider.registerStatesAndModels();
        }
    }

    public PackOutput getPackOutput() {
        return packOutput;
    }

    public ExistingFileHelper getExistingFileHelper() {
        return existingFileHelper;
    }

    public abstract static class Proxied extends BlockStateProvider {
        private final MainProvider provider;

        public Proxied(MainProvider provider) {
            super(provider.getPackOutput(), Main.MODID, provider.getExistingFileHelper());
            this.provider = provider;
        }

        @Override
        public BlockModelProvider models() {
            return provider.models();
        }

        @Override
        public ItemModelProvider itemModels() {
            return provider.itemModels();
        }

        public VariantBlockStateBuilder getVariantBuilder(Block b) {
            return provider.getVariantBuilder(b);
        }

        public MultiPartBlockStateBuilder getMultipartBuilder(Block b) {
            return provider.getMultipartBuilder(b);
        }

        public ResourceLocation key(Block block) {
            return BuiltInRegistries.BLOCK.getKey(block);
        }

        public abstract void registerStatesAndModels();
    }

}
