package com.mystic.rockyminerals.misc;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.TextureInfo;

import java.util.List;

public class ResourcesHelper {

    public static List<TextureInfo> Saltstone_Templates = List.of(
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

            TextureInfo.of("rockyminerals:stone_redstone_lamp", RockyMineral.res("block/template/stonetype_redstone_lamp_lit"), RockyMineral.res("block/mask/rocktype_redstone_lamp_common")).build(),
            TextureInfo.of("rockyminerals:stone_redstone_lamp", RockyMineral.res("block/template/stonetype_redstone_lamp_unlit"), RockyMineral.res("block/mask/rocktype_redstone_lamp_common")).build()
    );

    public static List<TextureInfo> BlueCacite_Templates = List.of(
            TextureInfo.of("rockyminerals:cobbled_blue_calcite", RockyMineral.res("block/cobbled_blue_calcite")).build(),
            TextureInfo.of("rockyminerals:cracked_blue_calcite", RockyMineral.res("block/cracked_blue_calcite")).build(),
            TextureInfo.of("rockyminerals:cut_blue_calcite", RockyMineral.res("block/cut_blue_calcite")).build(),
            TextureInfo.of("rockyminerals:mosaic_blue_calcite", RockyMineral.res("block/mosaic_blue_calcite")).build(),
            TextureInfo.of("rockyminerals:polished_blue_calcite", RockyMineral.res("block/polished_blue_calcite")).build(),
            TextureInfo.of("rockyminerals:blue_calcite_bricks", RockyMineral.res("block/blue_calcite_bricks")).build(),
            TextureInfo.of("rockyminerals:blue_calcite_lamp", RockyMineral.res("block/blue_calcite_lamp")).build(),
            TextureInfo.of("rockyminerals:blue_calcite_tile", RockyMineral.res("block/blue_calcite_tile")).build(),

            TextureInfo.of("rockyminerals:chiseled_blue_calcite", RockyMineral.res("block/chiseled_blue_calcite")).build(),
            TextureInfo.of("rockyminerals:chiseled_blue_calcite", RockyMineral.res("block/chiseled_blue_calcite_top")).build(),

            TextureInfo.of("rockyminerals:blue_calcite_pillar", RockyMineral.res("block/blue_calcite_pillar")).build(),
            TextureInfo.of("rockyminerals:blue_calcite_pillar", RockyMineral.res("block/blue_calcite_pillar_top")).build(),

            TextureInfo.of("rockyminerals:blue_calcite_redstone_lamp", RockyMineral.res("block/blue_calcite_redstone_lamp_lit"), RockyMineral.res("block/mask/rocktype_redstone_lamp_common")).build(),
            TextureInfo.of("rockyminerals:blue_calcite_redstone_lamp", RockyMineral.res("block/blue_calcite_redstone_lamp_unlit"), RockyMineral.res("block/mask/rocktype_redstone_lamp_common")).build()
    );
}
