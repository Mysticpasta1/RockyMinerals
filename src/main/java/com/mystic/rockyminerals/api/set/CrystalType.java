package com.mystic.rockyminerals.api.set;

import com.mystic.rockyminerals.Main;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class CrystalType extends RockType {

    /**
     * Childkey Availability:
     * stone, stairs, slab, wall, button, pressure_plate, smooth_stone
     * polished, polished_stairs, polished_slab
     * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles
     **/

    public final Block stone;

    protected CrystalType(ResourceLocation id, Block stone) {
        super(id, stone);
        this.stone = stone;
    }

    @Override
    public String getTranslationKey() {
        return "crystal_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    public ItemLike mainChild() {
        return this.stone;
    }

    public Block bricksOrStone() {
        Block bricks= this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.stone;
    }

    @Override
    protected void initializeChildrenItems() {
        this.addChild("shard", this.findRelatedEntry("shard", BuiltInRegistries.ITEM));
    }

    public static class Finder implements BlockType.SetFinder<CrystalType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> crystalFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> crystal) {
            this.id = id;
            this.crystalFinder = crystal;
        }

        public static CrystalType.Finder vanilla(String nameCrystal, String blockId){
            return simple("minecraft", nameCrystal, blockId);
        }

        public static CrystalType.Finder simple(String modId, String nameCrystalType, String nameCrystal) {
            return simple(ResourceLocation.fromNamespaceAndPath(modId, nameCrystalType), ResourceLocation.fromNamespaceAndPath(modId, nameCrystal));
        }

        public static CrystalType.Finder simple(ResourceLocation nameCrystalTYpe, ResourceLocation nameCrystal) {
            return new CrystalType.Finder(nameCrystalTYpe,
                    () -> BuiltInRegistries.BLOCK.get(nameCrystal));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, ResourceLocation.fromNamespaceAndPath(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<CrystalType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block crystal = crystalFinder.get();
                    var d = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (crystal != d && crystal != null) {
                        var w = new CrystalType(id, crystal);
                        childNames.forEach((key, value) -> w.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(w);
                    }
                } catch (Exception ignored) {
                }
                Main.LOGGER.warn("Failed to find custom crystal type {}", id);
            }
            return Optional.empty();
        }
    }

}
