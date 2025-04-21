package com.mystic.rockyminerals.misc;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.ResourceLocationTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.io.ByteArrayInputStream;
import java.util.*;

public class ResourcesUtils {

    /// Generate blockstates, models/block, and models/item
    public static <T extends BlockType> void generateStandardResources(
            T currentType, ArrayList<Block> decorativeType, T baseType,
            ResourceLocationTransformer<T> blockStateTransformer,
            ResourceLocationTransformer<T> modelTransformer,
            DynClientResourcesGenerator generator, ResourceManager manager) {

        decorativeType.forEach(( currentBlock) -> {

            if (decorativeType.isEmpty()) return;

            /// Get the equivalent name of block - Example: pumice_pillar, then the equivalent is saltstone_pillar
            Block baseBlock = BlockType.changeBlockType(currentBlock, currentType, baseType);

            if (baseBlock == null) {
                RockyMineral.LOGGER.error("Failed to get baseBlock for {}", Utils.getID(currentBlock).toString());
                return;
            }

            ResourceLocation baseBlockId = Utils.getID(baseBlock);


            /// Blockstate & models/block
            try {
                StaticResource baseBlockstate = StaticResource.getOrFail(manager, ResType.BLOCKSTATES.getPath(baseBlockId));
                StaticResource baseItemModel = StaticResource.getOrFail(manager, ResType.ITEM_MODELS.getPath(baseBlockId));

                JsonElement insideBlockstates = RPUtils.deserializeJson(new ByteArrayInputStream(baseBlockstate.data));
                JsonObject insideItemModel = RPUtils.deserializeJson(new ByteArrayInputStream(baseItemModel.data));

                //adds models referenced from here
                Set<String> blockModelsLoc = new HashSet<>(
                        RPUtils.findAllResourcesInJsonRecursive(insideBlockstates, s -> s.equals("model"))
                );
                Set<String> itemModelsLoc = new HashSet<>(
                        RPUtils.findAllResourcesInJsonRecursive(insideItemModel, s -> s.equals("model") || s.equals("parent"))
                );

                List<StaticResource> baseBlockModels = gatherNonVanillaModels(manager, blockModelsLoc);
                List<StaticResource> baseItemModels = gatherNonVanillaModels(manager, itemModelsLoc);

                ResourceLocation currentBlockId = Utils.getID(currentBlock);
                try {
                    /// creates blockstate
                    StaticResource newBlockState = blockStateTransformer.transform(baseBlockstate, currentBlockId, currentType);
                    Preconditions.checkArgument(newBlockState.location != baseBlockstate.location,
                            "ids can't be the same: " + newBlockState.location);

                    //Adding to the resources
                    generator.addResourceIfNotPresent(manager, newBlockState);

                    ///creates block model
                    for (StaticResource model : baseBlockModels) {
                        try {
                            // Modifying models' contents & path
                            StaticResource newModel = modelTransformer.transform(model, currentBlockId, currentType);
                            Preconditions.checkArgument(newModel.location != model.location,
                                    "ids cant be the same: " + newModel.location);

                            //Adding to the resources
                            generator.addResourceIfNotPresent(manager, newModel);
                        } catch (Exception e) {
                            RockyMineral.LOGGER.error("Failed to add {} model json file:", currentBlock, e);
                        }
                    }

                } catch (Exception e) {
                    RockyMineral.LOGGER.error("Failed to add {} blockstate json file:", currentBlock, e);
                }

                /// models/item
                try {
                    StaticResource newRes = modelTransformer.transform(baseItemModel, currentBlockId, currentType);
                    Preconditions.checkArgument(newRes.location != baseItemModel.location,
                            "ids cant be the same: " + newRes.location);

                    generator.addResourceIfNotPresent(manager, newRes);

                    for (StaticResource model : baseItemModels) {
                        try {
                            StaticResource newModel = modelTransformer.transform(model, currentBlockId, currentType);
                            Preconditions.checkArgument(newModel.location != model.location,
                                    "ids cant be the same: " + model.location);

                            generator.addResourceIfNotPresent(manager, newModel);
                        } catch (Exception ex) {
                            RockyMineral.LOGGER.error("Failed to modify {} model json file:", currentBlock, ex);
                        }
                    }

                } catch (Exception ex) {
                    RockyMineral.LOGGER.error("Failed to add {} item model json file:", currentBlock, ex);
                }
            } catch (Exception e) {
                RockyMineral.LOGGER.error("Could not find file definition for {} - {}", baseBlock, e);
            }
        });



    }

