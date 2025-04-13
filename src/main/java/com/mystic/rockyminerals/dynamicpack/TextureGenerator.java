package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.api.TextureInfo;
import com.mystic.rockyminerals.api.set.*;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

import static com.mystic.rockyminerals.misc.SpriteHelper.textureReference;


public class TextureGenerator {

    public static void generateTextures(DynClientResourcesGenerator generator, ResourceManager manager) {
        for (StoneType currentType : StoneTypeRegistry.getTypes()) {
            if (currentType.isVanilla()) continue;
            creatingTexture(currentType, generator, manager);
        }
        for (MineralType currentType : MineralTypeRegistry.getTypes()) {
            if (currentType.isVanilla()) continue;
            creatingTexture(currentType, generator, manager);
        }
    }

    private static void creatingTexture(RockType rockType, DynClientResourcesGenerator generator, ResourceManager manager) {

        try (TextureImage stoneTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, rockType.getBlockOfThis("stone")))
        ) {
            for (TextureInfo textureInfo : textureReference) {
                /// Creating new Path to add the new textures to the resources
                String newResLoc = textureInfo.texture().toString()
                        .replace("template/", "")
                        .replace("stonetype", rockType.getTypeName());

                try (TextureImage baseTexture = TextureImage.open(manager, textureInfo.texture())
                ) {
                    TextureImage maskTexture;
                    Respriter respriter;

                    if (Objects.nonNull(textureInfo.mask())) {
                        maskTexture = TextureImage.open(manager, textureInfo.mask());
                        respriter = Respriter.masked(baseTexture, maskTexture);
                    }
                    else {
                        respriter = Respriter.of(baseTexture);
                    }

                    /// Swapping out the old palettes of the texture with new plattes
                    Supplier<TextureImage> finishedTexture = () -> respriter.recolorWithAnimationOf(stoneTexture);

                    /// Adding the textures to the resource
                    generator.addTextureIfNotPresent(manager, newResLoc, finishedTexture);
                }
                catch (IOException e) {
                    generator.getLogger().error("Failed to generate texture for {} with {} : {}", textureInfo.blockId(), rockType.getTypeName(), e);
                }
            }

        } catch (Exception e) {
            Main.LOGGER.error(e.getMessage());
        }
    }

}
