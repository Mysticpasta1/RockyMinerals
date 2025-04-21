package com.mystic.rockyminerals.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

import static com.mystic.rockyminerals.dynamicpack.ResourcesGenerator.decorativeBlockTypes;
import static com.mystic.rockyminerals.dynamicpack.ResourcesGenerator.decorativeItemTypes;

public abstract class RockType extends BlockType{
    /**
     * Childkey Availability:
     * stone, cobblestone, stairs, slab, wall, button, pressure_plate, smooth_stone
     * polished, polished_stairs, polished_slab
     * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles,
     * mossy_bricks, mossy_brick_slab, mossy_brick_stairs, mossy_brick_wall
     **/

    public final Block block;

    protected RockType(ResourceLocation id, Block stone) {
        super(id);
        this.block = stone;
    }

    @Override
    public ItemLike mainChild() {
        return block;
    }

    public Block bricksOrStone() {
        Block bricks= this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.block;
    }

    @Override
    protected void initializeChildrenBlocks() {
        this.addChild("stone", this.block);
        this.addChild("stairs", this.findRelatedEntry("stairs", BuiltInRegistries.BLOCK));
        this.addChild("slab", this.findRelatedEntry("slab", BuiltInRegistries.BLOCK));
        this.addChild("wall", this.findRelatedEntry("wall", BuiltInRegistries.BLOCK));
        this.addChild("button", this.findRelatedEntry("button", BuiltInRegistries.BLOCK));
        this.addChild("pressure_plate", this.findRelatedEntry("pressure_plate", BuiltInRegistries.BLOCK));
        this.addChild("smooth", this.findRelatedEntry("smooth", "stone", BuiltInRegistries.BLOCK));
        this.addChild("redstone_lamp", this.findRelatedEntry("redstone_lamp", BuiltInRegistries.BLOCK));

        Block polished = this.findRelatedEntry("polished", BuiltInRegistries.BLOCK);
        this.addChild("polished", polished);
        if (Objects.nonNull(polished)) {
            this.addChild("polished_stairs", findRelatedEntry("polished", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("polished_slab", findRelatedEntry("polished", "slab", BuiltInRegistries.BLOCK));
            this.addChild("polished_wall", findRelatedEntry("polished", "wall", BuiltInRegistries.BLOCK));
            this.addChild("polished_button", findRelatedEntry("polished", "button", BuiltInRegistries.BLOCK));
            this.addChild("polished_pressure_plate", findRelatedEntry("polished", "pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block tile = this.findRelatedEntry("tile", BuiltInRegistries.BLOCK);
        this.addChild("tile", tile);
        if (Objects.nonNull(tile)) {
            this.addChild("tile_stairs", findRelatedEntry("tile_stairs", BuiltInRegistries.BLOCK));
            this.addChild("tile_slab", findRelatedEntry("tile_slab", BuiltInRegistries.BLOCK));
            this.addChild("tile_wall", findRelatedEntry("tile_wall", BuiltInRegistries.BLOCK));
            this.addChild("tile_button", findRelatedEntry("tile_button", BuiltInRegistries.BLOCK));
            this.addChild("tile_pressure_plate", findRelatedEntry("tile_pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block bricks = this.findRelatedEntry("bricks", BuiltInRegistries.BLOCK);
        this.addChild("bricks", bricks);
        if (bricks != null) {
            this.addChild("brick_stairs", findChildBrickEntry("stairs"));
            this.addChild("brick_slab", findChildBrickEntry("slab"));
            this.addChild("brick_wall", findChildBrickEntry("wall"));
            this.addChild("brick_button", findChildBrickEntry("button"));
            this.addChild("brick_pressure_plate", findChildBrickEntry("pressure_plate"));
        }

        Block lamp = this.findRelatedEntry("lamp", BuiltInRegistries.BLOCK);
        this.addChild("lamp", lamp);
        if (lamp != null) {
            this.addChild("lamp_stairs", findRelatedEntry("lamp_stairs", BuiltInRegistries.BLOCK));
            this.addChild("lamp_slab", findRelatedEntry("lamp_slab", BuiltInRegistries.BLOCK));
            this.addChild("lamp_wall", findRelatedEntry("lamp_wall", BuiltInRegistries.BLOCK));
            this.addChild("lamp_button", findRelatedEntry("lamp_button", BuiltInRegistries.BLOCK));
            this.addChild("lamp_pressure_plate", findRelatedEntry("lamp_pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block pillar = this.findRelatedEntry("pillar", BuiltInRegistries.BLOCK);
        this.addChild("pillar", pillar);
        if (pillar != null) {
            this.addChild("pillar_stairs", findRelatedEntry("pillar_stairs", BuiltInRegistries.BLOCK));
            this.addChild("pillar_slab", findRelatedEntry("pillar_slab", BuiltInRegistries.BLOCK));
            this.addChild("pillar_wall", findRelatedEntry("pillar_wall", BuiltInRegistries.BLOCK));
            this.addChild("pillar_button", findRelatedEntry("pillar_button", BuiltInRegistries.BLOCK));
            this.addChild("pillar_pressure_plate", findRelatedEntry("pillar_pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block mosaic = this.findRelatedEntry("mosaic", BuiltInRegistries.BLOCK);
        this.addChild("mosaic", mosaic);
        if (mosaic != null) {
            this.addChild("mosaic_stairs", findRelatedEntry("mosaic", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("mosaic_slab", findRelatedEntry("mosaic", "slab", BuiltInRegistries.BLOCK));
            this.addChild("mosaic_wall", findRelatedEntry("mosaic", "wall", BuiltInRegistries.BLOCK));
            this.addChild("mosaic_button", findRelatedEntry("mosaic", "button", BuiltInRegistries.BLOCK));
            this.addChild("mosaic_pressure_plate", findRelatedEntry("mosaic", "pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block cut = this.findRelatedEntry("cut", BuiltInRegistries.BLOCK);
        this.addChild("cut", cut);
        if (Objects.nonNull(cut)) {
            this.addChild("cut_stairs", findRelatedEntry("cut", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("cut_slab", findRelatedEntry("cut", "slab", BuiltInRegistries.BLOCK));
            this.addChild("cut_wall", findRelatedEntry("cut", "wall", BuiltInRegistries.BLOCK));
            this.addChild("cut_button", findRelatedEntry("cut", "button", BuiltInRegistries.BLOCK));
            this.addChild("cut_pressure_plate", findRelatedEntry("cut", "pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block cracked = this.findRelatedEntry("cracked", BuiltInRegistries.BLOCK);
        this.addChild("cracked", cracked);
        if (Objects.nonNull(cracked)) {
            this.addChild("cracked_stairs", findRelatedEntry("cracked", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("cracked_slab", findRelatedEntry("cracked", "slab", BuiltInRegistries.BLOCK));
            this.addChild("cracked_wall", findRelatedEntry("cracked", "wall", BuiltInRegistries.BLOCK));
            this.addChild("cracked_button", findRelatedEntry("cracked", "button", BuiltInRegistries.BLOCK));
            this.addChild("cracked_pressure_plate", findRelatedEntry("cracked", "pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block cobbled = this.findRelatedEntry("cobbled", BuiltInRegistries.BLOCK);
        this.addChild("cobbled", cobbled);
        if (Objects.nonNull(cobbled)) {
            this.addChild("cobbled_stairs", findRelatedEntry("cobbled", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("cobbled_slab", findRelatedEntry("cobbled", "slab", BuiltInRegistries.BLOCK));
            this.addChild("cobbled_wall", findRelatedEntry("cobbled", "wall", BuiltInRegistries.BLOCK));
            this.addChild("cobbled_button", findRelatedEntry("cobbled", "button", BuiltInRegistries.BLOCK));
            this.addChild("cobbled_pressure_plate", findRelatedEntry("cobbled", "pressure_plate", BuiltInRegistries.BLOCK));
        }

        Block chiseled = this.findRelatedEntry("chiseled", BuiltInRegistries.BLOCK);
        this.addChild("chiseled", chiseled);
        if (Objects.nonNull(chiseled)) {
            this.addChild("chiseled_stairs", findRelatedEntry("chiseled", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("chiseled_slab", findRelatedEntry("chiseled", "slab", BuiltInRegistries.BLOCK));
            this.addChild("chiseled_wall", findRelatedEntry("chiseled", "wall", BuiltInRegistries.BLOCK));
            this.addChild("chiseled_button", findRelatedEntry("chiseled", "button", BuiltInRegistries.BLOCK));
            this.addChild("chiseled_pressure_plate", findRelatedEntry("chiseled", "pressure_plate", BuiltInRegistries.BLOCK));
        }

    }

    @Override
    protected void initializeChildrenItems() {

    }

    /// @param suffix concatenation of "brick_" + suffix
    private @Nullable Block findChildBrickEntry(String suffix) {
        var first = this.findRelatedEntry("brick_" + suffix, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry("bricks_" + suffix, BuiltInRegistries.BLOCK);
    }

    @SuppressWarnings("SameParameterValue")
    private @Nullable Block findBrickEntry(String pre, String post) {
        post = (post.isEmpty()) ? "" : "_" + post;

        var first = this.findRelatedEntry(pre,"brick" + post, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry(pre,"bricks" + post, BuiltInRegistries.BLOCK);
    }

    /**
     * @param prefixOrInfix parameter can be either prefix or infix
     *
     * @apiNote Example: TYPE_prefixOrInfix_suffix
     *          Example: prefixOrInfix_TYPE_suffix
     */
    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        if (id.toString().equals("minecraft:stone") && prefixOrInfix.equals("cobblestone")) {
            return reg.get(ResourceLocation.parse("cobblestone"));
        }

        if (!suffix.isEmpty()) suffix = "_" + suffix;
        ResourceLocation[] targets = {
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() +"_"+ prefixOrInfix + suffix),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), prefixOrInfix +"_"+ id.getPath() + suffix),
        };
        V found = null;
        for (var r : targets) {
            if (reg.containsKey(r)) {
                found = reg.get(r);
                break;
            }
        }
        return found;
    }

    @Override
    protected <V> V findRelatedEntry(String prefixOrInfix, Registry<V> reg) {
        return findRelatedEntry(prefixOrInfix, "", reg);
    }

    @Override
    /// Extra method to add object to decorativeBlockTypes or decorativeItemTypes
    public void addChild(String genericName, @Nullable Object obj) {
        if (obj != Items.AIR && obj != Blocks.AIR) {
            if (obj != null) {
                super.addChild(genericName, obj);

                if (obj instanceof Block foundBlock) {
                    if (!decorativeBlockTypes.containsKey(this)) {
                        ArrayList<Block> array = new ArrayList<>();
                        array.add(foundBlock);
                        decorativeBlockTypes.put(this, array);
                    }
                    else {
                        decorativeBlockTypes.get(this).add(foundBlock);
                    }
                }

                if (obj instanceof Item ITEM) {
                    if (!decorativeItemTypes.containsKey(this)) {
                        ArrayList<Item> array = new ArrayList<>();
                        array.add(ITEM);
                        decorativeItemTypes.put(this, array);
                    }
                    else {
                        decorativeItemTypes.get(this).add(ITEM);
                    }
                }

            }

        } else {
            throw new IllegalStateException("Tried to add air block/item to Block Type. Key " + genericName + ". This is a Moonlight bug, please report me");
        }
    }

}