    /// Generate only models/item for ITEMS
    public static <T extends BlockType> void generateStandardItemModels(
            T currentType, ArrayList<Item> decorativeType, T baseType,
            ResourceLocationTransformer<T> itemModelTransformer,
            DynClientResourcesGenerator generator, ResourceManager manager) {

        decorativeType.forEach((currentItem) -> {

            if (decorativeType.isEmpty()) return;

            /// Get the equivalent name of block - Example: pumice_pillar, then the equivalent is saltstone_pillar
            Item baseItem = BlockType.changeItemType(currentItem, currentType, baseType);

            if (baseItem == null) {
                RockyMineral.LOGGER.error("Failed to get baseItem for {}", Utils.getID(currentItem).toString());
                return;
            }
            String baseItemName = baseType.getTypeName();


            /// models/item
            try {

                StaticResource baseItemModel = StaticResource.getOrFail(manager,
                        ResType.ITEM_MODELS.getPath(Utils.getID(baseItem)));

                JsonObject json = RPUtils.deserializeJson(new ByteArrayInputStream(baseItemModel.data));

                //adds models referenced from here, it will not be recursive
                Set<String> modelsLoc = new HashSet<>(
                        RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model") || s.equals("parent"))
                );

                if (json.has("parent")) {
                    String parent = json.get("parent").getAsString();
                    if (parent.contains("item/generated")) {
                        itemModelTransformer.replaceItemType(baseItemName);
                    }
                }

                List<StaticResource> baseItemModels = gatherNonVanillaModels(manager, modelsLoc);

                ResourceLocation currentItemId = Utils.getID(currentItem);
                try {
                    StaticResource newRes = itemModelTransformer.transform(baseItemModel, currentItemId, currentType);
                    Preconditions.checkArgument(newRes.location != baseItemModel.location,
                            "ids cant be the same: " + newRes.location);

                    generator.addResourceIfNotPresent(manager, newRes);

                    for (StaticResource model : baseItemModels) {
                        try {
                            StaticResource newModel = itemModelTransformer.transform(model, currentItemId, currentType);
                            Preconditions.checkArgument(newModel.location != model.location,
                                    "ids cant be the same: " + model.location);

                            generator.addResourceIfNotPresent(manager, newModel);
                        } catch (Exception ex) {
                            RockyMineral.LOGGER.error("Failed to add {} model json file:", currentItem, ex);
                        }
                    }

                } catch (Exception ex) {
                    RockyMineral.LOGGER.error("Failed to add {} item model json file:", currentItem, ex);
                }


            } catch (Exception e) {
                RockyMineral.LOGGER.error("Could not find item model for {} - {}", baseItem, e);
            }

        });
    }


    /// Create a new instance of ResourceLocationTransformer
    public static <T extends BlockType> ResourceLocationTransformer<T> makeModelTransformer(T baseType, ResourceManager manager) {
        ResourceLocationTransformer<T> modelTransformer = ResourceLocationTransformer.create(RockyMineral.MOD_ID, manager);

        addBuiltinModelTransformer(modelTransformer, baseType);

        return modelTransformer;
    }

    /// Create a new instance of ResourceLocationTransformer
    public static <T extends BlockType> ResourceLocationTransformer<T> makeBlockStateTransformer(T baseType, ResourceManager manager) {
        String baseBlockName = baseType.getTypeName();
        return ResourceLocationTransformer.<T>create(RockyMineral.MOD_ID, manager)
                .replaceBlockType(baseBlockName)
                .IDReplaceType(baseBlockName);
    }

    @SuppressWarnings("UnusedReturnValue")
    /// Responsible for modifying the ResourceLocation & contents of model files
    public static <T extends BlockType> ResourceLocationTransformer<T> addBuiltinModelTransformer(
            ResourceLocationTransformer<T> transformer, BlockType baseType) {
        String oldTypeName = baseType.getTypeName();

        // Modifying models' filename & ResourceLocation
        transformer.setIDModifier((text, blockid, blockType) ->
                ResourceLocationTransformer.replaceFullGenericType(text, blockType, blockid, oldTypeName, null, 2));

        // Modifying the model files' content
        transformer.replaceGenericType(oldTypeName, "block");

        return transformer;
    }

    /// Gather all of model files excluding Minecraft
    private static List<StaticResource> gatherNonVanillaModels(ResourceManager manager, Set<String> modelsLoc) {
        List<StaticResource> models = new ArrayList<>();

        for (var m : modelsLoc) {
            //remove the ones from mc namespace
            ResourceLocation modelRes = ResourceLocation.parse(m);
            if (!modelRes.getNamespace().equals("minecraft")) {
                StaticResource model = StaticResource.getOrLog(manager, ResType.MODELS.getPath(m));
                if (model != null) models.add(model);
            }
        }
        return models;
    }
}
