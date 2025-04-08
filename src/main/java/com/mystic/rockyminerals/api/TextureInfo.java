package com.mystic.rockyminerals.api;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public record TextureInfo(ResourceLocation texture, @Nullable ResourceLocation mask,
                          boolean copyMCMETA, boolean onAtlas) {

    public static Builder of(ResourceLocation res) {
        return new Builder(res);
    }

    public static final class Builder {
        private final ResourceLocation texture;
        private ResourceLocation mask;
        private boolean copyMCMETA = false;
        private boolean onAtlas;

        public Builder(ResourceLocation texture) {
            this.texture = texture;
            this.onAtlas = !texture.getPath().startsWith("entity/");
        }

        public Builder mask(ResourceLocation mask) {
            this.mask = mask;
            return this;
        }

        // for textures not on atlas that won't be cleared
        public Builder forEntityOrGui(){
            this.onAtlas = false;
            return this;
        }

        public Builder copyMCMETA() {
            this.copyMCMETA = true;
            return this;
        }

        public TextureInfo build() {
            return new TextureInfo(texture, mask, copyMCMETA, onAtlas);
        }
    }
}
