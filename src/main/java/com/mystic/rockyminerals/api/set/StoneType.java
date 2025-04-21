package com.mystic.rockyminerals.api.set;

import com.mystic.rockyminerals.RockyMineral;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class StoneType extends RockType {

    /**
     * Childkey Availability:
     * stone, stairs, slab, wall, button, pressure_plate, smooth_stone
     * polished, polished_stairs, polished_slab
     * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles
    **/

    public final Block stone;

    protected StoneType(ResourceLocation id, Block stone) {
        super(id, stone);
        this.stone = stone;
    }

    @Override
    public String getTranslationKey() {
        return "stone_type." + this.getTypeName();
    }

    @Override
    public ItemLike mainChild() {
        return this.stone;
    }

    public static class Finder implements SetFinder<StoneType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> stoneFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> stone) {
            this.id = id;
            this.stoneFinder = stone;
        }

        public static Finder vanilla(String nameStone){
            return simple("minecraft", nameStone, nameStone);
        }

        public static Finder simple(String modId, String nameStoneType, String nameStone) {
            return simple(ResourceLocation.fromNamespaceAndPath(modId, nameStoneType), ResourceLocation.fromNamespaceAndPath(modId, nameStone));
        }

        public static Finder simple(ResourceLocation nameStoneTYpe, ResourceLocation nameStone) {
            return new Finder(nameStoneTYpe,
                    () -> BuiltInRegistries.BLOCK.get(nameStone));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, ResourceLocation.fromNamespaceAndPath(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<StoneType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block stone = stoneFinder.get();
                    Block blockKey = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (stone != blockKey && stone != null) {
                        StoneType stoneType = new StoneType(id, stone);
                        childNames.forEach((key, value) -> stoneType.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(stoneType);
                    }
                } catch (Exception ignored) {
                }
                RockyMineral.LOGGER.warn("Failed to find custom stone type {}", id);
            }
            return Optional.empty();
        }
    }

}
