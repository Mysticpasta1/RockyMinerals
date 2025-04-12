package com.mystic.rockyminerals.api;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public record TextureInfo(String blockId, ResourceLocation texture, ResourceLocation mask,
                          boolean onAtlas) {


    public static Builder of(String blockId, ResourceLocation texture) {
        return new Builder(blockId, texture);
    }

    public static Builder of(String blockId, ResourceLocation texture, ResourceLocation mask) {
        return new Builder(blockId, texture, mask);
    }

    public static final class Builder {
        private String blockId;
        private ResourceLocation texture;
        private ResourceLocation mask;
        private final boolean onAtlas;

        public Builder(String blockId, ResourceLocation texture) {
            this.blockId = blockId;
            this.texture = texture;
            this.onAtlas = !texture.getPath().startsWith("entity/");
        }

        public Builder(String blockId, ResourceLocation texture, ResourceLocation mask) {
            this.blockId = blockId;
            this.texture = texture;
            this.mask = mask;
            this.onAtlas = !texture.getPath().startsWith("entity/");
        }

        public Builder(List<Pair<ResourceLocation, ResourceLocation>> pairList) {
            this.onAtlas = !pairList.get(0).getFirst().getPath().startsWith("entity/");

        }

        public TextureInfo build() {
            return new TextureInfo(blockId, texture, mask, onAtlas);
        }
    }
}
