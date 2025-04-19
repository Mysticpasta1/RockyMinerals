package com.mystic.rockyminerals.dynamicpack;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.TextureInfo;
import com.mystic.rockyminerals.api.set.*;
import com.mystic.rockyminerals.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

import static com.mystic.rockyminerals.misc.ResourcesHelper.BlueCacite_Templates;
import static com.mystic.rockyminerals.misc.ResourcesHelper.Saltstone_Templates;

public class ResourcesGenerator {

    public static void generateResources(DynClientResourcesGenerator generator, ResourceManager manager) {
        for (StoneType currentType : StoneTypeRegistry.getTypes()) {
            if (currentType.isVanilla()) continue;
            generateTexture(currentType, Saltstone_Templates, generator, manager);

        }
        for (MineralType currentType : MineralTypeRegistry.getTypes()) {
            if (currentType.isVanilla()) continue;
            generateTexture(currentType, BlueCacite_Templates, generator, manager);
        }
    }

    private static void generateTexture(BlockType blockType, List<TextureInfo> textureTemplates, DynClientResourcesGenerator generator, ResourceManager manager) {

        try (TextureImage stoneTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, blockType.getBlockOfThis("stone")))
        ) {
            for (TextureInfo textureInfo : textureTemplates) {
                /// Creating new Path to add the new textures to the resources
                String newResLoc = textureInfo.texture().toString()
                        .replace("template/", "")
                        .replace("stonetype", blockType.getTypeName());

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
                    generator.getLogger().error("Failed to generate texture for {} with {} : {}", textureInfo.blockId(), blockType.getTypeName(), e);
                }
            }

        } catch (Exception e) {
            RockyMineral.LOGGER.error(e.getMessage());
        }
    }

}
