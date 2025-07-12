package com.mystic.rockyminerals.dynamicpack;

import com.mojang.blaze3d.platform.NativeImage;
import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.TextureInfo;
import com.mystic.rockyminerals.api.set.MineralType;
import com.mystic.rockyminerals.api.set.MineralTypeRegistry;
import com.mystic.rockyminerals.api.set.StoneType;
import com.mystic.rockyminerals.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

import static com.mystic.rockyminerals.misc.ResourcesHelper.BlueCacite_Templates;
import static com.mystic.rockyminerals.misc.ResourcesHelper.Saltstone_Templates;
import static com.mystic.rockyminerals.misc.ResourcesUtils.*;

public class ResourcesGenerator {

    public static Map<BlockType, ArrayList<Block>> decorativeBlockTypes = new HashMap<>();
    public static Map<BlockType, ArrayList<Item>> decorativeItemTypes = new HashMap<>();
    public static ArrayList<BlockType> rockTypes = new ArrayList<>();

    public static void generateResources(DynClientResourcesGenerator generator, ResourceManager manager) {

        /// Creating textures for all RockTypes
        for (StoneType currentType : StoneTypeRegistry.getTypes()) {
            generateTexture("saltstone", currentType, Saltstone_Templates, generator, manager);
        }
        for (MineralType currentType : MineralTypeRegistry.getTypes()) {
            generateTexture("blue_calcite", currentType, BlueCacite_Templates, generator, manager);
        }

        /// Creating blockstates, models/block, models/item
        for (BlockType currentType : rockTypes) {
            BlockType baseType = StoneTypeRegistry.getSaltstoneType();

            /// Modifying blockstates & models/block files
            generateStandardResources(currentType, decorativeBlockTypes.get(currentType), baseType,
                    makeBlockStateTransformer(baseType, manager), makeModelTransformer(baseType, manager),
                    generator, manager
            );

            /// Modifying models/item files
            if (!decorativeItemTypes.isEmpty()) {
                generateStandardItemModels(currentType, decorativeItemTypes.get(currentType), baseType,
                        makeModelTransformer(baseType, manager), generator, manager
                );
            }
        }
    }

    private static void generateTexture(String targetBlockType, BlockType blockType, List<TextureInfo> textureTemplates,
                                        DynClientResourcesGenerator generator, ResourceManager manager) {

        if (!targetBlockType.equals(blockType.getTypeName())) { // Skip the blocktype's template
            try (TextureImage stoneImage = TextureImage.open(manager,
                    RPUtils.findFirstBlockTextureLocation(manager, blockType.getBlockOfThis("stone")))
            ) {
                for (TextureInfo textureInfo : textureTemplates) {
                    /// Creating new Path to add the new textures to the resources
                    String newResLoc;
                    if (textureInfo.customPath().isEmpty())
                        newResLoc = textureInfo.textureResLoc().toString().replace(targetBlockType, blockType.getTypeName());
                    else
                        newResLoc = textureInfo.customPath().replace(targetBlockType, blockType.getTypeName());

                    try (TextureImage baseTexture = TextureImage.open(manager, textureInfo.textureResLoc())) {

                        TextureImage maskTexture;
                        Respriter respriter;

                        if (Objects.nonNull(textureInfo.mask())) {
                            maskTexture = TextureImage.open(manager, textureInfo.mask());
                            respriter = Respriter.masked(baseTexture, maskTexture);
                        }
                        else {
                            respriter = Respriter.of(baseTexture);
                        }

                        TextureImage stoneTexture;
                        // Creating a 16x16 texture from an animated log's texture
                        if (Objects.nonNull(stoneImage.getMcMeta())) {
                            NativeImage standardSize = new NativeImage(16, 16, false);
                            standardSize.copyFrom(stoneImage.getImage());
                            stoneTexture = TextureImage.of(standardSize);
                        }
                        // Default
                        else {
                            stoneTexture = stoneImage;
                        }

                        /// Swapping out the old palettes of the texture with new plattes
                        List<Palette> paletteTexture = Palette.fromAnimatedImage(stoneTexture);
                        Supplier<TextureImage> finishedTexture = () -> respriter.recolor(paletteTexture);

                        /// Post-Processing the texture
                        if (newResLoc.contains("mossy")) finishedTexture = postProcessTexture(blockType, manager, finishedTexture);

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

    public static Supplier<TextureImage> postProcessTexture(BlockType blockType, ResourceManager manager,
                                                            Supplier<TextureImage> textureSupplier) {
        if (blockType.getClass() == StoneType.class) {
            return maybePostProcessStoneTexture((StoneType) blockType, manager, textureSupplier);
        }
        return textureSupplier;
    }

    public static Supplier<TextureImage> maybePostProcessStoneTexture(StoneType stoneType, ResourceManager manager, Supplier<TextureImage> textureSupplier) {
        return () -> {
            var texture = textureSupplier.get();
            mossyTexture(texture, manager, stoneType);
            return texture;
        };
    }

    private static void mossyTexture(TextureImage textureImage, ResourceManager manager, StoneType stoneType) {
        try (TextureImage mossyOverlay = TextureImage.open(manager, RockyMineral.res("block/overlay/mossy"))) {
            textureImage.applyOverlayOnExisting(mossyOverlay);

        } catch (Exception e) {
            RockyMineral.LOGGER.warn("Failed to apply mossy overlay for {} texture: {}", stoneType.id.toString(), String.valueOf(e));
        }
    }

}
