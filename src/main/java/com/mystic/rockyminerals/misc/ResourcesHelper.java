package com.mystic.rockyminerals.misc;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.api.TextureInfo;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ResourcesHelper {

/// WIP
//    public static List<ResourceLocation> baseFileReference = List.of(
//
//            Main.res("stonetype_redstone_lamp"),

//            Main.res("stonetype"),
//            Main.res("stonetype_tile"),
//            Main.res("stonetype_bricks"),
//            Main.res("stonetype_lamp"),
//            Main.res("stonetype_pillar"),
//            Main.res("polished_stonetype"),
//            Main.res("mosaic_stonetype"),
//            Main.res("cut_stonetype"),
//            Main.res("cracked_stonetype"),
//            Main.res("cobbled_stonetype"),
//            Main.res("chiseled_stonetype"),
//
//            Main.res("stonetype_X_button"),
//            Main.res("stonetype_X_pressure_plate"),
//            Main.res("stonetype_X_slab"),
//            Main.res("stonetype_X_stairs"),
//            Main.res("stonetype_X_wall"),
//
//            Main.res("X_button"),
//            Main.res("X_pressure_plate"),
//            Main.res("X_slab"),
//            Main.res("X_stairs"),
//            Main.res("X_wall")
//
//    );

    public static List<TextureInfo> textureReference = List.of(
            TextureInfo.of("rockyminerals:cobbled_stone", Main.res("block/template/cobbled_stonetype")).build(),
            TextureInfo.of("rockyminerals:cracked_stone", Main.res("block/template/cracked_stonetype")).build(),
            TextureInfo.of("rockyminerals:cut_stone", Main.res("block/template/cut_stonetype")).build(),
            TextureInfo.of("rockyminerals:mosaic_stone", Main.res("block/template/mosaic_stonetype")).build(),
            TextureInfo.of("rockyminerals:polished_stone", Main.res("block/template/polished_stonetype")).build(),
            TextureInfo.of("rockyminerals:stone_bricks", Main.res("block/template/stonetype_bricks")).build(),
            TextureInfo.of("rockyminerals:stone_lamp", Main.res("block/template/stonetype_lamp")).build(),
            TextureInfo.of("rockyminerals:stone_tile", Main.res("block/template/stonetype_tile")).build(),

            TextureInfo.of("rockyminerals:chiseled_stone", Main.res("block/template/chiseled_stonetype")).build(),
            TextureInfo.of("rockyminerals:chiseled_stone", Main.res("block/template/chiseled_stonetype_top")).build(),

            TextureInfo.of("rockyminerals:stone_pillar", Main.res("block/template/stonetype_pillar")).build(),
            TextureInfo.of("rockyminerals:stone_pillar", Main.res("block/template/stonetype_pillar_top")).build(),

            TextureInfo.of("rockyminerals:stone_redstone_lamp", Main.res("block/template/stonetype_redstone_lamp_lit"), Main.res("block/template/stonetype_redstone_lamp_common_m")).build(),
            TextureInfo.of("rockyminerals:stone_redstone_lamp", Main.res("block/template/stonetype_redstone_lamp_unlit"), Main.res("block/template/stonetype_redstone_lamp_common_m")).build()
    );
}
