package com.mystic.rockyminerals.misc;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.TextureInfo;

import java.util.List;

public class ResourcesHelper {

/// WIP
//    public static List<ResourceLocation> baseFileReference = List.of(
//
//            RockyMineral.res("stonetype_redstone_lamp"),

//            RockyMineral.res("stonetype"),
//            RockyMineral.res("stonetype_tile"),
//            RockyMineral.res("stonetype_bricks"),
//            RockyMineral.res("stonetype_lamp"),
//            RockyMineral.res("stonetype_pillar"),
//            RockyMineral.res("polished_stonetype"),
//            RockyMineral.res("mosaic_stonetype"),
//            RockyMineral.res("cut_stonetype"),
//            RockyMineral.res("cracked_stonetype"),
//            RockyMineral.res("cobbled_stonetype"),
//            RockyMineral.res("chiseled_stonetype"),
//
//            RockyMineral.res("stonetype_X_button"),
//            RockyMineral.res("stonetype_X_pressure_plate"),
//            RockyMineral.res("stonetype_X_slab"),
//            RockyMineral.res("stonetype_X_stairs"),
//            RockyMineral.res("stonetype_X_wall"),
//
//            RockyMineral.res("X_button"),
//            RockyMineral.res("X_pressure_plate"),
//            RockyMineral.res("X_slab"),
//            RockyMineral.res("X_stairs"),
//            RockyMineral.res("X_wall")
//
//    );

    public static List<TextureInfo> textureReference = List.of(
            TextureInfo.of("rockyminerals:cobbled_stone", RockyMineral.res("block/template/cobbled_stonetype")).build(),
            TextureInfo.of("rockyminerals:cracked_stone", RockyMineral.res("block/template/cracked_stonetype")).build(),
            TextureInfo.of("rockyminerals:cut_stone", RockyMineral.res("block/template/cut_stonetype")).build(),
            TextureInfo.of("rockyminerals:mosaic_stone", RockyMineral.res("block/template/mosaic_stonetype")).build(),
            TextureInfo.of("rockyminerals:polished_stone", RockyMineral.res("block/template/polished_stonetype")).build(),
            TextureInfo.of("rockyminerals:stone_bricks", RockyMineral.res("block/template/stonetype_bricks")).build(),
            TextureInfo.of("rockyminerals:stone_lamp", RockyMineral.res("block/template/stonetype_lamp")).build(),
            TextureInfo.of("rockyminerals:stone_tile", RockyMineral.res("block/template/stonetype_tile")).build(),

            TextureInfo.of("rockyminerals:chiseled_stone", RockyMineral.res("block/template/chiseled_stonetype")).build(),
            TextureInfo.of("rockyminerals:chiseled_stone", RockyMineral.res("block/template/chiseled_stonetype_top")).build(),

            TextureInfo.of("rockyminerals:stone_pillar", RockyMineral.res("block/template/stonetype_pillar")).build(),
            TextureInfo.of("rockyminerals:stone_pillar", RockyMineral.res("block/template/stonetype_pillar_top")).build(),

            TextureInfo.of("rockyminerals:stone_redstone_lamp", RockyMineral.res("block/template/stonetype_redstone_lamp_lit"), RockyMineral.res("block/template/stonetype_redstone_lamp_common_m")).build(),
            TextureInfo.of("rockyminerals:stone_redstone_lamp", RockyMineral.res("block/template/stonetype_redstone_lamp_unlit"), RockyMineral.res("block/template/stonetype_redstone_lamp_common_m")).build()
    );
}
