package com.mystic.rockyminerals.api.set;

import com.mystic.rockyminerals.RockyMineral;
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

public class MineralType extends StoneType {

    /**
     * Childkey Availability:
     * stone, stairs, slab, wall, button, pressure_plate, smooth_stone
     * polished, polished_stairs, polished_slab
     * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles
     **/

    public final Block stone;

    protected MineralType(ResourceLocation id, Block stone) {
        super(id, stone);
        this.stone = stone;
    }

    @Override
    public String getTranslationKey() {
        return "mineral_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    public ItemLike mainChild() {
        return this.stone;
    }

    public static class Finder implements BlockType.SetFinder<MineralType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> mineralFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> mineral) {
            this.id = id;
            this.mineralFinder = mineral;
        }

        public static MineralType.Finder vanilla(String nameMineral, String blockId){
            return simple("minecraft", nameMineral, blockId);
        }

        public static MineralType.Finder simple(String modId, String nameMineralType, String nameMineral) {
            return simple(ResourceLocation.fromNamespaceAndPath(modId, nameMineralType), ResourceLocation.fromNamespaceAndPath(modId, nameMineral));
        }

        public static MineralType.Finder simple(ResourceLocation nameCrystalTYpe, ResourceLocation nameMineral) {
            return new MineralType.Finder(nameCrystalTYpe,
                    () -> BuiltInRegistries.BLOCK.get(nameMineral));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, ResourceLocation.fromNamespaceAndPath(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<MineralType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block mineral = mineralFinder.get();
                    Block blockKey = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (mineral != blockKey && mineral != null) {
                        MineralType mineralType = new MineralType(id, mineral);
                        childNames.forEach((key, value) -> mineralType.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(mineralType);
                    }
                } catch (Exception ignored) {
                }
                RockyMineral.LOGGER.warn("Failed to find custom mineral type {}", id);
            }
            return Optional.empty();
        }
    }

}
