package com.mystic.rockyminerals.dynamicpack;

import com.google.gson.Gson;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.api.TextureInfo;
import com.mystic.rockyminerals.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.misc.StrOpt;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

import static com.mystic.rockyminerals.misc.SpriteHelper.textureReference;


public class TextureGenerator {

    protected static final Set<TextureInfo> textures = new HashSet<>(); //TODO: WIP

    private static final Gson GSON = new Gson();

    // only works for wood.
    public record RecolorableTexture(List<Text> textures, boolean mergePalette, Block block) {

        public static final Codec<RecolorableTexture> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Text.TEXT_CODEC.listOf().fieldOf("textures").forGetter(RecolorableTexture::textures),
                StrOpt.of(Codec.BOOL, "merge_palette", true).forGetter(RecolorableTexture::mergePalette),
                BuiltInRegistries.BLOCK.byNameCodec().fieldOf("planks_block").forGetter(RecolorableTexture::block)
        ).apply(instance, RecolorableTexture::new));
    }

    public record Text(ResourceLocation text, @Nullable ResourceLocation mask) {
        public static final Codec<Text> TEXT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ResourceLocation.CODEC.fieldOf("texture").forGetter(Text::text),
                StrOpt.of(ResourceLocation.CODEC, "mask").forGetter(t -> Optional.ofNullable(t.mask))
        ).apply(instance, (a, b) -> new Text(a, b.orElse(null))));
    }

    public static void generateExtraTextures(DynClientResourcesGenerator generator, ResourceManager manager) {
//        Map<ResourceLocation, JsonElement> map = new HashMap<>();
//        SimpleJsonResourceReloadListener.scanDirectory(manager, "recolorable_textures", GSON, map);
//        var extraTextures = new ArrayList<RecolorableTexture>();
//        map.forEach((id, json) -> {
//
//            RecolorableTexture modifier = RecolorableTexture.CODEC.decode(JsonOps.INSTANCE, json)
//                    .getOrThrow(false, errorMsg -> Main.LOGGER.warn("Failed to load recolorable texture {}: {}", id, errorMsg))
//                    .getFirst();
//
//            extraTextures.add(modifier);
//        });

        generateTextures(generator, manager);
    }

    public static void generateTextures(DynClientResourcesGenerator generator, ResourceManager manager) {
        for (var currentType : StoneTypeRegistry.getTypes()) {
            if (currentType.isVanilla()) continue;

            try (TextureImage stoneTexture = TextureImage.open(manager,
                    RPUtils.findFirstBlockTextureLocation(manager, currentType.stone))
            ) {
                for (TextureInfo textureInfo : textureReference) {
                    /// Creating new Path to add the new textures to the resources
                    String newResLoc = textureInfo.texture().toString()
                            .replace("template/", "")
                            .replace("stonetype", currentType.getTypeName());

//                    Supplier<TextureImage> finishedTexture = null;

//                    if (Objects.nonNull(textureInfo.mask())) {
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
                            generator.getLogger().error("Failed to generate texture for {} with {} : {}", textureInfo.blockId(), currentType.getTypeName(), e);
                        }
//                    }
                }

            } catch (Exception e) {
                Main.LOGGER.error(e.getMessage());
            }
        }
    }

}
