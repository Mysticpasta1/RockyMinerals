package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.TextureInfo;
import com.mystic.rockyminerals.api.set.MineralType;
import com.mystic.rockyminerals.api.set.MineralTypeRegistry;
import com.mystic.rockyminerals.api.set.StoneType;
import com.mystic.rockyminerals.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
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
            generateTexture("stonetype", currentType, Saltstone_Templates, generator, manager);
        }
        for (MineralType currentType : MineralTypeRegistry.getTypes()) {
            generateTexture("blue_calcite", currentType, BlueCacite_Templates, generator, manager);
        }

        /// Creating blockstates, models/block, models/item
        for (BlockType currentType : rockTypes) {
            BlockType baseType = StoneTypeRegistry.getSaltstoneType();

            /// Modifying blocks' files
            generateStandardResources(currentType, decorativeBlockTypes.get(currentType), baseType,
                    makeBlockStateTransformer(baseType, manager), makeModelTransformer(baseType, manager),
                    generator, manager
            );

            /// Modifying items' files
            if (!decorativeItemTypes.isEmpty()) {
                generateStandardItemModels(currentType, decorativeItemTypes.get(currentType), baseType,
                        makeModelTransformer(baseType, manager), generator, manager
                );
            }
        }
    }

    private static void generateTexture(String targetBlockType, BlockType blockType, List<TextureInfo> textureTemplates, DynClientResourcesGenerator generator, ResourceManager manager) {

        if (!targetBlockType.equals(blockType.getTypeName())) { // Skip the blocktype's template
            try (TextureImage stoneTexture = TextureImage.open(manager,
                    RPUtils.findFirstBlockTextureLocation(manager, blockType.getBlockOfThis("stone")))
            ) {
                for (TextureInfo textureInfo : textureTemplates) {
                    /// Creating new Path to add the new textures to the resources
                    String newResLoc = textureInfo.texture().toString()
                            .replace("template/", "")
                            .replace(targetBlockType, blockType.getTypeName());

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

}
