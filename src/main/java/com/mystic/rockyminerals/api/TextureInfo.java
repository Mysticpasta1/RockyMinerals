package com.mystic.rockyminerals.api;

import net.minecraft.resources.ResourceLocation;

public record TextureInfo(String blockId, ResourceLocation textureResLoc, ResourceLocation mask, String customPath,
                          boolean onAtlas) {


    public static Builder of(String blockId, ResourceLocation texture) {
        return new Builder("rockyminerals:" + blockId, texture);
    }

    public static Builder of(String blockId, ResourceLocation texture, ResourceLocation mask) {
        return new Builder("rockyminerals:" +  blockId, texture, mask);
    }

    public static final class Builder {
        private final String blockId;
        private final ResourceLocation texture;
        private ResourceLocation mask;
        private final boolean onAtlas;
        private String customPath = "";

        public Builder customPath(String path) {
            this.customPath = path;
            return this;
        }

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

        public TextureInfo build() {
            return new TextureInfo(blockId, texture, mask, customPath, onAtlas);
        }
    }
}
